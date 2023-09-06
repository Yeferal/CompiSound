/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.conditionals;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.Primitive;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConditionalCase extends Node{
    private Node valueCase; //TIENE QUE SE UNO DE TIPO PRIMITIVO
    private List<Node> instructions;

    public ConditionalCase(Node valueCase, List<Node> instructions, PositionToken positionToken) {
        super(positionToken, null);
        this.valueCase = valueCase;
        this.instructions = instructions;
    }

    public Node getValueCase() {
        return valueCase;
    }

    public void setValueCase(Node valueCase) {
        this.valueCase = valueCase;
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
