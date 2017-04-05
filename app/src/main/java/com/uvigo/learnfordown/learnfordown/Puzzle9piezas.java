package com.uvigo.learnfordown.learnfordown;

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
import android.os.Handler;
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

public class Puzzle9piezas extends AppCompatActivity {
    ArrayList<Integer> verticalList;
    ArrayList<Bitmap> PrimeraColumna;
    HashMap<Integer,Bitmap> Imagenes = new HashMap<>();
    HashMap<Integer,Integer> Piezas = new HashMap<>();
    TextView mensaje;
    private LayoutInflater layoutInflater;
    private PopupWindow popupWindow;
    int acierto;
    int posicion;

    int id_imagen;
    ArrayList<Bitmap> SegundaColumna;
    ArrayList<Bitmap> TerceraColumna;
    int LastClick;
    int IDpieza;
    int ActualClick;
    TextView texto;
    private RecyclerView vertical_recycler_view;
    private RecyclerView vertical_recycler_view1;
    private RecyclerView vertical_recycler_view2;

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


    ImageView pieza1;
    ImageView pieza2;
    ImageView pieza3;
    ImageView pieza4;
    ImageView pieza5;
    ImageView pieza6;
    ImageView pieza7;
    ImageView pieza8;
    ImageView pieza9;

    RelativeLayout relativeLayout;

    Button button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle9piezas);
        int rand =(int) (Math.random() * 9);
        id_imagen = this.getResources().getIdentifier("puzzle"+rand, "drawable", this.getPackageName());
        //Piezas
        pieza1 = (ImageView)findViewById(R.id.imageView22);
        pieza2 = (ImageView)findViewById(R.id.imageView21);
        pieza3 = (ImageView)findViewById(R.id.imageView20);
        pieza4 = (ImageView)findViewById(R.id.imageView7);
        pieza5 = (ImageView)findViewById(R.id.imageView15);
        pieza6 = (ImageView)findViewById(R.id.imageView16);
        pieza7 = (ImageView)findViewById(R.id.imageView8);
        pieza8 = (ImageView)findViewById(R.id.imageView4);
        pieza9 = (ImageView)findViewById(R.id.imageView14);
