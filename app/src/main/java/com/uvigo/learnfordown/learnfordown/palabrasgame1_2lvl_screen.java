package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class palabrasgame1_2lvl_screen extends AppCompatActivity {
    TextView titulo;
    Integer ultimoPulsado;
  //  final HashMap<Integer, Float> thresholds = new HashMap<>();
   // RatingBar ratingbar1 = null;
    String figure = "plato";
    HashMap<Integer,String>  map;
    GestionNiveles  gn;
    String tipoNivel="palabrassilabasdirectas";
    ArrayList<FotoPalabra> fp;
    int i=0;

  //  int contador;
    ImageButton imageButton1,imageButton2,imageButton3;
    Button button1,button2,button3, boton_mover;
    int aciertos=0;
    int nivel;
    boolean cambiado=false;
    boolean nuevaActivity= false;
    Estrellas es;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabrasgame1_2lvl_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        ultimoPulsado = null;
   //     ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);

        button1 = (Button)  findViewById(R.id.button1);
        button2 = (Button)  findViewById(R.id.button2);
        button3 = (Button)  findViewById(R.id.button3);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tipoNivel = extras.getString("tipoSilaba");
            nivel = extras.getInt("nivel");
            System.out.println(tipoNivel);
        }

        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);


       es= new Estrellas(this,gn, gn.setNivel(tipoNivel,nivel));
        fp=gn.getFotos();
        cambiarFoto();


    }
    public void BackArrow (View v) {

        Intent intent1 = new Intent();
        switch (gn.getTipo()) {
            case "palabrassilabasdirectas":
                intent1 = new Intent(palabrasgame1_2lvl_screen.this, palabrasdi_screen.class);
                break;
            case "palabrassilabasinversas":
                intent1 = new Intent(palabrasgame1_2lvl_screen.this, palabrasin_screen.class);
                break;
            case "palabrassilabastrabadas":
                intent1 = new Intent(palabrasgame1_2lvl_screen.this, palabrastra_screen.class);
                break;
        }

        startActivity(intent1);

    }
    public void goHome (View v){
        Intent intent1 = new Intent(palabrasgame1_2lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void pulsar (View v){
        Log.i("pulsar()", v.getId() + " ultimoPulsado:" +  ultimoPulsado);
//        Button b=(Button)v;



     /*   Animation anim = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                0f, 1.5f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(500);
        v.startAnimation(anim);*/

        if(ultimoPulsado != null) {

            if (v.getId()!=(ultimoPulsado)){
                if (map.get(v.getId()).equals(map.get(ultimoPulsado))) {
                   // anim.finalize();
                    if (findViewById(v.getId()) instanceof Button) {
                        Button b1 = (Button) findViewById(v.getId());
                        b1.setEnabled(false);
                        ImageButton b2 = (ImageButton) findViewById(ultimoPulsado);
                        b2.setEnabled(false);
                       animacionButton(b1,b2);


                    } else {

                        Button b1 = (Button) findViewById(ultimoPulsado);
                        b1.setEnabled(false);
                        ImageButton b2 = (ImageButton) findViewById(v.getId());
                        b2.setEnabled(false);
                       animacionButton1(b1,b2);
                    }
                    ultimoPulsado=0;
                    es.acierto();
                    aciertos++;
                    MediaPlayer aciertoMedia = es.getAciertoMedia();
                    aciertoMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            es.pulsar(true);



                            if (aciertos == 3) {

                                aciertos = 0;
                                if (!gn.isnivelCompletado()) {
                                    i += 3;
                                    cambiarFoto();
                                    cambiado = true;
                                } else {
                                    System.out.print("el nivel esta finalizado");
                                    avanzaNivel();

                                }
                            }

                        }

                    });
                } else {
                    if(ultimoPulsado==0)
                        es.fallo();

                }
            }
        }
        if(!cambiado) {
            ultimoPulsado = v.getId();
            System.out.println("has pulsado: "+map.get(ultimoPulsado));
        }

        else{
            ultimoPulsado=0;
            cambiado=false;
        }

    }

    public void animacionButton(Button b, ImageButton i){
       // TranslateAnimation animation=null;
        Animation animation;
        i = (ImageButton) findViewById(ultimoPulsado);
        int posicion = posicionImageButton(b);
         b = (Button) findViewById(b.getId());

       // if(posicion==1) {
        if ((i.getId())==(R.id.imageButton1)) {
           // if (map.get(b.getId()).equals(map.get(R.id.button3))) {
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                 ViewCompat.setElevation(button1, 100.0f);
                button3.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button3.startAnimation(animation);
            }

           /* if (map.get(b.getId()).equals(map.get(R.id.button1))) {
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                // ViewCompat.setElevation(button2, 1000.0f);
                button3.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button3.startAnimation(animation);
            }

            else /*(map.get(b.getId()).equals(map.get(R.id.button2))){
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                // ViewCompat.setElevation(button2, 1000.0f);
                button3.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button3.startAnimation(animation);
            }

        }*/
   /*   //  else{
            //if(posicion==2){
                animation = new TranslateAnimation(0.0f, 0.0f, -50.0f, 0.0f);
               // ViewCompat.setElevation(button1, 1000.0f);
                button3.bringToFront();
               // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button3.startAnimation(animation);
          //  }
          //  else{
               animation = new TranslateAnimation(0.0f, 0.0f, -100.0f, 0.0f);
               // ViewCompat.setElevation(button3, 1000.0f);
                button2.bringToFront();
               // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button2.startAnimation(animation);
           // }
       // }
        //animation.setDuration(500);
       //animation.setFillAfter(true);
      // b.startAnimation(animation);*/
    }
    public void animacionButton1(Button b, ImageButton i){
        // TranslateAnimation animation=null;
        Animation animation;
        ImageButton b2 = (ImageButton) findViewById(b.getId());
        int posicion = posicionImageButton(b);
        Button b1 = (Button) findViewById(ultimoPulsado);
        if ((i.getId())==(R.id.imageButton1)) {
            if (map.get(b.getId()).equals(map.get(R.id.button3))) {
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                // ViewCompat.setElevation(button2, 1000.0f);
                button3.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button3.startAnimation(animation);
            }
        }
        if ((i.getId())==(R.id.imageButton1)) {
            if (map.get(b.getId()).equals(map.get(R.id.button1))) {
                animation = new TranslateAnimation(0.0f, 0.0f, -50.0f, 00.0f);
                // ViewCompat.setElevation(button2, 1000.0f);
                button1.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button1.startAnimation(animation);
            }
        }
        if ((i.getId())==(R.id.imageButton1)){
            if(map.get(b.getId()).equals(map.get(R.id.button2))) {
                animation = new TranslateAnimation(0.0f, 0.0f, -100.0f, 0.0f);
                // ViewCompat.setElevation(button2, 1000.0f);
                button2.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button2.startAnimation(animation);
            }

        }

        /*if(posicion==1) {
            if(map.get(b.getId()).equals(map.get(R.id.button3))) {
                animation = new TranslateAnimation(0.0f, 0.0f, -50.0f, 0.0f);
                // ViewCompat.setElevation(button2, 1000.0f);
                button3.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button3.startAnimation(animation);
            }
            if(map.get(b.getId()).equals(map.get(R.id.button3))) {
                animation = new TranslateAnimation(0.0f, 0.0f, -50.0f, -50.0f);
                // ViewCompat.setElevation(button2, 1000.0f);
                button3.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button3.startAnimation(animation);
            }
            if(map.get(b.getId()).equals(map.get(R.id.button1))) {
                animation = new TranslateAnimation(0.0f, 0.0f, -80.0f, -80.0f);
                // ViewCompat.setElevation(button2, 1000.0f);
                button3.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button3.startAnimation(animation);
            }

        }
        else{
            if(posicion==2){
                animation = new TranslateAnimation(0.0f, 0.0f, -50.0f, 0.0f);
                // ViewCompat.setElevation(button1, 1000.0f);
                button3.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button3.startAnimation(animation);
            }
            else{
                animation = new TranslateAnimation(0.0f, 0.0f, -100.0f, 0.0f);
                // ViewCompat.setElevation(button3, 1000.0f);
                button2.bringToFront();
                // animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mover);
                button2.startAnimation(animation);
            }
        }
        //animation.setDuration(500);
        //animation.setFillAfter(true);
        // b.startAnimation(animation);*/
    }
    public int posicionImageButton(Button b){
      if(map.get(b.getId()).equals(map.get(R.id.imageButton1))){
              return 1;
        }
        else{
            if(map.get(b.getId()).equals(map.get(R.id.imageButton2))){
                return 2;
            }
          else{
                return 3;
            }
        }

    }



    private void cambiarFoto() {
        try {
            map = new HashMap<>();
            ArrayList<Integer> arrayImageResource = new ArrayList();
            arrayImageResource.add(fp.get(0 + i).getFoto());
            arrayImageResource.add(fp.get(1 + i).getFoto());
            arrayImageResource.add(fp.get(2 + i).getFoto());
            Collections.shuffle(arrayImageResource);
            imageButton1.clearFocus();
            imageButton1.setEnabled(true);
            imageButton2.setEnabled(true);
            imageButton3.setEnabled(true);
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);

            imageButton1.clearFocus();
            imageButton1.setImageResource(arrayImageResource.get(0));
            imageButton2.clearFocus();
            imageButton2.setImageResource(arrayImageResource.get(1));
            imageButton3.clearFocus();
            imageButton3.setImageResource(arrayImageResource.get(2));

            if (fp.get(0 + i).getFoto() == arrayImageResource.get(0)) {
                map.put(R.id.imageButton1, fp.get(0 + i).getPalabra());
            } else {
                if (fp.get(0 + i).getFoto() == arrayImageResource.get(1)) {
                    map.put(R.id.imageButton2, fp.get(0 + i).getPalabra());
                } else {
                    if (fp.get(0 + i).getFoto() == arrayImageResource.get(2)) {
                        map.put(R.id.imageButton3, fp.get(0 + i).getPalabra());
                    }
                }
            }

            if (fp.get(1 + i).getFoto() == arrayImageResource.get(0)) {
                map.put(R.id.imageButton1, fp.get(1 + i).getPalabra());
            } else {
                if (fp.get(1 + i).getFoto() == arrayImageResource.get(1)) {
                    map.put(R.id.imageButton2, fp.get(1 + i).getPalabra());
                } else {
                    if (fp.get(1 + i).getFoto() == arrayImageResource.get(2)) {
                        map.put(R.id.imageButton3, fp.get(1 + i).getPalabra());
                    }
                }
            }
            if (fp.get(2 + i).getFoto() == arrayImageResource.get(0)) {
                map.put(R.id.imageButton1, fp.get(2 + i).getPalabra());
            } else {
                if (fp.get(2 + i).getFoto() == arrayImageResource.get(1)) {
                    map.put(R.id.imageButton2, fp.get(2 + i).getPalabra());
                } else {
                    if (fp.get(2 + i).getFoto() == arrayImageResource.get(2)) {
                        map.put(R.id.imageButton3, fp.get(2 + i).getPalabra());
                    }
                }
            }


            ArrayList<String> arrayText = new ArrayList();
            arrayText.add(fp.get(0 + i).getPalabra());
            arrayText.add(fp.get(1 + i).getPalabra());
            arrayText.add(fp.get(2 + i).getPalabra());
            Collections.shuffle(arrayText);
            button1.setText(arrayText.get(0).toUpperCase());
            button2.setText(arrayText.get(1).toUpperCase());
            button3.setText(arrayText.get(2).toUpperCase());


            if (fp.get(0 + i).getPalabra() == arrayText.get(0)) {
                map.put(R.id.button1, fp.get(0 + i).getPalabra());
            } else {
                if (fp.get(0 + i).getPalabra() == arrayText.get(1)) {
                    map.put(R.id.button2, fp.get(0 + i).getPalabra());
                } else {
                    if (fp.get(0 + i).getPalabra() == arrayText.get(2)) {
                        map.put(R.id.button3, fp.get(0 + i).getPalabra());
                    }
                }
            }

            if (fp.get(1 + i).getPalabra() == arrayText.get(0)) {
                map.put(R.id.button1, fp.get(1 + i).getPalabra());
            } else {
                if (fp.get(1 + i).getPalabra() == arrayText.get(1)) {
                    map.put(R.id.button2, fp.get(1 + i).getPalabra());
                } else {
                    if (fp.get(1 + i).getPalabra() == arrayText.get(2)) {
                        map.put(R.id.button3, fp.get(1 + i).getPalabra());
                    }
                }
            }
            if (fp.get(2 + i).getPalabra() == arrayText.get(0)) {
                map.put(R.id.button1, fp.get(2 + i).getPalabra());
            } else {
                if (fp.get(2 + i).getPalabra() == arrayText.get(1)) {
                    map.put(R.id.button2, fp.get(2 + i).getPalabra());
                } else {
                    if (fp.get(2 + i).getPalabra() == arrayText.get(2)) {
                        map.put(R.id.button3, fp.get(2 + i).getPalabra());
                    }
                }
            }
        } catch (java.lang.IndexOutOfBoundsException e){
        e.printStackTrace();
        avanzaNivel();
        }
    }
    public void avanzaNivel(){
        gn.avanzaNivel();
        cambiado=true;
        if (!(gn.getTipo().equals(tipoNivel))) {
            System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
            //Código para abrir otra pantalla
            nuevaActivity=true;
            String strName=null;
            if(gn.getTipo().equals("silabastrabadas")||gn.getTipo().equals("silabasinversas")) {
                Intent  intent = new Intent(palabrasgame1_2lvl_screen.this, silabasgame1lvl_screen.class);
                if(gn.getTipo().contains("inversas")) {
                    strName = "silabasinversas";
                }
                else{
                    strName = "silabastrabadas";
                }
                intent.putExtra("tipoSilaba", strName);
                startActivity(intent);
            }
            else{
                Intent  intent = new Intent(palabrasgame1_2lvl_screen.this, frasegame1lvl_screen.class);
                strName = "frasessilabasdirectas";
                intent.putExtra("tipoSilaba", strName);
                intent.putExtra("nivel",1);
                startActivity(intent);
            }

        } else {
            if(!nuevaActivity) {
                fp = gn.getFotos();
                i = 0;
                cambiarFoto();
                System.out.println("Se debe avanzar el nivel");
            }


        }
    }
    public void reset(View v){
        i=0;
        es.resetPanelEstrellas();
        fp=gn.getFotos();
        cambiarFoto();
    }


}
