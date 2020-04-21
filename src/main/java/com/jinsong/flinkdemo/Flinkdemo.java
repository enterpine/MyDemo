package com.jinsong.flinkdemo;


import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Flinkdemo {
    public static void main(String args[]) throws Exception{

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> text = env.readTextFile("/Users/jinsong/GitProject/MyDemo/data/flinkdata/data.txt");


        DataStream<String> result = text.map(a->a);

        result.print();

        env.execute("demo");
    }
}
