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
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class DeclarationVar extends Node{
    private boolean keep;
    private DataType dataType;
    private String id;
    private Node asignation;
    private ConverterDataType cdt = new ConverterDataType();
    private boolean paramFlag = false;

    public DeclarationVar(boolean keep, DataType dataType, String id, Node asignation, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.keep = keep;
        this.dataType = dataType;
        this.id = id;
        this.asignation = asignation;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
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

    public boolean isParamFlag() {
        return paramFlag;
    }

    public void setParamFlag(boolean paramFlag) {
        this.paramFlag = paramFlag;
    }
    
    
    public Deque<Integer> setAmbitPileS(Deque<Integer> ambitsPile){
        Deque<Integer> tempPile = new LinkedList<>(ambitsPile);
//        System.out.println("Pilaaaaaaaaaaaaaaaaaaaaaaa -> "+tempPile);
//        System.out.println("Pilaaaaaaaaaaaaaaaaaaaaaaa -> "+tempPile.toString());
        return tempPile;
    }
    
    @Override
    public Object execute(Environment env) {
        Symbol symbol = env.getTableSymbol().searchSymbol(id, env.currentAmbit);
        
        if (symbol != null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "Ya existe una variable con el nombre "+id+"."));
            return null;
        }
        
        Object valAsig = null;
        if (asignation!=null) {
            valAsig = asignation.execute(env);
        }else {
            if (!paramFlag) {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "No se puede asignar valores nulos a una variable "+id+"."));
                return null;
            }else {
                Symbol sTemp = new Symbol(id, getDataType(), env.currentAmbit, DataType.IDENTIFICADOR, null);
                sTemp.setGlobal(keep);
                sTemp.setAmbitsPile(setAmbitPileS(env.getTableSymbol().getPileAmbit()));
                env.getTableSymbol().addSymbol(sTemp);
                return null;
            }
            
        }
        
        Object valAsigT = valAsig;
        if (asignation instanceof Identifier) {
            Symbol s = (Symbol) valAsig;
            valAsigT = s.getValue();
        }
        
        Object valConverter = cdt.converterFromAsig(getType(), asignation.getType(), valAsigT);
        if (valConverter!=null) {
            System.out.println("Amvito de la declaracion: "+id+ " => "+env.currentAmbit);
            Symbol sTemp = new Symbol(id, getDataType(), env.currentAmbit, DataType.IDENTIFICADOR, valAsigT);
            sTemp.setGlobal(keep);
            sTemp.setAmbitsPile(setAmbitPileS(env.getTableSymbol().getPileAmbit()));
            env.getTableSymbol().addSymbol(sTemp);
        }else {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "El tipo de dato no conicide con el tipo de la variable."));
            return null;
        }
        return null;
    }
}
