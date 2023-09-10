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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ListDeclaration extends Node{
    private List<Node> list;
    private boolean keep, array;
    private DataType dataType;
    private Node asignation;
    private List<Node> dimensions;
    private ConverterDataType cdt = new ConverterDataType();

    public ListDeclaration(boolean keep, DataType dataType, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.keep = keep;
        this.dataType = dataType;
        this.array = false;
        list = new ArrayList<>();
    }

    public ListDeclaration(List<Node> list, boolean keep, DataType dataType, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.list = list;
        this.keep = keep;
        this.dataType = dataType;
        this.array = false;
        list = new ArrayList<>();
    }

    public ListDeclaration(boolean keep, boolean array, DataType dataType, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.keep = keep;
        this.array = array;
        this.dataType = dataType;
        list = new ArrayList<>();
    }

    public List<Node> getList() {
        return list;
    }

    public void setList(List<Node> list) {
        this.list = list;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Node getAsignation() {
        return asignation;
    }

    public void setAsignation(Node asignation) {
        this.asignation = asignation;
    }
    
    public void addDeclaration(Node declaration){
        list.add(declaration);
    }

    public boolean isArray() {
        return array;
    }

    public void setArray(boolean array) {
        this.array = array;
    }

    public List<Node> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Node> dimensions) {
        this.dimensions = dimensions;
    }
    
    public Deque<Integer> setAmbitPileS(Deque<Integer> ambitsPile){
        Deque<Integer> tempPile = new LinkedList<>(ambitsPile);
        return tempPile;
    }
    
    @Override
    public Object execute(Environment env) {
        if (array) {
            declaratedArray(env);
        }else {
            declaratedVar(env);
        }
        return null;
    }
    
    private Object declaratedVar(Environment env){
        if (asignation!=null) {
            Object obj = asignation.execute(env);
            if (obj == null) {
                if (asignation.getType() == getType()) {
                    for (Node node : list) {
                        DeclarationVar declarationVar = (DeclarationVar) node;
                        if (env.getTableSymbol().searchSymbol(declarationVar.getId(), env.currentAmbit) == null) {
                            Symbol sTemp = new Symbol(declarationVar.getId(), getType(), env.currentAmbit, DataType.IDENTIFICADOR, null);
                            sTemp.setGlobal(keep);
                            sTemp.setAmbitsPile(setAmbitPileS(env.getTableSymbol().getPileAmbit()));
                            env.getTableSymbol().addSymbol(sTemp);
                        }else {
                            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Ya existe una variable con el nombre "+declarationVar.getId()+"."));
                            return null;
                        }
                    }
                }else {
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "El tipo de dato no conicide con el tipo de la variable."));
                    return null;
                }
            }else {
                Object valConverter = cdt.converterFromAsig(getType(), asignation.getType(), obj);
                if (valConverter!=null) {
                    for (Node node : list) {
                        DeclarationVar declarationVar = (DeclarationVar) node;
                        if (env.getTableSymbol().searchSymbol(declarationVar.getId(), env.currentAmbit) == null) {
                            Symbol sTemp = new Symbol(declarationVar.getId(), getType(), env.currentAmbit, DataType.IDENTIFICADOR, valConverter);
                            sTemp.setGlobal(keep);
                            sTemp.setAmbitsPile(setAmbitPileS(env.getTableSymbol().getPileAmbit()));
                            env.getTableSymbol().addSymbol(sTemp);
                        }else {
                            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Ya existe una variable con el nombre "+declarationVar.getId()+"."));
                            return null;
                        }
                        
                    }
                }else {
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "El tipo de dato no conicide con el tipo de la variable."));
                    return null;
                }
                
            }
        }else {
            for (Node node : list) {
                DeclarationVar declarationVar = (DeclarationVar) node;
                if (env.getTableSymbol().searchSymbol(declarationVar.getId(), env.currentAmbit) == null) {
                    Symbol sTemp = new Symbol(declarationVar.getId(), getType(), env.currentAmbit, DataType.IDENTIFICADOR, null);
                    sTemp.setGlobal(keep);
                    sTemp.setAmbitsPile(setAmbitPileS(env.getTableSymbol().getPileAmbit()));
                    env.getTableSymbol().addSymbol(sTemp);
                }else {
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Ya existe una variable con el nombre "+declarationVar.getId()+"."));
                    return null;
                }
                
            }
        }
        return null;
    }
    
    private Object declaratedArray(Environment env){
        if (dimensions != null && dimensions.size()>0) {
            boolean dimEmpty = isDimNull(dimensions);
            boolean dimFull = isDimNotNull(dimensions);
            if (!dimFull && !dimEmpty) {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Dimensiones incorrectas del arreglo."));
            }else {
                
                if (dimFull) {
                    List<Integer> listDim = new ArrayList<>();
                    for (Node dimension : dimensions) {
                        Object valDim = dimension.execute(env);
                        if (dimension instanceof Identifier) {
                            if (dimension.getType() == DataType.ENTERO) {
                                Object valConverter = cdt.castEntero(valDim);
                                listDim.add((Integer) valConverter);
                            }else {
                                env.getErrorsSemantic().add(new ErrorGramm(dimension.getPositionToken(),ErrorType.SEMANTIC, "", "El valor de la dimension no es un entero."));
                                break;
                            }
                        }else {
                            if (dimension.getType() == DataType.ENTERO || dimension.getType() == DataType.DOBLE) {
                                Object valConverter = cdt.castEntero(valDim);
                                listDim.add((Integer) valConverter);
                            }else {
                                env.getErrorsSemantic().add(new ErrorGramm(dimension.getPositionToken(),ErrorType.SEMANTIC, "", "El valor de la dimension no es un entero."));
                                break;
                            }
                        }
                        
                    }
                    System.out.println(listDim);
                    if (asignation == null) {
                        for (Node node : list) {
                            DeclaracionArray declarationVar =  (DeclaracionArray) node;
                            int maxPos = getTotalSize(listDim);
                            List<Object> arr = new ArrayList<>(maxPos);
                            // Agrega elementos a tu ArrayList
                            for (int i = 0; i < maxPos; i++) {
                                arr.add(null); // Agrega elementos nulos para alcanzar la capacidad deseada
                            }
                            Symbol sTemp = new Symbol(declarationVar.getId(), getType(), env.currentAmbit, DataType.ARREGLO, arr );
                            sTemp.setGlobal(keep);
                            sTemp.setAmbitsPile(setAmbitPileS(env.getTableSymbol().getPileAmbit()));
                            sTemp.setListDim(listDim);
                            env.getTableSymbol().addSymbol(sTemp);
                        }
                    }else {
                        env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Para poder asignar datos a un arreglo tiene que tener dimensiones vacias."));
                        
                    }
                }else {
                    asigArray(env, asignation);
                }
            }
            
        }else {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Arreglo sin dimensiones."));
        }
        return null;
    }
    
    private boolean isDimNull(List<Node> listDims){
        boolean isNull = listDims.get(0)==null;
        
        for (int i = 1; i < listDims.size(); i++) {
            if (listDims.get(i) != null) {
                isNull = false;
                break;
            }
        }
        return isNull;
    }
    
    private boolean isDimNotNull(List<Node> listDims){
        boolean isNotNull = listDims.get(0)!=null;
        
        for (int i = 1; i < listDims.size(); i++) {
            if (listDims.get(i) == null) {
                isNotNull = false;
                break;
            }
        }
        return isNotNull;
    }
    
    public Object asigArray(Environment env, Node asigNode){
        if (asigNode != null) {
            if (asigNode instanceof DataArreglo) {
                DataArreglo dataArreglo = (DataArreglo) asigNode;
                int numDims = runTreeDataArray(env, dataArreglo.getContentList());
                List<Integer> listaD = getDimArray(env, dataArreglo.getContentList());
                Collections.reverse(listaD);
                if (numDims == dimensions.size()) {
                    //Obtener el arreglo de una dimension
                    List<Object> listData = getDataArray(env, dataArreglo.getContentList());
                    //setear los datos del arreglo en el Symbolo
                    for (Node node : list) {
                        DeclaracionArray declarationVar =  (DeclaracionArray) node;
                        Symbol sTemp = new Symbol(declarationVar.getId(), getType(), env.currentAmbit, DataType.ARREGLO, listData);
                        sTemp.setGlobal(keep);
                        sTemp.setAmbitsPile(setAmbitPileS(env.getTableSymbol().getPileAmbit()));
                        sTemp.setListDim(listaD);
                        env.getTableSymbol().addSymbol(sTemp);
                    }
                }else {
                    env.getErrorsSemantic().add(new ErrorGramm(asigNode.getPositionToken(),ErrorType.SEMANTIC, "", "EL Numero de dimensiones no coincide con el numbero de valores ingresados en la asignacion del arreglo."));
                    return null;
                }
                
            }else {
                env.getErrorsSemantic().add(new ErrorGramm(asigNode.getPositionToken(),ErrorType.SEMANTIC, "", "El valor del arreglo es incorrecto."));
                return null;
            }
        }
        return null;
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
    
    private int getTotalSize(List<Integer> listDim){
        int res = 1;
        for (Integer integer : listDim) {
            res *= integer;
        }
        return res;
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
}
