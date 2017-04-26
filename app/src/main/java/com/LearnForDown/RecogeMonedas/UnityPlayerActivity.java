package com.LearnForDown.RecogeMonedas;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

import com.unity3d.player.*;
import com.uvigo.learnfordown.learnfordown.BluetoothService;

public class UnityPlayerActivity extends AppCompatActivity
{
	protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code
	private boolean  mBound= false;
	private  BluetoothService mService;
	private AppCompatActivity app=this;
	// Setup activity layout
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy

		mUnityPlayer = new UnityPlayer(this);
		setContentView(mUnityPlayer);
		mUnityPlayer.requestFocus();
		Intent intent=new Intent(this,BluetoothService.class);
		bindService(intent,mConnection,BIND_AUTO_CREATE);

	}


	// This ensures the layout will be correct.


//****************************************


	// Quit Unity
	@Override protected void onDestroy ()
	{
		mUnityPlayer.quit();
		super.onDestroy();
	}

	// Pause Unity
	@Override protected void onPause()
	{
		super.onPause();
		mUnityPlayer.pause();
	}

	// Resume Unity
	@Override protected void onResume()
	{
		super.onResume();
		mUnityPlayer.resume();
	}

	// This ensures the layout will be correct.
	@Override public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		mUnityPlayer.configurationChanged(newConfig);
	}

	// Notify Unity of the focus change.
	@Override public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		mUnityPlayer.windowFocusChanged(hasFocus);
	}

	// For some reason the multiple keyevent type is not supported by the ndk.
	// Force event injection by overriding dispatchKeyEvent().
	@Override public boolean dispatchKeyEvent(KeyEvent event)
	{
		if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
			return mUnityPlayer.injectEvent(event);
		return super.dispatchKeyEvent(event);
	}

	// Pass any events not handled by (unfocused) views straight to UnityPlayer
	@Override public boolean onKeyUp(int keyCode, KeyEvent event)     { return mUnityPlayer.injectEvent(event); }
	@Override public boolean onKeyDown(int keyCode, KeyEvent event)   {
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT)
			UnityPlayer.UnitySendMessage("Pencil", "Left", "izquierda");
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
			UnityPlayer.UnitySendMessage("Pencil", "Right", "derecha");
		return mUnityPlayer.injectEvent(event); }
	@Override public boolean onTouchEvent(MotionEvent event)          { return mUnityPlayer.injectEvent(event); }
	/*API12*/ public boolean onGenericMotionEvent(MotionEvent event)  { return mUnityPlayer.injectEvent(event); }


	private ServiceConnection mConnection = new ServiceConnection() {
		/*API12*/ public boolean onGenericMotionEvent(MotionEvent event)  { return mUnityPlayer.injectEvent(event); }



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
