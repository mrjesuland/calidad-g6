package com.example.myapplication.domain.score.points;

import com.example.myapplication.domain.Status;

import java.util.Random;

public class SimplePointsStrategy implements PointsStrategy {

    private final Random random;

    public SimplePointsStrategy() {
        this.random = new Random();
    }

    @Override
    public int computeIncrement(Status state) {
        int number = state == Status.SUCCESS ? randomInRange(10, 50) : randomInRange(5, 25);
        if(state != Status.SUCCESS) number = -number;
        return number;
    }

    private int randomInRange(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

}
