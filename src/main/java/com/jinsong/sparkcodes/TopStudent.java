package com.jinsong.sparkcodes;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class TopStudent {
    public static void main(String[] args){
        SparkConf sparkConf = new SparkConf();
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        JavaRDD<String> lines = sc.textFile("/input/GradeTable");


    }
}
