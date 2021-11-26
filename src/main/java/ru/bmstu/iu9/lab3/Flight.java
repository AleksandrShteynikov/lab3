package ru.bmstu.iu9.lab3;

public class Flight {
    static int totalNum = 0;
    static int numOfLate = 0;
    static int numOfCancelled = 0;
    private final int delayTime;
    private final boolean cancelled;

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
}
