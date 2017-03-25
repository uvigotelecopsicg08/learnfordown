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
    private String subnivel;
    private int numeroFotos;
    DataBaseManager db;


    public GestionNiveles(Context context) {
        db = new DataBaseManager(context);
        //Consulta a la bd el id_user actual
       id_user= db.getIdUser();

    }

    public boolean isnivelCompletado() {
        if(tipo.contains("escribir")){
            return  iscompletadoEscritura();
        }
        else{
        return iscompletadoLectura();
        }
        
    }

    private boolean iscompletadoEscritura() {
       switch (tipo){
           case "escribirconsombreado":
               if(aciertos>=10){
                   return true;
               }
               else return false;
           case "escribirsinsombreado":
               if(aciertos>=15){
                   return true;
               }
               else return false;
           case "escribirtecladopalabra":
               if(aciertos>=15){
                   return true;
               }
               else return false;
           default:
               return false;
       }
    }

    private boolean iscompletadoLectura() {
        if(tipo.contains("frases")){
            if (( aciertos >= 3) || (numeroFotos <= (aciertos+2))){
                return true;
            }
            else
                return false;
        }
        else {
            if ((dificultad == 1 && aciertos >= 3) || (numeroFotos <= aciertos)) {
                return true;
            } else {
                if ((dificultad == 2 && aciertos >= 5)) {
                    return true;
                } else {
                    if ((dificultad == 3 && aciertos >= 8)) {
                        return true;
                    } else {
                        if ((dificultad == 4 && aciertos >= 10)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
    }

    public void avanzaNivel() {

        //escritura en la BD  los resultados del nivel
        db.updateResults(id_user,id_nivel,true,aciertos,fallos);
        aciertos = fallos = 0;
        id_nivel++;
        //lectura parametros del nivel
        getParameterNivel();
        db.mostrarTablas();
        db.actulizaTimeStamp(true,id_nivel,id_user);

    }

    public void acierto() {
        aciertos++;
    }

    public void fallo(){
        fallos++;
    }
    public int setNivel(String tipo,int dificultad){
        this.tipo=tipo;
        this.dificultad=dificultad;
        aciertos=fallos=0;
       Cursor cursor= db.getNivel(tipo,dificultad,id_user);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                id_nivel = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_ID_LEVEL));
                subnivel = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_STEP));
            }

            //Codigo pendiente de posibles modificaciones en la implementacion
            else {
                resetNivel();

            }
        }
        db.actulizaTimeStamp(true,id_nivel,id_user);
        return  getEstrellas();

    }
    public void resetNivel(){
        aciertos=fallos=0;
        Cursor cursor= db.resetNivel(tipo, dificultad, id_user);
        if (cursor.moveToFirst()) {
            id_nivel = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_ID_LEVEL));
            subnivel = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_STEP));
        }
        db.actulizaTimeStamp(true,id_nivel,id_user);

    }
    public ArrayList<FotoPalabra> getFotos(){
        String tiposilaba="";
        int numeroSilabas=0;
        Cursor cursor=null;
        boolean vocales=false;
        if(tipo.contains("trabada")){
            tiposilaba="trabada";
        }
        else{
            if(tipo.contains("inversa")){
                tiposilaba="inversa";
            }
            else{
                tiposilaba="directa";
            }
        }
        if(tipo.contains("palabra")||tipo.contains("frase")){
            numeroSilabas=dificultad;
          //  numeroSilabas=-1;
        }
        else{
            numeroSilabas=-1;
        }
        if(subnivel!=null) {
            if (subnivel.equals("a") || subnivel.equals("e") || subnivel.equals("i") || subnivel.equals("o") || subnivel.equals("u")) {
                vocales = true;
            }
        }
        if(tipo.contains("escribir")||vocales) {
            cursor = db.buscarFotosbyLetra(id_user, subnivel);
        }
        else{
            cursor = db.buscarFotos(tiposilaba, id_user, subnivel, numeroSilabas);
        }
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
                    if((!tipo.equals("silabastrabadas")&&!tipo.equals("silabasinversas")&&!tipo.equals("silabasdirectas"))||palabra.startsWith(silaba)||dificultad<3) {
                        fotos.add(new FotoPalabra(letra, silaba, tiposilaba, palabra, frase, foto, tema));
                    }
                }while(cursor.moveToNext());
            }
        }
        numeroFotos=fotos.size();
        return  fotos;
    }
    public ArrayList<FotoPalabra> getFotosAleatorias(){
        Cursor   cursor = db.buscarFotosAleatorias(id_user);
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
                     String tiposilaba = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_TYPE_SYLLABE));
                     fotos.add(new FotoPalabra(letra, silaba, tiposilaba, palabra, frase, foto, tema));
                 }while(cursor.moveToNext());
            }
        }
        return fotos;
    }


    public void getParameterNivel(){
        Cursor cursor=db.getNivelByid(id_nivel);
        if(cursor!=null) {
            if (cursor.moveToLast()) {
                tipo = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_TYPE));
                dificultad= cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_DIFFICULTY));
                subnivel=cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_STEP));
            }
        }
        System.out.println("id "+ id_nivel+" tipo: "+tipo+"dificultad: "+ dificultad+"subnivel:  "+ subnivel);
    }

    public void rellenarConletras(String elegida,ArrayList<String> horizontalList) {

        String relleno[]=null;
        if((tipo.contains("inversas")||tipo.contains("trabadas")||(tipo.equals("silabasdirectas")&&elegida.length()<3))&&!(subnivel.equals("kcq"))&&!(subnivel.equals("zc"))&&!(subnivel.equals("gu"))){
            relleno= generaRelleno(elegida);
            System.out.println("Genero el relleno de manera artificial");
        }else {
            relleno = db.getRelleno(tipo, subnivel);
        }
        int posicionArray;
        int posicionesUsadas[]= {-1,-1,-1,-1};
        horizontalList.add(elegida);
        for(int i=0;i<4;i++){
            posicionArray=  (int)Math.random()*relleno.length;
            while((relleno[posicionArray].toUpperCase()).equals(elegida)||posicionArray==posicionesUsadas[0]||posicionArray==posicionesUsadas[1]||posicionArray==posicionesUsadas[2]||posicionArray==posicionesUsadas[3]){
                posicionArray=  (int)(Math.random()*relleno.length);
            }
            horizontalList.add(relleno[posicionArray]);
            posicionesUsadas[i]=posicionArray;
        }
    }

    private String[] generaRelleno(String elegida) {
        String vocales[]= new String[]{"a","e","i","o","u"};
        String resultado[]=new String[5];
        String consonantes[]={"B","C","D","F","G","H","J","K","L","M","N","Ñ","P","Q","R","S","T","V","W","X","Y","Z"};
        String digrafos[] ={"ch","ll",};
        if(tipo.equals("silabasdirectas")){
            for(int j=0;j<consonantes.length;j++) {
                if(elegida.toUpperCase().contains(consonantes[j])) {
                    for (int i = 0; i < vocales.length; i++) {
                        resultado[i] = consonantes[j] + vocales[i];
                    }
                }
            }
        }
        else {
            if (tipo.contains("inversa")) {
                for (int i = 0; i < vocales.length; i++) {
                    resultado[i] = vocales[i] + subnivel;
                }
            } else {
                for (int i = 0; i < vocales.length; i++) {
                    resultado[i] = subnivel + vocales[i];
                }
            }
        }

        return  resultado;
    }
    public void actualizarEstrellas(int aciertos){
        if(tipo.contains("palabras")||tipo.contains("frases")){
            db.actualizarEstrellas(tipo,1,aciertos);
        }
        else{
            db.actualizarEstrellas(tipo,dificultad,aciertos);
        }
    }
    public int getEstrellas(){
        if( tipo.contains("palabras")||tipo.contains("frases")) {
             return db.getEstrellas(tipo, 1);
     }
        else{
             return db.getEstrellas(tipo, dificultad);
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
    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void close(){
        db.close();
    }
}
