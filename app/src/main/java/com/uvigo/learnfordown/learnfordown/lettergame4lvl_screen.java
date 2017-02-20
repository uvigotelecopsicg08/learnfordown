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

public class lettergame4lvl_screen extends AppCompatActivity {
    TextView titulo;
    Button ButtonActual;
    String Correcta = "R";
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    private RecyclerView horizontal_recycler_view2;
    private ArrayList<String> horizontalList2;
    private HorizontalAdapter horizontalAdapter2;
    final HashMap<Integer, Float> thresholds = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame4lvl_screen);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        horizontal_recycler_view = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontal_recycler_view2 = (RecyclerView) findViewById(R.id.horizontal_recycler_view2);
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        RatingBar  ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas

        horizontalList = new ArrayList<String>();
        horizontalList.add("A");
        horizontalList.add("R");
        horizontalList.add("C");
        horizontalList.add("D");
        horizontalList.add("E");

        horizontalAdapter = new HorizontalAdapter(horizontalList);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        horizontalList2 = new ArrayList<String>();
        horizontalList2.add("R");
        horizontalList2.add("B");
        horizontalList2.add("C");
        horizontalList2.add("D");
        horizontalList2.add("E");

        horizontalAdapter2 = new HorizontalAdapter(horizontalList2);

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(lettergame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);


        horizontal_recycler_view.setAdapter(horizontalAdapter);


        horizontal_recycler_view2.setAdapter(horizontalAdapter2);
    }

    public void BackArrow(View v) {
        Intent intent1 = new Intent(lettergame4lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }

    public void goHome(View v) {
        Intent intent1 = new Intent(lettergame4lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void ButtonCheck(View v) {
        Button b = (Button) v;
        ButtonActual =b;
        String buttonText = b.getText().toString();
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                -50.0f, 0.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {
                    ButtonActual.setBackgroundColor(Color.GREEN);
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