package com.LearnForDown.RecogeMonedas11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @deprecated Use UnityPlayerActivity11 instead.
 */
public class UnityPlayerProxyActivity11 extends Activity
{
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		Log.w("Unity", "UnityPlayerNativeActivity11 has been deprecated, please update your AndroidManifest to use UnityPlayerActivity11 instead");
		super.onCreate(savedInstanceState);

		Intent intent = new Intent(this, UnityPlayerActivity11.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		Bundle extras = getIntent().getExtras();
		if (extras != null)
			intent.putExtras(extras);
		startActivity(intent);
	}
}
