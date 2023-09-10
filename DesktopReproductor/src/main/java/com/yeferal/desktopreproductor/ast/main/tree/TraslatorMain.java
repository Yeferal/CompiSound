/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tree;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.main.AsignationArray;
import com.yeferal.desktopreproductor.ast.main.AsignationVar;
import com.yeferal.desktopreproductor.ast.main.DataArreglo;
import com.yeferal.desktopreproductor.ast.main.DeclaracionArray;
import com.yeferal.desktopreproductor.ast.main.DeclarationVar;
import com.yeferal.desktopreproductor.ast.main.FunctionProc;
import com.yeferal.desktopreproductor.ast.main.Identifier;
import com.yeferal.desktopreproductor.ast.main.ListDeclaration;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.NodeFinally;
import com.yeferal.desktopreproductor.ast.main.OperationArithmetic;
import com.yeferal.desktopreproductor.ast.main.OperationLogical;
import com.yeferal.desktopreproductor.ast.main.OperationRational;
import com.yeferal.desktopreproductor.ast.main.Primitive;
import com.yeferal.desktopreproductor.ast.main.Principal;
import com.yeferal.desktopreproductor.ast.main.TrackNode;
import com.yeferal.desktopreproductor.ast.main.conditionals.ConditionalCase;
import com.yeferal.desktopreproductor.ast.main.conditionals.ConditionalHacerMientras;
import com.yeferal.desktopreproductor.ast.main.conditionals.ConditionalMientras;
import com.yeferal.desktopreproductor.ast.main.conditionals.ConditionalPara;
import com.yeferal.desktopreproductor.ast.main.conditionals.ConditionalSi;
import com.yeferal.desktopreproductor.ast.main.conditionals.ConditionalSino;
import com.yeferal.desktopreproductor.ast.main.conditionals.ConditionalSinoSi;
import com.yeferal.desktopreproductor.ast.main.conditionals.ConditionalSwitch;
import com.yeferal.desktopreproductor.ast.main.conditionals.NodeDefault;
import com.yeferal.desktopreproductor.ast.main.instructions.CallArreglo;
import com.yeferal.desktopreproductor.ast.main.instructions.CallFunction;
import com.yeferal.desktopreproductor.ast.main.instructions.InstEsperar;
import com.yeferal.desktopreproductor.ast.main.instructions.InstLongitud;
import com.yeferal.desktopreproductor.ast.main.instructions.InstMensaje;
import com.yeferal.desktopreproductor.ast.main.instructions.InstOrdenar;
import com.yeferal.desktopreproductor.ast.main.instructions.InstReproducir;
import com.yeferal.desktopreproductor.ast.main.instructions.InstSumarizar;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import javax.sound.midi.MidiUnavailableException;

/**
 *
 * @author Usuario
 */
public class TraslatorMain {
    
    public Environment initMain(List<Node> listTrack) throws MidiUnavailableException{
        Environment environment = new Environment();
        for (Node node : listTrack) {
            
            runTreeTrack(environment, node);
        }
//        environment.resetData();
        return environment;
    }
    
    public Environment initMainEnv(Node track, Environment env) throws MidiUnavailableException{
        Environment environment = env;
        runTreeTrack(environment, track);
        environment.resetData();
        return environment;
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
//                env.runInst();
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
}
