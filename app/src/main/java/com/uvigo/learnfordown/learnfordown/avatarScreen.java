package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class avatarScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_screen);
    }

    public void goHome (View v){
        Intent intent1 = new Intent(avatarScreen.this, home_screen.class);
        startActivity(intent1);
    }
    public void BackArrow (View v) {
        Intent intent1 = new Intent(avatarScreen.this, home_screen.class);
        startActivity(intent1);
    }
}
