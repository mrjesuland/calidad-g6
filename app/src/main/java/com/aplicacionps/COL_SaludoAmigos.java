package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class COL_SaludoAmigos extends AppCompatActivity {

    //Se crea un ProgressBar para representar el pocentaje de contagio que lleva el personaje
    private ProgressBar ProgressBar;
    //Se crea una variable para almacenar el valor que se pasa de un activity a otro
    private int PorcentajeActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relaccionaremos la clase COL_SaludoAmigos.java con su XML activity_col_saludo_amigos.xml
        setContentView(R.layout.activity_col_saludo_amigos);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        //pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtiene el dato como string y luego se convierte en su tipo correspondiente
        String Dato = getIntent().getStringExtra("dato");
        PorcentajeActual = Integer.parseInt(Dato);
        //La barra de progreso se relaciona con el activity y se establece el porcentaje que se va a
        //mostrar con el numero anteriormente obtenido
        ProgressBar = (ProgressBar)findViewById(R.id.barra1);
        ProgressBar.setProgress(PorcentajeActual);
    }

    //El boton te lleva directamente a la clase COL_MesaClase
    public void ConLaMano(View view){
        //Se obtiene el porcentaje actual y se actualiza al elegir una mala opcion
        int valor= PorcentajeActual + 10;
        String Bool= String.valueOf(true);
        String val= String.valueOf(valor);
        //Se crea el nuevo activity, se pasa el dato anteriormente sacado y se inicializa
        Intent ConLaMano = new Intent (this, COL_MesaClase.class);
        ConLaMano.putExtra("amigo", Bool);
        ConLaMano.putExtra("dato", val);
        startActivity(ConLaMano);
    }

    //El boton te lleva directamnete a la clase COL_MesaClase
    public void ConElCodo(View view){
        //Se obtiene el porcentaje actual
        int valor= PorcentajeActual;
        String Bool= String.valueOf(true);
        String val= String.valueOf(valor);
        //Se crea el nuevo activity, se pasa el dato anteriormente sacado y se inicializa
        Intent ConElCodo = new Intent (this, COL_MesaClase.class);
        ConElCodo.putExtra("amigo", Bool);
        ConElCodo.putExtra("dato", val);
        startActivity(ConElCodo);
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