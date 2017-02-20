package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class lettergame3lvl_screen extends AppCompatActivity {
    TextView titulo;
    String Correcta="";
    Button ButtonActual;
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    int aciertos=0;
    GestionNiveles gn;
    RatingBar ratingbar1 = null;
    final HashMap<Integer, Float> thresholds = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame3lvl_screen);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas

        horizontalList=new ArrayList<String>();
        horizontalList.add("E");
        horizontalList.add("K");
        horizontalList.add("I");
        horizontalList.add("P");
        horizontalList.add("O");

        horizontalAdapter=new HorizontalAdapter(horizontalList);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame3lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(lettergame3lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(lettergame3lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void pulsar() {
        float rating = 0;
        for (int i : new TreeSet<>(thresholds.keySet())) {
            if (gn.getAciertos() < i) {
                break;
            }
            rating = thresholds.get(i);
        }
        ratingbar1.setRating(rating);
    }

    public void ButtonCheck (View v){
        Button b = (Button)v;
        ButtonActual =b;
            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                    -50.0f, 0.0f);
            animation.setDuration(400);
            animation.setFillAfter(true);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (Correcta.equals(ButtonActual.getText().toString())) {
                        ButtonActual.setBackgroundColor(Color.GREEN);
                        gn.acierto();
                        pulsar();
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (Correcta.equals(ButtonActual.getText().toString())) {


                    }
//Codigo de Animacion Acierto
                    else {
                        //Codigo de Animacion Fallo


                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            b.startAnimation(animation);

    }
}
