package com.uvigo.learnfordown.learnfordown.strokes.app.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Rect;


import com.uvigo.learnfordown.learnfordown.strokes.app.datatype.Point2D;

public class CanvasView extends View {

	/*   - Con un objeto Canvas definimos qué vamos a dibujar (forma)
	     - Con un objeto Paint, definimos como lo vamos a dibujar (color, grosor, estilo, fuente...)
	     - Con un objeto Bitmap definimos nuestra pantalla de dibujo (framebuffer)
	     - Con un objeto Path encapsulamos trazados geométricos compuestos (de contorno múltiple)
	       que consisten en segmentos de línea recta, curvas cuadráticas y curvas cúbicas            */

	protected final Paint mStrokePaint;
	protected final Path mPath;
	protected final Paint mBitmapPaint;
	protected Bitmap mBitmap;
	protected Canvas mCanvas;
	protected Rect areaDibujo = new Rect(180, 20, 220, 80);

	private float mX, mY;

	private float minX, maxX, minY, maxY;

	private int strokeCount;

	public int getStrokeCount() {
		return strokeCount;
	}

	private int touchEventCount;

	public int getTouchEventCount() {
		return touchEventCount;
	}

	private float centerX, centerY;

	private LinkedList<Float> xCoors, yCoors;

	private static final double TOUCH_TOLERANCE = 4;

	public CanvasView(Context c) {
		super(c);
		mPath = new Path();
		mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		mStrokePaint = new Paint();
		mStrokePaint.setAntiAlias(true);
		mStrokePaint.setDither(true);
		mStrokePaint.setColor(Color.GREEN);
		mStrokePaint.setStyle(Paint.Style.STROKE);
		mStrokePaint.setStrokeJoin(Paint.Join.ROUND);
		mStrokePaint.setStrokeCap(Paint.Cap.ROUND);
		mStrokePaint.setStrokeWidth(30);
	}

