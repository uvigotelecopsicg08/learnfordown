package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;



public class writegame_level4_screen extends AppCompatActivity {

    private EditText Texto;
    private TextView Titulo, Frase;
    private ImageView Foto;
    private String TipoNivel, Correcta;
    private String RellenoFrase;
    Estrellas es;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
    private GestionNiveles gn;
    private ArrayList<FotoPalabra> fp;
    private int i = 0;
    SoundPool soundPool;
    int idDisparo,idacierto;
    public Estrellas getEs() {
        return es;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writegame_level4_screen);

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        Foto = (ImageView) findViewById(R.id.imageView2);
        Frase = (TextView) findViewById(R.id.textView4);
        Texto = (EditText) findViewById(R.id.teclado);
        Texto.setGravity(Gravity.CENTER);

        TipoNivel = "escribirtecladopalabra"; // Esto tiene que cambiarse cada n iteraciones -> IMPORTANTE
        Context context = this.getApplicationContext();
        soundPool = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0); // El primero corresponde al máximo de reproducciones simultáneas. El segundo es el tipo de stream de audio (normalmente STREAM_MUSIC). El tercero es la calidad de reproducción, aunque actualmente no se implementa
        Estrellas es = new Estrellas(context);
        idDisparo = soundPool.load(context, R.raw.disparo, 0);
        idacierto = soundPool.load(context, R.raw.disparo, 0);

        gn = new GestionNiveles(context);
        gn.setNivel(TipoNivel, 1);
        fp = gn.getFotosAleatorias();
        es = new Estrellas(this, gn, gn.setNivel(TipoNivel, 4));

        RellenoFrase = fp.get(i).getFrase().toUpperCase();
        Correcta = fp.get(i).getPalabra().toUpperCase();
        Foto.setImageResource(fp.get(i).getFoto());
        Rellenar();


    }

    public void Rellenar() {

        String Relleno = "_";
        int Longitud = Correcta.length();
        while (Relleno.length() != Longitud) {
            Relleno = Relleno.concat(" _");
            Longitud++; // Debido a que concatenamos un espacio + _
        }

        if (RellenoFrase.contains("*")) Frase.setText(RellenoFrase.replace("*", Relleno));
    }

    public void BackArrow(View v) {
        Intent intent1 = new Intent(writegame_level4_screen.this, menu_write_screen.class);
        startActivity(intent1);
    }

    public void goHome(View v) {
        Intent intent1 = new Intent(writegame_level4_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void CompruebaEntrada(View v) {

        if (Texto.getText().toString().equals(Correcta)) {
            soundPool.play(idacierto, 1, 1, 1, 0, 1); //el volumen para el canal izquierdo y derecho (0.0 a 1.0); La prioridad; El número de repeticiones (-1= siempre, 0=solo una vez, 1=repetir una vez, …  )  y el ratio de reproducción, con el que podremos modificar la velocidad o pitch (1.0 reproducción normal, rango: 0.5 a 2.0)

            if (RellenoFrase.contains("*")) {
                Frase.setText(RellenoFrase.replace("*", Correcta));

                // Esperamos
                final android.os.Handler handler = new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RespuestaCorrecta();
                       // finish();
                        //Do something after 100ms
                    }
                }, 1000 );


            } else {
                gn.fallo();
                soundPool.play(idDisparo, 1, 1, 1, 0, 1); //el volumen para el canal izquierdo y derecho (0.0 a 1.0); La prioridad; El número de repeticiones (-1= siempre, 0=solo una vez, 1=repetir una vez, …  )  y el ratio de reproducción, con el que podremos modificar la velocidad o pitch (1.0 reproducción normal, rango: 0.5 a 2.0)

            }

        }
    }

    public void cambiarFoto() {

        Correcta = fp.get(i).getPalabra().toUpperCase();
        Foto.setImageResource(fp.get(i).getFoto());
        RellenoFrase = fp.get(i).getFrase().toUpperCase();
        Rellenar();

    }


    public void RespuestaCorrecta(){

        // Estrellitas
        es.acierto();
        es.pulsar(true);

        // Otra pantalla
        if (!gn.isnivelCompletado()) { // Aún no terminó el nivel
            i++;
            cambiarFoto();
            Texto.setText("");
        } else {


            gn.avanzaNivel();
            if (gn.getDificultad() != 1 || !(gn.getTipo().equals(TipoNivel))) {

                //Código para abrir otra pantalla
                //Intent intent = new Intent(writegame_level2_screen.this, writegame_level3_screen.class);
                //startActivity(intent);
            } else {
                fp = gn.getFotosAleatorias();
                i = 0;
                cambiarFoto();
                Texto.setText("");
            }
        }
    }
}