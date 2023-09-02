/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tree;

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
import com.yeferal.desktopreproductor.ast.main.instructions.notas.MusicalNotes;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.TableSymbol;

/**
 *
 * @author Usuario
 */
public class RunOperator {
    
    
    public Object runOpArith(Node child, TableSymbol tableSymbol){
        switch (child.getClass().getSimpleName()) {
            case "Primitive":
                Primitive primitive = (Primitive) child;
                // Realizar acciones específicas para Primitive
                break;
            case "Identifier":
                Identifier identifier = (Identifier) child;
                // Realizar acciones específicas para Identifier
                break;
            case "CallFunction":
                CallFunction callFunction = (CallFunction) child;
                // Realizar acciones específicas para CallFunction
                break;
            case "CallArreglo":
                CallArreglo callArreglo = (CallArreglo) child;
                // Realizar acciones específicas para CallArreglo
                break;
            case "InstReproducir":
                InstReproducir instReproducir = (InstReproducir) child;
                // Realizar acciones específicas para InstReproducir
                break;
            case "InstOrdenar":
                InstOrdenar instOrdenar = (InstOrdenar) child;
                // Realizar acciones específicas para InstOrdenar
                break;
            case "InstSumarizar":
                InstSumarizar instSumarizar = (InstSumarizar) child;
                // Realizar acciones específicas para InstSumarizar
                break;
            case "InstLongitud":
                InstLongitud instLongitud = (InstLongitud) child;
                // Realizar acciones específicas para InstLongitud
                break;
            case "OperationArithmetic":
                OperationArithmetic operationArithmetic = (OperationArithmetic) child;
                // Realizar acciones específicas para OperationArithmetic
                break;
            default:
                System.out.println("No se encontró una acción para el tipo de objeto.");
                break;
        }
        
        return null;
    }
    
    public Object runOpRational(){
        return null;
    }
    
    public Object runOpLogical(Node child){
        switch (child.getClass().getSimpleName()) {
            case "Primitive":
                Primitive primitive = (Primitive) child;
                // Realizar acciones específicas para Primitive
                break;
            case "Identifier":
                Identifier identifier = (Identifier) child;
                // Realizar acciones específicas para Identifier
                break;
            case "CallFunction":
                CallFunction callFunction = (CallFunction) child;
                // Realizar acciones específicas para CallFunction
                break;
            case "CallArreglo":
                CallArreglo callArreglo = (CallArreglo) child;
                // Realizar acciones específicas para CallArreglo
                break;
            case "InstReproducir":
                InstReproducir instReproducir = (InstReproducir) child;
                // Realizar acciones específicas para InstReproducir
                break;
            case "InstOrdenar":
                InstOrdenar instOrdenar = (InstOrdenar) child;
                // Realizar acciones específicas para InstOrdenar
                break;
            case "InstSumarizar":
                InstSumarizar instSumarizar = (InstSumarizar) child;
                // Realizar acciones específicas para InstSumarizar
                break;
            case "InstLongitud":
                InstLongitud instLongitud = (InstLongitud) child;
                // Realizar acciones específicas para InstLongitud
                break;
            case "AsignationVar":
                AsignationVar asignationVar = (AsignationVar) child;
                // Realizar acciones específicas para AsignationVar
                break;
            case "AsignationArray":
                AsignationArray asignationArray = (AsignationArray) child;
                // Realizar acciones específicas para AsignationArray
                break;
            case "DataArreglo":
                DataArreglo dataArreglo = (DataArreglo) child;
                // Realizar acciones específicas para DataArreglo
                break;
            case "DeclaracionArray":
                DeclaracionArray declaracionArray = (DeclaracionArray) child;
                // Realizar acciones específicas para DeclaracionArray
                break;
            case "DeclarationVar":
                DeclarationVar declarationVar = (DeclarationVar) child;
                // Realizar acciones específicas para DeclarationVar
                break;
            case "FunctionProc":
                FunctionProc functionProc = (FunctionProc) child;
                // Realizar acciones específicas para FunctionProc
                break;
            case "ListDeclaration":
                ListDeclaration listDeclaration = (ListDeclaration) child;
                // Realizar acciones específicas para ListDeclaration
                break;
            case "NodeFinally":
                NodeFinally nodeFinally = (NodeFinally) child;
                // Realizar acciones específicas para NodeFinally
                break;
            case "OperationArithmetic":
                OperationArithmetic operationArithmetic = (OperationArithmetic) child;
                // Realizar acciones específicas para OperationArithmetic
                break;
            case "OperationRational":
                OperationRational operationRational = (OperationRational) child;
                // Realizar acciones específicas para OperationRational
                break;
            case "OperationLogical":
                OperationLogical operationLogical = (OperationLogical) child;
                // Realizar acciones específicas para OperationLogical
                break;
            case "Principal":
                Principal principal = (Principal) child;
                // Realizar acciones específicas para Principal
                break;
            case "TrackNode":
                TrackNode trackNode = (TrackNode) child;
                // Realizar acciones específicas para TrackNode
                break;
            case "ConditionalCase":
                ConditionalCase conditionalCase = (ConditionalCase) child;
                // Realizar acciones específicas para ConditionalCase
                break;
            case "ConditionalHacerMientras":
                ConditionalHacerMientras conditionalHacerMientras = (ConditionalHacerMientras) child;
                // Realizar acciones específicas para ConditionalHacerMientras
                break;
            case "ConditionalMientras":
                ConditionalMientras conditionalMientras = (ConditionalMientras) child;
                // Realizar acciones específicas para ConditionalMientras
                break;
            case "ConditionalPara":
                ConditionalPara conditionalPara = (ConditionalPara) child;
                // Realizar acciones específicas para ConditionalPara
                break;
            case "ConditionalSi":
                ConditionalSi conditionalSi = (ConditionalSi) child;
                // Realizar acciones específicas para ConditionalSi
                break;
            case "ConditionalSino":
                ConditionalSino conditionalSino = (ConditionalSino) child;
                // Realizar acciones específicas para ConditionalSino
                break;
            case "ConditionalSinoSi":
                ConditionalSinoSi conditionalSinoSi = (ConditionalSinoSi) child;
                // Realizar acciones específicas para ConditionalSinoSi
                break;
            case "ConditionalSwitch":
                ConditionalSwitch conditionalSwitch = (ConditionalSwitch) child;
                // Realizar acciones específicas para ConditionalSwitch
                break;
            case "NodeDefault":
                NodeDefault nodeDefault = (NodeDefault) child;
                // Realizar acciones específicas para NodeDefault
                break;
            case "InstMensaje":
                InstMensaje instMensaje = (InstMensaje) child;
                // Realizar acciones específicas para InstMensaje
                break;
            case "InstEsperar":
                InstEsperar instEsperar = (InstEsperar) child;
                // Realizar acciones específicas para InstEsperar
                break;
            default:
                System.out.println("No se encontró una acción para el tipo de objeto.");
                break;
        }

        
        
        return null;
    }
}
