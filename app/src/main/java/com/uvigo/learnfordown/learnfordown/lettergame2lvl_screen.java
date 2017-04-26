package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.LearnForDown.RecogeMonedas.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class lettergame2lvl_screen extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    private RecyclerView horizontal_recycler_view2;
    private ArrayList<String> horizontalList2;
    private HorizontalAdapter horizontalAdapter2;
    Button ButtonActual;
    Intent minijuego;
    AlertDialog dialog;
    String Nombre="";

    TextView titulo;
    String Correcta="";
    ImageButton BackArrow,Home;
    ImageView palabra;
    GestionNiveles  gn;
    TextView letracorrecta;
    String tipoNivel="leerletras",palabracom, tmpDownSlash= " ";
    ArrayList<FotoPalabra> fp;
    int i=0;
    int aciertos=0;

   /* int contador=0;
    RatingBar ratingbar1 = null;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
*/
    Estrellas es;
    AppCompatActivity app = this;
    private boolean  mBound= false;
    private  BluetoothService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame2lvl_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontal_recycler_view2= (RecyclerView) findViewById(R.id.horizontal_recycler_view2);
        titulo = (TextView) findViewById(R.id.textView2);
        Home = (ImageButton) findViewById(R.id.button5);
        palabra= (ImageView)findViewById(R.id.imageView2);
        letracorrecta=(TextView)findViewById(R.id.textView4);
        titulo.setTypeface(face);
        /*
        contador=0;
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(79, 6f); //79 aciertos, 6 estrellas
*/
        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context,this);
        es= new Estrellas (this,gn,gn.setNivel(tipoNivel,2));
        fp=gn.getFotos();


        horizontalList = new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList);
        Collections.shuffle( horizontalList);
        palabra.setImageResource(fp.get(i).getFoto());
        letracorrecta.setText(fp.get(i).getLetra().toUpperCase());
        Correcta= fp.get(i).getLetra().toUpperCase();
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

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        horizontalList2=new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList2);
        Collections.shuffle( horizontalList2);

        horizontalAdapter2 = new HorizontalAdapter(horizontalList2,5,metrics,"lectura");

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        horizontal_recycler_view2.setAdapter(horizontalAdapter2);

        Intent intent=new Intent(this,BluetoothService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

    }

    public void BackArrow (View v){
        Intent intent1 = new Intent(lettergame2lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(lettergame2lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void ButtonCheck (View v){
        Button b = (Button)v;
        ButtonActual =b;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                    -50.0f, 0.0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                if (Correcta.equals(ButtonActual.getText().toString())) {

                    ButtonActual.setBackgroundColor(Color.GREEN);
                    ButtonActual.setEnabled(false);
                    aciertos++;

                    palabracom = fp.get(i).getPalabra().toUpperCase().replaceAll(tmpDownSlash, ButtonActual.getText().toString());
                    letracorrecta.setText(palabracom);

                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (Correcta.equals(ButtonActual.getText().toString())) {
                    if (aciertos == 1) {
                        es.acierto();
                        gn.enviaResultado(fp.get(i).getPalabra());
                        MediaPlayer aciertoMedia = es.getAciertoMedia();
                        aciertoMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {

                                es.pulsar(true);
                                if (es.ratingbar1.getRating() == 6) {
                                    MensajeMinijuego();
                                }
                                System.out.println("Se ha anotado un acierto");
                                if (!gn.isnivelCompletado()) {
                                    i++;
                                    cambiarFoto();
                                } else {
                                    System.out.print("el nivel esta finalizado");
                                    avanzaNivel();
                                }
                                aciertos = 0;


                            }

                        });
                    }
                 } else {
                     es.fallo();
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
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(lettergame2lvl_screen.this, lettergame3lvl_screen.class);
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
            gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(), horizontalList);
            Collections.shuffle(horizontalList);

            horizontalList2.clear();
            horizontalList2 = new ArrayList<String>();
            gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(), horizontalList2);
            Collections.shuffle(horizontalList2);

            palabra.setImageResource(fp.get(i).getFoto());
            letracorrecta.setText(fp.get(i).getLetra().toUpperCase());
            Correcta = fp.get(i).getLetra().toUpperCase();
            palabra.setImageResource(fp.get(i).getFoto());
            tmpDownSlash = "";
            for (int i = 0; i < Correcta.length(); i++) {
                tmpDownSlash += " _";
            }
            palabracom = fp.get(i).getPalabra().toUpperCase();
            palabracom = palabracom.replaceAll(Correcta.toUpperCase().replaceAll("A", "Á").replaceAll("E", "É").replaceAll("I", "Í").replaceAll("O", "Ó").replaceAll("U", "Ú"), tmpDownSlash);
            palabracom = palabracom.replaceAll(Correcta.toUpperCase(), tmpDownSlash);
            letracorrecta.setText(palabracom);
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            horizontalAdapter = new HorizontalAdapter(horizontalList, 5, metrics, "lectura");
            horizontalAdapter2 = new HorizontalAdapter(horizontalList2, 5, metrics, "lectura");

            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

            LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);

            horizontal_recycler_view.setAdapter(horizontalAdapter);
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
