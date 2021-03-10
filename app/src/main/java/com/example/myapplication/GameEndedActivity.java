package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.application.ScoreboardService;
import com.example.myapplication.application.ScoreboardServiceImpl;
import com.example.myapplication.domain.Game;
import com.example.myapplication.domain.GameFactory;
import com.example.myapplication.domain.score.Score;

import java.io.File;
import java.util.List;

@SuppressWarnings("all")
public class GameEndedActivity extends AppCompatActivity {

    private final int TOP_SCORES_AMOUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_ended_screen);

        ScoreboardService scoreboardService = new ScoreboardServiceImpl(getStorageFilePath());

        Game game = GameFactory.currentGame();
        Score score = game.getScore();
        if(score.getPoints() > 0) {
            scoreboardService.addScoreToScoreboard(score);
        }

        List<Score> topScores = scoreboardService.findByHighestScore(TOP_SCORES_AMOUNT);

        final String SCORE_ID_PREFIX = "score_";
        int traversalIndex = 0;
        while(traversalIndex < topScores.size() - 1) {
            String elementId = SCORE_ID_PREFIX + (traversalIndex + 1);
            int resourceId = findResourceId(elementId);
            TextView rankingScore = findViewById(resourceId);
            Score currentScore = topScores.get(traversalIndex);
            rankingScore.setText(Integer.toString(currentScore.getPoints()));
            traversalIndex++;
        }

    }

    public String getStorageFilePath() {
        File file = new File(getFilesDir(), "database.txt");
        return file.getAbsolutePath();
    }

    public int findResourceId(String elementId) {
        return getResources().getIdentifier(elementId, "id", getPackageName());
    }

}

