/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Principal extends Node{
    private List<Node> instructions;

    public Principal(List<Node> instructions, PositionToken positionToken) {
        super(positionToken, null);
        this.instructions = instructions;
    }
    
    public List<Node> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Node> instructions) {
        this.instructions = instructions;
    }
    
    @Override
    public Object execute(Environment env) {
//        System.out.println("AMBITPS DE ;A ");
//        env.getTableSymbol().backAllAmbit();
        for (Node instruction : instructions) {
            //EJECTURA INSTRUCCIONES
            
            instruction.execute(env);
        }
        return null;
    }
}
