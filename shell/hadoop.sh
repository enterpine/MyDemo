#!/usr/bin/env bash


hadoop jar /Users/jinsong/GitProject/MyDemo/target/MyDemo-1.0-SNAPSHOT.jar com.jinsong.mr.sort.SortBaseDriver \
-D mapreduce.job.reduces=5 /input/Sample.txt /input/Sample2.txt /output

hadoop jar /Users/jinsong/GitProject/MyDemo/target/MyDemo-1.0-SNAPSHOT.jar com.jinsong.mr.sort.SortDemoUsingHashPart \
-D mapreduce.job.reduces=5  /output/part-m-00000  /output/part-m-00001 /outputHashPart