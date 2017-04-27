package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;

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

     HashMap<Integer, Float> thresholds = new HashMap<>();
    public Estrellas(){}

    public  Estrellas(AppCompatActivity app, GestionNiveles gn, int contador){

        aciertoMedia = MediaPlayer.create(app.getApplicationContext(),R.raw.acierto);
        fallo = MediaPlayer.create(app.getApplicationContext(),R.raw.disparo);


       this.app =app;
       this.gn =gn;
       this.contador = contador;
       ratingbar1 = (RatingBar) app.findViewById(R.id.ratingBar);
        /*
       thresholds.clear();
       thresholds.put(100, 1f); // 1 aciertoMedia, 1 estrella
       thresholds.put(200, 2f); //10 aciertos, 2 estrellas
       thresholds.put(300, 3f); //20 aciertos, 3 estrellas
       thresholds.put(400, 4f); //30 aciertos, 4 estrellas
       thresholds.put(500, 5f); //40 aciertos, 5 estrellas
       thresholds.put(600, 6f); //50 aciertos, 6 estrellas
       */
        thresholds =getThresholds();
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
    private HashMap<Integer, Float> getThresholds(){
        int numeros[]=null;
        switch(gn.getTipo()) {
            case "leerletras":
                switch (gn.getDificultad()){
                    case 1:
                        numeros = new int[]{1,12,24,36,50,69};
                        break;
                    case 2:
                        numeros = new int[]{1,22,44,66,88,109};
                        break;
                    case 3:
                        numeros = new int[]{1,30,60,90,120,154};
                        break;
                    case 4:
                        numeros = new int[]{1,33,66,99,132,171};
                        break;
                }
                break;
            case "silabasdirectas":
                switch (gn.getDificultad()){
                    case 1:
                        numeros = new int[]{1,10,20,30,40,51};
                        break;
                    case 2:
                        numeros = new int[]{1,15,31,45,63,83};
                        break;
                    case 3:
                        numeros = new int[]{1,24,48,72,96,119};
                        break;
                    case 4:
                        numeros = new int[]{1,25,50,75,100,122};
                        break;
                }
                break;
            case "silabasinversas":
                switch (gn.getDificultad()){
                    case 1:
                        numeros = new int[]{1,3,6,9,11,15};
                        break;
                    case 2:
                        numeros = new int[]{1,4,8,13,18,24};
                        break;
                    case 3:
                        numeros = new int[]{1,5,10,15,20,26};
                        break;
                    case 4:
                        numeros = new int[]{1,5,11,16,21,28};
                        break;
                }
                break;
            case "silabastrabadas":
                switch (gn.getDificultad()){
                    case 1:
                        numeros = new int[]{1,4,7,11,15,20};
                        break;
                    case 2:
                        numeros = new int[]{1,5,9,14,19,26};
                        break;
                    case 3:
                        numeros = new int[]{1,4,8,12,16,22};
                        break;
                    case 4:
                        numeros = new int[]{1,4,8,12,16,22};
                        break;
                }
                break;
            case "palabrassilabasdirectas":
                numeros = new int[]{1,7,12,18,24,30};
                break;
            case "palabrassilabasinversas":
                numeros = new int[]{1,6,11,17,21,27};
                break;
            case "palabrassilabastrabadas":
                numeros = new int[]{1,4,7,10,14,18};
                break;
            case "frasessilabasdirectas":
                numeros = new int[]{1,3,5,7,9,12};
                break;
            case "frasessilabasinversas":
                numeros = new int[]{1,3,5,7,9,12};
                break;
            case "frasessilabastrabadas":
                numeros = new int[]{1,2,4,6,8,11};
                break;
            case "escribirletras":
                numeros = new int[]{1,5,11,16,21,27};
                break;
            case "escribirconsombreado":
                numeros = new int[]{1,7,12,18,24,30};
                break;
            case "escribirsinsombreado":
                numeros = new int[]{1,8,16,24,32,40};
                break;
            case "escribirtecladopalabra":
                numeros = new int[]{1,10,20,30,40,50};
                break;
        }


        return generarMap(numeros);
    }

private  HashMap<Integer, Float> generarMap(int[] numeros){
    Float[] estrellas= new Float[]{1f,2f,3f,4f,5f,6f};
    HashMap<Integer, Float> map = new HashMap<>();
    for(int i=0;i<numeros.length;i++){
        map.put(numeros[i],estrellas[i]);
        }
    return map;
    }
}




