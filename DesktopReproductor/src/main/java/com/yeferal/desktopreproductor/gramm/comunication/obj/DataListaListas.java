/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.gramm.comunication.obj;

/**
 *
 * @author Usuario
 */
public class DataListaListas {
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
    
    
}
