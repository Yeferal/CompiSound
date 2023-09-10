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
import com.yeferal.desktopreproductor.ast.main.tree.ConverterDataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;

/**
 *
 * @author Usuario
 */
public class AsignationVar extends Node{
    private String id;
    private Node asignation;
    private ConverterDataType cdt = new ConverterDataType();

    public AsignationVar(String id, Node asignation, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.id = id;
        this.asignation = asignation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getAsignation() {
        return asignation;
    }

    public void setAsignation(Node asignation) {
        this.asignation = asignation;
    }

    @Override
    public Object execute(Environment env) {
        Symbol symbol = env.getTableSymbol().searchSymbol(id, env.currentAmbit);
        if (symbol == null || symbol.getRol() != DataType.IDENTIFICADOR) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "No sea a declarado un variable con el nombre "+id+"."));
            return null;
        }
        
        setType(symbol.getType());
        
        if (asignation == null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "No se aceptan valores nulos en la asignacion."));
            return null;
        }
        
        Object valAsignation = asignation.execute(env);
        Object valAsigT = valAsignation;
        if (asignation instanceof Identifier) {
            Symbol s = (Symbol) valAsignation;
            valAsigT = s.getValue();
        }
        
        if (valAsigT == null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "No se aceptan valores nulos en la asignacion."));
            return null;
        }
        
        Object valConverter = cdt.converterFromAsig(getType(), asignation.getType(), valAsigT);
        if (valConverter!=null) {
            env.getTableSymbol().setValueSymbol(id, env.currentAmbit, valAsigT);
        }else {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "El tipo de dato no conicide con el tipo de la variable."));
            return null;
        }

        return null;
    }
    
}
