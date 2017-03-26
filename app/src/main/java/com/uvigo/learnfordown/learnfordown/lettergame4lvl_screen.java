package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.RatingBar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.TreeSet;

public class lettergame4lvl_screen extends AppCompatActivity {
    TextView titulo;
    Button ButtonActual;
    String Correcta = "R";
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    private RecyclerView horizontal_recycler_view2;
    private ArrayList<String> horizontalList2;
    private HorizontalAdapter horizontalAdapter2;
    RatingBar ratingbar1 = null;
    ImageView palabra;
    GestionNiveles  gn;
    String tipoNivel="leerletras";
    ArrayList<FotoPalabra> fp;
    int i=0;

    int aciertos=0;
    /*
    int contador;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
*/
    Estrellas es;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame4lvl_screen);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        horizontal_recycler_view = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontal_recycler_view2 = (RecyclerView) findViewById(R.id.horizontal_recycler_view2);
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        /*
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);
        contador=0;

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(30, 2f); //30 aciertos, 2 estrellas
        thresholds.put(60, 3f); //60 aciertos, 3 estrellas
        thresholds.put(90, 4f); //90 aciertos, 4 estrellas
        thresholds.put(120, 5f); //120 aciertos, 5 estrellas
        thresholds.put(158, 6f); //158 aciertos, 6 estrellas
        */
        palabra= (ImageView)findViewById(R.id.imageView2);

        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        es =new Estrellas(this,gn,gn.setNivel(tipoNivel,4));
        fp=gn.getFotos();


        horizontalList=new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList);
        Collections.shuffle( horizontalList);
        palabra.setImageResource(fp.get(i).getFoto());
        Correcta= fp.get(i).getLetra().toUpperCase();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        horizontalAdapter = new HorizontalAdapter(horizontalList,5,metrics,"lectura");

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        horizontalList2=new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList2);
        Collections.shuffle( horizontalList2);
        horizontalAdapter2=new HorizontalAdapter(horizontalList2);

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(lettergame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        horizontal_recycler_view2.setAdapter(horizontalAdapter2);
    }

    public void BackArrow(View v) {
        Intent intent1 = new Intent(lettergame4lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }

    public void goHome(View v) {
        Intent intent1 = new Intent(lettergame4lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }
/*
    public void pulsar() {
        float rating = 0;
        for (int i : new TreeSet<>(thresholds.keySet())) {
            if (contador < i) {
                break;
            }
            rating = thresholds.get(i);
        }
        if (rating != ratingbar1.getRating()) {
            ratingbar1.setRating(rating);
            Toast toast = Toast.makeText(this, "¡HAS CONSEGUIDO UNA ESTRELLITA!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, -270, -50);
            toast.show();
        }
    }
    */

    public void ButtonCheck(View v) {
        Button b = (Button) v;
        ButtonActual =b;
        String buttonText = b.getText().toString();
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                -50.0f, 0.0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {
                    ButtonActual.setBackgroundColor(Color.GREEN);
                    ButtonActual.setEnabled(false);

                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {
                        es.acierto();
                        es.pulsar(true);
                    //Codigo de Animacion Acierto

                        System.out.println("Se ha anotado un acierto");
                        if (!gn.isnivelCompletado()) {
                            i++;
                            cambiarFoto();
                        } else {
                            System.out.print("el nivel esta finalizado");
                            gn.avanzaNivel();
                            if (gn.getDificultad() != 4 || !(gn.getTipo().equals(tipoNivel))) {
                                System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
                                //Código para abrir otra pantalla
                                Intent intent = new Intent(lettergame4lvl_screen.this, silabasgame1lvl_screen.class);
                                startActivity(intent);
                            } else {
                                fp = gn.getFotos();
                                i = 0;
                                cambiarFoto();
                                System.out.println("Se debe avanzar el nivel");
                            }

                        }
                        aciertos = 0;

                } else {
                    gn.fallo();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        b.startAnimation(animation);

    }



    private void cambiarFoto() {
        horizontalList.clear();
        horizontalList = new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList);
        Collections.shuffle(horizontalList);

        horizontalList2.clear();
        horizontalList2 = new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList2);
        Collections.shuffle(horizontalList2);

        palabra.setImageResource(fp.get(i).getFoto());
        Correcta= fp.get(i).getLetra().toUpperCase();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        horizontalAdapter = new HorizontalAdapter(horizontalList,5,metrics,"lectura");
        horizontalAdapter2 = new HorizontalAdapter(horizontalList2,5,metrics,"lectura");

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(lettergame4lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);

        horizontal_recycler_view.setAdapter(horizontalAdapter);
        horizontal_recycler_view2.setAdapter(horizontalAdapter2);
    }
    public void reset(View v){
        i=0;
        es.resetPanelEstrellas();
        fp=gn.getFotos();
        cambiarFoto();
    }

}

