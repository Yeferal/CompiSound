/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.utils.filesadm;

import java.io.*;
import java.net.*;

/**
 *
 * @author Usuario
 */
public class MainSocket {
    
    
    public void init(){
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor Java esperando conexiones en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                
                // Manejar la conexión en una clase separada
                Comunicator handler = new Comunicator(clientSocket);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
