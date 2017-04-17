package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
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

import com.LearnForDown.RecogeMonedas.UnityPlayerActivity;

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
    Intent minijuego;
    AlertDialog dialog;




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


        gn = new GestionNiveles(context,this);
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

            if (RellenoFrase.contains("*")) {
                Frase.setText(RellenoFrase.replace("*", Correcta));
                RespuestaCorrecta();



            }


            }else {
            es.fallo();
            Texto.setText("");

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
        gn.enviaResultado(fp.get(i).getPalabra());
        MediaPlayer aciertoMedia = es.getAciertoMedia();
        aciertoMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

        es.pulsar(true);
                if (es.ratingbar1.getRating()==6){
                    MensajeMinijuego();
                }

        // Otra pantalla
        if (!gn.isnivelCompletado()) { // Aún no terminó el nivel
            i++;
            cambiarFoto();
            Texto.setText("");
        } else {
            Intent intent = new Intent(writegame_level4_screen.this, endScreen.class);
            startActivity(intent);

            gn.avanzaNivel();
            if (gn.getDificultad() != 1 || !(gn.getTipo().equals(TipoNivel))) {

                //Código para abrir otra pantalla
               // Intent intent = new Intent(writegame_level4_screen.this, endScreen.class);
                //startActivity(intent);
           } else {
                fp = gn.getFotosAleatorias();
                i = 0;
               cambiarFoto();
               Texto.setText("");
           }
        }

            }
        });
    }


    public void reset(View v){
        i=0;
        es.resetPanelEstrellas();
        fp=gn.getFotosAleatorias();
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
        String Nombre="";
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
        dialog.dismiss();
    }
}