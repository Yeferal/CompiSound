/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;

/**
 *
 * @author Usuario
 */
public class InstEsperar extends Node{
    private Node value;
    private Node chanel;

    public InstEsperar(Node value, Node chanel, PositionToken positionToken) {
        super(positionToken, null);
        this.chanel = chanel;
        this.value = value;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
    }

    public Node getChanel() {
        return chanel;
    }

    public void setChanel(Node chanel) {
        this.chanel = chanel;
    }
     
    @Override
    public Object execute(Environment env) {
        setType(DataType.ENTERO);
        Object intTime = value.execute(env);
        Object intChanel = chanel.execute(env);
        
        if (intTime==null || intChanel==null) {
            env.getErrorsSemantic().add(new ErrorGramm(value.getPositionToken(),ErrorType.SEMANTIC, "Esperar", "El valor del parametro es nulo."));
            return "";
        }
        
        if (value.getType() != DataType.ENTERO) {
            env.getErrorsSyntact().add(new ErrorGramm(value.getPositionToken(),ErrorType.SYNTACTIC, "Esperar", "El valor del parametro TIEMPO ES INCORRECTO, solo se admiten enteros"));
        }
        
        if (chanel.getType() != DataType.ENTERO) {
            env.getErrorsSyntact().add(new ErrorGramm(chanel.getPositionToken(),ErrorType.SYNTACTIC, "Esperar", "El valor del parametro CANAL ES INCORRECTO, solo se admiten enteros"));
        }
        
        ExeEsperar er = new ExeEsperar((int) intTime,(int) intChanel, env.getSynth());
        env.getListExecutes().add(er);
        if (env.active) {
            Thread thread1 = new Thread(er);
            thread1.start();
//            er.execute();
        }
        
        return intTime;
    } 
    
}
