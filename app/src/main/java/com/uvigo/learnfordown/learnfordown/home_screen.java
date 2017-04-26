package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.ComponentName;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.LearnForDown.RecogeMonedas.UnityPlayerActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.File;
import java.util.ArrayList;

public class home_screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // *** VARIABLES NECESARIAS BLUETOOTH ****

    final int handlerState = 0;
    private String MAC_LFD = null;
    BluetoothSocket BS = null;
    Handler bluetoothIn;
    BluetoothConnection BT;
    private boolean mBound=false;

    // *************************************

    NavigationView navigationView;
    private GoogleApiClient client;
    private   GestionNiveles gn;
    TextView titulo;
    Menu menu;
    File dbFile;
    int id_user = 0;
    DrawerLayout menulateral;
    Typeface face;
    boolean registrado = false;
    BluetoothService mService;
    private StringBuilder recDataString = new StringBuilder();
    ImageButton Exit;
    AppCompatActivity app=this;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Eliminar la barra con el titulo de la aplicacion
        setContentView(R.layout.activity_home_screen);

        // Botones

        Exit = (ImageButton) findViewById(R.id.imagebutton7);

        // ******
        face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView);
        menulateral = (DrawerLayout) findViewById(R.id.drawer_layout);
        titulo.setTypeface(face);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        Context context = this.getApplicationContext();
        dbFile = context.getDatabasePath("learn.sqlite");
        registrado = dbFile.exists();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (dbFile.exists()) {
            ActualizarDatosLateral();
        }

        Intent intent=new Intent(this,BluetoothService.class);
        bindService(intent,mConnection,Context.BIND_AUTO_CREATE);



// ************************************





    //    BT = new BluetoothConnection(this.getApplicationContext(),bluetoothIn);
