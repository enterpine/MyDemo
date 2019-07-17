package com.jinsong.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class MrLocalTest {

    public void test() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        conf.set("mapreduce.framework,name","local");
        conf.setInt("mapreduce.task.io.sort.mb",1);

        Path input = new Path("/Users/jinsong/GitProject/MyDemo/data/input/Sample.txt");
        Path output = new Path("/Users/jinsong/GitProject/MyDemo/data/output");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output,true);

        MaxTempDriver driver = new MaxTempDriver();
        driver.setConf(conf);

        int exitCode = driver.run(new String[]{
                input.toString(),output.toString()
        });



    }
    public static void main(String[] args) throws Exception{
        MrLocalTest t = new MrLocalTest();
        t.test();
    }

}
