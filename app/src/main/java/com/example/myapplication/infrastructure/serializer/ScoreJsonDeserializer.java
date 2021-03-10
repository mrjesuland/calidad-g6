package com.example.myapplication.infrastructure.serializer;

import com.example.myapplication.domain.score.Score;
import com.example.myapplication.infrastructure.json.JsonDeserializer;

import org.json.JSONObject;

public class ScoreJsonDeserializer implements JsonDeserializer<Score> {

    private static final String JSON_POINTS_KEY = "points";

    public Score deserialize(JSONObject object) {
        try {
            return new Score(object.getInt(JSON_POINTS_KEY));
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}