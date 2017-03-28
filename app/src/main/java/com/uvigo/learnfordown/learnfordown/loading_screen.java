package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.io.ObjectOutputStream;
import java.util.HashMap;

public class loading_screen extends AppCompatActivity {

    String nombre;
    int edad;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        System.out.println("LoadingScreenActivity  screen started");
        ProgressBar s = (ProgressBar )findViewById(R.id.mainSpinner1);
        s.setVisibility(View.VISIBLE);
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(500);  //Delay of 0.5 seconds
                    Intent intent = getIntent();
                    Bundle extras = intent.getExtras();
                    if(extras != null) {
                        nombre = extras.getString("nombre");
                        edad = extras.getInt("edad");
                        HashMap<String,Boolean> gustos = (HashMap<String,Boolean>)intent.getSerializableExtra("map");
                        Context context =getApplicationContext();
                        context.deleteDatabase("learn.sqlite");
                        InsertData iD = new InsertData(context);
                        iD.insertar(nombre,edad,gustos);
                    }


                } catch (Exception e) {

                } finally {

                    Intent intent = new Intent(loading_screen.this,home_screen.class);
                    loading_screen.this.startActivity(intent);
                    loading_screen.this.finish();
                }
            }
        };
        welcomeThread.start();

    }

}
