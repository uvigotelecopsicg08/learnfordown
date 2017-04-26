package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.Log;
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
    boolean azureEnable=false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    TextView Titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        textoNombre = (TextView) findViewById(R.id.editTextNombre);
        textoEdad = (TextView) findViewById(R.id.editTextEdad);

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Intent intent=new Intent(this,BluetoothService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

    }

    void textNombreUp(View v) {
        textoNombre.setVisibility(View.VISIBLE);
    }

    void textEdadUp(View v) {
        textoEdad.setVisibility(View.VISIBLE);

    }


    public void registrar(View v) {
        SpannableStringBuilder nombreSpanable = (SpannableStringBuilder) textoNombre.getText();
        SpannableStringBuilder edadStringSpanable =  (SpannableStringBuilder) textoEdad.getText();
       String nombre= nombreSpanable.toString();
        String edadString=edadStringSpanable.toString();


        if(edadString.equals("")||nombre.equals("")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            // set title
            if(edadString.equals("")&&nombre.equals("")){
                alertDialogBuilder.setTitle("¡Eh! ¡Todavía no me has dicho quién eres!");
            }
            else{
                if(edadString.equals("")){
                    alertDialogBuilder.setTitle("¡Dime cuantos años tienes!");
                }
                else{
                    alertDialogBuilder.setTitle("¡Dime cómo te llamas!");
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
                Intent intent = new Intent(this, avatarScreen.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad",edad);
                intent.putExtra("azureEnable",azureEnable);
                if(azureEnable)
                    System.out.print("************");
                startActivity(intent);
            }
            catch (NumberFormatException e){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("¡Escribe tus años con números por favor!");
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

    public void setAzure(View view) {
        azureEnable=true;
        System.out.println("habilitado azure");
    }
    AppCompatActivity app = this;
    private boolean  mBound= false;
    private  BluetoothService mService;
    private ServiceConnection mConnection = new ServiceConnection() {



        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            Log.d("BINDER", "service="+service + " className" + className);
            BluetoothService.LocalBinder binder = (BluetoothService.LocalBinder) service;
            mService= binder.getService();
            mBound = true;
            mService.setApp(app);
            mService.setConnection();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
