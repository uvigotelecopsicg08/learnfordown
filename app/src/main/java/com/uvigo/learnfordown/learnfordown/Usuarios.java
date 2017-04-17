package com.uvigo.learnfordown.learnfordown;

/**
 * Created by Juani on 15/04/2017.
 */

public class Usuarios {
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    private String nombre;
    private int edad;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Usuarios() {
    }

    public Usuarios(String mId, String nombre, int edad) {
        this.mId = mId;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Usuarios(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}
