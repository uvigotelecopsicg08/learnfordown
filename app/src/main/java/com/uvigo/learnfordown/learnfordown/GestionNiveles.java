package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Juani on 10/02/2017.
 */

public class GestionNiveles {

    private int id_nivel;
    private int aciertos;
    private int fallos;
    private int dificultad;
    private int id_user;
    private String tipo;
    DataBaseManager db;


    public GestionNiveles(Context context) {
        db = new DataBaseManager(context);
        //Consulta a la bd el id_user actual
       id_user= db.getIdUser();

    }

    public boolean isnivelCompletado() {
        if (dificultad == 1 && aciertos == 3) {
            return true;
        } else {
            if (dificultad == 2 && aciertos == 5) {
                return true;
            } else {
                if (dificultad == 3 && aciertos == 8) {
                    return true;
                } else {
                    if (dificultad == 4 && aciertos == 10) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public void avanzaNivel() {
        aciertos = fallos = 0;
        //escritura en la BD  los resultados del nivel
        db.updateResults(id_user,id_nivel,true,aciertos,fallos);
        id_nivel++;
        //lectura parametros del nivel
        getParameterNivel();

    }

    public void acierto() {
        aciertos++;
    }

    public void fallo(){
        fallos++;
    }
    public void setNivel(String tipo,int dificultad){
        this.tipo=tipo;
       Cursor cursor= db.getNivel(tipo,dificultad);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                id_nivel = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_ID_LEVEL));
            }
        }
    }

    public ArrayList<FotoPalabra> getFotos(){
        String tiposilaba="";
        if(tipo.contains("trabadas")){
            tiposilaba="trabadas";
        }
        else{
            if(tipo.contains("inversas")){
                tiposilaba="inversas";
            }
            else{
                tiposilaba="directas";
            }
        }
       Cursor cursor=  db.buscarFotos(tiposilaba,id_user);
        ArrayList<FotoPalabra> fotos=new ArrayList<FotoPalabra>();
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    String letra = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_LETTER));
                    String silaba = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_SYLLABLE));
                    String palabra = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_WORD));
                    String frase = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_SENTENCE));
                    int foto = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_PHOTO));
                    String tema = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_TOPIC));
                    fotos.add(new FotoPalabra(letra, silaba, tiposilaba, palabra, frase, foto, tema));
                }while(cursor.moveToNext());
            }
        }
        return  fotos;
    }


    public void getParameterNivel(){
        Cursor cursor=db.getNivelByid(id_nivel);
        if(cursor!=null) {
            if (cursor.moveToLast()) {
                tipo = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_TYPE));
                dificultad= cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_DIFFICULTY));
            }
        }
    }
    public int getId_nivel() {
        return id_nivel;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }
    public void close(){
        db.close();
    }
}
