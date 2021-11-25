package ru.bmstu.iu9.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Objects;

public class App {
    static final String APP_NAME = "flights stats";
    static final String AIRPORTS_FILE = "L_AIRPORT_ID.csv";
    static final String FLIGHTS_FILE = "664600583_T_ONTIME_sample.csv";
    static final String AIRPORTS_REDUNDANT = "Code,Description";
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airportsFile = sc.textFile(AIRPORTS_FILE);
        JavaRDD<String> pureAirports = airportsFile.filter(s -> !Objects.equals(String.trim(s), AIRPORTS_REDUNDANT));
        JavaPairRDD<String, String> airports = pureAirports.mapToPair();
        JavaRDD<String> flightsFile = sc.textFile(FLIGHTS_FILE);
    }
}
