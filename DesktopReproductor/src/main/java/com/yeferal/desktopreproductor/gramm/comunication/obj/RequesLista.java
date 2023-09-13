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
public class RequesLista {
    private List<DataListaListas> dataListaListas;
    private List<DataListaPistas> dataListaPistas;

    public RequesLista(List<DataListaListas> dataListaListas, List<DataListaPistas> dataListaPistas) {
        this.dataListaListas = dataListaListas;
        this.dataListaPistas = dataListaPistas;
    }

    public List<DataListaListas> getDataListaListas() {
        return dataListaListas;
    }

    public void setDataListaListas(List<DataListaListas> dataListaListas) {
        this.dataListaListas = dataListaListas;
    }

    public List<DataListaPistas> getDataListaPistas() {
        return dataListaPistas;
    }

    public void setDataListaPistas(List<DataListaPistas> dataListaPistas) {
        this.dataListaPistas = dataListaPistas;
    }
    
    
}
