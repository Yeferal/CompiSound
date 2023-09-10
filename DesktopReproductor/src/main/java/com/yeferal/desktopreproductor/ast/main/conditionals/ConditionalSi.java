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
    private boolean existSalir = false;

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
    public Object execute(Environment env) {
        env.addNewAmbit();
        Object valSi = condition.execute(env);
        Object valSiT = null;
        if (condition.getType() != DataType.BOOLEAN || valSi == null){
            env.getErrorsSemantic().add(new ErrorGramm(condition.getPositionToken(), ErrorType.SEMANTIC, "", "La condicional es invalida"));
            env.backAmbit();
            return null;
        }
        valSiT = valSi;
        if (condition instanceof Identifier) {
            Symbol s = (Symbol) valSi;
            valSiT = s.getValue();
        }

        if (valSiT == null) {
            env.getErrorsSemantic().add(new ErrorGramm(condition.getPositionToken(),ErrorType.SEMANTIC, "Sumarizar", "El valor del parametro es nulo."));
                return "";
        }
        
        if ((boolean) valSiT) {
            return executeSI(env);
            
        }else {
            env.backAmbit();
            return executeSINOSI(env);
        }
        
        
//        return null;
    }
    
    public Object executeSI(Environment env){
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i) instanceof NodeFinally) {
                //Si es un nodo de finalizacion (salir, return, continue)
                NodeFinally nodeFinally = (NodeFinally) instructions.get(i);
                if (!isBelongFunc()) {
                    if (nodeFinally.getNodeEndType() == NodeEndType.SALIR) {
                        existSalir = true;
                        env.backAmbit();
                        return null;
                    }
                    env.backAmbit();
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, nodeFinally.getTypeString(), "Error de FLUJO DE CONTROL, no se permiten retornar dentro de procedimientos o continuar."));
                    return null;
                }else {
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
            }else {
                instructions.get(i).execute(env);
            }
        }
        return null;
    }
    
    public Object executeSINOSI(Environment env){
        if (listConditionalSinoSi == null) {
            return executeSINO(env);
        }
        for (int i = 0; i < listConditionalSinoSi.size(); i++) {
            env.addNewAmbit();
            Object valSi = listConditionalSinoSi.get(i).execute(env);
            Object valSiT = null;
            if (listConditionalSinoSi.get(i).getType() != DataType.BOOLEAN || valSi == null){
                env.getErrorsSemantic().add(new ErrorGramm(listConditionalSinoSi.get(i).getPositionToken(), ErrorType.SEMANTIC, "", "La condicional es invalida"));
                env.backAmbit();
                return null;
            }
            valSiT = valSi;
            if (condition instanceof Identifier) {
                Symbol s = (Symbol) valSi;
                valSiT = s.getValue();
            }

            if (valSiT == null) {
                env.getErrorsSemantic().add(new ErrorGramm(listConditionalSinoSi.get(i).getPositionToken(),ErrorType.SEMANTIC, "Sumarizar", "El valor del parametro es nulo."));
                    return "";
            }
            ConditionalSinoSi cond = (ConditionalSinoSi) listConditionalSinoSi.get(i);
            if ((boolean) valSiT) {
//                env.addNewAmbit();
                for (int j = 0; j < cond.getInstructions().size(); j++) {
                    if (cond.getInstructions().get(i) instanceof NodeFinally) {
                        //Si es un nodo de finalizacion (salir, return, continue)
                        NodeFinally nodeFinally = (NodeFinally) cond.getInstructions().get(j);
                        if (!isBelongFunc()) {
                            if (nodeFinally.getNodeEndType() == NodeEndType.SALIR) {
                                existSalir = true;
                                env.backAmbit();
                                return null;
                            }
                            env.backAmbit();
                            env.getErrorsSemantic().add(new ErrorGramm(cond.getInstructions().get(j).getPositionToken(),ErrorType.SEMANTIC, nodeFinally.getTypeString(), "Error de FLUJO DE CONTROL, no se permiten retornar dentro de procedimientos o continuar."));
                            return null;
                        }else {
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
                    }else {
                        cond.getInstructions().get(j).execute(env);
                    }
                }
                env.backAmbit();
                return null;
            }
        }
        return executeSINO(env);
    }
    
    public Object executeSINO(Environment env){
        if (conditionalSino == null) {
            return null;
        }
        env.addNewAmbit();
        
        ConditionalSino cond = (ConditionalSino) conditionalSino;
        for (int i = 0; i < cond.getInstructions().size(); i++) {
            if (cond.getInstructions().get(i) instanceof NodeFinally) {
                //Si es un nodo de finalizacion (salir, return, continue)
                NodeFinally nodeFinally = (NodeFinally) cond.getInstructions().get(i);
                if (!isBelongFunc()) {
                    if (nodeFinally.getNodeEndType() == NodeEndType.SALIR) {
                        existSalir = true;
                        env.backAmbit();
                        return null;
                    }
                    env.backAmbit();
                    env.getErrorsSemantic().add(new ErrorGramm(cond.getInstructions().get(i).getPositionToken(),ErrorType.SEMANTIC, nodeFinally.getTypeString(), "Error de FLUJO DE CONTROL, no se permiten retornar dentro de procedimientos o continuar."));
                    return null;
                }else {
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
            }else {
                cond.getInstructions().get(i).execute(env);
            }
        }
        env.backAmbit();
        return null;
    }
}
