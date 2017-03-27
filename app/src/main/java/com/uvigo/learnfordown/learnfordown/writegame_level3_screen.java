package com.uvigo.learnfordown.learnfordown;

import android.view.Gravity;
import android.widget.RatingBar;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;


public class writegame_level3_screen extends AppCompatActivity {

    private RecyclerView PanelHorizontal;
    private HorizontalAdapter HorizontalAdapter;
    private ArrayList<String> ListaHorizontal;
    private char [] LetrasPalabra;

    private GestionNiveles  gn;
    private ArrayList <FotoPalabra> fp;

    private TextView Titulo, Frase;
    private ImageView Foto;
    private String TipoNivel,Correcta;
    private int i = 0;
    private int j = 0;
    private Button ButtonActual;
    private String RellenoFrase;
    private int num_iteracion = 0;
    //int contador;
    //RatingBar ratingbar1;
    Estrellas  es;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
    private Button bottonRefencia;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writegame_level2_screen);

        PanelHorizontal = (RecyclerView) findViewById(R.id.PanelHorizontal);

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        Foto = (ImageView)findViewById(R.id.imageView2);
        Frase =(TextView)findViewById(R.id.textView4);

       /* // ** Estrellitas **

        contador = 0;
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas */

        //** Base de datos **

        TipoNivel = "escribirsinsombreado"; // Esto tiene que cambiarse cada n iteraciones -> IMPORTANTE
        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        gn.setNivel(TipoNivel,1);
        fp = gn.getFotosAleatorias();
        es= new Estrellas (this,gn,gn.setNivel(TipoNivel,3));

        RellenoFrase = fp.get(i).getFrase().toUpperCase();
        Correcta = fp.get(i).getPalabra().toUpperCase();
        Foto.setImageResource(fp.get(i).getFoto());
        Rellenar();



        CompletaLista();

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(writegame_level3_screen.this, LinearLayoutManager.HORIZONTAL, false);
        PanelHorizontal.setLayoutManager(horizontalLayoutManager);
        HorizontalAdapter = new HorizontalAdapter(ListaHorizontal,"escritura");
        PanelHorizontal.setAdapter(HorizontalAdapter);




    }


    public void BackArrow (View v){
        Intent intent1 = new Intent(writegame_level3_screen.this, menu_write_screen.class);
        startActivity(intent1);
    }

    public void goHome (View v){
        Intent intent1 = new Intent(writegame_level3_screen.this, home_screen.class);
        startActivity(intent1);
    }

    /* public void pulsar() {
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
            toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, -350, -50);
            toast.show();
        }
    } */

    public void ButtonCheck (View v){

        Button b = (Button)v;
        ButtonActual = b;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, -50.0f, 0.0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
              //  PanelHorizontal.setEnabled(false);
                if (String.valueOf(LetrasPalabra[num_iteracion]).equals(ButtonActual.getText().toString())) {
                    ButtonActual.setBackgroundColor(Color.GREEN);
                    bottonRefencia=ButtonActual;
                  //  if (num_iteracion == Correcta.length());

                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                if (String.valueOf(LetrasPalabra[num_iteracion]).equals(bottonRefencia.getText().toString())){
                    //gn.acierto();
                    SustituirLinea();
                    num_iteracion++;


                    if(num_iteracion == Correcta.length()) {

                        es.acierto();
                        es.pulsar(true);
                        if (!gn.isnivelCompletado()) { // Aún no terminó el nivel
                            i++;
                            cambiarFoto();
                        } else {

                           // contador++;
                           // pulsar();
                            gn.avanzaNivel();

                            if (gn.getDificultad() != 1 || !(gn.getTipo().equals(TipoNivel))) {

                                //Código para abrir otra pantalla
                                Intent intent = new Intent(writegame_level3_screen.this, writegame_level4_screen.class);
                                startActivity(intent);
                            } else {
                                fp = gn.getFotos();
                                i = 0;
                                cambiarFoto();
                            }
                        }
                    }

                } else if (String.valueOf(LetrasPalabra[num_iteracion]).equals(ButtonActual.getText().toString())) gn.fallo();

                PanelHorizontal.setEnabled(true);
            }


            @Override
            public void onAnimationRepeat(Animation animation) {}

        });

        b.startAnimation(animation);

    }


    private void cambiarFoto() {

        Correcta = fp.get(i).getPalabra().toUpperCase();
        Foto.setImageResource(fp.get(i).getFoto());
        RellenoFrase = fp.get(i).getFrase().toUpperCase();
        Rellenar();
        CompletaLista();
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(writegame_level3_screen.this, LinearLayoutManager.HORIZONTAL, false);
        PanelHorizontal.setLayoutManager(horizontalLayoutManager);
        HorizontalAdapter = new HorizontalAdapter(ListaHorizontal,"escritura");
        PanelHorizontal.setAdapter(HorizontalAdapter);
        num_iteracion=0;

    }

    public void Rellenar (){

        String Relleno = "_";
        int Longitud = Correcta.length();
        while (Relleno.length() != Longitud){
            Relleno = Relleno.concat(" _");
            Longitud ++; // Debido a que concatenamos un espacio + _
        }

        if (RellenoFrase.contains("*"))RellenoFrase = RellenoFrase.replace("*",Relleno);
        Frase.setText(RellenoFrase);
    }

    public void CompletaLista(){

        ListaHorizontal = new ArrayList <String> ();
        LetrasPalabra = new char[Correcta.length()];
        Correcta.getChars(0,Correcta.length(),LetrasPalabra,0);
        for(int n=0; n < (LetrasPalabra.length);n++){
            ListaHorizontal.add(String.valueOf(LetrasPalabra[n]));
            Collections.shuffle(ListaHorizontal);
        }


    }


    public void SustituirLinea(){

        if (j == 0) RellenoFrase = RellenoFrase.replaceFirst("_",String.valueOf(LetrasPalabra[num_iteracion]));
        else RellenoFrase = RellenoFrase.replaceFirst(" _",String.valueOf(LetrasPalabra[num_iteracion]));
        Frase.setText(RellenoFrase);
        j++;

    }



}

