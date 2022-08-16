package com.jinsong.mr.sort;


import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.Iterator;


public class SortByTempGlobleOverPart extends Configured implements Tool {
    //全局排序，通过实现Partitioner子类，重写getPartition方法，使分区有序
    //自定义排序，通过实现WritableComparator子类，重写compare方法，实现自定义排序
    //分组

    public static class Mapper2 extends Mapper<LongWritable, Text, IntWritable,Text> {
        @Override
        public void map(LongWritable key, Text value, org.apache.hadoop.mapreduce.Mapper.Context context)
                throws IOException,InterruptedException{

            String str = value.toString();
            String [] words = str.split("\t");

            Integer outputkey  = Integer.parseInt(words[0]);
            String outputvalue = words[1].substring(15,19);

            context.write(new IntWritable(outputkey),new Text(outputvalue));
        }

    }

    public static class GlobleSortPartitioner extends Partitioner<IntWritable,Text>{
        @Override
        public int getPartition(IntWritable key,Text value,int numPartitions){
            int a = key.get();
            if(a>=-100 && a<=0 ){
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

    public static class KeyComparator extends WritableComparator{
        protected KeyComparator(){
            super(IntWritable.class,true);

        }
        @Override
        public int compare(WritableComparable w1,WritableComparable w2) {
            IntWritable it1 = (IntWritable) w1;
            IntWritable it2 = (IntWritable) w2;
            int cmp = it1.compareTo(it2);
            return -cmp;

        }
    }

    public static class GroupComparator extends WritableComparator{
        protected GroupComparator(){
            super(IntWritable.class,true);
        }

        @Override
        public int compare(WritableComparable w1,WritableComparable w2){
            IntWritable it1 = (IntWritable) w1;
            IntWritable it2 = (IntWritable) w2;
            if(it1.get() == -11 && it2.get() == -52){
                return 0;
            }
            else{
                return it1.compareTo(it2);
            }
        }
    }

    static class Reducer2 extends Reducer<IntWritable,Text,IntWritable,Text>{
        @Override
        protected void reduce(IntWritable key, Iterable<Text> values,Context context)
                throws IOException,InterruptedException{

            Iterator it =values.iterator();

            StringBuilder output = new StringBuilder();

            while(it.hasNext()){
                output.append(it.next().toString()+"|");
            }
            Text t = new Text(output.toString());

            context.write(key,t);
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
        job.setReducerClass(Reducer2.class);

        //job.setPartitionerClass(HashPartitioner.class);
        job.setPartitionerClass(GlobleSortPartitioner.class);//默尔使用HashPartitioner
        job.setSortComparatorClass(KeyComparator.class);//自定义排序
        job.setGroupingComparatorClass(GroupComparator.class); //自定义分组

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