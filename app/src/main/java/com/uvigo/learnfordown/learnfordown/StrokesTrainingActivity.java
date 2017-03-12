package  com.uvigo.learnfordown.learnfordown;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.uvigo.learnfordown.learnfordown.dtw.FastDTW;
import com.uvigo.learnfordown.learnfordown.strokes.app.C;
import com.uvigo.learnfordown.learnfordown.strokes.app.U;
import com.uvigo.learnfordown.learnfordown.strokes.app.datatype.Point2D;
import com.uvigo.learnfordown.learnfordown.strokes.app.view.CanvasView;
import com.uvigo.learnfordown.learnfordown.timeseries.TimeSeries;
import com.uvigo.learnfordown.learnfordown.util.DistanceFunctionFactory;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.ArrayList;
import java.util.LinkedList;

public class StrokesTrainingActivity extends Activity {


	private static final int SAMPLES_REQUIRED = 3; // Número de muestras requeridas para validaciones

	// values between 0 and 1, as percentage
	private static final float[] DRAW_AREA_X_RELATIVE_POS = new float[]{0.2f, 0.2f, 0.2f};
	private static final float[] DRAW_AREA_Y_RELATIVE_POS = new float[]{0.05f, 0.05f, 0.05f};
	private static final float[] DRAW_AREA_RELATIVE_SIZE = new float[]{0.9f, 0.8f, 0.8f};


	LinearLayout Lienzo;
	CanvasView canvasView;

