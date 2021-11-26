package ru.bmstu.iu9.lab3;

import java.io.Serializable;

public class Flight implements Serializable {
    private int totalNum;
    private int numOfLateAndCancelled;
    private int delayTime;

    public Flight() {
        this.delayTime = 0;
        this.totalNum = 0;
        this.numOfLateAndCancelled = 0;
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

    public void setCancellation() {
        this.totalNum += 1;
        this.numOfLateAndCancelled += 1;
    }

    public void setDelayTime(int delay) {
        this.totalNum += 1;
        this.numOfLateAndCancelled += 1;
        this.delayTime = delay;
    }
}
