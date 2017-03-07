package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class lettergame1lvl_screen extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    String Correcta;
    Button ButtonActual;
    TextView titulo,letracorrecta;
    ImageButton BackArrow,Home;
    ImageView palabra;
    GestionNiveles  gn;
    String tipoNivel="leerletras";
    boolean siguientepalabra=true;
    ArrayList<FotoPalabra> fp;
    int i = 0;
    int contador=0;
    RatingBar ratingbar1 = null;

    final HashMap<Integer, Float> thresholds = new HashMap<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame1lvl_screen);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        BackArrow = (ImageButton) findViewById(R.id.button3);
        Home = (ImageButton) findViewById(R.id.button5);
        palabra= (ImageView)findViewById(R.id.imageView2);
        letracorrecta=(TextView)findViewById(R.id.textView4);
        titulo.setTypeface(face);

        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);
        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(20, 3f); //20 aciertos, 3 estrellas
        thresholds.put(30, 4f); //30 aciertos, 4 estrellas
        thresholds.put(40, 5f); //40 aciertos, 5 estrellas
        thresholds.put(50, 6f); //50 aciertos, 6 estrellas


        contador = 0;
        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        gn.setNivel(tipoNivel,1);
        fp=gn.getFotos();

        horizontalList = new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList);
        Collections.shuffle( horizontalList);
        palabra.setImageResource(fp.get(i).getFoto());
        letracorrecta.setText(fp.get(i).getLetra().toUpperCase());
        Correcta= fp.get(i).getLetra().toUpperCase();

        horizontalAdapter = new HorizontalAdapter(horizontalList);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame1lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);


          horizontal_recycler_view.setAdapter(horizontalAdapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    public void BackArrow (View v){
        Intent intent1 = new Intent(lettergame1lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(lettergame1lvl_screen.this, home_screen.class);
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

    public void ButtonCheck(View v) {
        /*
        Button b = (Button) v;
        ButtonActual = b;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                -50.0f, 0.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {
                    ButtonActual.setBackgroundColor(Color.GREEN);
                    gn.acierto();
                    contador++;
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
                            Intent intent = new Intent(lettergame1lvl_screen.this, lettergame2lvl_screen.class);
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
        */
        Button b = (Button) v;
        ButtonActual = b;
        if (Correcta.equals(ButtonActual.getText().toString())){
            System.out.println(gn.getDificultad());
            gn.acierto();
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
                    Intent intent = new Intent(lettergame1lvl_screen.this, lettergame2lvl_screen.class);
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

    private void cambiarFoto() {
        horizontalList.clear();
        horizontalList = new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList);
        Collections.shuffle(horizontalList);
        palabra.setImageResource(fp.get(i).getFoto());
        letracorrecta.setText(fp.get(i).getLetra().toUpperCase());
        Correcta= fp.get(i).getLetra().toUpperCase();
        horizontalAdapter = new HorizontalAdapter(horizontalList);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame1lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);


        horizontal_recycler_view.setAdapter(horizontalAdapter);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("lettergame1lvl_screen Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
