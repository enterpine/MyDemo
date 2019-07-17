package com.jinsong.mr.sort;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public  class SortDemoMapper extends Mapper<LongWritable, Text, IntWritable,Text> {

    @Override
    public void map(LongWritable key, Text value, org.apache.hadoop.mapreduce.Mapper.Context context)
            throws IOException,InterruptedException{

        System.out.print("Mapper input:");
        System.out.print(key.get());
        System.out.println("\t"+value.toString());

        String line = value.toString();
        int airtemp = Integer.parseInt(line.substring(87,92));


        context.write(new IntWritable(airtemp),value);
    }

}
