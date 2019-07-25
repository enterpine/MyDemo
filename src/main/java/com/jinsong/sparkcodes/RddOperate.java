package com.jinsong.sparkcodes;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.api.java.function.VoidFunction;
import scala.Function1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RddOperate {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf();
        //conf.setMaster("local");
        //conf.setMaster("spark://songdeMacBook-Pro.local:7077 ");
        //conf.setAppName("demo");

        JavaSparkContext jsc = new JavaSparkContext(conf);

        JavaRDD<String> lines = jsc.textFile("/input/Sample.txt",1);
        //JavaRDD<String> lines = jsc.textFile("/Users/jinsong/GitProject/MyDemo/data/input/Sample.txt",1);

        JavaRDD<String> rdd = lines.filter(new Function<String,Boolean>(){
            public Boolean call(String x){
               return x.contains("2017");
            }
        });

        rdd.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                String year = s.substring(15,19);
                String tmp = s.substring(87,92);
                System.out.print(year+" "+tmp+"\n");
            }
        });


        //rd.cache();
        List<String> a = rdd.collect();

        Iterator<String> it = a.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}
