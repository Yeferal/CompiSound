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
public class ListDeclaration extends Node{
    private List<Node> list;
    private boolean keep, array;
    private DataType dataType;
    private Node asignation;
    private List<Node> dimensions;

    public ListDeclaration(boolean keep, DataType dataType, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.keep = keep;
        this.dataType = dataType;
        this.array = false;
        list = new ArrayList<>();
    }

    public ListDeclaration(List<Node> list, boolean keep, DataType dataType, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.list = list;
        this.keep = keep;
        this.dataType = dataType;
        this.array = false;
        list = new ArrayList<>();
    }

    public ListDeclaration(boolean keep, boolean array, DataType dataType, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.keep = keep;
        this.array = array;
        this.dataType = dataType;
        list = new ArrayList<>();
    }

    public List<Node> getList() {
        return list;
    }

    public void setList(List<Node> list) {
        this.list = list;
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

    public Node getAsignation() {
        return asignation;
    }

    public void setAsignation(Node asignation) {
        this.asignation = asignation;
    }
    
    public void addDeclaration(Node declaration){
        list.add(declaration);
    }

    public boolean isArray() {
        return array;
    }

    public void setArray(boolean array) {
        this.array = array;
    }

    public List<Node> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Node> dimensions) {
        this.dimensions = dimensions;
    }
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
