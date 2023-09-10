/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.conditionals;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.NodeEndType;
import com.yeferal.desktopreproductor.ast.main.NodeFinally;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class NodeDefault extends Node{
    private List<Node> instructions;

    public NodeDefault(List<Node> instructions, PositionToken positionToken) {
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
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i) instanceof NodeFinally) {
                //Si es un nodo de finalizacion (salir, return, continue)
                NodeFinally nodeFinally = (NodeFinally) instructions.get(i);
                if (!isBelongFunc()) {
                    if (nodeFinally.getNodeEndType() == NodeEndType.SALIR) {
                        return null;
                    }
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, nodeFinally.getTypeString(), "Error de FLUJO DE CONTROL, no se permiten retornar dentro de procedimientos o continuar."));
                    return null;
                }else {
                    if (nodeFinally.getNodeEndType() != NodeEndType.CONTINUAR) {
                        if (nodeFinally.getNodeEndType() == NodeEndType.RETORNAR) {
                            setFlagRetorn(true);
                            Object obj1 = nodeFinally.execute(env);
                            setType(nodeFinally.getType());
                            return obj1;
                        }else{
                            return null;
                        }
                    }
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, nodeFinally.getTypeString(), "Error de FLUJO DE CONTROL, no se permiten la sentencia continuar."));
                    return null;
                }
            }else {
                instructions.get(i).execute(env);
            }
        }
        
        return null;
    }
}
