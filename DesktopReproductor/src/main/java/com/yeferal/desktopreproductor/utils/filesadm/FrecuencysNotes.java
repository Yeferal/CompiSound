/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.utils.filesadm;

import com.yeferal.desktopreproductor.ast.main.instructions.notas.MusicalNotes;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class FrecuencysNotes implements Serializable {
    private double [][] tablaFrecuency = {
        // Oc.0,   Oc.1,    Oc.2,    Oc.3,    Oc.4,    Oc.5,     Oc.6,     Oc.7,     Oc.8
        {   0.0,  32.70,   65.41,  130.81,  261.63,  523.25,  1046.50,  2093.00,  4186.01}, // C
        {   0.0,  34.65,   69.30,  138.59,  277.18,  554.37,  1108.73,  2217.49,     0.00}, // C#
        {   0.0,  36.71,   73.42,  146.83,  293.66,  587.33,  1174.66,  2349.32,     0.00}, // D
        {   0.0,  38.89,   77.78,  155.56,  311.13,  622.25,  1244.51,  2489.02,     0.00}, // D#
        {   0.0,  41.20,   82.41,  164.81,  329.63,  659.26,  1318.51,  2637.02,     0.00}, // E
        {   0.0,  43.65,   87.31,  174.61,  349.23,  698.46,  1396.91,  2793.83,     0.00}, // F
        {   0.0,  46.25,   92.50,  185.00,  369.99,  739.99,  1479.98,  2959.96,     0.00}, // F#
        {   0.0,  49.00,   98.00,  196.00,  392.00,  793.99,  1567.98,  3135.96,     0.00}, // G
        {   0.0,  51.91,  103.83,  207.65,  415.30,  830.61,  1661.22,  3322.44,     0.00}, // G#
        { 27.50,  55.00,  110.00,  220.00,  440.00,  880.00,  1760.00,  3520.00,     0.00}, // A
        { 29.14,  58.27,  116.54,  233.08,  466.16,  932.33,  1864.66,  3729.31,     0.00}, // A#
        { 30.14,  61.74,  123.47,  246.94,  493.88,  987.77,  1975.53,  3951.07,     0.00}, // B
    };
    
//    private double[][] tablaFrecuency = {
//    //   Do,     Do#,      Re,     Re#,     Mi,        Fa,     Fa#,     Sol,    Sol#,      La,     La#,       Si
//    {   0.0,   32.70,   34.65,   65.41,   69.30,   130.81,  138.59,  261.63,  277.18,  523.25,  554.37,  1046.50 },  // Octava 1
//    {   0.0,   34.65,   36.71,   69.30,   73.42,   138.59,  146.83,  277.18,  293.66,  554.37,  587.33,  1108.73 },  // Octava 2
//    {   0.0,   36.71,   38.89,   73.42,   77.78,   146.83,  155.56,  293.66,  311.13,  587.33,  622.25,  1174.66 },  // Octava 3
//    {   0.0,   38.89,   41.20,   77.78,   82.41,   155.56,  164.81,  311.13,  329.63,  622.25,  659.26,  1244.51 },  // Octava 4
//    {   0.0,   41.20,   43.65,   82.41,   87.31,   164.81,  174.61,  329.63,  349.23,  659.26,  698.46,  1318.51 },  // Octava 5
//    {   0.0,   43.65,   46.25,   87.31,   92.50,   174.61,  185.00,  349.23,  369.99,  698.46,  739.99,  1396.91 },  // Octava 6
//    {   0.0,   46.25,   49.00,   92.50,   98.00,   185.00,  196.00,  369.99,  392.00,  739.99,  793.99,  1479.98 },  // Octava 7
//    { 27.50,   29.14,   30.87,   58.27,   61.74,   116.54,  123.47,  233.08,  246.94,  466.16,  493.88,  0.0 },     // Octava 8
//    };

    public FrecuencysNotes() {
        
    }

    public double[][] getTablaFrecuency() {
        return tablaFrecuency;
    }

    public void setTablaFrecuency(double[][] tablaFrecuency) {
        this.tablaFrecuency = tablaFrecuency;
    }
    
    public double getFrecency(MusicalNotes note, int octave){
        return tablaFrecuency[note.ordinal()][octave];
    }
}