	private int samplesCollected;
	private ArrayList<LinkedList<Point2D>> normStrokePoints;
	private ArrayList<LinkedList<Float>> strokeRadialAngles;
	private ArrayList<Integer> strokeCounts;
	private ArrayList<Integer> touchEventCounts;
	Double maxNormPointDTWDistance;
	Double maxAngularDTWDistance;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_strokes_training);   // Establece como layout la pantalla indicada

		Lienzo = (LinearLayout) findViewById(R.id.lienzoPatron);
		canvasView = new TrainingCanvasView(this, DRAW_AREA_X_RELATIVE_POS[samplesCollected], DRAW_AREA_Y_RELATIVE_POS[samplesCollected],
				DRAW_AREA_RELATIVE_SIZE[samplesCollected]);
		Lienzo.addView(canvasView);

		samplesCollected = 0;
		normStrokePoints = new ArrayList<LinkedList<Point2D>>(SAMPLES_REQUIRED);
		strokeRadialAngles = new ArrayList<LinkedList<Float>>(SAMPLES_REQUIRED);
		strokeCounts = new ArrayList<Integer>(SAMPLES_REQUIRED);
		touchEventCounts = new ArrayList<Integer>(SAMPLES_REQUIRED);

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}




	public void resetCanvas(View v) {

		setContentView(R.layout.activity_strokes_training);
		Lienzo = (LinearLayout) findViewById(R.id.lienzoPatron);

		canvasView = new TrainingCanvasView(this, DRAW_AREA_X_RELATIVE_POS[samplesCollected], DRAW_AREA_Y_RELATIVE_POS[samplesCollected],
				DRAW_AREA_RELATIVE_SIZE[samplesCollected]);
		Lienzo.addView(canvasView);

	}

	public void resetCanvas(LayoutParams layoutParams) {

		setContentView(R.layout.activity_strokes_training);
		Lienzo = (LinearLayout) findViewById(R.id.lienzoPatron);
		canvasView = new TrainingCanvasView(this, DRAW_AREA_X_RELATIVE_POS[samplesCollected], DRAW_AREA_Y_RELATIVE_POS[samplesCollected],
				DRAW_AREA_RELATIVE_SIZE[samplesCollected]);
		canvasView.setLayoutParams(layoutParams);
		Lienzo.addView(canvasView);
	}

	public void saveAllData() {


		normStrokePoints.add(canvasView.getNormalizedPoints());     // Obtiene los puntos (x,y) normalizados
		strokeRadialAngles.add(canvasView.getAngles());             // Retorna el angulo theta procedente de la conversion
																	// de coordenadas rectangulares (x,y) a polares (r,theta)
		strokeCounts.add(canvasView.getStrokeCount());              // Número de trazos que contiene  el dibujo
		touchEventCounts.add(canvasView.getTouchEventCount());      // Número de veces que el usuario toca la pantalla



		U.saveObjectToFile(strokeRadialAngles, getApplicationContext(), C.ANGLES_SAMPLES_FILENAME);
		U.saveObjectToFile(normStrokePoints, getApplicationContext(), C.NORM_POINTS_SAMPLES_FILENAME);
		U.saveObjectToFile(strokeCounts, getApplicationContext(), C.STROKE_COUNT_FILENAME);
		U.saveObjectToFile(touchEventCounts, getApplicationContext(), C.TOUCH_EVENT_COUNT_FILENAME);


		/* DTW: Dinamic Time Warping

		   El Alineamiento Temporal Dinámico (Dynamic Time Warping, DTW), es una
		   técnica surgida de la problemática inherente a diferentes realizaciones de una
           misma locución, en las que se observa una variabilidad, de modo que no existe una sincronización
           temporal (alineamiento temporal).

		 */
		maxNormPointDTWDistance = getMaxNormPointDTWDistance(normStrokePoints);
		maxAngularDTWDistance = getMaxAngularDTWDistance(strokeRadialAngles);

		U.saveObjectToFile(maxNormPointDTWDistance, getApplicationContext(), C.NORM_POINT_DTW_THRESHOLD_FILENAME);
		U.saveObjectToFile(maxAngularDTWDistance, getApplicationContext(), C.ANGULAR_DTW_THRESHOLD_FILENAME);

		Toast.makeText(this, "Patrón guardado correctamente", Toast.LENGTH_SHORT).show();

		// Prueba: Generación de un fichero que guarde los datos anteriores en un objeto de tipo Patrón
		// Este fichero podrá ser único para todos los patrones añadiendole un atributo que identifique la letra
		// Importante, ahora estoy trabajando solamente con un objeto, luego tendré que cambiarlo por un ArrayList o mapa
		U.saveObjectToFile(new Patrones("C", normStrokePoints, strokeRadialAngles, strokeCounts, maxNormPointDTWDistance, maxAngularDTWDistance), getApplicationContext(), "C");

		finish();
	}

	public void procesarMuestra(View v){



		samplesCollected++; // Se ha capturado bién un patrón
		if (samplesCollected == 3) saveAllData();
		else saveAndPrepareNextSample();
	}

	public void saveAndPrepareNextSample() {



		normStrokePoints.add(canvasView.getNormalizedPoints());     // Obtiene los puntos (x,y) normalizados
		strokeRadialAngles.add(canvasView.getAngles());             // Retorna el angulo theta procedente de la conversion
																	// de coordenadas rectangulares (x,y) a polares (r,theta)
		strokeCounts.add(canvasView.getStrokeCount());              // Número de trazos que contiene  el dibujo
		touchEventCounts.add(canvasView.getTouchEventCount());      // Número de veces que el usuario toca la pantalla

		resetCanvas(new LayoutParams(600, 600));
		Toast.makeText(this, "Por favor, introduzca otro patrón", Toast.LENGTH_SHORT).show();
	}

	public double getMaxNormPointDTWDistance(ArrayList<LinkedList<Point2D>> normStrokePoints) {
		double maxNormPointWarpDist = 0;
		// Create the initial vector
		ICombinatoricsVector<LinkedList<Point2D>> initialVector = Factory.createVector(normStrokePoints);
		// Create a simple combination generator to generate 2-combinations of the initial vector
		Generator<LinkedList<Point2D>> gen = Factory.createSimpleCombinationGenerator(initialVector, 2);
		// loop thru all combinations and get max dtw distance
		for (ICombinatoricsVector<LinkedList<Point2D>> combination : gen) {
			TimeSeries timeSeries1 = new TimeSeries(combination.getVector().get(0));
			TimeSeries timeSeries2 = new TimeSeries(combination.getVector().get(1));
			double normWarpDist = FastDTW.getWarpDistBetween(timeSeries1, timeSeries2, DistanceFunctionFactory.getDistFnByName("EuclideanDistance"));
			maxNormPointWarpDist = Math.max(normWarpDist, maxNormPointWarpDist);
		}
		return maxNormPointWarpDist;
	}

	public double getMaxAngularDTWDistance(ArrayList<LinkedList<Float>> strokeRadialAngles) {
		double maxAngularWarpDist = 0;
		// Create the initial vector
		ICombinatoricsVector<LinkedList<Float>> initialVector = Factory.createVector(strokeRadialAngles);
		// Create a simple combination generator to generate 2-combinations of the initial vector
		Generator<LinkedList<Float>> gen = Factory.createSimpleCombinationGenerator(initialVector, 2);
		// loop thru all combinations and get max dtw distance
		for (ICombinatoricsVector<LinkedList<Float>> combination : gen) {
			TimeSeries timeSeries1 = new TimeSeries(combination.getVector().get(0));
			TimeSeries timeSeries2 = new TimeSeries(combination.getVector().get(1));
			double angularWarpDist = FastDTW.getWarpDistBetween(timeSeries1, timeSeries2, DistanceFunctionFactory.getDistFnByName("AngularDistance"));
			maxAngularWarpDist = Math.max(angularWarpDist, maxAngularWarpDist);
		}
		return maxAngularWarpDist;
	}

	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	public Action getIndexApiAction() {
		Thing object = new Thing.Builder()
				.setName("StrokesTraining Page") // TODO: Define a title for the content shown.
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

	private class TrainingCanvasView extends CanvasView {

		RectF mDrawArea;
		final Paint mDrawAreaPaint;
		final float relativeXPos, relativeYPos, relativeSize;

		public TrainingCanvasView(Context c, float relativeXPosition, float relativeYPosition, float relativeSize) {
			super(c);

			mDrawAreaPaint = new Paint();                        // Un objeto Paint nos permite definir como vamos
																 // a dibujar (color, grosor, estilo, fuente...)
			mDrawAreaPaint.setColor(Color.MAGENTA);                // Color = rojo
			mDrawAreaPaint.setStyle(Paint.Style.STROKE);        // STROKE = Se dibuja lo mismo que se traza, sin ser rellenado
			mDrawAreaPaint.setStrokeWidth(10);                    // Ancho = 10 ¿píxeles?

			relativeXPos = relativeXPosition;
			relativeYPos = relativeYPosition;
			this.relativeSize = relativeSize;

		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			super.onSizeChanged(w, h, oldw, oldh); // Ejecuta el método onSizeChanged de la clase CanvasView
			setDrawArea(relativeXPos, relativeYPos, relativeSize, w, h);
		}

		@Override
		protected void onDraw(Canvas canvas) {

			canvas.drawColor(0x7FB3D5);
			canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint); // Dibuja el bitmap mBitmap escalándolo automáticamente para
			// encajar en el rectángulo de destino
			canvas.drawRect(mDrawArea, mDrawAreaPaint);        // Dibuja un rectángulo rojo
			canvas.drawPath(mPath, mStrokePaint);            // Dibuja el trazado mPath empleando el pincel mStrokePaint

		}


		private void setDrawArea(float relativeXPosition, float relativeYPosition, float relativeSize, int canvasWidth, int canvasHeight) {
			int boundSize = Math.min(canvasWidth, canvasHeight);
			float absoluteLeft = relativeXPosition * canvasWidth;
			float absoluteTop = relativeYPosition * canvasHeight;
			float absoluteSize = relativeSize * boundSize;
			float absoluteRight = absoluteLeft + absoluteSize;
			float absoluteBottom = absoluteTop + absoluteSize;

			mDrawArea = new RectF(absoluteLeft, absoluteTop, absoluteRight, absoluteBottom);    // Crea un rectangulo que posteriormente
			// emplearemos para el rectangulo rojo
		}
	}
}
