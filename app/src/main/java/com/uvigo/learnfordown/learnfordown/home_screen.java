package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class home_screen extends AppCompatActivity {

    private GoogleApiClient client;
    private   GestionNiveles gn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Eliminar la barra con el titulo de la aplicacion
        setContentView(R.layout.activity_home_screen);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //Prueba bd

        Context context =this.getApplicationContext();
        context.deleteDatabase("learn.sqlite");
        DataBaseManager db = new DataBaseManager(context);

        db.insertar_nivel (0,"leerletras",1,"p");
        db.insertar_nivel (1,"leerletras",2,"p");
        db.insertar_nivel (2,"leerletras",3,"p");
        db.insertar_nivel (4,"leerletras",1,"c");
        db.insertar_foto("c","ca","directas","casa","La casa es blanca",R.drawable.casa,"casa");
        db.insertar_foto("p","pe","directas","perro","El perro es marrón",R.drawable.perro,"animales");
        db.insertar_user("pepe",5);


        gn = new GestionNiveles(context);
        gn.setNivel("leerletras",1);
        ArrayList<FotoPalabra> fp=gn.getFotos();
        System.out.println(fp);
        System.out.println("Holhhha");
        for(int i=0;i<fp.size();i++){
           System.out.println(fp.get(i).getPalabra());
        }


    }

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, menu_screen.class);
        startActivity(intent);
    }



   /* public void sendMessage3(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

    public void sendMessage4(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }*/

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("home_screen Page") // TODO: Define a title for the content shown.
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
        gn.close();
    }
}