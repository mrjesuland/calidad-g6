package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class SUP_CaminoVuelta extends AppCompatActivity {
    //Se crean las variables que van a almacenar los datos que se han pasado de la otra activity
    private int PorcentajeActual;
    private boolean Mascarilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionaremos la clase CaminoVuelta.java con su XML activity_caminovuelta.xml
        setContentView(R.layout.activity_sup_caminovuelta);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        //pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtiene el porcentaje actual
        String Dato = getIntent().getStringExtra("dato");
        PorcentajeActual = Integer.parseInt(Dato);
    }

    public void vueltaAElegirCamino(View view){
        int valor = PorcentajeActual;
        //El booleano Mascarilla se pone en true ya que es obligado a volver a su casa para ponersela
        String mascarilla = Boolean.toString(true);
        String val = String.valueOf(valor);
        //Se crea el intento de volver a elegir el juego_autobus_fuera con la mascarilla ya puesta y el numero de
        //porcentaje se mantiene
        Intent vueltaAElegirCamino = new Intent(this, SUP_ElegirCamino.class);
        //Se pasan ambos datos y se inicia la actividad
        vueltaAElegirCamino.putExtra("dato", val);
        vueltaAElegirCamino.putExtra("masc", mascarilla);
        startActivity(vueltaAElegirCamino);
    }

    //Este método hace que no podamos retroceder de escenario en la historia jugable
    @Override
    public void onBackPressed() {

    }

    //Los siguientes metodos sirven para poner musica que se va a escuchar en la aplicacion
    @Override
    public void onPause() {
        super.onPause();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.PAUSE);
        startService(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        Boolean valordelboton = sharedPreferences.getBoolean("value", false);
        if (valordelboton != true) {
            Intent i = new Intent(this, AudioService.class);
            i.putExtra("action", AudioService.START);
            startService(i);
        }
    }

}