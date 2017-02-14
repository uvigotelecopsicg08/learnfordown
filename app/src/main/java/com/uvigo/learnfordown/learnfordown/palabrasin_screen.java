package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class palabrasin_screen extends AppCompatActivity {
TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabrasin_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(palabrasin_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(palabrasin_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void nivel1(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, palabrasgame1_1lvl_screen.class);
        startActivity(intent);
    }
}
