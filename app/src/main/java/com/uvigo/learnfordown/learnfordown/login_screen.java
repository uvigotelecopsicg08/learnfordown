package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class login_screen extends AppCompatActivity {
    TextView textoNombre, textoEdad;
    CheckBox playa, musica, coches, ropa, casas, animales;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        textoNombre = (TextView) findViewById(R.id.editTextNombre);
        textoEdad = (TextView) findViewById(R.id.editTextEdad);
      /*  playa= (CheckBox) findViewById(R.id.checkBoxPlaya);
        musica= (CheckBox) findViewById(R.id.checkBoxMusica);
        casas = (CheckBox) findViewById(R.id.checkBoxCasas);
        coches = (CheckBox) findViewById(R.id.checkBoxCoches);
        ropa = (CheckBox) findViewById(R.id.checkBoxRopa);
        animales = (CheckBox) findViewById(R.id.checkBoxAnimales);
        */
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void textNombreUp(View v) {
        textoNombre.setVisibility(View.VISIBLE);
    }

    void textEdadUp(View v) {
        textoEdad.setVisibility(View.VISIBLE);

    }

    /*
    void gustosCheckUp(View v){
        playa.setVisibility(View.VISIBLE);
        musica.setVisibility(View.VISIBLE);
        coches.setVisibility(View.VISIBLE);
        ropa.setVisibility(View.VISIBLE);
        animales.setVisibility(View.VISIBLE);

    }
*/
    void registar() {
        playa.isChecked();
        String nombre = (String) textoNombre.getText();
        String edadString = (String) textoNombre.getText();
        int edad = Integer.parseInt(edadString);
        System.out.println("  " + nombre + "   " + edad);


    }

    public void goHome(View v) {
        Intent intent1 = new Intent(login_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void BackArrow(View v) {
        Intent intent1 = new Intent(login_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void siguiente(View v) {
        Intent intent1 = new Intent(login_screen.this, login_screen_like.class);
        startActivity(intent1);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("login_screen Page") // TODO: Define a title for the content shown.
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
