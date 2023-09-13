/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.gramm.comunication.obj;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class DataChannel {
    private int channel;
    private List<DataNote> listNotes;

    public DataChannel(int channel, List<DataNote> listNotes) {
        this.channel = channel;
        this.listNotes = listNotes;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public List<DataNote> getListNotes() {
        return listNotes;
    }

    public void setListNotes(List<DataNote> listNotes) {
        this.listNotes = listNotes;
    }
    
    
}
