package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class COL_Pasillos extends AppCompatActivity {
    //Se crea un ProgressBar para representar el pocentaje de contagio que lleva el personaje
    private ProgressBar ProgressBar;
    //Se crea una variable para almacenar el valor que se pasa de un activity a otro
    private int PorcentajeActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionaremos la clase COL_Pasillos.java con su XML activity_col_pasillos.xml
        setContentView(R.layout.activity_col_pasillos);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        //pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtiene el dato como string y luego se convierte en su tipo correspondiente
        String Dato = getIntent().getStringExtra("dato");
        String Am= getIntent().getStringExtra("am");
        PorcentajeActual = Integer.parseInt(Dato);
        //La barra se relaciona con el activity y se establece el porcentaje que se va a mostrar con
        //el numero anteriormente obtenido
        ProgressBar = (ProgressBar)findViewById(R.id.barra1);
        ProgressBar.setProgress(PorcentajeActual);
    }

    //El boton te lleva a la clase COL_ClaseBuena
    public void claseBuena(View view) {
        //Se obtiene el porcentaje actual
        int valor = PorcentajeActual;
        String val = String.valueOf(valor);
        //Se crea el intento
        Intent claseBuena = new Intent(this, COL_ClaseBuena.class);
        //Se envia el dato y se inicia la actividad
        claseBuena.putExtra("dato", val);
        startActivity(claseBuena);
    }

    //El boton te lleva a la calle que lleva al COL_SaludoAmigos
    public void claseMala(View view){
        //Se obtiene el porcentaje actual y se actualiza al escoger mal
        int valor = PorcentajeActual+10;
        String val = String.valueOf(valor);
        //Se crea el nuevo intento, se envia el dato y se inicializa la actividad
        Intent claseMala = new Intent(this, COL_SaludoAmigos.class);
        claseMala.putExtra("dato", val);
        startActivity(claseMala);
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