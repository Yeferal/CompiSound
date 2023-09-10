/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Identifier;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;

/**
 *
 * @author Usuario
 */
public class InstMensaje extends Node{
    private Node value;

    public InstMensaje(Node value, PositionToken positionToken) {
        super(positionToken, null);
        this.value = value;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
    }
    
    @Override
    public Object execute(Environment env) {
        Object valAsignation = value.execute(env);
        Object valAsigT = valAsignation;
        if (value instanceof Identifier) {
            Symbol s = (Symbol) valAsignation;
            valAsigT = s.getValue();
        }
        
        System.out.println(valAsigT+"");
        //ENVIAR EL PRINT AL LOG
        
        
        return null;
    }
}
