package com.example.myapplication.infrastructure.serializer;

import com.example.myapplication.domain.score.Score;
import com.example.myapplication.infrastructure.json.JsonSerializer;

import org.json.JSONObject;

public class ScoreJsonSerializer implements JsonSerializer<Score> {

    public JSONObject serialize(Score score) {
        try {
            return new JSONObject(Integer.toString(score.getPoints()));
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}