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
public class NodeFinally extends Node{
    private NodeEndType nodeEndType;
    private Node valueReturn;

    public NodeFinally(NodeEndType nodeEndType, Node valueReturn, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.nodeEndType = nodeEndType;
        this.valueReturn = valueReturn;
    }

    public NodeFinally(NodeEndType nodeEndType, PositionToken positionToken) {
        super(positionToken, null);
        this.nodeEndType = nodeEndType;
    }

    public NodeEndType getNodeEndType() {
        return nodeEndType;
    }

    public void setNodeEndType(NodeEndType nodeEndType) {
        this.nodeEndType = nodeEndType;
    }

    public Node getValueReturn() {
        return valueReturn;
    }

    public void setValueReturn(Node valueReturn) {
        this.valueReturn = valueReturn;
    }
    
    @Override
    public Object execute(Environment env) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
