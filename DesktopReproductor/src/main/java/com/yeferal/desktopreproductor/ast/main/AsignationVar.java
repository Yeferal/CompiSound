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
public class AsignationVar extends Node{
    private String id;
    private Node asignation;

    public AsignationVar(String id, Node asignation, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.id = id;
        this.asignation = asignation;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
