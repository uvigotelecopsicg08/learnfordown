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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.TreeSet;

public class silabasgame4lvl_screen extends AppCompatActivity {
    TextView titulo;
    String Correcta;
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    private RecyclerView horizontal_recycler_view2;
    private ArrayList<String> horizontalList2;
    private HorizontalAdapter horizontalAdapter2;
//    final HashMap<Integer, Float> thresholds = new HashMap<>();
    RatingBar ratingbar1;
    Button ButtonActual;
    ImageView palabra;
    GestionNiveles  gn;
    String tipoNivel="silabasdirectas";
    ArrayList<FotoPalabra> fp;
    int i=0;
    int aciertos=0;

  //  int contador;
    boolean activiftiFinalizado =false;
    Estrellas es;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silabasgame4lvl_screen);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tipoNivel = extras.getString("tipoSilaba");
            System.out.println(tipoNivel);
        }
        try {
            Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
            titulo = (TextView) findViewById(R.id.textView2);
            titulo.setTypeface(face);
            horizontal_recycler_view = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
            horizontal_recycler_view2 = (RecyclerView) findViewById(R.id.horizontal_recycler_view2);
            titulo = (TextView) findViewById(R.id.textView2);
            palabra = (ImageView) findViewById(R.id.imageView2);
            titulo.setTypeface(face);


            Context context = this.getApplicationContext();


            gn = new GestionNiveles(context);
            es = new Estrellas(this, gn, gn.setNivel(tipoNivel, 4));
            fp = gn.getFotos();

            horizontalList = new ArrayList<String>();
            gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(), horizontalList);
            Collections.shuffle(horizontalList);
            palabra.setImageResource(fp.get(i).getFoto());
            Correcta = fp.get(i).getSilaba().toUpperCase();

            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            horizontalAdapter = new HorizontalAdapter(horizontalList, 5, metrics, "lectura");
            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(silabasgame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

            horizontalList2 = new ArrayList<String>();
            gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(), horizontalList2);
            Collections.shuffle(horizontalList2);

            horizontalAdapter2 = new HorizontalAdapter(horizontalList2, 5, metrics, "lectura");

            LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(silabasgame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);
            horizontal_recycler_view.setAdapter(horizontalAdapter);
            horizontal_recycler_view2.setAdapter(horizontalAdapter2);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            avanzaNivel();
        }
    }
    public void BackArrow (View v){
        menu_screen pantalla_anterior = new menu_screen();
        Intent intent1 = new Intent();
        switch (pantalla_anterior.getNivelAnterior()) {
            case "SilabasDirectas":
                intent1 = new Intent(silabasgame4lvl_screen.this, sidirectas_screen.class);
                break;
            case "SilabasInversas":
                intent1 = new Intent(silabasgame4lvl_screen.this, siinversas_screen.class);
                break;
            case "SilabasTrabadas":
                intent1 = new Intent(silabasgame4lvl_screen.this, sitrabadas_screen.class);
                break;
        }

        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(silabasgame4lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void ButtonCheck (View v){
        Button b = (Button)v;
        ButtonActual =b;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                -50.0f, 0.0f);
        animation.setDuration(400);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {

                    ButtonActual.setBackgroundColor(Color.GREEN);
                    ButtonActual.setEnabled(false);
                    aciertos++;

                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {
                    if (aciertos == 1) {


                        es.acierto();

                        MediaPlayer aciertoMedia = es.getAciertoMedia();
                        aciertoMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                        es.pulsar(true);
                        System.out.println("Se ha anotado un acierto");
                        if (!gn.isnivelCompletado()) {
                            i++;
                            cambiarFoto();
                        } else {
                            System.out.print("el nivel esta finalizado");
                            avanzaNivel();

                        }
                        aciertos = 0;

                    }

                });
                    }
                } else {
                   es.fallo();
                    //Codigo de Animacion Fallo

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        b.startAnimation(animation);
    }

    private void avanzaNivel() {
        gn.avanzaNivel();
        if (gn.getDificultad() != 4 || !(gn.getTipo().equals(tipoNivel))) {
            System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
            //Código para abrir otra pantalla
            activiftiFinalizado=true;
            Intent intent = new Intent(silabasgame4lvl_screen.this, frasegame1lvl_screen.class);
            String strName= null;
            if(gn.getTipo().contains("directas")){
                strName = "frasessilabasdirectas";
            }
            else{
                if(gn.getTipo().contains("inversas")){
                    strName = "frasessilabasinversas";
                }
                else{
                    strName ="frasessilabastrabadas";
                }
            }

            intent.putExtra("tipoSilaba", strName);
            intent.putExtra("nivel",1);
            startActivity(intent);

        } else {
            if(!activiftiFinalizado) {
                fp = gn.getFotos();
                i = 0;
                cambiarFoto();
                System.out.println("Se debe avanzar el nivel");
            }
        }
    }

    private void cambiarFoto() {
        try {
            horizontalList.clear();
            horizontalList = new ArrayList<String>();
            gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(), horizontalList);
            Collections.shuffle(horizontalList);
            palabra.setImageResource(fp.get(i).getFoto());
            Correcta = fp.get(i).getSilaba().toUpperCase();
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            horizontalAdapter = new HorizontalAdapter(horizontalList, 5, metrics, "lectura");
            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(silabasgame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

            horizontal_recycler_view.setAdapter(horizontalAdapter);
            horizontalList2.clear();
            horizontalList2 = new ArrayList<String>();
            gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(), horizontalList2);
            Collections.shuffle(horizontalList2);
            horizontalAdapter2 = new HorizontalAdapter(horizontalList2, 5, metrics, "lectura");

            LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(silabasgame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);
            horizontal_recycler_view2.setAdapter(horizontalAdapter2);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            avanzaNivel();
        }
    }
    public void reset(View v){
        i=0;
        es.resetPanelEstrellas();
        fp=gn.getFotos();
        cambiarFoto();
    }

}

