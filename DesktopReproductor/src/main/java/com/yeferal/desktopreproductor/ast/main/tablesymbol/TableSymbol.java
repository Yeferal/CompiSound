/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tablesymbol;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Usuario
 */
public class TableSymbol implements Serializable{
    private List<Symbol> tableSymbol = new ArrayList<>();
    Deque<Integer> pileAmbit;
    
    public TableSymbol(){
        pileAmbit = new ArrayDeque<>();
    }
    
    public void addSymbol(Symbol s){
        tableSymbol.add(s);
    }
    
    public void addAmbit(int ambit){
        pileAmbit.add(ambit);
    }
    
    public void popAmbit(){
        if (!pileAmbit.isEmpty()) {
            pileAmbit.pollLast();
        }
    }
    
    public void backAllAmbit(){
        while (!pileAmbit.isEmpty()) {
            pileAmbit.pollLast();
        }
    }
    
    public void getAmbitActual(){
        pileAmbit.peek();
    }
    
    public Symbol searchSymbolIdentifier(String name, int ambit){
        for (Symbol symbol : tableSymbol) {
//            System.out.println("Busca: "+name+"\t De tipo IDENTIFICADOR");
            if (symbol!=null && (DataType)symbol.getRol()==DataType.IDENTIFICADOR && name.equals(symbol.getName())) {
//                System.out.println("Lo encontro: ");
//                System.out.println("Busca: "+name+ " == "+symbol.getName());
                if (searchAmbit(symbol.getAmbitsPile(), symbol.getAmbit())) {
//                    System.out.println("Simbolo21: "+symbol.getName()+"\t Tipo: "+symbol.getType()+"\t Ambito: "+symbol.getAmbit()+"\t Valor: "+symbol.getValue());
                    return symbol;
                }
            }
        }
        return null;
    }
    
    public Symbol searchSymbolArreglo(String name, int ambit){
        for (Symbol symbol : tableSymbol) {
            if (symbol!=null && (DataType)symbol.getRol()==DataType.ARREGLO && name.equals(symbol.getName())) {
                if (searchAmbit(symbol.getAmbitsPile(), symbol.getAmbit())) {
                    return symbol;
                }
            }
        }
        return null;
    }
    
    public Symbol searchSymbol(String name, int ambit){
        for (Symbol symbol : tableSymbol) {
            if (symbol!=null && 
                    ((DataType)symbol.getRol()==DataType.ARREGLO || 
                    (DataType)symbol.getRol()==DataType.IDENTIFICADOR || 
                    (DataType)symbol.getRol()==DataType.FUNCION || 
                    (DataType)symbol.getRol()==DataType.VOID) && 
                    name.equals(symbol.getName())) {
//                System.out.println("Busca: "+name+ " == "+symbol.getName());
                if (searchAmbit(pileAmbit, symbol.getAmbit())) {
                    return symbol;
                }
            }
        }
        return null;
    }
    
    public Symbol searchSymbolFunction(String name, int ambit){
//        System.out.println("BUSCA FUNCION: "+name);
        for (Symbol symbol : tableSymbol) {
//            System.out.println("compara: "+symbol.getName());
            if (symbol!=null && ((DataType)symbol.getRol()==DataType.FUNCION) && name.equals(symbol.getName())) {
//                System.out.println("searchAmbit: "+symbol.getName());
                if (searchAmbit(symbol.getAmbitsPile(), symbol.getAmbit())) {
//                    System.out.println("Encontro la funcion");
                    return symbol;
                }
            }
        }
        return null;
    }
    
    public Symbol searchSymbolProcedure(String name, int ambit){
        for (Symbol symbol : tableSymbol) {
            if (symbol!=null && ((DataType)symbol.getRol()==DataType.VOID) && name.equals(symbol.getName())) {
                if (searchAmbit(symbol.getAmbitsPile(), symbol.getAmbit())) {
                    return symbol;
                }
            }
        }
        return null;
    }
    
    public boolean searchAmbit(Deque<Integer> pile, int ambit){
        Deque<Integer> tempPile = new LinkedList<>(pile);
        Deque<Integer> tempPileNow = new LinkedList<>(pileAmbit);
//        System.out.println(tempPile.toString()+"   ===   "+tempPileNow.toString());
        // Iteramos a través de la copia de la pila para buscar el valor
//        while (!tempPile.isEmpty()) {
//            int element = tempPile.pop();
//            while (!tempPileNow.isEmpty()) {                
//                int element2 = tempPile.pop();
//                if (element == element2) {
//                    return true; // Encontramos el valor en la pila
//                }
//            }
//            
//        }
//        System.out.println("BUSCO EN LA LISTA DE AMBITOS: "+tempPileNow.contains(ambit));
//        for (Integer elemento : tempPile) {
            if (tempPileNow.contains(ambit)) {
                return true; // Se encontró un elemento común
            }
//        }

        // Si llegamos aquí, no se encontró el valor en la pila
        return false;
    }
    
    public void setValueSymbol(String name, int ambit, Object value){
        for (Symbol symbol : tableSymbol) {
            if (symbol!=null && 
                    ((DataType)symbol.getRol()==DataType.ARREGLO || 
                    (DataType)symbol.getRol()==DataType.IDENTIFICADOR || 
                    (DataType)symbol.getRol()==DataType.FUNCION || 
                    (DataType)symbol.getRol()==DataType.VOID) && 
                    name.equals(symbol.getName())) {
                
                if (searchAmbit(symbol.getAmbitsPile(), ambit)) {
                    symbol.setValue(value);
                }
            }
        }
    }
    
    public void getValueSymbol(String name, int ambit){
        
    }
    
    public void paintTable(){
        for (Symbol symbol : tableSymbol) {
            System.out.println(
                    "Simbolo: "+symbol.getName()+
                    "\t Tipo: "+symbol.getType()+
                    "\t Ambito: "+symbol.getAmbit()+"->"+symbol.getAmbitsPile().toString()+
                    "\t Rol: "+symbol.getRol()+
                    "\t Global: "+symbol.isGlobal()+
                    "\t Valor: "+symbol.getValue()
            );
//            if (symbol.getRol() == DataType.ARREGLO) {
//                System.out.println(symbol.getListDim() + " -> " + symbol.getValue().toString());
//            }
        }
    }

    public List<Symbol> getTableSymbol() {
        return tableSymbol;
    }

    public void setTableSymbol(List<Symbol> tableSymbol) {
        this.tableSymbol = tableSymbol;
    }

    public Deque<Integer> getPileAmbit() {
        return pileAmbit;
    }

    public void setPileAmbit(Deque<Integer> pileAmbit) {
        this.pileAmbit = pileAmbit;
    }
    
}
