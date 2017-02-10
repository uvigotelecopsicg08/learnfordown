package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class palabrasgame1_2lvl_screen extends AppCompatActivity {
    TextView titulo;
    Integer ultimoPulsado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabrasgame1_2lvl_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView1);
        titulo.setTypeface(face);
        ultimoPulsado = null;
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(palabrasgame1_2lvl_screen.this, palabrasgame1_1lvl_screen.class);
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
                    }
                    break;
                case R.id.imageButton2:
                    if(ultimoPulsado.equals(R.id.button2)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.imageButton3:
                    if(ultimoPulsado.equals(R.id.button3)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.button1:
                    if(ultimoPulsado.equals(R.id.imageButton1)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.button2:
                    if(ultimoPulsado.equals(R.id.imageButton2)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.button3:
                    if(ultimoPulsado.equals(R.id.imageButton3)){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
        ultimoPulsado = v.getId();
    }
}
