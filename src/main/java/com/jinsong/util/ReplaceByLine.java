package com.jinsong.util;


import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.jinsong.util.ReplaceColumn.lineSplitChars;

public class ReplaceByLine {

    public static void main(String[] args) throws Exception {

        String mapfile = "D:\\ideaprojects\\MyDemo\\src\\main\\java\\com\\jinsong\\util\\map.file";
        String sqlfile = "D:\\ideaprojects\\MyDemo\\src\\main\\java\\com\\jinsong\\util\\sqltest.sql";
        HashMap<String,String> map = readMap(mapfile);
        ArrayList<String> sqltext = readSqltext(sqlfile);
//
//        map.entrySet().forEach((a) ->{
//            System.out.println(a);
//        });
//        sqltext.forEach((a)->{
//            System.out.println(a);
//        });
//        System.out.println("============");
        for(String a :replaceByLine(sqltext,map)){
            System.out.println(a);
        }


    }

    static ArrayList<String> replaceByLine(ArrayList<String> sqltext,HashMap<String,String> mapc){
        ArrayList<String> rtn = new ArrayList<>();

        for(String line :sqltext){
            String tmpline = line;

            for(Map.Entry<String,String> b:mapc.entrySet()){
                String key = b.getKey();
                String value = b.getValue();
                tmpline = columeReplaceInLine(tmpline,key,value);
            }

            rtn.add(tmpline);
        }

        return rtn;
    }

    static String  columeReplaceInLine(String line,String key ,String value){

        String rtn = "";

        ArrayList<String> substrset = new ArrayList<>();

        int firstSindex = line.indexOf(key);
        if(firstSindex == -1){
            return line;
        }



        int len = line.length();
        int klen = key.length();

        int lastSindex = line.lastIndexOf(key);

        Integer start = 0;
        Integer end = len;

        int flag1 = 0;
        int flag2 = 0;
        substrset.clear();

        while(true){

            int i = line.substring(start,end).indexOf(key);
            if(i == -1){
                break;
            }

            Integer tmps  = i+start;
            Integer tmpe  = tmps+klen-1;

            if(tmps==0||!(((int)line.charAt(tmps-1)>=65&&(int)line.charAt(tmps-1)<=90)||((int)line.charAt(tmps-1)>=97&&(int)line.charAt(tmps-1)<=122)
                            ||((int)line.charAt(tmps-1)>=48&&(int)line.charAt(tmps-1)<=57)
                            ||line.charAt(tmps-1)=='_')){
                if(tmpe==len-1
                        ||!(((int)line.charAt(tmpe+1)>=65&&(int)line.charAt(tmpe+1)<=90)
                        ||((int)line.charAt(tmpe+1)>=97&&(int)line.charAt(tmpe+1)<=122)
                        ||((int)line.charAt(tmpe+1)>=48&&(int)line.charAt(tmpe+1)<=57)
                        ||line.charAt(tmpe+1)=='_')) {

                    flag1 = 1;
                    if(tmps!=0) {
                        String tmp = line.substring(start, tmps);
                        substrset.add(tmp);
                    }else{
                        String tmp = "";
                        substrset.add(tmp);
                    }
                    if(i+start == lastSindex){
                        if(tmpe!=len-1) {

                            substrset.add(line.substring(tmpe + 1,len));
                        }else{
                            flag2 = 1;
                        }
                    }
                }
            }
            start = tmpe + 1;
        }


        if(flag1==1) {
            int j = 0;
            int len2 = substrset.size();
            for (String str : substrset) {
                if (j == 0) {
                    rtn += str + value;
                } else if (j == len2 - 1 && flag2 == 0) {
                    rtn += str;
                } else {
                    rtn += str + value;
                }
                j++;
            }
            return rtn;
        }

        return line;
    }

    public static ArrayList<String> readSqltext(String filePath) throws IOException {

        ArrayList<String> rtn = new ArrayList<>();


        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            rtn.add(line);
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
        return rtn;
    }

    public static HashMap<String,String> readMap(String filePath) throws Exception{
        HashMap<String,String> map = new HashMap<>();


        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            map.put(line.split("\\s+")[0],line.split("\\s+")[1]);
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();

        return map;
    }
}
