/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.reproductor.gramm.comunication.obj;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DataStructPista implements Serializable {
    private String name;
    private List<DataChannel> listChannels;

    public DataStructPista(String name, List<DataChannel> listChannels) {
        this.name = name;
        this.listChannels = listChannels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataChannel> getListChannels() {
        return listChannels;
    }

    public void setListChannels(List<DataChannel> listChannels) {
        this.listChannels = listChannels;
    }
    
}
