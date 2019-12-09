package com.jinsong.javabase;

public class Fibo {
    public static void main(String[] args){
        System.out.println(fibo(5));
        System.out.println(fiboTail(2,1,1));
    }

    private static int fibo( int n){
        if(n==2||n==1){
            return 1;
        }
        return fibo(n-2)+fibo(n-1);
    }

    private static int fiboTail(int n,int i,int s){
        if(n==1){return 1;}
       if(n == 2){return s;}
       return fiboTail(n-1,s,i+s);
    }
}
