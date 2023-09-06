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
public class ConditionalPara extends Node{
    private Node asignation;
    private Node conditionalPara;
    private Node sentenciaPara;
    private List<Node> instructions;

    public ConditionalPara(Node asignation, Node conditionalPara, Node sentenciaPara, List<Node> instructions, PositionToken positionToken) {
        super(positionToken, null);
        this.asignation = asignation;
        this.conditionalPara = conditionalPara;
        this.sentenciaPara = sentenciaPara;
        this.instructions = instructions;
    }
    
    public Node getAsignation() {
        return asignation;
    }

    public void setAsignation(Node asignation) {
        this.asignation = asignation;
    }

    public Node getConditionalPara() {
        return conditionalPara;
    }

    public void setConditionalPara(Node conditionalPara) {
        this.conditionalPara = conditionalPara;
    }

    public Node getSentenciaPara() {
        return sentenciaPara;
    }

    public void setSentenciaPara(Node sentenciaPara) {
        this.sentenciaPara = sentenciaPara;
    }

    public List<Node> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Node> instructions) {
        this.instructions = instructions;
    }
    
    @Override
    public Object execute(Environment env) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
