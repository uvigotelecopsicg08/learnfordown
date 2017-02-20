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
    private String[] subnivelesTrabadas = new String[]{"pl","pr","br","bl","cl","cr","fr","gl","gr","tr","tl","fr","fr","bvr"};
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

        db.insertar_foto("p","pe","directa","perro","El perro es marrón",R.drawable.perro,"animales",4,"p");


        db.insertar_foto("o","o","directa","oso","El oso es bonito",R.drawable.oso,"animal",2,"vocales");
        db.insertar_foto("r","ra","directa","raton","El raton es bonito ",R.drawable.oso,"animales",2,"r");
        db.insertar_foto("t","tren","trabada","tren","El tren esta la estación ",R.drawable.tren,"vehiculos",1,"tr");


        db.insertar_foto("a","a","directa","abeja","poner frase aqui ",R.drawable.abeja,"animales",3,"vocales");
        db.insertar_foto("a","a","directa","agua","poner frase aqui",R.drawable.agua,"otro",2,"vocales");
        db.insertar_foto("a","a","directa","amarillo","poner frase aqui",R.drawable.amarillo,"otro",4,"vocales");
        db.insertar_foto("a","a","directa","arbol","poner frase aqui",R.drawable.arbol,"otro",2,"vocales");
        db.insertar_foto("a","al","inversa","alcachofa","poner frase aqui",R.drawable.alcachofa,"otro",4,"l");
        db.insertar_foto("a","ar","inversa","arbol","poner frase aqui",R.drawable.arbol,"otro",2,"r");
        db.insertar_foto("a","al","inversa","almeja","poner frase aqui",R.drawable.almeja,"otro",3,"l");
        db.insertar_foto("a","al","inversa","almendras","poner frase aqui",R.drawable.almendras,"otro",3,"l");
        db.insertar_foto("a","an","inversa","ancla","poner frase aqui",R.drawable.ancla,"otro",2,"n");


        db.insertar_foto("b","bra","trabada","brazo","poner frase aqui",R.drawable.brazo,"otro",2,"bvr");
        db.insertar_foto("b","bre","trabada","brecol","poner frase aqui",R.drawable.brecol,"otro",2,"bvr");
        db.insertar_foto("b","bri","trabada","bricolaje","poner frase aqui",R.drawable.bricolaje,"otro",4,"bvr");
        db.insertar_foto("b","bro","trabada","brote","poner frase aqui",R.drawable.brote,"otro",2,"bvr");
        db.insertar_foto("b","bu","directa","bufanda","La bufanda abriga",R.drawable.bufanda,"ropa",3,"bv");
        db.insertar_foto("b","bru","trabada","brujula","poner frase aqui",R.drawable.brujula,"otro",3,"bvr");
        db.insertar_foto("b","blu","trabada","blusa","poner frase aqui",R.drawable.blusa,"otro",3,"bvr");

        db.insertar_foto("c","co","directa","coche","El coche de mi madre es gris ",R.drawable.coche,"vehiculos",2,"c");
        db.insertar_foto("c","ca","directa","caballo","poner frase aqui",R.drawable.caballo,"animales",3,"c");
        db.insertar_foto("c","ca","directa","cabra","poner frase aqui",R.drawable.cabra,"animales",2,"c");
        db.insertar_foto("c","ca","directa","casa","La casa es blanca",R.drawable.casa,"casa",2,"c");
        db.insertar_foto("c","ca","directa","camello","poner frase aqui",R.drawable.camello,"animales",3,"c");
        db.insertar_foto("c","ci","directa","cine","poner frase aqui",R.drawable.cine,"otros",2,"c");
        db.insertar_foto("c","co","directa","cocina","poner frase aqui",R.drawable.cocina,"otros",3,"c");
        db.insertar_foto("c","co","directa","cocodrilo","poner frase aqui",R.drawable.cocodrilo,"animales",4,"c");
        db.insertar_foto("c","co","directa","conejo","poner frase aqui",R.drawable.conejo,"animales",3,"c");
        db.insertar_foto("c","cu","directa","cubo","poner frase aqui",R.drawable.cubo,"otros",2,"c");
        db.insertar_foto("c","cu","directa","cuchillo","poner frase aqui",R.drawable.cuchillo,"otros",3,"c");
        db.insertar_foto("c","cu","directa","cuna","poner frase aqui",R.drawable.cuna,"otros",2,"c");
        db.insertar_foto("c","cla","trabada","clarinete","poner frase aqui",R.drawable.clarinete,"otros",2,"cl");
        db.insertar_foto("c","cla","trabada","clave","poner frase aqui",R.drawable.clave,"otros",2,"cl");
        db.insertar_foto("c","cro","trabada","croquetas","poner frase aqui",R.drawable.croquetas,"otros",2,"cr");

        db.insertar_foto("d","da","directa","damas","poner frase aqui",R.drawable.damas,"otros",2,"d");
        db.insertar_foto("d","de","directa","desierto","poner frase aqui",R.drawable.desierto,"otros",3,"d");
        db.insertar_foto("d","di","directa","dibujar","poner frase aqui",R.drawable.dibujar,"otros",3,"d");
        db.insertar_foto("d","di","directa","dinero","poner frase aqui",R.drawable.dinero,"otros",3,"d");
        db.insertar_foto("d","di","directa","diploma","poner frase aqui",R.drawable.diploma,"otros",3,"d");
        db.insertar_foto("d","do","directa","domino","poner frase aqui",R.drawable.domino,"otros",3,"d");
        db.insertar_foto("d","du","directa","ducha","poner frase aqui",R.drawable.ducha,"otros",2,"d");

        db.insertar_foto("e","e","directa","escalera","poner frase aqui",R.drawable.escalera,"otro",4,"vocales");
        db.insertar_foto("e","e","directa","estrella","poner frase aqui",R.drawable.estrella,"otro",4,"vocales");
        db.insertar_foto("e","e","directa","elefante","poner frase aqui",R.drawable.elefante,"animales",4,"vocales");
        db.insertar_foto("e","en","inversa","enchufe","poner frase aqui",R.drawable.enchufe,"otros",3,"n");
        db.insertar_foto("e","es","inversa","espagueti","poner frase aqui",R.drawable.espagueti,"otros",4,"s");
        db.insertar_foto("e","es","inversa","escalera","poner frase aqui",R.drawable.escalera,"otro",4,"s");
        db.insertar_foto("e","es","inversa","estrella","poner frase aqui",R.drawable.estrella,"otro",4,"s");

        db.insertar_foto("f","fa","directa","familia","poner frase aqui",R.drawable.familia,"otros",3,"f");
        db.insertar_foto("f","fa","directa","faro","poner frase aqui",R.drawable.faro,"otros",2,"f");
        db.insertar_foto("f","fi","directa","fideos","poner frase aqui",R.drawable.fideos,"otros",3,"f");
        db.insertar_foto("f","fi","directa","figuras","poner frase aqui",R.drawable.figuras,"otros",3,"f");
        db.insertar_foto("f","fla","trabada","flamenco","poner frase aqui",R.drawable.flamenco,"animales",3,"f");
        db.insertar_foto("f","fla","trabada","flan","poner frase aqui",R.drawable.flan,"otros",1,"fl");
        db.insertar_foto("f","flo","trabada","flores","poner frase aqui",R.drawable.flores,"otros",2,"fl");
        db.insertar_foto("f","fre","trabada","fresa","poner frase aqui",R.drawable.fresa,"otros",2,"fr");
        db.insertar_foto("f","fru","trabada","frutero","poner frase aqui",R.drawable.frutero,"otros",3,"fr");
        db.insertar_foto("f","fu","directa","fular","poner frase aqui",R.drawable.fular,"ropa",2,"f");

        db.insertar_foto("g","ga","directa","gato","poner frase aqui",R.drawable.gato,"animales",1,"g");
        db.insertar_foto("g","gui","directa","guitarra","poner frase aqui",R.drawable.guitarra,"otro",3,"g");

        db.insertar_foto("i","i","directa","iglu","poner frase aqui",R.drawable.iglu,"iglu",2,"vocales");
        db.insertar_foto("i","i","directa","imán","poner frase aqui",R.drawable.iman,"otro",2,"vocales");
        db.insertar_foto("i","im","inversa","impresora","poner frase aqui",R.drawable.impresora,"otros",4,"m");
        db.insertar_foto("i","is","inversa","isla","poner frase aqui",R.drawable.isla,"playa",2,"s");
        db.insertar_foto("i","in","inversa","indio","poner frase aqui",R.drawable.indio,"otros",2,"n");

        db.insertar_foto("l","la","directa","lapiz","poner frase aqui",R.drawable.lapiz,"otro",2,"l");
        db.insertar_foto("l","la","directa","labios","poner frase aqui",R.drawable.labios,"otro",2,"l");
        db.insertar_foto("l","la","directa","lata","poner frase aqui",R.drawable.lata,"otro",2,"l");
        db.insertar_foto("l","le","directa","leon","poner frase aqui",R.drawable.leon,"animales",2,"l");
        db.insertar_foto("l","li","directa","libros","poner frase aqui",R.drawable.libros,"otro",2,"l");
        db.insertar_foto("l","li","directa","limon","poner frase aqui",R.drawable.limon,"otro",2,"l");
        db.insertar_foto("l","lo","directa","lobo","poner frase aqui",R.drawable.lobo,"animales",2,"l");
        db.insertar_foto("l","lo","directa","loro","poner frase aqui",R.drawable.loro,"animales",2,"l");
        db.insertar_foto("l","lu","directa","luna","poner frase aqui",R.drawable.luna,"otro",2,"l");
        db.insertar_foto("l","lu","directa","lupa","poner frase aqui",R.drawable.lupa,"otro",2,"l");



        db.insertar_foto("m","ma","directa","mano","Mi mano es pequeña",R.drawable.mano,"otro",2,"m");
        db.insertar_foto("m","ma","directa","mapa","poner frase aqui",R.drawable.mapa,"otro",2,"m");
        db.insertar_foto("m","ma","directa","mariposa","poner frase aqui",R.drawable.mariposa,"animales",4,"m");
        db.insertar_foto("m","ma","directa","mariquita","poner frase aqui",R.drawable.mariquita,"animales",4,"m");
        db.insertar_foto("m","me","directa","melon","poner frase aqui",R.drawable.melon,"otro",2,"m");
        db.insertar_foto("m","me","directa","mesa","poner frase aqui",R.drawable.mesa,"otro",2,"m");
        db.insertar_foto("m","mi","directa","microfono","poner frase aqui",R.drawable.microfono,"otro",4,"m");
        db.insertar_foto("m","mo","directa","mochila","poner frase aqui",R.drawable.mochila,"otro",3,"m");
        db.insertar_foto("m","mo","directa","mono","poner frase aqui",R.drawable.mono,"animal",2,"m");
        db.insertar_foto("m","mu","directa","muleta","poner frase aqui",R.drawable.muleta,"otro",3,"m");
        db.insertar_foto("m","mu","directa","muñeca","poner frase aqui",R.drawable.muneca,"otro",3,"m");

        db.insertar_foto("n","na","directa","naranja","Comí una naranja de postre",R.drawable.naranja,"otro",3,"n");
        db.insertar_foto("n","na","directa","nariz","poner frase aqui",R.drawable.nariz,"otro",2,"n");
        db.insertar_foto("n","ne","directa","nevera","poner frase aqui",R.drawable.nevera,"otro",3,"n");
        db.insertar_foto("n","ni","directa","nido","poner frase aqui",R.drawable.nido,"otro",2,"n");
        db.insertar_foto("n","no","directa","noria","poner frase aqui",R.drawable.noria,"otro",2,"n");
        db.insertar_foto("n","nu","directa","nubes","poner frase aqui",R.drawable.nubes,"otro",2,"n");
        db.insertar_foto("n","nu","directa","nuez","poner frase aqui",R.drawable.nuez,"otro",2,"n");

        db.insertar_foto("o","o","directa","ojo","poner frase aqui",R.drawable.ojo,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","olla","poner frase aqui",R.drawable.olla,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","oreja","poner frase aqui",R.drawable.oreja,"otro",3,"vocales");
        db.insertar_foto("o","o","directa","oruga","poner frase aqui",R.drawable.oruga,"animales",3,"vocales");
        db.insertar_foto("o","o","directa","oveja","poner frase aqui",R.drawable.oveja,"animales",3,"vocales");
        db.insertar_foto("o","on","inversa","once","poner frase aqui",R.drawable.once,"otro",2,"n");

        db.insertar_foto("p","pla","trabada","planeta","poner frase aqui",R.drawable.planeta,"otro",3,"pl");
        db.insertar_foto("p","pla","trabada","platano","poner frase aqui",R.drawable.platano,"otro",3,"pl");
        db.insertar_foto("p","pla","trabada","playa","Me gusta estar en la playa",R.drawable.playa,"playa",2,"pl");
        db.insertar_foto("p","pa","directa","patata","poner frase aqui",R.drawable.patata,"otro",3,"p");
        db.insertar_foto("p","pi","directa","pimiento","poner frase aqui",R.drawable.pimiento,"otro",3,"p");
        db.insertar_foto("p","po","directa","policia","poner frase aqui",R.drawable.policia,"otro",3,"p");
        db.insertar_foto("p","pu","directa","puma","poner frase aqui",R.drawable.puma,"animales",2,"p");
        db.insertar_foto("p","pla","trabada","planeta","poner frase aqui",R.drawable.planeta,"otro",3,"pl");
        db.insertar_foto("p","pla","trabada","platano","poner frase aqui",R.drawable.platano,"otro",3,"pl");
        db.insertar_foto("p","pla","trabada","plato","poner frase aqui",R.drawable.plato,"otro",2,"pl");
        db.insertar_foto("p","pla","trabada","playa","poner frase aqui",R.drawable.playa,"otro",2,"pl");
        db.insertar_foto("p","plu","trabada","pluma","poner frase aqui",R.drawable.pluma,"otro",2,"pl");



        db.insertar_foto("t","ta","directa","tapa","poner frase aqui",R.drawable.tapa,"otro",2,"t");
        db.insertar_foto("t","ta","directa","taza","poner frase aqui",R.drawable.taza,"otro",2,"t");
        db.insertar_foto("t","te","directa","tenis","poner frase aqui",R.drawable.tenis,"otro",2,"t");
        db.insertar_foto("t","te","directa","tesoro","poner frase aqui",R.drawable.tesoro,"otro",3,"t");
        db.insertar_foto("t","ti","directa","tiburon","poner frase aqui",R.drawable.tiburon,"animales",3,"t");
        db.insertar_foto("t","ti","directa","timon","poner frase aqui",R.drawable.timon,"otro",2,"t");
        db.insertar_foto("t","to","directa","tomate","poner frase aqui",R.drawable.tomate,"otro",3,"t");
        db.insertar_foto("t","to","directa","toro","poner frase aqui",R.drawable.toro,"animales",2,"t");
        db.insertar_foto("t","tri","trabada","triangulo","poner frase aqui",R.drawable.triangulo,"otro",4,"tr");
        db.insertar_foto("t","tru","trabada","trufas","poner frase aqui",R.drawable.trufas,"otro",2,"tr");

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
