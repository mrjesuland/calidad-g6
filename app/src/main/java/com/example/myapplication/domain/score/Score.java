package com.example.myapplication.domain.score;

import com.example.myapplication.domain.Status;
import com.example.myapplication.domain.score.points.PointsStrategy;
import com.example.myapplication.domain.score.points.SimplePointsStrategy;

import java.util.concurrent.atomic.AtomicInteger;

public class Score implements Comparable<Score> {

    private static final int STARTING_POINTS = 0;

    private final AtomicInteger points;
    private final PointsStrategy pointsStrategy;

    public Score() {
        this(STARTING_POINTS);
    }

    public Score(int points) {
        this.points = new AtomicInteger(points);
        this.pointsStrategy = new SimplePointsStrategy();
    }

    public int getPoints() {
        return points.get();
    }

    public void resetPoints() {
        points.set(STARTING_POINTS);
    }

    public void updateScore(Status status) {
        int increment = pointsStrategy.computeIncrement(status);
        int result = increment + points.get() < 0 ? STARTING_POINTS : increment;
        points.set(result);
    }

    @Override
    public int compareTo(Score score) {
        return Integer.compare(points.get(), score.points.get());
    }

}