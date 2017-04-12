package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class endScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        MediaPlayer exito = MediaPlayer.create(this, R.raw.success);
        exito.start();
    }

    public void home (View view){
        Intent intent1 = new Intent(endScreen.this, home_screen.class);
        startActivity(intent1);
    }

}
