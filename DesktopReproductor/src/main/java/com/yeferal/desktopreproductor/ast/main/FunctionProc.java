/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class FunctionProc extends Node{
    private boolean keep, function; // si no es un metodo
//    private DataType type; //Puede o no ser una funcion
    private String id;
    private List<Node> params;
    private List<Node> instructions;

    public FunctionProc(boolean keep, boolean function, String id, List<Node> params, List<Node> instructions, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.keep = keep;
        this.function = function;
        this.id = id;
        this.params = params;
        this.instructions = instructions;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public boolean isFunction() {
        return function;
    }

    public void setFunction(boolean function) {
        this.function = function;
    }

//    public DataType getType() {
//        return type;
//    }
//
//    public void setType(DataType type) {
//        this.type = type;
//    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getParams() {
        return params;
    }

    public void setParams(List<Node> params) {
        this.params = params;
    }

    public List<Node> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Node> instructions) {
        this.instructions = instructions;
    }
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
