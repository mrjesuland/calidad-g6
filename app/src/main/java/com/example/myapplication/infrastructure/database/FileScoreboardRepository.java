package com.example.myapplication.infrastructure.database;

import android.os.Build;
import androidx.annotation.RequiresApi;

import com.example.myapplication.domain.scoreboard.Scoreboard;
import com.example.myapplication.domain.scoreboard.ScoreboardRepository;
import com.example.myapplication.infrastructure.json.JsonDeserializer;
import com.example.myapplication.infrastructure.json.JsonSerializer;
import com.example.myapplication.infrastructure.serializer.ScoreboardJsonDeserializer;
import com.example.myapplication.infrastructure.serializer.ScoreboardJsonSerializer;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.R)
public class FileScoreboardRepository implements ScoreboardRepository {

    private final String filename;

    private final JsonSerializer<Scoreboard> scoreboardSerializer;
    private final JsonDeserializer<Scoreboard> scoreboardDeserializer;

    public FileScoreboardRepository(String filename) {
        this.filename = filename;
        this.scoreboardSerializer = new ScoreboardJsonSerializer();
        this.scoreboardDeserializer = new ScoreboardJsonDeserializer();
    }

    @Override
    public Scoreboard findScoreboard() {
        Scoreboard scoreboard = new Scoreboard();
        File file = new File(filename);
        if(!file.exists() || !file.isFile()) {
            return scoreboard;
        }

        StringBuilder contentBuilder = new StringBuilder();
        int lineNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null) {
                contentBuilder.append(line);
                lineNumber++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean isFileEmpty = lineNumber == 0;
        if(isFileEmpty) return scoreboard;

        try {
            JSONObject jsonObject = new JSONObject(contentBuilder.toString());
            scoreboard = scoreboardDeserializer.deserialize(jsonObject);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        return scoreboard;
    }

    @Override
    public Scoreboard saveScoreboard(Scoreboard scoreboard) {
        File file = new File(filename);
        JSONObject jsonObject = scoreboardSerializer.serialize(scoreboard);

        if(file.exists() && !file.delete()) {
            throw new RuntimeException("Existent file not erased");
        }

        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return scoreboard;
    }

}
