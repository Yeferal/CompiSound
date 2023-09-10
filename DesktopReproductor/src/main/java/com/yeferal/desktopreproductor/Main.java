/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.Primitive;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.ConverterDataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import com.yeferal.desktopreproductor.gui.MainWindow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
        
        
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();

            List<PlaySound> sounds = Arrays.asList(
//                new PlaySound(synth, 62, 100, 1000, 0),
//                new PlaySound(synth, 69, 100, 1000, 0),
//                new PlaySound(synth, 62, 100, 1000, 1), // Diferente canal
//                new PlaySound(synth, 60, 100, 1000, 0),
//                new PlaySound(synth, 60, 100, 1000, 1), // Diferente canal
//                new PlaySound(synth, 69, 100, 1000, 2), // Otro canal diferente
//                new PlaySound(synth, 62, 100, 1000, 0)
                // ... Agrega todos los sonidos aquí
            );

            Map<Integer, Thread> channelThreads = new HashMap<>();

            for (PlaySound sound : sounds) {
                int channel = sound.getChannel();

                if (channelThreads.containsKey(channel)) {
                    // Si ya hay un hilo ejecutándose en el mismo canal, espera a que termine
                    Thread thread = channelThreads.get(channel);
                    thread.join();
                }

                Thread thread = new Thread(sound);
                thread.start();
                channelThreads.put(channel, thread);
            }

            // Espera a que todos los hilos terminen
            for (Thread thread : channelThreads.values()) {
                thread.join();
            }

            synth.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        MainWindow mainWindow = new MainWindow();
//        mainWindow.setVisible(true);
        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run(){
//                MainWindow mainWindow = new MainWindow();
//                mainWindow.setVisible(true);
//            }
//        });


    }
    static class PlaySound implements Runnable {
        private Synthesizer synth;
        private int note;
        private int intensity;
        private int duration;
        private int channel;

        public PlaySound(Synthesizer synth, int note, int intensity, int duration, int channel) {
            this.synth = synth;
            this.note = note;
            this.intensity = intensity;
            this.duration = duration;
            this.channel = channel;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }

        
        @Override
        public void run() {
            try {
                MidiChannel[] channels = synth.getChannels();

                channels[channel].noteOn(note, intensity);
                Thread.sleep(duration);
                channels[channel].noteOff(note);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
