/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CallFunction extends Node{
    private String id;
    private List<Node> params = new ArrayList<>();

    public CallFunction(String id, List<Node> params, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.id = id;
        this.params = params;
    }

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
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
