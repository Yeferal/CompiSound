/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;

/**
 *
 * @author Usuario
 */
public class OperationLogical extends Node{
    private LogicalTypes logicalTypes;
    private Node nodeLeft, nodeRight;

    public OperationLogical(LogicalTypes logicalTypes, Node nodeLeft, Node nodeRight, PositionToken positionToken, DataType type) {
        super(positionToken, null);
        this.logicalTypes = logicalTypes;
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }

    public LogicalTypes getLogicalTypes() {
        return logicalTypes;
    }

    public void setLogicalTypes(LogicalTypes logicalTypes) {
        this.logicalTypes = logicalTypes;
    }

    public Node getNodeLeft() {
        return nodeLeft;
    }

    public void setNodeLeft(Node nodeLeft) {
        this.nodeLeft = nodeLeft;
    }

    public Node getNodeRight() {
        return nodeRight;
    }

    public void setNodeRight(Node nodeRight) {
        this.nodeRight = nodeRight;
    }
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
