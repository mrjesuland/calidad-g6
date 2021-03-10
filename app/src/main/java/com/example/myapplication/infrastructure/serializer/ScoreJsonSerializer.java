package com.example.myapplication.infrastructure.serializer;

import com.example.myapplication.domain.score.Score;
import com.example.myapplication.infrastructure.json.JsonSerializer;

import org.json.JSONObject;

public class ScoreJsonSerializer implements JsonSerializer<Score> {

    public JSONObject serialize(Score score) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("points", score.getPoints());
            return jsonObject;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}