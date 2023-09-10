/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.DeclarationVar;
import com.yeferal.desktopreproductor.ast.main.FunctionProc;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CallFunction extends Node{
    private String id;
    private List<Node> params = new ArrayList<>();

    public CallFunction(String id, List<Node> params, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.id = id;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getParams() {
        return params;
    }

    public void setParams(List<Node> params) {
        this.params = params;
    }
    
    @Override
    public Object execute(Environment env) {
        //Buscamos la funcion si existe
        Symbol symbol = env.getTableSymbol().searchSymbolFunction(id, env.currentAmbit);
        if (symbol == null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "No sea a declarado una funcion con el nombre "+id+"."));
            return null;
        }
        Object func = symbol.getValue();
        if (!(func instanceof FunctionProc)) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "No sea a declarado una funcion con el nombre "+id+"."));
            return null;
        }
        FunctionProc functionProc = (FunctionProc) func;
        //Declaracion de los parametros
        System.out.println("Parametros: "+ functionProc.getParams() + " == "+params);
        if ((functionProc.getParams()==null && (params!=null && params.size()>0)) || 
            ((functionProc.getParams()!=null && functionProc.getParams().size()>0) && params==null) || 
            ((functionProc.getParams()!=null && params!=null) && 
                functionProc.getParams().size() != params.size())) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "El numero de parametros no coincide con los parametros de la funcion "+id+"."));
            return null;
        }else {
            env.addNewAmbit();
            System.out.println("Ambitos de la funcion: "+env.getTableSymbol().getPileAmbit().toString());
            if ((functionProc.getParams()!=null && params!=null)) {
                //Cuando si hay parametros y se esperan parametros
                
                for (int i = 0; i < functionProc.getParams().size(); i++) {
                    //asignacion, esta es una ejecucion
                    DeclarationVar dv = (DeclarationVar) functionProc.getParams().get(i);
                    dv.setAsignation(params.get(i));
                    dv.execute(env);
                }
            }
        }
        //recorrec funcion, ejecutar las sentencias de la misma
        Object resRetorn = functionProc.execute(env);
        //devolver el valor con el tipo de variable retornada verificada.
        if (resRetorn!=null) {
            return resRetorn;
        }
        
        return null;
    }
}
