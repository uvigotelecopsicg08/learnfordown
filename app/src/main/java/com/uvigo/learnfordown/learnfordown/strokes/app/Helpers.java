package com.uvigo.learnfordown.learnfordown.strokes.app;

import java.io.Closeable;

public class Helpers {
	
	
	// for closing resources
	public static void closeQuietly(Closeable closeable) {
	    if (closeable != null) {
	        try {
	            closeable.close();
	        } catch (Exception ex) {
	            // ignore
	        }
	    }
	}
}