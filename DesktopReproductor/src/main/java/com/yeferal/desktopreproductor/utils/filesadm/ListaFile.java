/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.utils.filesadm;

import com.yeferal.desktopreproductor.gramm.list.TrackList;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ListaFile implements Serializable{
    private List<TrackList> list;
    private String txt;

    public ListaFile(List<TrackList> list) {
        this.list = list;
    }
    
    public List<TrackList> getList() {
        return list;
    }

    public void setList(List<TrackList> list) {
        this.list = list;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    
    
    
    public boolean addList(TrackList tl){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(tl.getName())) {
                list.set(i, tl);
                return true;
            }
        }
        list.add(tl);
        return false;
    }
}
