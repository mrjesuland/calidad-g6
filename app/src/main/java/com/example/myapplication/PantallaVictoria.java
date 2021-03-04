package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;

public class PantallaVictoria extends AppCompatActivity {
    Button volver;
    String puntuacionFinal;
    ArrayList<TextView> puntuacionesTop=new ArrayList<>();
    ArrayList<ParPuntuacion> punts=new ArrayList<>();
    JSONObject obj;

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
    }

}
