package com.aplicacionps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class SUP_DentroDeLaCalle extends AppCompatActivity {
    //Se crea un ProgressBar para representar el pocentaje de contagio que lleva el personaje
    private ProgressBar ProgressBar;
    //Se crean dos variables para almacenar los valores que se pasan de un activity a otro
    private int PorcentajeActual;
    private boolean Mascarilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionamos la clase DentroDeLaCalle con su respectivo XML activity_dentrodelacalle
        setContentView(R.layout.activity_sup_dentrodelacalle);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        //pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtienen los datos como strings y luego se convierten en sus tipos correspondientes
        String Dato = getIntent().getStringExtra("dato");
        String Masc = getIntent().getStringExtra("masc");
        PorcentajeActual = Integer.parseInt(Dato);
        Mascarilla = Boolean.valueOf(Masc);
        //La barra se relaciona con el activity y se establece el porcentaje que se va a mostrar con
        //el numero anteriormente obtenido
        ProgressBar = (ProgressBar)findViewById(R.id.barra1);
        ProgressBar.setProgress(PorcentajeActual);
    }

    //Este método MeLaQuito corresponde con la parte lógica del botón 'Me la quito, necesito respirar'
    //del XML activity_dentrodelacalle,y por el momento, al no estar disponible la acción de incrementar
    //el porcentaje de contagio de nuestro personaje, nos saldrá por pantalla un toast con el mensaje 'No disponible'
    public void MeLaQuito(View view){
        //Se obtiene el porcentaje actual y el booleano de la Mascarilla y se actualiza al elegir una mala opcion
        String bool = Boolean.toString(Mascarilla);
        int valor= PorcentajeActual + 10;
        String val= String.valueOf(valor);
        //Se crea el nuevo activity, se pasan los datos anteriormente sacados y se inicializa
        Intent MeLaQuito= new Intent(this, SUP_superfuera.class);
        MeLaQuito.putExtra("dato", val);
        MeLaQuito.putExtra("masc", bool);
        startActivity(MeLaQuito);
    }

    //Este método NoMeLaQuito corresponde con la parte lógica del botón 'No me la quito' del XML
    //activity_dentrodelacalle, y lo que hará es que nos llevará al siguiente escenario que corresponde
    // la clase superfuera
    public void NoMeLaQuito(View view){
        //Se obtiene el porcentaje actual y el booleano de la Mascarilla
        String bool = Boolean.toString(Mascarilla);
        int valor= PorcentajeActual;
        String val= String.valueOf(valor);
        //Se crea el nuevo activity, se pasan los datos anteriormente sacados y se inicializa
        Intent NoMeLaQuito= new Intent(this, SUP_superfuera.class);
        NoMeLaQuito.putExtra("dato", val);
        NoMeLaQuito.putExtra("masc", bool);
        startActivity(NoMeLaQuito);
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