//Imagenes
        imagen = (ImageView)findViewById(R.id.imageView41);
        imagen2 = (ImageView)findViewById(R.id.imageView42);
        imagen3 = (ImageView)findViewById(R.id.imageView43);
        imagen4 = (ImageView)findViewById(R.id.imageView44);
        imagen5 = (ImageView)findViewById(R.id.imageView45);
        imagen6 = (ImageView)findViewById(R.id.imageView46);
        imagen7 = (ImageView)findViewById(R.id.imageView25);
        imagen8 = (ImageView)findViewById(R.id.imageView26);
        imagen9 = (ImageView)findViewById(R.id.imageView27);
        imagen10 = (ImageView)findViewById(R.id.imageView18);

        relativeLayout=(RelativeLayout) findViewById(R.id.puzzle9piezas);

        texto = (TextView) findViewById(R.id.textView2);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        texto.setTypeface(face);



        mensaje =(TextView)findViewById(R.id.textView);
        mensaje.setTypeface(face);

        imagen.setVisibility(View.GONE);
        imagen2.setVisibility(View.GONE);
        imagen3.setVisibility(View.GONE);
        imagen4.setVisibility(View.GONE);
        imagen5.setVisibility(View.GONE);
        imagen6.setVisibility(View.GONE);
        imagen7.setVisibility(View.GONE);
        imagen8.setVisibility(View.GONE);
        imagen9.setVisibility(View.GONE);
        imagen10.setVisibility(View.VISIBLE);


        //BOTONES INVISBLES
        button1 = (Button)findViewById(R.id.button9);
        button2 = (Button)findViewById(R.id.button10);
        button3 = (Button)findViewById(R.id.button11);
        button4 = (Button)findViewById(R.id.button12);
        button5 = (Button)findViewById(R.id.button13);
        button6 = (Button)findViewById(R.id.button14);
        button7 = (Button)findViewById(R.id.button16);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button15);

        button1.setBackgroundColor(Color.TRANSPARENT);
        button2.setBackgroundColor(Color.TRANSPARENT);
        button3.setBackgroundColor(Color.TRANSPARENT);
        button4.setBackgroundColor(Color.TRANSPARENT);
        button5.setBackgroundColor(Color.TRANSPARENT);
        button6.setBackgroundColor(Color.TRANSPARENT);
        button7.setBackgroundColor(Color.TRANSPARENT);
        button8.setBackgroundColor(Color.TRANSPARENT);
        button9.setBackgroundColor(Color.TRANSPARENT);

        mensaje.setVisibility(View.GONE);

        // imagen5.setVisibility(View.GONE);
        // imagen6.setVisibility(View.GONE);
        // imagen7.setVisibility(View.GONE);
        // imagen8.setVisibility(View.GONE);


      /*  imagen.setImageBitmap(CreatePiece(R.drawable.a_part13));
        imagen2.setImageBitmap(CreatePiece(R.drawable.a_part21));
        imagen3.setImageResource(R.drawable.a_layout);*/
        verticalList=new ArrayList<>();

        verticalList.add(R.drawable.modelo9piezas_1);
        verticalList.add(R.drawable.modelo9piezas_2);
        verticalList.add(R.drawable.modelo9piezas_3);
        verticalList.add(R.drawable.modelo9piezas_4);
        verticalList.add(R.drawable.modelo9piezas_5);
        verticalList.add(R.drawable.modelo9piezas_6);
        verticalList.add(R.drawable.modelo9piezas_7);
        verticalList.add(R.drawable.modelo9piezas_8);
        verticalList.add(R.drawable.modelo9piezas_9);

        Collections.shuffle(verticalList);
        for (int i =0; i<9; i++){
            switch (i){
                case 0:
                    pieza1.setImageBitmap(CreatePiece(verticalList.get(0)));
                    pieza1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(pieza1.getId(),posicion);
                    break;
                case 1:
                    pieza2.setImageBitmap(CreatePiece(verticalList.get(0)));
                    pieza2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                    verticalList.remove(0);
                    Piezas.put(pieza2.getId(),posicion);

                    break;
                case 2:
                    pieza3.setImageBitmap(CreatePiece(verticalList.get(0)));
                    pieza3.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(pieza3.getId(),posicion);

                    break;
                case 3:
                    pieza4.setImageBitmap(CreatePiece(verticalList.get(0)));
                    pieza4.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(pieza4.getId(),posicion);

                    break;
                case 4:
                    pieza5.setImageBitmap(CreatePiece(verticalList.get(0)));
                    pieza5.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(pieza5.getId(),posicion);
                    break;
                case 5:
                    pieza6.setImageBitmap(CreatePiece(verticalList.get(0)));
                    pieza6.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                    verticalList.remove(0);
                    Piezas.put(pieza6.getId(),posicion);

                    break;
                case 6:
                    pieza7.setImageBitmap(CreatePiece(verticalList.get(0)));
                    pieza7.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(pieza7.getId(),posicion);

                    break;
                case 7:
                    pieza8.setImageBitmap(CreatePiece(verticalList.get(0)));
                    pieza8.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(pieza8.getId(),posicion);

                    break;
                case 8:
                    pieza9.setImageBitmap(CreatePiece(verticalList.get(0)));
                    pieza9.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    verticalList.remove(0);
                    Piezas.put(pieza9.getId(),posicion);
                    break;

            }

        }

        Intent i =new Intent(Puzzle9piezas.this,poppuzzle.class);
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

        switch (id_parte) {
            case R.drawable.modelo9piezas_1:
                cropped = Bitmap.createBitmap(result, 0, 0, result.getWidth() / 3 + result.getWidth() / 6, result.getHeight() / 3+ result.getHeight() / 6);

                posicion=1;
                Imagenes.put(posicion,result);

                return cropped;

            case R.drawable.modelo9piezas_2:
                cropped = Bitmap.createBitmap(result, result.getWidth() / 3- result.getWidth() / 6, 0, result.getWidth() / 3+ result.getWidth() / 6, result.getHeight() / 3 + result.getHeight() / 6);
                posicion=2;
                Imagenes.put(posicion,result);

                return cropped;

            case R.drawable.modelo9piezas_3:
                cropped = Bitmap.createBitmap(result, 2*result.getWidth() / 3- result.getWidth() / 6, 0, result.getWidth() / 3+ result.getWidth() / 6, result.getHeight() / 3 + result.getHeight() / 6);
                posicion=3;
                Imagenes.put(posicion,result);

                return cropped;

            case R.drawable.modelo9piezas_4:
                cropped = Bitmap.createBitmap(result, 0, result.getHeight() / 3- result.getHeight() / 6, result.getWidth() / 3 + result.getWidth() / 6, result.getHeight() / 3+ 2*result.getHeight() / 6);
                posicion=4;
                Imagenes.put(posicion,result);

                return cropped;
            case R.drawable.modelo9piezas_5:
                cropped = Bitmap.createBitmap(result, result.getWidth() / 3- result.getWidth() / 6, result.getHeight() / 3- result.getHeight() / 6, result.getWidth() / 3 + result.getWidth() / 6, result.getHeight() / 3+ 2*result.getHeight() / 6);
                posicion=5;
                Imagenes.put(posicion,result);

                return cropped;
            case R.drawable.modelo9piezas_6:
                cropped = Bitmap.createBitmap(result, 2*result.getWidth() / 3- result.getWidth() / 6, result.getHeight() / 3- result.getHeight() / 6, result.getWidth() / 3 + result.getWidth() / 6, result.getHeight() / 3+ 2*result.getHeight() / 6);
                posicion=6;
                Imagenes.put(posicion,result);

                return cropped;
            case R.drawable.modelo9piezas_7:
                cropped = Bitmap.createBitmap(result, 0, 2*result.getHeight() / 3, result.getWidth() / 3 + result.getWidth() / 6, result.getHeight() / 3);
                posicion=7;
                Imagenes.put(posicion,result);

                return cropped;
            case R.drawable.modelo9piezas_8:
                cropped = Bitmap.createBitmap(result, result.getWidth() / 3 - result.getWidth() / 6, 2*result.getHeight() / 3, result.getWidth() / 3 + 2*result.getWidth() / 6, result.getHeight() / 3);
                posicion=8;
                Imagenes.put(posicion,result);

                return cropped;
            case R.drawable.modelo9piezas_9:
                cropped = Bitmap.createBitmap(result, 2*result.getWidth() / 3 - result.getWidth() / 6, 2*result.getHeight() / 3, result.getWidth() / 3 + result.getWidth() / 6, result.getHeight() / 3);
                posicion=9;
                Imagenes.put(posicion,result);

                return cropped;
        }
        return cropped;


    }
    public void ButtonCheckPiezas(View v) {
        ImageView pieza=(ImageView)v;
        ActualClick =Piezas.get(pieza.getId())+10;

        System.out.println(ActualClick);
        if (ActualClick-10== LastClick){

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

                case 5:
                    imagen5.setImageBitmap(Imagenes.get(5));
                    imagen5.setVisibility(View.VISIBLE);
                    break;

                case 6:
                    imagen6.setImageBitmap(Imagenes.get(6));
                    imagen6.setVisibility(View.VISIBLE);
                    break;

                case 7:
                    imagen7.setImageBitmap(Imagenes.get(7));
                    imagen7.setVisibility(View.VISIBLE);
                    break;

                case 8:
                    imagen8.setImageBitmap(Imagenes.get(8));
                    imagen8.setVisibility(View.VISIBLE);
                    break;

                case 9:
                    imagen9.setImageBitmap(Imagenes.get(9));
                    imagen9.setVisibility(View.VISIBLE);
                    break;


            }
            System.out.println("ACIERTO!!");


            acierto++;
            LastClick =0;
        }
        if( acierto>=9) {
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
        IDpieza=pieza.getId();
    }

    public void ButtonCheckModelo(View v) {
        Button button=(Button)v;
        switch (button.getId()){
            case R.id.button9:
                ActualClick =1;
                break;
            case R.id.button10:
                ActualClick =2;

                break;
            case R.id.button11:
                ActualClick =3;

                break;
            case R.id.button12:
                ActualClick =4;

                break;
            case R.id.button13:
                ActualClick =5;

                break;
            case R.id.button14:
                ActualClick =6;

                break;
            case R.id.button16:
                ActualClick =7;

                break;
            case R.id.button8:
                ActualClick =8;

                break;
            case R.id.button15:
                ActualClick =9;

                break;



        }

        if (ActualClick+10== LastClick){
            System.out.println("ACIERTO!!");
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

                case 5:
                    imagen5.setImageBitmap(Imagenes.get(5));
                    imagen5.setVisibility(View.VISIBLE);
                    break;

                case 6:
                    imagen6.setImageBitmap(Imagenes.get(6));
                    imagen6.setVisibility(View.VISIBLE);
                    break;

                case 7:
                    imagen7.setImageBitmap(Imagenes.get(7));
                    imagen7.setVisibility(View.VISIBLE);
                    break;

                case 8:
                    imagen8.setImageBitmap(Imagenes.get(8));
                    imagen8.setVisibility(View.VISIBLE);
                    break;

                case 9:
                    imagen9.setImageBitmap(Imagenes.get(9));
                    imagen9.setVisibility(View.VISIBLE);
                    break;


            }
            LastClick =0;
        }
            if( acierto>=9) {
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
        Intent i =new Intent(Puzzle9piezas.this,poppuzzle.class);
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
        Intent intent1 = new Intent(Puzzle9piezas.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(Puzzle9piezas.this, home_screen.class);
        startActivity(intent1);
    }
}
