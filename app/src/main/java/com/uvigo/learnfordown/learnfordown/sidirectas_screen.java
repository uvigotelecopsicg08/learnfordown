package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class sidirectas_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidirectas_screen);
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(sidirectas_screen.this, menu_screen.class);
        startActivity(intent1);
    }
}