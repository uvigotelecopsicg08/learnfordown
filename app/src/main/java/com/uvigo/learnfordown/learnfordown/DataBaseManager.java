package com.uvigo.learnfordown.learnfordown;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.session.PlaybackState;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Juani on 03/02/2017.
 */
public class DataBaseManager {
    private SQLiteDatabase db;

    //Tabla usuario
    public static final String TABLE_USER="USUARIO";
    public static final String CN_ID_USER = "_id";
    public static final String CN_NAME_USER ="nombre";
    public static final String CN_AGE_USER="edad";
    public static final String CN_LOGGUED="logeado";
    public static final String CN_AVATAR="avatar";

    //Tabla afinidad
    public static final String TABLE_AFFINITY ="AFINIDAD";
    public static final String CN_ID_AFINITY = "_id";
    public static final String CN_ID_USER_AFINITY ="id_usuario";
    public static final String CN_ID_WORD_AFINIFTY="id_palabra";
    public static final String CN_AFINITY_RATE ="grado_afinidad";
    public static final String CN_DIFFICULTY_RATE="grado_dificultad";

    //Tabla palabra
    public static final String TABLE_WORD="PALABRA";
    public static final String CN_ID_WORD = "_id";
    public static final String CN_LETTER = "letra";
    public static final String CN_SYLLABLE="silaba";
    public static final String CN_TYPE_SYLLABE="tipo_silaba";
    public static final String CN_WORD="palabra";
    public static final String CN_SENTENCE= "frase";
    public static final String CN_PHOTO= "foto";
    public static final String CN_TOPIC= "tema";
    public static final String CN_NUMBER_SYLLABLES= "numero_silabas";
    public static final String CN_SOUND= "sonido";

    //Tabla SystemLog

    public static final String TABLE_SYSTEM_LOG="SYSTEMLOG";
    public static final String CN_ID_SYSTEM_LOG = "_id";
    public static final String CN_ID_USER_LOG = "id_user";
    public static final String CN_LAST_DATE="fecha_ultima";
    public static final String CN_GAME_TIME="tiempo_juego";
    public static final String CN_REGISTER_DATE="fecha_registro";


    //Tabla NivelUser
    public static final String TABLE_LEVEL_USER="NIVELUSUARIO";
    public static final String CN_ID_LEVELUSER= "_id";
    public static final String CN_ID_USER_LEVEL ="id_usuario";
    public static final String CN_ID_LEVEL_LEVEL="id_nivel";
    public static final String CN_RIGTHS = "aciertos";
    public static final String CN_WRONGS = "errores";
    public static final String CN_COMPLETED = "completado";
    public static final String CN_TIME_START ="tiempo_inicio";
    public static final String CN_TIME_END ="tiempo_fin";

    //Tabla nivel
    public static final String TABLE_LEVEL= "NIVEL";
    public static final String CN_ID_LEVEL= "_id";
    public static final String CN_TYPE ="tipo";
    public static final String CN_DIFFICULTY="dificultad";
    public static final String CN_STEP ="subnivel";
   ;


    // Tabla estrelllas
    public static final String TABLE_STARS= "ESTRELLAS";
    public static final String CN_STARS_TYPE ="tipo_nivel";
    public static final String CN_NUMBER_STARS ="numero_estrellas";
    public static final String CN_STARS_DIFICULTAD ="dificultad_estrellas";

    public static final String CREATE_TABLE_USER ="create table "+TABLE_USER+" ("+CN_ID_USER+
            " integer primary key autoincrement,"
            + CN_NAME_USER + " VARCHAR(50) NOT NULL UNIQUE,"
            +CN_AGE_USER+" integer NOT NULL,"+
             CN_AVATAR+"  integer NOT NULL,"+
             CN_LOGGUED+" boolean NOT NULL);";
    //+CN_DATE+" timestamp  DEFAULT CURRENT_TIMESTAMP);";

    public static final String CREATE_TABLE_AFFINITY="create table "+ TABLE_AFFINITY +" ("+CN_ID_AFINITY+
            " integer primary key autoincrement,"
            + CN_ID_USER_AFINITY+ " integer NOT NULL,"
            +CN_ID_WORD_AFINIFTY+" integer NOT NULL,"+
            CN_AFINITY_RATE +"  FLOAT(5,4) NOT NULL,"+
            CN_DIFFICULTY_RATE+" FLOAT(5,4) NOT NULL,"+
            " FOREIGN KEY ("+CN_ID_USER_AFINITY+") REFERENCES "+TABLE_USER+"("+CN_ID_USER+") ON DELETE CASCADE," +
            " FOREIGN KEY ("+CN_ID_WORD_AFINIFTY+") REFERENCES "+TABLE_WORD+"("+CN_ID_WORD+") ON DELETE CASCADE);";

