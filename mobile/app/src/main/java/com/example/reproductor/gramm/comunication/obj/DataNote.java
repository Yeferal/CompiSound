/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.reproductor.gramm.comunication.obj;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class DataNote implements Serializable {
    private int duration;
    private double frecuencia;

    private int channel;

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

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Reproducirt(" +
                "duration=" + duration +
                ", frecuencia=" + frecuencia +
                ", channel=" + channel +
                ')';
    }
}
