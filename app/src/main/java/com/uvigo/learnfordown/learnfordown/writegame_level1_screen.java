package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.*;
import android.os.*;
import java.util.*;
import java.io.*;
import android.widget.*;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

import com.LearnForDown.RecogeMonedas.UnityPlayerActivity;
import com.uvigo.learnfordown.learnfordown.strokes.app.view.CanvasView;
import com.uvigo.learnfordown.learnfordown.timeseries.TimeSeries;
import com.uvigo.learnfordown.learnfordown.strokes.app.U;
import com.uvigo.learnfordown.learnfordown.strokes.app.datatype.Point2D;
import com.uvigo.learnfordown.learnfordown.dtw.FastDTW;
import com.uvigo.learnfordown.learnfordown.util.DistanceFunctionFactory;
import com.uvigo.learnfordown.learnfordown.gifView.*;


public class writegame_level1_screen extends AppCompatActivity {

    public static final float VALIDATION_THRESHOLD_MULTIPLIER = 1.8f;

    private static ImageButton Borrar; // Para GifView

    ImageButton Help;
    GifImageView gifImageView;
    LinearLayout Lienzo;
    ImageView plantilla,foto;
    CanvasView canvas;
    GestionNiveles  gn;
    String tipoNivel = "escribirletras";
    TextView Titulo;
    Intent minijuego;
    AlertDialog dialog;
    Context context;
    ArrayList<FotoPalabra> fp;

    Estrellas  es;
    final HashMap<Integer, Float> thresholds = new HashMap<>();


    Map <String, Integer> duracion = new HashMap <String, Integer>(); // Clave: letra, Valor: Duracion GIF

    public writegame_level1_screen() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {        // Inicializa la actividad

        super.onCreate(savedInstanceState);                           // Pasa el estado de la APP guardado en un "bundle" a la actividad para poder recrearla
        setContentView(R.layout.activity_writegame_level1_screen);    // Establece como layout la pantalla indicada

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        plantilla =(ImageView) findViewById(R.id.imageView3);
        foto = (ImageView) findViewById(R.id.imageView2);
        Borrar= (ImageButton) findViewById(R.id.button6);
        Help = (ImageButton) findViewById(R.id.button4);
        // GIF
        gifImageView = (GifImageView) findViewById(R.id.gifImageView);
        RellenaDuraciones();



        // Canvas
        Lienzo = (LinearLayout) findViewById(R.id.lienzo);
        canvas = new CanvasView(this);
        Lienzo.addView(canvas);


        context = this.getApplicationContext();

        gn = new GestionNiveles(context,this);
        gn.setNivel(tipoNivel,1);
        fp = gn.getFotos();
        es= new Estrellas (this,gn,gn.setNivel(tipoNivel,1));
        es.setRatingbar1(R.id.ratingBar);

        int resId = this.getResources().getIdentifier(fp.get(0).getLetra(), "drawable", this.getPackageName());
        plantilla.setImageResource(resId);
        foto.setImageResource(fp.get(0).getFoto());


    }



    public void resetCanvas(View v) {

        setContentView(R.layout.activity_writegame_level1_screen);
        es.setRatingbar1(R.id.ratingBar);
        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        plantilla =(ImageView) findViewById(R.id.imageView3);
        foto= (ImageView) findViewById(R.id.imageView2);
        Lienzo = (LinearLayout) findViewById(R.id.lienzo);
        canvas = new CanvasView(this);
        Lienzo.addView(canvas);
        // GIF
        gifImageView = (GifImageView) findViewById(R.id.gifImageView);

        int resId=this.getResources().getIdentifier(fp.get(0).getLetra(), "drawable", this.getPackageName());
        if (fp.get(0).getLetra().equals("ñ")) plantilla.setImageResource(R.drawable.nn);
        else   plantilla.setImageResource(resId);
        foto.setImageResource(fp.get(0).getFoto());

    }


