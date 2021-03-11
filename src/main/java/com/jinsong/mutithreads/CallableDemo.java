package com.jinsong.mutithreads;

import java.util.concurrent.Callable;
import java.lang.String;
import java.util.concurrent.FutureTask;

public class CallableDemo implements Callable<Integer> {

    public int re  = 0;

    public synchronized Integer call() throws Exception{
        for(int i=0;i<1000000;i++){
            re++;
        }
        return re;
    }

    public static void main(String[] args) throws Exception{

        CallableDemo cd = new CallableDemo();

        FutureTask<Integer> ft = new FutureTask(cd);

        Thread ta = new Thread(ft);
        Thread tb = new Thread(ft);

        ta.start();
        tb.start();

        ta.join();
        tb.join();

        int a = ft.get();

        System.out.println("result->"+a);

    }

}
