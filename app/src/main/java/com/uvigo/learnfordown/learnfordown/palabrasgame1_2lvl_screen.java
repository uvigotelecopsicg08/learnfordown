package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
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
    Button button1,button2,button3;
    TextView respuesta1,respuesta2,respuesta3;
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
//Respuestas
        respuesta1 = (TextView) findViewById(R.id.textView9);
        respuesta2 = (TextView) findViewById(R.id.textView10);
        respuesta3 = (TextView) findViewById(R.id.textView11);
       /* respuesta1.setVisibility(View.VISIBLE);
        respuesta2.setVisibility(View.VISIBLE);
        respuesta3.setVisibility(View.VISIBLE);*/
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
        if (findViewById(v.getId()) instanceof Button) {
            Button b2 = (Button) findViewById(v.getId());
            b2.setBackgroundColor(getResources().getColor(R.color.Gris));
        }
   //     Button boton=(Button)v;



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


                        switch( posicionImageButton(b1)){
                            case 1:
                                respuesta1.setText(b1.getText());
                                respuesta1.setVisibility(View.VISIBLE);

                                break;
                            case 2:
                                respuesta2.setText(b1.getText());
                                respuesta2.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                respuesta3.setText(b1.getText());
                                respuesta3.setVisibility(View.VISIBLE);
                                break;

                        }

                    } else {

                        Button b1 = (Button) findViewById(ultimoPulsado);
                        switch( posicionImageButton(b1)){
                            case 1:
                                respuesta1.setVisibility(View.VISIBLE);
                                respuesta1.setText(b1.getText());
                                break;
                            case 2:
                                respuesta2.setVisibility(View.VISIBLE);
                                respuesta2.setText(b1.getText());

                                break;
                            case 3:
                                respuesta3.setVisibility(View.VISIBLE);
                                respuesta3.setText(b1.getText());

                                break;

                        }
                        b1.setEnabled(false);
                        ImageButton b2 = (ImageButton) findViewById(v.getId());
                        b2.setEnabled(false);
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

    public void animacionButton(Button b){
        TranslateAnimation animation=null;
        int posicionImageButton = posicionImageButton(b);
        int posicion =posicionButton(b);
        int animacionposicion = posicion+posicionImageButton;
        /*switch (animacionposicion) {
            case 11:
                //Posicion 1 del boton y posicion una de la imagen.... asi todas
                animation = new TranslateAnimation(0.0f, -50.0f,
                        0.0f, 0.0f);
                break;
            case 12:
                animation = new TranslateAnimation(0.0f, 0.0f,
                        -50.0f, 0.0f);

                break;
            case 13:
                animation = new TranslateAnimation(0.0f, 0.0f,
                        -50.0f, 0.0f);
                break;
            case 21:
                animation = new TranslateAnimation(0.0f, 0.0f,
                        -50.0f, 0.0f);
                break;
            case 22:
                animation = new TranslateAnimation(0.0f, 0.0f,
                        -50.0f, 0.0f);
                break;
            case 23:
                animation = new TranslateAnimation(0.0f, 0.0f,
                        -50.0f, 0.0f);
                break;
            case 31:
                animation = new TranslateAnimation(0.0f, 0.0f,
                        -50.0f, 0.0f);
                break;
            case 32:
                animation = new TranslateAnimation(0.0f, 0.0f,
                        -50.0f, 0.0f);
                break;
            case 33:
                animation = new TranslateAnimation(0.0f, 0.0f,
                        -50.0f, 0.0f);
                break;

        }
        */
        animation = new TranslateAnimation(0.0f, -500.0f,
                0.0f, 0.0f);
        animation.setDuration(500);
        b.bringToFront();
        ((View)b.getParent()).requestLayout();
        ((View)b.getParent()).invalidate();
   //     animation.setFillAfter(true);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        animation.setFillBefore(false);
        b.startAnimation(animation);
    }
    public void resetBotones(){
        respuesta1.setVisibility(View.INVISIBLE);
        respuesta2.setVisibility(View.INVISIBLE);
        respuesta3.setVisibility(View.INVISIBLE);
        button1.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.Blanco));
        button2.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.Blanco));
        button3.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.Blanco));
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
    public int posicionButton(Button b){
        int posicion=0;


        switch (b.getId()){
            case R.id.button3:
            /*    LinearLayout layout3 =(LinearLayout)findViewById(R.id.LinearLayoutBoton3);
                layout3.bringToFront();*/
                posicion= 10;
                break;
            case R.id.button1:
             /*   LinearLayout layout1 =(LinearLayout)findViewById(R.id.LinearLayoutBoton1);
                layout1.bringToFront();*/
                posicion= 20;
                break;
            case R.id.button2:
           /*     LinearLayout layout2 =(LinearLayout)findViewById(R.id.LinearLayoutBoton2);
                layout2.bringToFront();*/
                posicion= 30;
            break;
        }
return posicion;
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
            resetBotones();
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