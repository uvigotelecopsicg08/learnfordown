package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.LearnForDown.RecogeMonedas.UnityPlayerActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class frasegame1lvl_screen extends AppCompatActivity {
    TextView titulo;
   // RatingBar ratingbar1 = null;
    String figure = "plato";
    Button button1,button2,button3;
    Button Actual;

   // int contador;
    //final HashMap<Integer, Float> thresholds = new HashMap<>();
    ImageView palabra;
    GestionNiveles  gn;
    String tipoNivel="frasessilabasdirectas";
    ArrayList<FotoPalabra> fp;
    int i=0;
   // int aciertos=0;
    TextView textView;
    int nivel;
    String tmpDownSlash;
    boolean activiftiFinalizado =false;
    Estrellas es;
    Intent minijuego;
    AlertDialog dialog;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frasegame1lvl_screen);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        palabra = (ImageView) findViewById(R.id.foto);
        textView = (TextView)findViewById(R.id.button4);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tipoNivel = extras.getString("tipoSilaba");
            nivel = extras.getInt("nivel");
        }

        System.out.println(tipoNivel);
        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context,this);
       es = new Estrellas(this,gn, gn.setNivel(tipoNivel, nivel));
        fp = gn.getFotos();
        figure=fp.get(i).getPalabra().toUpperCase();
        palabra.setImageResource(fp.get(i).getFoto());
        ArrayList<String> arrayAux = new ArrayList<>();
        arrayAux.add(fp.get(i).getPalabra().toUpperCase());
        arrayAux.add(fp.get(i+1).getPalabra().toUpperCase());
        arrayAux.add(fp.get(i+2).getPalabra().toUpperCase());
        Collections.shuffle(arrayAux);
        button1.setText(arrayAux.get(0));
        button2.setText(arrayAux.get(1));
        button3.setText(arrayAux.get(2));



        tmpDownSlash = "";
        for (int i = 0; i < figure.length(); i++) {
            tmpDownSlash += " _";
        }
        String stringAux = fp.get(i).getFrase().toUpperCase().replace("*",tmpDownSlash);
        textView.setText(stringAux);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        /*
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);
        contador = 0;
        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas
        */
    }

    public void BackArrow (View v) {

        Intent intent1 = new Intent();
        switch (gn.getTipo()) {
            case "frasessilabasdirectas":
                intent1 = new Intent(frasegame1lvl_screen.this, frasedi_screen.class);
                break;
            case "frasessilabasinversas":
                intent1 = new Intent(frasegame1lvl_screen.this, frasein_screen.class);
                break;
            case "frasessilabastrabadas":
                intent1 = new Intent(frasegame1lvl_screen.this, frasetra_screen.class);
                break;
        }


        startActivity(intent1);

    }


    public void goHome (View v){
        Intent intent1 = new Intent(frasegame1lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void pulsar(View v) {

       Button bAxu = (Button) findViewById(v.getId());
        Actual =bAxu;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                -50.0f, 0.0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if ((fp.get(i).getPalabra().toUpperCase()).equals(Actual.getText().toString())) {
                    Actual.setBackgroundColor(Color.GREEN);
                    System.out.println(figure);
                    String stringAux = fp.get(i).getFrase().toUpperCase().replace("*",figure);
                    textView.setText(stringAux);

                }


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if ((fp.get(i).getPalabra().toUpperCase()).equals(Actual.getText().toString())) {
                    Actual.setBackgroundColor(Color.WHITE);
                    es.acierto();
                    es.pulsar(true);
                    if (es.ratingbar1.getRating()==6){
                        MensajeMinijuego();
                    }

                    if (!gn.isnivelCompletado()) {
                        i++;
                        cambiarFoto();
                    } else {
                        System.out.print("el nivel esta finalizado");
                        avanzaNivel();
                    }

                } else {
                    es.fallo();

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bAxu.startAnimation(animation);
    }

    private void cambiarFoto() {
    try {
    figure = fp.get(i).getPalabra().toUpperCase();
    palabra.setImageResource(fp.get(i).getFoto());
    ArrayList<String> arrayAux = new ArrayList<>();
    arrayAux.add(fp.get(i).getPalabra().toUpperCase());
    arrayAux.add(fp.get(i + 1).getPalabra().toUpperCase());
    arrayAux.add(fp.get(i + 2).getPalabra().toUpperCase());
    Collections.shuffle(arrayAux);
    button1.setText(arrayAux.get(0));
    button2.setText(arrayAux.get(1));
    button3.setText(arrayAux.get(2));

        String tmpDownSlash = "";
        for (int i = 0; i < figure.length(); i++) {
            tmpDownSlash += " _";
        }
        String stringAux = fp.get(i).getFrase().toUpperCase().replace("*",tmpDownSlash);
        textView.setText(stringAux);
    }catch (java.lang.IndexOutOfBoundsException e){
        e.printStackTrace();
        avanzaNivel();
    }
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("frasegame1lvl_screen Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

   public void avanzaNivel(){
       gn.avanzaNivel();
       if (!(gn.getTipo().contains("frases"))) {
           System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
           //Código para abrir otra pantalla
           activiftiFinalizado =true;
           Intent intent = new Intent(frasegame1lvl_screen.this, endScreen.class);
           startActivity(intent);
       } else {
           if (!activiftiFinalizado) {
               if (gn.getTipo().equals(tipoNivel)) {
                 //  es.resetPanelEstrellas();
               }
               fp = gn.getFotos();
               i = 0;
               cambiarFoto();
           }
       }

    }
    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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
                    // User cancelled the dialog
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

