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
import com.yeferal.desktopreproductor.ast.main.tree.RunOperator;

/**
 *
 * @author Usuario
 */
public class OperationRational extends Node{
    private RationalTypes rationalTypes;
    private Node nodeLeft, nodeRight;
    private Object value;
    private ConverterDataType converterDataType;
    private RunOperator ro = new RunOperator();

    public OperationRational(RationalTypes rationalTypes, Node nodeLeft, Node nodeRight, PositionToken positionToken, DataType type) {
        super(positionToken, null);
        this.rationalTypes = rationalTypes;
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }

    public RationalTypes getRationalTypes() {
        return rationalTypes;
    }

    public void setRationalTypes(RationalTypes rationalTypes) {
        this.rationalTypes = rationalTypes;
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
        switch(rationalTypes){
            case EQUAL:
                return "==";
            case NOT_EQUAL:
                return "!=";
            case GRATE_THAN:
                return ">";
            case GRATE_THAN_EQUAL:
                return ">=";
            case LESS_THAN:
                return "<";
            case LESS_THAN_EQUAL:
                return "<=";
            case IS_NULL:
                return "!!";
            default:
                return "";
        }
    }
    
    @Override
    public Object execute(Environment env) {
        //Hace las verificaciones, tipo de dato y tipo de asignacion
        setType(DataType.BOOLEAN);
        
        if (nodeRight == null && rationalTypes == RationalTypes.IS_NULL) {
            Object val1 = nodeLeft.execute(env);
            
            Object valueLeft;
            
            if (nodeLeft instanceof Identifier) {
                Symbol sTemp = (Symbol) val1;
                valueLeft = sTemp.getValue();
            }else {
                valueLeft = val1;
            }
            if ((nodeLeft instanceof Identifier)) {
                value = ro.runOpRational(valueLeft, null, nodeLeft.getType(), nodeRight.getType(), rationalTypes);
            }else {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion racionales de este tipo con valor que no sean variblea."));
                return false;
            }
        }else {
            Object val1 = nodeLeft.execute(env);
            Object val2 = nodeRight.execute(env);
            
            Object valueLeft;
            Object valueRight;
            //Obtenemos el dato o el valor que trae el nodo hijo
            if (nodeLeft instanceof Identifier) {
                if (val1==null) {
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion racionales de este tipo con valores nulos."));
                    return false;
                }
                Symbol sTemp = (Symbol) val1;
                valueLeft = sTemp.getValue();
            }else {
                valueLeft = val1;
            }

            if (nodeRight instanceof Identifier) {
                Symbol sTemp = (Symbol) val2;
                valueRight = sTemp.getValue();
            }else {
                valueRight = val2;
            }
            //setea los datos pertinentes
            setType(DataType.BOOLEAN);
            if (valueLeft == null || valueRight == null) {
                if (rationalTypes == RationalTypes.IS_NULL) {
                    if ((nodeLeft instanceof Identifier)) {
                        value = ro.runOpRational(valueLeft, null, nodeLeft.getType(), nodeRight.getType(), rationalTypes);
                    }else {
                        env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion racionales de este tipo con valor que no sean variblea."));
                        return false;
                    }
                }else {
                    if (rationalTypes == RationalTypes.EQUAL) {
                        return valueLeft == valueRight;
                    }else if (rationalTypes == RationalTypes.NOT_EQUAL) {
                        return valueLeft != valueRight;
                    }else {
                        env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion racionales con valores nulos."));
                        return false;
                    }
                }
            }else {
                value = ro.runOpRational(valueLeft, valueRight, nodeLeft.getType(), nodeRight.getType(), rationalTypes);
            }
        }
        
//        System.out.println("Valor Obtendio: "+value);
        return value;
    }
    
}
