package com.uvigo.learnfordown.learnfordown;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import static android.R.attr.name;
import static com.uvigo.learnfordown.learnfordown.DataBaseManager.CN_AGE_USER;
import static com.uvigo.learnfordown.learnfordown.DataBaseManager.CN_COMPLETED;
import static com.uvigo.learnfordown.learnfordown.DataBaseManager.CN_ID_USER;
import static com.uvigo.learnfordown.learnfordown.DataBaseManager.CN_NAME_USER;
import static com.uvigo.learnfordown.learnfordown.DataBaseManager.CN_PHOTO;
import static com.uvigo.learnfordown.learnfordown.DataBaseManager.TABLE_LEVEL_USER;
import static com.uvigo.learnfordown.learnfordown.DataBaseManager.TABLE_USER;

public class listusers_screen extends AppCompatActivity {
    private DataBaseManager db;
    private Cursor cursor;
    private ListView lista;
    private TodoCursorAdapter adapter;
    private int id_user,edad,avatar;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listusers_screen);
        lista = (ListView) findViewById(R.id.listView);
        db = new DataBaseManager(this);
        cursor = db.getUser();
        if(cursor.getCount()==0){
            System.out.println("El cursor está vacío");
            /*
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_scores_screen);
            setContentView(R.layout.empty_scores);
            */

        }
        else {
            /*
            String[] from = new String[]{db.CN_NAME_USER, db.CN_AGE_USER};
            int[] to = new int[]{android.R.id.text1, android.R.id.text2};
            */
             adapter = new TodoCursorAdapter(this, cursor);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    System.out.println("position "+position+" id: "+id);
                    Cursor cursor= (Cursor)   lista.getItemAtPosition(position);
                    System.out.println(cursor.getString(cursor.getColumnIndexOrThrow(CN_NAME_USER)));
                    cambiaUserLoging(cursor.getInt(cursor.getColumnIndexOrThrow(CN_ID_USER)));

                }
            });

        }
    }

    private void cambiaUserLoging(int id) {
        db.changeLogint(id);
        refreshLisView();
    }


    public void onDestroy(){
        db.close();
        super.onDestroy();

    }
    public void BackArrow(View v) {
        Intent intent1 = new Intent(this, home_screen.class);
        startActivity(intent1);
    }

    public void edit(View v){
        Intent intent1 = new Intent(this, edit_screen.class);
        startActivity(intent1);
        id_user=  recorreCursor((int) v.getTag(), cursor);
        db.update_name(id_user,nombre);
        db.update_age(id_user,edad);
        db.update_photo(id_user,avatar);
        refreshLisView();

    }

    public void delete(View v){
     if(adapter.getCount() >1) {
        id_user=  recorreCursor((int) v.getTag(), cursor);
         lanzaAlerta();

     }

        else{
         System.out.println("Solo hay un usuario");
     }
    }
    private int recorreCursor(int position,Cursor cursor){
        if(cursor!=null){
            if(cursor.moveToFirst()) {
                int i = 0;
                if (position != i) {
                    while (cursor.moveToNext() &&position!=i){
                        i++;
                    }
                    cursor.moveToPrevious();

                }
            }
            System.out.println(cursor.getString(cursor.getColumnIndexOrThrow(CN_NAME_USER)));
        }

        return  cursor.getInt(cursor.getColumnIndexOrThrow(CN_ID_USER));



    }
    public void goHome (View v){
        Intent intent1 = new Intent(listusers_screen.this, home_screen.class);
        startActivity(intent1);
    }
    private void refreshLisView(){
        Cursor cursor_update = db.getUser();
        adapter.swapCursor(cursor_update);
    }


    public void lanzaAlerta(){
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(CN_NAME_USER));
           String mensajeAlerta= "¿Quieres borrar a el  usuario "+ nombre+ " ?";
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                View alerta = getLayoutInflater().inflate(R.layout.dialog_recuperar_nivel, null);
              TextView mensaje= (TextView)alerta.findViewById(R.id.mensaje);

                mensaje.setText(mensajeAlerta);
                builder.setView(alerta);
            }
            else {
                builder.setMessage(mensajeAlerta)
                        .setTitle("Borrar usuario");
            }
            builder.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    db.deleteUser(id_user);
                    refreshLisView();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

    }

}
