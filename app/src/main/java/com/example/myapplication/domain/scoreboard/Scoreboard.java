package com.example.myapplication.domain.scoreboard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.myapplication.domain.score.Score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Scoreboard {

    private final PriorityQueue<Score> scores;

    public Scoreboard() {
        this.scores = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public List<Score> getAllScores() {
        return new ArrayList<>(scores);
    }

    public void addAllScores(Collection<Score> givenScores) {
        givenScores.forEach(this::addScore);
    }

    public void addScore(Score givenScore) {
        scores.add(givenScore);
    }

}