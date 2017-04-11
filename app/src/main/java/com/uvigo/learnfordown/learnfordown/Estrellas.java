package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;

import com.LearnForDown.RecogeMonedas.UnityPlayerActivity;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by Juani on 24/03/2017.
 */

public class Estrellas {

    private Context c;
    int contador,idestrellitas;
    RatingBar ratingbar1;
    GestionNiveles gn;
    AppCompatActivity app;
    SoundPool soundPool;
    Intent minijuego;
    private MediaPlayer aciertoMedia,fallo;

    final HashMap<Integer, Float> thresholds = new HashMap<>();
    public Estrellas(){}

    public  Estrellas(AppCompatActivity app, GestionNiveles gn, int contador){

        aciertoMedia = MediaPlayer.create(app.getApplicationContext(),R.raw.acierto);
        fallo = MediaPlayer.create(app.getApplicationContext(),R.raw.disparo);


       this.app =app;
       this.gn =gn;
       this.contador = contador;
       ratingbar1 = (RatingBar) app.findViewById(R.id.ratingBar);
       thresholds.clear();
       thresholds.put(1, 1f); // 1 aciertoMedia, 1 estrella
       thresholds.put(2, 2f); //10 aciertos, 2 estrellas
       thresholds.put(3, 3f); //20 aciertos, 3 estrellas
       thresholds.put(4, 4f); //30 aciertos, 4 estrellas
       thresholds.put(5, 5f); //40 aciertos, 5 estrellas
       thresholds.put(6, 6f); //50 aciertos, 6 estrellas
       pulsar(false);
    }

    public void setC(Context c) {
        this.c = c;
    }

    public MediaPlayer getAciertoMedia() {
        return aciertoMedia;
    }

    public void pulsar(boolean to) {
        float rating = 0;

        for (int i : new TreeSet<>(thresholds.keySet())) {
            if (contador < i) {
                break;
            }
            rating = thresholds.get(i);
        }
        if (rating != ratingbar1.getRating()) {
            ratingbar1.setRating(rating);

            if (to){
                //Toast toast = Toast.makeText(app, "¡HAS CONSEGUIDO UNA ESTRELLITA!", Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, -270, -50);
                //toast.show();

                Intent intent =new Intent(app.getApplicationContext(),poppuzzle.class);
                intent.putExtra("primera","no");
                intent.putExtra("imagen",R.drawable.estrellita);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                app.getApplicationContext().startActivity(intent);
                gn.actualizarEstrellas(contador);
             }

        }
    }
    public void acierto(){

        aciertoMedia.start();
        contador++;
        gn.acierto();





    }

    public void resetPanelEstrellas(){
       contador= 0;
        gn.actualizarEstrellas(contador);
        pulsar(false);
        gn.resetNivel();

    }
    public void fallo(){
        fallo.start();
        gn.fallo();

    }
    public void setRatingbar1(int id){
        ratingbar1 = (RatingBar) app.findViewById(id);
        pulsar(false);
    }



    }