    public static final String CREATE_TABLE_WORD="create table "+TABLE_WORD+" ("+CN_ID_WORD+
            " integer primary key autoincrement,"
            + CN_LETTER+ " VARCHAR(20) NOT NULL,"
            +CN_SYLLABLE+" VARCHAR(20) NOT NULL,"+
            CN_TYPE_SYLLABE+" VARCHAR(20) NOT NULL,"+
            CN_WORD+"  VARCHAR(50) NOT NULL UNIQUE,"+
            CN_SENTENCE+" VARCHAR(200) NOT NULL,"+
            CN_PHOTO+ "  integer NOT NULL,"+
            CN_TOPIC+" VARCHAR(50) NOT NULL,"+
            CN_NUMBER_SYLLABLES +" integer NOT NULL,"+
            CN_SOUND +" VARCHAR(50) NOT NULL);";


    public static final String CREATE_TABLE_SYSTEM="create table "+TABLE_SYSTEM_LOG+" ("+CN_ID_SYSTEM_LOG+
            " integer primary key autoincrement,"
            + CN_ID_USER_LOG+ " integer NOT NULL,"
            +CN_LAST_DATE+" timestamp  DEFAULT CURRENT_TIMESTAMP,"+
            CN_GAME_TIME+"  FLOAT(8.4),"+
            CN_REGISTER_DATE+" timestamp,"+
            " FOREIGN KEY ("+CN_ID_USER_LOG+") REFERENCES "+TABLE_USER+"("+CN_ID_USER+") ON DELETE CASCADE);";

    public static final String CREATE_TABLE_NIVELUSER="create table "+TABLE_LEVEL_USER+" ("+CN_ID_LEVELUSER+
            " integer primary key autoincrement,"
            + CN_ID_USER_LEVEL+ " integer NOT NULL,"
            + CN_ID_LEVEL_LEVEL+ " integer NOT NULL,"
            +CN_RIGTHS+" integet NOT NULL,"+
            CN_WRONGS+" integer NOT NULL ,"+
            CN_COMPLETED+ " boolean NOT NULL,"+
             CN_TIME_START +" timeStamp, "+
            CN_TIME_END+ " timeStamp, "+
            " FOREIGN KEY ("+CN_ID_LEVEL_LEVEL+") REFERENCES "+TABLE_LEVEL+"("+CN_ID_LEVEL+") ON DELETE CASCADE,"+
            " FOREIGN KEY ("+CN_ID_USER_LEVEL+") REFERENCES "+TABLE_USER+"("+CN_ID_USER+") ON DELETE CASCADE);";


    public static final String CREATE_TABLE_LEVEL="create table "+TABLE_LEVEL+" ("+CN_ID_LEVEL+
            " integer primary key autoincrement,"
            + CN_TYPE+ " VARCHAR(50),"
            + CN_DIFFICULTY+ " integer NOT NULL,"
            + CN_STEP +" VARCHAR(50)  NOT NULL );";

    public static final String CREATE_TABLE_STARS="create table "+TABLE_STARS+" ("+CN_STARS_TYPE+
            " VARCHAR(50)  NOT NULL, "+
          CN_STARS_DIFICULTAD + " integer NOT NULL, "+
            CN_ID_USER + " integer NOT NULL, "+
            CN_NUMBER_STARS+" integer NOT NULL );";


    public DataBaseManager(Context context) {
        DbHelper helper=new DbHelper(context);
        db= helper.getWritableDatabase();
    }
    public void insertar_user (String nombre,int edad,  HashMap<String,Boolean> gustos,int avatar){
        int  id_user=0;
        ContentValues valores =new ContentValues();
        valores.put(CN_NAME_USER,nombre);
        valores.put(CN_AGE_USER,edad);
        valores.put(CN_LOGGUED,true);
        valores.put(CN_AVATAR,avatar);
        //Desloguear si hay otro usuario
        singOut();
        db.insert(TABLE_USER,null,valores);
        Cursor cursor= cargarCursor_user();
        if(cursor!=null) {
            if (cursor.moveToLast()) {
                id_user = cursor.getInt(cursor.getColumnIndexOrThrow(CN_ID_USER));
                insertar_Systemlog(id_user, 0);
                insertarAfinidad(id_user,gustos);
                insertarNivelUser(id_user);
            }
        }
        inicializarEstrellas(id_user);

    }

