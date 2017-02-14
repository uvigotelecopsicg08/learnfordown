package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class lettergame3lvl_screen extends AppCompatActivity {
    TextView titulo;
    String Correcta="";
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame3lvl_screen);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);

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
    public void ButtonCheck (View v){
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        if (Correcta.equals(buttonText)){
            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                    -50.0f, 0.0f);
            animation.setDuration(400);
            animation.setFillAfter(true);
            b.startAnimation(animation);
//Codigo de Animacion Acierto
        } else{
            //Codigo de Animacion Fallo

        }
    }
}
