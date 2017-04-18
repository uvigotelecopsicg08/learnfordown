package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.LearnForDown.RecogeMonedas.UnityPlayerActivity;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.TreeSet;

public class silabasgame2lvl_screen extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    private RecyclerView horizontal_recycler_view2;
    private ArrayList<String> horizontalList2;
    private HorizontalAdapter horizontalAdapter2;
    Button ButtonActual;
    TextView titulo,letracorrecta;
    RatingBar ratingbar1;
    String Correcta,palabracom, tmpDownSlash= " ";
    ImageButton BackArrow,Home;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
    Intent minijuego;
    AlertDialog dialog;
    String Nombre="";

    ImageView palabra;
    GestionNiveles  gn;
    String tipoNivel="silabasdirectas";
    ArrayList<FotoPalabra> fp;

    int i=0;
    /*
    int contador;
    int aciertos=0;
    */
    Estrellas es;
    public Estrellas getEs() {
        return es;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silabasgame2lvl_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tipoNivel = extras.getString("tipoSilaba");
            System.out.println(tipoNivel);
        }
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontal_recycler_view2= (RecyclerView) findViewById(R.id.horizontal_recycler_view2);
        titulo = (TextView) findViewById(R.id.textView2);
        Home = (ImageButton) findViewById(R.id.button5);
        palabra= (ImageView)findViewById(R.id.imageView2);
        letracorrecta=(TextView)findViewById(R.id.textView4);
        titulo.setTypeface(face);


        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context,this);
       es =new Estrellas(this,gn, gn.setNivel(tipoNivel,2));
        fp=gn.getFotos();
        horizontalList=new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(),horizontalList);
        Collections.shuffle( horizontalList);
        letracorrecta.setText(fp.get(i).getSilaba().toUpperCase());
        palabra.setImageResource(fp.get(i).getFoto());
        Correcta= fp.get(i).getSilaba().toUpperCase();
        tmpDownSlash = "";
        for (int i=0;i<Correcta.length();i++){
            tmpDownSlash += " _";
        }


        palabracom = fp.get(i).getPalabra().toUpperCase();
        palabracom = palabracom.replaceAll(Correcta.toUpperCase().replaceAll("A","Á").replaceAll("E","É").replaceAll("I","Í").replaceAll("O","Ó").replaceAll("U","Ú"), tmpDownSlash);
        palabracom = palabracom.replaceAll(Correcta.toUpperCase(), tmpDownSlash);
        letracorrecta.setText(palabracom);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        horizontalAdapter = new HorizontalAdapter(horizontalList,5,metrics,"lectura");
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(silabasgame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        horizontalList2=new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(),horizontalList2);
        Collections.shuffle( horizontalList2);
        horizontalAdapter2 = new HorizontalAdapter(horizontalList2,5,metrics,"lectura");

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(silabasgame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);





        horizontal_recycler_view.setAdapter(horizontalAdapter);


        horizontal_recycler_view2.setAdapter(horizontalAdapter2);
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent();
        switch (gn.getTipo()) {
            case "silabasdirectas":
                intent1 = new Intent(silabasgame2lvl_screen.this, sidirectas_screen.class);
                break;
            case "silabasinversas":
                intent1 = new Intent(silabasgame2lvl_screen.this, siinversas_screen.class);
                break;
            case "silabastrabadas":
                intent1 = new Intent(silabasgame2lvl_screen.this, sitrabadas_screen.class);
                break;
        }
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(silabasgame2lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void ButtonCheck (View v){
        Button b = (Button)v;
        ButtonActual =b;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                -50.0f, 0.0f);
        animation.setDuration(400);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {

                    ButtonActual.setBackgroundColor(Color.GREEN);
                    ButtonActual.setEnabled(false);
                    palabracom=fp.get(i).getPalabra().toUpperCase().replaceAll(tmpDownSlash,ButtonActual.getText().toString());
                    letracorrecta.setText(palabracom);
                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (Correcta.equals(ButtonActual.getText().toString())) {
                    System.out.println("Se ha anotado un acierto");
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

                            if (!gn.isnivelCompletado()) {
                        i++;
                        cambiarFoto();
                    } else {
                        System.out.print("el nivel esta finalizado");
                        avanzaNivel();

                    }

                }

            });

                } else {
                    es.fallo();
                    //Codigo de Animacion Fallo

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        b.startAnimation(animation);
    }

    private void avanzaNivel() {
        gn.avanzaNivel();
        if (gn.getDificultad() != 2 || !(gn.getTipo().equals(tipoNivel))) {
            System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
            //Código para abrir otra pantalla
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(silabasgame2lvl_screen.this, silabasgame3lvl_screen.class);
                    startActivity(intent);
                }
            }, 2000);


        } else {
            fp = gn.getFotos();
            i = 0;
            cambiarFoto();
            System.out.println("Se debe avanzar el nivel");
        }
    }

    private void cambiarFoto() {
        try {
            horizontalList.clear();
            horizontalList = new ArrayList<String>();
            gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(), horizontalList);
            Collections.shuffle(horizontalList);
            palabra.setImageResource(fp.get(i).getFoto());
            letracorrecta.setText(fp.get(i).getSilaba().toUpperCase());
            Correcta = fp.get(i).getSilaba().toUpperCase();
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            horizontalAdapter = new HorizontalAdapter(horizontalList, 5, metrics, "lectura");
            tmpDownSlash = "";
            for (int i = 0; i < Correcta.length(); i++) {
                tmpDownSlash += " _";
            }

            palabracom = fp.get(i).getPalabra().toUpperCase();
            palabracom = palabracom.replaceAll(Correcta.toUpperCase().replaceAll("A", "Á").replaceAll("E", "É").replaceAll("I", "Í").replaceAll("O", "Ó").replaceAll("U", "Ú"), tmpDownSlash);
            palabracom = palabracom.replaceAll(Correcta.toUpperCase(), tmpDownSlash);
            letracorrecta.setText(palabracom);

            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(silabasgame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

            horizontal_recycler_view.setAdapter(horizontalAdapter);
            horizontalList2.clear();
            horizontalList2 = new ArrayList<String>();
            gn.rellenarConletras(fp.get(i).getSilaba().toUpperCase(), horizontalList2);
            Collections.shuffle(horizontalList2);
            horizontalAdapter2 = new HorizontalAdapter(horizontalList2, 5, metrics, "lectura");

            LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(silabasgame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);
            horizontal_recycler_view2.setAdapter(horizontalAdapter2);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            avanzaNivel();

        }
    }
    public void reset(View v){
        i=0;
        es.resetPanelEstrellas();
        fp=gn.getFotos();
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
}

