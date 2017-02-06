package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class sitrabadas_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitrabadas_screen);
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(sitrabadas_screen.this, menu_screen.class);
        startActivity(intent1);
    }
}
