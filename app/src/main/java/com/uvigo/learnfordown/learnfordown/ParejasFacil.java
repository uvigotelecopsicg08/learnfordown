package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ParejasFacil extends AppCompatActivity implements View.OnClickListener{

    private int numberOfElements;
    private int contador;

    private MemoryButtonFacil[] buttons;

    private int[] buttonGraphicLocations;
    private int[] buttonGraphics;

    private MemoryButtonFacil selectedButton1;
    private MemoryButtonFacil selectedButton2;

    private ImageButton ayuda;
    private TextView Titulo;

    private boolean isBusy = false;
    private boolean isFinished = false;

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public SoundPool sp;
    public int flujoacierto=0;
    public int flujofallo=0;
    public int flujovictoria=0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parejas_facil);

        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        ayuda = (ImageButton)findViewById(R.id.button4);
        ayuda.setOnClickListener(this);

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoacierto= sp.load(this,R.raw.acierto,2);
   //     flujofallo= sp.load(this,R.raw.error,3);
        flujovictoria= sp.load(this,R.raw.success,1);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.activity_main_2x3);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        contador = 0;
        numberOfElements = numColumns * numRows;

        buttons = new MemoryButtonFacil[numberOfElements];

        buttonGraphics = new int[numberOfElements/2];

        int[] res = {R.drawable.anillo, R.drawable.brujula, R.drawable.chaqueta, R.drawable.conejo, R.drawable.domino,
                R.drawable.esponja, R.drawable.flor, R.drawable.fresa, R.drawable.gato, R.drawable.globo,
                R.drawable.guitarra, R.drawable.jeringa, R.drawable.judias, R.drawable.lapiz, R.drawable.llavero,
                R.drawable.mariposa, R.drawable.mesa, R.drawable.miel, R.drawable.muneca, R.drawable.naranja,
                R.drawable.nido, R.drawable.nuez, R.drawable.oso, R.drawable.pimiento, R.drawable.platano,
                R.drawable.sofa, R.drawable.taza};

       /* int[] res = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
                R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
                R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.enie,
                R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s,
                R.drawable.t, R.drawable.u, R.drawable.v, R.drawable.w, R.drawable.x,
                R.drawable.y, R.drawable.z};*/

        int[] res1 = new int[26];
        int[] res2 = new int[25];
        int[] res3 = new int[24];


        asignarLetra(res, res1, 0);
        asignarLetra(res1, res2, 1);
        asignarLetra(res2, res3, 2);


        buttonGraphicLocations = new int[numberOfElements];


        shuffleButtonGraphics();

        for(int r=0; r < numRows; r++)
        {
            for(int c=0; c < numColumns; c++)
            {
                MemoryButtonFacil tempButton = new MemoryButtonFacil(this, r, c, buttonGraphics[ buttonGraphicLocations[r * numColumns + c] ]);
                tempButton.setId(generarViewId());//Generar IDs unívocos para cada botón del grid
                tempButton.setOnClickListener(this);
                buttons[r * numColumns + c] = tempButton;
                gridLayout.addView(tempButton);
            }

    }


    }



    protected void shuffleButtonGraphics(){

        Random rand = new Random();

        for(int i=0; i < numberOfElements; i++){
            buttonGraphicLocations[i] = i % (numberOfElements / 2);
        }

        for(int i=0; i < numberOfElements; i++){
            int temp = buttonGraphicLocations[i];
            int swapIndex = rand.nextInt(6);
            buttonGraphicLocations[i] = buttonGraphicLocations[swapIndex];
            buttonGraphicLocations[swapIndex] = temp;
        }

    }

    public void goHome (View v){
        Intent intent1 = new Intent(ParejasFacil.this, home_screen.class);
        startActivity(intent1);
    }

    public void BackArrow (View v){
        Intent intent1 = new Intent(ParejasFacil.this, home_screen.class);
        startActivity(intent1);
    }


    //Esta función:
    // *asigna aleatoriamente a un par de botones una letra presente en array1 y la elimina
    // *copia los elementos de array1 en array2 excepto la letra que se asignó para
    // que no pueda repetirse
    public void asignarLetra(int[] array1, int[] array2, int posicion){
        Random random = new Random();
        int rndInt = random.nextInt(array1 .length);
        buttonGraphics[posicion]=array1[rndInt];
        int j=0;
        for(int i=0; i<array1.length ; i++){
            if(i!=rndInt){
                array2[j]=array1[i];
                j++;
            }
        }
    }



    @Override
    public void onClick(View view) {

        if (isBusy)
            return;

        if(view.getId() == R.id.button4)//Si se ha pulsado el botón de ayuda
        {

            if(isFinished)
                    return;

            isBusy = true;

            for (int i=0; i < 4; i++)
            {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.ayuda_parejas_facil, null);
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.RIGHT, 150, 110);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }


            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    isBusy = false;

                }
            }, 6000);


        }else{//Si se ha pulsado algún botón del juego

        MemoryButtonFacil button = (MemoryButtonFacil) view;

        if (button.isMatched)
            return;

        if (selectedButton1 == null)
        {
            selectedButton1 = button;
            selectedButton1.flip();
            return;
        }

        if (selectedButton1.getId() == button.getId())
            return;

        if (selectedButton1.getFrontDrawableId() == button.getFrontDrawableId())
        {

            contador++;
            button.flip();

            button.setMatched(true);
            selectedButton1.setMatched(true);

            selectedButton1.setEnabled(false);
            button.setEnabled(false);

            selectedButton1 = null;

            if(contador==numberOfElements / 2)
            {
                play_victoria();
                isFinished = true;

                for (int i=0; i < 2; i++)
                {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.victoria_parejas_facil, null);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.RIGHT, 0, 110);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("AQUI");
                        finish();
                        //Do something after 100ms
                    }
                }, 4000 );
            }
            else play_acierto();

            return;
        }

        else {
            play_fallo();
            selectedButton2 = button;
            selectedButton2.flip();
            isBusy = true;

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.fallo_parejas_facil, null);
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.RIGHT, 280, 240);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedButton2.flip();
                    selectedButton1.flip();
                    selectedButton1 = null;
                    selectedButton2 = null;
                    isBusy = false;


                }
            }, 3900);


        }



        }
    }

    public static int generarViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    private void play_acierto() {
// TODO Auto-generated method stub
        sp.play(flujoacierto, 1, 1, 0, 0, 1);
    }

    private void play_fallo() {
// TODO Auto-generated method stub
        sp.play(flujofallo, 1, 1, 0, 0, 1);
    }

    private void play_victoria() {
// TODO Auto-generated method stub
        sp.play(flujovictoria, 1, 1, 0, 0, 1);
    }

}