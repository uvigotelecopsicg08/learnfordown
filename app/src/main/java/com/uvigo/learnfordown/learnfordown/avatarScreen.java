package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class avatarScreen extends AppCompatActivity {
    int edad;
    String nombre;
    ImageButton BackArrow,Home;
    TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        BackArrow = (ImageButton) findViewById(R.id.button4);
        Home = (ImageButton) findViewById(R.id.button6);
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
    lanzaIntent(R.drawable.avatarcompas);

    }
    public void pulsar2(View v){
        lanzaIntent(R.drawable.avatarglobo);

    }
    public void pulsar3(View v){
        lanzaIntent(R.drawable.avatarlibro);

    }

    public void pulsar4(View v){
        lanzaIntent(R.drawable.avatarregla);

    }
    public void pulsar5(View v){
        lanzaIntent(R.drawable.avatarrotu);

    }
    public void pulsar6(View v){
        lanzaIntent(R.drawable.avatartijeras);

    }



    public void lanzaIntent(int avatar){
        Intent intent = new Intent(this, login_screen_like.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("edad",edad);
        intent.putExtra("avatar", avatar);
        startActivity(intent);
    }
}
