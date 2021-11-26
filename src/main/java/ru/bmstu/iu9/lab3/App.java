package ru.bmstu.iu9.lab3;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Objects;

public class App {
    static final String APP_NAME = "flights stats";
    static final String AIRPORTS_FILE = "L_AIRPORT_ID.csv";
    static final String FLIGHTS_FILE = "664600583_T_ONTIME_sample.csv";
    static final String AIRPORTS_REDUNDANT = "Code,Description";
    static final String FLIGHTS_REDUNDANT = "\"YEAR\",\"QUARTER\",\"MONTH\",\"DAY_OF_MONTH\",\"DAY_OF_WEEK\",\"FL_DATE\",\"UNIQUE_CARRIER\",\"AIRLINE_ID\",\"CARRIER\",\"TAIL_NUM\",\"FL_NUM\",\"ORIGIN_AIRPORT_ID\",\"ORIGIN_AIRPORT_SEQ_ID\",\"ORIGIN_CITY_MARKET_ID\",\"DEST_AIRPORT_ID\",\"WHEELS_ON\",\"ARR_TIME\",\"ARR_DELAY\",\"ARR_DELAY_NEW\",\"CANCELLED\",\"CANCELLATION_CODE\",\"AIR_TIME\",\"DISTANCE\",";
    static final String SEPARATOR = ",";
    static final String TRIMMER = "\"";
    static final int CODE_INDEX = 0;
    static final int AIRPORT_INDEX = 1;
    static final int AIRPORT_SPLIT_LIMIT = 2;
    static final int FLIGHT_SPLIT_LIMIT = -1;
    static final int DELAY_POS = 17;
    static final int ARR_CODE_POS = 14;
    static final int DEP_CODE_POS = 13;
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airportsFile = sc.textFile(AIRPORTS_FILE);
        JavaRDD<String> pureAirports = airportsFile.filter(s -> !Objects.equals(s.trim(), AIRPORTS_REDUNDANT));
        JavaPairRDD<String, String> airports = pureAirports.mapToPair(s -> {
            String[] airport = s.split(SEPARATOR, AIRPORT_SPLIT_LIMIT);
            String airportCode = airport[CODE_INDEX];
            String airportName = airport[AIRPORT_INDEX];
            airportCode = StringUtils.strip(airportCode, TRIMMER);
            airportName = StringUtils.strip(airportName, TRIMMER);
            return new Tuple2<>(airportCode, airportName);
        });
        JavaRDD<String> flightsFile = sc.textFile(FLIGHTS_FILE);
        JavaRDD<String> pureFlights = flightsFile.filter(s -> !Objects.equals(s.trim(), FLIGHTS_REDUNDANT));
        JavaPairRDD<Tuple2<String, String>, Flight> flights = pureFlights.mapToPair(s -> {
            
        })
        //airports.saveAsTextFile("result");
    }
}
