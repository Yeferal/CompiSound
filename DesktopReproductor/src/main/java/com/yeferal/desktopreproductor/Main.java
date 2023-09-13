/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor;

import com.yeferal.desktopreproductor.gui.MainWindow;
import com.yeferal.desktopreproductor.utils.filesadm.MainSocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("IP de localhost: " + localhost.getHostAddress());
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });

        MainSocket mainSocket = new MainSocket();
        
        mainSocket.init();

//        Servidor serv = new Servidor(); //Se crea el servidor
//
//        System.out.println("Iniciando servidor\n");
//        serv.startServer();

    }
    
}
