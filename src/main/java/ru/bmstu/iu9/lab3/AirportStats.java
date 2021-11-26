package ru.bmstu.iu9.lab3;

import java.io.Serializable;

public class AirportStats implements Serializable {
    private int totalNum;
    private int numOfLateAndCancelled;
    private int delayTime;
    private float latePercent;
    private String arrAirport;
    private String depAirport;

    public AirportStats() {
        this.delayTime = 0;
        this.totalNum = 0;
        this.numOfLateAndCancelled = 0;
        this.latePercent = 0;
        this.arrAirport = "";
        this.depAirport = "";
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public int getNumOfLateAndCancelled() {
        return this.numOfLateAndCancelled;
    }

    public int getDelayTime() {
        return this.delayTime;
    }

    public void setNumOfLateAndCancelled(int numOfLateAndCancelled) {
        this.numOfLateAndCancelled = numOfLateAndCancelled;
    }

    public void setDelayTime(int delay) {
        this.delayTime = delay;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public void setLatePercent() {
        this.latePercent = this.numOfLateAndCancelled / (float) this.totalNum * 100;
    }

    public void setDepAirport(String airport) {
        this.depAirport = airport;
    }

    public void setArrAirport(String airport) {
        this.arrAirport = airport;
    }

    public String toString() {
        return "Departed from: " + this.depAirport + "  Arrived to: " + this.arrAirport +
                "  Maximum delay time: " + this.delayTime + "  Percentage of late and cancelled flights: " + this.latePercent;
    }
}
