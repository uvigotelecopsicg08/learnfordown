package com.uvigo.learnfordown.learnfordown;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class siinversas_screen extends AppCompatActivity {
TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siinversas_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        Intent intent=new Intent(this,BluetoothService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(siinversas_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(siinversas_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void nivel1(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, silabasgame1lvl_screen.class);
        String strName = "silabasinversas";
        intent.putExtra("tipoSilaba", strName);
        startActivity(intent);
    }

    public void nivel2(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, silabasgame2lvl_screen.class);
        String strName = "silabasinversas";
        intent.putExtra("tipoSilaba", strName);
        startActivity(intent);
    }

    public void nivel3(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, silabasgame3lvl_screen.class);
        String strName = "silabasinversas";
        intent.putExtra("tipoSilaba", strName);
        startActivity(intent);
    }

    public void nivel4(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, silabasgame4lvl_screen.class);
        String strName = "silabasinversas";
        intent.putExtra("tipoSilaba", strName);
        startActivity(intent);
    }
    AppCompatActivity app = this;
    private boolean  mBound= false;
    private  BluetoothService mService;
    private ServiceConnection mConnection = new ServiceConnection() {



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
