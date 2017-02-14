package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.RecyclerView;

public class lettergame1lvl_screen extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    String Correcta="C";
    TextView titulo;
    ImageButton BackArrow,Home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame1lvl_screen);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        BackArrow = (ImageButton) findViewById(R.id.button3);
        Home = (ImageButton) findViewById(R.id.button5);
        titulo.setTypeface(face);

        horizontalList=new ArrayList<String>();
        horizontalList.add("A");
        horizontalList.add("B");
        horizontalList.add("C");
        horizontalList.add("D");
        horizontalList.add("E");

        horizontalAdapter=new HorizontalAdapter(horizontalList);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame1lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);





        horizontal_recycler_view.setAdapter(horizontalAdapter);




    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(lettergame1lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(lettergame1lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }
    public void ButtonCheck (View v){
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        if (Correcta.equals(buttonText)){
            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                    -50.0f, 0.0f);
            animation.setDuration(2000);
            animation.setFillAfter(true);
            b.startAnimation(animation);
//Codigo de Animacion Acierto
        } else{
            //Codigo de Animacion Fallo

        }
    }

}
