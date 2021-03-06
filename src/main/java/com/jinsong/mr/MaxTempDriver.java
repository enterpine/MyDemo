package com.jinsong.mr;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.map.InverseMapper;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.FileInputStream;

public class MaxTempDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception{

        Job job = new Job(getConf(),"MaxTemp");
        job.setJarByClass(getClass());

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.setMapperClass(MaxTempMapper.class);
        //job.setMapperClass(InverseMapper.class);
        //job.setCombinerClass(MaxTempReducer.class);
        //job.setReducerClass(MaxTempReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //job.setSortComparatorClass();


        return job.waitForCompletion(true)?0:1;
    }
//    public static void main(String[] args) throws Exception{
//        int exitCode = ToolRunner.run(new MaxTempDriver(),args);
//        System.exit(exitCode);
//    }
}
