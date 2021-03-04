package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class PantallaVictoria extends AppCompatActivity {
    Button volver;
    String puntuacionFinal;
    ArrayList<TextView> puntuacionesTop=new ArrayList<>();
    ArrayList<ParPuntuacion> punts=new ArrayList<>();
    JSONObject obj;

    public static final String JSON_SCORE_FILENAME = "jsonPuntos.txt";
    public static final String JSON_POINTS_KEY = "puntos";
    public static final String JSON_TOP_SCORES_KEY = "listaPuntuacionesMejores";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_victoria);
        volver=(Button) findViewById(R.id.button4);

        puntuacionesTop.add((TextView) findViewById(R.id.t1));
        puntuacionesTop.add((TextView) findViewById(R.id.t2));
        puntuacionesTop.add((TextView) findViewById(R.id.t3));
        puntuacionesTop.add((TextView) findViewById(R.id.t4));
        puntuacionesTop.add((TextView) findViewById(R.id.t5));
        TextView view = (TextView) findViewById(R.id.TimeOutView);
        Context context=getApplicationContext();
        String fileName="json.txt";
        try {
            obj= new JSONObject(getJSONLine(context,fileName));
            ChangePunct(obj,view);
            FuncionGuardarNombre(view);
            SetArrayPuncts(context);
        } catch (JSONException e) {
            view.setText("error al principio");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            view.setText(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public void SetArrayPuncts(Context context) throws JSONException, IOException {
        JSONObject o= new JSONObject(getJSONLine(context,"jsonPuntos.txt"));
        JSONArray theJsonArray=o.getJSONArray("listaPuntuacionesMejores");
        for(int i=0;i<theJsonArray.length();i++){
            JSONObject aux= theJsonArray.getJSONObject(i);
            punts.add(new ParPuntuacion(aux.getInt(JSON_POINTS_KEY)));

        }
        Collections.sort(punts);
        int size=punts.size();
        if(size>=5){
            for(int j=0;j<5;j++){
                puntuacionesTop.get(j).setText(String.valueOf(punts.get(j).getPuntos()));
            }
        }
        else{
            for(int j=0;j<punts.size();j++){
                puntuacionesTop.get(j).setText(String.valueOf(punts.get(j).getPuntos()));
            }
        }


    }
    public void ChangePunct(JSONObject object, TextView thetext) {
        try {
            puntuacionFinal= String.valueOf(object.getInt("puntuacionReciente"));
            thetext.setText(puntuacionFinal);
        } catch (JSONException e) {
            thetext.setText(e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    public String getJSONLine(Context context, String name) throws IOException {
        File f = new File(context.getFilesDir(),name);
        FileReader fis= new FileReader(f);
        BufferedReader buffer= new BufferedReader(fis);
        StringBuilder builder= new StringBuilder();
        String line=buffer.readLine();
        while(line!=null){
            builder.append(line).append("\n");
            line=buffer.readLine();
        }
        buffer.close();
        return builder.toString();
    }
    public void FuncionVolverMenuPrincipal(View view){
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
    public void FuncionGuardarNombre(TextView view){
        JSONObject lis= null;
        try {
            Context context= getApplicationContext();
            lis = new JSONObject(getJSONLine(context,JSON_SCORE_FILENAME));
        } catch (JSONException e) {
            lis = new JSONObject();
        } catch (IOException e) {
            lis = new JSONObject();
        }

        File listFile=new File(getFilesDir(), JSON_SCORE_FILENAME);
        try {
            FileWriter writer= new FileWriter(listFile);
            JSONArray arr=lis.getJSONArray(JSON_TOP_SCORES_KEY);
            JSONObject jo= new JSONObject();
            jo.put(JSON_POINTS_KEY,puntuacionFinal);
            arr.put(jo);
            writer.write(lis.toString());
            writer.flush();
            writer.close();
        } catch (JSONException | IOException e) {
            try {
                insertFirst();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }

    }

    public void insertFirst() throws JSONException {
        JSONObject childJsonObject = new JSONObject();
        childJsonObject.put(JSON_POINTS_KEY, puntuacionFinal);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(childJsonObject);

        JSONObject parentJsonObject = new JSONObject();
        parentJsonObject.put(JSON_TOP_SCORES_KEY, jsonArray);

        File scoresFile = new File(getFilesDir(), JSON_SCORE_FILENAME);
        try(FileWriter fileWriter = new FileWriter(scoresFile)) {
            fileWriter.write(parentJsonObject.toString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
