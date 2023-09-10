/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tree;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.main.FunctionProc;
import com.yeferal.desktopreproductor.ast.main.ListDeclaration;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.Principal;
import com.yeferal.desktopreproductor.ast.main.TrackNode;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiUnavailableException;

/**
 *
 * @author Usuario
 */
public class PlayerSound extends Thread{
    
    private Environment environment;
    private Node trackNode;
    public Thread th;

    public PlayerSound(Environment environment, Node trackNode) {
        this.environment = environment;
        this.trackNode = trackNode;
        th = new Thread(this);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    
    
    
    public void runTreeTrack(Environment env, Node rootTrack){
        if (rootTrack instanceof TrackNode) {
            TrackNode trackNode = (TrackNode) rootTrack;
            env.nameTrac = trackNode.getId();
            env.nodeRoot = trackNode;
            for (Node node : trackNode.getListInstruction()) {
                if (node instanceof ListDeclaration) {
                    runDeclarationsGlobal(env, node);
                }
            }
        
            for (Node node : trackNode.getListInstruction()) {
                if (node instanceof FunctionProc) {
                    runFunctions(env, node);
                }
            }
            
            for (Node node : trackNode.getListInstruction()) {
                if (node instanceof Principal) {
                    runPrincipal(env, node);
                }
            }
            
            for (ErrorGramm errorGramm : env.getErrorsSyntact()) {
                System.out.println(errorGramm.getStringError());
            }
            for (ErrorGramm errorGramm : env.getErrorsSemantic()) {
                System.out.println(errorGramm.getStringError());
            }
            env.getTableSymbol().paintTable();
            try {
                if (env.active) {
                    env.getTh().run();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

    }
    
    
    public void runDeclarationsGlobal(Environment env, Node node){
        ListDeclaration listDeclaration = (ListDeclaration) node;
        listDeclaration.execute(env);
    }
    
    public void runFunctions(Environment env, Node node){
        //Recorrer la lista de instrucciones y agrega a la tabla de simbolos
        if (node instanceof FunctionProc) {
            FunctionProc functionProc = (FunctionProc) node;
//            functionProc.execute(env);
            Symbol sF = env.getTableSymbol().searchSymbolFunction(functionProc.getId(), env.currentAmbit);
            if (sF != null) {
                env.getErrorsSemantic().add(new ErrorGramm(functionProc.getPositionToken(), ErrorType.SEMANTIC, functionProc.getId(), "Ya existe un Metodo con el nombre"+functionProc.getId()+"."));
            }else {
                Symbol sNew = new Symbol(functionProc.getId(), functionProc.getType(), env.currentAmbit, DataType.FUNCION, functionProc);
                sNew.setAmbitsPile(setAmbitPileS(env.getTableSymbol().getPileAmbit()));
                sNew.setGlobal(functionProc.isKeep());
                env.getTableSymbol().addSymbol(sNew);
            }
        }

    }
    
    public void runPrincipal(Environment env, Node node){
        boolean principalExist = false;
        
        if (node instanceof Principal) {
            Principal principal = (Principal) node;
            if (principalExist) {
                env.getErrorsSemantic().add(new ErrorGramm(principal.getPositionToken(), ErrorType.SEMANTIC, "Principal", "Ya existe un Metodo Principal."));
            }else{
                principalExist = true;
                principal.execute(env);
            }
        }
    }
    
    public Deque<Integer> setAmbitPileS(Deque<Integer> ambitsPile){
        Deque<Integer> tempPile = new LinkedList<>(ambitsPile);
        return tempPile;
    }

//    @Override
    public void run() {
        try {
            Environment env = environment;
            runTreeTrack(env, trackNode);
            env.resetData();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(PlayerSound.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void kill() {
        environment.kill();
    }
    public void pause(){
        environment.pause();
    }
    
    public void reanude(){
        environment.reanude();
    }
    
}
