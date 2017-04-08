package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class avatarScreen extends AppCompatActivity {
    int edad;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_screen);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            nombre = extras.getString("nombre");
            edad = extras.getInt("edad");
        }
    }

    public void goHome (View v){
        Intent intent1 = new Intent(avatarScreen.this, home_screen.class);
        startActivity(intent1);
    }
    public void BackArrow (View v) {
        Intent intent1 = new Intent(avatarScreen.this, home_screen.class);
        startActivity(intent1);
    }


    public void pulsar1(View v){
    lanzaIntent(R.drawable.avatara);

    }
    public void pulsar2(View v){
        lanzaIntent(R.drawable.policia);

    }
    public void pulsar3(View v){
        lanzaIntent(R.drawable.abeja);

    }

    public void pulsar4(View v){
        lanzaIntent(R.drawable.cabra);

    }
    public void pulsar5(View v){
        lanzaIntent(R.drawable.guitarra);

    }
    public void pulsar6(View v){
        lanzaIntent(R.drawable.caballo);

    }



    public void lanzaIntent(int avatar){
        Intent intent = new Intent(this, login_screen_like.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("edad",edad);
        intent.putExtra("avatar", avatar);
        startActivity(intent);
    }
}
