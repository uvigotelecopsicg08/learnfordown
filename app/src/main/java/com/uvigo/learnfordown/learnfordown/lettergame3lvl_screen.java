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
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.TreeSet;

public class lettergame3lvl_screen extends AppCompatActivity {
    TextView titulo;
    String Correcta="";
    Button ButtonActual;
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    ImageView palabra;
    GestionNiveles gn;
    String tipoNivel = "leerletras";
    ArrayList<FotoPalabra> fp;
    int i = 0;
    int aciertos=0;

    Estrellas es;


    /*  int contador;
    RatingBar ratingbar1 = null;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame3lvl_screen);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        palabra = (ImageView) findViewById(R.id.imageView2);
        titulo.setTypeface(face);


        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        es =new Estrellas(this,gn, gn.setNivel(tipoNivel, 3));
        fp = gn.getFotos();


try {
    palabra = (ImageView) findViewById(R.id.imageView2);
    horizontalList = new ArrayList<String>();
    gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(), horizontalList);
    Collections.shuffle(horizontalList);
    palabra.setImageResource(fp.get(i).getFoto());
    Correcta = fp.get(i).getLetra().toUpperCase();

    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    horizontalAdapter = new HorizontalAdapter(horizontalList, 5, metrics, "lectura");
    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame3lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
    horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
    horizontal_recycler_view.setAdapter(horizontalAdapter);
    }
    catch (IndexOutOfBoundsException e){
     e.printStackTrace();
        avanzaNivel();
}
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(lettergame3lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(lettergame3lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }


    public void ButtonCheck (View v){
        Button b = (Button)v;
        ButtonActual =b;
            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                    -50.0f, 0.0f);
            animation.setDuration(500);
            animation.setFillAfter(true);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (Correcta.equals(ButtonActual.getText().toString())) {

                        ButtonActual.setBackgroundColor(Color.GREEN);
                        horizontal_recycler_view.setEnabled(false);
                       //
                        // gn.acierto();

                    }



                }

                @Override
                public void onAnimationEnd(Animation animation) {


                            if (Correcta.equals(ButtonActual.getText().toString())) {
                                es.acierto();

                                MediaPlayer aciertoMedia = es.getAciertoMedia();
                                aciertoMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        horizontal_recycler_view.setEnabled(true);
                                        es.pulsar(true);


                                        if (!gn.isnivelCompletado()) {
                                            i++;
                                            cambiarFoto();
                                        } else {
                                            System.out.print("el nivel esta finalizado");
                                            //gn.actualizarEstrellas(contador);
                                            avanzaNivel();

                                        }

                                    }

                                });
                            } else {
                                //Codigo de Animacion Fallo
                                es.fallo();
                                System.out.println("Se ha anotado un fallo");


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
        if (gn.getDificultad() != 3 || !(gn.getTipo().equals(tipoNivel))) {
            System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
            //Código para abrir otra pantalla
            Intent intent = new Intent(lettergame3lvl_screen.this, lettergame4lvl_screen.class);
            startActivity(intent);
        } else {
            fp = gn.getFotos();
            i = 0;
            cambiarFoto();
            System.out.println("Se debe avanzar el nivel");
        }

    }

    private void cambiarFoto() {
        try {
            horizontalList.clear();
            horizontalList = new ArrayList<String>();
            gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(), horizontalList);
            Collections.shuffle(horizontalList);
            palabra.setImageResource(fp.get(i).getFoto());
            Correcta = fp.get(i).getLetra().toUpperCase();
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            horizontalAdapter = new HorizontalAdapter(horizontalList, 5, metrics, "lectura");
            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame3lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);


            horizontal_recycler_view.setAdapter(horizontalAdapter);
        }

        catch(IndexOutOfBoundsException e){
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

