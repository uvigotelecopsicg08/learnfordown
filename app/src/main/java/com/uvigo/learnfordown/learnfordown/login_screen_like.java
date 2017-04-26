package com.uvigo.learnfordown.learnfordown;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class login_screen_like extends AppCompatActivity implements View.OnClickListener  {

  //  boolean[] opcion=new boolean[6];
    int[] drawable =new int[6];
    String[]  gustosString ={"animales","coches","ropa","casa","playa","musica"};
    int numero=0;
    ImageView foto;
    String nombre;
    TextView Titulo;
    int edad;
    int avatar;
    boolean azureEnable;
    HashMap<String,Boolean> gustos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_like);
        numero=0;
        gustos =new HashMap<String,Boolean>();
        foto = (ImageView) findViewById(R.id.imageView);

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        drawable[0]=R.drawable.oso;
        drawable[1]=R.drawable.coche;
        drawable[2]=R.drawable.bufanda;
        drawable[3]=R.drawable.casa;
        drawable[4]=R.drawable.playa;
        drawable[5]=R.drawable.guitarra;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            nombre = extras.getString("nombre");
            edad = extras.getInt("edad");
            avatar=extras.getInt("avatar");
            azureEnable=extras.getBoolean("azureEnable");
            if(azureEnable)
                System.out.print("**********************");
        }


       Button buttonYes=(Button)findViewById(R.id.buttonYes);
        buttonYes.setOnClickListener(this);
        Button buttonNo=(Button)findViewById(R.id.buttonNo);
        buttonNo.setOnClickListener(this);

        Intent intent=new Intent(this,BluetoothService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonYes:
                //opcion[numero]=true;
                gustos.put(gustosString[numero],true);
                break;
            case R.id.buttonNo:
               // opcion[numero]=false;
                gustos.put(gustosString[numero],false);
                break;

        }
        if(numero==5){
       ;
            Intent intent = new Intent(this, loading_screen.class);
            intent.putExtra("map", gustos);
            intent.putExtra("edad",edad);
            intent.putExtra("nombre",nombre);
            intent.putExtra("avatar",avatar);
            intent.putExtra("azureEnable",azureEnable);
            if(azureEnable)
                System.out.print("**********************");
            startActivity(intent);
        }else {
            numero++;
            foto.setImageResource(drawable[numero]);

        }

    }
    public void goHome (View v){
        Intent intent1 = new Intent(login_screen_like.this, home_screen.class);
        startActivity(intent1);
    }

    public void BackArrow(View v) {
        Intent intent1 = new Intent(login_screen_like.this, login_screen.class);
        startActivity(intent1);
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
