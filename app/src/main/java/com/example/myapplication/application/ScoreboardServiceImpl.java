package com.example.myapplication.application;

import android.os.Environment;

import com.example.myapplication.domain.score.Score;
import com.example.myapplication.domain.scoreboard.Scoreboard;
import com.example.myapplication.domain.scoreboard.ScoreboardRepository;
import com.example.myapplication.infrastructure.database.FileScoreboardRepository;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("NewAPI")
public class ScoreboardServiceImpl implements ScoreboardService {

    private final ScoreboardRepository scoreboardRepository;

    public ScoreboardServiceImpl(String filename) {
        this.scoreboardRepository = new FileScoreboardRepository(filename);
    }


    @Override
    public List<Score> findByHighestScore(int amount) {
        Scoreboard scoreboard = scoreboardRepository.findScoreboard();
        return scoreboard.getAllScores().stream().limit(amount).collect(Collectors.toList());
    }

    @Override
    public Score addScoreToScoreboard(Score score) {
        Scoreboard scoreboard = scoreboardRepository.findScoreboard();
        scoreboard.addScore(score);
        scoreboardRepository.saveScoreboard(scoreboard);
        return score;
    }

}
