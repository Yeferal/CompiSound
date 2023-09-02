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
public class TrackNode extends Node{
    private String id;
    private List<String> listExtends;
    private List<Node> listInstruction;

    public TrackNode(String id, List<String> listExtends, List<Node> listInstruction, PositionToken positionToken, DataType type) {
        super(positionToken, null);
        this.id = id;
        this.listExtends = listExtends;
        this.listInstruction = listInstruction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getListExtends() {
        return listExtends;
    }

    public void setListExtends(List<String> listExtends) {
        this.listExtends = listExtends;
    }

    public List<Node> getListInstruction() {
        return listInstruction;
    }

    public void setListInstruction(List<Node> listInstruction) {
        this.listInstruction = listInstruction;
    }
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
