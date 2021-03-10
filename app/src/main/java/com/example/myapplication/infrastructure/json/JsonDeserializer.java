package com.example.myapplication.infrastructure.json;

import org.json.JSONObject;
import java.io.Serializable;

public interface JsonDeserializer<T> extends Serializable {

    T deserialize(JSONObject jsonObject);

}