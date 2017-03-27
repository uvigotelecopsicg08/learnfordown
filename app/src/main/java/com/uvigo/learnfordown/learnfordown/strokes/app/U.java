package com.uvigo.learnfordown.learnfordown.strokes.app;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.security.auth.x500.X500Principal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

import com.uvigo.learnfordown.learnfordown.Patrones;

/**
 * A class for shared utility methods
 */
public class U {

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

	public static Object loadObjectFromFile(Context appContext, String filename,InputStream f) {

		Object data = null;
		//Patrones data = new Patrones();
		FileInputStream fis = null;
		ObjectInputStream is = null;

		try {


			//fis = appContext.openFileInput(filename);
			//is = new ObjectInputStream(fis);
			is = new ObjectInputStream(f);
			data = is.readObject();


		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			U.closeQuietly(is);
			U.closeQuietly(fis);
		}

		return data;
	}


	public static void saveObjectToFile(Object data, Context appContext, String filename) {
		FileOutputStream fos = null;
		ObjectOutputStream os = null;

		// save angles data to file
		try {
			fos = appContext.openFileOutput(filename, Context.MODE_PRIVATE);
			os = new ObjectOutputStream(fos);
			os.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			U.closeQuietly(os);
			U.closeQuietly(fos);
		}
	}

	public static final X500Principal DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");

	public static boolean isDebuggable(Context ctx) {
		boolean debuggable = false;

		try {
			PackageInfo pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_SIGNATURES);
			android.content.pm.Signature[] signatures = pinfo.signatures;

			CertificateFactory cf = CertificateFactory.getInstance("X.509");

			for (Signature signature : signatures) {
				ByteArrayInputStream stream = new ByteArrayInputStream(signature.toByteArray());
				X509Certificate cert = (X509Certificate) cf.generateCertificate(stream);
				debuggable = cert.getSubjectX500Principal().equals(DEBUG_DN);
				if (debuggable) {
					break;
				}
			}
		} catch (NameNotFoundException e) {
			// debuggable variable will remain false
		} catch (CertificateException e) {
			// debuggable variable will remain false
		}
		return debuggable;
	}
}