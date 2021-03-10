package com.aplicacionps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class COL_ClaseBuena extends AppCompatActivity {
    //Se crea un ProgressBar para representar el pocentaje de contagio que lleva el personaje
    private ProgressBar ProgressBar;
    //Se crea otra variable para almacenar el valor que se pasa de un activity a otro
    private int PorcentajeActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionaremos la clase SuperDentro.java con su XML activity_superdentro.xml
        setContentView(R.layout.activity_col_clase_buena);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        // pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtienen el dato como string y luego se convierte en sus tipo correspondiente
        String Dato = getIntent().getStringExtra("dato");
        PorcentajeActual = Integer.parseInt(Dato);
        //La barra se relaciona con el activity y se establece el porcentaje que se va a mostrar con
        //el numero anteriormente obtenido
        ProgressBar = (ProgressBar) findViewById(R.id.barra1);
        ProgressBar.setProgress(PorcentajeActual);
    }

    //El boton te lleva a la cola del super al haber acabado la compra. Al ser la opion correcta,
    //el porcentaje de contagio no variará
    public void esNecesario(View view) {
        int valor = PorcentajeActual;
        String Bool= String.valueOf(false);
        String val = String.valueOf(valor);
        Intent esNecesario = new Intent(this, COL_MesaClase.class);
        esNecesario.putExtra("amigos", Bool);
        esNecesario.putExtra("dato", val);
        startActivity(esNecesario);
    }

    //El boton te lleva a la cola del super al haber acabado la compra. Al ser la opcion incorrecta,
    //se aumentará en un 10% el porcentaje de contagio
    public void tengoFrio(View view) {
        int valor = PorcentajeActual + 10;
        String Bool= String.valueOf(false);
        String val = String.valueOf(valor);
        Intent tengoFrio = new Intent(this, COL_MesaClase.class);
        tengoFrio.putExtra("amigos", Bool);
        tengoFrio.putExtra("dato", val);
        startActivity(tengoFrio);
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