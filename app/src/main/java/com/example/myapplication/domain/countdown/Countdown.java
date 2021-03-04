package com.example.myapplication.domain.countdown;

import java.util.concurrent.atomic.AtomicInteger;

public class Countdown {

    private static final int START_FROM = 30;

    private final AtomicInteger counter;

    public Countdown() {
        this.counter = new AtomicInteger(START_FROM);
    }

    public boolean finishedCount() {
        return counter.get() == 0;
    }

    public int counterValue() {
        return counter.get();
    }

    public int decrementCounter() {
        return counter.getAndDecrement();
    }

    public void resetCounter() {
        counter.set(START_FROM);
    }

}
