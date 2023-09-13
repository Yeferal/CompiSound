/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.reproductor.gramm.comunication.obj;

import com.example.reproductor.gramm.notas.MusicalNotes;

/**
 *
 * @author Usuario
 */
public class DataPista {
    private int channel;
    private MusicalNotes note;
    private int octave;
    private int duracion;

    public DataPista(int channel, MusicalNotes note, int octave, int duracion) {
        this.channel = channel;
        this.note = note;
        this.octave = octave;
        this.duracion = duracion;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public MusicalNotes getNote() {
        return note;
    }

    public void setNote(MusicalNotes note) {
        this.note = note;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    public String getValNoteString(){
        switch(note){
            case C:
                return "Do";
            case C_S:
                return "Do#";
            case D:
                return "Re";
            case D_S:
                return "Re#";
            case E:
                return "Mi";
            case F:
                return "Fa";
            case F_S:
                return "Fa#";
            case G:
                return "Sol";
            case G_S:
                return "Sol#";
            case A:
                return "La";
            case A_S:
                return "La#";
            case B:
                return "Si";
            default:
                return "Do";
        }
    }
    
}
