package com.uvigo.learnfordown.learnfordown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.Scanner;

public class writegame_level4_screen extends AppCompatActivity {
    EditText Texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writegame_level4_screen);
        Texto = (EditText) findViewById(R.id.teclado);
        Texto.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //YOUR CODE
                 EditText Texto1= (EditText)v;
                CompruebaEntrada(Texto1.getText().toString());
                return false;
            }
        });
    }

    public void ObtieneEscritura(){

        String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        System.out.println ("Entrada recibida por teclado es: \"" + entradaTeclado +"\"");

    }
  /*  @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        CompruebaEntrada(Texto.getText().toString());
        return super.onKeyDown(keyCode, event);
    }*/


    public void CompruebaEntrada (String Entrada){
        System.out.println("***** "+Entrada+" *****");

    }
}
