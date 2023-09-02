/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.conditionals;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConditionalSi extends Node{
    private Node condition;
    private List<Node> instructions;
    private List<Node> listConditionalSinoSi;
    private Node conditionalSinoSi;
    private Node conditionalSino;

    public ConditionalSi(Node condition, List<Node> instructions, Node conditionalSinoSi, Node conditionalSino, PositionToken positionToken) {
        super(positionToken, null);
        this.condition = condition;
        this.instructions = instructions;
        this.conditionalSinoSi = conditionalSinoSi;
        this.conditionalSino = conditionalSino;
        listConditionalSinoSi = new ArrayList<>();
    }

    public ConditionalSi(Node condition, List<Node> instructions, PositionToken positionToken) {
        super(positionToken, null);
        this.condition = condition;
        this.instructions = instructions;
        listConditionalSinoSi = new ArrayList<>();
    }

    public Node getCondition() {
        return condition;
    }

    public void setCondition(Node condition) {
        this.condition = condition;
    }

    public List<Node> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Node> instructions) {
        this.instructions = instructions;
    }

    public Node getConditionalSinoSi() {
        return conditionalSinoSi;
    }

    public void setConditionalSinoSi(Node conditionalSinoSi) {
        this.conditionalSinoSi = conditionalSinoSi;
    }

    public Node getConditionalSino() {
        return conditionalSino;
    }

    public void setConditionalSino(Node conditionalSino) {
        this.conditionalSino = conditionalSino;
    }
    
    public List<Node> getListConditionalSinoSi() {
        return listConditionalSinoSi;
    }

    public void setListConditionalSinoSi(List<Node> listConditionalSinoSi) {
        this.listConditionalSinoSi = listConditionalSinoSi;
    }
    public void addConditionalSinoSi(Node conditional){
        listConditionalSinoSi.add(conditional);
    };
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
