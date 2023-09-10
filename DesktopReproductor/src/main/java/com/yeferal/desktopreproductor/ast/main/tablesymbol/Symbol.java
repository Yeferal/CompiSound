/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tablesymbol;

import com.yeferal.desktopreproductor.ast.main.DataArreglo;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Usuario
 */
public class Symbol implements Serializable{
    private String name;
    private DataType type;
    private int ambit;
    private Deque<Integer> ambitsPile = new ArrayDeque<>();
    private List<Integer> listDim = new ArrayList<>();
//    private Stack<Integer> ambitsPile = new Stack<>();
    private Object rol;
    private Object value;
    private boolean global;

    /**
     * @constructor 
     * @param name nombre del simbolo (Identificador del atributo)
     * @param type tipo de dato o variable (Tipo del atributo)
     * @param ambit ambito del atributo
     * @param rol  rol que tomara el simbolo (Función del atributo), si es una fioncion o variable, arreglo
     * @param value valor del atributo
     */
    public Symbol(String name, DataType type, int ambit, Object rol, Object value) {
        this.name = name;
        this.type = type;
        this.ambit = ambit;
        this.rol = rol;
        this.value = value;
    }
    
    public Object searchValueArray(Environment env, List<Integer> dims, int index){
        if (value instanceof ArrayList) {
            ArrayList<Object> contentList = (ArrayList<Object>) value;
            for (int i = index; i < dims.size(); i++) {
                if (contentList.get(i) instanceof ArrayList) {
                    ArrayList<Object> dataArregloTemp = (ArrayList<Object>) contentList.get(i);
                    Object res = runArray(env, contentList, dims, index+1);
                    return res;
                }else {
                    return contentList.get(i);
                }
            }
        }
        return null;
    }
    
    public Object searchValueArrayFromPosition(Environment env, int position){
        if (value instanceof ArrayList) {
            ArrayList<Object> contentList = (ArrayList<Object>) value;
            if (position < contentList.size()) {
                return contentList.get(position);
            }
        }
        return null;
    }
    
    public Object runArray(Environment env, ArrayList<Object> contentList, List<Integer> dims, int index){
        for (int i = index; i < dims.size(); i++) {
            if (contentList.get(i) instanceof ArrayList) {
                ArrayList<Object> dataArregloTemp = (ArrayList<Object>) contentList.get(i);
                Object res = runArray(env, contentList, dims, index+1);
                return res;
            }else {
                return contentList.get(i);
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public int getAmbit() {
        return ambit;
    }

    public void setAmbit(int ambit) {
        this.ambit = ambit;
    }

    public Deque<Integer> getAmbitsPile() {
        return ambitsPile;
    }

    public void setAmbitsPile(Deque<Integer> ambitsPile) {
        this.ambitsPile = ambitsPile;
    }

    public Object getRol() {
        return rol;
    }

    public void setRol(Object rol) {
        this.rol = rol;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Integer> getListDim() {
        return listDim;
    }

    public void setListDim(List<Integer> listDim) {
        this.listDim = listDim;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }
    
}
