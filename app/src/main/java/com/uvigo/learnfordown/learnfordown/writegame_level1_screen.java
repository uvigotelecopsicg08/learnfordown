package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import com.uvigo.learnfordown.learnfordown.strokes.app.view.CanvasView;
import com.uvigo.learnfordown.learnfordown.timeseries.TimeSeries;
import com.uvigo.learnfordown.learnfordown.strokes.app.U;
import com.uvigo.learnfordown.learnfordown.strokes.app.datatype.Point2D;
import com.uvigo.learnfordown.learnfordown.dtw.FastDTW;
import com.uvigo.learnfordown.learnfordown.util.DistanceFunctionFactory;

import static com.uvigo.learnfordown.learnfordown.R.drawable.f;

public class writegame_level1_screen extends AppCompatActivity {

    public static final float VALIDATION_THRESHOLD_MULTIPLIER = 1.8f;



    ImageButton Borrar;
    LinearLayout Lienzo;
    ImageView plantilla,foto;
    CanvasView canvas;
    GestionNiveles  gn;
    String tipoNivel = "escribirletras";
    TextView Titulo;


    ArrayList<FotoPalabra> fp;

    Estrellas  es;

    final HashMap<Integer, Float> thresholds = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {        // Inicializa la actividad

        super.onCreate(savedInstanceState);                           // Pasa el estado de la APP guardado en un "bundle" a la actividad para poder recrearla
        setContentView(R.layout.activity_writegame_level1_screen);    // Establece como layout la pantalla indicada

        plantilla =(ImageView) findViewById(R.id.imageView3);
        foto = (ImageView) findViewById(R.id.imageView2);
        Borrar= (ImageButton) findViewById(R.id.button6);

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        // Canvas
        Lienzo = (LinearLayout) findViewById(R.id.lienzo);
        canvas = new CanvasView(this);
        Lienzo.addView(canvas);


        Context context = this.getApplicationContext();

        gn = new GestionNiveles(context);
        gn.setNivel(tipoNivel,1);
        fp=gn.getFotos();
        es= new Estrellas (this,gn,gn.setNivel(tipoNivel,1));

        int resId = this.getResources().getIdentifier(fp.get(0).getLetra(), "drawable", this.getPackageName());
        plantilla.setImageResource(resId);
        foto.setImageResource(fp.get(0).getFoto());


    }



    public void resetCanvas(View v) {

        setContentView(R.layout.activity_writegame_level1_screen);
        plantilla =(ImageView) findViewById(R.id.imageView3);
        foto= (ImageView) findViewById(R.id.imageView2);
        Lienzo = (LinearLayout) findViewById(R.id.lienzo);
        canvas = new CanvasView(this);
        Lienzo.addView(canvas);

        int resId=this.getResources().getIdentifier(fp.get(0).getLetra(), "drawable", this.getPackageName());
        plantilla.setImageResource(resId);
        foto.setImageResource(fp.get(0).getFoto());

    }


    public void validateStrokes(View v) {

        int resIdRaw=this.getResources().getIdentifier(fp.get(0).getLetra(), "raw", this.getPackageName());
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
                    es.pulsar(true);
                    Toast.makeText(this, "LETRA " + fp.get(0).getLetra().toUpperCase(), Toast.LENGTH_SHORT).show();

                    gn.avanzaNivel();


                    setContentView(R.layout.activity_writegame_level1_screen);
                    plantilla =(ImageView) findViewById(R.id.imageView3);
                    foto= (ImageView) findViewById(R.id.imageView2);
                    Lienzo = (LinearLayout) findViewById(R.id.lienzo);
                    canvas = new CanvasView(this);
                    Lienzo.addView(canvas);

                    fp=gn.getFotos();
                    int resId=this.getResources().getIdentifier(fp.get(0).getLetra(), "drawable", this.getPackageName());
                    plantilla.setImageResource(resId);
                    foto.setImageResource(fp.get(0).getFoto());


                }else{
                    Toast.makeText(this, "VUELVE A INTENTARLO", Toast.LENGTH_SHORT).show();

                    Borrar.callOnClick();
                }



                // ** AÑADIDA LA ÚLTIMA FRASE **
                //Toast.makeText(this, "Normalized points validation = " + normPointValidationStatus + "\n" + "Angular validation = " + angularValidationStatus + "\n" + "Validacion de trazos = " + estadoValidacionTrazos,
                //Toast.LENGTH_SHORT).show();

            }catch (Exception e){ /* Caso de que le de a aceptar pero no escriba nada */ }

        }
    }


    public void trainStrokes(View v) {
        Intent intent = new Intent(this, StrokesTrainingActivity.class);
        intent.putExtra("fichero", fp.get(0).getLetra());
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





}
