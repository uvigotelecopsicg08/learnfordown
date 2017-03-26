package com.uvigo.learnfordown.learnfordown;

import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;


public class writegame_level2_screen extends AppCompatActivity {

    private RecyclerView PanelHorizontal;
    private HorizontalAdapter HorizontalAdapter;
    private ArrayList<String> ListaHorizontal;
    private char[] LetrasPalabra;

    private GestionNiveles gn;
    private ArrayList<FotoPalabra> fp;

    private TextView Titulo, Frase;
    private ImageView Foto;
    private String TipoNivel, Correcta;
    private int i = 0;
    private int j = 0;
    private Button ButtonActual;
    private String RellenoFrase;
    private int num_iteracion = 0;
    int contador;
    RatingBar ratingbar1;
    final HashMap<Integer, Float> thresholds = new HashMap<>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writegame_level2_screen);

        PanelHorizontal = (RecyclerView) findViewById(R.id.PanelHorizontal);

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        Foto = (ImageView) findViewById(R.id.imageView2);
        Frase = (TextView) findViewById(R.id.textView4);

        // ** Estrellitas **

        contador = 0;
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas

        //** Base de datos **

        TipoNivel = "palabrassilabasdirectas"; // Esto tiene que cambiarse cada n iteraciones -> IMPORTANTE
        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        gn.setNivel(TipoNivel, 1);
        fp = gn.getFotosAleatorias();

        RellenoFrase = fp.get(i).getFrase().toUpperCase();
        Correcta = fp.get(i).getPalabra().toUpperCase();
        Foto.setImageResource(fp.get(i).getFoto());
        Rellenar(true);


        CompletaLista();

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(writegame_level2_screen.this, LinearLayoutManager.HORIZONTAL, false);
        PanelHorizontal.setLayoutManager(horizontalLayoutManager);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        HorizontalAdapter = new HorizontalAdapter(ListaHorizontal,ListaHorizontal.size(),metrics,"escritura");
        PanelHorizontal.setAdapter(HorizontalAdapter);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void BackArrow(View v) {
        Intent intent1 = new Intent(writegame_level2_screen.this, menu_write_screen.class);
        startActivity(intent1);
    }

    public void goHome(View v) {
        Intent intent1 = new Intent(writegame_level2_screen.this, home_screen.class);
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
            toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, -350, -50);
            toast.show();
        }
    }
    public void ButtonCheck(View v) {

        Button b = (Button) v;
        ButtonActual = b;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, -50.0f, 0.0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                if (String.valueOf(LetrasPalabra[num_iteracion]).equals(ButtonActual.getText().toString())) {
                    ButtonActual.setBackgroundColor(Color.GREEN);
                    if (num_iteracion == Correcta.length()) gn.acierto();
                    Rellenar(false);




                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (String.valueOf(LetrasPalabra[num_iteracion]).equals(ButtonActual.getText().toString())) {

                    num_iteracion++;

                    if (num_iteracion == Correcta.length()) {


                        if (!gn.isnivelCompletado()) { // Aún no terminó el nivel
                            i++;
                            cambiarFoto();
                        } else {

                            contador++;
                            pulsar();
                            gn.avanzaNivel();
                            if (gn.getDificultad() != 1 || !(gn.getTipo().equals(TipoNivel))) {

                                //Código para abrir otra pantalla
                                Intent intent = new Intent(writegame_level2_screen.this, writegame_level3_screen.class);
                                startActivity(intent);
                            } else {
                                fp = gn.getFotos();
                                i = 0;
                                cambiarFoto();
                            }
                        }
                    }

                } else if (String.valueOf(LetrasPalabra[num_iteracion]).equals(ButtonActual.getText().toString()))
                    gn.fallo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });

        b.startAnimation(animation);

    }


    private void cambiarFoto() {

        Correcta = fp.get(i).getPalabra().toUpperCase();
        Foto.setImageResource(fp.get(i).getFoto());
        RellenoFrase = fp.get(i).getFrase().toUpperCase();
        Rellenar(false);
        CompletaLista();

    }

    public void Rellenar(boolean inicio) {

        String parte1,parte2;
        String[] cadena;
        cadena = RellenoFrase.split("\\*");
        SpannableStringBuilder builder = new SpannableStringBuilder();


       if (inicio == true) {
            parte1 = cadena[0];
            parte2 = Correcta;
        }else{
            parte1 = cadena[0]+Correcta.substring(0,num_iteracion+1);
            parte2 = Correcta.substring(num_iteracion+1);
        }


        SpannableString FirstSpannable = new SpannableString(parte1);
        FirstSpannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.Negro)), 0, parte1.length(), 0);
        builder.append(FirstSpannable);

        SpannableString ShadowSpannable = new SpannableString(parte2);
        ShadowSpannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.Sombreado)), 0, parte2.length(), 0);
        builder.append(ShadowSpannable);


        SpannableString ThirdSpannable = new SpannableString(cadena[1]);
        ThirdSpannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.Negro)), 0, cadena[1].length(), 0);
        builder.append(ThirdSpannable);


        Frase.setText(builder, TextView.BufferType.SPANNABLE);

    }

    public void CompletaLista() {

        ListaHorizontal = new ArrayList<String>();
        LetrasPalabra = new char[Correcta.length()];
        Correcta.getChars(0, Correcta.length(), LetrasPalabra, 0);
        for (int n = 0; n < (LetrasPalabra.length); n++) {
            ListaHorizontal.add(String.valueOf(LetrasPalabra[n]));
            Collections.shuffle(ListaHorizontal);
        }
    }



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("writegame_level2_screen Page") // TODO: Define a title for the content shown.
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

