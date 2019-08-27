package com.jinsong.hadoop;

import org.apache.hadoop.io.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SeriablizeTest {
    public static void main(String args[]) throws IOException{
        IntWritable writable = new IntWritable();
        ArrayWritable a = new ArrayWritable(Text.class);
        ArrayPrimitiveWritable b = new ArrayPrimitiveWritable();
        TwoDArrayWritable c = new TwoDArrayWritable(Text.class);

        MapWritable d = new MapWritable();
        SortedMapWritable e = new SortedMapWritable();

        writable.set(163);
        byte[] bytes = seriablize(writable);

        System.out.println(bytes.toString());

    }
    public static byte[] seriablize(Writable writable) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataout = new DataOutputStream(out);

        writable.write(dataout);
        dataout.close();

        return out.toByteArray();
    }
}
