package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.application.ScoreboardService;
import com.example.myapplication.application.ScoreboardServiceImpl;
import com.example.myapplication.domain.score.Score;

import java.io.File;
import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class GameEndedActivity extends AppCompatActivity {

    private final int TOP_SCORES_AMOUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_victoria);

        ScoreboardService scoreboardService = new ScoreboardServiceImpl(getStorageFilePath());
        scoreboardService.addScoreToScoreboard(new Score(new Random().nextInt(5000)));
        List<Score> topScores = scoreboardService.findByHighestScore(TOP_SCORES_AMOUNT);

        int traversalIndex = 0;
        while(traversalIndex < topScores.size() - 1) {
            String elementId = "t" + (traversalIndex + 1);
            System.out.println(elementId);
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
