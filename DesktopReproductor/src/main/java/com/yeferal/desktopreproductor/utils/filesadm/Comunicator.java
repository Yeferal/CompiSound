/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.utils.filesadm;

import com.yeferal.desktopreproductor.utils.ExecutorRequest;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Comunicator extends Thread{
    private Socket clientSocket;
    int port = 12345;
    private ExecutorRequest executorRequest = new ExecutorRequest();
    
    public Comunicator(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    
    @Override
    public void run() {
        try {
            // Configurar canales de entrada y salida
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            Object request = in.readObject();
            
            // TODO: read message
            System.out.println("Message received");
            String response = executorRequest.executeReques((String) request);
            System.out.println("Respuesta:\n"+response);
            out.writeObject(response);

            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Cerro");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Comunicator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int convertirYRevolver(String texto) {
        // Eliminar caracteres no numéricos (excepto '-')
        texto = texto.replaceAll("[^\\d-]", "");

        try {
            int entero = Integer.parseInt(texto);
            // Revuelve el valor entero (cambia el orden de los dígitos)
            int valorRevuelto = Integer.parseInt(new StringBuilder().append(entero).reverse().toString());
            return valorRevuelto;
        } catch (NumberFormatException e) {
            // Si la conversión falla, devuelve 0
            return 0;
        }
    }
}
