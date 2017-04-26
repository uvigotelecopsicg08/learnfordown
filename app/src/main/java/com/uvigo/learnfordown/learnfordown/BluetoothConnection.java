package com.uvigo.learnfordown.learnfordown;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Susana on 22/04/2017.
 */

public class BluetoothConnection {


    final int REQUEST_ENABLE_BT = 1;
    final int handlerState = 0;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String MAC_LFD = null;

    BluetoothAdapter BA; // Punto de entrada de cualquier iteración de BLUETOOTH
    BluetoothSocket BS = null;

    ConnectedThread mConnectedThread;
    Handler bluetoothIn;

    HashMap <String, String> dispositivosSincronizados = new HashMap();

    Context app;

    public BluetoothConnection(Context app, Handler bluetoothIn) {

        this.app = app;
        this.bluetoothIn = bluetoothIn;
    }

    public int getREQUEST_ENABLE_BT() {
        return REQUEST_ENABLE_BT;
    }



    public void obtenerListaDispositivos(){

        Set<BluetoothDevice> pairedDevices =BA.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                dispositivosSincronizados.put(device.getName(), device.getAddress());
            }
        }
    }

    public String buscaDispositivo(){

        if (dispositivosSincronizados.containsKey("LEARN_FOR_DOWN")){
            MAC_LFD = dispositivosSincronizados.get("LEARN_FOR_DOWN");

        }


        return MAC_LFD;
    }

    public void conectarBluetooth() {

        BluetoothDevice device = BA.getRemoteDevice(MAC_LFD);

        try {
            BS = device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        } catch (IOException e) {

        }

        try {
            BS.connect();
        } catch (IOException e) {



            try {
                BS.close();
            } catch (IOException e2)
            {

            }
        }

        mConnectedThread = new ConnectedThread(BS);
        mConnectedThread.start();
        //I send a character when resuming.beginning transmission to check device is connected
        //If it is not an exception will be thrown in the write method and finish() will be called
        mConnectedThread.write("x");

    }



    public boolean configurarBluetooth(){

        BA = BluetoothAdapter.getDefaultAdapter(); // BA nos permite establecer comunicación BLUETOOTH
        if (BA != null) {
            if (!BA.isEnabled()) {
                // El Bluetooth está apagado, solicitamos permiso al usuario para iniciarlo
                return false;
            }else return true;

        } else{
            Toast.makeText(app,"Su dispositivo no soporta Bluetooth", Toast.LENGTH_LONG);
            return true;
        }
    }


    public void cerrarSocketBT(){
        try {
            if (BS!= null) BS.close(); // Cerramos el socket
        } catch (Exception e) {
        }
    }








    // ****************************************************************************************************************************************************

    public class ConnectedThread extends Thread {

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
}
