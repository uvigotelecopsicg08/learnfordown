package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class lettergame2lvl_screen extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    private RecyclerView horizontal_recycler_view2;
    private ArrayList<String> horizontalList2;
    private HorizontalAdapter horizontalAdapter2;
    Button ButtonActual;
    TextView titulo;
    String Correcta="";
    ImageButton BackArrow,Home;
    ImageView palabra;
    GestionNiveles  gn;
    TextView letracorrecta;
    String tipoNivel="leerletras";
    ArrayList<FotoPalabra> fp;
    int i=0;
    int aciertos=0;
    int contador=0;
    RatingBar ratingbar1 = null;
    final HashMap<Integer, Float> thresholds = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame2lvl_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontal_recycler_view2= (RecyclerView) findViewById(R.id.horizontal_recycler_view2);
        titulo = (TextView) findViewById(R.id.textView2);
        Home = (ImageButton) findViewById(R.id.button5);
        palabra= (ImageView)findViewById(R.id.imageView2);
        letracorrecta=(TextView)findViewById(R.id.textView4);
        titulo.setTypeface(face);
        contador=0;
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(79, 6f); //79 aciertos, 6 estrellas

        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        gn.setNivel(tipoNivel,2);
        fp=gn.getFotos();

        horizontalList = new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList);
        Collections.shuffle( horizontalList);
        palabra.setImageResource(fp.get(i).getFoto());
        letracorrecta.setText(fp.get(i).getLetra().toUpperCase());
        Correcta= fp.get(i).getLetra().toUpperCase();

        horizontalAdapter=new HorizontalAdapter(horizontalList);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        horizontalList2=new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList2);
        Collections.shuffle( horizontalList2);

        horizontalAdapter2=new HorizontalAdapter(horizontalList2);

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        horizontal_recycler_view2.setAdapter(horizontalAdapter2);
    }

    public void BackArrow (View v){
        Intent intent1 = new Intent(lettergame2lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(lettergame2lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

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
                    contador++;
                    pulsar();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {
                    if (aciertos == 1) {
                        gn.acierto();
                        pulsar();
                        System.out.println("Se ha anotado un acierto");
                        if (!gn.isnivelCompletado()) {
                            i++;
                            cambiarFoto();
                        } else {
                            System.out.print("el nivel esta finalizado");
                            gn.avanzaNivel();
                            if (gn.getDificultad() != 2 || !(gn.getTipo().equals(tipoNivel))) {
                                System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
                                //Código para abrir otra pantalla
                            } else {
                                fp = gn.getFotos();
                                i = 0;
                                cambiarFoto();
                                System.out.println("Se debe avanzar el nivel");
                            }

                        }
                        aciertos = 0;
                    }
                } else {
                    gn.fallo();
                    //Codigo de Animacion Fallo

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
        letracorrecta.setText(fp.get(i).getLetra().toUpperCase());
        Correcta= fp.get(i).getLetra().toUpperCase();
        horizontalAdapter = new HorizontalAdapter(horizontalList);
        horizontalAdapter2 = new HorizontalAdapter(horizontalList2);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);

        horizontal_recycler_view.setAdapter(horizontalAdapter);
        horizontal_recycler_view2.setAdapter(horizontalAdapter2);
    }

}
