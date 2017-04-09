package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Juani on 29/12/2016.
 */

public class TodoCursorAdapter extends CursorAdapter {
    Context context;
    View row;
    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.context= context;

    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
           row= LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
            return row;
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);
        ImageView logueado= (ImageView) view.findViewById(R.id.logueado);
        ImageView avatar= (ImageView)  view.findViewById(R.id.imagen);
        // Extract properties from cursor
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_NAME_USER));
        int edad = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_AGE_USER));
//        Date date = Timestamp.valueOf(cursor.getString(3));
        // Populate fields with extracted properties
        titulo.setText(nombre);
        descripcion.setText("Tienes : "+ String.valueOf(edad)+" años");
        avatar.setImageResource( cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_AVATAR)));
        if( cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_LOGGUED))==1){
             logueado.setImageResource(R.mipmap.icon_validate);
        }
        else{
                    logueado.setImageDrawable(null);

        }



    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     View viewR=   super.getView(position, convertView, parent);
        ImageButton borrar = (ImageButton) viewR.findViewById(R.id.borrar);
        borrar.setTag(position);
        ImageButton edit = (ImageButton) viewR.findViewById(R.id.editar);
        edit.setTag(position);
        /*
        borrar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer index = (Integer) v.getTag();
                        System.out.println(index);
                         int id = getCursor().getInt(getCursor().getColumnIndex(DataBaseManager.CN_ID_USER));
                        System.out.println(id);
                      System.out.println(getCursor().getString(getCursor().getColumnIndexOrThrow(DataBaseManager.CN_NAME_USER)));
                        //recorreCursor(index,getCursor());
                    }
                }
        );
        `*/
        return viewR;

    }



}