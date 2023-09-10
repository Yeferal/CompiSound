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
import com.yeferal.desktopreproductor.ast.main.tree.RunOperator;

/**
 *
 * @author Usuario
 */
public class OperationLogical extends Node{
    private LogicalTypes logicalTypes;
    private Node nodeLeft, nodeRight;
    private Object value;
    private RunOperator ro = new RunOperator();

    public OperationLogical(LogicalTypes logicalTypes, Node nodeLeft, Node nodeRight, PositionToken positionToken, DataType type) {
        super(positionToken, null);
        this.logicalTypes = logicalTypes;
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }

    public LogicalTypes getLogicalTypes() {
        return logicalTypes;
    }

    public void setLogicalTypes(LogicalTypes logicalTypes) {
        this.logicalTypes = logicalTypes;
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
    
    public void setValue(Object value) {
        this.value = value;
    }
    
    public String getSign(){
        switch(logicalTypes){
            case AND:
                return "&&";
            case NAND:
                return "!&&";
            case OR:
                return "||";
            case NOR:
                return "!||";
            case XOR:
                return "&|";
            case NOT:
                return "!";
            default:
                return "";
        }
    }
    
    @Override
    public Object execute(Environment env) {
        //Hace las verificaciones, tipo de dato y tipo de asignacion
        setType(DataType.BOOLEAN);
        if (nodeRight == null && logicalTypes == LogicalTypes.NOT) {
            Object val1 = nodeLeft.execute(env);
            if (val1 == null) {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion logicas con valores nulos."));
                return false;
            }
            
            Object valueLeft;
            
            if (nodeLeft instanceof Identifier) {
                Symbol sTemp = (Symbol) val1;
                valueLeft = sTemp.getValue();
            }else {
                valueLeft = val1;
            }
            
            if (valueLeft==null) {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion logicas con valores nulos."));
                return null;
            }
            
            value = ro.runOpLogical(valueLeft, null, DataType.BOOLEAN, DataType.BOOLEAN, logicalTypes);
            
        }else {
            Object val1 = nodeLeft.execute(env);
            Object val2 = nodeRight.execute(env);

            if (val1 == null || val2 == null) {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion logicas con valores nulos."));
                return null;
            }

            if (nodeLeft.getType()!= DataType.BOOLEAN || nodeLeft.getType()!= DataType.BOOLEAN) {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion logicas con valores no booleanos."));
                return null;
            }

            boolean valueLeft;
            boolean valueRight;
            //Obtenemos el dato o el valor que trae el nodo hijo
            if (nodeLeft instanceof Identifier) {
                Symbol sTemp = (Symbol) val1;
                if (sTemp.getValue()==null) {
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion logicas con valores nulos."));
                    return null;
                }
                valueLeft = (boolean) sTemp.getValue();
            }else {
                valueLeft = (boolean) val1;
            }

            if (nodeRight instanceof Identifier) {
                Symbol sTemp = (Symbol) val2;
                if (sTemp.getValue()==null) {
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion logicas con valores nulos."));
                    return null;
                }
                valueRight = (boolean) sTemp.getValue();
            }else {
                valueRight = (boolean) val2;
            }
            
            value = ro.runOpLogical(valueLeft, valueRight, DataType.BOOLEAN, DataType.BOOLEAN, logicalTypes);
        }
        setType(DataType.BOOLEAN);
//        System.out.println("Valor Obtendio: "+value);
        return value;
    }
}
