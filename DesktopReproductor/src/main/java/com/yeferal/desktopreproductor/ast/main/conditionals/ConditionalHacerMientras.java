/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.conditionals;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Identifier;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.NodeEndType;
import com.yeferal.desktopreproductor.ast.main.NodeFinally;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConditionalHacerMientras extends Node{
    private List<Node> instructions;
    private Node condition;
    private boolean existSalir = false;

    public ConditionalHacerMientras(List<Node> instructions, Node condition, PositionToken positionToken) {
        super(positionToken, null);
        this.instructions = instructions;
        this.condition = condition;
    }

    public List<Node> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Node> instructions) {
        this.instructions = instructions;
    }

    public Node getCondition() {
        return condition;
    }

    public void setCondition(Node condition) {
        this.condition = condition;
    }
    
    public boolean isExistSalir() {
        return existSalir;
    }

    public void setExistSalir(boolean existSalir) {
        this.existSalir = existSalir;
    }
    
    @Override
    public Object execute(Environment env) {
        env.addNewAmbit();
        Object obj = true;
        Object valAsigT = true;
        do {
            for (int i = 0; i < instructions.size(); i++) {
                if (instructions.get(i) instanceof NodeFinally) {
                    //Si es un nodo de finalizacion (salir, return, continue)
                    NodeFinally nodeFinally = (NodeFinally) instructions.get(i);
                    if (!isBelongFunc()) {
                        if (nodeFinally.getNodeEndType() == NodeEndType.SALIR) {
                            existSalir = true;
                            env.backAmbit();
                            return null;
                        }else if (nodeFinally.getNodeEndType() == NodeEndType.CONTINUAR) {
                            continue;
                        }
                        env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, nodeFinally.getTypeString(), "Error de FLUJO DE CONTROL, no se permiten retornar dentro de procedimientos o continuar."));
                        env.backAmbit();
                        return null;
                    }else {
                        if (nodeFinally.getNodeEndType() != NodeEndType.CONTINUAR) {
                            if (nodeFinally.getNodeEndType() == NodeEndType.RETORNAR) {
                                existSalir = true;
                                setFlagRetorn(true);
                                env.backAmbit();
                                Object obj1 = nodeFinally.execute(env);
                                setType(nodeFinally.getType());
                                return obj1;
                            }else{
                                existSalir = true;
                                env.backAmbit();
                                return null;
                            }
                        }
                        continue;
                    }
                }else {
                    instructions.get(i).execute(env);
                }
            }
            obj = condition.execute(env);
            if (condition.getType() != DataType.BOOLEAN || obj == null){
                env.getErrorsSemantic().add(new ErrorGramm(condition.getPositionToken(), ErrorType.SEMANTIC, "", "La condicional es invalida"));
                env.backAmbit();
                return null;
            }
            valAsigT = obj;
            if (condition instanceof Identifier) {
                Symbol s = (Symbol) obj;
                valAsigT = s.getValue();
            }
            
            if (valAsigT == null) {
                env.getErrorsSemantic().add(new ErrorGramm(condition.getPositionToken(),ErrorType.SEMANTIC, "Sumarizar", "El valor del parametro es nulo."));
                    return "";
            }
            
        }while ((boolean)valAsigT);
        
        env.backAmbit();
        return null;
    }
}
