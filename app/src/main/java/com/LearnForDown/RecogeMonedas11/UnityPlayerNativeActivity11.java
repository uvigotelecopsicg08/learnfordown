package com.LearnForDown.RecogeMonedas11;

import android.os.Bundle;
import android.util.Log;

/**
 * @deprecated It's recommended that you base your code directly on UnityPlayerActivity11 or make your own NativeActitivty implementation.
 **/
public class UnityPlayerNativeActivity11 extends UnityPlayerActivity11
{
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		Log.w("Unity", "UnityPlayerNativeActivity11 has been deprecated, please update your AndroidManifest to use UnityPlayerActivity11 instead");
		super.onCreate(savedInstanceState);
	}
}
