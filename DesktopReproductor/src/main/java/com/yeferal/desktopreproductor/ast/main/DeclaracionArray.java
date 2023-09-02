/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DeclaracionArray extends Node{
    private boolean keep;
    private DataType dataType;
    private String id;
    private List<Integer> dimensions;
    private int sizeArray;
    private Node asignation;

    public DeclaracionArray(boolean keep, DataType dataType, String id, List<Integer> dimensions, Node asignation, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.keep = keep;
        this.dataType = dataType;
        this.id = id;
        this.dimensions = dimensions;
        this.asignation = asignation;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Integer> dimensions) {
        this.dimensions = dimensions;
    }

    public int getSizeArray() {
        return sizeArray;
    }

    public void setSizeArray(int sizeArray) {
        this.sizeArray = sizeArray;
    }

    public Node getAsignation() {
        return asignation;
    }

    public void setAsignation(Node asignation) {
        this.asignation = asignation;
    }
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
