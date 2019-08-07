package com.lovejobs.spark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class CreateTcLog {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String args[]) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:\\Users\\love_\\Desktop\\tc_log_out.log"))));
        String[] hours ={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
        for (int i =0;i<hours.length;i++){
            String date_str = "2019-07-30 "+hours[i]+":30:21";
            Date date = sdf.parse(date_str);
            for (int j=0;j<100;j++){
                int x=(int)(Math.random()*1000)+1;
                for (int m =0;m<x;m++){
                    String beylaid = String.valueOf(UUID.randomUUID()).replace("-","");
                    String tc_log = beylaid.concat("||||||4050028|en|159.138.88.145|").concat("1|21|1|").concat(String.valueOf(j)).concat("|").concat(String.valueOf(date.getTime()));
                    System.out.println(tc_log);
                    bw.write(tc_log);
                    bw.newLine();
                }

            }

        }
        bw.close();
    }
}
