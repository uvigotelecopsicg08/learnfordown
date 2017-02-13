package com.uvigo.learnfordown.learnfordown;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.TreeSet;

import static android.R.attr.radioButtonStyle;
import static android.R.attr.rating;

public class frasegame1lvl_screen extends AppCompatActivity {
    TextView titulo;
    RatingBar ratingbar1 = null;
    String figure = "plato";
    String button1 = "plano";
    String button2 = "plato";
    String button3 = "platano";
    int contador;
    final HashMap<Integer, Float> thresholds = new HashMap<>();

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
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        contador = 0;
        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(5, 2f); //5 aciertos, 2 estrellas
        thresholds.put(10, 3f); //10 aciertos, 3 estrellas
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
                    ratingbar1.setRating(rating);
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
                    ratingbar1.setRating(rating);
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
                    ratingbar1.setRating(rating);
                }
                break;
        }
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

