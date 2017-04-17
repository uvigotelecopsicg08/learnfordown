package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.util.ArrayList;

public class home_screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    private GoogleApiClient client;
    private   GestionNiveles gn;
    TextView titulo;
    Menu menu;

    Typeface face;
    boolean registrado=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Eliminar la barra con el titulo de la aplicacion
        setContentView(R.layout.activity_home_screen);
        face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView);

        titulo.setTypeface(face);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //Prueba bd
    //   getApplicationContext().deleteDatabase("learn.sqlite");
/*
        Context context =this.getApplicationContext();
        context.deleteDatabase("learn.sqlite");
        DataBaseManager db = new DataBaseManager(context);

        InsertData iD = new InsertData(context);
        iD.insertar();
        //db.insertar_user("pepe",5);
*/
        Context context =this.getApplicationContext();
        File dbFile = context.getDatabasePath("learn.sqlite");
        registrado = dbFile.exists();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActualizarDatosLateral();


    }

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        // Do something in response to button
        if(registrado) {
            Intent intent = new Intent(home_screen.this, menu_screen.class);
            startActivity(intent);
            //Aqui esta el codigo para lanzar el juego de Unity en version facil. Para la version dificil
            //cambiar el nombre del paquete por com.LearnForDown.RecogeMonedas
            /*Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.LearnForDown.RecogeMonedas11");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            }*/
        }
        else{
            lanzaAlerta();
        }
    }



    public void sendMessage2(View view) {
        // Do something in response to button
        if(registrado) {
            Intent intent = new Intent(home_screen.this, menu_write_screen.class);
            startActivity(intent);
        }

        else{
            lanzaAlerta();
        }
    }
    public void ajustes (View view){
        Intent intent1 = new Intent(home_screen.this, login_screen.class);
        startActivity(intent1);
    }

    public void salir (View view) {
        // Do something in response to button

        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void lanzaAlerta() {

        /* AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           alertDialogBuilder.setView(R.layout.dialog_recuperar_nivel);
        } else {
            alertDialogBuilder.setTitle("¡HOLA, PULSA EN EL ICONO VERDE PARA DECIRME CÓMO TE LLAMAS! ");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } */

        Intent intent1 = new Intent(home_screen.this, login_screen.class);
        startActivity(intent1);



    }
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


    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();

    }
    @Override
    public void onRestart() {
        super.onRestart();
        lanzaMensaje();
    }
    public void lanzaIntent(Nivel nivel){

            if(nivel.getTipo().equals("leerletras")) {
                if (nivel.dificultad == 1) {
                    Intent intent = new Intent(this, lettergame1lvl_screen.class);
                    startActivity(intent);
                }
                if (nivel.dificultad == 2) {
                    Intent intent = new Intent(this, lettergame2lvl_screen.class);
                    startActivity(intent);
                }
                if (nivel.dificultad == 3) {
                    Intent intent = new Intent(this, lettergame3lvl_screen.class);
                    startActivity(intent);
                }
                if (nivel.dificultad == 4) {
                    Intent intent = new Intent(this, lettergame4lvl_screen.class);
                    startActivity(intent);
                }
            }
            if(nivel.getTipo().equals("silabasdirectas")||nivel.getTipo().equals("silabasinversas")||nivel.getTipo().equals("silabastrabadas")){
                if(nivel.dificultad==1) {
                    Intent intent = new Intent(this, silabasgame1lvl_screen.class);
                    intent.putExtra("tipoSilaba", nivel.getTipo());
                    startActivity(intent);
                }
                if(nivel.dificultad==2) {
                    Intent intent = new Intent(this, silabasgame2lvl_screen.class);
                    intent.putExtra("tipoSilaba", nivel.getTipo());
                    startActivity(intent);
                }
                if(nivel.dificultad==3) {
                    Intent intent = new Intent(this, silabasgame3lvl_screen.class);
                    intent.putExtra("tipoSilaba", nivel.getTipo());
                    startActivity(intent);
                }
                if(nivel.dificultad==4) {
                    Intent intent = new Intent(this, silabasgame4lvl_screen.class);
                    intent.putExtra("tipoSilaba", nivel.getTipo());
                    startActivity(intent);
                }

              }
         if(nivel.getTipo().contains("palabras")){
            Intent intent = new Intent(this,palabrasgame1_2lvl_screen.class);
            intent.putExtra("tipoSilaba", nivel.getTipo());
            intent.putExtra("nivel", nivel.dificultad);
            startActivity(intent);
        }
        if(nivel.getTipo().contains("frase")){
            Intent intent = new Intent(this,frasegame1lvl_screen.class);
            intent.putExtra("tipoSilaba", nivel.getTipo());
            intent.putExtra("nivel", nivel.dificultad);
            startActivity(intent);
        }
        if(nivel.getTipo().equals("escribirletras")){
            Intent intent = new Intent(this,writegame_level1_screen.class);
            startActivity(intent);
        }
        if(nivel.getTipo().equals("escribirconsombreado")){
            Intent intent = new Intent(this,writegame_level2_screen.class);
            startActivity(intent);

        }
        if(nivel.getTipo().equals("escribirsinsombreado")){
            Intent intent = new Intent(this,writegame_level3_screen.class);
            startActivity(intent);

        }
        if(nivel.getTipo().equals("escribirtecladopalabra")){
            Intent intent = new Intent(this,writegame_level4_screen.class);
            startActivity(intent);

        }
    }
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater().inflate(R.menu.prueb, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.info) {
            // Handle the camera action
        } else if (id == R.id.cambiar) {
            Intent intent1 = new Intent(home_screen.this, listusers_screen.class);
            startActivity(intent1);

        } else if (id == R.id.registrar) {
            Intent intent1 = new Intent(home_screen.this, login_screen.class);
            startActivity(intent1);

        } else if (id == R.id.puzzle) {
            Intent intent1 = new Intent(home_screen.this, Puzzle4piezas.class);
            startActivity(intent1);

        } else if (id == R.id.plataformas) {


        }else if (id == R.id.parejasdificil) {
            Intent intent1 = new Intent(home_screen.this, ParejasDificil.class);
            startActivity(intent1);


        }else if (id == R.id.puzzledificil) {
            Intent intent1 = new Intent(home_screen.this, Puzzle9piezas.class);
            startActivity(intent1);


        }
        else if (id == R.id.parejas) {
            Intent intent1 = new Intent(home_screen.this, ParejasFacil.class);
            startActivity(intent1);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void lanzaMensaje(){

        if(registrado){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder.setView(R.layout.dialog_recuperar_nivel);
            }
            else {
                builder.setMessage("¿Quieres seguir por donde estabas la última vez?  ")
                        .setTitle("Recuperacion nivel");
            }
            builder.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    DataBaseManager db = new DataBaseManager(getApplicationContext());
                    lanzaIntent(db.getPrimerNivel());
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    public void ActualizarDatosLateral(){
        int id_user =0;
        String nombre ="";
        String avatar ="";
        Cursor cursor;
        int puzzle= -1;
        int memory = -1;
        int plataforma = -1;
        int puzzledificil= -1;
        int memorydificil = -1;
        int plataformadificil = -1;
        View header = navigationView.getHeaderView(0);
        menu=navigationView.getMenu();
        ImageView imagen = (ImageView) header.findViewById(R.id.imageView);
        TextView texto = (TextView) header.findViewById(R.id.textView);
        DataBaseManager db = new DataBaseManager(getApplicationContext());
        cursor= db.getInfoLogged();
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                 id_user = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                 avatar = cursor.getString(cursor.getColumnIndexOrThrow("avatar"));
                 nombre =  cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                texto.setText(nombre);
                texto.setTypeface(face);
                int resId=this.getResources().getIdentifier(avatar, "drawable", this.getPackageName());
                imagen.setImageResource(resId);
            }
        }
        cursor= db.getMinijuegos(id_user);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                 puzzle=cursor.getInt(cursor.getColumnIndexOrThrow("puzzle"));
                memory=cursor.getInt(cursor.getColumnIndexOrThrow("memory"));
                plataforma=cursor.getInt(cursor.getColumnIndexOrThrow("plataform"));
                puzzledificil=cursor.getInt(cursor.getColumnIndexOrThrow("puzzledificil"));
                memorydificil=cursor.getInt(cursor.getColumnIndexOrThrow("memorydificil"));
                plataformadificil=cursor.getInt(cursor.getColumnIndexOrThrow("plataformdificil"));            }
        }
        if (puzzle>0) {
            menu.findItem(R.id.puzzle).setEnabled(true);
        }
        if  (puzzledificil>0){
            menu.findItem(R.id.puzzledificil).setEnabled(true);

        }
        if (memory>0) {
            menu.findItem(R.id.parejas).setEnabled(true);
        }
        if  (memorydificil>0){
            menu.findItem(R.id.parejasdificil).setEnabled(true);

        }
        if (plataforma>0) {
            menu.findItem(R.id.plataformas).setEnabled(true);
        }
        if  (plataformadificil>0){
            menu.findItem(R.id.plataformasdificil).setEnabled(true);

        }
        }

}