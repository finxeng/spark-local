package com.lovejobs.spark;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class CreateTcLog {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String args[]) throws Exception {
        createTcLog();
    }


    public static void createBdDwd() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/fengxin/Desktop/tc_log/tc_log_out_20190808.log"))));
        String str;
        while ((str = br.readLine())!=null){
            String[] lineStrs = str.split("|");

        }
    }


    public static void createTcLog() throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/fengxin/Desktop/tc_log/tc_log_out_20190808.log"))));

        BufferedWriter bw_1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/fengxin/Desktop/bd_dwd/bd_dwd_out_20190808.log"))));

        String[] groupid = {"1","2","3"};
        for (int z=0;z<groupid.length;z++){
            String[] hours ={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
            for (int i =0;i<hours.length;i++){
                String date_str = "2019-08-08 "+hours[i]+":30:21";
                Date date = sdf.parse(date_str);
                for (int j=0;j<100;j++){
                    for (int m=0;m<100;m++) {
                        String beylaid = String.valueOf(UUID.randomUUID()).replace("-","");
                        String tc_log = beylaid.concat("||||||4050028|en|159.138.88.145|").concat("1|1|"+groupid[z]+"|1|").concat(String.valueOf(j)).concat("|").concat(String.valueOf(date.getTime()));
                        bw.write(tc_log);
                        bw.newLine();
                        if(groupid[z].equals("1")){
                            if(m<30){
                                bw_1.write(tc_log);
                                bw_1.newLine();
                            }
                        }else if(groupid[z].equals("2")){
                            if(m<60){
                                bw_1.write(tc_log);
                                bw_1.newLine();
                            }
                        }else if(groupid[z].equals("3")){
                            if(m<90){
                                bw_1.write(tc_log);
                                bw_1.newLine();
                            }
                        }
                    }

                }

            }

        }
        bw.close();
        bw_1.close();
    }
}
