package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SUP_AutobusInterior extends AppCompatActivity {
    //Se crea un ProgressBar para representar el pocentaje de contagio que lleva el personaje
    private ProgressBar ProgressBar;
    //Se crean dos variables para almacenar los valores que se pasan de un activity a otro
    private int PorcentajeActual;
    private boolean Mascarilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relaccionaremos la clase AutobusInterior.java con su XML activity_autobusinterior.xml
        setContentView(R.layout.activity_sup_autobusinterior);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        //pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtienen los datos como strings y luego se convierten en sus tipos correspondientes
        String Dato = getIntent().getStringExtra("dato");
        String Masc = getIntent().getStringExtra("masc");
        PorcentajeActual = Integer.parseInt(Dato);
        Mascarilla = Boolean.valueOf(Masc);
        //La barra de progreso se relaciona con el activity y se establece el porcentaje que se va a
        //mostrar con el numero anteriormente obtenido
        ProgressBar = (ProgressBar)findViewById(R.id.barra1);
        ProgressBar.setProgress(PorcentajeActual);
    }

    //El boton te lleva directamente a la entrada del menu_boton_supermercado
    public void sitioSenhora(View view){
        //Se obtiene el porcentaje actual y el booleano de la Mascarilla y se actualiza al elegir una mala opcion
        String bool = Boolean.toString(Mascarilla);
        int valor= PorcentajeActual +10;
        String val= String.valueOf(valor);
        //Se crea el nuevo activity, se pasan los datos anteriormente sacados y se inicializa
        Intent sitioSenhora = new Intent (this, SUP_superfuera.class);
        sitioSenhora.putExtra("dato", val);
        sitioSenhora.putExtra("masc", bool);
        startActivity(sitioSenhora);
    }

    //El boton te lleva directamnete a la entrada del menu_boton_supermercado
    public void sitioSolo(View view){
        //Se obtiene el porcentaje actual y el booleano de la Mascarilla
        String bool = Boolean.toString(Mascarilla);
        int valor= PorcentajeActual;
        String val= String.valueOf(valor);
        //Se crea el nuevo activity, se pasan los datos anteriormente sacados y se inicializa
        Intent sitioSolo = new Intent (this, SUP_superfuera.class);
        sitioSolo.putExtra("dato", val);
        sitioSolo.putExtra("masc", bool);
        startActivity(sitioSolo);
    }

    //Este método hace que no podamos retroceder de escenario en la historia jugable
    @Override
    public void onBackPressed() {

    }

    //Los siguientes metodos sirven para poner la musica que se va a escuchar a lo largo de la aplicacion
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