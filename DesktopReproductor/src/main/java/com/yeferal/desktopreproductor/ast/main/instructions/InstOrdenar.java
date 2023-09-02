/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.instructions.notas.OrderType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;

/**
 *
 * @author Usuario
 */
public class InstOrdenar extends Node{
    private Node arreglo;
    private OrderType orderType;

    public InstOrdenar(Node arreglo, OrderType orderType, PositionToken positionToken) {
        super(positionToken, DataType.ENTERO);
        this.arreglo = arreglo;
        this.orderType = orderType;
    }

    public Node getArreglo() {
        return arreglo;
    }

    public void setArreglo(Node arreglo) {
        this.arreglo = arreglo;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
