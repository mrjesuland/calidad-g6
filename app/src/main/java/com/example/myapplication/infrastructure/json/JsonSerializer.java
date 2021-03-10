package com.example.myapplication.infrastructure.json;

import org.json.JSONObject;
import java.io.Serializable;

public interface JsonSerializer<T> extends Serializable {

    JSONObject serialize(T object);

}