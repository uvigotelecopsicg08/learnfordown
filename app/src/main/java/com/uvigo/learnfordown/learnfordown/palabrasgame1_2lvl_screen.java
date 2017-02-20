package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.TreeSet;

public class palabrasgame1_2lvl_screen extends AppCompatActivity {
    TextView titulo;
    Integer ultimoPulsado;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
    RatingBar ratingbar1 = null;
    String figure = "plato";
    String button1 = "plano";
    String button2 = "plato";
    String button3 = "platano";
    int contador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabrasgame1_2lvl_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        ultimoPulsado = null;
         ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas

    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(palabrasgame1_2lvl_screen.this, palabrasgame1_1lvl_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(palabrasgame1_2lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void pulsar (View v){
        Log.i("pulsar()", v.getId() + " ultimoPulsado:" +  ultimoPulsado);
        if(ultimoPulsado != null) {
            switch (v.getId()) {
                case R.id.imageButton1:
                    if(ultimoPulsado.equals(R.id.button1)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.imageButton2:
                    if(ultimoPulsado.equals(R.id.button2)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.imageButton3:
                    if(ultimoPulsado.equals(R.id.button3)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.button1:
                    if(ultimoPulsado.equals(R.id.imageButton1)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.button2:
                    if(ultimoPulsado.equals(R.id.imageButton2)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.button3:
                    if(ultimoPulsado.equals(R.id.imageButton3)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
            }
        }
        ultimoPulsado = v.getId();
    }

}
