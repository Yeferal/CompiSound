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
public class ConditionalMientras extends Node{
    private Node condition;
    private List<Node> instructions;

    public ConditionalMientras(Node condition, List<Node> instructions, PositionToken positionToken) {
        super(positionToken, null);
        this.condition = condition;
        this.instructions = instructions;
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
    
    @Override
    public Object execute(Environment env) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
