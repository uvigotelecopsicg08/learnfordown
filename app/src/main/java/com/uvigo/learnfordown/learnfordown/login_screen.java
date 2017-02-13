package com.uvigo.learnfordown.learnfordown;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class login_screen extends AppCompatActivity {
    TextView textoNombre,textoEdad;
    CheckBox playa,musica,coches,ropa,casas,animales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        textoNombre = (TextView) findViewById(R.id.editTextNombre);
        textoEdad = (TextView) findViewById(R.id.editTextEdad);
      /*  playa= (CheckBox) findViewById(R.id.checkBoxPlaya);
        musica= (CheckBox) findViewById(R.id.checkBoxMusica);
        casas = (CheckBox) findViewById(R.id.checkBoxCasas);
        coches = (CheckBox) findViewById(R.id.checkBoxCoches);
        ropa = (CheckBox) findViewById(R.id.checkBoxRopa);
        animales = (CheckBox) findViewById(R.id.checkBoxAnimales);
        */
    }
    void textNombreUp(View v){
        textoNombre.setVisibility(View.VISIBLE);
        }
      void textEdadUp(View v){
        textoEdad.setVisibility(View.VISIBLE);

    }
    /*
    void gustosCheckUp(View v){
        playa.setVisibility(View.VISIBLE);
        musica.setVisibility(View.VISIBLE);
        coches.setVisibility(View.VISIBLE);
        ropa.setVisibility(View.VISIBLE);
        animales.setVisibility(View.VISIBLE);

    }
*/
    void registar(){
        playa.isChecked();
        String nombre= (String) textoNombre.getText();
        String edadString = (String) textoNombre.getText();
        int edad= Integer.parseInt(edadString);
        System.out.println("  "+nombre+"   "+edad);


    }



}
