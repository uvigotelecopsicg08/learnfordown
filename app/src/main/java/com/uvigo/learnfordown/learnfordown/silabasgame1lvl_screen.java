package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class silabasgame1lvl_screen extends AppCompatActivity {

    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;

    Button ButtonActual;
    String Correcta;
    TextView titulo,letracorrecta;
    ImageButton BackArrow,Home;
    ImageView palabra;
    GestionNiveles  gn;
    String tipoNivel="silabasdirectas",palabracom,palabraini, tmpDownSlash= " ";
    ArrayList<FotoPalabra> fp;
    int i=0;
    int contador;
    RatingBar ratingbar1;
    final HashMap<Integer, Float> thresholds = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silabasgame1lvl_screen);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tipoNivel = extras.getString("tipoSilaba");
            System.out.println(tipoNivel);

        }

        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        BackArrow = (ImageButton) findViewById(R.id.button3);
        Home = (ImageButton) findViewById(R.id.button5);
        titulo.setTypeface(face);
        contador=0;
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas

        palabra= (ImageView)findViewById(R.id.imageView2);
        letracorrecta=(TextView)findViewById(R.id.textView4);
        Context context = this.getApplicationContext();

        gn = new GestionNiveles(context);
        gn.setNivel(tipoNivel,1);
        fp=gn.getFotos();


        horizontalList=new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(),horizontalList);
        Collections.shuffle( horizontalList);
        palabra.setImageResource(fp.get(i).getFoto());
        letracorrecta.setText(fp.get(i).getSilaba().toUpperCase());
        Correcta= fp.get(i).getSilaba().toUpperCase();
        tmpDownSlash = "";
        for (int j=0;j<Correcta.length();j++){
            tmpDownSlash += " _";
        }

        System.out.println("getPalabra "+ fp.get(i).getPalabra());
        palabracom = fp.get(i).getPalabra().toUpperCase();
        palabracom = palabracom.replaceAll(Correcta.toUpperCase(), tmpDownSlash);
        letracorrecta.setText(palabracom);


        horizontalAdapter=new HorizontalAdapter(horizontalList);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(silabasgame1lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);





        horizontal_recycler_view.setAdapter(horizontalAdapter);




    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(silabasgame1lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(silabasgame1lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void pulsar() {
        float rating = 0;
        for (int j : new TreeSet<>(thresholds.keySet())) {
            if (contador < j) {
                break;
            }
            rating = thresholds.get(j);
        }
        if (rating != ratingbar1.getRating()) {
            ratingbar1.setRating(rating);
            Toast toast = Toast.makeText(this, "¡HAS CONSEGUIDO UNA ESTRELLITA!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, -350, -50);
            toast.show();
        }
    }
    public void ButtonCheck (View v){
        Button b = (Button)v;
        ButtonActual = b;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                -50.0f, 0.0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {
                    ButtonActual.setBackgroundColor(Color.GREEN);
                    gn.acierto();
                    contador++;
                    palabracom=fp.get(i).getPalabra().toUpperCase();
                    //palabracom=fp.get(i).getPalabra().toUpperCase().replaceAll(tmpDownSlash,ButtonActual.getText().toString());
                    letracorrecta.setText(palabracom);
                    pulsar();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())){
                    System.out.println(gn.getDificultad());
                    if(!gn.isnivelCompletado()) {
                        i++;
                        cambiarFoto();
                    }
                    else{
                        System.out.print("el nivel esta finalizado");
                        gn.avanzaNivel();
                        if(gn.getDificultad()!=1 ||!(gn.getTipo().equals(tipoNivel))){
                            System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
                            //Código para abrir otra pantalla
                            Intent intent = new Intent(silabasgame1lvl_screen.this, silabasgame2lvl_screen.class);
                            startActivity(intent);
                        }
                        else {
                            fp= gn.getFotos();
                            i=0;
                            cambiarFoto();
                            System.out.println("Se debe avanzar el nivel");
                        }


                    }
//Codigo de Animacion Acierto
                } else{
                    //Codigo de Animacion Fallo
                    gn.fallo();
                    System.out.println("Se ha anotado un fallo");


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
        gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(),horizontalList);
        Collections.shuffle(horizontalList);
        palabra.setImageResource(fp.get(i).getFoto());
        letracorrecta.setText(fp.get(i).getSilaba().toUpperCase());
        System.out.println("+++++++++++++++++"+fp.get(i).getSilaba().toUpperCase());
        Correcta= fp.get(i).getSilaba().toUpperCase();
        horizontalAdapter = new HorizontalAdapter(horizontalList);

        tmpDownSlash = "";
        for (int j=0;j<Correcta.length();j++){
            tmpDownSlash += " _";
        }
        palabracom=fp.get(i).getPalabra().toUpperCase().replaceAll(Correcta.toUpperCase(), tmpDownSlash);
        letracorrecta.setText(palabracom);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(silabasgame1lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);


        horizontal_recycler_view.setAdapter(horizontalAdapter);
    }


}
