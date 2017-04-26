package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
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

public class ParejasDificil extends AppCompatActivity implements View.OnClickListener{

    private int numberOfElements;
    private int contador;

    private MemoryButtonDificil[] buttons;

    private int[] buttonGraphicLocations;
    private int[] buttonGraphics;

    private MemoryButtonDificil selectedButton1;
    private MemoryButtonDificil selectedButton2;

    private TextView Titulo;
    private ImageButton ayuda;

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
        setContentView(R.layout.parejas_dificil);


        Titulo = (TextView) findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Berlin Sans FB Demi Bold.ttf");
        Titulo.setTypeface(face);

        ayuda = (ImageButton)findViewById(R.id.button4);
        ayuda.setOnClickListener(this);

        //sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder()
                    .build();
        } else {
            sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoacierto= sp.load(this,R.raw.acierto,2);
        flujofallo= sp.load(this,R.raw.disparo,3);
        flujovictoria= sp.load(this,R.raw.success,1);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.activity_main_3x4);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        contador = 0;
        numberOfElements = numColumns * numRows;

        buttons = new MemoryButtonDificil[numberOfElements];

        buttonGraphics = new int[numberOfElements/2];

        int[] res = {R.drawable.parejasajedrez, R.drawable.parejasbaloncesto, R.drawable.parejasbanador, R.drawable.parejasbarco,
                R.drawable.parejasbota, R.drawable.parejascamara, R.drawable.parejascompas, R.drawable.parejasdados, R.drawable.parejasestuche,
                R.drawable.parejasgafas, R.drawable.parejasgaita, R.drawable.parejasgaviota, R.drawable.parejasgrifo, R.drawable.parejashuron,
                R.drawable.parejasmacarrones, R.drawable.parejasmango, R.drawable.parejasparaguas, R.drawable.parejaspera, R.drawable.parejaspinata,
                R.drawable.parejasrastrillo, R.drawable.parejassandia, R.drawable.parejassilbato, R.drawable.parejassirena, R.drawable.parejastambor,
                R.drawable.parejastornillo, R.drawable.parejasvieiras, R.drawable.parejasviolin};




        int[] res1 = new int[26];
        int[] res2 = new int[25];
        int[] res3 = new int[24];
        int[] res4 = new int[23];
        int[] res5 = new int[22];
        int[] res6 = new int[21];


        asignarLetra(res, res1, 0);
        asignarLetra(res1, res2, 1);
        asignarLetra(res2, res3, 2);
        asignarLetra(res3, res4, 3);
        asignarLetra(res4, res5, 4);
        asignarLetra(res5, res6, 5);


        buttonGraphicLocations = new int[numberOfElements];


        shuffleButtonGraphics();

        for(int r=0; r < numRows; r++)
        {
            for(int c=0; c < numColumns; c++)
            {
                MemoryButtonDificil tempButton = new MemoryButtonDificil(this, r, c, buttonGraphics[ buttonGraphicLocations[r * numColumns + c] ]);
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

    public void goHome (View v){
        Intent intent1 = new Intent(ParejasDificil.this, home_screen.class);
        startActivity(intent1);
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(ParejasDificil.this, home_screen.class);
        startActivity(intent1);
    }

    @Override
    public void onClick(View view) {

        if (isBusy)
            return;

        if(view.getId() == R.id.button4) //Si se ha pulsado el botón de ayuda
        {

            if(isFinished)
                return;

            isBusy = true;

            for (int i=0; i < 4; i++)
            {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.ayuda_parejas_dificil, null);
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.RIGHT, 50, 110);
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

        MemoryButtonDificil button = (MemoryButtonDificil) view;

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
                    View layout = inflater.inflate(R.layout.popup_victoria_parejas, null);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.RIGHT, 100, 50);
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
            View layout = inflater.inflate(R.layout.popup_fallo_parejas, null);
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.RIGHT, 100, 50);
            toast.setDuration(Toast.LENGTH_SHORT);
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
            }, 2000);


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