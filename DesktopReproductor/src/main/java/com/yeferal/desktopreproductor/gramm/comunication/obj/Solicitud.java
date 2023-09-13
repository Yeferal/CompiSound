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
public class Solicitud {
    private RequestType requestType;
    private String name;
    private List<DataPista> listData;

    public Solicitud(RequestType requestType) {
        this.requestType = requestType;
    }

    public Solicitud(RequestType requestType, String name) {
        this.requestType = requestType;
        this.name = name;
    }

//    public Solicitud(RequestType requestType, List<DataPista> listData) {
//        this.requestType = requestType;
//        this.listData = listData;
//    }

    public Solicitud(RequestType requestType, String name, List<DataPista> listData) {
        this.requestType = requestType;
        this.name = name;
        this.listData = listData;
    }
    
    

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataPista> getListData() {
        return listData;
    }

    public void setListData(List<DataPista> listData) {
        this.listData = listData;
    }
    
    
    
}
