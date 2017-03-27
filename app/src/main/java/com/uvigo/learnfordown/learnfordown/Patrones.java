package com.uvigo.learnfordown.learnfordown;

/**
 * Created by Susana on 20/02/2017.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.ArrayList;

import com.uvigo.learnfordown.learnfordown.strokes.app.datatype.Point2D;

public class Patrones implements Serializable {

    private String letra;
    private ArrayList<LinkedList<Point2D>> puntosNormalizados;
    private ArrayList<LinkedList<Float>> angulosRadiales;
    private ArrayList<Integer> numeroTrazos;
    private Double umbralNormalizacion;
    private Double umbralAngular;

    public Patrones() {
    }

    public Patrones(String letra, ArrayList<LinkedList<Point2D>> puntosNormalizados, ArrayList<LinkedList<Float>> angulosRadiales, ArrayList<Integer> numeroTrazos, Double umbralNormalizacion, Double umbralAngular) {
        super();
        this.letra = letra;
        this.puntosNormalizados = puntosNormalizados;
        this.angulosRadiales = angulosRadiales;
        this.numeroTrazos = numeroTrazos;
        this.umbralNormalizacion = umbralNormalizacion;
        this.umbralAngular = umbralAngular;

    }


    public String getLetra() {
        return letra;
    }

    public ArrayList<LinkedList<Point2D>> getPuntosNormalizados() {
        return puntosNormalizados;
    }

    public ArrayList<LinkedList<Float>> getAngulosRadiales() {
        return angulosRadiales;
    }

    public ArrayList<Integer> getNumeroTrazos() {
        return numeroTrazos;
    }

    public Double getUmbralNormalizacion() {
        return umbralNormalizacion;
    }

    public Double getUmbralAngular() {
        return umbralAngular;
    }



}
