/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public abstract class Node implements Serializable {
    private PositionToken positionToken;
    private DataType type;
    private boolean belongFunc = false;
    private boolean flagRetorn = false;

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
    
    public boolean isBelongFunc() {
        return belongFunc;
    }

    public void setBelongFunc(boolean belongFunc) {
        this.belongFunc = belongFunc;
    }

    public boolean isFlagRetorn() {
        return flagRetorn;
    }

    public void setFlagRetorn(boolean flagRetorn) {
        this.flagRetorn = flagRetorn;
    }
    
    abstract public Object execute(Environment env);
    

}
