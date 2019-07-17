package com.jinsong.mr.sort;

import com.jinsong.mr.sort.SortDemoDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class SortDemoLocal {

    public void test() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        conf.set("mapreduce.framework,name","local");
        conf.setInt("mapreduce.task.io.sort.mb",1);

        Path input = new Path("/Users/jinsong/GitProject/flinkdemo/data/input/Sample.txt");
        Path output = new Path("/Users/jinsong/GitProject/flinkdemo/data/output/");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output,true);

        SortDemoDriver driver = new SortDemoDriver();
        driver.setConf(conf);

        int exitCode = driver.run(new String[]{
                input.toString(),output.toString()
        });



    }
    public static void main(String[] args) throws Exception{
        SortDemoLocal t = new SortDemoLocal();
        t.test();
    }

}
