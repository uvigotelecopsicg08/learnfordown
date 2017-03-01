package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
    RatingBar ratingbar1 = null;
    String figure = "plato";
    Button button1,button2,button3;
    int contador;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
    ImageView palabra;
    GestionNiveles  gn;
    String tipoNivel="frasessilabasdirectas";
    ArrayList<FotoPalabra> fp;
    int i=0;
    int aciertos=0;
    TextView textView;

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
            System.out.println(tipoNivel);
        }
        System.out.println(tipoNivel);
        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        gn.setNivel(tipoNivel, 1);
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



        String tmpDownSlash = "";
        for (int i = 0; i < figure.length()-1; i++) {
            tmpDownSlash += " _";
        }
        String stringAux = fp.get(i).getFrase().toUpperCase().replace("*",tmpDownSlash);
        textView.setText(stringAux);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);
        contador = 0;
        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas
    }

    public void BackArrow(View v) {
        Intent intent1 = new Intent(frasegame1lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(frasegame1lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void pulsar(View v) {

       Button bAxu = (Button) findViewById(v.getId());

        if(bAxu.getText().equals(fp.get(i).getPalabra().toUpperCase())){
            Log.i("pulsar()", "CORRECTO!");
            Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
            contador++;
            float rating = 0;
            for (int i : new TreeSet<>(thresholds.keySet())) {
                if(contador < i) {
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
            gn.acierto();
            if(!gn.isnivelCompletado()) {
                i++;
                cambiarFoto();
            }
            else{
                System.out.print("el nivel esta finalizado");
                gn.avanzaNivel();
                if(!(gn.getTipo().equals(tipoNivel))){
                    System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
                    //Código para abrir otra pantalla
                }
                else {
                    fp= gn.getFotos();
                    i=0;
                    cambiarFoto();
                    System.out.println("Se debe avanzar el nivel");
                }

                gn.fallo();
            }

        }else{

        }
/*
        switch (v.getId()) {
            case R.id.button1:
                if (figure.equals(button1)) {
                    Log.i("pulsar()", "CORRECTO!");
                    Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                    contador++;
                    float rating = 0;
                    for (int i : new TreeSet<>(thresholds.keySet())) {
                        if(contador < i) {
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
                break;
            case R.id.button2:
                if (figure.equals(button2)) {
                    Log.i("pulsar()", "CORRECTO!");
                    Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                    contador++;
                    float rating = 0;
                    for (int i : new TreeSet<>(thresholds.keySet())) {
                        if(contador < i) {
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
                break;
            case R.id.button3:
                if (figure.equals(button3)) {
                    Log.i("pulsar()", "CORRECTO!");
                    Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                    contador++;
                    float rating = 0;
                    for (int i : new TreeSet<>(thresholds.keySet())) {
                        if(contador < i) {
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
                break;
        }*/
    }

    private void cambiarFoto() {

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
        String tmpDownSlash = "";
        for (int i = 0; i < figure.length()-1; i++) {
            tmpDownSlash += " _";
        }
        String stringAux = fp.get(i).getFrase().toUpperCase().replace("*",tmpDownSlash);
        textView.setText(stringAux);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
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

