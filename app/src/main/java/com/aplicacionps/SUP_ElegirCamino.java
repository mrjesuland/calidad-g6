package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SUP_ElegirCamino extends AppCompatActivity {
    //Se crea un ProgressBar para representar el pocentaje de contagio que lleva el personaje
    private ProgressBar ProgressBar;
    //Se crean dos variables para almacenar los valores que se pasan de un activity a otro
    private int PorcentajeActual;
    private boolean Mascarilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionaremos la clase ElegirCamino.java con su XML activity_elegircamino.xml
        setContentView(R.layout.activity_sup_elegircamino);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        //pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtienen los datos como strings y luego se convierten en sus tipos correspondientes
        String Dato = getIntent().getStringExtra("dato");
        String Masc = getIntent().getStringExtra("masc");
        PorcentajeActual = Integer.parseInt(Dato);
        //La barra se relaciona con el activity y se establece el porcentaje que se va a mostrar con
        //el numero anteriormente obtenido
        ProgressBar = (ProgressBar)findViewById(R.id.barra1);
        ProgressBar.setProgress(PorcentajeActual);
        Mascarilla = Boolean.valueOf(Masc);
    }

    //El boton te lleba al interior del autobus
    public void caminoAutobus(View view) {
        //Se obtiene el porcentaje actual y el booleano de la Mascarilla
        int valor = PorcentajeActual;
        String bool = Boolean.toString(Mascarilla);
        String val = String.valueOf(valor);
        //Se crean los 2 intentos que se iniciaran dependiendo del booleano
        Intent caminoAutobus = new Intent(this, SUP_AutobusInterior.class);
        Intent caminoVuelta = new Intent(this, SUP_CaminoVuelta.class);
        //En ambos caminos se envian los datos para no perderlos y se inicia la actividad correspondiente
        if (!Mascarilla) {
            caminoVuelta.putExtra("dato", val);
            caminoVuelta.putExtra("masc", bool);
            startActivity(caminoVuelta);
        } else {
            caminoAutobus.putExtra("dato", val);
            caminoAutobus.putExtra("masc", bool);
            startActivity(caminoAutobus);
        }
    }

    //El boton te lleva a la calle que lleva al menu_boton_supermercado
    public void caminoAndando(View view){
        //Se obtiene el porcentaje actual y el booleano
        int valor= PorcentajeActual;
        String val= String.valueOf(valor);
        String bool = Boolean.toString(Mascarilla);
        //Se crea el nuevo intento, se envian los datos y se inicializa la actividad
        Intent caminoAndando = new Intent(this, SUP_IrAndando.class);
        caminoAndando.putExtra("dato", val);
        caminoAndando.putExtra("masc", bool);
        startActivity(caminoAndando);
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