    public void insertarAfinidad(int id_user, HashMap<String,Boolean> gustos) {
        Cursor cursor = db.query(TABLE_WORD,null,null,null,null,null,null,null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {

                    int id_word = cursor.getInt(cursor.getColumnIndexOrThrow(CN_ID_WORD));
                    String tema= cursor.getString(cursor.getColumnIndexOrThrow(CN_TOPIC));
                    ContentValues valores = new ContentValues();
                    valores.put(CN_ID_WORD_AFINIFTY, id_word);
                    valores.put(CN_ID_USER_AFINITY, id_user);
                    //Cambiar por los valores de usuario
                    if(gustos.containsKey(tema)){
                        if(gustos.get(tema)){
                            valores.put(CN_AFINITY_RATE, 0.75);
                        }
                        else{
                            valores.put(CN_AFINITY_RATE, 0.25);
                        }
                    }
                    else {
                        valores.put(CN_AFINITY_RATE, 0.5);
                    }
                    valores.put(CN_DIFFICULTY_RATE, 0.5);
                    db.insert(TABLE_AFFINITY, null, valores);
                }while(cursor.moveToNext());
            }
        }

    }
    public void insertarNivelUser(int id_user){
        Cursor cursor = db.query(TABLE_LEVEL,null,null,null,null,null,null,null);
        if(cursor!=null) {
            if(cursor.moveToFirst()) {
                do {
                    int id_level = cursor.getInt(cursor.getColumnIndexOrThrow(CN_ID_LEVEL));
                    ContentValues valores = new ContentValues();
                    valores.put(CN_ID_LEVEL_LEVEL, id_level);
                    valores.put(CN_ID_USER_LEVEL, id_user);
                    valores.put(CN_RIGTHS, 0);
                    valores.put(CN_WRONGS, 0);
                    valores.put(CN_COMPLETED, false);
                    db.insert(TABLE_LEVEL_USER, null, valores);
                }while(cursor.moveToNext());
            }
        }

    }

    public void insertar_Systemlog (int id_user,float tiempo){
        ContentValues valores =new ContentValues();
        valores.put(CN_GAME_TIME,tiempo);
        valores.put(CN_ID_USER_LOG,id_user);
        valores.put(CN_REGISTER_DATE,new java.util.Date().getTime());
        db.insert(TABLE_SYSTEM_LOG,null,valores);
    }
    public void insertar_nivel (String tipo,int dificultad,String subnivel){

        ContentValues valores =new ContentValues();
      //  valores.put(CN_ID_LEVEL,id_level);
        valores.put(CN_TYPE,tipo);
        valores.put(CN_DIFFICULTY,dificultad);
        valores.put(CN_STEP,subnivel);
        db.insert(TABLE_LEVEL,null,valores);
    }
    public void insertar_foto(String letra,String silaba,String tipoSilaba,String palabra,String frase,int foto,String tema,int numero_silabas,String  sonido){
        ContentValues valores =new ContentValues();
        valores.put(CN_LETTER,letra);
        valores.put(CN_SYLLABLE,silaba);
        valores.put(CN_TYPE_SYLLABE,tipoSilaba);
        valores.put(CN_WORD,palabra);
        valores.put(CN_SENTENCE,frase);
        valores.put(CN_PHOTO,foto);
        valores.put(CN_TOPIC,tema);
        valores.put(CN_NUMBER_SYLLABLES,numero_silabas);
        valores.put(CN_SOUND,sonido);
        db.insert(TABLE_WORD,null,valores);

    }

    public Cursor cargarCursor_user(){
        String columnas[] = new String[]{CN_ID_USER, CN_NAME_USER,CN_AGE_USER};
        return db.query(TABLE_USER,columnas,null,null,null,null,null,null);
    }

    public  int getIdUser(){
        int id_user =0;
        String columnas[] = new String[]{CN_ID_USER};
        String whereClause = CN_LOGGUED+" = ? ";
        String[] whereArgs = new String[] {String.valueOf(1)};
       Cursor cursor= db.query(TABLE_USER,columnas,whereClause,whereArgs,null,null,null,null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                id_user = cursor.getInt(cursor.getColumnIndexOrThrow(CN_ID_USER));
            }
        }
        System.out.println("El id de usuario es "+id_user);
        return id_user;

    }

    public Cursor getNivel(String tipo, int dificultad,int id_user){
        Cursor cursor;
        String tablas=TABLE_LEVEL+","+TABLE_LEVEL_USER;
        String columnas[] = new String[]{TABLE_LEVEL_USER+"."+CN_ID_LEVEL_LEVEL,CN_STEP};

        if(dificultad==-1){
            String whereClause = CN_TYPE+" = ? AND "+CN_COMPLETED+ " = 0 AND "+TABLE_LEVEL_USER+"."+CN_ID_LEVEL_LEVEL+" = "+TABLE_LEVEL+"."+CN_ID_LEVEL+
                    " AND "+CN_ID_USER_LEVEL+" = ?";
            String[] whereArgs = new String[] {tipo,String.valueOf(id_user)};
             cursor= db.query(TABLE_LEVEL,columnas,whereClause,whereArgs,null,null,null,null);
        }
        else{
            String whereClause =CN_TYPE+" = ? AND "+CN_DIFFICULTY+" = ? AND "+CN_COMPLETED+ " = 0 AND "+TABLE_LEVEL_USER+"."+CN_ID_LEVEL_LEVEL+" = "+TABLE_LEVEL+"."+CN_ID_LEVEL+
                    " AND "+CN_ID_USER_LEVEL+" = ?";
            String[] whereArgs = new String[] {tipo,String.valueOf(dificultad),String.valueOf(id_user)};
            cursor= db.query(tablas,columnas,whereClause,whereArgs,null,null,null,null);
        }
        return cursor;
    }
    public Cursor getNivelByid(int id_nivel){
        String columnas[] = new String[]{CN_TYPE,CN_DIFFICULTY,CN_STEP};
        String whereClause = CN_ID_LEVEL+" = ? ";
        String[] whereArgs = new String[] {String.valueOf(id_nivel)};
        Cursor cursor= db.query(TABLE_LEVEL,null,whereClause,whereArgs,null,null,null,null);
        return  cursor;
    }
    public void close() {
        db.close();
    }
    public void delete(){
        db.execSQL("DELETE FROM "+TABLE_USER);
    }

    public void updateResults(int id_user, int id_nivel,boolean completado,int aciertos,int fallos) {
        ContentValues valores = new ContentValues();
        valores.put(CN_COMPLETED,completado);
        valores.put(CN_RIGTHS,aciertos);
        valores.put(CN_WRONGS,fallos);
        String whereClause =CN_ID_USER_LEVEL+" = ? AND "+CN_ID_LEVEL_LEVEL+ " =?";
        String[] whereArgs = new String[]{String.valueOf(id_user),String.valueOf(id_nivel) };
        db.update(TABLE_LEVEL_USER, valores,whereClause, whereArgs);
        //Insertar referencia a log
    }
    public Cursor buscarFotos(String tipo,int id_user,String subnivel,int numeroSilabas){

        String tablas=TABLE_AFFINITY+","+TABLE_WORD;
        String  whereClause =null;
        String[] whereArgs=null;
        if(numeroSilabas>0){
          /*  whereClause = CN_ID_USER_LEVEL+" = ?  AND "+TABLE_AFFINITY+"."+CN_ID_WORD_AFINIFTY+" = "+TABLE_WORD+"."+CN_ID_WORD+" AND "+
                    CN_SOUND+" = ? AND "+CN_TYPE_SYLLABE+" = ? AND "+CN_NUMBER_SYLLABLES+ " = ?";
            whereArgs = new String[] {String.valueOf(id_user),subnivel,tipo,String.valueOf(numeroSilabas)};
            */
            whereClause = CN_ID_USER_LEVEL+" = ?  AND "+TABLE_AFFINITY+"."+CN_ID_WORD_AFINIFTY+" = "+TABLE_WORD+"."+CN_ID_WORD+" AND "+
                    CN_TYPE_SYLLABE+" = ? AND "+CN_NUMBER_SYLLABLES+ " = ?";
            whereArgs = new String[] {String.valueOf(id_user),tipo,String.valueOf(numeroSilabas)};
        }
        else{
            whereClause = CN_ID_USER_LEVEL+" = ?  AND "+TABLE_AFFINITY+"."+CN_ID_WORD_AFINIFTY+" = "+TABLE_WORD+"."+CN_ID_WORD+" AND "+
                    CN_SOUND+" = ? AND "+CN_TYPE_SYLLABE+" = ?";
            whereArgs = new String[] {String.valueOf(id_user),subnivel,tipo};
        }

        String orderBy= CN_AFINITY_RATE +" DESC";
        Cursor cursor= db.query(tablas,null,whereClause,whereArgs,null,null,orderBy ,null);
        if(cursor==null){
            System.out.println("El cursor es nulo");
        }
        return cursor;
    }
    public String[] getRelleno(String tipo,String subnivel)
    {
        String resultado[]=null;
        String tablas=TABLE_WORD;
        String whereClause=null;
        String whereArgs[]=null;
        String orderBy =null;
        String columnas[]=null ;
        System.out.println(tipo);
        System.out.println(subnivel);

        if(tipo.contains("letra")) {
            columnas = new String[]{"letra"};
        }
        else{
            if(tipo.contains("silaba")){
                columnas=new String[]{"silaba"};
            }
            else if(tipo.contains("palabra")){
                columnas=new String[]{"palabra"};
            }
        }
        orderBy= columnas[0] +" ASC";


        if(subnivel!=null){
            if(subnivel.equals("a")||subnivel.equals("e")||subnivel.equals("i")||subnivel.equals("o")||subnivel.equals("u")){
                subnivel="vocales";
            }
        }

        if(subnivel!=null &&!(tipo.equals("leerletras")&&!subnivel.equals("vocales"))) {
            if(tipo.contains("silaba")){
                if(tipo.equals("silabasdirectas")){
                    whereClause = CN_SOUND + " = ? AND "+CN_TYPE_SYLLABE+" = ? " ;
                    whereArgs = new String[]{subnivel,"directa"};
                }
                if(tipo.equals("silabasindirectas")){
                    whereClause = CN_SOUND + " = ? AND "+CN_TYPE_SYLLABE+" = ? " ;
                    whereArgs = new String[]{subnivel,"directa"};
                }
                if(tipo.equals("silabastrabadas")){
                    whereClause = CN_SOUND + " = ? AND "+CN_TYPE_SYLLABE+" = ? " ;
                    whereArgs = new String[]{subnivel,"trabada"};
                }

            }else {
                whereClause = CN_SOUND + " = ?";
                whereArgs = new String[]{subnivel};
            }

        }



        Cursor cursor= db.query(true,tablas,columnas,whereClause,whereArgs,null,null,orderBy ,null);
        ArrayList<String> arrayListAux= new ArrayList<>();
        String stringAux="";
        if(cursor!=null){

            if (cursor.moveToFirst()) {
                do {
                    if(tipo.contains("letra")) {
                        stringAux =  cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_LETTER));
                    }
                    else{
                        if(tipo.contains("silaba")){
                            stringAux =  cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_SYLLABLE));
                        }
                        else if(tipo.contains("palabra")){
                            stringAux =  cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_WORD));
                        }

                    }
                    arrayListAux.add(stringAux);
                }while(cursor.moveToNext());
            }
        }
        else{
            System.out.println("El cursor es nulo");
        }
    resultado=new String[arrayListAux.size()];
        for(int i=0;i<arrayListAux.size();i++){
            resultado[i]=arrayListAux.get(i);
        }
    return  resultado;
    }
    public void mostrarTablas(){
        Cursor cursor= db.query(TABLE_LEVEL_USER,null,null,null,null,null,null,null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    System.out.print("id: "+ cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_ID_LEVEL)));
                    System.out.print("aciertos: "+cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_RIGTHS)));
                    System.out.print("fallos: "+cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_WRONGS)));
                    System.out.print("completado: "+cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_COMPLETED)));
                    System.out.println("");
                }while(cursor.moveToNext());
            }
        }

    }

    public Cursor resetNivel(String tipo, int dificultad, int id_user) {
        Cursor cursor;
        String tablas=TABLE_LEVEL+","+TABLE_LEVEL_USER;
        String columnas[] = new String[]{TABLE_LEVEL_USER+"."+CN_ID_LEVEL,CN_STEP};

        if(dificultad==-1){
            String whereClause = CN_TYPE+" = ? AND "+TABLE_LEVEL_USER+"."+CN_ID_LEVEL_LEVEL+" = "+TABLE_LEVEL+"."+CN_ID_LEVEL+
                    " AND "+CN_ID_USER_LEVEL+" = ?";
            String[] whereArgs = new String[] {tipo,String.valueOf(id_user)};
            cursor= db.query(TABLE_LEVEL,columnas,whereClause,whereArgs,null,null,null,null);
        }
        else {
            String whereClause = CN_TYPE + " = ? AND " + CN_DIFFICULTY + " = ? AND " + TABLE_LEVEL_USER + "." + CN_ID_LEVEL_LEVEL + " = " + TABLE_LEVEL + "." + CN_ID_LEVEL +
                    " AND " + CN_ID_USER_LEVEL + " = ?";
            String[] whereArgs = new String[]{tipo, String.valueOf(dificultad), String.valueOf(id_user)};
            cursor = db.query(tablas, columnas, whereClause, whereArgs, null, null, null, null);
        }

            if(cursor!=null) {
                if (cursor.moveToFirst()) {
                    do {
                        ContentValues valores = new ContentValues();
                        valores.put(CN_COMPLETED,0);
                        valores.put(CN_RIGTHS,0);
                        valores.put(CN_WRONGS,0);
                      String  whereClause2 =CN_ID_USER_LEVEL+" = ? AND "+CN_ID_LEVEL_LEVEL+ " =?";
                      String[]   whereArgs2 = new String[]{String.valueOf(id_user),String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_ID_LEVEL))) };
                        db.update(TABLE_LEVEL_USER, valores,whereClause2, whereArgs2);
                    }while(cursor.moveToNext());
                }
            }
        return cursor;

    }

    public Cursor buscarFotosbyLetra(int id_user, String subnivel) {
        String tablas=TABLE_AFFINITY+","+TABLE_WORD;
        String  whereClause =null;
        String[] whereArgs=null;

        whereClause = CN_ID_USER_LEVEL+" = ?  AND "+TABLE_AFFINITY+"."+CN_ID_WORD_AFINIFTY+" = "+TABLE_WORD+"."+CN_ID_WORD+" AND "+
                    CN_LETTER+" = ?  ";
        whereArgs = new String[] {String.valueOf(id_user),subnivel};


        String orderBy= CN_AFINITY_RATE +" DESC";
        Cursor cursor= db.query(tablas,null,whereClause,whereArgs,null,null,orderBy ,null);
        if(cursor==null){
            System.out.println("El cursor es nulo");
        }
        return cursor;
    }
    public Cursor buscarFotosAleatorias(int id_user){
        String tablas=TABLE_AFFINITY+","+TABLE_WORD;
        String  whereClause =null;
        String[] whereArgs=null;

        whereClause = CN_ID_USER_LEVEL+" = ?  AND "+TABLE_AFFINITY+"."+CN_ID_WORD_AFINIFTY+" = "+TABLE_WORD+"."+CN_ID_WORD ;
        whereArgs = new String[] {String.valueOf(id_user)};


        String orderBy= CN_AFINITY_RATE +" DESC";
        Cursor cursor= db.query(tablas,null,whereClause,whereArgs,null,null,orderBy ,null);
        if(cursor==null){
            System.out.println("El cursor es nulo");
        }
        return cursor;
    }
    public Nivel getPrimerNivel(){
        int id_user=  getIdUser();
        String tablas=TABLE_LEVEL+","+TABLE_LEVEL_USER;

         //   String whereClause = CN_COMPLETED+ " = 0 AND "+TABLE_LEVEL_USER+"."+CN_ID_LEVEL_LEVEL+" = "+TABLE_LEVEL+"."+CN_ID_LEVEL+
           //         " AND "+CN_ID_USER_LEVEL+" = ?";
        String whereClause = TABLE_LEVEL_USER+"."+CN_ID_LEVEL_LEVEL+" = "+TABLE_LEVEL+"."+CN_ID_LEVEL+
                        " AND "+CN_ID_USER_LEVEL+" = ?";
            String[] whereArgs = new String[] {String.valueOf(id_user)};
        String orderBy= CN_TIME_START +" DESC";
          Cursor cursor= db.query(tablas,null,whereClause,whereArgs,null,null,orderBy,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                String tipoNivel=cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_TYPE));
                int id_nivel=cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_ID_LEVEL));
                int dificultad=cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_DIFFICULTY));
                System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_TIME_START)));
                Nivel nivel = new Nivel(id_nivel,dificultad,tipoNivel);
                return  nivel;
            }

            else{
                return  null;
            }

        }
        else {
            return null;
        }

    }

    public void inicializarEstrellas(int id_usuario) {
        String tablas = TABLE_LEVEL;
        String columnas[] = new String[]{CN_TYPE, CN_DIFFICULTY};
        Cursor cursor = db.query(true, tablas, columnas, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ContentValues valores = new ContentValues();
                    String tipo =cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.CN_TYPE));
                    int dificultad= cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_DIFFICULTY));
                    if((!tipo.contains("palabras")&&!tipo.contains("frases"))||dificultad==1) {
                        valores.put(CN_STARS_TYPE, tipo);
                        valores.put(CN_NUMBER_STARS, 0);
                        valores.put(CN_STARS_DIFICULTAD, dificultad);
                        valores.put(CN_ID_USER, id_usuario);
                        db.insert(TABLE_STARS, null, valores);
                    }
                } while (cursor.moveToNext());
            }
        }
    }
    public void actualizarEstrellas(String tipo,int dificultad,int aciertos,int id_user){
        ContentValues valores = new ContentValues();
        valores.put(CN_NUMBER_STARS,aciertos);

        String  whereClause =CN_STARS_TYPE+" = ? AND "+CN_STARS_DIFICULTAD+ " =? AND "+CN_ID_USER+ " =?";
        String[]   whereArgs = new String[]{tipo,String.valueOf(dificultad),String.valueOf(id_user)};
        db.update(TABLE_STARS, valores,whereClause, whereArgs);

    }
    public int getEstrellas(String tipo,int dificultad,int id_user){
        String  whereClause =CN_STARS_TYPE+" = ? AND "+CN_STARS_DIFICULTAD+ " =? AND "+CN_ID_USER+ " =?";
        String[]   whereArgs = new String[]{tipo,String.valueOf(dificultad),String.valueOf(id_user)};
        Cursor cursor= db.query(TABLE_STARS,null,whereClause,whereArgs,null,null,null ,null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_NUMBER_STARS));
            }
            else{
                return 0;
            }
        }
        else{
            return 0;
        }

    }
    public void actulizaTimeStamp(boolean inicio,int id_nivel,int id_user){
        ContentValues valores = new ContentValues();
        if(inicio) {
            valores.put(CN_TIME_START,System.currentTimeMillis());
        }
        else{
            valores.put(CN_TIME_END,System.currentTimeMillis());
        }
        String  whereClause =CN_ID_LEVEL_LEVEL+" = ? AND "+CN_ID_USER_LEVEL+ " =?";
        String[]   whereArgs = new String[]{String.valueOf(id_nivel),String.valueOf(id_user)};
        db.update(TABLE_LEVEL_USER, valores,whereClause, whereArgs);
    }
    public Cursor getUser(){
        return  db.query(TABLE_USER,null,null,null,null,null,null ,null);
    }
    public void deleteUser(int id_user) {
        String whereClause = CN_ID_USER + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id_user)};
        db.delete(TABLE_USER, whereClause, whereArgs);
         whereClause = CN_ID_USER_LEVEL + " = ?";
        db.delete(TABLE_LEVEL_USER, whereClause, whereArgs);
        logueaUltimo();

    }

    private void singOut(){
        ContentValues valores = new ContentValues();
        valores.put(CN_LOGGUED,0);

        String  whereClause =CN_LOGGUED+" = ?";
        String[]   whereArgs = new String[]{String.valueOf(1)};
        db.update(TABLE_USER, valores,whereClause, whereArgs);
    }

    public void changeLogint(int id_user){
        singOut();
        ContentValues valores = new ContentValues();
        valores.put(CN_LOGGUED,1);
        String  whereClause =CN_ID_USER+" = ?";
        String[]   whereArgs = new String[]{String.valueOf(id_user)};
        db.update(TABLE_USER, valores,whereClause, whereArgs);
    }

    private void logueaUltimo(){
        Cursor cursor = getUser();
        if (cursor != null) {
            if(cursor.moveToLast()){
             changeLogint(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_ID_USER)));
            }
        }
    }
}
