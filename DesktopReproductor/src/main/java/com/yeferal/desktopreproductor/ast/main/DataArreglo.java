/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DataArreglo extends Node{
    private List<Node> contentList;
//    private List<Integer> 

    public DataArreglo(List<Node> contentList, PositionToken positionToken) {
        super(positionToken, null);
        this.contentList = contentList;
    }

    public List<Node> getContentList() {
        return contentList;
    }

    public void setContentList(List<Node> contentList) {
        this.contentList = contentList;
    }
    
    public Object searchValueArray(Environment env, List<Integer> dims, int index){
        for (int i = index; i < dims.size(); i++) {
            if (contentList.get(i) instanceof DataArreglo) {
                DataArreglo dataArregloTemp = (DataArreglo) contentList.get(i);
                Object res = dataArregloTemp.searchValueArray(env, dims, index+1);
                setType(dataArregloTemp.getType());
                return res;
            }else {
                Node node = contentList.get(i);
                Object res = node.execute(env);
                setType(node.getType());
                return res;
            }
            
        }
        return null;
    }
    
    @Override
    public Object execute(Environment env) {
        //Recorrer los datos de la lista y verificar que todos sean del mismo tamanio;
        if (contentList != null) {
            if (contentList.size()>0) {
                getDimArray(env, contentList);
                if (verifyDimArray(env, contentList)) {
                    System.out.println("El arreglo es correcto");
                }else {
                    System.out.println("Arreglo incorrecto");
                }
//                for (Node node : contentList) {
//                    if (node instanceof DataArreglo) {
//                        System.out.println("Si es xD un DataArreglo");
////                        node.execute(env);
//                    }else {
//                        System.out.println("No es una DataArreglo");
//                        Object obj = node.execute(env);
//                        
//                    }
//                    
//                }
            }
        }
        return null;
    }
    
    
    private boolean verifyDimArray(Environment env, List<Node> listNode){
        boolean dimCorrect = true;
        boolean nodeUnit = true;
        if (listNode.get(0) instanceof DataArreglo) {
            nodeUnit = false;
        }
        for (int i = 1; i < listNode.size(); i++) {
            if (listNode.get(i) instanceof DataArreglo) {
                if (nodeUnit) {
                    //Detecta que es un Data Arreglo, y que no es un nodo de dato primitivo o operacion
                    env.getErrorsSemantic().add(new ErrorGramm(listNode.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "La jerarquia de los valores de asignacion del arreglo son incorrectos."));
                    return false;
                }
            }else {
                if (!nodeUnit) {
                    //Detecta que no es un Data Arreglo, y que es un nodo de dato primitivo o operacion
                    env.getErrorsSemantic().add(new ErrorGramm(listNode.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "La jerarquia de los valores de asignacion del arreglo son incorrectos."));
                    return false;
                }
            }
        }
        return false;
    }
    
    private int getDimArray(Environment env, List<Node> listNode){
        int dimCount = 0;
        boolean nodeUnit = true;
        if (listNode.get(0) instanceof DataArreglo) {
            DataArreglo da = (DataArreglo) listNode.get(0);
            dimCount = da.getContentList().size();
            nodeUnit = false;
        }else {
            dimCount = listNode.size();
        }
        for (int i = 1; i < listNode.size(); i++) {
            if (listNode.get(i) instanceof DataArreglo) {
                DataArreglo dataArreglo = (DataArreglo) listNode.get(0);
                if (nodeUnit) {
                    //Detecta que es un Data Arreglo, y que no es un nodo de dato primitivo o operacion
                    env.getErrorsSemantic().add(new ErrorGramm(listNode.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "La jerarquia de los valores de asignacion del arreglo son incorrectos."));
                    return 0;
                } else if (dimCount != dataArreglo.getContentList().size()){
                    env.getErrorsSemantic().add(new ErrorGramm(listNode.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "Los valores de asignacion del arreglo son incorrectos, no son los tamanio correctos."));
                    return 0;
                }
            }else {
                if (!nodeUnit) {
                    //Detecta que no es un Data Arreglo, y que es un nodo de dato primitivo o operacion
                    env.getErrorsSemantic().add(new ErrorGramm(listNode.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "La jerarquia de los valores de asignacion del arreglo son incorrectos."));
                    return 0;
                }
            }
        }
        return dimCount;
    }
    
    //Cuando son nodos de tipo valor
    public ArrayList<ArrayList<Object>> runListSimpleNode(Environment env){
        System.out.println("Nodo de multiples dimensiones");
        return null;
    }
    
    //Cuando son nodos de tipo Array
    public ArrayList<Object> runListComplexNode(Environment env){
        System.out.println("Nodo de una Dimension");
        return null;
    }
}
