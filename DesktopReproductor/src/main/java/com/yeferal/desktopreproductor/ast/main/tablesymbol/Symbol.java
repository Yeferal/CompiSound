/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tablesymbol;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Symbol {
    private String name;
    private DataType type;
    private int ambit;
    private Deque<Integer> ambitsPile = new ArrayDeque<>();
    private Object rol;
    private Object value;

    /**
     * @constructor 
     * @param name nombre del simbolo (Identificador del atributo)
     * @param type tipo de dato o variable (Tipo del atributo)
     * @param ambit ambito del atributo
     * @param rol  rol que tomara el simbolo (Función del atributo)
     * @param value valor del atributo
     */
    public Symbol(String name, DataType type, int ambit, Object rol, Object value) {
        this.name = name;
        this.type = type;
        this.ambit = ambit;
        this.rol = rol;
        this.value = value;
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

    
    
    
    
}
