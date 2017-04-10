package com.uvigo.learnfordown.learnfordown;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Puzzle4piezas extends AppCompatActivity {
    ArrayList<Integer> verticalList;
    ArrayList<Bitmap> PrimeraColumna;
    HashMap<Integer,Bitmap> Imagenes = new HashMap<>();
    HashMap<Integer,Integer> Piezas = new HashMap<>();
    TextView mensaje;
    private LayoutInflater layoutInflater;
    private PopupWindow popupWindow;
    int acierto;
    int posicion;
    boolean clickPieza = false;
    boolean clickPuzzle = false;
    boolean ganaste = false;
    boolean huboacierto = false;
    boolean aciertoPieza = false;
    boolean aciertoPuzzle = false;
    int id_imagen;
    ArrayList<Bitmap> SegundaColumna;
    ArrayList<Bitmap> TerceraColumna;
    int LastClick;
    int IDpieza;
    int ActualClick;
    TextView texto;
   /* private RecyclerView vertical_recycler_view;
    private RecyclerView vertical_recycler_view1;
    private RecyclerView vertical_recycler_view2;
    private VerticalAdapter verticalAdapter;
    private VerticalAdapter verticalAdapter1;
    private VerticalAdapter verticalAdapter2;*/
    //Imagenes finales piezas
    ImageView imagen;
    ImageView imagen2;
    ImageView imagen3;
    ImageView imagen4;
    //imagenes piezas vacias
    ImageView imagen5;
    ImageView imagen6;
    ImageView imagen7;
    ImageView imagen8;
    //imagenes piezas rellenas
    ImageView imagen9;
    ImageView imagen10;
    ImageView imagen11;
    ImageView imagen12;
    RelativeLayout relativeLayout;

    Button button1,button2,button3,button4;

    public SoundPool sp;
    public int flujoacierto=0;
    public int flujofallo=0;
    public int flujovictoria=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle4piezas);
        relativeLayout=(RelativeLayout) findViewById(R.id.activity_puzzle4piezas);
        imagen = (ImageView)findViewById(R.id.imageView);
        imagen2 = (ImageView)findViewById(R.id.imageView3);
        imagen3 = (ImageView)findViewById(R.id.imageView5);
        imagen4 = (ImageView)findViewById(R.id.imageView6);
        texto = (TextView) findViewById(R.id.textView2);
        int rand =(int) (Math.random() * 9);
        id_imagen = this.getResources().getIdentifier("puzzle"+rand, "drawable", this.getPackageName());


        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        texto.setTypeface(face);


        imagen5 = (ImageView)findViewById(R.id.imageView10);

        mensaje =(TextView)findViewById(R.id.textView);
        mensaje.setTypeface(face);
        imagen9 = (ImageView)findViewById(R.id.imageView8);
        imagen10 = (ImageView)findViewById(R.id.imageView7);
        imagen11 = (ImageView)findViewById(R.id.imageView15);
        imagen12 = (ImageView)findViewById(R.id.imageView4);

        imagen.setVisibility(View.GONE);
        imagen2.setVisibility(View.GONE);
        imagen3.setVisibility(View.GONE);
        imagen4.setVisibility(View.GONE);
        imagen5.setVisibility(View.VISIBLE);


        //BOTONES INVISBLES
        button1 = (Button)findViewById(R.id.button2);
        button2 = (Button)findViewById(R.id.button7);
        button3 = (Button)findViewById(R.id.button);
        button4 = (Button)findViewById(R.id.button6);
        button1.setBackgroundColor(Color.TRANSPARENT);
        button2.setBackgroundColor(Color.TRANSPARENT);
        button3.setBackgroundColor(Color.TRANSPARENT);
        button4.setBackgroundColor(Color.TRANSPARENT);
        mensaje.setVisibility(View.GONE);

        // imagen5.setVisibility(View.GONE);
        // imagen6.setVisibility(View.GONE);
        // imagen7.setVisibility(View.GONE);
        // imagen8.setVisibility(View.GONE);


      /*  imagen.setImageBitmap(CreatePiece(R.drawable.a_part13));
        imagen2.setImageBitmap(CreatePiece(R.drawable.a_part21));
        imagen3.setImageResource(R.drawable.a_layout);*/

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoacierto= sp.load(this,R.raw.acierto,2);
        flujofallo= sp.load(this,R.raw.error,3);
        flujovictoria= sp.load(this,R.raw.success,1);

        verticalList=new ArrayList<>();

        verticalList.add(R.drawable.piezas_part1);
        verticalList.add(R.drawable.piezas_part2);
        verticalList.add(R.drawable.piezas_part3);
        verticalList.add(R.drawable.piezas_part4);
        Collections.shuffle(verticalList);
        for (int i =0; i<4; i++){
            switch (i){
                case 0:
                    imagen9.setImageBitmap(CreatePiece(verticalList.get(0)));
                    imagen9.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(imagen9.getId(),posicion);
                    break;
                case 1:
                    imagen10.setImageBitmap(CreatePiece(verticalList.get(0)));
                    imagen10.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                    verticalList.remove(0);
                    Piezas.put(imagen10.getId(),posicion);

                    break;
                case 2:
                    imagen11.setImageBitmap(CreatePiece(verticalList.get(0)));
                    imagen11.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(imagen11.getId(),posicion);

                    break;
                case 3:
                    imagen12.setImageBitmap(CreatePiece(verticalList.get(0)));
                    imagen12.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(imagen12.getId(),posicion);

                    break;

            }

        }
  /*      for (int i =0; i<2; i++){
            SegundaColumna.add(CreatePiece(verticalList.get(0)));
            verticalList.remove(0);

        }*/
     /*   for (int i =0; i<3; i++){
            TerceraColumna.add(CreatePiece(verticalList.get(0)));
            verticalList.remove(0);

        }*/

        //      verticalAdapter=new VerticalAdapter(PrimeraColumna);
   /*     verticalAdapter1=new VerticalAdapter(SegundaColumna);
       verticalAdapter2=new VerticalAdapter(PrimeraColumna);

    /*    LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        vertical_recycler_view.setLayoutManager(verticalLayoutmanager);*/

 /*       LinearLayoutManager verticalLayoutmanager1
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        vertical_recycler_view1.setLayoutManager(verticalLayoutmanager1);
        LinearLayoutManager verticalLayoutmanager2
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        vertical_recycler_view2.setLayoutManager(verticalLayoutmanager2);

      //  vertical_recycler_view.setAdapter(verticalAdapter);
        vertical_recycler_view1.setAdapter(verticalAdapter1);
        vertical_recycler_view2.setAdapter(verticalAdapter2);*/

        Intent i =new Intent(Puzzle4piezas.this,poppuzzle.class);
        i.putExtra("primera","si");
        i.putExtra("imagen",id_imagen);
        startActivity(i);
    }
    public Bitmap CreatePiece(int id_parte){
        Bitmap cropped=null;
        ImageView mImageView= new ImageView(this);
        //   Bitmap original = BitmapFactory.decodeResource(getResources(),R.drawable.escalera);
        Bitmap original = BitmapFactory.decodeResource(getResources(),id_imagen);
        //     original = scaleBitmap(original,470,500);
        //   Bitmap mask = BitmapFactory.decodeResource(getResources(),R.drawable.a_part_1);

        Bitmap mask = BitmapFactory.decodeResource(getResources(),id_parte);
        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCanvas.drawBitmap(original, 0, 0, null);
        mCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);
        mImageView.setImageBitmap(result);


    /*    switch (id_parte){
            case R.drawable.a_part11 :
                 cropped = Bitmap.createBitmap(result, 0, 0, result.getWidth()/3+ result.getWidth()/6, result.getHeight()/3);
                 return cropped;

            case R.drawable.a_part21 :
                cropped = Bitmap.createBitmap(result, result.getWidth()/3, 0, result.getWidth()/3, result.getHeight()/3+result.getHeight()/6);
                return cropped;

            case R.drawable.a_part31 :
                cropped = Bitmap.createBitmap(result, 2*result.getWidth()/3- result.getWidth()/6, 0, result.getWidth()/3+ result.getWidth()/6, result.getHeight()/3+result.getHeight()/6);
                                 return cropped;

            case R.drawable.a_part41 :
                cropped = Bitmap.createBitmap(result, 0, result.getHeight()/3-result.getHeight()/6, result.getWidth()/3, result.getHeight()/3+2*result.getHeight()/6);
                         return cropped;

            case R.drawable.a_part51 :
                cropped = Bitmap.createBitmap(result, result.getWidth()/3-result.getWidth()/6,  result.getHeight()/3, result.getWidth()/3+ result.getWidth()/6, result.getHeight()/3+result.getHeight()/6);
                 return cropped;

            case R.drawable.a_part61 :
                cropped = Bitmap.createBitmap(result, 2*result.getWidth()/3- result.getWidth()/6, result.getHeight()/3, result.getWidth()/3+ result.getWidth()/6, result.getHeight()/3+result.getHeight()/6);
                 return cropped;

            case R.drawable.a_part71 :
                cropped = Bitmap.createBitmap(result, 0,  2*result.getHeight()/3, result.getWidth()/3, result.getHeight()/3);
                 return cropped;

           case R.drawable.a_part81 :
                cropped = Bitmap.createBitmap(result, result.getWidth()/3-result.getWidth()/6, 2*result.getHeight()/3, result.getWidth()/3+ 2*result.getWidth()/6, result.getHeight()/3);
                 return cropped;

            case R.drawable.a_part91 :
                cropped = Bitmap.createBitmap(result, 2*result.getWidth()/3, 2*result.getHeight()/3, result.getWidth()/3, result.getHeight()/3);
                 return cropped;






        }
*/
        switch (id_parte) {
            case R.drawable.piezas_part1:
                cropped = Bitmap.createBitmap(result, 0, 0, result.getWidth() / 2 + result.getWidth() / 4, result.getHeight() / 2+ result.getHeight() / 4);

                posicion=1;
                Imagenes.put(posicion,result);

                return cropped;

            case R.drawable.piezas_part2:
                cropped = Bitmap.createBitmap(result, result.getWidth() / 2- result.getWidth() / 4, 0, result.getWidth() / 2+ result.getWidth() / 4, result.getHeight() / 2 + result.getHeight() / 4);
                posicion=2;
                Imagenes.put(posicion,result);

                return cropped;

            case R.drawable.piezas_part3:
                cropped = Bitmap.createBitmap(result, 0, result.getHeight() / 2 - result.getHeight() / 4, result.getWidth() / 2+ result.getWidth() / 4, result.getHeight() / 2 +  result.getHeight() / 4);
                posicion=3;
                Imagenes.put(posicion,result);

                return cropped;

            case R.drawable.piezas_part4:
                cropped = Bitmap.createBitmap(result, result.getWidth() / 2 - result.getWidth() / 4, result.getHeight() / 2 - result.getHeight() / 4, result.getWidth() / 2 + result.getWidth() / 4, result.getHeight() / 2 +  result.getHeight() / 4);
                posicion=4;
                Imagenes.put(posicion,result);

                return cropped;
        }
        return cropped;


    }
    public void ButtonCheckPiezas(View v) {
        clickPieza = true;
        //inicioPuzzle=true;
        //clickPuzzle = false;

        ImageView pieza=(ImageView)v;
        ActualClick =Piezas.get(pieza.getId())+10;

        System.out.println(ActualClick);
        /*if (ActualClick-10== LastClick){
//huboacierto = true;
//Codigo acierto
            pieza.setVisibility(View.INVISIBLE);
            switch (LastClick){
                case 1:
                    imagen.setImageBitmap(Imagenes.get(1));
                    imagen.setVisibility(View.VISIBLE);

                    break;

                case 2:
                    imagen2.setImageBitmap(Imagenes.get(2));
                    imagen2.setVisibility(View.VISIBLE);
                    break;

                case 3:
                    imagen3.setImageBitmap(Imagenes.get(3));
                    imagen3.setVisibility(View.VISIBLE);
                    break;

                case 4:
                    imagen4.setImageBitmap(Imagenes.get(4));
                    imagen4.setVisibility(View.VISIBLE);
                    break;


            }
            System.out.println("ACIERTO!!");
            aciertoPieza = true;
            //contPieza = 0;
            //contPuzzle = 0;
            play_acierto();

            acierto++;
            LastClick =0;
        }*/

        LastClick =0;

        //else play_fallo();

        //huboacierto = false;

        //if(clickPieza == false && contPieza != 0 && inicioPieza)
            //play_fallo();


        //aciertoPieza = false;

        if(acierto>=4) {
            if(!ganaste) {
                play_victoria();
                ganaste = true;
            }
            mensaje.setVisibility(View.VISIBLE);
        }
        LastClick =ActualClick;
        IDpieza=pieza.getId();

        //contPieza++;

        //clickPieza = true;
    }


    public void ButtonCheckModelo(View v) {

        //inicioPieza = true;
        //clickPieza = false;
        if(huboacierto==false && clickPieza==true)
            play_fallo();
        Button button=(Button)v;
        switch (button.getId()) {
            case R.id.button2:
                ActualClick = 1;
                break;
            case R.id.button7:
                ActualClick = 2;

                break;
            case R.id.button:
                ActualClick = 3;

                break;
            case R.id.button6:
                ActualClick = 4;

                break;


        }
        if (ActualClick+10== LastClick){
            System.out.println("ACIERTO!!");
            //contPuzzle = 0;
            //contPieza = 0;
            huboacierto=true;
            play_acierto();
            acierto++;
            ImageView Acierto = (ImageView)findViewById(IDpieza);
            Acierto.setVisibility(View.INVISIBLE);
            switch (ActualClick){
                case 1:
                    imagen.setImageBitmap(Imagenes.get(1));
                    imagen.setVisibility(View.VISIBLE);

                    break;

                case 2:
                    imagen2.setImageBitmap(Imagenes.get(2));
                    imagen2.setVisibility(View.VISIBLE);
                    break;

                case 3:
                    imagen3.setImageBitmap(Imagenes.get(3));
                    imagen3.setVisibility(View.VISIBLE);
                    break;

                case 4:
                    imagen4.setImageBitmap(Imagenes.get(4));
                    imagen4.setVisibility(View.VISIBLE);
                    break;


            }
            LastClick =0;
        }


        //if(clickPuzzle == false && contPuzzle != 0 && inicioPuzzle)
            //play_fallo();


        //else play_fallo();
        huboacierto = false;

        if(acierto>=4) {
            if(!ganaste){
                play_victoria();
                ganaste = true;
            }
            mensaje.setVisibility(View.VISIBLE);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    //Do something after 100ms
                }
            }, 5000 );
        }

        LastClick =ActualClick;

        //contPuzzle++;
        clickPieza = false;
        //clickPuzzle = true;
    }
    public void HelpButton(View v) {

      /*  Bitmap mask = BitmapFactory.decodeResource(getResources(),R.drawable.bomboneschocolate);
        imagen.setImageBitmap(mask);
        layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup container = (ViewGroup)layoutInflater.inflate(R.layout.custom_layout,null);
        popupWindow = new PopupWindow(container, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

        container.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view,MotionEvent motionEvent){
                 popupWindow.dismiss();
                 return true;
}
        });
        popupWindow.showAtLocation(relativeLayout, Gravity.LEFT,0,0);
*/
        Intent i =new Intent(Puzzle4piezas.this,poppuzzle.class);
        i.putExtra("primera","no");
        i.putExtra("imagen",id_imagen);
        startActivity(i);
    }
    public static Bitmap scaleBitmap(Bitmap bitmap, int wantedWidth, int wantedHeight) {
        Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) wantedWidth / bitmap.getWidth(), (float) wantedHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());

        return output;
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(Puzzle4piezas.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(Puzzle4piezas.this, home_screen.class);
        startActivity(intent1);
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
