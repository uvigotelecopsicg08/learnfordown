package com.uvigo.learnfordown.learnfordown;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class poppuzzle extends AppCompatActivity {
    TextView texto1,texto2;
    ImageView imagenAyuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nombre = getIntent().getStringExtra("primera");
        int id = getIntent().getIntExtra("imagen",0);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");

        setContentView(R.layout.activity_poppuzzle);
        texto1 = (TextView)findViewById(R.id.textView5);
        texto1 = (TextView)findViewById(R.id.textView5);
        LinearLayout fondo = (LinearLayout)findViewById(R.id.linearLayout);
        RelativeLayout fondo2 =(RelativeLayout)findViewById(R.id.activity_poppuzzle);
        texto1.setTypeface(face);
        texto2 = (TextView)findViewById(R.id.textView6);
        texto2.setTypeface(face);
        ImageView imagen = (ImageView) findViewById(R.id.imageView2);

        if(id==R.drawable.estrellita || id == R.drawable.cambionivel){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("AQUI***************************************************************************");
                    finish();
                    //Do something after 100ms
                }
            }, 2000);
        }
        if (!nombre.equals("si")) {
            texto1.setVisibility(View.GONE);
            texto2.setVisibility(View.GONE);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("AQUI");
                    finish();
                    //Do something after 100ms
                }
            }, 10000);


        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bitmap mask = BitmapFactory.decodeResource(getResources(),id);
        imagen.setImageBitmap(mask);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout(mask.getWidth(),mask.getHeight());
    }
}
