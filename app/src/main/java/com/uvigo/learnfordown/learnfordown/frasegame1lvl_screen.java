package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class frasegame1lvl_screen extends AppCompatActivity {
    TextView titulo;
    String figure = "plato";
    String button1 = "plano";
    String button2 = "plato";
    String button3 = "platano";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frasegame1lvl_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView1);
        titulo.setTypeface(face);
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(frasegame1lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }

    public void pulsar (View v){

        switch (v.getId()) {
            case R.id.button1:
                if(figure.equals(button1)){
                    Log.i("pulsar()", "CORRECTO!");
                    Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button2:
                if(figure.equals(button2)){
                    Log.i("pulsar()", "CORRECTO!");
                    Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button3:
                if(figure.equals(button3)){
                    Log.i("pulsar()", "CORRECTO!");
                    Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}

