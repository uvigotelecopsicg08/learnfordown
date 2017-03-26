package com.uvigo.learnfordown.learnfordown;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by Juani on 24/03/2017.
 */

public class Estrellas {
    int contador;
    RatingBar ratingbar1;
    GestionNiveles gn;
    AppCompatActivity app;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
   public  Estrellas(AppCompatActivity app, GestionNiveles gn,int contador){
       this.app =app;
       this.gn =gn;
       this.contador = contador;
       ratingbar1 = (RatingBar) app.findViewById(R.id.ratingBar);
       thresholds.clear();
       thresholds.put(1, 1f); // 1 acierto, 1 estrella
       thresholds.put(10, 2f); //10 aciertos, 2 estrellas
       thresholds.put(20, 3f); //20 aciertos, 3 estrellas
       thresholds.put(30, 4f); //30 aciertos, 4 estrellas
       thresholds.put(40, 5f); //40 aciertos, 5 estrellas
       thresholds.put(50, 6f); //50 aciertos, 6 estrellas
       pulsar(false);
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
                Toast toast = Toast.makeText(app, "¡HAS CONSEGUIDO UNA ESTRELLITA!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, -270, -50);
                 toast.show();
             }
            gn.actualizarEstrellas(contador);
        }
    }
    public void acierto(){
        contador++;
        gn.acierto();
    }

    public void resetPanelEstrellas(){
       contador= 0;
        gn.actualizarEstrellas(contador);
        pulsar(false);
        gn.resetNivel();

    }
}
