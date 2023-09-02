/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tablesymbol;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TableSymbol {
    private List<Symbol> tableSymbol = new ArrayList<>();
    
    public void addSimbol(Symbol s){
        tableSymbol.add(s);
    }
    
    public Symbol searchSymbol(String name, int ambit){
        return null;
    }
    
    public void setValueSymbol(String name, int ambit, Object value){
        
    }
    
    public void getValueSymbol(String name, int ambit){
        
    }
    
    public void get(){
        
    }
    
    public void paintTable(){
        for (Symbol symbol : tableSymbol) {
            System.out.println("Simbolo: "+symbol.getName()+"\t Tipo: "+symbol.getType()+"\t Ambito: "+symbol.getAmbit()+"\t Valor: "+symbol.getValue());
        }
    }
}
