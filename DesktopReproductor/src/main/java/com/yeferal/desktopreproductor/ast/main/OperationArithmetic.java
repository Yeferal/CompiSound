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
public class OperationArithmetic extends Node{
    private ArithType arithType;
    private Node nodeLeft, nodeRight;
    private Object value;
    private ConverterDataType converterDataType = new ConverterDataType();

    public OperationArithmetic(ArithType arithType, Node nodeLeft, Node nodeRight, PositionToken positionToken, DataType type) {
        super(positionToken, null);
        this.arithType = arithType;
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }

    public ArithType getArithType() {
        return arithType;
    }

    public void setArithType(ArithType arithType) {
        this.arithType = arithType;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    public String getSign(){
        switch(arithType){
            case ADD:
                return "+";
            case SUBTRAC:
                return "-";
            case MULTI:
                return "*";
            case DIV:
                return "/";
            case MOD:
                return "%";
            case POW:
                return "^";
            default:
                return "";
        }
    }
    
    @Override
    public Object execute(Environment env) {
        //Hace las verificaciones, tipo de dato y tipo de asignacion
        Object val1 = nodeLeft.execute(env);
        Object val2 = nodeRight.execute(env);
        
        if (val1 == null || val2 == null) {
            return null;
        }
        //Verifica que se pueda realizar la operaionc arithmetica
//        System.out.println(nodeLeft.getType() +" , "+ nodeRight.getType()+" , "+arithType);
//        System.out.println(converterDataType);
        if (!converterDataType.isConverterOpArith(nodeLeft.getType(), nodeRight.getType(), arithType)) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion aritmetica porque los datos de la operacion no son valido."));
            return null;
        }
        Object valueLeft;
        Object valueRight;
        //Obtenemos el dato o el valor que trae el nodo hijo
        if (nodeLeft instanceof Identifier) {
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
        
        if (valueLeft == null || valueRight == null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getSign(), "No se puede realizar la operacion aritmetica porque los valores son nulos."));
            return null;
        }
        
        setType(converterDataType.getTypeData(nodeLeft.getType(), nodeRight.getType(), getArithType()));
        value = converterDataType.operateArith(valueLeft, valueRight, nodeLeft.getType(), nodeRight.getType(), arithType);
//        System.out.println("Valor Obtendio: "+value);
        return value;
    }
    
}
