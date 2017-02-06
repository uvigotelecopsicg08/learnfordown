package com.uvigo.learnfordown.learnfordown;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Timestamp;

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

    //Tabla afinidad
    public static final String TABLE_AFINITY="AFINIDAD";
    public static final String CN_ID_AFINITY = "_id";
    public static final String CN_ID_USER_AFINITY ="id_usuario";
    public static final String CN_ID_WORD_AFINIFTY="id_palabra";
    public static final String CN_AFINITY_RATE ="grado_afinidad";
    public static final String CN_DIFFICULTY_RATE="grado_dificultad";

    //Tabla palabra
    public static final String TABLE_WORD="WORD";
    public static final String CN_ID_WORD = "_id";
    public static final String CN_LETTER = "letra";
    public static final String CN_SYLLABLE="silaba";
    public static final String CN_WORD="palabra";
    public static final String CN_SENTENCE= "frase";
    public static final String CN_PHOTO= "foto";
    public static final String CN_TOPIC= "tema";

    //Tabla SystemLog

    public static final String TABLE_SYSTEM_LOG="SYSTEMLOG";
    public static final String CN_ID_SYSTEM_LOG = "_id";
    public static final String CN_ID_USER_LOG = "id_user";
    public static final String CN_LAST_DATE="fecha_ultima";
    public static final String CN_GAME_TIME="tiempo_juego";
    public static final String CN_REGISTER_DATE="fecha_registro";


    //Tabla NivelUser
    public static final String TABLE_LEVEL_USER="NIVELUSER";
    public static final String CN_ID_LEVELUSER= "_id";
    public static final String CN_ID_USER_LEVEL ="id_usuario";
    public static final String CN_ID_LEVEL_LEVEL="id_nivel";
    public static final String CN_RIGTHS = "aciertos";
    public static final String CN_WRONGS = "errores";

    //Tabla nivel
    public static final String TABLE_LEVEL= "NIVEL";
    public static final String CN_ID_LEVEL= "_id";
    public static final String CN_TYPE ="tipo";
    public static final String CN_DIFFICULTY="dificultad";
    public static final String CN_STEP_NUMBER="numero_pruebas";








    public static final String CREATE_TABLE_USER ="create table "+TABLE_USER+" ("+CN_ID_USER+
            " integer primary key autoincrement,"
            + CN_NAME_USER + " VARCHAR(50) NOT NULL,"
            +CN_AGE_USER+" integer NOT NULL);";
    //+CN_DATE+" timestamp  DEFAULT CURRENT_TIMESTAMP);";

    public static final String CREATE_TABLE_AFFINITY="create table "+TABLE_AFINITY+" ("+CN_ID_AFINITY+
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
            CN_WORD+"  VARCHAR(50) NOT NULL,"+
            CN_SENTENCE+" VARCHAR(200) NOT NULL,"+
            CN_PHOTO+ "  integer NOT NULL,"+
            CN_TOPIC+" VARCHAR(50) NOT NULL); ";


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
            " FOREIGN KEY ("+CN_ID_LEVEL_LEVEL+") REFERENCES "+TABLE_LEVEL+"("+CN_ID_LEVEL+") ON DELETE CASCADE,"+
            " FOREIGN KEY ("+CN_ID_USER_LEVEL+") REFERENCES "+TABLE_USER+"("+CN_ID_USER+") ON DELETE CASCADE);";


    public static final String CREATE_TABLE_LEVEL="create table "+TABLE_LEVEL+" ("+CN_ID_LEVEL+
            " integer primary key autoincrement,"
            + CN_TYPE+ " VARCHAR(50),"
            + CN_DIFFICULTY+ " integer NOT NULL,"
            +CN_STEP_NUMBER+" integet NOT NULL,"+
            CN_WRONGS+" integer NOT NULL);";




    public DataBaseManager(Context context) {
        DbHelper helper=new DbHelper(context);
        db= helper.getWritableDatabase();
    }
    public void insertar_user (String nombre,int edad){
        ContentValues valores =new ContentValues();
        valores.put(CN_NAME_USER,nombre);
        valores.put(CN_AGE_USER,edad);
        db.insert(TABLE_USER,null,valores);
        Cursor cursor= cargarCursor_user();
        if(cursor!=null) {
            if (cursor.moveToLast()) {
                int id_user = cursor.getInt(cursor.getColumnIndexOrThrow(CN_ID_USER));
                insertar_Systemlog(id_user, 0);
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

    public Cursor cargarCursor_user(){
        String columnas[] = new String[]{CN_ID_USER, CN_NAME_USER,CN_AGE_USER};
        return db.query(TABLE_USER,columnas,null,null,null,null,null,null);
    }
    public void close() {
        db.close();
    }
    public void delete(){
        db.execSQL("DELETE FROM "+TABLE_USER);
    }
}
