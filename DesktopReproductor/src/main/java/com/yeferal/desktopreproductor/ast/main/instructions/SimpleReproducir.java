/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.main.instructions.notas.MusicalNotes;
import com.yeferal.desktopreproductor.utils.filesadm.FrecuencysNotes;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class SimpleReproducir implements Serializable{
    private MusicalNotes note;
    private int octave;
    private int time;
    private int channel;
    private FrecuencysNotes frecuencysNotes = new FrecuencysNotes();

    public SimpleReproducir(MusicalNotes note, int octave, int time, int channel) {
        this.note = note;
        this.octave = octave;
        this.time = time;
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

    public void setOctave(int Octave) {
        this.octave = Octave;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getChannel() {
        return channel;
    }

    public void setChanel(int chanel) {
        this.channel = chanel;
    }
    
    public int getValNote(){
        switch(note){
            case C:
                return 60;
            case C_S:
                return 61;
            case D:
                return 62;
            case D_S:
                return 63;
            case E:
                return 64;
            case F:
                return 65;
            case F_S:
                return 66;
            case G:
                return 67;
            case G_S:
                return 68;
            case A:
                return 69;
            case A_S:
                return 70;
            case B:
                return 71;
            default:
                return 0;
        }
    }
}
