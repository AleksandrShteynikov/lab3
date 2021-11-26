package ru.bmstu.iu9.lab3;

import java.io.Serializable;

public class Flight implements Serializable {
    static int totalNum = 0;
    static int numOfLate = 0;
    static int numOfCancelled = 0;
    private int delayTime;

    public Flight() {
        this.delayTime = 0;
    }

    public int getDelayTime() {
        return this.delayTime;
    }

    public void setDelayTime(int delay) {
        this.delayTime = delay;
    }
}
