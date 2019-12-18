package com.jinsong.leetcode;

public class LeetCode5_dp {
    public static  String longestPalindrome(String s) {

        if(s.equals("")){
            return "";
        }

        class dpnode{
            int sindex = 0;
            int endindex = 0;
            int flag = 0;
        }

        char[] s1 = s.toCharArray();
        char[] s2 = new char[s1.length];
        for(int i = s1.length-1; i>=0; i--){
            s2[s1.length-1-i] = s1[i];
        }
        dpnode[][] dp = new dpnode[s1.length][s2.length];
        dpnode maxdpnode = new dpnode();
        for(int i = 0;i<s1.length;i++){
            for(int j = 0;j<s2.length;j++){

                if(s1[i] == s2[j]){
                    if(i>0 && j>0) {
                        if (dp[i - 1][j - 1].flag == 1) {
                            dp[i][j] = new dpnode();
                            dp[i][j].endindex = i;
                            dp[i][j].sindex = dp[i - 1][j - 1].sindex;
                            dp[i][j].flag = 1;
                            if( j + dp[i-1][j-1].sindex == s1.length-1) {
                                maxdpnode = (dp[i][j].endindex - dp[i][j].sindex) > (maxdpnode.endindex - maxdpnode.sindex) ? dp[i][j] : maxdpnode;
                            }
                        }
                        else{
                            dp[i][j] = new dpnode();
                            dp[i][j].endindex=i;
                            dp[i][j].sindex=i;
                            dp[i][j].flag=1;
                            maxdpnode = (dp[i][j].endindex-dp[i][j].sindex)>(maxdpnode.endindex-maxdpnode.sindex)?dp[i][j]:maxdpnode;
                        }
                    }
                    else{
                        dp[i][j] = new dpnode();
                        dp[i][j].endindex=i;
                        dp[i][j].sindex=i;
                        dp[i][j].flag=1;
                        maxdpnode = (dp[i][j].endindex-dp[i][j].sindex)>(maxdpnode.endindex-maxdpnode.sindex)?dp[i][j]:maxdpnode;
                    }
                }
                else{
                    dp[i][j] = new dpnode();
                    dp[i][j].sindex=i;
                    dp[i][j].endindex=i;
                    dp[i][j].flag=0;
                }

            }
        }

//        System.out.println(maxdpnode.sindex);
//        System.out.println(maxdpnode.endindex);

        StringBuffer sb = new StringBuffer();
        for(int i=maxdpnode.sindex;i<=maxdpnode.endindex;i++){
            sb.append(s1[i]);
        }
        String rtn = sb.toString();
        return rtn;
    }
    public static  String longestPalindrome2(String s) {

        if(s.equals("")){
            return "";
        }

        char[] s1 = s.toCharArray();
        char[] s2 = new char[s1.length];

        for(int i = s1.length-1; i>=0; i--){
            s2[s1.length-1-i] = s1[i];
        }


        int[][] sindex = new int[s1.length][s2.length];
        int[][] endindex = new int[s1.length][s2.length];

        int maxsindex = 0;
        int maxendindex = 0;


        for(int i = 0;i<s1.length;i++){
            for(int j = 0;j<s2.length;j++){

                if(s1[i] == s2[j]){
                    if(i>0 && j>0) {
                        if (s1[i-1] == s2[j-1]) {
                            endindex[i][j] = i;
                            sindex[i][j] = sindex[i - 1][j - 1];

                            if( j + sindex[i-1][j-1] == s1.length-1) {
                                maxsindex = (endindex[i][j] - sindex[i][j])> (maxendindex - maxsindex)? sindex[i][j] : maxsindex;
                                maxendindex = (endindex[i][j] - sindex[i][j])> (maxendindex - maxsindex)? endindex[i][j] : maxendindex;
                            }
                        }
                        else{
                            endindex[i][j]=i;
                            sindex[i][j]=i;
                            maxsindex = (endindex[i][j] - sindex[i][j])> (maxendindex - maxsindex)? sindex[i][j] : maxsindex;
                            maxendindex = (endindex[i][j] - sindex[i][j])> (maxendindex - maxsindex)? endindex[i][j] : maxendindex;
                        }
                    }
                    else{
                        endindex[i][j]=i;
                        sindex[i][j]=i;
                        maxsindex = (endindex[i][j] - sindex[i][j])> (maxendindex - maxsindex)? sindex[i][j] : maxsindex;
                        maxendindex = (endindex[i][j] - sindex[i][j])> (maxendindex - maxsindex)? endindex[i][j] : maxendindex;
                    }
                }
                else{
                    endindex[i][j]=i;
                    sindex[i][j]=i;
                }

            }
        }

//        System.out.println(maxdpnode.sindex);
//        System.out.println(maxdpnode.endindex);

        StringBuffer sb = new StringBuffer();
        for(int i= maxsindex;i<=maxendindex;i++){
            sb.append(s1[i]);
        }
        String rtn = sb.toString();
        return rtn;
    }

    public static void  main(String[] args){
        String input ="1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";

        System.out.println(longestPalindrome2(input));
    }

}
