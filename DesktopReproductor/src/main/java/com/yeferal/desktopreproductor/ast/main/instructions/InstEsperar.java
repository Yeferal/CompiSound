/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;

/**
 *
 * @author Usuario
 */
public class InstEsperar extends Node{
    private Node value;
    private Node chanel;

    public InstEsperar(Node value, Node chanel, PositionToken positionToken) {
        super(positionToken, null);
        this.chanel = chanel;
        this.value = value;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
    }

    public Node getChanel() {
        return chanel;
    }

    public void setChanel(Node chanel) {
        this.chanel = chanel;
    }
     
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
}