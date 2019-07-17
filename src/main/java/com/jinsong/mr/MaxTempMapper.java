package com.jinsong.mr;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTempMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

    @Override
    public void map(LongWritable key, Text value, org.apache.hadoop.mapreduce.Mapper.Context context)
            throws  IOException,InterruptedException{
        System.out.print("Mapper input:");
        System.out.print(key.get());
        System.out.println("\t"+value.toString());
        String line = value.toString();
        String year = line.substring(15,19);
        int airtemp = Integer.parseInt(line.substring(87,92));
        context.write(new Text(year),new IntWritable(airtemp));
    }

}
