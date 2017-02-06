package com.uvigo.learnfordown.learnfordown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

public class MenuLectura1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lectura1);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
    }

}
