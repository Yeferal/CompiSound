/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.conditionals;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConditionalSwitch extends Node{
    private Node value;
    private List<Node> listCases;
    private Node defaultCase;

    public ConditionalSwitch(Node value, List<Node> listCases, Node defaultCase, PositionToken positionToken) {
        super(positionToken, null);
        this.value = value;
        this.listCases = listCases;
        this.defaultCase = defaultCase;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
    }

    public List<Node> getListCases() {
        return listCases;
    }

    public void setListCases(List<Node> listCases) {
        this.listCases = listCases;
    }

    public Node getDefaultCase() {
        return defaultCase;
    }

    public void setDefaultCase(Node defaultCase) {
        this.defaultCase = defaultCase;
    }
    
    @Override
    public Object execute(Environment env) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
