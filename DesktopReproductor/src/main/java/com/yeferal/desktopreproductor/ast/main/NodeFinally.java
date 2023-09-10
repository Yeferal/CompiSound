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
    
    public String getTypeString(){
        switch(nodeEndType){
            case SALIR:
                return "salir:";
            case CONTINUAR:
                return "Continuar:";
            case RETORNAR:
                return "Rertornar:";
            default:
                return "";
        }
    }
    
    @Override
    public Object execute(Environment env) {
        Object val = valueReturn.execute(env);
        Object valT = val;
        setType(valueReturn.getType());
        
        if (val == null) {
            env.getErrorsSemantic().add(new ErrorGramm(valueReturn.getPositionToken(),ErrorType.SEMANTIC, "", "El valor de retorno es nulo."));
            return null;
        }

        if (valueReturn instanceof Identifier) {
            Symbol s = (Symbol) val;
            valT = s.getValue();
            if (s.getRol() == DataType.ARREGLO) {
                env.getErrorsSemantic().add(new ErrorGramm(valueReturn.getPositionToken(),ErrorType.SEMANTIC, "", "El valor "+s.getName()+" no es un arreglo."));
                return null;
            }
        }
        System.out.println("return "+valT+";");
        return valT;
    }
}
