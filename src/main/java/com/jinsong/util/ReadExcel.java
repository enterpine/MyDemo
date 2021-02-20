package com.jinsong.util;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import scala.math.Ordering;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ReadExcel {
    private XSSFSheet sheet;

    ReadExcel(String filePath,String sheetName){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            sheet = sheets.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getExcelDateByIndex(int row,int column){
        try {
            XSSFRow row1 = sheet.getRow(row);
            if (row1 == null) {
                return null;
            }
            if (row1.getCell(column) == null) {
                return null;
            }
            String cell = row1.getCell(column).toString();
            return cell;
        }catch(Exception e){
            return null;
        }

    }
    public String getCellByCaseName(String caseName,int currentColumn,int targetColumn){
        String operateSteps="";
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=0;i<rows;i++){
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(currentColumn).toString();
            if(cell.equals(caseName)){
                operateSteps = row.getCell(targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }
    public void readExcelData(){
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=0;i<rows;i++){
            //获取列数
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            for(int j=0;j<columns;j++){
                String cell = row.getCell(j).toString();
                System.out.println(cell);
            }
        }
    }

    //测试方法
    public static void main(String[] args){
        String fpath = "C:\\Users\\jinsong\\Documents\\58工作文档\\数据治理一期\\DSP表名字段名新旧映射.xlsx";

        String[] ttt = {"ad_del_rsm_num","ajk_jx_pro","callnum","callsuccess","clicknum","costcall","costcallsuccess","del_rsm_cnt","del_rsm_num","dispcateids","dspids","fc_ajk_pro","gj_jx_pro","gj_pro","imp","inneradtype","lm_clk0","lm_clk1","lm_tele_suc","nor_pro","resumedeliver_num","wbdspsid","zp_yx_pro","zpjx_cre_rsm_num","zpjx_del_rsm_cnt","zpjx_del_rsm_num","zpyx_cre_rsm_num","zpyx_del_rsm_cnt","zpyx_del_rsm_num"};

        List<String> l = Arrays.asList(ttt);

        TreeSet<String> set= new TreeSet<>();

        for(Integer i = 2 ;i<=21;i++) {
            ReadExcel sheet1 = new ReadExcel(fpath, i.toString());

            Integer row=0;

            while(sheet1.getExcelDateByIndex(row,0)!=null&&!sheet1.getExcelDateByIndex(row,0).trim().equals("")) {
                String a = sheet1.getExcelDateByIndex(row, 0);
                String b = sheet1.getExcelDateByIndex(row, 1);
                if((!(a.equals("新表")||a.contains(".")))&&!a.trim().equals(b.trim())&&l.contains(b.trim())){
                    //System.out.println(i.toString().trim()+":"+a.trim()+"->"+b.trim());
                    //set.add(b.trim()+","+a.trim());2

                    System.out.println(i.toString()+","+b.trim()+","+a.trim());
                }
                row++;
            }
        }
        Integer i = 1;
//        for(String a :set){
//            System.out.println(i.toString()+","+a);
//            i++;
//        }

    }


}
