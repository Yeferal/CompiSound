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
import com.yeferal.desktopreproductor.ast.main.Primitive;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConditionalCase extends Node{
    private Node valueCase; //TIENE QUE SER UNO DE TIPO PRIMITIVO
    private List<Node> instructions;
    private boolean existSalir = false;

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

    public boolean isExistSalir() {
        return existSalir;
    }

    public void setExistSalir(boolean existSalir) {
        this.existSalir = existSalir;
    }
    
    @Override
    public Object execute(Environment env) {
        
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i) instanceof NodeFinally) {
                //Si es un nodo de finalizacion (salir, return, continue)
                NodeFinally nodeFinally = (NodeFinally) instructions.get(i);
                if (!isBelongFunc()) {
                    if (nodeFinally.getNodeEndType() == NodeEndType.SALIR) {
                        existSalir = true;
                        return null;
                    }
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, nodeFinally.getTypeString(), "Error de FLUJO DE CONTROL, no se permiten retornar dentro de procedimientos o continuar."));
                    return null;
                }else {
                    if (nodeFinally.getNodeEndType() != NodeEndType.CONTINUAR) {
                        if (nodeFinally.getNodeEndType() == NodeEndType.RETORNAR) {
                            existSalir = true;
                            setFlagRetorn(true);
                            Object obj1 = nodeFinally.execute(env);
                            setType(nodeFinally.getType());
                            return obj1;
                        }else{
                            existSalir = true;
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
