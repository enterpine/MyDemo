package com.jinsong.mr.sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapred.lib.TotalOrderPartitioner;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileAsBinaryOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class SortBaseDriver extends Configured implements Tool {



    public int run(String[] args) throws Exception{


        Job job = new Job(getConf(),"MaxTemp");
        job.setJarByClass(getClass());

        //FileInputFormat.addInputPath(job,new Path(args[0]));
        FileInputFormat.setInputPaths(job
                ,new Path(args[0])
                ,new Path(args[1])
        );
        FileOutputFormat.setOutputPath(job,new Path(args[2]));


        job.setMapperClass(SortDemoMapper.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);


//        job.setOutputFormatClass(SequenceFileOutputFormat.class);
//        SequenceFileOutputFormat.setCompressOutput(job,true);
//        SequenceFileOutputFormat.setOutputCompressorClass(job,GzipCodec.class);
//        SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.BLOCK);

        job.setNumReduceTasks(0);


        return job.waitForCompletion(true)?0:1;
    }

    public static void runlocal(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        conf.set("mapreduce.framework,name","local");
        conf.setInt("mapreduce.task.io.sort.mb",1);


        Path input = new Path("/Users/jinsong/GitProject/MyDemo/data/input/Sample.txt");
        Path input2 = new Path("/Users/jinsong/GitProject/MyDemo/data/input/Sample2.txt");
        Path output = new Path("/Users/jinsong/GitProject/MyDemo/data/output1/");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output,true);



        SortBaseDriver driver = new SortBaseDriver();
        driver.setConf(conf);

        int exitCode = driver.run(new String[]{
                input.toString(),input2.toString(),output.toString()
        });

        //int exitCode = ToolRunner.run(new SortDemoUsingHashPart(),args);
        System.exit(exitCode);


    }

    public static void main(String[] args) throws Exception{
        int exitCode = ToolRunner.run(new SortBaseDriver(), args);
        System.exit(exitCode);
    }

}