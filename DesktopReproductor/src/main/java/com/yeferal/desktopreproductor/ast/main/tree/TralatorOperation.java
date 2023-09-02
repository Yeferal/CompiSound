/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tree;

import com.yeferal.desktopreproductor.ast.main.ArithType;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.RationalTypes;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;

/**
 *
 * @author Usuario
 */
public class TralatorOperation {
    
    
    public Object runOpArithmetic(Node nodeLeft, Node nodeRigth, ArithType arithType){
        switch(arithType){
            case ADD:
                
                return true;
            case SUBTRAC:
            case MULTI:
            case DIV:
            case MOD:
            case POW:
                
                return false;
            default:
                return false;
        }
    }
    
    
    
    
    
    
    
    public void runOpRatioanal(Node nodeLeft, Node nodeRigth, RationalTypes rt){
        
    }
    
    public void runOpLogical(){
        
    }
}
