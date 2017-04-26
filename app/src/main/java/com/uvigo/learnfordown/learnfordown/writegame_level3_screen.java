package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.ServiceConnection;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
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

import com.LearnForDown.RecogeMonedas.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;


public class writegame_level3_screen extends AppCompatActivity {

    private RecyclerView PanelHorizontal;
    private HorizontalAdapter HorizontalAdapter;
    private ArrayList<String> ListaHorizontal;
    private char [] LetrasPalabra;
    String Nombre="";

    private GestionNiveles  gn;
    private ArrayList <FotoPalabra> fp;

    private TextView Titulo, Frase;
    private ImageView Foto;
    private String TipoNivel,Correcta;
    private int i = 0;
    private int j = 0;
    private Button ButtonActual;
    private String RellenoFrase;
    private int num_iteracion = 0;
    Intent minijuego;
    AlertDialog dialog;

    Estrellas  es;


    final HashMap<Integer, Float> thresholds = new HashMap<>();
    private Button botonReferencia;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writegame_level3_screen);

        PanelHorizontal = (RecyclerView) findViewById(R.id.PanelHorizontal);

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        Foto = (ImageView)findViewById(R.id.imageView2);
        Frase =(TextView)findViewById(R.id.textView4);


        TipoNivel = "escribirsinsombreado"; // Esto tiene que cambiarse cada n iteraciones -> IMPORTANTE
        Context context = this.getApplicationContext();


        gn = new GestionNiveles(context,this);
        gn.setNivel(TipoNivel,1);
        fp = gn.getFotosAleatorias();
        es= new Estrellas (this,gn,gn.setNivel(TipoNivel,3));

        RellenoFrase = fp.get(i).getFrase().toUpperCase();
        Correcta = fp.get(i).getPalabra().toUpperCase();
        Foto.setImageResource(fp.get(i).getFoto());
        Rellenar();



        CompletaLista();

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(writegame_level3_screen.this, LinearLayoutManager.HORIZONTAL, false);
        PanelHorizontal.setLayoutManager(horizontalLayoutManager);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        HorizontalAdapter = new HorizontalAdapter(ListaHorizontal,ListaHorizontal.size(),metrics,"escritura");
        PanelHorizontal.setAdapter(HorizontalAdapter);

        Intent intent=new Intent(this,BluetoothService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);



    }


    public void BackArrow (View v){
        Intent intent1 = new Intent(writegame_level3_screen.this, menu_write_screen.class);
        startActivity(intent1);
    }

    public void goHome (View v){
        Intent intent1 = new Intent(writegame_level3_screen.this, home_screen.class);
        startActivity(intent1);
    }


    public void ButtonCheck (View v){

        Button b = (Button)v;
        ButtonActual = b;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, -50.0f, 0.0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {


                // ¿El botón se ha pulsado ya?
                boolean pulsado;

                try {

                    ColorDrawable buttonColor = (ColorDrawable)  ButtonActual.getBackground();
                    buttonColor.getColor();
                    if (buttonColor.getColor() == Color.GREEN) {
                        pulsado = true;
                    }
                    else pulsado = false; // No esta pulsado

                } catch(Exception e){
                    pulsado = false; // No esta pulsado
                }

                if (String.valueOf(LetrasPalabra[num_iteracion]).equals(ButtonActual.getText().toString()) && pulsado == false) {
                    SustituirLinea();
                    ButtonActual.setBackgroundColor(Color.GREEN);
                    num_iteracion++;

                    if(num_iteracion == Correcta.length()) {

                                RespuestaCorrecta();

                    }

                } else {
                    if (!String.valueOf(LetrasPalabra[num_iteracion]).equals(ButtonActual.getText().toString()))
                        es.fallo();

                }

                PanelHorizontal.setEnabled(true);

            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }


            @Override
            public void onAnimationRepeat(Animation animation) {}

        });

        b.startAnimation(animation);

    }

    private void cambiarFoto() {

        Correcta = fp.get(i).getPalabra().toUpperCase();
        Foto.setImageResource(fp.get(i).getFoto());
        RellenoFrase = fp.get(i).getFrase().toUpperCase();
        Rellenar();
        CompletaLista();
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(writegame_level3_screen.this, LinearLayoutManager.HORIZONTAL, false);
        PanelHorizontal.setLayoutManager(horizontalLayoutManager);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        HorizontalAdapter = new HorizontalAdapter(ListaHorizontal,ListaHorizontal.size(),metrics,"escritura");

        PanelHorizontal.setAdapter(HorizontalAdapter);
        num_iteracion=0;

    }

    public void Rellenar (){

        String Relleno = "_";
        int Longitud = Correcta.length();
        while (Relleno.length() != Longitud){
            Relleno = Relleno.concat(" _");
            Longitud ++; // Debido a que concatenamos un espacio + _
        }

        if (RellenoFrase.contains("*"))RellenoFrase = RellenoFrase.replace("*"," "+Relleno);
        Frase.setText(RellenoFrase);
    }

    public void CompletaLista(){

        ListaHorizontal = new ArrayList <String> ();
        LetrasPalabra = new char[Correcta.length()];
        Correcta.getChars(0,Correcta.length(),LetrasPalabra,0);
        for(int n=0; n < (LetrasPalabra.length);n++){
            ListaHorizontal.add(String.valueOf(LetrasPalabra[n]));
            Collections.shuffle(ListaHorizontal);
        }


    }


    public void SustituirLinea(){

        if (j == 0) RellenoFrase = RellenoFrase.replaceFirst("_",String.valueOf(LetrasPalabra[num_iteracion]));
        else RellenoFrase = RellenoFrase.replaceFirst(" _",String.valueOf(LetrasPalabra[num_iteracion]));
        Frase.setText(RellenoFrase);
        j++;

    }


    public void RespuestaCorrecta(){
        es.acierto();
        gn.enviaResultado(fp.get(i).getPalabra());
        MediaPlayer aciertoMedia = es.getAciertoMedia();
        aciertoMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                es.pulsar(true);
                if (es.ratingbar1.getRating()==6){
                    MensajeMinijuego();
                }

                if (!gn.isnivelCompletado()) { // Aún no terminó el nivel
                 i++;
                cambiarFoto();

                } else {

                gn.avanzaNivel();
                if (gn.getDificultad() != 1 || !(gn.getTipo().equals(TipoNivel))) {

                //Código para abrir otra pantalla
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(writegame_level3_screen.this, writegame_level4_screen.class);
                            startActivity(intent);
                        }
                    }, 2000);

                } else {
                fp = gn.getFotosAleatorias();
                i = 0;
                cambiarFoto();

            }
        }
            }
        });
    }

    public void reset(View v){
        i=0;
        es.resetPanelEstrellas();
        fp=gn.getFotosAleatorias();
        cambiarFoto();
    }
    public void MensajeMinijuego(){
        String Minijuego;
        Minijuego=MinijuegoRandom();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            builder.setView(R.layout.dialogominijuegos);
        }
        else {
            builder.setMessage("¿QUIERES JUGAR AHORA O MAS TARDE?")
                    .setTitle("¡TIENES UN MINIJUEGO NUEVO!  " + Minijuego);

            builder.setPositiveButton("¡LO QUIERO AHORA!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(minijuego);
                }
            });
            builder.setNegativeButton("¡LO QUIERO MAS TARDE!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Usuarios cancelled the dialog
                }
            });
        }
        dialog = builder.create();
        dialog.show();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");

            TextView Titulo =(TextView)dialog.findViewById(R.id.textView7);
            Titulo.setText("¡TIENES UN MINIJUEGO NUEVO!  " + Minijuego);
            Titulo.setTypeface(face);
            TextView mensaje =(TextView)dialog.findViewById(R.id.textView8);
            mensaje.setTypeface(face);
            Button positivo =(Button)dialog.findViewById(R.id.button11);
            Button negativo =(Button)dialog.findViewById(R.id.button12);

        }



    }
    public String MinijuegoRandom(){
        int rand =(int) (Math.random() * 2.0);
        switch(rand) {
            case 0:
                minijuego = new Intent(getApplicationContext(),Puzzle4piezas.class);
                Nombre= "PUZZLE";
                break;
            case 1:
                minijuego = new Intent(getApplicationContext(),ParejasFacil.class);
                Nombre= "PAREJAS";
                break;

            case 2:
                minijuego = new Intent(getApplicationContext(),UnityPlayerActivity.class);
                Nombre= "PLATAFORMAS";
                break;

        }
        return Nombre;
    }
    public void DialogPositive(View v){
        startActivity(minijuego);
        dialog.dismiss();
    }
    public void DialogNegative(View v){
//Codigo de meter en la base de datos
        DataBaseManager db =  new DataBaseManager(getApplicationContext());

        db.updateMinijuego(gn.getId_user(),Nombre,"suma");
        dialog.dismiss();
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

