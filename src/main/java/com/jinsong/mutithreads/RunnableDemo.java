package com.jinsong.mutithreads;
import java.lang.Runnable;

public class RunnableDemo implements Runnable{

    public int a = 0;

    public synchronized  void run(){
        for(int i=0;i<100000000;i++) {
            this.a = a + 1;
            //System.out.println(a);
        }
        System.out.println(a);
    }


    public static void main(String[] args) throws Exception{


        RunnableDemo rd = new RunnableDemo();

        Thread ta = new Thread(rd);
        Thread tb = new Thread(rd);

        ta.setPriority(10);
        ta.start();
        //ta.join();


        tb.start();
        //tb.join();

        System.out.println("wed"+rd.a);
    }
}

