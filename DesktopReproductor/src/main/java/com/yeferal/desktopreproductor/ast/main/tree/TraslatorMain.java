/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tree;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
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
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TraslatorMain {
    
    public void initMain(List<Node> listTrack){
        Environment environment = new Environment();
        for (Node node : listTrack) {
            runTreeTrack(environment, node);
        }
    }
    
    public void checkTrack(Environment env, Node track){
        //Comprobacion de Tipos
        //Comprobacion de Nombres
        //Comprobacion de ambito
        //Comprobacion de flujo de control
        //Comprobacion de Unicidad
        //Comprobacion de Acceso
        
    }
    
    
    public void runTreeTrack(Environment env, Node rootTrack){
        if (rootTrack instanceof TrackNode) {
            TrackNode trackNode = (TrackNode) rootTrack;
            System.out.println("Pista: "+trackNode.getId());
            for (Node node : trackNode.getListInstruction()) {
                if (node instanceof ListDeclaration) {
                    runDeclarationsGlobal(env, node);
                }
            }
        
            for (Node node : trackNode.getListInstruction()) {
                if (node instanceof FunctionProc || node instanceof Principal) {
                    runFunctions(env, node);
                }
            }
            for (ErrorGramm errorGramm : env.getErrorsSemantic()) {
                System.out.println(errorGramm.getStringError());
            }
            env.getTableSymbol().paintTable();
        }
        

    }
    
    
    public void runDeclarationsGlobal(Environment env, Node node){
        ListDeclaration listDeclaration = (ListDeclaration) node;
        listDeclaration.execute(env);
        for (Node node1 : listDeclaration.getList()) {
            if (node1 instanceof DeclarationVar) {
                DeclarationVar declarationVar = (DeclarationVar) node1;
//                System.out.println("Declaraction Var: "+declarationVar.getId());
//                declarationVar.execute(env);
            }
            
            if (node1 instanceof DeclaracionArray) {
                DeclaracionArray declaracionArray = (DeclaracionArray) node1;
//                System.out.println("Declaracion Arreglo: "+ declaracionArray.getId());
            }
        }
    }
    
    public void runFunctions(Environment env, Node node){
        //Recorrer la lista de instrucciones y agrega a la tabla de simbolos
        
        if (node instanceof FunctionProc) {
            FunctionProc functionProc = (FunctionProc) node;
            System.out.println("Funcion o Procedimiento: " + functionProc.getId());
        }
        
        if (node instanceof Principal) {
            Principal principal = (Principal) node;
            System.out.println("Principal");
        }
    }
    
    public Object runAll(Environment env, Node child){
        if (child instanceof Primitive) {
            Primitive primitive = (Primitive) child;
            // Realizar acciones específicas para Primitive
            return primitive;
        } else if (child instanceof Identifier) {
            Identifier identifier = (Identifier) child;
            // Realizar acciones específicas para Identifier
            
        } else if (child instanceof CallFunction) {
            CallFunction callFunction = (CallFunction) child;
            // Realizar acciones específicas para CallFunction
        } else if (child instanceof CallArreglo) {
            CallArreglo callArreglo = (CallArreglo) child;
            // Realizar acciones específicas para CallArreglo
        } else if (child instanceof InstReproducir) {
            InstReproducir instReproducir = (InstReproducir) child;
            // Realizar acciones específicas para InstReproducir
        } else if (child instanceof InstOrdenar) {
            InstOrdenar instOrdenar = (InstOrdenar) child;
            // Realizar acciones específicas para InstOrdenar
        } else if (child instanceof InstSumarizar) {
            InstSumarizar instSumarizar = (InstSumarizar) child;
            // Realizar acciones específicas para InstSumarizar
        } else if (child instanceof InstLongitud) {
            InstLongitud instLongitud = (InstLongitud) child;
            // Realizar acciones específicas para InstLongitud
        } else if (child instanceof AsignationVar) {
            AsignationVar asignationVar = (AsignationVar) child;
            // Realizar acciones específicas para AsignationVar
        } else if (child instanceof AsignationArray) {
            AsignationArray asignationArray = (AsignationArray) child;
            // Realizar acciones específicas para AsignationArray
        } else if (child instanceof DataArreglo) {
            DataArreglo dataArreglo = (DataArreglo) child;
            // Realizar acciones específicas para DataArreglo
        } else if (child instanceof DeclaracionArray) {
            DeclaracionArray declaracionArray = (DeclaracionArray) child;
            // Realizar acciones específicas para DeclaracionArray
        } else if (child instanceof DeclarationVar) {
            DeclarationVar declarationVar = (DeclarationVar) child;
            // Realizar acciones específicas para DeclarationVar
        } else if (child instanceof FunctionProc) {
            FunctionProc functionProc = (FunctionProc) child;
            // Realizar acciones específicas para FunctionProc
        } else if (child instanceof ListDeclaration) {
            ListDeclaration listDeclaration = (ListDeclaration) child;
            // Realizar acciones específicas para ListDeclaration
        } else if (child instanceof NodeFinally) {
            NodeFinally nodeFinally = (NodeFinally) child;
            // Realizar acciones específicas para NodeFinally
        } else if (child instanceof OperationArithmetic) {
            OperationArithmetic operationArithmetic = (OperationArithmetic) child;
            // Realizar acciones específicas para OperationArithmetic
        } else if (child instanceof OperationRational) {
            OperationRational operationRational = (OperationRational) child;
            // Realizar acciones específicas para OperationRational
        } else if (child instanceof OperationLogical) {
            OperationLogical operationLogical = (OperationLogical) child;
            // Realizar acciones específicas para OperationLogical
        } else if (child instanceof Principal) {
            Principal principal = (Principal) child;
            // Realizar acciones específicas para Principal
        } else if (child instanceof TrackNode) {
            TrackNode trackNode = (TrackNode) child;
            // Realizar acciones específicas para TrackNode
        } else if (child instanceof ConditionalCase) {
            ConditionalCase conditionalCase = (ConditionalCase) child;
            // Realizar acciones específicas para ConditionalCase
        } else if (child instanceof ConditionalHacerMientras) {
            ConditionalHacerMientras conditionalHacerMientras = (ConditionalHacerMientras) child;
            // Realizar acciones específicas para ConditionalHacerMientras
        } else if (child instanceof ConditionalMientras) {
            ConditionalMientras conditionalMientras = (ConditionalMientras) child;
            // Realizar acciones específicas para ConditionalMientras
        } else if (child instanceof ConditionalPara) {
            ConditionalPara conditionalPara = (ConditionalPara) child;
            // Realizar acciones específicas para ConditionalPara
        } else if (child instanceof ConditionalSi) {
            ConditionalSi conditionalSi = (ConditionalSi) child;
            // Realizar acciones específicas para ConditionalSi
        } else if (child instanceof ConditionalSino) {
            ConditionalSino conditionalSino = (ConditionalSino) child;
            // Realizar acciones específicas para ConditionalSino
        } else if (child instanceof ConditionalSinoSi) {
            ConditionalSinoSi conditionalSinoSi = (ConditionalSinoSi) child;
            // Realizar acciones específicas para ConditionalSinoSi
        } else if (child instanceof ConditionalSwitch) {
            ConditionalSwitch conditionalSwitch = (ConditionalSwitch) child;
            // Realizar acciones específicas para ConditionalSwitch
        } else if (child instanceof NodeDefault) {
            NodeDefault nodeDefault = (NodeDefault) child;
            // Realizar acciones específicas para NodeDefault
        } else if (child instanceof InstMensaje) {
            InstMensaje instMensaje = (InstMensaje) child;
            // Realizar acciones específicas para InstMensaje
        } else if (child instanceof InstEsperar) {
            InstEsperar instEsperar = (InstEsperar) child;
            // Realizar acciones específicas para InstEsperar
        }
        return null;
    }
}
