/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;

/**
 *
 * @author Usuario
 */
public class DeclarationVar extends Node{
    private boolean keep;
    private DataType dataType;
    private String id;
    private Node asignation;

    public DeclarationVar(boolean keep, DataType dataType, String id, Node asignation, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.keep = keep;
        this.dataType = dataType;
        this.id = id;
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

    public Node getAsignation() {
        return asignation;
    }

    public void setAsignation(Node asignation) {
        this.asignation = asignation;
    }
    
    @Override
    public Object execute(Environment env) {
        System.out.println("Entro: "+getType()+", asignacion="+asignation);
        if (asignation!=null) {
            asignation.execute(env);
        }else {
            
        }
        
        return null;
    }
}
