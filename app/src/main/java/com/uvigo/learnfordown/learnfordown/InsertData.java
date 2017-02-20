package com.uvigo.learnfordown.learnfordown;

import android.content.Context;

/**
 * Created by Juani on 13/02/2017.
 */

public class InsertData {
    DataBaseManager db;
    private Context context;
    private String[] subnivelesDirectas = new String[]{"p","m","t","bv","n","kcq","l","s","d","zc","f","j","lly","ñ","g","ch","r"};
    private String[] subnivelesInversas = new String[]{"m","n","l","s"};
    private String[] subnivelesTrabadas = new String[]{"pl","pr","br","bl","cl","fr","gl","gr","tr","tl"};
    public InsertData(Context context) {
        this.context =context;
        db = new DataBaseManager(context);


    }
    public void insertar() {
        insertarNiveles();
        insertarFotos();
        db.close();
    }

    private void insertarFotos() {

        //Ordenar estas alfabeticamente también
        db.insertar_foto("c","ca","directa","casa","La casa es blanca",R.drawable.casa,"casa",2,"c");
        db.insertar_foto("p","pe","directa","perro","El perro es marrón",R.drawable.perro,"animales",4,"p");
        db.insertar_foto("b","bu","directa","bufanda","La bufanda abriga",R.drawable.bufanda,"ropa",3,"bv");
        db.insertar_foto("p","pla","trabada","playa","Me gusta estar en la playa",R.drawable.playa,"playa",2,"pl");
        db.insertar_foto("o","o","directa","oso","El oso es bonito",R.drawable.oso,"animal",2,"vocales");
        db.insertar_foto("r","ra","directa","raton","El raton es bonito ",R.drawable.oso,"animales",2,"r");
        db.insertar_foto("t","tren","trabada","tren","El tren esta la estación ",R.drawable.tren,"vehiculos",1,"tr");


        db.insertar_foto("a","a","directa","abeja","poner frase aqui ",R.drawable.abeja,"animales",3,"vocales");
        db.insertar_foto("a","a","directa","agua","poner frase aqui",R.drawable.agua,"otro",2,"vocales");
        db.insertar_foto("a","a","directa","amarillo","poner frase aqui",R.drawable.amarillo,"otro",4,"vocales");
        db.insertar_foto("a","a","directa","arbol","poner frase aqui",R.drawable.arbol,"otro",2,"vocales");

        db.insertar_foto("e","e","directa","elefante","poner frase aqui",R.drawable.elefante,"animales",4,"vocales");
        db.insertar_foto("e","e","directa","escalera","poner frase aqui",R.drawable.escalera,"otro",4,"vocales");
        db.insertar_foto("e","e","directa","estrella","poner frase aqui",R.drawable.estrella,"otro",4,"vocales");

        db.insertar_foto("g","ga","directa","gato","poner frase aqui",R.drawable.gato,"animales",1,"g");

        db.insertar_foto("i","i","directa","iglu","poner frase aqui",R.drawable.iglu,"iglu",2,"vocales");
        db.insertar_foto("i","i","directa","imán","poner frase aqui",R.drawable.iman,"otro",2,"vocales");
        db.insertar_foto("i","im","inversa","impresora","poner frase aqui",R.drawable.impresora,"otros",4,"m");
        db.insertar_foto("i","is","inversa","isla","poner frase aqui",R.drawable.isla,"playa",2,"s");

        db.insertar_foto("l","la","directa","lapiz","poner frase aqui",R.drawable.lapiz,"otro",1,"l");

        db.insertar_foto("o","o","directa","ojo","poner frase aqui",R.drawable.ojo,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","olla","poner frase aqui",R.drawable.olla,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","oreja","poner frase aqui",R.drawable.oreja,"otro",3,"vocales");
        db.insertar_foto("o","o","directa","oruga","poner frase aqui",R.drawable.oruga,"animales",3,"vocales");
        db.insertar_foto("o","o","directa","oveja","poner frase aqui",R.drawable.oveja,"animales",3,"vocales");

        db.insertar_foto("p","pla","trabada","planeta","poner frase aqui",R.drawable.planeta,"otro",3,"pl");
        db.insertar_foto("p","pla","trabada","platano","poner frase aqui",R.drawable.platano,"otro",3,"pl");
        db.insertar_foto("p","pa","directa","patata","poner frase aqui",R.drawable.patata,"otro",3,"p");
        db.insertar_foto("p","pi","directa","pimiento","poner frase aqui",R.drawable.pimiento,"otro",3,"p");
        db.insertar_foto("p","po","directa","policia","poner frase aqui",R.drawable.policia,"otro",3,"p");
        db.insertar_foto("p","pu","directa","puma","poner frase aqui",R.drawable.puma,"animales",2,"p");

        db.insertar_foto("u","u","directa","uniforme","poner frase aqui",R.drawable.uniforme,"otro",4,"vocales");
        db.insertar_foto("u","u","directa","uva","poner frase aqui",R.drawable.uva,"otro",2,"vocales");
        db.insertar_foto("u","u","directa","una","poner frase aqui",R.drawable.una,"otro",2,"vocales");





    }

    private void insertarNiveles() {
        for(int j=1;j<5;j++){
            db.insertar_nivel ("leerletras",j,"vocales");
            for(int i = 0; i< subnivelesDirectas.length; i++){
                db.insertar_nivel ("leerletras",j, subnivelesDirectas[i]);
            }
        }
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesDirectas.length; i++){
                db.insertar_nivel ("silabasdirectas",j, subnivelesDirectas[i]);
            }
        }
        db.insertar_nivel("palabrassilabasdirectas",1,"nivelunico");

        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesInversas.length; i++){
                db.insertar_nivel ("silabasinversas",j, subnivelesInversas[i]);
            }
        }
        db.insertar_nivel("palabrassilabasdirectas",1,"");

        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesTrabadas.length; i++){
                db.insertar_nivel ("silabastrabadas",j, subnivelesTrabadas[i]);
            }
        }
        db.insertar_nivel("frasessilabasdirectas",1,"nivelunico");
        db.insertar_nivel("frasessilabasinversas",1,"nivelunico");
        db.insertar_nivel("frasessilabastrabadas",1,"nivelunico");
    }
}
