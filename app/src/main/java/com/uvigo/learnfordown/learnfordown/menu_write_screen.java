package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class menu_write_screen extends AppCompatActivity {

    TextView titulo;
    ImageButton BackArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_write_screen);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        BackArrow = (ImageButton) findViewById(R.id.button3);
        titulo.setTypeface(face);



    }

    public void SpinnerLevel1Up(View v){
        Intent intent1 = new Intent(menu_write_screen.this, writegame_level1_screen.class);
        startActivity(intent1);
    }

    public void SpinnerLevel2Up(View v){
        Intent intent1 = new Intent(menu_write_screen.this, writegame_level2_screen.class);
        startActivity(intent1);
    }
    public void SpinnerLevel3Up(View v){
        Intent intent1 = new Intent(menu_write_screen.this, writegame_level3_screen.class);
        startActivity(intent1);
    }
    public void SpinnerLevel4Up(View v){
        Intent intent1 = new Intent(menu_write_screen.this, writegame_level4_screen.class);
        startActivity(intent1);
    }


    public void BackArrow (View v){
        Intent intent1 = new Intent(menu_write_screen.this, home_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(menu_write_screen.this, home_screen.class);
        startActivity(intent1);
    }
}
