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
public class OperationRational extends Node{
    private RationalTypes rationalTypes;
    private Node nodeLeft, nodeRight;

    public OperationRational(RationalTypes rationalTypes, Node nodeLeft, Node nodeRight, PositionToken positionToken, DataType type) {
        super(positionToken, null);
        this.rationalTypes = rationalTypes;
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }

    public RationalTypes getRationalTypes() {
        return rationalTypes;
    }

    public void setRationalTypes(RationalTypes rationalTypes) {
        this.rationalTypes = rationalTypes;
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
