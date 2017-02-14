package com.uvigo.learnfordown.learnfordown;

/**
 * Created by Juani on 12/02/2017.
 */

public class FotoPalabra {

    private String letra;
    private String silaba;


    private String tipo_silaba;
    private String palabra;
    private String frase;
    private int foto;
    private String tema;


    public FotoPalabra(String letra, String silaba, String tipo_silaba, String palabra, String frase, int foto, String tema) {
        this.letra = letra;
        this.silaba = silaba;
        this.tipo_silaba = tipo_silaba;
        this.palabra = palabra;
        this.frase = frase;
        this.foto = foto;
        this.tema = tema;
        utilizada =false;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getSilaba() {
        return silaba;
    }

    public void setSilaba(String silaba) {
        this.silaba = silaba;
    }

    public String getTipo_silaba() {
        return tipo_silaba;
    }

    public void setTipo_silaba(String tipo_silaba) {
        this.tipo_silaba = tipo_silaba;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public boolean isUtilizada() {
        return utilizada;
    }

    public void setUtilizada(boolean utilizada) {
        this.utilizada = utilizada;
    }

    private boolean utilizada;

}
