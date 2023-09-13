/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.gramm.comunication.obj;

/**
 *
 * @author Usuario
 */
public class DataNote {
    private int duration;
    private double frecuencia;

    public DataNote(int duration, double frecuencia) {
        this.duration = duration;
        this.frecuencia = frecuencia;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    
}
