package com.jinsong.mutithreads;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

@SuppressWarnings("serial")
public class SocketWindowFlinkDemo {
    public static void main(String[] args){

        final String hostname;
        final int port;

        try {
            final ParameterTool params = ParameterTool.fromArgs(args);
            hostname = params.has("hostname") ? params.get("hostname") : "localhost";
            port = params.getInt("port");
        }
        catch(Exception e){
            System.out.println("Hostname and port are not provided!");
            System.out.println(e.toString());
            return;
        }

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> text = env.socketTextStream(hostname,port);

        DataStream<WordWithCount> windowCounts = text.flatMap(
                new FlatMapFunction<String, WordWithCount>() {
                    public void flatMap(String s, Collector<WordWithCount> collector) throws Exception {
                        for(String word:s.split("\\s")){
                            collector.collect(new WordWithCount(word,1));
                        }
                    }
                }
        );

//        DataStream<WordWithCount> windowCounts2 = text
//
//                .flatMap(new FlatMapFunction<String, WordWithCount>() {
//                    //@Override
//                    public void flatMap(String value, Collector<WordWithCount> out) {
//                        for (String word : value.split("\\s")) {
//                            out.collect(new WordWithCount(word, 1L));
//                        }
//                    }
//                })
//
//                .keyBy("word")
//                .timeWindow(Time.seconds(5))
//
//                .reduce(new ReduceFunction<WordWithCount>() {
//                    //@Override
//                    public WordWithCount reduce(WordWithCount a, WordWithCount b) {
//                        return new WordWithCount(a.word, a.count + b.count);
//                    }
//                });


    }
    public static class WordWithCount {

        public String word;
        public long count;

        public WordWithCount() {}

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + " : " + count;
        }
    }
}
