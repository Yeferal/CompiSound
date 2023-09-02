/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.utils.filesadm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
}
