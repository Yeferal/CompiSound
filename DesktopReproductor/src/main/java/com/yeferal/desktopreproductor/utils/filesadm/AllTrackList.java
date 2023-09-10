/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.utils.filesadm;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class AllTrackList implements Serializable{
    private List<PistaFile> list;

    public AllTrackList(List<PistaFile> list) {
        this.list = list;
    }

    public List<PistaFile> getList() {
        return list;
    }

    public void setList(List<PistaFile> list) {
        this.list = list;
    }
    
    public boolean addPista(PistaFile pf){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(pf.getName())) {
                list.set(i, pf);
                return true;
            }
        }
        list.add(pf);
        return false;
    }
    
}
