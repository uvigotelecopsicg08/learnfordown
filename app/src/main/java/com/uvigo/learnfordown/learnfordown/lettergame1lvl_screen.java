package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.support.v7.widget.RecyclerView;

public class lettergame1lvl_screen extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    String Correcta;
    TextView titulo,letracorrecta;
    ImageButton BackArrow,Home;
    ImageView palabra;
    GestionNiveles  gn;
    String tipoNivel="leerletras";
    ArrayList<FotoPalabra> fp;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame1lvl_screen);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        BackArrow = (ImageButton) findViewById(R.id.button3);
        Home = (ImageButton) findViewById(R.id.button5);
        palabra= (ImageView)findViewById(R.id.imageView2);
        letracorrecta=(TextView)findViewById(R.id.textView4);
        titulo.setTypeface(face);

        Context context = this.getApplicationContext();
        gn = new GestionNiveles(context);
        gn.setNivel(tipoNivel,1);
        fp=gn.getFotos();
        
          horizontalList = new ArrayList<String>();
          gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList);
        Collections.shuffle( horizontalList);
            palabra.setImageResource(fp.get(i).getFoto());
         letracorrecta.setText(fp.get(i).getLetra().toUpperCase());
          Correcta= fp.get(i).getLetra().toUpperCase();

          horizontalAdapter = new HorizontalAdapter(horizontalList);

          LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame1lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
          horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);


          horizontal_recycler_view.setAdapter(horizontalAdapter);









    }



    public void BackArrow (View v){
        Intent intent1 = new Intent(lettergame1lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(lettergame1lvl_screen.this, home_screen.class);
        startActivity(intent1);
    }
    public void ButtonCheck (View v){
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        if (Correcta.equals(buttonText)){
            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                    -50.0f, 0.0f);
            animation.setDuration(2000);
            animation.setFillAfter(true);
            b.startAnimation(animation);

            gn.acierto();
            if(!gn.isnivelCompletado()) {
                i++;
                cambiarFoto();
            }
            else{
                System.out.print("el nivel esta finalizado");
                gn.avanzaNivel();
                if(gn.getDificultad()!=1 ||!(gn.getTipo().equals(tipoNivel))){
                    System.out.println("Se debe abrir otra pantalla porque esta ya no vale");
                    //Código para abrir otra pantalla
                }
               else {
                    fp= gn.getFotos();
                    i=0;
                    cambiarFoto();
                    System.out.println("Se debe avanzar el nivel");
                }


            }
//Codigo de Animacion Acierto
        } else{
            //Codigo de Animacion Fallo
            gn.fallo();
            System.out.println("Se ha anotado un fallo");


        }
    }

    private void cambiarFoto() {
        horizontalList.clear();
        horizontalList = new ArrayList<String>();
        gn.rellenarConletras(fp.get(i).getLetra().toUpperCase(),horizontalList);
        Collections.shuffle(horizontalList);
        palabra.setImageResource(fp.get(i).getFoto());
        letracorrecta.setText(fp.get(i).getLetra().toUpperCase());
        Correcta= fp.get(i).getLetra().toUpperCase();
        horizontalAdapter = new HorizontalAdapter(horizontalList);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame1lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);


        horizontal_recycler_view.setAdapter(horizontalAdapter);
    }

}
