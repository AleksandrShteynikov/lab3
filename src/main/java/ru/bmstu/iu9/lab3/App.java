package ru.bmstu.iu9.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class App {
    static final String APP_NAME = "";
    static final String 
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airportsFile = sc.textFile();
    }
}