    public void validateStrokes(View v) {

        String l;
        if (fp.get(0).getLetra().equals("ñ")) l = "nn";
        else l = fp.get(0).getLetra();
        int resIdRaw = this.getResources().getIdentifier(l, "raw", this.getPackageName());
        InputStream fraw =  getResources().openRawResource(resIdRaw);

        // ** Carga de ficheros ** //
        Patrones patronLetra = (Patrones) U.loadObjectFromFile(getApplicationContext(),  fp.get(0).getLetra(),fraw);
        //Patrones patronLetra = (Patrones) U.loadObjectFromFile(getApplicationContext(), fp.get(0).getLetra());

        ArrayList<LinkedList<Point2D>> loadedNormPointsSamples = patronLetra.getPuntosNormalizados();
        ArrayList<LinkedList<Float>> loadedAnglesSamples = patronLetra.getAngulosRadiales();
        ArrayList<Integer> numTrazos_patron = patronLetra.getNumeroTrazos();
        Double normPointValidationThreshold = patronLetra.getUmbralNormalizacion();
        Double angularValidationThreshold = patronLetra.getUmbralAngular();

        if (loadedAnglesSamples.equals(null) || loadedNormPointsSamples.equals(null) || numTrazos_patron.equals(null) ) // Si no se han captado puntos de validación
            Toast.makeText(getApplicationContext(), "Error cargando ficheros", Toast.LENGTH_LONG).show(); // Salta una ventana con dicho mensaje
        else {

            TimeSeries tsCanvasNormPoints = new TimeSeries(canvas.getNormalizedPoints());
            TimeSeries tsCanvasAngles = new TimeSeries(canvas.getAngles());

            // ** AÑADIDO **
            Integer numTrazos_muestra = canvas.getStrokeCount(); // Leemos el número de trazos de la escritura del usuario

            double avgNormPointDTWDistance = 0;
            double avgAngularDTWDistance = 0;

            int sampleCount = 0;

            // find average norm point dtw distance

            try {
                for (LinkedList<Point2D> normPointsSample : loadedNormPointsSamples) {
                    sampleCount++;
                    TimeSeries tsLoadedNormPointsSample = new TimeSeries(normPointsSample);

                    double normPointWarpDist = FastDTW.getWarpDistBetween(tsCanvasNormPoints, tsLoadedNormPointsSample,
                            DistanceFunctionFactory.getDistFnByName("EuclideanDistance"));

                    avgNormPointDTWDistance += normPointWarpDist;
                }


                avgNormPointDTWDistance = avgNormPointDTWDistance / sampleCount;

                sampleCount = 0;

                // find average angular dtw distance
                for (LinkedList<Float> loadedAnglesSample : loadedAnglesSamples) { // Recorre la el arraylist de listas
                    sampleCount++;
                    TimeSeries tsLoadedAnglesSample = new TimeSeries(loadedAnglesSample);
                    double anglesWarpDist = FastDTW.getWarpDistBetween(tsCanvasAngles, tsLoadedAnglesSample,
                            DistanceFunctionFactory.getDistFnByName("AngularDistance"));
                    avgAngularDTWDistance += anglesWarpDist;
                }
                avgAngularDTWDistance = avgAngularDTWDistance / sampleCount;

                String normPointValidationStatus = "failed";
                String angularValidationStatus = "failed";
                String estadoValidacionTrazos = "failed"; // ** AÑADIDO **

                if (avgNormPointDTWDistance < VALIDATION_THRESHOLD_MULTIPLIER * normPointValidationThreshold)
                    normPointValidationStatus = "passed";
                if (avgAngularDTWDistance < VALIDATION_THRESHOLD_MULTIPLIER * angularValidationThreshold)
                    angularValidationStatus = "passed";

                // ** AÑADIDO **
                for (int j = 0; j < numTrazos_patron.size(); j++) {
                    if (numTrazos_muestra.equals(numTrazos_patron.get(j)))
                        estadoValidacionTrazos = "passed";
                }

                if (normPointValidationStatus == "passed" && angularValidationStatus == "passed" && estadoValidacionTrazos == "passed") {


                    es.acierto();

                    MediaPlayer aciertoMedia = es.getAciertoMedia();
                    aciertoMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {


                    es.pulsar(true);
                    //Toast.makeText(this, "LETRA " + fp.get(0).getLetra().toUpperCase(), Toast.LENGTH_SHORT).show();
                            if (es.ratingbar1.getRating()==6){
                                MensajeMinijuego();
                            }

                    gn.avanzaNivel();
                    if(!(gn.getTipo().equals(tipoNivel))) {
                        // Terminó el nivel
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(writegame_level1_screen.this, writegame_level2_screen.class);
                                startActivity(intent);
                            }
                        }, 2000);

                    }

                    cambiarFoto();
                        }

                });

                }else{
                    //Toast.makeText(this, "VUELVE A INTENTARLO", Toast.LENGTH_SHORT).show();
                    es.fallo();
                    Borrar.callOnClick();
                }



                // ** AÑADIDA LA ÚLTIMA FRASE **
                //Toast.makeText(this, "Normalized points validation = " + normPointValidationStatus + "\n" + "Angular validation = " + angularValidationStatus + "\n" + "Validacion de trazos = " + estadoValidacionTrazos,
                //Toast.LENGTH_SHORT).show();

            }catch (Exception e){ /* Caso de que le de a aceptar pero no escriba nada */ }

        }
    }


    public void trainStrokes(View v) {
        String l;
        Intent intent = new Intent(this, StrokesTrainingActivity.class);
        if (fp.get(0).getLetra().equals("ñ"))  l = "nn";
        else l = fp.get(0).getLetra();
        intent.putExtra("fichero", l);
        startActivity(intent);
    }


    //Funciones que habrá que descomentar cuando se integre en la APP LearnForDown

    public void BackArrow (View v){
        Intent intent1 = new Intent(writegame_level1_screen.this, menu_write_screen.class);
        startActivity(intent1);
        //Toast.makeText(this, "ATRÁS", Toast.LENGTH_SHORT).show();
    }

    public void goHome (View v){
        Intent intent1 = new Intent(writegame_level1_screen.this, home_screen.class);
        startActivity(intent1);
        //Toast.makeText(this, "MENÚ PRINCIPAL", Toast.LENGTH_SHORT).show();
    }

    public ImageButton getBorrar() {
        return Borrar;
    }

    public void showHelp (View v){

        // Pantalla sin Canvas, no quiero que me deje escribir mientras se reproduce el GIF
        setContentView(R.layout.activity_writegame_level1_screen);
        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);
        es.setRatingbar1(R.id.ratingBar);
        foto = (ImageView) findViewById(R.id.imageView2);
        foto.setImageResource(fp.get(0).getFoto());


        // ********************
        try {

            int resIdRaw = this.getResources().getIdentifier(fp.get(0).getLetra()+"_gif", "raw", this.getPackageName());
            InputStream is =  getResources().openRawResource(resIdRaw);
            //InputStream is = getResources().openRawResource(R.raw.a_gif);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            is.close();

            gifImageView = new GifImageView(this);
            gifImageView = (GifImageView) findViewById(R.id.gifImageView);

            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Borrar.performClick();
                }
            }, duracion.get(fp.get(0).getLetra()+"_gif"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        // *********************



    }



    public void reset(View v){

        es.resetPanelEstrellas();
        fp=gn.getFotos();
        cambiarFoto();
    }

    public void cambiarFoto(){

        setContentView(R.layout.activity_writegame_level1_screen);
        es.setRatingbar1(R.id.ratingBar);

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);
        plantilla =(ImageView) findViewById(R.id.imageView3);
        foto= (ImageView) findViewById(R.id.imageView2);
        Lienzo = (LinearLayout) findViewById(R.id.lienzo);
        canvas = new CanvasView(this);
        Lienzo.addView(canvas);

        fp=gn.getFotos();
        String f;
        if(fp.get(0).getLetra().equals("ñ")) f = "nn";
        else f = fp.get(0).getLetra();
        int resId=this.getResources().getIdentifier(f, "drawable", this.getPackageName());
        plantilla.setImageResource(resId);
        foto.setImageResource(fp.get(0).getFoto());
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
                    // User cancelled the dialog
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
        String Nombre="";
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
        dialog.dismiss();
    }


    public void RellenaDuraciones(){
        duracion.put("a_gif", 8000);
        duracion.put("i_gif", 6000);
        duracion.put("e_gif", 9000);
        duracion.put("o_gif", 5000);
        duracion.put("u_gif", 6000);
    }
}
