package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

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

public class writegame_level1_screen extends AppCompatActivity {

    public static final float VALIDATION_THRESHOLD_MULTIPLIER = 1.8f;



    ImageButton Borrar;
    LinearLayout Lienzo;
    ImageView plantilla,foto;
    CanvasView canvas;
    GestionNiveles  gn;
    String tipoNivel="escribirletras";

    ArrayList<FotoPalabra> fp;

    int contador;
    RatingBar ratingbar1;
    final HashMap<Integer, Float> thresholds = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {        // Inicializa la actividad

        super.onCreate(savedInstanceState);                           // Pasa el estado de la APP guardado en un "bundle" a la actividad para poder recrearla
        setContentView(R.layout.activity_writegame_level1_screen);    // Establece como layout la pantalla indicada
        plantilla =(ImageView) findViewById(R.id.imageView3);
        foto = (ImageView) findViewById(R.id.imageView2);
        Borrar= (ImageButton) findViewById(R.id.button6);
        Lienzo = (LinearLayout) findViewById(R.id.lienzo);
        canvas = new CanvasView(this);
        Lienzo.addView(canvas);
        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        gn.setNivel(tipoNivel,1);
        fp=gn.getFotos();


        int resId = this.getResources().getIdentifier(fp.get(0).getLetra(), "drawable", this.getPackageName());
        plantilla.setImageResource(resId);
        foto.setImageResource(fp.get(0).getFoto());

        // ** Estrellitas **

        contador = 0;
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);

        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas
    }

    public void pulsar() {
        float rating = 0;
        for (int i : new TreeSet<>(thresholds.keySet())) {
            if (contador < i) {
                break;
            }
            rating = thresholds.get(i);
        }
        if (rating != ratingbar1.getRating()) {
            ratingbar1.setRating(rating);
            Toast toast = Toast.makeText(this, "¡HAS CONSEGUIDO UNA ESTRELLITA!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, -350, -50);
            toast.show();
        }
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


        Patrones patronLetra = (Patrones) U.loadObjectFromFile(getApplicationContext(), fp.get(0).getLetra());

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


                    contador++;
                    pulsar();
                    Toast.makeText(this, "LETRA " + fp.get(0).getLetra().toUpperCase(), Toast.LENGTH_SHORT).show();
                    gn.avanzaNivel();

                    /* No creo que sea necesario rehacer esto ¿? Si hay que rehacerlo, meter en función*/
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
