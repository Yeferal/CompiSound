/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CallArreglo extends Node{
    private String id;
    private List<Node> dimensions;

    public CallArreglo(String id, List<Node> dimensions, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.id = id;
        this.dimensions = dimensions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
