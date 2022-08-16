package com.jinsong.util;

import org.apache.hadoop.util.hash.Hash;

import scala.Tuple2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;

public class ReplaceColumn {

    static String lineSplitChars="_@%@_";

    public static void main(String[] args) throws Exception {
        String sqlpath = "D:\\ideaprojects\\MyDemo\\src\\main\\java\\com\\jinsong\\util\\sqltest.sql";
        String tablename = "tableb";

//        String sqlpath = "D:\\ideaprojects\\data-warehouse\\ad\\adx\\app\\hql\\lm_app_adx_effect_data.sql";
//        String tablename = "hdp_lbg_ectech_lmdb.lm_dwd_adx_effect_detail";

        HashMap<String,String> map = new HashMap<>();
        map.put("requestid","request_id");
        map.put("sspid","ssp_id");
        map.put("sessionid","session_id");
        map.put("cookieid","cookie_id");
        map.put("connectiontype","connection_type");
        map.put("isfullscreen","is_fullscreen");
        map.put("userid","user_id");
        map.put("impname","imp_name");
        map.put("impid","imp_id");
        map.put("dspid","dsp_id");
        map.put("bidid","bid_id");
        map.put("subid","sub_id");
        map.put("iswin","is_win");
        map.put("inneradtype","ad_type");
        map.put("click_count","clk_cnt");
        map.put("callnum","call_num");
        map.put("callsuccess","callsuc_num");
        map.put("calltimes","callsuc_time");
        map.put("costcallsuccess","costcallsuc_num");
        map.put("costcalltimes","costcallsuc_time");
        map.put("resumecreatenum","cre_num");
        map.put("resumedelivernum","dlvr_num");
        autoReplace(sqlpath,tablename,map);
    }

    static ArrayList<Integer> getTableIndex(String sqltext,String tablename){
        ArrayList<Integer> rlt = new ArrayList<>();
        String t = " "+tablename+" ";
        int len = tablename.length();
        int firstSindex = sqltext.indexOf(" "+t+" ");
        int firstEindex = firstSindex+len + 1;

        int lastSindex = sqltext.lastIndexOf(" "+t+" ");

        int start = 0;
        int end = len-1;

        start = firstEindex;

        while(true){
            int i = sqltext.substring(start,sqltext.length()-1).indexOf(t);
            if(i == -1){
                break;
            }
            rlt.add(i+start+1);
            start = i+start+1+len;
            if(i+start == lastSindex){
                break;
            }
        }
        return rlt;
    }

    static Tuple2<Integer,Integer> getSubQuery(String sqltext,Integer sindex,Integer eindex){
        //向左查"("
        int lflag=0;
        int lindex = 0;
        for(int a = sindex;a>=0;a--){
            if(sqltext.charAt(a)==')'){
                lflag = lflag - 1;
            }else if(sqltext.charAt(a)=='('){
                lflag = lflag + 1;
            }
            if(lflag == 1){
                lindex=a+1;
                break;
            }
            if(a-9>=0){
                if(sqltext.substring(a-9,a).equals("union all")){
                    lindex = a+1;
                    break;
                }

            }
        }
        //向右查")"
        int rflag=0;
        int rindex = eindex;
        for(int a = eindex;a>=0;a++){
            if(sqltext.charAt(a)==')'){
                rflag = rflag - 1;
            }else if(sqltext.charAt(a)=='('){
                rflag = rflag + 1;
            }
            if(rflag == -1){
                rindex=a-1;
                break;
            }
            if(a+9<sqltext.length()){
                if(sqltext.substring(a,a+9).equals("union all")){
                    rindex = a-1;
                    break;
                }

            }
        }

        return new Tuple2<>(lindex,rindex+1);
    }

    static String replaceColumn(String sql,String tablename, HashMap columnMap ){
        System.out.println(sql);
        for(String a:sql.split(lineSplitChars)){
            System.out.println(a);
        }
        return "";
    }

    static ArrayList<String> autoReplace(String sqlpath, String tablename, HashMap columnMap) throws Exception {
        //读取sql内容
        String sqltext = readSqltext(sqlpath);
        System.out.println(sqltext);

        //getTableIndex();获取sql中出现该表的所有位置
        ArrayList<Integer> tindexList = getTableIndex(sqltext,tablename);

        //获取每个该表出现位置的子查询
        for(Integer index : tindexList){
            int sindex = index;
            int eindex = sindex+tablename.length()-1;
            Tuple2<Integer,Integer> t =  getSubQuery(sqltext,sindex,eindex);
            Integer start = t._1;
            Integer end = t._2;
            String targetsql = sqltext.substring(start,end+1);
            String resultsql = replaceColumn(targetsql,tablename,columnMap);
        }

        return new ArrayList<>();
    }

    public static String readSqltext(String filePath) throws IOException {
        StringBuffer sqltext = new StringBuffer();
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            sqltext.append(line); // 将读到的内容添加到 buffer 中
            sqltext.append(" "+lineSplitChars+" ");//自定义换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
        return sqltext.toString();
    }
}
