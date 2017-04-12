package com.uvigo.learnfordown.learnfordown;

import java.util.Date;

/**
 * Created by Juani on 22/03/2017.
 */

public class Nivel {
    private int id_user;
    private int id_nivel;
    private Date horainicio;
    private Date horafin;
    private   String tipo;
    private String subnivel;
    private String palabra;
    private int fallos;
    private int aciertos;
      int dificultad;

    public Nivel(int id_nivel,int dificultad,String tipoNivel){
        this.id_nivel= id_nivel;
        this.dificultad= dificultad;
        this.tipo = tipoNivel;
    }

;


    public Nivel(int id_user, int id_nivel, Date horainicio, Date horafin, String tipo, String subnivel, int fallos, int aciertos, int dificultad) {
        this.id_user = id_user;
        this.id_nivel = id_nivel;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.tipo = tipo;
        this.subnivel = subnivel;
        this.fallos = fallos;
        this.aciertos = aciertos;
        this.dificultad = dificultad;
    }



    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubnivel() {
        return subnivel;
    }

    public void setSubnivel(String subnivel) {
        this.subnivel = subnivel;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
