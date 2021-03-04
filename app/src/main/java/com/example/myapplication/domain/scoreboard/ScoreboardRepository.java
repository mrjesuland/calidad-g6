package com.example.myapplication.domain.scoreboard;

public interface ScoreboardRepository {

    Scoreboard findScoreboard();
    Scoreboard saveScoreboard(Scoreboard scoreboard);

}
