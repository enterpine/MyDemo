package com.jinsong.chinamobile;

import java.util.Scanner;

public class Question1 {
    static int result=0;
    public static void main(String[] args){
        //BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try {
            while (1==1) {
                result=0;
                //String strin = br.readLine();
                int strin = sc.nextInt();
                int N = strin;
                solve(0, N, 1);
                solve(0, N, 2);
                System.out.println(result);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage().toString());
        }
    }
    public static void solve( int le,int N,int step){
        if (le+step==N){
            result=result+1;
            return;
        }
        else if (le+step>N){return;}
        else {
            solve(le+step,N,1);
            solve(le+step,N,2);
        }
    }
}
