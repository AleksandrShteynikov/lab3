package ru.bmstu.iu9.lab3;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class App {
    static final String APP_NAME = "flights stats";
    static final String AIRPORTS_FILE = "L_AIRPORT_ID.csv";
    static final String FLIGHTS_FILE = "664600583_T_ONTIME_sample.csv";
    static final String AIRPORTS_REDUNDANT = "Code,Description";
    static final String FLIGHTS_REDUNDANT = "\"YEAR\",\"QUARTER\",\"MONTH\",\"DAY_OF_MONTH\",\"DAY_OF_WEEK\",\"FL_DATE\",\"UNIQUE_CARRIER\",\"AIRLINE_ID\",\"CARRIER\",\"TAIL_NUM\",\"FL_NUM\",\"ORIGIN_AIRPORT_ID\",\"ORIGIN_AIRPORT_SEQ_ID\",\"ORIGIN_CITY_MARKET_ID\",\"DEST_AIRPORT_ID\",\"WHEELS_ON\",\"ARR_TIME\",\"ARR_DELAY\",\"ARR_DELAY_NEW\",\"CANCELLED\",\"CANCELLATION_CODE\",\"AIR_TIME\",\"DISTANCE\",";
    static final String SEPARATOR = ",";
    static final String TRIMMER = "\"";
    static final String CANCELLATION_SYMB = "1.00";
    static final int CODE_INDEX = 0;
    static final int AIRPORT_INDEX = 1;
    static final int AIRPORT_SPLIT_LIMIT = 2;
    static final int FLIGHT_SPLIT_LIMIT = -1;
    static final int DELAY_POS = 17;
    static final int ARR_CODE_POS = 14;
    static final int DEP_CODE_POS = 11;
    static final int CANCELLATION_POS = 19;
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airportsFile = sc.textFile(AIRPORTS_FILE);
        JavaRDD<String> pureAirports = airportsFile.filter(s -> !s.trim().equals(AIRPORTS_REDUNDANT));
        JavaPairRDD<String, String> airports = pureAirports.mapToPair(s -> {
            String[] airport = s.split(SEPARATOR, AIRPORT_SPLIT_LIMIT);
            String airportCode = airport[CODE_INDEX];
            String airportName = airport[AIRPORT_INDEX];
            airportCode = StringUtils.strip(airportCode, TRIMMER);
            airportName = StringUtils.strip(airportName, TRIMMER);
            return new Tuple2<>(airportCode, airportName);
        });
        Map<String, String> airportsMap = airports.collectAsMap();
        JavaRDD<String> flightsFile = sc.textFile(FLIGHTS_FILE);
        JavaRDD<String> pureFlights = flightsFile.filter(s -> !s.trim().equals(FLIGHTS_REDUNDANT));
        JavaPairRDD<Tuple2<String, String>, Flight> flights = pureFlights.mapToPair(s -> {
            Flight flight = new Flight();
            flight.setTotalNum(1);
            String[] flightData = s.split(SEPARATOR, FLIGHT_SPLIT_LIMIT);
            String departureAirport = flightData[DEP_CODE_POS];
            String arrivalAirport = flightData[ARR_CODE_POS];
            String flightDelay = flightData[DELAY_POS];
            if (!flightDelay.isEmpty()) {
                float flightDelayNum = Float.parseFloat(flightDelay);
                if (flightDelayNum > 0) {
                    flight.setDelayTime((int)flightDelayNum);
                    flight.setNumOfLateAndCancelled(1);
                }
            } else {
                String cancelled = flightData[CANCELLATION_POS];
                if (cancelled.equals(CANCELLATION_SYMB)) {
                    flight.setNumOfLateAndCancelled(1);
                }
            }
            return new Tuple2<>(new Tuple2<>(departureAirport, arrivalAirport), flight);
        });
        JavaPairRDD<Tuple2<String, String>, Flight> reducedFlights = flights.reduceByKey((flight1, flight2) -> {
            Flight flight = new Flight();
            flight.setTotalNum(flight1.getTotalNum() + flight2.getTotalNum());
            flight.setNumOfLateAndCancelled(flight1.getNumOfLateAndCancelled() + flight2.getNumOfLateAndCancelled());
            int delayTime1 = flight1.getDelayTime();
            int delayTime2 = flight2.getDelayTime();
            flight.setDelayTime(Math.max(delayTime1, delayTime2));
            return flight;
        });
        final Broadcast<Map<String, String>> airportsBroadcasted = sc.broadcast(airportsMap);
        JavaPairRDD<Tuple2<String, String>, Flight> processedFlights = reducedFlights.map(flight -> {
            flight.
        });
        //processedFlights.saveAsTextFile("result");
    }
}
