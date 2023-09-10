/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.utils.filesadm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class FileAccess {
    
    
    
    public String readFile(String path){
        StringBuilder txt = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                txt.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return txt.toString(); 
    }
    
    public boolean writeFile(String path, String txt){
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(txt);
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void guardarClaseEnArchivoPista(PistaFile pistaFile, String nombreArchivo) {
        try {
            FileOutputStream archivoSalida = new FileOutputStream(nombreArchivo);
            ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida);

            objetoSalida.writeObject(pistaFile);

            objetoSalida.close();
            archivoSalida.close();
            System.out.println("Objeto guardado correctamente en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void guardarClaseEnArchivoLista(ListaFile listaFile, String nombreArchivo) {
        try {
            FileOutputStream archivoSalida = new FileOutputStream(nombreArchivo);
            ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida);

            objetoSalida.writeObject(listaFile);

            objetoSalida.close();
            archivoSalida.close();
            System.out.println("Objeto guardado correctamente en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void guardarClaseEnArchivoAllPista(AllTrackList pistaFile, String nombreArchivo) {
        try {
            FileOutputStream archivoSalida = new FileOutputStream(nombreArchivo, false); // El segundo argumento, 'false', indica que no se debe añadir (modo sobrescribir)

            ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida);
            objetoSalida.writeObject(pistaFile);

            objetoSalida.close();
            archivoSalida.close();
            System.out.println("Objeto guardado correctamente en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public void guardarClaseEnArchivoLista(ListaFile listaFile, String nombreArchivo) {
//        try {
//            FileOutputStream archivoSalida = new FileOutputStream(nombreArchivo);
//            ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida);
//
//            objetoSalida.writeObject(listaFile);
//
//            objetoSalida.close();
//            archivoSalida.close();
//            System.out.println("Objeto guardado correctamente en " + nombreArchivo);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    public AllTrackList cargarClaseDesdeArchivoAllPista(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            
            if (!archivo.exists()) {
                guardarClaseEnArchivoAllPista(new AllTrackList(new ArrayList<>()), nombreArchivo);
            }
            
            FileInputStream archivoEntrada = new FileInputStream(nombreArchivo);
            ObjectInputStream objetoEntrada = new ObjectInputStream(archivoEntrada);

            AllTrackList objeto = (AllTrackList) objetoEntrada.readObject();

            objetoEntrada.close();
            archivoEntrada.close();

            System.out.println("Objeto cargado correctamente desde " + nombreArchivo);
            return objeto;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public PistaFile cargarClaseDesdeArchivoPista(String nombreArchivo) {
        try {
            
            
            
            FileInputStream archivoEntrada = new FileInputStream(nombreArchivo);
            ObjectInputStream objetoEntrada = new ObjectInputStream(archivoEntrada);

            PistaFile objeto = (PistaFile) objetoEntrada.readObject();

            objetoEntrada.close();
            archivoEntrada.close();

            System.out.println("Objeto cargado correctamente desde " + nombreArchivo);
            return objeto;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ListaFile cargarClaseDesdeArchivoLista(String nombreArchivo) {
        try {
            
            File archivo = new File(nombreArchivo);
            
            if (!archivo.exists()) {
                guardarClaseEnArchivoLista(new ListaFile(new ArrayList<>()), nombreArchivo);
            }
            
            FileInputStream archivoEntrada = new FileInputStream(nombreArchivo);
            ObjectInputStream objetoEntrada = new ObjectInputStream(archivoEntrada);

            ListaFile objeto = (ListaFile) objetoEntrada.readObject();

            objetoEntrada.close();
            archivoEntrada.close();

            System.out.println("Objeto cargado correctamente desde " + nombreArchivo);
            return objeto;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<String> getListaFiles(String dir){
        List<String> nombresArchivos = new ArrayList<>();
        File carpeta = new File(dir);
        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    nombresArchivos.add(archivo.getName());
                }
            }
        }

        return nombresArchivos;
    }
    
    public static boolean deleteFile(String pathFile) {
        File archivo = new File(pathFile);

        // Verificar si el archivo existe
        if (archivo.exists()) {
            // Intentar eliminar el archivo
            if (archivo.delete()) {
                return true; // Éxito al eliminar el archivo
            } else {
                return false; // No se pudo eliminar el archivo
            }
        } else {
            return false; // El archivo no existe
        }
    }
}
