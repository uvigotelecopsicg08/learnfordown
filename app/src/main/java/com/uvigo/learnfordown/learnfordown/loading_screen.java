package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import java.io.File;
import java.util.HashMap;

public class loading_screen extends AppCompatActivity {

    String nombre;
    int edad;
    int avatar;
    boolean azureEnable=false;
    AzureUser azure ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        System.out.println("LoadingScreenActivity  screen started");
        ProgressBar s = (ProgressBar )findViewById(R.id.mainSpinner1);
        s.setVisibility(View.VISIBLE);
        azure= new AzureUser(this);
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(500);  //Delay of 0.5 seconds
                    Intent intent = getIntent();
                    Bundle extras = intent.getExtras();
                    if(extras != null) {
                        nombre = extras.getString("nombre");
                        edad = extras.getInt("edad");
                        avatar= extras.getInt("avatar");
                        azureEnable=extras.getBoolean("azureEnable");
                        if(azureEnable)
                            System.out.println("esta habilitado azure");

                        HashMap<String,Boolean> gustos = (HashMap<String,Boolean>)intent.getSerializableExtra("map");
                        Context context =getApplicationContext();


                   //     context.deleteDatabase("learn.sqlite");
                        File dbFile = context.getDatabasePath("learn.sqlite");
                        //Aun que parezca un poco repetitivo  no se puede crear una instancia de insertData fuera del if
                        // De no ser asi se generaria un archivo sqlite y el if no tendria sentido
                        if(!dbFile.exists()) {
                            InsertData iD = new InsertData(context);
                            iD.insertar_niveles();
                            iD.insert_usuario(nombre, edad, gustos,avatar);
                            if(azureEnable){
                                //LLamar al bloque de codigo
                              String mId=  azure.addItem(nombre,edad);
                                if(mId!=null){
                                    iD.loggedAzure(mId);
                                }
                            }
                        }
                        else {
                            InsertData iD = new InsertData(context);
                            iD.insert_usuario(nombre, edad, gustos,avatar);
                            if(azureEnable){
                                //LLamar al bloque de codigo
                                String mId=  azure.addItem(nombre,edad);
                                if(mId!=null){
                                    iD.loggedAzure(mId);
                                }



                            }
                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    Intent intent = new Intent(loading_screen.this,home_screen.class);
                    loading_screen.this.startActivity(intent);
                    loading_screen.this.finish();
                }
            }


        };
        welcomeThread.start();

    }

}
