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
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;


public class SortDemoUsingHashPart extends Configured implements Tool {

    public static class Mapper1 extends Mapper<LongWritable, Text, IntWritable,Text> {

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


    public int run(String[] args) throws Exception{


        Job job = new Job(getConf(),"MaxTemp");
        job.setJarByClass(getClass());

        //FileInputFormat.addInputPath(job,new Path(args[0]));
        FileInputFormat.setInputPaths(job
                ,new Path(args[0])
                ,new Path(args[1])
        );
        FileOutputFormat.setOutputPath(job,new Path(args[2]));


        job.setMapperClass(Mapper1.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(4);


        return job.waitForCompletion(true)?0:1;
    }

    public static void localtest(String[] args) throws Exception{

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        conf.set("mapreduce.framework,name","local");
        conf.setInt("mapreduce.task.io.sort.mb",1);


        Path input = new Path("/Users/jinsong/GitProject/MyDemo/data/output1/part-m-00000");
        Path input2 = new Path("/Users/jinsong/GitProject/MyDemo/data/output1/part-m-00001");

        Path output = new Path("/Users/jinsong/GitProject/MyDemo/data/outputUsingHashPartition/");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output,true);



        SortDemoUsingHashPart driver = new SortDemoUsingHashPart();
        driver.setConf(conf);

        int exitCode = driver.run(new String[]{
                input.toString(),input2.toString(),output.toString()
        });

        //int exitCode = ToolRunner.run(new SortDemoUsingHashPart(),args);
        System.exit(exitCode);


    }
    public static void main(String[] args) throws Exception{

        int exitCode = ToolRunner.run(new SortDemoUsingHashPart(), args);
        System.exit(exitCode);

    }


}