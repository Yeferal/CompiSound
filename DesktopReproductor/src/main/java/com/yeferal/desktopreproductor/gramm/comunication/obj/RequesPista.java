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
public class RequesPista {
    private List<TrackOnList> listPistas;
    private List<DataStructPista> listDataStructPista;

    public RequesPista(List<TrackOnList> listPistas, List<DataStructPista> listDataStructPista) {
        this.listPistas = listPistas;
        this.listDataStructPista = listDataStructPista;
    }

    public List<TrackOnList> getListPistas() {
        return listPistas;
    }

    public void setListPistas(List<TrackOnList> listPistas) {
        this.listPistas = listPistas;
    }

    public List<DataStructPista> getListDataStructPista() {
        return listDataStructPista;
    }

    public void setListDataStructPista(List<DataStructPista> listDataStructPista) {
        this.listDataStructPista = listDataStructPista;
    }
    
    
}
