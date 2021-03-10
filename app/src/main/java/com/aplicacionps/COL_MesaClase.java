package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class COL_MesaClase extends AppCompatActivity {

    //Se crea un ProgressBar para representar el pocentaje de contagio que lleva el personaje
    private ProgressBar ProgressBar;
    //Se crea una variable para almacenar el valor que se pasa de un activity a otro
    private int PorcentajeActual;
    private boolean Amigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionaremos la clase COL_MesaClase.java con su XML activity_col_mesa_clase.xml
        setContentView(R.layout.activity_col_mesa_clase);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        //pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtiene el dato como string y luego se convierte en su tipo correspondiente
        String Dato = getIntent().getStringExtra("dato");
        String Am = getIntent().getStringExtra("amigo");
        PorcentajeActual = Integer.parseInt(Dato);
        //La barra se relaciona con el activity y se establece el porcentaje que se va a mostrar con
        //el numero anteriormente obtenido
        ProgressBar = (ProgressBar)findViewById(R.id.barra1);
        ProgressBar.setProgress(PorcentajeActual);
        Amigos= Boolean.valueOf(Am);
    }

    //El boton te lleva a la clase COL_Lista
    public void desinfectar(View view) {
        //Se obtiene el porcentaje actual
        int valor = PorcentajeActual;
        String val = String.valueOf(valor);
        //Se crean el intentos
        Intent teacher = new Intent(this, COL_PasarLista.class);
        Intent recreo = new Intent(this, COL_Recreo.class);
        //En ambos caminos se envian los datos para no perderlos y se inicia la actividad correspondiente
        if(Amigos){
            teacher.putExtra("dato", val);
            startActivity(teacher);
        }
        else{
            recreo.putExtra("dato", val);
            startActivity(recreo);
        }
    }

    //El boton te lleva a la clase COL_Lista
    public void noDesinfectar(View view){
        //Se obtiene el porcentaje actual
        int valor = PorcentajeActual + 10;
        String val = String.valueOf(valor);
        //Se crea el nuevo intento, se envia el dato y se inicializa la actividad
        Intent teacher = new Intent(this, COL_PasarLista.class);
        Intent recreo = new Intent(this, COL_Recreo.class);
        //En ambos caminos se envian los datos para no perderlos y se inicia la actividad correspondiente
        if(Amigos){
            teacher.putExtra("dato", val);
            startActivity(teacher);
        }
        else{
            recreo.putExtra("dato", val);
            startActivity(recreo);
        }
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