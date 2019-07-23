package com.jinsong.mr.sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;


public class SortByTempGlobleOverPart extends Configured implements Tool {
    //部分排序，使用IntWritable排序，分区内是排序的 跨分区不排序

    public static class Mapper2 extends Mapper<LongWritable, Text, IntWritable,Text> {
        @Override
        public void map(LongWritable key, Text value, org.apache.hadoop.mapreduce.Mapper.Context context)
                throws IOException,InterruptedException{

            String str = value.toString();
            String [] words = str.split("\t");

            Integer outputkey  = Integer.parseInt(words[0]);
            String outputvalue = words[1];

            context.write(new IntWritable(outputkey),new Text(outputvalue));
        }

    }

    public static class GlobleSortPartitioner extends Partitioner<IntWritable,Text>{
        @Override
        public int getPartition(IntWritable key,Text value,int numPartitions){
            int a = key.get();
            if(a>=-50 && a<=0 ){
                return 0;
            }
            else if(a>0 && a<=50){
                return 1;
            }
            else if(a>50 && a<=100){
                return 2;
            }
            else if(a>100 && a<=150){
                return 3;
            }
            else if(a>150 && a<=200){
                return 4;
            }
            else if(a>200 && a<=250){
                return 5;
            }
            else if(a>250 && a<=300){
                return 6;
            }
            else {
                return 7;
            }
        }
    }


    public int run(String[] args) throws Exception{

        Job job = new Job(getConf(),"SortByTempUsingHashPart");
        job.setJarByClass(getClass());

        //FileInputFormat.addInputPath(job,new Path(args[0]));
        FileInputFormat.setInputPaths(job
                ,new Path(args[0])
                ,new Path(args[1])
        );
        FileOutputFormat.setOutputPath(job,new Path(args[2]));


        job.setMapperClass(Mapper2.class);

        job.setPartitionerClass(GlobleSortPartitioner.class);//默尔使用HashPartitioner

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        //job.setNumReduceTasks(8);


        return job.waitForCompletion(true)?0:1;
    }

    public static void main(String[] args) throws Exception{

        int exitCode = ToolRunner.run(new SortByTempGlobleOverPart(), args);
        System.exit(exitCode);

    }


}