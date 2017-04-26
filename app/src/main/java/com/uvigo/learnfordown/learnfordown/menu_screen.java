package com.uvigo.learnfordown.learnfordown;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class menu_screen extends AppCompatActivity {


    TextView titulo;
    ImageButton BackArrow;
    private Spinner nameValueSpinner;
    private Spinner nameValueSpinner1;
    private Spinner nameValueSpinner2;
    private Spinner nameValueSpinner3;
    private static String NivelAnterior;
    private boolean  mBound= false;
    private  BluetoothService mService;
    private AppCompatActivity app=this;

    public menu_screen() {}

    public menu_screen(String nivelAnterior) {
        NivelAnterior = nivelAnterior;
    }

    public String getNivelAnterior() {
        return NivelAnterior;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        BackArrow = (ImageButton) findViewById(R.id.button3);
        titulo.setTypeface(face);
        nameValueSpinner = (Spinner) findViewById(R.id.spinner);
        nameValueSpinner1 = (Spinner) findViewById(R.id.spinner1);
        nameValueSpinner2 = (Spinner) findViewById(R.id.spinner2);
        nameValueSpinner3 = (Spinner) findViewById(R.id.spinner3);



        setupSpinner();
        Intent intent=new Intent(this,BluetoothService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

    }

    private void setupSpinner() {
        // Setup data
        List<SpinnerModel> nameLettersValues = createLettersValues();
        List<SpinnerModel> nameSilabasValues = createSilabasValues();
        List<SpinnerModel> namePalabrasValues = createPalabrasValues();
        List<SpinnerModel> nameFrasesValues = createFrasesValues();
        SpinnerAdapter nameValueSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_rows, nameLettersValues,"LETRA");
        SpinnerAdapter nameValueSpinnerAdapter1 = new SpinnerAdapter(this, R.layout.spinner_rows, nameSilabasValues,"SILABA");
        SpinnerAdapter nameValueSpinnerAdapter2 = new SpinnerAdapter(this, R.layout.spinner_rows, namePalabrasValues,"PALABRA");
        SpinnerAdapter nameValueSpinnerAdapter3 = new SpinnerAdapter(this, R.layout.spinner_rows, nameFrasesValues,"FRASE");
        nameValueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerModel selection = (SpinnerModel) nameValueSpinner.getItemAtPosition(position);
                switch (position){
                    case 0:
                        break;

                    case 1:
                        Intent intent1 = new Intent(menu_screen.this, lettergame1lvl_screen.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(menu_screen.this, lettergame2lvl_screen.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(menu_screen.this, lettergame3lvl_screen.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(menu_screen.this, lettergame4lvl_screen.class);
                        startActivity(intent4);
                        break;




                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                nameValueSpinner.setVisibility(View.GONE);

            }
        });

        nameValueSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerModel selection = (SpinnerModel) nameValueSpinner.getItemAtPosition(position);
                switch (position){
                    case 0:
                        break;

                    case 1:
                        NivelAnterior = "SilabasDirectas";
                        Intent intent1 = new Intent(menu_screen.this, sidirectas_screen.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        NivelAnterior = "SilabasInversas";
                        Intent intent2 = new Intent(menu_screen.this, siinversas_screen.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        NivelAnterior = "SilabasTrabadas";
                        Intent intent3 = new Intent(menu_screen.this, sitrabadas_screen.class);
                        startActivity(intent3);
                        break;





                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                nameValueSpinner1.setVisibility(View.GONE);

            }
        });
        nameValueSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerModel selection = (SpinnerModel) nameValueSpinner.getItemAtPosition(position);
                switch (position){
                    case 0:
                        break;

                    case 1:
                        NivelAnterior = "PalabrasDirectas";
                        Intent intent1 = new Intent(menu_screen.this, palabrasdi_screen.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        NivelAnterior = "PalabrasInversas";
                        Intent intent2 = new Intent(menu_screen.this, palabrasin_screen.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        NivelAnterior = "PalabrasTrabadas";
                        Intent intent3 = new Intent(menu_screen.this, palabrastra_screen.class);
                        startActivity(intent3);
                        break;





                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                nameValueSpinner2.setVisibility(View.GONE);

            }
        });
        nameValueSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerModel selection = (SpinnerModel) nameValueSpinner.getItemAtPosition(position);
                switch (position){
                    case 0:
                        break;

                    case 1:
                        NivelAnterior = "FrasesDirectas";
                        Intent intent1 = new Intent(menu_screen.this, frasedi_screen.class);
                        System.out.println("este es el menu 1");
                        startActivity(intent1);
                        break;

                    case 2:
                        NivelAnterior = "FrasesInversas";
                        Intent intent2 = new Intent(menu_screen.this, frasein_screen.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        NivelAnterior = "FrasesTrabadas";
                        Intent intent3 = new Intent(menu_screen.this, frasetra_screen.class);
                        startActivity(intent3);
                        break;





                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                nameValueSpinner3.setVisibility(View.GONE);

            }
        });

        nameValueSpinner.setAdapter(nameValueSpinnerAdapter);
        nameValueSpinner1.setAdapter(nameValueSpinnerAdapter1);
        nameValueSpinner2.setAdapter(nameValueSpinnerAdapter2);
        nameValueSpinner3.setAdapter(nameValueSpinnerAdapter3);


        // Setup behaviour
    }

    private List<SpinnerModel> createLettersValues() {
        List<SpinnerModel> nameValues = new ArrayList<>();
        nameValues.add(new SpinnerModel("", ""));
        nameValues.add(new SpinnerModel("Nivel 1", ""));
        nameValues.add(new SpinnerModel("Nivel 2", ""));
        nameValues.add(new SpinnerModel("Nivel 3", ""));
        nameValues.add(new SpinnerModel("Nivel 4", ""));

        return nameValues;
    }
    private List<SpinnerModel> createSilabasValues() {
        List<SpinnerModel> nameValues = new ArrayList<>();
        nameValues.add(new SpinnerModel("", ""));
        nameValues.add(new SpinnerModel("Sílabas directas", ""));
        nameValues.add(new SpinnerModel("Sílabas inversas", ""));
        nameValues.add(new SpinnerModel("Sílabas trabadas", ""));
        return nameValues;
    }
    private List<SpinnerModel> createPalabrasValues() {
        List<SpinnerModel> nameValues = new ArrayList<>();
        nameValues.add(new SpinnerModel("", ""));
        nameValues.add(new SpinnerModel("Palabras con sílabas directas", ""));
        nameValues.add(new SpinnerModel("Palabras con sílabas inversas", ""));
        nameValues.add(new SpinnerModel("Palabras con sílabas trabadas", ""));
        return nameValues;
    }
    private List<SpinnerModel> createFrasesValues() {
        List<SpinnerModel> nameValues = new ArrayList<>();
        nameValues.add(new SpinnerModel("", ""));
        nameValues.add(new SpinnerModel("Frases con sílabas directas", ""));
        nameValues.add(new SpinnerModel("Frases con sílabas inversas", ""));
        nameValues.add(new SpinnerModel("Frases con sílabas trabadas", ""));

        return nameValues;
    }
  public   void SpinnerLetterUp(View v){
        nameValueSpinner.setVisibility(View.INVISIBLE);
        nameValueSpinner.performClick();
    }
 public    void SpinnerSilabaUp(View v){
       nameValueSpinner1.setVisibility(View.INVISIBLE);
        nameValueSpinner1.performClick();
    }
 public    void SpinnerPalabraUp(View v){
        nameValueSpinner2.setVisibility(View.INVISIBLE);
        nameValueSpinner2.performClick();
    }
 public    void SpinnerFraseUp(View v){
       nameValueSpinner3.setVisibility(View.INVISIBLE);
        nameValueSpinner3.performClick();
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(menu_screen.this, home_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(menu_screen.this, home_screen.class);
        startActivity(intent1);
    }
    private ServiceConnection mConnection = new ServiceConnection() {



        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            Log.d("BINDER", "service="+service + " className" + className);
            BluetoothService.LocalBinder binder = (BluetoothService.LocalBinder) service;
            mService= binder.getService();
            mBound = true;
            mService.setApp(app);
            mService.setConnection();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
