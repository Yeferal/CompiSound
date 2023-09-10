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
public class InstLongitud extends Node{
    private Node value;

    public InstLongitud(Node value, PositionToken positionToken) {
        super(positionToken, null);
        this.value = value;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
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
        setType(DataType.ENTERO);
        
        if (value instanceof DataArreglo) {
            DataArreglo dataArreglo = (DataArreglo) value ;
            int numDims = runTreeDataArray(env, dataArreglo.getContentList());
            if (numDims>1) {
                env.getErrorsSemantic().add(new ErrorGramm(value.getPositionToken(),ErrorType.SEMANTIC, "Longitud", "El arreglo debe de ser de una dimension no es un arreglo."));
                return 0;
            }
            List<Object> listData = getDataArray(env, dataArreglo.getContentList());
            //ENVIAR A SUMAR;
            return measureArray(listData);
        } else{
            Object valAsignation = value.execute(env);
            Object valAsigT = valAsignation;
            
            if (valAsigT == null) {
                env.getErrorsSemantic().add(new ErrorGramm(value.getPositionToken(),ErrorType.SEMANTIC, "Longitud", "El valor del parametro es nulo."));
                return 0;
            }
            
            if (value instanceof Identifier) {
                Symbol s = (Symbol) valAsignation;
                valAsigT = s.getValue();
                if (s.getRol() != DataType.ARREGLO && s.getRol() != DataType.CADENA) {
                    env.getErrorsSemantic().add(new ErrorGramm(value.getPositionToken(),ErrorType.SEMANTIC, "Longitud", "El valor "+s.getName()+" no es un arreglo."));
                    return 0;
                }
            }
            
            if (valAsigT == null) {
                env.getErrorsSemantic().add(new ErrorGramm(value.getPositionToken(),ErrorType.SEMANTIC, "Longitud", "El valor del parametro es nulo."));
                    return 0;
            }
            
            
            //ENVIAR A SUMAR;
            if (valAsigT instanceof String) {
                return measureCadena(valAsigT);
            }else {
                List<Object> listData = (List<Object>) valAsigT;
                return measureArray(listData);
            }
        }
    }
    
    public int measureArray(List<Object> listData){
        return listData.size();
    }
    
    public int measureCadena(Object valCad){
        String cad = (String) valCad;
        return cad.length();
    }
    
    
}
