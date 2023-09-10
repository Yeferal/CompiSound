/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.ConverterDataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class FunctionProc extends Node{
    private boolean keep, function; // si no es un metodo
    private String id;
    private List<Node> params;
    private List<Node> instructions;
    private ConverterDataType cdt = new ConverterDataType();

    public FunctionProc(boolean keep, boolean function, String id, List<Node> params, List<Node> instructions, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.keep = keep;
        this.function = function;
        this.id = id;
        this.params = params;
        this.instructions = instructions;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public boolean isFunction() {
        return function;
    }

    public void setFunction(boolean function) {
        this.function = function;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getParams() {
        return params;
    }

    public void setParams(List<Node> params) {
        this.params = params;
    }

    public List<Node> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Node> instructions) {
        this.instructions = instructions;
    }
    
    @Override
    public Object execute(Environment env) {
//        env.addNewAmbit();
        System.out.println("FUNCION O PROCEDIMIENTO: "+getId()+ "\t ambitos:"+env.getTableSymbol().getPileAmbit().toString());
        Object valRetorn = null;
        
        
        if (function) {
//            if (params.size()>0) {
//                runParams(env);
//            }
            for (int i = 0; i < instructions.size(); i++) {
                instructions.get(i).setBelongFunc(true);
                if (instructions.get(i) instanceof NodeFinally) {
                    NodeFinally nodeFinally = (NodeFinally) instructions.get(i);
                    if (nodeFinally.getNodeEndType() != NodeEndType.RETORNAR) {
                        env.getErrorsSyntact().add(new ErrorGramm(nodeFinally.getPositionToken(),ErrorType.SYNTACTIC, nodeFinally.getTypeString(), "Flujo de control invalido no se admite este Token"));
                    }
                    valRetorn = nodeFinally.execute(env);
                    Object valAsigT = valRetorn;

                    if (valAsigT == null) {
                        env.getErrorsSemantic().add(new ErrorGramm(nodeFinally.getPositionToken(),ErrorType.SEMANTIC, "", "El valor de retorno es nulo."));
                            return "";
                    }

                    if (valRetorn instanceof Identifier) {
                        Symbol s = (Symbol) valRetorn;
                        valAsigT = s.getValue();
                        if (s.getRol() != DataType.ARREGLO) {
                            env.getErrorsSemantic().add(new ErrorGramm(nodeFinally.getPositionToken(),ErrorType.SEMANTIC, "", "El valor "+s.getName()+" no es un arreglo."));
                            return "";
                        }
                    }
                    if (valAsigT == null) {
                        env.getErrorsSemantic().add(new ErrorGramm(nodeFinally.getPositionToken(),ErrorType.SEMANTIC, "", "El valor de retorno es nulo."));
                        return "";
                    }
                    
                    Object valConverter = cdt.converterFromAsig(getType(), nodeFinally.getType(),valAsigT);
                    if (valConverter!=null) {
                        return valConverter;
                    }else {
                        env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "El tipo de dato no conicide con el tipo de la variable."));
                        return null;
                    }
                }else{

                    valRetorn = instructions.get(i).execute(env);
                    Object valAsigT = valRetorn;

                    if (instructions.get(i).isFlagRetorn()) {

                        if (valAsigT == null) {
                            env.getErrorsSemantic().add(new ErrorGramm(instructions.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "El valor de retorno es nulo."));
                                return "";
                        }

                        if (valRetorn instanceof Identifier) {
                            Symbol s = (Symbol) valRetorn;
                            valAsigT = s.getValue();
                            if (s.getRol() != DataType.ARREGLO) {
                                env.getErrorsSemantic().add(new ErrorGramm(instructions.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "El valor "+s.getName()+" no es un arreglo."));
                                return "";
                            }
                        }
                        if (valAsigT == null) {
                            env.getErrorsSemantic().add(new ErrorGramm(instructions.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "El valor de retorno es nulo."));
                            return "";
                        }
                        
                        Object valConverter = cdt.converterFromAsig(getType(), instructions.get(i).getType(),valAsigT);
                        if (valConverter!=null) {

                            return valConverter;
                        }else {
                            env.getErrorsSemantic().add(new ErrorGramm(instructions.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "El tipo de dato no conicide con el tipo de la variable."));
                            return null;
                        }

                    }



                }
            } 
        }else {
//            if (params.size()>0) {
//                runParams(env);
//            }
            for (int i = 0; i < instructions.size(); i++) {
                if (instructions.get(i) instanceof NodeFinally) {
                    NodeFinally nodeFinally = (NodeFinally) instructions.get(i);
                    env.getErrorsSyntact().add(new ErrorGramm(nodeFinally.getPositionToken(),ErrorType.SYNTACTIC, nodeFinally.getTypeString(), "Flujo de control invalido no se admite este Token"));
                }else{
                    instructions.get(i).execute(env);
                }
            }
        }
        env.backAmbit();
        return valRetorn;
    }
    
    
    public void runParams(Environment env){
        for (Node param : params) {
            if (param instanceof  DeclarationVar) {
                DeclarationVar declarationVar = (DeclarationVar) param;
                System.out.println("PARAMETRO: "+declarationVar.getId());
                declarationVar.setParamFlag(true);
                declarationVar.execute(env);
            }
        }
    }
}
