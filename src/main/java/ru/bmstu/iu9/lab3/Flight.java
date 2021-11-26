package ru.bmstu.iu9.lab3;

import java.io.Serializable;

public class Flight implements Serializable {
    private int totalNum;
    private int numOfLateAndCancelled;
    private int delayTime;
    private float latePercent;

    public Flight() {
        this.delayTime = 0;
        this.totalNum = 0;
        this.numOfLateAndCancelled = 0;
        this.latePercent = 0;
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
        this.latePercent = this.numOfLateAndCancelled / (double) this.totalNum;
    }
}
