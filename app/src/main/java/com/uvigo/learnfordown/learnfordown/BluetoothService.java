package com.uvigo.learnfordown.learnfordown;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.Vector;

import static android.os.SystemClock.uptimeMillis;


public  class  BluetoothService extends Service {
    private static final long INTERVALO_ACTUALIZACION = 1000;
    private BluetoothAdapter mBluetoothAdapter;
    private String MAC_LFD = null;
    private IBinder mBinder = new LocalBinder();
    static BluetoothConnection BT;
    static Handler bluetoothIn;
    static AppCompatActivity app;
    static AppCompatActivity appAnterior;
    private Looper mServiceLooper;
    private  Handler mServiceHandler;
    final int handlerState = 0;
    private  Timer temporizador;
            private boolean pulsado;



    private static boolean BluetoothEnable=false;

    public BluetoothService(){}

    @Override
    public void onCreate() {
       // Log.d("PrinterService", "Service started");
        //super.onCreate();
        HandlerThread thread = new HandlerThread("ServiceStartArguments");
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public BluetoothService getService() {
            return BluetoothService.this;
        }
    }




/*
    public void setHandler(AppCompatActivity app){
        //Esta clase la vamos a implementar ahora en el service y hacer
        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) { // msg = mensaje que contiene descripciÃ³n y datos
                // que se pueden enviar a un handler

                if (msg.what == handlerState) {                 // msg.what = CÃ³digo de mensaje definido por el usuario para que
                    // el destinatario pueda identificar de quÃ© se trata este mensaje

                    String readMessage = (String) msg.obj;      // msg.obj = Objeto arbitrario para enviar al destinatario
                    readMessage = readMessage.trim();           // Le metimos dos espacios al envÃ­o desde ARDUINO
                    System.out.println("Soy el service **************** " + readMessage + "***************");
                       analizarEntradaBT(readMessage);
                }
            }
        };
        this.app=app;
         System.out.println(app.getClass());

    }
    */
    public void setApp(AppCompatActivity app){
        setAppAnterior(this.app);
        this.app=app;

    }
    public void setAppAnterior(AppCompatActivity app){
        this.appAnterior=app;
    }
    public void setConnection(){
       if(BluetoothEnable) {
           bluetoothIn = new Handler() {
               public void handleMessage(android.os.Message msg) { // msg = mensaje que contiene descripciÃ³n y datos
                   // que se pueden enviar a un handler

                   if (msg.what == handlerState) {                 // msg.what = CÃ³digo de mensaje definido por el usuario para que
                       // el destinatario pueda identificar de quÃ© se trata este mensaje

                       String readMessage = (String) msg.obj;      // msg.obj = Objeto arbitrario para enviar al destinatario
                       readMessage = readMessage.trim();           // Le metimos dos espacios al envÃ­o desde ARDUINO
                       System.out.println("Soy el service **************** " + readMessage + "***************");
                       analizarEntradaBT(readMessage);
                   }
               }
           };

           BT = new BluetoothConnection(app.getApplicationContext(), bluetoothIn);
           if (!BT.configurarBluetooth()) {
               Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
               app.startActivityForResult(enableBtIntent, BT.getREQUEST_ENABLE_BT());
           }
           BT.obtenerListaDispositivos();
           MAC_LFD = BT.buscaDispositivo();
           if (MAC_LFD == null)
               Toast.makeText(getBaseContext(), "No se ha encontrado el dispositivo", Toast.LENGTH_LONG).show();
           else BT.conectarBluetooth();
       }
    }
    public void lanzarTemporizador() {
        temporizador = new Timer();
        temporizador.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                app.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_DPAD_RIGHT));
                pulsado=false;
                this.cancel();

            }
        }, 0, INTERVALO_ACTUALIZACION);
    }
    public void analizarEntradaBT(String entrada){
        Intent intent=null;
        switch (entrada){
            //Boton de EXIT
            case "exit":
                System.out.print(app.getClass());
                app.finish();
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            //Boton de HOME

            case "home":
                if(!app.getClass().toString().contains("home_screen")) {
                    System.out.print(app.getClass());
                    intent = new Intent(app, home_screen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;

            //Boton de atras

            case "back":
                intent = null;
                if(!app.getClass().toString().contains("home_screen")){
                    if(app.getClass().toString().contains("menu_screen")) {
                        System.out.print(app.getClass());
                       intent  = new Intent(app,home_screen.class);
                    }else{
                        if(isMenuIntermedio()){
                            intent= new Intent(app,menu_screen.class);
                        }
                        else{
                        System.out.print(app.getClass());
                        intent= new Intent(app,appAnterior.getClass());
                        }
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;


            //Boton de ayuda

            case "help":
                 intent = null;
                if(app.getClass().toString().contains("writegame_level1_screen")) {
                    System.out.print(app.getClass());
                    writegame_level1_screen wl = (writegame_level1_screen) app;
                    wl.Help.performClick();
                }
                break;
            case "left":
                if(app.getClass().toString().contains("UnityPlayerActivity")) {
                    System.out.print(app.getClass());
                    app.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT));
              //      app.dispatchKeyEvent(new  KeyEvent (uptimeMillis(),uptimeMillis(), KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT, 1));
                 //   UnityPlayer.UnitySendMessage("Pencil", "Left", "izquierda");

                }
                break;
            case "right":

                if(app.getClass().toString().contains("UnityPlayerActivity")) {
                    System.out.print(app.getClass());
               //     UnityPlayer.UnitySendMessage("Pencil", "Right", "derecha");
                  //  if(!pulsado) {

                        app.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_RIGHT));
                   //     lanzarTemporizador();
                  //      pulsado=true;
                   // }


                }
                break;
            case "game":

                if(app.getClass().toString().contains("UnityPlayerActivity")) {
                    System.out.print(app.getClass());
              //      UnityPlayer.UnitySendMessage("Pencil", "Salto", "");
                    app.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP));

                    //    app.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP));
                //    app.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_UP));



                }
                break;


        }

    }
    private boolean isMenuIntermedio(){
        if(app.getClass().toString().contains("frasein_screen")||app.getClass().toString().contains("frasetra_screen")||app.getClass().toString().contains("frasedi_screen")){
            return true;
        }
        else{
            if(app.getClass().toString().contains("palabrasin_screen")||app.getClass().toString().contains("palabrastra_screen")||app.getClass().toString().contains("palabrasdi_screen")){
                return  true;
            }
            else{
                if(app.getClass().toString().contains("siinversas_screen")||app.getClass().toString().contains("sitrabadas_screen")||app.getClass().toString().contains("sidirectas_screen")){
                    return  true;
                }
                else{
                    return false;
                }
            }
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        /*
        Log.d("PrinterService", "Onstart Command");
        BT = new BluetoothConnection(this.getApplicationContext(),bluetoothIn);
        if (mBluetoothAdapter != null) {
            device = (BluetoothClass.Device) intent.getSerializableExtra(BT_DEVICE);
            deviceName = device.getDeviceName();
            String macAddress = device.getMacAddress();
            if (macAddress != null && macAddress.length() > 0) {
                connectToDevice(macAddress);
            } else {
                stopSelf();
                return 0;
            }
        }
        String stopservice = intent.getStringExtra("stopservice");
        if (stopservice != null && stopservice.length() > 0) {
            stop();
        }
        */

        return START_STICKY;
    }
/*
    private synchronized void connectToDevice(String macAddress) {
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(macAddress);
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {
                mConnectThread.cancel();
                mConnectThread = null;
            }
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        mConnectThread = new ConnectThread(device);
        mConnectThread.start();
        setState(STATE_CONNECTING);
    }

    private void setState(int state) {
        BluetoothService.mState = state;
        if (mHandler != null) {
            mHandler.obtainMessage(AbstractActivity.MESSAGE_STATE_CHANGE, state, -1).sendToTarget();
        }
    }
*/
    public synchronized void stop() {
        /*
        setState(STATE_NONE);
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
        }
        */
        stopSelf();
    }

    @Override
    public boolean stopService(Intent name) {
        /*
        setState(STATE_NONE);
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        mBluetoothAdapter.cancelDiscovery();
        */
        return super.stopService(name);
    }
/*
    private void connectionFailed() {

        BluetoothService.this.stop();
        Message msg = mHandler.obtainMessage(AbstractActivity.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(AbstractActivity.TOAST, getString(R.string.error_connect_failed));
        msg.setData(bundle);
        mHandler.sendMessage(msg);

    }
    */
/*
    private void connectionLost() {
        BluetoothService.this.stop();
        Message msg = mHandler.obtainMessage(AbstractActivity.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(AbstractActivity.TOAST, getString(R.string.error_connect_lost));
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }
    */

    /*
    private static Object obj = new Object();

    public static void write(byte[] out) {
        // Create temporary object
        ConnectedThread r;
        // Synchronize a copy of the ConnectedThread
        synchronized (obj) {
            if (mState != STATE_CONNECTED)
                return;
            r = mConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }
*/
    /*
    private synchronized void connected(BluetoothSocket mmSocket, BluetoothDevice mmDevice) {
        // Cancel the thread that completed the connection
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        mConnectedThread = new ConnectedThread(mmSocket);
        mConnectedThread.start();

        // Message msg =
        // mHandler.obtainMessage(AbstractActivity.MESSAGE_DEVICE_NAME);
        // Bundle bundle = new Bundle();
        // bundle.putString(AbstractActivity.DEVICE_NAME, "p25");
        // msg.setData(bundle);
        // mHandler.sendMessage(msg);
        setState(STATE_CONNECTED);

    }
*/
    /*
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            this.mmDevice = device;
            BluetoothSocket tmp = null;
            try {
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(SPP_UUID));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmSocket = tmp;
        }

        @Override
        public void run() {
            setName("ConnectThread");
            mBluetoothAdapter.cancelDiscovery();
            try {
                mmSocket.connect();
            } catch (IOException e) {
                try {
                    mmSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                connectionFailed();
                return;

            }
            synchronized (BluetoothService.this) {
                mConnectThread = null;
            }
            connected(mmSocket, mmDevice);
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e("PrinterService", "close() of connect socket failed", e);
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        byte[] readBuffer;
        int readBufferPosition;
        int counter;
        volatile boolean stopWorker;


        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }




        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);         //read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes

            try {
                mmOutStream.write(msgBuffer);
                Toast.makeText(app, "Conectado con LEARN_FOR_DOWN", Toast.LENGTH_LONG).show();
                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                //Toast.makeText(app, "La conexión falló", Toast.LENGTH_LONG).show();


            }
        }
    }
    */
    public static void setBluetoothEnable(boolean bluetoothEnable) {
        BluetoothEnable = bluetoothEnable;
    }

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                System.out.print(msg.toString());
            } catch (Exception e) {
                e.printStackTrace();
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1);
        }
    }
}

