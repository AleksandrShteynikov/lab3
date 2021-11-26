package ru.bmstu.iu9.lab3;

import java.io.Serializable;

public class Flight implements Serializable {
    static int totalNum = 0;
    static int numOfLate = 0;
    static int numOfCancelled = 0;
    private int delayTime;
    private boolean cancelled;

    public Flight() {
        this.delayTime = 0;
        this.cancelled = false;
    }

    public Flight(int delay, boolean cancelled) {
        this.delayTime = delay;
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public int getDelayTime() {
        return this.delayTime;
    }

    public void setDelayTime(int delay) {
        this.delayTime = delay;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
