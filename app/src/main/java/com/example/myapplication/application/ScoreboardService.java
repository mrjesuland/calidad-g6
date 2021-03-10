package com.example.myapplication.application;

import com.example.myapplication.domain.score.Score;

import java.util.List;

public interface ScoreboardService {

    List<Score> findByHighestScore(int amount);

    Score addScoreToScoreboard(Score score);

}
