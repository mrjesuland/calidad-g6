package com.example.myapplication.domain.score.points;

import com.example.myapplication.domain.Status;

public interface PointsStrategy {

    int computeIncrement(Status state);

}
