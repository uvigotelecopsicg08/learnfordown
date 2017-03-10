package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class frasein_screen extends AppCompatActivity {
TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frasein_screen);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(frasein_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(frasein_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void nivel1(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, frasegame1lvl_screen.class);
        String strName = "frasessilabasinversas";
        intent.putExtra("tipoSilaba", strName);
        intent.putExtra("nivel",1);
        startActivity(intent);
    }
    public void nivel2(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, frasegame1lvl_screen.class);
        String strName = "frasessilabasinversas";
        intent.putExtra("tipoSilaba", strName);
        intent.putExtra("nivel",2);
        startActivity(intent);
    }
    public void nivel3(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, frasegame1lvl_screen.class);
        String strName = "frasessilabasinversas";
        intent.putExtra("tipoSilaba", strName);
        intent.putExtra("nivel",3);
        startActivity(intent);
    }
    public void nivel4(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, frasegame1lvl_screen.class);
        String strName = "frasessilabasinversas";
        intent.putExtra("tipoSilaba", strName);
        intent.putExtra("nivel",4);
        startActivity(intent);
    }
}
