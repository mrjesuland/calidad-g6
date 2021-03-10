package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class COL_EntradaInstituto extends AppCompatActivity {

    //Se crea un nuevo sistema de contagio con porcentaje igual a 0 y se crea un ProgressBar que será
    //utilizado después
    SistemaContagio si = new SistemaContagio(0);
    private ProgressBar ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionamos la clase COL_EntradaInstituto con su respectivo XML activity_col_entrada_instituto
        setContentView(R.layout.activity_col_entrada_instituto);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        // pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se relaciona la ProgressBar creada con la ProgressBar del activity y se inicializa el porcentaje que muestra
        ProgressBar = (ProgressBar)findViewById(R.id.barra1);
        ProgressBar.setProgress(si.getPorcentaje());

    }

    //Este método gelSi corresponde con la parte lógica del botón 'si' del XML activity_col_entrada_instituto,
    //y lo que hará es que nos llevará al siguiente escenario que corresponde la clase COL_Pasillos
    public void gelSi(View view){
        //Se obtiene el porcentaje de contagio
        int valor = si.getPorcentaje();
        String val = String.valueOf(valor);
        Intent gelSi = new Intent (this, COL_Pasillos.class);
        //Se pasan el dato anteriormente sacado al siguiente activity y se inicializa
        gelSi.putExtra("dato", val);
        startActivity(gelSi);
    }

    //Este método gelNo corresponde con la parte lógica del botón 'no' del XML activity_col_entrada_instituto
    //y lo que hará es que nos llevará al siguiente escenario que corresponde la clase COL_Pasillos
    public void gelNo(View view){
        //Se obtiene el porcentaje y se incrementa al haber escogido mal
        int valor = si.getPorcentaje() + 10;
        String val = String.valueOf(valor);
        Intent gelNo = new Intent (this, COL_Pasillos.class);
        //Se pasan el dato anteriormente sacado al siguiente activity y se inicializa
        gelNo.putExtra("dato", val);
        startActivity(gelNo);
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