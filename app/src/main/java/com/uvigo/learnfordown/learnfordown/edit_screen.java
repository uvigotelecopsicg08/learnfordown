package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static com.uvigo.learnfordown.learnfordown.R.id.horizontal_recycler_view;

public class edit_screen extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    private ArrayList<Integer> horizontalList;
    private HorizontalAdapterDrawable horizontalAdapter;

    TextView titulo;
    ImageButton BackArrow,Home;
    TextView textoNombre, textoEdad;
    private GoogleApiClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);
        textoNombre = (TextView) findViewById(R.id.editTextn);
        textoEdad = (TextView) findViewById(R.id.editTexte);

        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        titulo = (TextView) findViewById(R.id.textView2);
        BackArrow = (ImageButton) findViewById(R.id.button3);
        Home = (ImageButton) findViewById(R.id.button9);
        titulo.setTypeface(face);


        horizontalList = new ArrayList<>();
        horizontalList.add(R.drawable.casa);
        horizontalList.add(R.drawable.casa);
        horizontalList.add(R.drawable.casa);
        horizontalList.add(R.drawable.casa);
        horizontalList.add(R.drawable.casa);
        horizontalList.add(R.drawable.casa);
        horizontalList.add(R.drawable.casa);
        horizontalList.add(R.drawable.casa);
        horizontalList.add(R.drawable.casa);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        horizontalAdapter = new HorizontalAdapterDrawable(horizontalList,3,metrics);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(edit_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_view.setAdapter(horizontalAdapter);

    }

    public void BackArrow (View v){
        Intent intent1 = new Intent(edit_screen.this, menu_screen.class);
        startActivity(intent1);
    }
    public void goHome (View v){
        Intent intent1 = new Intent(edit_screen.this, home_screen.class);
        startActivity(intent1);
    }
    void textNombreUp(View v) {
        textoNombre.setVisibility(View.VISIBLE);
    }

    void textEdadUp(View v) {
        textoEdad.setVisibility(View.VISIBLE);

    }
    public void registrar(View v) {
        SpannableStringBuilder nombreSpanable = (SpannableStringBuilder) textoNombre.getText();
        SpannableStringBuilder edadStringSpanable =  (SpannableStringBuilder) textoEdad.getText();
        String nombre= nombreSpanable.toString();
        String edadString=edadStringSpanable.toString();


        if(edadString.equals("")||nombre.equals("")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            if(edadString.equals("")&&nombre.equals("")){
                alertDialogBuilder.setTitle("Introduce nombre y edad, por favor");
            }
            else{
                if(edadString.equals("")){
                    alertDialogBuilder.setTitle("Introduce edad, por favor");
                }
                else{
                    alertDialogBuilder.setTitle("Introduce nombre, por favor");
                }
            }


            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        else{
            System.out.println(" Nombre " + nombre + "  edad " + edadString);
            try{
                int edad = Integer.parseInt(edadString);
                Intent intent = new Intent(this, home_screen.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad",edad);
                startActivity(intent);
            }
            catch (NumberFormatException e){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Introduce un valor numérico para la edad");
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }



    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("login_screen Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}
