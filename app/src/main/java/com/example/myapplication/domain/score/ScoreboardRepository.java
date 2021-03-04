package com.example.myapplication.domain.score;

public interface ScoreboardRepository {

    Scoreboard findScoreboard();
    Scoreboard saveScoreboard(Scoreboard scoreboard);

}
