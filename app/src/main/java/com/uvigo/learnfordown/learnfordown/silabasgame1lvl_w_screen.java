package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uvigo.learnfordown.learnfordown.dtw.FastDTW;
import com.uvigo.learnfordown.learnfordown.strokes.app.U;
import com.uvigo.learnfordown.learnfordown.strokes.app.datatype.Point2D;
import com.uvigo.learnfordown.learnfordown.strokes.app.view.CanvasView;
import com.uvigo.learnfordown.learnfordown.timeseries.TimeSeries;
import com.uvigo.learnfordown.learnfordown.util.DistanceFunctionFactory;

import java.util.ArrayList;
import java.util.LinkedList;

public class silabasgame1lvl_w_screen extends AppCompatActivity {


    public static final float VALIDATION_THRESHOLD_MULTIPLIER = 1.8f;



    ImageButton Borrar;
    LinearLayout Lienzo1;
    LinearLayout Lienzo2;
    CanvasView canvas1;
    CanvasView canvas2;
    boolean validacionLetra1,validacionLetra2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {                // Inicializa la actividad

        super.onCreate(savedInstanceState);                             // Pasa el estado de la APP guardado en un "bundle" a la actividad para poder recrearla
        setContentView(R.layout.activity_silabasgame1lvl_w_screen);      // Establece como layout la pantalla indicada

        Borrar = (ImageButton) findViewById(R.id.button6);

        Lienzo1 = (LinearLayout) findViewById(R.id.lienzo1);
        canvas1 = new CanvasView(this);
        Lienzo1.addView(canvas1);

        Lienzo2 = (LinearLayout) findViewById(R.id.lienzo2);
        canvas2 = new CanvasView(this);
        Lienzo2.addView(canvas2);



    }


    public void resetCanvas(View v) {

        setContentView(R.layout.activity_silabasgame1lvl_w_screen);

        Lienzo1 = (LinearLayout) findViewById(R.id.lienzo1);
        canvas1 = new CanvasView(this);
        Lienzo1.addView(canvas1);

        Lienzo2 = (LinearLayout) findViewById(R.id.lienzo2);
        canvas2 = new CanvasView(this);
        Lienzo2.addView(canvas2);



    }


