package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class PantallaFinal2 extends AppCompatActivity {
    //Se crea una variable para almacenar el porcentaje final obtenido y 2 textview para mostrar el
    //porcentaje y el Mensaje final
    private int PorcentajeActual;
    private Boolean vuelta;
    private TextView Mensaje;
    private TextView Porciento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionamos la clase PantallaFinal.java con su XML activity_pantallafinal.xml
        setContentView(R.layout.activity_pantallafinal);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        //pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtiene el porcentaje de la actividad anterior
        String Dato = getIntent().getStringExtra("dato");
        String pantalla = getIntent().getStringExtra("volver");
        vuelta = Boolean.parseBoolean(pantalla);
        PorcentajeActual = Integer.parseInt(Dato);
        //Se relaciona el TextView "Porciento" con el del activity y muestra el porcentaje final obtenido
        Porciento = (TextView) findViewById(R.id.porciento);
        Porciento.setText("PORCENTAJE = " + Dato);
        //Se relaciona el TextView "Mensaje" con el del activity
        Mensaje = (TextView) findViewById(R.id.mensajeFinal);
        //Se obtiene un numero aleatorio que dependiendo del porcentaje final hará que aparezca un
        //Mensaje diferente cada vez
        int numAleatorio = (int) (Math.random() * 100);

        //Si el porcentaje final obtenido es 0 no se puede contagiar
        if (PorcentajeActual == 0) {
            Mensaje.setText("¡ENHORABUENA! No te has contagiado ya que tienes un 0 por ciento de probabilidades. Sigue así.");
            //Si el porcentaje final es 100 o mas se contagiará si o si
        }
        else if (PorcentajeActual <= 100) {
            Mensaje.setText("Te has contagiado. Ten más cuidado ya que puedes enfermar a los que más quieres.");
        }
        else {
            //Si el numero aleatorio obtenido es menor o igual que el porcentaje obtenido, se contagiará
            if (numAleatorio <= PorcentajeActual) {
                //Dependiendo del porcentaje que se haya obtenido al final aparecerá un Mensaje diferente cada vez
                if (PorcentajeActual <= 30) {
                    Mensaje.setText("Mala suerte, te has contagiado. Incluso con poco porcentaje te puedes contagiar. Ten más cuidado la proxima vez");
                }
                else if ((PorcentajeActual <= 60) && (PorcentajeActual > 30)) {
                    Mensaje.setText("Mala suerte, te ha contagiado. Ten más cuidado la proxima vez");
                }
                else {
                    Mensaje.setText("Te has contagiado. Tienes que tener más cuidado si no quieres que te vuelva a pasar");
                }
                //Por el contrario, si es mayor no se contagiará
            } else {
                //Dependiendo del porcentaje que se haya obtenido al final aparecerá un Mensaje diferente cada vez
                if (PorcentajeActual <= 30) {
                    Mensaje.setText("No te has contagiado aunque habian pocas posibilidades. Intentalo de nuevo para bajarlas.");
                }
                else if ((PorcentajeActual <= 60) && (PorcentajeActual > 30)) {
                    Mensaje.setText("Has tenido suerte y no te has contagiado. Puedes mejorar este resultado. Intentálo de nuevo");
                }
                else {
                    Mensaje.setText("Has tenido muchiiiisima suerte. Pero puede que algún día no la tengas y lo pilles. Ten cuidado.");
                }
            }
        }
    }

    //Al pulsar el boton de volver a jugar, el juego te lleva a la casa del jugador para iniciar de nuevo
    //el juego_autobus_fuera
    public void volverajugar(View view){
        //Se obtiene el string de la historia a la que debemos volver
        Intent volverInsti = new Intent (this, COL_EntradaInstituto.class);
        startActivity(volverInsti);
    }

    //Al pulsar el boton de menu, te lleva a la pantalla principal del juego
    public void menuprincipal(View view){
        Intent menuprincipal = new Intent (this, MainActivity.class);
        startActivity(menuprincipal);
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
