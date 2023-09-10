/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import java.io.Serializable;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Usuario
 */
public class ExeEsperar implements Runnable, Serializable {
    private Synthesizer synth;
    private int value;
    private int channel;

    public ExeEsperar(int value, int channel, Synthesizer synth) {
        this.value = value;
        this.channel = channel;
        this.synth = synth;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getChannel() {
        return channel;
    }

    public void setChanel(int chanel) {
        this.channel = chanel;
    }
    
    @Override
    public void run() {
        try {
            MidiChannel[] channels = synth.getChannels();
            System.out.println("Ejecutando > ESPERAR("+value+"ms,"+channel+","+");");
            // Silenciar el canal especificado
            channels[channel].allNotesOff();

            // Pausar durante la duración especificada
            Thread.sleep(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Object execute() {
        try {
            MidiChannel[] channels = synth.getChannels();
            System.out.println("Ejecutando > ESPERAR("+value+"ms,"+channel+","+");");
            // Silenciar el canal especificado
            channels[channel].allNotesOff();

            // Pausar durante la duración especificada
            Thread.sleep(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return value;
    }
}
