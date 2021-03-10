package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SUP_EscenarioCasa extends AppCompatActivity {
    //Se crea un nuevo sistema de contagio con porcentaje igual a 0 y se crea un ProgressBar que será
    //utilizado después
    SistemaContagio si= new SistemaContagio(0);
    private ProgressBar ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionamos la clase EscenarioCasa con su respectivo XML activity_entradacasa
        setContentView(R.layout.activity_sup_entradacasa);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        // pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se relaciona la ProgressBar creada con la ProgressBar del activity y se inicializa el porcentaje que muestra
        ProgressBar = (ProgressBar)findViewById(R.id.barra1);
        ProgressBar.setProgress(si.getPorcentaje());

    }

    //Este método mascarilla corresponde con la parte lógica del botón 'coger mascarilla y gel' del XML activity_entradacasa,
    //y lo que hará es que nos llevará al siguiente escenario que corresponde la clase ElegirCamino
    public void mascarilla(View view){
        //Se obtiene el porcentaje y el booleano de la mascarilla de pone a true
        int valor = si.getPorcentaje();
        String bool = Boolean.toString(true);
        String val = String.valueOf(valor);
        Intent mascarilla = new Intent (this, SUP_ElegirCamino.class);
        //Se pasan los datos anteriormente sacados al siguiente activity y se inicializa
        mascarilla.putExtra("dato", val);
        mascarilla.putExtra("masc", bool);
        startActivity(mascarilla);
    }

    //Este método noMascarilla corresponde con la parte lógica del botón 'no coger nada' del XML activity_entradacasa,
    //y por el momento, al no estar disponible la acción de incrementar el porcentaje de contagio de nuestro personaje,
    //nos saldrá por pantalla un toast con el mensaje 'No disponible'
    public void noMascarilla(View view){
        //Se obtiene el porcentaje y el booleano de la mascarilla de pone a false
        int valor = si.getPorcentaje() + 10;
        String bool = Boolean.toString(false);
        String val = String.valueOf(valor);
        Intent noMascarilla = new Intent (this, SUP_ElegirCamino.class);
        //Se pasan los datos anteriormente sacados al siguiente activity y se inicializa
        noMascarilla.putExtra("dato", val);
        noMascarilla.putExtra("masc", bool);
        startActivity(noMascarilla);
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