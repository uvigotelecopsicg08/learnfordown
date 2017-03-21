package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class palabrasgame1_2lvl_screen extends AppCompatActivity {
    TextView titulo;
    Integer ultimoPulsado;
    final HashMap<Integer, Float> thresholds = new HashMap<>();
    RatingBar ratingbar1 = null;
    String figure = "plato";
    HashMap<Integer,String>  map;
    GestionNiveles  gn;
    String tipoNivel="palabrassilabasdirectas";
    ArrayList<FotoPalabra> fp;
    int i=0;
    int contador;
    ImageButton imageButton1,imageButton2,imageButton3;
    Button button1,button2,button3;
    int aciertos=0;
    int nivel;
    boolean cambiado=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabrasgame1_2lvl_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);
        ultimoPulsado = null;
         ratingbar1 = (RatingBar) findViewById(R.id.ratingBar);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);

        button1 = (Button)  findViewById(R.id.button1);
        button2 = (Button)  findViewById(R.id.button2);
        button3 = (Button)  findViewById(R.id.button3);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tipoNivel = extras.getString("tipoSilaba");
            nivel = extras.getInt("nivel");
            System.out.println(tipoNivel);
        }

        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        gn.setNivel(tipoNivel,nivel);
        fp=gn.getFotos();
        cambiarFoto();


        thresholds.clear();
        thresholds.put(1, 1f); // 1 acierto, 1 estrella
        thresholds.put(10, 2f); //10 aciertos, 2 estrellas
        thresholds.put(25, 3f); //25 aciertos, 3 estrellas
        thresholds.put(45, 4f); //45 aciertos, 4 estrellas
        thresholds.put(65, 5f); //65 aciertos, 5 estrellas
        thresholds.put(80, 6f); //80 aciertos, 6 estrellas

    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(palabrasgame1_2lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(palabrasgame1_2lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }

    public void pulsar (View v){
        Log.i("pulsar()", v.getId() + " ultimoPulsado:" +  ultimoPulsado);
        if(ultimoPulsado != null) {/*
            switch (v.getId()) {
                case R.id.imageButton1:
                    if(map.get(R.id.imageButton1).equals(map.get(ultimoPulsado))){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.imageButton2:
                    if(map.get(R.id.imageButton2).equals(map.get(ultimoPulsado))){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.imageButton3:
                    if(map.get(R.id.imageButton3).equals(map.get(ultimoPulsado))){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.button1:
                    if(map.get(R.id.button1).equals(map.get(ultimoPulsado))){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.button2:
                    if(map.get(R.id.button2).equals(map.get(ultimoPulsado))){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
                case R.id.button3:
                    if(map.get(R.id.button3).equals(map.get(ultimoPulsado))){
                        Log.i("pulsar()", "CORRECTO!");
                        Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show();
                        contador++;
                        float rating = 0;
                        for (int i : new TreeSet<>(thresholds.keySet())) {
                            if(contador < i) {
                                break;
                            }
                            rating = thresholds.get(i);
                        }
                        ratingbar1.setRating(rating);

                    }
                    break;
            }



*/
            if (v.getId()!=(ultimoPulsado)){
                if (map.get(v.getId()).equals(map.get(ultimoPulsado))) {
                    if (findViewById(v.getId()) instanceof Button) {
                        Button b1 = (Button) findViewById(v.getId());
                        b1.setEnabled(false);
                        ImageButton b2 = (ImageButton) findViewById(ultimoPulsado);
                        b2.setEnabled(false);
                    } else {
                        Button b1 = (Button) findViewById(ultimoPulsado);
                        b1.setEnabled(false);
                        ImageButton b2 = (ImageButton) findViewById(v.getId());
                        b2.setEnabled(false);
                    }
                    contador++;
                    float rating = 0;
                    for (int i : new TreeSet<>(thresholds.keySet())) {
                        if (contador < i) {
                            break;
                        }
                        rating = thresholds.get(i);
                    }
                    if (rating != ratingbar1.getRating()) {
                        ratingbar1.setRating(rating);
                        Toast toast = Toast.makeText(this, "¡HAS CONSEGUIDO UNA ESTRELLITA!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, -180, -50);
                        toast.show();
                    }
                    gn.acierto();
                    aciertos++;

                    if (aciertos == 3) {
                        aciertos = 0;
                        if (!gn.isnivelCompletado()) {
                            i += 3;
                            cambiarFoto();
                            cambiado=true;
                        } else {
                            System.out.print("el nivel esta finalizado");
                            gn.avanzaNivel();
                            cambiado=true;
                            if (!(gn.getTipo().equals(tipoNivel))) {
                                System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
                                //Código para abrir otra pantalla
                                Intent intent=null;
                                String strName=null;
                                if(!gn.getTipo().contains("palabra")) {
                                    intent = new Intent(palabrasgame1_2lvl_screen.this, silabasgame1lvl_screen.class);
                                    if(gn.getTipo().contains("inversas")) {
                                        strName = "silabasinversas";
                                    }
                                    else{
                                        strName = "trabadas";
                                    }
                                    intent.putExtra("tipoSilaba", strName);
                                }
                                else{
                                    intent = new Intent(palabrasgame1_2lvl_screen.this, frasegame1lvl_screen.class);
                                    strName = "frasessilabasdirectas";
                                    intent.putExtra("tipoSilaba", strName);
                                    intent.putExtra("nivel",1);
                                }
                                startActivity(intent);

                            } else {
                                fp = gn.getFotos();
                                i = 0;
                                cambiarFoto();
                                System.out.println("Se debe avanzar el nivel");


                            }
                        }
                    }
                } else {
                    gn.fallo();
                }
            }
        }
        if(!cambiado) {
            ultimoPulsado = v.getId();
            System.out.println("has pulsado: "+map.get(ultimoPulsado));
        }

        else{
            ultimoPulsado=0;
            cambiado=false;
        }

    }

    private void cambiarFoto() {
        map = new HashMap<>();
        ArrayList<Integer> arrayImageResource= new ArrayList();
        arrayImageResource.add(fp.get(0+i).getFoto());
        arrayImageResource.add(fp.get(1+i).getFoto());
        arrayImageResource.add(fp.get(2+i).getFoto());
        Collections.shuffle(arrayImageResource);
        imageButton1.clearFocus();
        imageButton1.setEnabled(true);
        imageButton2.setEnabled(true);
        imageButton3.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

    imageButton1.clearFocus();
        imageButton1.setImageResource(arrayImageResource.get(0));
        imageButton2.clearFocus();
        imageButton2.setImageResource(arrayImageResource.get(1));
        imageButton3.clearFocus();
        imageButton3.setImageResource(arrayImageResource.get(2));

        if(fp.get(0+i).getFoto()==arrayImageResource.get(0)){
            map.put(R.id.imageButton1,fp.get(0+i).getPalabra());
        }else {
            if(fp.get(0+i).getFoto()==arrayImageResource.get(1)){
                map.put(R.id.imageButton2,fp.get(0+i).getPalabra());
            }
            else{
                if(fp.get(0+i).getFoto()==arrayImageResource.get(2)) {
                    map.put(R.id.imageButton3, fp.get(0+i).getPalabra());
                }
            }
        }

        if(fp.get(1+i).getFoto()==arrayImageResource.get(0)){
            map.put(R.id.imageButton1,fp.get(1+i).getPalabra());
        }else {
            if(fp.get(1+i).getFoto()==arrayImageResource.get(1)){
                map.put(R.id.imageButton2,fp.get(1+i).getPalabra());
            }
            else{
                if(fp.get(1+i).getFoto()==arrayImageResource.get(2)) {
                    map.put(R.id.imageButton3, fp.get(1+i).getPalabra());
                }
            }
        }
        if(fp.get(2+i).getFoto()==arrayImageResource.get(0)){
            map.put(R.id.imageButton1,fp.get(2+i).getPalabra());
        }else {
            if(fp.get(2+i).getFoto()==arrayImageResource.get(1)){
                map.put(R.id.imageButton2,fp.get(2+i).getPalabra());
            }
            else{
                if(fp.get(2+i).getFoto()==arrayImageResource.get(2)) {
                    map.put(R.id.imageButton3, fp.get(2+i).getPalabra());
                }
            }
        }


        ArrayList<String> arrayText= new ArrayList();
        arrayText.add(fp.get(0+i).getPalabra());
        arrayText.add(fp.get(1+i).getPalabra());
        arrayText.add(fp.get(2+i).getPalabra());
        Collections.shuffle(arrayText);
        button1.setText(arrayText.get(0).toUpperCase());
        button2.setText(arrayText.get(1).toUpperCase());
        button3.setText(arrayText.get(2).toUpperCase());


        if(fp.get(0+i).getPalabra()==arrayText.get(0)){
            map.put(R.id.button1,fp.get(0+i).getPalabra());
        }else {
            if(fp.get(0+i).getPalabra()==arrayText.get(1)){
                map.put(R.id.button2,fp.get(0+i).getPalabra());
            }
            else{
                if(fp.get(0+i).getPalabra()==arrayText.get(2)){
                    map.put(R.id.button3, fp.get(0+i).getPalabra());
                }
            }
        }

        if(fp.get(1+i).getPalabra()==arrayText.get(0)){
            map.put(R.id.button1,fp.get(1+i).getPalabra());
        }else {
            if(fp.get(1+i).getPalabra()==arrayText.get(1)){
                map.put(R.id.button2,fp.get(1+i).getPalabra());
            }
            else{
                if(fp.get(1+i).getPalabra()==arrayText.get(2)) {
                    map.put(R.id.button3, fp.get(1+i).getPalabra());
                }
            }
        }
        if(fp.get(2+i).getPalabra()==arrayText.get(0)){
            map.put(R.id.button1,fp.get(2+i).getPalabra());
        }else {
            if(fp.get(2+i).getPalabra()==arrayText.get(1)){
                map.put(R.id.button2,fp.get(2+i).getPalabra());
            }
            else{
                if(fp.get(2+i).getPalabra()==arrayText.get(2)) {
                    map.put(R.id.button3, fp.get(2+i).getPalabra());
                }
            }
        }
    }

}
