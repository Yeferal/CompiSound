/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;

/**
 *
 * @author Usuario
 */
public class Identifier extends Node{
    private String id;

    public Identifier(String id, PositionToken positionToken, DataType type) {
        super(positionToken, null);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public Object execute(Environment env) {
        Symbol symbol = env.getTableSymbol().searchSymbolIdentifier(id, env.currentAmbit);
        if (symbol == null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "No sea a declarado un variable con el nombre "+id+"."));
            return null;
        }
        setType(symbol.getType());
        return symbol;
    }
    
    
}
