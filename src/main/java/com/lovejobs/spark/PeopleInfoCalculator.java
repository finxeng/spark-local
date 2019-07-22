package com.lovejobs.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;
import java.util.Iterator;

public class PeopleInfoCalculator {

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("PeopleInfoCalculator").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> dataFile = sc.textFile("/Users/fengxin/Desktop/PeopleInfo.txt");

        JavaRDD<String> maleFilterData  = dataFile.filter(new Function<String, Boolean>() {
            public Boolean call(String s) throws Exception {
                return s.contains("M");
            }
        });

        JavaRDD<String> femaleFilterData  = dataFile.filter(new Function<String, Boolean>() {
            public Boolean call(String s) throws Exception {
                return s.contains("F");
            }
        });

        JavaRDD<String> maleHighterData = maleFilterData.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")[2]).iterator();
            }
        });

        JavaRDD<String> femaleHighterData = femaleFilterData.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")[2]).iterator();
            }
        });

        JavaRDD<Integer> maleHighterDataInt = maleHighterData.map(new Function<String, Integer>() {
            public Integer call(String s) throws Exception {
                return Integer.parseInt(String.valueOf(s));
            }
        });

        JavaRDD<Integer> femaleHighterDataInt = femaleHighterData.map(new Function<String, Integer>() {
            public Integer call(String s) throws Exception {
                return Integer.parseInt(String.valueOf(s));
            }
        });

        JavaRDD<Integer> maleHeightLowSort = maleHighterDataInt.sortBy(new Function<Integer,Integer>(){// true表示默认排序，为升序排序，从低到高排
            public Integer call(Integer s) throws Exception {
                return s;
            }
        },true,3);
        JavaRDD<Integer> femaleHeightLowSort = femaleHighterDataInt.sortBy(new Function<Integer,Integer>(){// true表示默认排序，为升序排序，从低到高排
            public Integer call(Integer s) throws Exception {
                return s;
            }
        },true,3);
        JavaRDD<Integer> maleHeightHightSort = maleHighterDataInt.sortBy(new Function<Integer,Integer>(){// false表示为降序排序，从高到低
            public Integer call(Integer s) throws Exception {
                return s;
            }
        },false,3);
        JavaRDD<Integer> femaleHeightHightSort = femaleHighterDataInt.sortBy(new Function<Integer,Integer>(){// true表示默认排序，为降序排序，从低到高排
            public Integer call(Integer s) throws Exception {
                return s;
            }
        },false,3);


        System.out.println("Number of Female Peole:" + femaleFilterData.count());//求出女性的总个数
        System.out.println("Number of Male Peole:" + maleFilterData.count());//求出男性的总个数
        System.out.println("Lowest Male:" + maleHeightLowSort.first());//求出男性最矮身高
        System.out.println("Lowest Female:" + femaleHeightLowSort.first());//求出女性最矮身高
        System.out.println("Highest Male:" + maleHeightHightSort.first());//求出男性最高身高
        System.out.println("Highest Female:" + femaleHeightHightSort.first());//求出女性最高身高

    }

}
