/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.reproductor.gramm.comunication.obj;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class DataListaListas implements Serializable {
    private String name;
    private int noPistas;

    public DataListaListas(String name, int noPistas) {
        this.name = name;
        this.noPistas = noPistas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoPistas() {
        return noPistas;
    }

    public void setNoPistas(int noPistas) {
        this.noPistas = noPistas;
    }
    
    public String toString(){
        return name+"\t\t #Pistas: "+noPistas;
    }
}
