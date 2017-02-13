package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class palabrasgame1_1lvl_screen extends AppCompatActivity {
    TextView titulo;
    Integer ultimoPulsado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabrasgame1_1lvl_screen);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView1);
        titulo.setTypeface(face);
        ultimoPulsado = null;
    }

    public void BackArrow(View v) {
        Intent intent1 = new Intent(palabrasgame1_1lvl_screen.this, palabrasdi_screen.class);
        startActivity(intent1);
    }

    public void seguir(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, palabrasgame1_2lvl_screen.class);
        startActivity(intent);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(palabrasgame1_1lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

}
