/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.DataArreglo;
import com.yeferal.desktopreproductor.ast.main.Identifier;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class InstSumarizar extends Node{
    private Node valueArray;

    public InstSumarizar(Node valueArray, PositionToken positionToken) {
        super(positionToken, null);
        this.valueArray = valueArray;
    }

    public Node getValueArray() {
        return valueArray;
    }

    public void setValueArray(Node valueArray) {
        this.valueArray = valueArray;
    }
    
    public int runTreeDataArray(Environment env, List<Node> listNode){
        int levelFlag = 0;
        if (listNode!=null) {
            for (int i = 0; i < listNode.size(); i++) {
                if (listNode.get(i) instanceof DataArreglo) {
                    DataArreglo da = (DataArreglo) listNode.get(i);
                    if (i == 0) {
                        levelFlag = runTreeDataArray(env, da.getContentList()) + 1;
                        
                    }else {
                        int level = runTreeDataArray(env, da.getContentList()) + 1;
                        if (levelFlag != level) {
                            env.getErrorsSemantic().add(new ErrorGramm(listNode.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "La jerarquia de los valores de asignacion del arreglo son incorrectos."));
                            break;
                        }
                    }
                }else {
                    return 1;
                }
            }
        }
        return levelFlag;
    }
    
    private List<Integer> getDimArray(Environment env, List<Node> listNode){
        List<Integer> listDimTemp = new ArrayList<>();
        int dimCount = 0;
        if (listNode!=null) {
            for (int i = 0; i < listNode.size(); i++) {
                if (listNode.get(i) instanceof DataArreglo) {
                    DataArreglo da = (DataArreglo) listNode.get(i);
                    if (i == 0) {
                        dimCount = da.getContentList().size();
                        List<Integer> listTemp = getDimArray(env, da.getContentList());
                        listDimTemp = listTemp;
                    }else {
                        if (dimCount != da.getContentList().size()) {
                            env.getErrorsSemantic().add(new ErrorGramm(listNode.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "La las dimensiones del arreglo no coincidem."));
                            break;
                        }
                        getDimArray(env, da.getContentList());
                    }
                }else {
                    dimCount = listNode.size();
                    listDimTemp.add(dimCount);
                    return listDimTemp;
                }
            }
            
        }
        listDimTemp.add(listNode.size());
        
        return listDimTemp;
    }
    
    private List<Object> getDataArray(Environment env, List<Node> listNode){
        List<Object> listDimTemp = new ArrayList<>();
        if (listNode!=null) {
            for (int i = 0; i < listNode.size(); i++) {
                if (listNode.get(i) instanceof DataArreglo) {
                    DataArreglo da = (DataArreglo) listNode.get(i);
                    List<Object> listTemp = getDataArray(env, da.getContentList());
                    listDimTemp.addAll(listTemp);
                }else {
                    if (listNode.get(i) instanceof Identifier) {
                        Symbol s = (Symbol) listNode.get(i).execute(env);
                        listDimTemp.add(s.getValue());
                    }else {
                        Object obj = listNode.get(i).execute(env);
                        listDimTemp.add(obj);
                    }
                }
            }
        }
        return listDimTemp;
    }
    
    @Override
    public Object execute(Environment env) {
        setType(DataType.CADENA);
        
        if (valueArray instanceof DataArreglo) {
            DataArreglo dataArreglo = (DataArreglo) valueArray ;
            int numDims = runTreeDataArray(env, dataArreglo.getContentList());
            if (numDims>1) {
                env.getErrorsSemantic().add(new ErrorGramm(valueArray.getPositionToken(),ErrorType.SEMANTIC, "Sumarizar", "El arreglo debe de ser de una dimension no es un arreglo."));
                return "";
            }
            List<Object> listData = getDataArray(env, dataArreglo.getContentList());
            //ENVIAR A SUMAR;
            return sumarizar(listData)+"";
        } else{
            Object valAsignation = valueArray.execute(env);
            Object valAsigT = valAsignation;
            
            if (valAsigT == null) {
                env.getErrorsSemantic().add(new ErrorGramm(valueArray.getPositionToken(),ErrorType.SEMANTIC, "Sumarizar", "El valor del parametro es nulo."));
                    return "";
            }
            
            if (valueArray instanceof Identifier) {
                Symbol s = (Symbol) valAsignation;
                valAsigT = s.getValue();
                if (s.getRol() != DataType.ARREGLO) {
                    env.getErrorsSemantic().add(new ErrorGramm(valueArray.getPositionToken(),ErrorType.SEMANTIC, "Sumarizar", "El valor "+s.getName()+" no es un arreglo."));
                    return "";
                }
            }
            
            if (valAsigT == null) {
                env.getErrorsSemantic().add(new ErrorGramm(valueArray.getPositionToken(),ErrorType.SEMANTIC, "Sumarizar", "El valor del parametro es nulo."));
                    return "";
            }
            
            List<Object> listData = (List<Object>) valAsigT;
            //ENVIAR A SUMAR;
            return sumarizar(listData)+"";
        }
    }
    
    public String sumarizar(List<Object> listData){
        double sumaNumeros = 0.0;
        StringBuilder concatenacion = new StringBuilder();

        for (Object elemento : listData) {
            if (elemento instanceof Integer || elemento instanceof Double) {
                // Si es un número (entero o double), lo sumamos
                if (elemento instanceof Integer) {
                    sumaNumeros += (Integer) elemento;
                } else {
                    sumaNumeros += (Double) elemento;
                }
            } else if (elemento instanceof String || elemento instanceof Character) {
                // Si es una cadena (string o char), lo concatenamos
                concatenacion.append(elemento.toString());
            }
        }

        if (concatenacion.length() > 0) {
            return concatenacion.toString();
        } else {
            return sumaNumeros+"";
        }
        
    }
}
