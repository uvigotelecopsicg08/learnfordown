package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
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
    public void registrar(View v) {
        SpannableStringBuilder nombreSpanable = (SpannableStringBuilder) textoNombre.getText();
        SpannableStringBuilder edadStringSpanable =  (SpannableStringBuilder) textoEdad.getText();
       String nombre= nombreSpanable.toString();
        String edadString=edadStringSpanable.toString();


        if(edadString.equals("")||nombre.equals("")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            // set title
            if(edadString.equals("")&&nombre.equals("")){
                alertDialogBuilder.setTitle("Introduce nombre y edad, por favor");
            }
            else{
                if(edadString.equals("")){
                    alertDialogBuilder.setTitle("Introduce edad, por favor");
                }
                else{
                    alertDialogBuilder.setTitle("Introduce nombre, por favor");
                }
            }


            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        else{
            System.out.println(" Nombre " + nombre + "  edad " + edadString);
            try{
                int edad = Integer.parseInt(edadString);
                Intent intent = new Intent(this, login_screen_like.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad",edad);
                startActivity(intent);
            }
            catch (NumberFormatException e){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Introduce un valor numerico para la edad");
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }



    }

    public void goHome(View v) {
        Intent intent1 = new Intent(login_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void BackArrow(View v) {
        Intent intent1 = new Intent(login_screen.this, home_screen.class);
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
