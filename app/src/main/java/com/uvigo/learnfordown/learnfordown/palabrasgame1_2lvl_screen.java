package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
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

import com.LearnForDown.RecogeMonedas.UnityPlayerActivity;

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
    Intent minijuego;
    AlertDialog dialog;
    String Nombre="";

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
        gn = new GestionNiveles(context,this);


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

    public void pulsar (View v) {
        Log.i("pulsar()", v.getId() + " ultimoPulsado:" + ultimoPulsado);


        if (ultimoPulsado != null ) {
            if (findViewById(v.getId()) instanceof Button && findViewById(ultimoPulsado) instanceof Button) {
                Button b2 = (Button) findViewById(ultimoPulsado);
                b2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Blanco));
                Button b3 = (Button) findViewById(v.getId());
                b3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Gris));
            } else {
                if (findViewById(v.getId()) instanceof ImageButton && findViewById(ultimoPulsado) instanceof ImageButton) {
                    ImageButton b2 = (ImageButton) findViewById(ultimoPulsado);
                    b2.clearColorFilter();
                    ImageButton b3 = (ImageButton) findViewById(v.getId());
                    b3.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.Gris), PorterDuff.Mode.SRC_ATOP);
                } else {

                    if (v.getId() != (ultimoPulsado)) {

                        if (map.get(v.getId()).equals(map.get(ultimoPulsado))) {
                            if (findViewById(v.getId()) instanceof Button) {
                                Button b1 = (Button) findViewById(v.getId());
                                b1.setEnabled(false);
                                ImageButton b2 = (ImageButton) findViewById(ultimoPulsado);
                                b2.clearColorFilter();
                                b2.setEnabled(false);
                                b1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.VerdeClarito));

                                switch (posicionImageButton(b1)) {
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
                                switch (posicionImageButton(b1)) {
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
                                b1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.VerdeClarito));
                                b1.setEnabled(false);
                                ImageButton b2 = (ImageButton) findViewById(v.getId());
                                b2.clearColorFilter();
                                b2.setEnabled(false);
                            }
                            cambiado = true;
                            es.acierto();
                            aciertos++;
                            MediaPlayer aciertoMedia = es.getAciertoMedia();
                            aciertoMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    es.pulsar(true);


                                    if (aciertos == 3) {
                                        if (es.ratingbar1.getRating()==6){
                                            MensajeMinijuego();
                                        }

                                        aciertos = 0;
                                        if (!gn.isnivelCompletado()) {
                                            i += 3;
                                            cambiarFoto();
                                        } else {
                                            System.out.print("el nivel esta finalizado");
                                            avanzaNivel();

                                        }
                                    }

                                }

                            });
                        } else {
                            if (findViewById(v.getId()) instanceof Button) {
                                Button b1 = (Button) findViewById(v.getId());
                                b1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Blanco));

                            } else {

                                Button b1 = (Button) findViewById(ultimoPulsado);
                                b1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Blanco));
                            }
                            if (findViewById(v.getId()) instanceof ImageButton) {
                                ImageButton b1 = (ImageButton) findViewById(v.getId());
                                b1.clearColorFilter();

                            } else {

                                ImageButton b1 = (ImageButton) findViewById(ultimoPulsado);
                                b1.clearColorFilter();
                            }
                            cambiado = true;
                            es.fallo();

                        }
                    }
                }
            }
        }else{
            cambiado=false;
            if (findViewById(v.getId()) instanceof Button) {
                Button b2 = (Button) findViewById(v.getId());
                b2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Gris));
            } else {
                if (findViewById(v.getId()) instanceof ImageButton) {
                    ImageButton b2 = (ImageButton) findViewById(v.getId());
                    b2.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.Gris), PorterDuff.Mode.SRC_ATOP);
                }

            }

        }
        if (!cambiado) {
            ultimoPulsado = v.getId();
            System.out.println("has pulsado: " + map.get(ultimoPulsado));
        } else {
            ultimoPulsado = null;
            cambiado = false;
        }

    }



    public void resetBotones(){
        respuesta1.setVisibility(View.INVISIBLE);
        respuesta2.setVisibility(View.INVISIBLE);
        respuesta3.setVisibility(View.INVISIBLE);
        button1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Blanco));
        button2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Blanco));
        button3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Blanco));
    }
    public int posicionImageButton(Button b) {
        if (map.get(b.getId()).equals(map.get(R.id.imageButton1))) {
            return 1;
        } else {
            if (map.get(b.getId()).equals(map.get(R.id.imageButton2))) {
                return 2;
            } else {
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
        resetBotones();
        gn.avanzaNivel();
        cambiado=true;
        if (!(gn.getTipo().equals(tipoNivel))) {
            System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
            //Código para abrir otra pantalla

            nuevaActivity=true;

            if(gn.getTipo().equals("silabastrabadas")||gn.getTipo().equals("silabasinversas")) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String strName=null;

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
                }, 2000);


            }
            else{
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String strName=null;

                        Intent  intent = new Intent(palabrasgame1_2lvl_screen.this, frasegame1lvl_screen.class);
                        strName = "frasessilabasdirectas";
                        intent.putExtra("tipoSilaba", strName);
                        intent.putExtra("nivel",1);
                        startActivity(intent);
                    }
                }, 2000);

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

    public void MensajeMinijuego(){
        String Minijuego;
        Minijuego=MinijuegoRandom();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            builder.setView(R.layout.dialogominijuegos);
        }
        else {
            builder.setMessage("¿QUIERES JUGAR AHORA O MAS TARDE?")
                    .setTitle("¡TIENES UN MINIJUEGO NUEVO!  " + Minijuego);

            builder.setPositiveButton("¡LO QUIERO AHORA!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(minijuego);
                }
            });
            builder.setNegativeButton("¡LO QUIERO MAS TARDE!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Usuarios cancelled the dialog
                }
            });
        }
        dialog = builder.create();
        dialog.show();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");

            TextView Titulo =(TextView)dialog.findViewById(R.id.textView7);
            Titulo.setText("¡TIENES UN MINIJUEGO NUEVO!  " + Minijuego);
            Titulo.setTypeface(face);
            TextView mensaje =(TextView)dialog.findViewById(R.id.textView8);
            mensaje.setTypeface(face);
            Button positivo =(Button)dialog.findViewById(R.id.button11);
            Button negativo =(Button)dialog.findViewById(R.id.button12);

        }



    }
    public String MinijuegoRandom(){
        int rand =(int) (Math.random() * 2.0);
        switch(rand) {
            case 0:
                minijuego = new Intent(getApplicationContext(),Puzzle4piezas.class);
                Nombre= "PUZZLE";
                break;
            case 1:
                minijuego = new Intent(getApplicationContext(),ParejasFacil.class);
                Nombre= "PAREJAS";
                break;

            case 2:
                minijuego = new Intent(getApplicationContext(),UnityPlayerActivity.class);
                Nombre= "PLATAFORMAS";
                break;

        }
        return Nombre;
    }
    public void DialogPositive(View v){
        startActivity(minijuego);
        dialog.dismiss();
    }
    public void DialogNegative(View v){
//Codigo de meter en la base de datos
        DataBaseManager db =  new DataBaseManager(getApplicationContext());

        db.updateMinijuego(gn.getId_user(),Nombre,"suma");
        dialog.dismiss();
    }
}