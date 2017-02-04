package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class home_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
       Context context= this.getApplicationContext();
        DataBaseManager db= new DataBaseManager(context);
        db.insertar_user("pepe",5);

    }
}
