package com.jinsong.mutithreads;

import java.util.concurrent.Callable;
import java.lang.String;
import java.util.concurrent.FutureTask;

public class CallableDemo implements Callable<Integer> {

    public Integer call() throws Exception{
        int re=0;
        for(int i=0;i<10;i++){
            System.out.println(i);
            re++;
        }
        return re;
    }

    public static void main(String[] args) throws Exception{
        CallableDemo cd = new CallableDemo();
        FutureTask<Integer> ft = new FutureTask(cd);
        Thread ta = new Thread(ft);
        ta.start();
        int a = ft.get();
        System.out.println(a);

    }

}
