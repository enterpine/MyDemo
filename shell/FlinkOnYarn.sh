#!/usr/bin/env bash

flink run -c "com.jinsong.flinkdemo.Flinkdemo" /Users/jinsong/GitProject/MyDemo/target/MyDemo-1.0-SNAPSHOT.jar

flink run -m yarn-cluster \
-yn 1 \
-c "com.jinsong.flinkdemo.Flinkdemo" \
/Users/jinsong/GitProject/MyDemo/target/MyDemo-1.0-SNAPSHOT.jar