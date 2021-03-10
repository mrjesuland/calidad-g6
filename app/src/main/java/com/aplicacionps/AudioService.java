package com.aplicacionps;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

//Servicio que implementan todas las activity que posean musica de fondo_coronavirus
public class AudioService extends Service {
    //Distintas acciones que se pueden realizar. En orden son: disminuir volumen, aumentar volumen,
    // empezar musica, parar musica
    static final int DECREASE = 1, INCREASE= 2, START = 3, PAUSE = 4;
    Boolean ShouldPause = false;
    MediaPlayer loop;
    //La musica se ejecuta en bucle
    private void startLoop(){
        //Si aun no se ha ejecutado el bucle, se inserta la cancion en loop
        // (musica_fondo_final en carpeta raw)
        if(loop == null){
            loop = MediaPlayer.create(this, R.raw.musica_fondo_final);
        }
        //Si ya se habia asignado una cancion y termina, se reproduce desde el principio
        if(!loop.isPlaying()){
            loop.setLooping(true);
            loop.start();
        }
    }

    //Funcion que disminuye el volumen de la musica
    private void decrease(){
        loop.setVolume(0.2f, 0.2f);
    }

    //Funcion que aumenta el volumen de la musica
    private void increase(){
        loop.setVolume(1.0f, 1.0f);
    }

    //Funcion que empieza la reproduccion de la musica
    private void start(){
        startLoop();
        ShouldPause = false;
    }

    //Funcion que pausa la musica
    private void stop(){
        ShouldPause = true;
        //Se pausa el bucle para poder reanudarlo cuando se vuelva a la activity (sensacion de
        // continuidad en la musica)
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(ShouldPause &&loop!=null) {
                            loop.pause();
                        }
                    }
                }, 100);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getClass().getSimpleName(), "Creating service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(getClass().getSimpleName(), "Intent received");
        try {
            int actionDefault = 0;
            int action = actionDefault;
            if(intent != null){
                if(intent.hasExtra("action")){
                    action = intent.getIntExtra("action", actionDefault);
                }
            }
            //Dependiendo el valor que se introduzca a la función se ejecuta una funcion u otra
            switch (action) {
                case INCREASE:
                    increase();
                    break;
                case DECREASE:
                    decrease();
                    break;
                case START:
                    start();
                    break;
                case PAUSE:
                    stop();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Se libera la memoria que ocupase la cancion al destruir este servicio (cierre total de la aplicacion)
        if (loop != null) loop.release();
    }

    //Funcion por defecto que tiene que aparecer en todos los service
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
}

//LAS SIGUIENTES TRES LÍNEAS SE INSERTAN EN EL PROCEDIMEINTO onPause() DE TODA ACTIVITY QUE REPRODUZCA MUSICA DE FONDO
/*Intent i = new Intent(this, AudioService.class);
  i.putExtra("action", AudioService.PAUSE);
  startService(i);*/

//LAS SIGUIENTES DOS LINEAS SE INSERTAN EN EL PROCEDIMIENTO onResume() DE TODA ACTIVITY QUE REPRODUZCA MUSICA DE FONDO
/*SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
  Boolean valordelboton = sharedPreferences.getBoolean("value", false);*/