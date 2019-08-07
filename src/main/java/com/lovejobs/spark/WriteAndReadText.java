package com.lovejobs.spark;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WriteAndReadText {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        File file = new  File("C:\\Users\\love_\\Desktop\\tc_log.log");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:\\Users\\love_\\Desktop\\tc_log_out.log"))));
        String str = "";
        while ((str = bufferedReader.readLine())!=null){
            String hour = str.split(",")[0];
            String beylaid = str.split(",")[1];

            String date_str = "2019-07-30 "+hour+":30:21";
            //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = sdf.parse(date_str);
            int x=(int)(Math.random()*100)+1;
            String tc_log = beylaid.concat("||||||4050028|en|159.138.88.145|").concat("1|21|1|").concat(String.valueOf(x)).concat("|").concat(String.valueOf(date.getTime()));
            System.out.println(tc_log);
            bw.write(tc_log);
            bw.newLine();
        }
        bufferedReader.close();
        bw.close();
    }



}
