#!/usr/bin/env bash

spark-submit --master yarn \
--name "demo" \
--deploy-mode client \
--class com.jinsong.sparkcodes.RddOperate \
/Users/jinsong/GitProject/MyDemo/target/MyDemo-1.0-SNAPSHOT.jar

spark-submit --master local \
--name "demo" \
--class com.jinsong.sparkcodes.RddOperate \
/Users/jinsong/GitProject/MyDemo/target/MyDemo-1.0-SNAPSHOT.jar

spark-submit --master spark://localhost:7070 \
--name "demo" \
--class com.jinsong.sparkcodes.RddOperate \
/Users/jinsong/GitProject/MyDemo/target/MyDemo-1.0-SNAPSHOT.jar