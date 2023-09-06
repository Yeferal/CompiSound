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
public abstract class Node {
    private PositionToken positionToken;
    private DataType type;

    public Node(PositionToken positionToken, DataType type) {
        this.positionToken = positionToken;
        this.type = type;
    }

    public PositionToken getPositionToken() {
        return positionToken;
    }

    public void setPositionToken(PositionToken positionToken) {
        this.positionToken = positionToken;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }
    
    abstract public Object execute(Environment env);
    

}
