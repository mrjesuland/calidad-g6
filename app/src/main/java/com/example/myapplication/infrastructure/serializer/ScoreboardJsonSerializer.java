package com.example.myapplication.infrastructure.serializer;

import android.os.Build;
import androidx.annotation.RequiresApi;

import com.example.myapplication.domain.scoreboard.Scoreboard;
import com.example.myapplication.infrastructure.json.JsonSerializer;

import org.json.JSONArray;
import org.json.JSONObject;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ScoreboardJsonSerializer implements JsonSerializer<Scoreboard> {

    private static final String SCORES_KEY = "scores";

    private final ScoreJsonSerializer scoreSerializer;

    public ScoreboardJsonSerializer() {
        this.scoreSerializer = new ScoreJsonSerializer();
    }

    public JSONObject serialize(Scoreboard scoreboard) {
        JSONArray scoresJsonArray = new JSONArray();
        scoreboard.getAllScores()
                .stream()
                .map(scoreSerializer::serialize)
                .forEach(scoresJsonArray::put);
        try {
            JSONObject parentJsonObject = new JSONObject();
            parentJsonObject.put(SCORES_KEY, scoresJsonArray);
            return parentJsonObject;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}