package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class login_screen_like extends AppCompatActivity implements View.OnClickListener  {

    boolean[] opcion=new boolean[6];
    int[] drawable =new int[6];
    int numero=0;
    ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_like);
        numero=0;
         foto = (ImageView) findViewById(R.id.imageView);
        drawable[0]=R.drawable.oso;
        drawable[1]=R.drawable.coche;
        drawable[2]=R.drawable.bufanda;
        drawable[3]=R.drawable.casa;
        drawable[4]=R.drawable.playa;
        drawable[5]=R.drawable.guitarra;


       Button buttonYes=(Button)findViewById(R.id.buttonYes);
        buttonYes.setOnClickListener(this);
        Button buttonNo=(Button)findViewById(R.id.buttonNo);
        buttonNo.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonYes:
                opcion[numero]=true;
            case R.id.buttonNo:
                opcion[numero]=false;

        }
        if(numero==5){
            numero=0;
            foto.setImageResource(drawable[numero]);
        }else {
            numero++;
            foto.setImageResource(drawable[numero]);

        }

    }
    public void goHome (View v){
        Intent intent1 = new Intent(login_screen_like.this, home_screen.class);
        startActivity(intent1);
    }
}