// ***********************************

    }

    public void Bluetooth(View V) {
/*
        if (!BT.configurarBluetooth()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, BT.getREQUEST_ENABLE_BT());
        }
        BT.obtenerListaDispositivos();
        MAC_LFD = BT.buscaDispositivo();
        if (MAC_LFD == null)
            Toast.makeText(getBaseContext(), "No se ha encontrado el dispositivo", Toast.LENGTH_LONG).show();
        else BT.conectarBluetooth();


 */

        if(mBound){
         //   mService.setApp(this);
            mService.setConnection();
            mService.setBluetoothEnable(true);
        }

    }


    public void analizarEntradaBT(String entrada){

        switch (entrada){
            case "exit":
                Exit.performClick();
                break;

        }

    }


    public void sendMessage(View view) {

        if (registrado) {

            Intent intent = new Intent(home_screen.this, menu_screen.class);
            startActivity(intent);
        } else lanzaAlerta();
    }


    public void sendMessage2(View view) {

        if (registrado) {

            Intent intent = new Intent(home_screen.this, menu_write_screen.class);
            startActivity(intent);
        } else lanzaAlerta();
    }

    public void ajustes(View view) {

        Intent intent1 = new Intent(home_screen.this, login_screen.class);
        startActivity(intent1);
    }

    public void salir(View view) {

      //  BT.cerrarSocketBT();


        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void lanzaAlerta() {

        Intent intent1 = new Intent(home_screen.this, login_screen.class);
        startActivity(intent1);
    }


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
        Intent intent=new Intent(this,BluetoothService.class);
        bindService(intent,mConnection,Context.BIND_AUTO_CREATE);
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

        if (mService != null) {
            stopService(new Intent(this, BluetoothService.class));
            unbindService(mConnection);
            mBound = false;
          //  mService = null;
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();

        if (dbFile.exists()) {
            ActualizarDatosLateral();
        }
        lanzaMensaje();
    }

    public void lanzaIntent(Nivel nivel) {



        if (nivel.getTipo().equals("leerletras")) {
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
        if (nivel.getTipo().equals("silabasdirectas") || nivel.getTipo().equals("silabasinversas") || nivel.getTipo().equals("silabastrabadas")) {
            if (nivel.dificultad == 1) {
                Intent intent = new Intent(this, silabasgame1lvl_screen.class);
                intent.putExtra("tipoSilaba", nivel.getTipo());
                startActivity(intent);
            }
            if (nivel.dificultad == 2) {
                Intent intent = new Intent(this, silabasgame2lvl_screen.class);
                intent.putExtra("tipoSilaba", nivel.getTipo());
                startActivity(intent);
            }
            if (nivel.dificultad == 3) {
                Intent intent = new Intent(this, silabasgame3lvl_screen.class);
                intent.putExtra("tipoSilaba", nivel.getTipo());
                startActivity(intent);
            }
            if (nivel.dificultad == 4) {
                Intent intent = new Intent(this, silabasgame4lvl_screen.class);
                intent.putExtra("tipoSilaba", nivel.getTipo());
                startActivity(intent);
            }

        }
        if (nivel.getTipo().contains("palabras")) {
            Intent intent = new Intent(this, palabrasgame1_2lvl_screen.class);
            intent.putExtra("tipoSilaba", nivel.getTipo());
            intent.putExtra("nivel", nivel.dificultad);
            startActivity(intent);
        }
        if (nivel.getTipo().contains("frase")) {
            Intent intent = new Intent(this, frasegame1lvl_screen.class);
            intent.putExtra("tipoSilaba", nivel.getTipo());
            intent.putExtra("nivel", nivel.dificultad);
            startActivity(intent);
        }
        if (nivel.getTipo().equals("escribirletras")) {
            Intent intent = new Intent(this, writegame_level1_screen.class);
            startActivity(intent);
        }
        if (nivel.getTipo().equals("escribirconsombreado")) {
            Intent intent = new Intent(this, writegame_level2_screen.class);
            startActivity(intent);

        }
        if (nivel.getTipo().equals("escribirsinsombreado")) {
            Intent intent = new Intent(this, writegame_level3_screen.class);
            startActivity(intent);

        }
        if (nivel.getTipo().equals("escribirtecladopalabra")) {
            Intent intent = new Intent(this, writegame_level4_screen.class);
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

            DataBaseManager db = new DataBaseManager(getApplicationContext());

            db.updateMinijuego(id_user, "PUZZLE", "resta");
            Intent intent1 = new Intent(home_screen.this, Puzzle4piezas.class);
            startActivity(intent1);

        } else if (id == R.id.plataformas) {

            DataBaseManager db = new DataBaseManager(getApplicationContext());

            //   db.updateMinijuego(id_user,"PLATAFORMA","resta");
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.LearnForDown.RecogeMonedas");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            }

        } else if (id == R.id.plataformasdificil) {
            DataBaseManager db = new DataBaseManager(getApplicationContext());

            db.updateMinijuego(id_user, "PLATAFORMADIFICIL", "resta");


        } else if (id == R.id.parejasdificil) {
            DataBaseManager db = new DataBaseManager(getApplicationContext());

            db.updateMinijuego(id_user, "PAREJASDIFICIL", "resta");

            Intent intent1 = new Intent(home_screen.this, ParejasDificil.class);
            startActivity(intent1);


        } else if (id == R.id.puzzledificil) {
            DataBaseManager db = new DataBaseManager(getApplicationContext());

            db.updateMinijuego(id_user, "PUZZLEDIFICIL", "resta");

            Intent intent1 = new Intent(home_screen.this, Puzzle9piezas.class);
            startActivity(intent1);


        } else if (id == R.id.parejas) {
            DataBaseManager db = new DataBaseManager(getApplicationContext());

            db.updateMinijuego(id_user, "PAREJAS", "resta");

            Intent intent1 = new Intent(home_screen.this, ParejasFacil.class);
            startActivity(intent1);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void lanzaMensaje() {

        if (registrado) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder.setView(R.layout.dialog_recuperar_nivel);
            } else {
                builder.setMessage("Â¿Quieres seguir por donde estabas la Ãºltima vez?  ")
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
                    // Usuarios cancelled the dialog
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void ActualizarDatosLateral() {
        String nombre = "";
        String avatar = "";
        Cursor cursor;
        int puzzle = -1;
        int memory = -1;
        int plataforma = -1;
        int puzzledificil = -1;
        int memorydificil = -1;
        int plataformadificil = -1;
        View header = navigationView.getHeaderView(0);
        menu = navigationView.getMenu();

        ImageView imagen = (ImageView) header.findViewById(R.id.imageView);
        TextView texto = (TextView) header.findViewById(R.id.textView);
        DataBaseManager db = new DataBaseManager(getApplicationContext());
        cursor = db.getInfoLogged();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                id_user = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                avatar = cursor.getString(cursor.getColumnIndexOrThrow("avatar"));
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                texto.setText(nombre);
                texto.setTypeface(face);
                int resId = this.getResources().getIdentifier(avatar, "drawable", this.getPackageName());
                imagen.setImageResource(resId);
            }
        }
        cursor = db.getMinijuegos(id_user);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                puzzle = cursor.getInt(cursor.getColumnIndexOrThrow("puzzle"));
                memory = cursor.getInt(cursor.getColumnIndexOrThrow("memory"));
                plataforma = cursor.getInt(cursor.getColumnIndexOrThrow("plataform"));
                puzzledificil = cursor.getInt(cursor.getColumnIndexOrThrow("puzzledificil"));
                memorydificil = cursor.getInt(cursor.getColumnIndexOrThrow("memorydificil"));
                plataformadificil = cursor.getInt(cursor.getColumnIndexOrThrow("plataformdificil"));
            }
        }
        if (puzzle > 0) {
            menu.findItem(R.id.puzzle).setEnabled(true);
        } else menu.findItem(R.id.puzzle).setEnabled(false);

        if (puzzledificil > 0) {
            menu.findItem(R.id.puzzledificil).setEnabled(true);

        } else menu.findItem(R.id.puzzledificil).setEnabled(false);
        if (memory > 0) {
            menu.findItem(R.id.parejas).setEnabled(true);
        } else menu.findItem(R.id.parejas).setEnabled(false);
        if (memorydificil > 0) {
            menu.findItem(R.id.parejasdificil).setEnabled(false);

        } else menu.findItem(R.id.parejasdificil).setEnabled(false);
        if (plataforma > 0) {
            menu.findItem(R.id.plataformas).setEnabled(false);
        } else menu.findItem(R.id.plataformas).setEnabled(false);
        if (plataformadificil > 0) {
            menu.findItem(R.id.plataformasdificil).setEnabled(true);

        } else menu.findItem(R.id.plataformasdificil).setEnabled(false);
    }

    public void FlechaMenu(View v) {
        menulateral.openDrawer(Gravity.LEFT);
    }
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
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


}






