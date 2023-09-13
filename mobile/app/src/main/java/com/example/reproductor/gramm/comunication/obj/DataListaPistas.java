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
public class DataListaPistas implements Serializable {
    private String nameList;
    private boolean random;
    private List<TrackOnList> listPist;

    public DataListaPistas(String nameList, boolean random, List<TrackOnList> listPist) {
        this.nameList = nameList;
        this.random = random;
        this.listPist = listPist;
    }

    
    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public List<TrackOnList> getListPist() {
        return listPist;
    }

    public void setListPist(List<TrackOnList> listPist) {
        this.listPist = listPist;
    }
    
    public String toString(){
        return "Lista: "+nameList+",\tAleatoria: "+(random? "SI": "NO");
    }
}
