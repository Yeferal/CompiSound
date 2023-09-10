/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.gramm.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TrackList implements Serializable {
    private String name;
    private boolean random, circular;
    private List<String> listTracks;
    private String txt;

    public TrackList(String name, boolean random, boolean circular, List<String> listTracks) {
        this.name = name;
        this.random = random;
        this.circular = circular;
        this.listTracks = listTracks;
    }

    public TrackList(String name) {
        this.name = name;
        this.random = false;
        this.circular = false;
        this.listTracks = new ArrayList<>();
    }

    public TrackList(String name, List<String> listTracks) {
        this.name = name;
        this.random = false;
        this.circular = false;
        this.listTracks = listTracks;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public boolean isCircular() {
        return circular;
    }

    public void setCircular(boolean circular) {
        this.circular = circular;
    }

    public List<String> getListTracks() {
        return listTracks;
    }

    public void setListTracks(List<String> listTracks) {
        this.listTracks = listTracks;
    }
    
    
}