	@Override
	/* Se llama a este método cuando:
	    - Se le asigna por primera vez un tamaño a tu zona de escritura
	    - Si el tamaño de tu zona de escritura cambia por alguna razón */

	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);    // Crea un área de dibujo con un ancho "w", altura "h"
																		// y en el que cada píxel se almacena en 4 bytes
		mCanvas = new Canvas(mBitmap);
		resetWorkingSetData();
	}

	@Override
	protected void onDraw(Canvas canvas) {


		canvas.drawColor(0xA9D0F5); // Pone el área del bitmap de color gris
		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint); // Dibuja el bitmap mBitmap escalándolo automáticamente para
														// encajar en el rectángulo de destino
		canvas.drawPath(mPath, mStrokePaint); // Dibuja el trazado mPath empleando el pincel mStrokePaint

	}

	private void resetWorkingSetData() {
		mX = mY = 0;
		minX = maxX = minY = maxY = 0;
		centerX = centerY = 0;
		strokeCount = 0;
		touchEventCount = 0;
		xCoors = new LinkedList<Float>();
		yCoors = new LinkedList<Float>();
	}

	protected void drawInfoText(Canvas canvas) {
		StringBuilder sb = new StringBuilder();
		sb.append("X=" + mX + ", Y=" + mY);
		sb.append("\n" + "minX=" + minX + ", maxX=" + maxX);
		sb.append("\n" + "minY=" + minY + ", maxY=" + maxY);
		sb.append("\n" + "centerX=" + centerX + ", centerY=" + centerY);
		sb.append("\n" + "strokeCount=" + strokeCount);
		sb.append("\n" + "touchEventCount=" + touchEventCount);

		TextPaint mTextPaint = new TextPaint();
		mTextPaint.setTextSize(32);
		StaticLayout mTextLayout = new StaticLayout(sb.toString(), mTextPaint, canvas.getWidth(), Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
		canvas.save();
		canvas.translate(10, 10);
		mTextLayout.draw(canvas);
		canvas.restore();
	}

	private void touch_start(float x, float y) {
		strokeCount++;
		mPath.reset();
		mPath.moveTo(x, y); // Establece el comienzo del siguiente contorno al punto (x, y)
		mX = x;
		mY = y;

		// initialized min and max point values
		if (strokeCount == 1) {
			minX = maxX = mX;
			minY = maxY = mY;
		}
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX); // Diferencia entre punto captado y punto anterior = desplazamiento en eje x
		float dy = Math.abs(y - mY); // Diferencia entre punto captado y punto anterior = desplazamiento en eje y

		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2); // Dibuja la curva resultante del desplazamiento
			mX = x; // Actualiza los valores
			mY = y;
		}

		// update min max point values
		if (mX < minX)
			minX = mX;
		else if (mX > maxX)
			maxX = mX;

		if (mY < minY)
			minY = mY;
		else if (mY > maxY)
			maxY = mY;
	}

	private void touch_end() {
		mPath.lineTo(mX, mY); // Traza una línea desde el último punto dibujado hasta el especificado (para terminar)

		// commit the path to our offscreen
		mCanvas.drawPath(mPath, mStrokePaint);
		// kill this so we don't double draw
		mPath.reset();

		// update center point values
		centerX = ((maxX - minX) / 2) + minX;
		centerY = ((maxY - minY) / 2) + minY;
	}

	@Override
	// Se llama a este método cuando se toca la pantalla

	public boolean onTouchEvent(MotionEvent event) {
		touchEventCount++;

		float x = event.getX(); // Obtiene coordenada x del punto donde el usuario está tocando
		float y = event.getY(); // Obtiene coordenada y del punto donde el usuario está tocando

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: // El usuario toca la pantalla por primera vez (es decir, aquí vamos en cada inicio de trazo)
				touch_start(x, y);
				invalidate();   // Se le dice al entorno que refresque la ventana lo más pronto posible.
								// Entiendo que esto es para que capte el siguiente punto lo más rápido posible.
				break;
			case MotionEvent.ACTION_MOVE: // El usuario ha cambiado el gesto entre ACTION_DOWN y ACTION_UP
				touch_move(x, y);
				invalidate();
				break;
			case MotionEvent.ACTION_UP: // El usuario deja de aplicar presión sobre la pantalla, es decir, ha terminado
				touch_end();
				invalidate();
				break;
		}

		// add to x and y points to data
		xCoors.add(mX);
		yCoors.add(mY);

		return true;
	}

	private static Point2D calcNormalizedScales(Point2D imgSize) {
		float original_width = imgSize.x;
		float original_height = imgSize.y;
		float new_width = 1;
		float new_height = 1;

		// first check if we need to scale height
		if (original_height > original_width)
			// scale width to maintain aspect ratio
			new_width = original_width / original_height;
		else if (original_width > original_height)
			// scale height to maintain aspect ratio
			new_height = original_height / original_width;

		return new Point2D(new_width, new_height);
	}

	private static LinkedList<Float> calcNormalizedValues(List<Float> vals, float min, float max, float scale) {
		LinkedList<Float> normalizedVals = new LinkedList<Float>();
		for (float v : vals)
			normalizedVals.add(calcNormalizedValue(v, min, max, scale));
		return normalizedVals;
	}

	private static float calcNormalizedValue(float val, float min, float max, float scale) {
		return (scale * (val - min)) / (max - min);
	}

	public LinkedList<Point2D> getNormalizedPoints() {
		Point2D scales = calcNormalizedScales(new Point2D(maxX - minX, maxY - maxY));
		Iterator<Float> normXCoors = calcNormalizedValues(xCoors, minX, maxX, scales.x).iterator();
		Iterator<Float> normYCoors = calcNormalizedValues(yCoors, minY, maxY, scales.y).iterator();
		LinkedList<Point2D> normalizedPoints = new LinkedList<Point2D>();
		while (normXCoors.hasNext() && normYCoors.hasNext())
			normalizedPoints.add(new Point2D(normXCoors.next(), normYCoors.next()));
		return normalizedPoints;
	}

	private static float calcAngle(float x, float y, float centerX, float centerY) {
		return (float) Math.atan2(y - centerY, x - centerX); // Retorna el angulo theta procedente de la conversion
															 // de coordenadas rectangulares (x,y) a polares (r,theta)
	}

	public LinkedList<Float> getAngles() {
		LinkedList<Float> angles = new LinkedList<Float>();
		Iterator<Float> xIterator = xCoors.iterator();
		Iterator<Float> yIterator = yCoors.iterator();
		while (xIterator.hasNext()) {
			float x = xIterator.next();
			float y = yIterator.next();
			angles.add(calcAngle(x, y, centerX, centerY));
		}
		return angles;
	}

	public boolean isEmpty() {
		return (strokeCount == 0);
	}
}
