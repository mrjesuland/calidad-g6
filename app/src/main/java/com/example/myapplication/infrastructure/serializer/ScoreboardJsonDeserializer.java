package com.example.myapplication.infrastructure.serializer;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.myapplication.domain.score.Score;
import com.example.myapplication.domain.scoreboard.Scoreboard;
import com.example.myapplication.infrastructure.json.JsonDeserializer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ScoreboardJsonDeserializer implements JsonDeserializer<Scoreboard> {

    private static final String SCORES_KEY = "scores";

    private final ScoreJsonDeserializer scoreDeserializer;

    public ScoreboardJsonDeserializer() {
        this.scoreDeserializer = new ScoreJsonDeserializer();
    }

    public Scoreboard deserialize(JSONObject object) {
        try {
            List<Score> scores = new LinkedList<>();
            JSONArray jsonScores = object.getJSONArray(SCORES_KEY);
            for(int i = 0; i< jsonScores.length(); i++) {
                JSONObject currentJsonScore = jsonScores.getJSONObject(i);
                Score score = scoreDeserializer.deserialize(currentJsonScore);
                scores.add(score);
            }
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.addAllScores(scores);
            return scoreboard;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}