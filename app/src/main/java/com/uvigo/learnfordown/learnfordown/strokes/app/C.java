package com.uvigo.learnfordown.learnfordown.strokes.app;


/**
 * A class for shared constants
 * 
 */
public class C {


        public static final String NORM_POINTS_SAMPLES_FILENAME = "norm_points.ArrayList[LinkedList[Point2D]]"; // Guarda los puntos (x,y) normalizados captados de los patrones definidos por el usuario
        public static final String ANGLES_SAMPLES_FILENAME = "angles.ArrayList[LinkedList[Float]]"; // Guarda angulos theta captados de los patrones definidos por el usuario
        public static final String STROKE_COUNT_FILENAME = "stroke_count.ArrayList[Integer]"; // Guarda el número de trazos de los patrones definidos por el usuario
        public static final String TOUCH_EVENT_COUNT_FILENAME = "touch_event_count.ArrayList[Integer]"; // Guarda el número de veces que el usuario toca la pantalla al definir un patron (para todos los patrones)

        public static final String NORM_POINT_DTW_THRESHOLD_FILENAME = "norm_points_DTW_Threshold.Double";
        public static final String ANGULAR_DTW_THRESHOLD_FILENAME = "angular_DTW_Threshold.Double";

        // default value is false
        public static boolean isDebuggingMode; // Si queremos debuggear (hay varias funciones en el código) poner a true
    }