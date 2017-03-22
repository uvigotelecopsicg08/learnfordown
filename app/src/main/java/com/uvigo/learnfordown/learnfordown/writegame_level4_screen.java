package com.uvigo.learnfordown.learnfordown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Scanner;

public class writegame_level4_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writegame_level4_screen);
    }

    public void ObtieneEscritura(){

        String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        System.out.println ("Entrada recibida por teclado es: \"" + entradaTeclado +"\"");

    }

}