    public void validateStrokes(View v) {


        Patrones patronLetra1 = (Patrones) U.loadObjectFromFile(getApplicationContext(), "a");
        Patrones patronLetra2 = (Patrones) U.loadObjectFromFile(getApplicationContext(), "a");


        ArrayList<LinkedList<Point2D>> loadedNormPointsSamples1 = patronLetra1.getPuntosNormalizados();
        ArrayList<LinkedList<Float>> loadedAnglesSamples1 = patronLetra1.getAngulosRadiales();
        ArrayList<Integer> numTrazos_patron1 = patronLetra1.getNumeroTrazos();
        Double normPointValidationThreshold1 = patronLetra1.getUmbralNormalizacion();
        Double angularValidationThreshold1 = patronLetra1.getUmbralAngular();

        ArrayList<LinkedList<Point2D>> loadedNormPointsSamples2 = patronLetra2.getPuntosNormalizados();
        ArrayList<LinkedList<Float>> loadedAnglesSamples2 = patronLetra2.getAngulosRadiales();
        ArrayList<Integer> numTrazos_patron2 = patronLetra2.getNumeroTrazos();
        Double normPointValidationThreshold2 = patronLetra2.getUmbralNormalizacion();
        Double angularValidationThreshold2 = patronLetra2.getUmbralAngular();



        // Comprobación patrones correctos

        if (loadedAnglesSamples1.equals(null) || loadedNormPointsSamples1.equals(null) || numTrazos_patron1.equals(null) || loadedAnglesSamples2.equals(null) || loadedNormPointsSamples2.equals(null) || numTrazos_patron2.equals(null)) // Si no se han captado puntos de validación
            Toast.makeText(getApplicationContext(), "Error cargando ficheros", Toast.LENGTH_LONG).show(); // Salta una ventana con dicho mensaje
        else {


            TimeSeries tsCanvasNormPoints1 = new TimeSeries(canvas1.getNormalizedPoints());
            TimeSeries tsCanvasAngles1 = new TimeSeries(canvas1.getAngles());
            Integer numTrazos_muestra1 = canvas1.getStrokeCount(); // Leemos el número de trazos de la escritura del usuario

            TimeSeries tsCanvasNormPoints2 = new TimeSeries(canvas2.getNormalizedPoints());
            TimeSeries tsCanvasAngles2 = new TimeSeries(canvas2.getAngles());
            Integer numTrazos_muestra2 = canvas2.getStrokeCount(); // Leemos el número de trazos de la escritura del usuario


            double avgNormPointDTWDistance1 = 0;
            double avgAngularDTWDistance1 = 0;

            double avgNormPointDTWDistance2 = 0;
            double avgAngularDTWDistance2 = 0;

            int sampleCount1 = 0;
            int sampleCount2 = 0;

            // find average norm point dtw distance

            // ¿Correcto el patrón 1?

            try {
                for (LinkedList<Point2D> normPointsSample : loadedNormPointsSamples1) {
                    sampleCount1++;
                    TimeSeries tsLoadedNormPointsSample = new TimeSeries(normPointsSample);

                    double normPointWarpDist = FastDTW.getWarpDistBetween(tsCanvasNormPoints1, tsLoadedNormPointsSample,
                            DistanceFunctionFactory.getDistFnByName("EuclideanDistance"));

                    avgNormPointDTWDistance1 += normPointWarpDist;
                }


                avgNormPointDTWDistance1 = avgNormPointDTWDistance1 / sampleCount1;

                sampleCount1 = 0;

                // find average angular dtw distance
                for (LinkedList<Float> loadedAnglesSample : loadedAnglesSamples1) { // Recorre la el arraylist de listas
                    sampleCount1++;
                    TimeSeries tsLoadedAnglesSample = new TimeSeries(loadedAnglesSample);
                    double anglesWarpDist = FastDTW.getWarpDistBetween(tsCanvasAngles1, tsLoadedAnglesSample,
                            DistanceFunctionFactory.getDistFnByName("AngularDistance"));
                    avgAngularDTWDistance1 += anglesWarpDist;
                }
                avgAngularDTWDistance1 = avgAngularDTWDistance1 / sampleCount1;

                String normPointValidationStatus = "failed";
                String angularValidationStatus = "failed";
                String estadoValidacionTrazos = "failed"; // ** AÑADIDO **

                if (avgNormPointDTWDistance1 < VALIDATION_THRESHOLD_MULTIPLIER * normPointValidationThreshold1)
                    normPointValidationStatus = "passed";
                if (avgAngularDTWDistance1 < VALIDATION_THRESHOLD_MULTIPLIER * angularValidationThreshold1)
                    angularValidationStatus = "passed";

                // ** AÑADIDO **
                for (int j = 0; j < numTrazos_patron1.size(); j++) {
                    if (numTrazos_muestra1.equals(numTrazos_patron1.get(j)))
                        estadoValidacionTrazos = "passed";
                }

                if (normPointValidationStatus == "passed" && angularValidationStatus == "passed" && estadoValidacionTrazos == "passed") validacionLetra1 = true;
                else validacionLetra1 = false;




                // ** AÑADIDA LA ÚLTIMA FRASE **
                //Toast.makeText(this, "Normalized points validation = " + normPointValidationStatus + "\n" + "Angular validation = " + angularValidationStatus + "\n" + "Validacion de trazos = " + estadoValidacionTrazos,
                //Toast.LENGTH_SHORT).show();

            }catch (Exception e){ /* Caso de que le de a aceptar pero no escriba nada */ }

            // ¿Correcto el patrón 2?


            try {
                for (LinkedList<Point2D> normPointsSample : loadedNormPointsSamples2) {
                    sampleCount2++;
                    TimeSeries tsLoadedNormPointsSample = new TimeSeries(normPointsSample);

                    double normPointWarpDist = FastDTW.getWarpDistBetween(tsCanvasNormPoints2, tsLoadedNormPointsSample,
                            DistanceFunctionFactory.getDistFnByName("EuclideanDistance"));

                    avgNormPointDTWDistance2 += normPointWarpDist;
                }


                avgNormPointDTWDistance2 = avgNormPointDTWDistance2 / sampleCount1;

                sampleCount2 = 0;

                // find average angular dtw distance
                for (LinkedList<Float> loadedAnglesSample : loadedAnglesSamples2) { // Recorre la el arraylist de listas
                    sampleCount2++;
                    TimeSeries tsLoadedAnglesSample = new TimeSeries(loadedAnglesSample);
                    double anglesWarpDist = FastDTW.getWarpDistBetween(tsCanvasAngles2, tsLoadedAnglesSample,
                            DistanceFunctionFactory.getDistFnByName("AngularDistance"));
                    avgAngularDTWDistance2 += anglesWarpDist;
                }
                avgAngularDTWDistance2 = avgAngularDTWDistance2 / sampleCount2;

                String normPointValidationStatus = "failed";
                String angularValidationStatus = "failed";
                String estadoValidacionTrazos = "failed"; // ** AÑADIDO **

                if (avgNormPointDTWDistance2 < VALIDATION_THRESHOLD_MULTIPLIER * normPointValidationThreshold2)
                    normPointValidationStatus = "passed";
                if (avgAngularDTWDistance2 < VALIDATION_THRESHOLD_MULTIPLIER * angularValidationThreshold2)
                    angularValidationStatus = "passed";

                // ** AÑADIDO **
                for (int j = 0; j < numTrazos_patron2.size(); j++) {
                    if (numTrazos_muestra2.equals(numTrazos_patron2.get(j)))
                        estadoValidacionTrazos = "passed";
                }

                if (normPointValidationStatus == "passed" && angularValidationStatus == "passed" && estadoValidacionTrazos == "passed") validacionLetra2 = true;
                else validacionLetra2 = false;




                // ** AÑADIDA LA ÚLTIMA FRASE **
                //Toast.makeText(this, "Normalized points validation = " + normPointValidationStatus + "\n" + "Angular validation = " + angularValidationStatus + "\n" + "Validacion de trazos = " + estadoValidacionTrazos,
                //Toast.LENGTH_SHORT).show();

            }catch (Exception e){ /* Caso de que le de a aceptar pero no escriba nada */ }


            if (validacionLetra1 == true && validacionLetra2 == true) Toast.makeText(getApplicationContext(), "¡GENIAL!", Toast.LENGTH_LONG).show();
            else{
                Borrar.callOnClick();
                Toast.makeText(getApplicationContext(), "¡VUELVE A INTENTARLO!", Toast.LENGTH_LONG).show();
            }

        }
    }


    public void trainStrokes(View v) {

        startActivity(new Intent(this, StrokesTrainingActivity.class));
    }


    //Funciones que habrá que descomentar cuando se integre en la APP LearnForDown

    public void BackArrow (View v){
        Intent intent1 = new Intent(silabasgame1lvl_w_screen.this, menu_write_screen.class);
        startActivity(intent1);
        //Toast.makeText(this, "ATRÁS", Toast.LENGTH_SHORT).show();
    }

    public void goHome (View v){
        Intent intent1 = new Intent(silabasgame1lvl_w_screen.this, home_screen.class);
        startActivity(intent1);
        //Toast.makeText(this, "MENÚ PRINCIPAL", Toast.LENGTH_SHORT).show();
    }



}
