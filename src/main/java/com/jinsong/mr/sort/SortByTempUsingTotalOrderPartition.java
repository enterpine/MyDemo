package com.jinsong.mr.sort;

import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapred.lib.InputSampler;
import org.apache.hadoop.mapred.lib.TotalOrderPartitioner;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileAsBinaryOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;


public class SortByTempUsingTotalOrderPartition extends Configured implements Tool {

    //TotalOrderPartitioner依赖于一个Sequence file来distribute keys


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


    public int run(String[] args) throws Exception{


        Job job = new Job(getConf(),"SortByTempUsingTotalOrderPartition");
        job.setJarByClass(getClass());

        //FileInputFormat.addInputPath(job,new Path(args[0]));

        FileInputFormat.setInputPaths(job
                ,new Path(args[0])
                ,new Path(args[1])
        );


        FileOutputFormat.setOutputPath(job,new Path(args[2]));


        job.setMapperClass(Mapper2.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        job.setPartitionerClass(TotalOrderPartitioner.class);

        //InputSampler.Sampler<LongWritable,Text> sampler = new InputSampler.RandomSampler<LongWritable, Text>(0.1,1000,5);

        //InputSampler.writePartitionFile(job,sampler);

//        Configuration conf = job.getConfiguration();
//        String partitionFile = TotalOrderPartitioner.getPartitionFile(conf);
//        URI partitionUri = new URI(partitionFile);
//        job.addCacheFile(partitionUri);

        job.setNumReduceTasks(5);


        return job.waitForCompletion(true)?0:1;
    }

    public static void localtest(String[] args) throws Exception{

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        conf.set("mapreduce.framework,name","local");
        conf.setInt("mapreduce.task.io.sort.mb",1);


        Path input = new Path("/Users/jinsong/GitProject/MyDemo/data/output1/part-m-00000");
        Path input2 = new Path("/Users/jinsong/GitProject/MyDemo/data/output1/part-m-00001");

        Path output = new Path("/Users/jinsong/GitProject/MyDemo/data/outputUsingTotalOrderPart/");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output,true);



        SortByTempUsingTotalOrderPartition driver = new SortByTempUsingTotalOrderPartition();
        driver.setConf(conf);

        int exitCode = driver.run(new String[]{
                input.toString(),input2.toString(),output.toString()
        });

        //int exitCode = ToolRunner.run(new SortByTempUsingHashPart(),args);
        System.exit(exitCode);


    }

    public static void main(String[] args) throws  Exception{
        int exitcode = ToolRunner.run(new SortByTempUsingTotalOrderPartition(), args);
        System.exit(exitcode);
    }


}