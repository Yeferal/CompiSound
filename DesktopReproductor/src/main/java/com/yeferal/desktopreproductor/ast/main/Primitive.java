/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.sun.jdi.Value;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;

/**
 *
 * @author Usuario
 */
public class Primitive extends Node{

    private Object value;
    
    public Primitive(PositionToken positionToken, DataType type, Object value) {
        super(positionToken, type);
        this.value = value;
    }
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
