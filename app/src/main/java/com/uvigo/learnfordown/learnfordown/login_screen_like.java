package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;

public class login_screen_like extends AppCompatActivity implements View.OnClickListener  {

  //  boolean[] opcion=new boolean[6];
    int[] drawable =new int[6];
    String[]  gustosString ={"animales","coches","ropa","casa","playa","musica"};
    int numero=0;
    ImageView foto;
    String nombre;
    int edad;
    HashMap<String,Boolean> gustos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_like);
        numero=0;
        gustos =new HashMap<String,Boolean>();
         foto = (ImageView) findViewById(R.id.imageView);

        drawable[0]=R.drawable.oso;
        drawable[1]=R.drawable.coche;
        drawable[2]=R.drawable.bufanda;
        drawable[3]=R.drawable.casa;
        drawable[4]=R.drawable.playa;
        drawable[5]=R.drawable.guitarra;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            nombre = extras.getString("nombre");
            edad = extras.getInt("edad");
        }


       Button buttonYes=(Button)findViewById(R.id.buttonYes);
        buttonYes.setOnClickListener(this);
        Button buttonNo=(Button)findViewById(R.id.buttonNo);
        buttonNo.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonYes:
                //opcion[numero]=true;
                gustos.put(gustosString[numero],true);
                break;
            case R.id.buttonNo:
               // opcion[numero]=false;
                gustos.put(gustosString[numero],false);
                break;

        }
        if(numero==5){
           // numero=0;
          //  foto.setImageResource(drawable[numero]);
            Context context =this.getApplicationContext();
            DataBaseManager db = new DataBaseManager(context);
            context.deleteDatabase("learn.sqlite");
            InsertData iD = new InsertData(context);
            iD.insertar(nombre,edad,gustos);
           // db.insertar_user(nombre,edad,gustos);
            //db.close();
            Intent intent = new Intent(this, home_screen.class);
            startActivity(intent);
        }else {
            numero++;
            foto.setImageResource(drawable[numero]);

        }

    }
    public void goHome (View v){
        Intent intent1 = new Intent(login_screen_like.this, home_screen.class);
        startActivity(intent1);
    }

    public void BackArrow(View v) {
        Intent intent1 = new Intent(login_screen_like.this, login_screen.class);
        startActivity(intent1);
    }
}
