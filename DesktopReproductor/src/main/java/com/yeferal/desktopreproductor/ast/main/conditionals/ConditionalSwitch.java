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
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConditionalSwitch extends Node{
    private Node value;
    private List<Node> listCases;
    private Node defaultCase;
    

    public ConditionalSwitch(Node value, List<Node> listCases, Node defaultCase, PositionToken positionToken) {
        super(positionToken, null);
        this.value = value;
        this.listCases = listCases;
        this.defaultCase = defaultCase;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
    }

    public List<Node> getListCases() {
        return listCases;
    }

    public void setListCases(List<Node> listCases) {
        this.listCases = listCases;
    }

    public Node getDefaultCase() {
        return defaultCase;
    }

    public void setDefaultCase(Node defaultCase) {
        this.defaultCase = defaultCase;
    }
    
    @Override
    public Object execute(Environment env) {
        Object valRes = value.execute(env);
        Object valCond = null;
        //COMPROBACION DE VALOR NULO
        if (valRes instanceof Identifier) {
            Symbol s = (Symbol) valRes;
            if (s !=null) {
                valCond = s.getValue();
            }else {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Para la condicional del Switch no se admiten valores nulos."));
            }
        }else {
            if (valRes != null) {
                valCond = valRes;
            }else {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Para la condicional del Switch no se admiten valores nulos."));
            }
        }
        
        //VARIFICAION DE UNICIDAD
        List<Object> listCaseVal = new ArrayList<>();
        for (Node listCase : listCases) {
            if (!(listCase instanceof NodeDefault)) {
                ConditionalCase conditionalCase = (ConditionalCase) listCase;
                Object caseVal = conditionalCase.getValueCase().execute(env);
                if (value.getType() != conditionalCase.getValueCase().getType()) {
                    env.getErrorsSemantic().add(new ErrorGramm(conditionalCase.getValueCase().getPositionToken(),ErrorType.SEMANTIC, "caso "+caseVal, "El valor condicional y el valor del Caso no son del mismo tipo de dato."));
                    env.backAmbit();
                    return null;
                }
                listCaseVal.add(caseVal);
            }
        }
        
        listCaseVal = searchValUnique(env, listCaseVal);
        //paso todas la pruebas ahora a buscar cual se parece
        for (int i = 0; i < listCaseVal.size(); i++) {
            if (listCaseVal.get(i).equals(valCond)) {
                ConditionalCase conditionalCase = (ConditionalCase) listCases.get(i);
                env.addNewAmbit();
                conditionalCase.setBelongFunc(isBelongFunc());
                Object result = conditionalCase.execute(env);
                env.backAmbit();
                if (conditionalCase.isExistSalir()) {
                    if (defaultCase.isFlagRetorn()) {
                        setFlagRetorn(true);
                    }
                    return result;
                }
            }
        }
        //NO ES IGUAL A NINGUNO ENTONCES AJECTURA EL DEFAULT
        if (defaultCase != null) {
            env.addNewAmbit();
            defaultCase.setBelongFunc(isBelongFunc());
            Object valorRetorn = defaultCase.execute(env);
            if (defaultCase.isFlagRetorn()) {
                setFlagRetorn(true);
            }
            env.backAmbit();
            return valorRetorn;
            
        }
        return null;
    }
    
    public List<Object> searchValUnique(Environment env, List<Object> list){
        HashSet<Object> valUniques = new HashSet<>();
        ArrayList<Object> listNotDuplicated = new ArrayList<>();
        for (Object object : list) {
            if (valUniques.add(object)) {
                listNotDuplicated.add(object);
            }else {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Erro de UNICIDAD, el Switch tiene Casos duplicados."));
            }
        }
        list.clear();
        list.addAll(listNotDuplicated);
        return list;
    }
}
