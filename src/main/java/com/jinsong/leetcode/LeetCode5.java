package com.jinsong.leetcode;

public class LeetCode5 {

    public static String getStr(String s,int i){

        int j=0;
        int maxlen = 1;
        int startindex = i;


        while(1 == 1){

            j = j + 1;

            if(i+j<s.length() && i-j>=0) {
                //System.out.print(i+" "+j+"\n");
                if (s.charAt(i + j) == s.charAt(i - j)) {
                    maxlen = 2 * j + 1;
                    startindex = i-j;
                }
                else{
                    break;
                }
            }else{
                break;
            }

        }
        String rtn = new String(s.substring(startindex,startindex+maxlen));
        return rtn;
    }

    public static String getStr2(String s,int i){

        int j=0;
        int maxlen = 1;
        int startindex = i;


        while(1 == 1){

            j = j + 1;

            if(i+j<s.length() && i-j+1>=0) {
                //System.out.print(i+" "+j+"\n");
                if (s.charAt(i + j) == s.charAt(i - j + 1)) {
                    maxlen = 2 * j ;
                    startindex = i-j + 1;
                }
                else{
                    break;
                }
            }else{
                break;
            }

        }
        String rtn = new String(s.substring(startindex,startindex+maxlen));
        return rtn;
    }

    public static  String longestPalindrome(String s) {

        String rtn = new String("");

        int maxlen = 0;

        for(int i = 0;i<s.length();i++){
            String tmpstr = getStr(s,i);
            if(tmpstr.length()>maxlen){
                maxlen = tmpstr.length();
                rtn = tmpstr;
            }

            String tmpstr2 = getStr2(s,i);
            if(tmpstr2.length()>maxlen){
                maxlen = tmpstr2.length();
                rtn = tmpstr2;
            }

        }

        return rtn;
    }

    public static void  main(String[] args){
        String input ="1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        System.out.println(longestPalindrome(input));
    }
}
