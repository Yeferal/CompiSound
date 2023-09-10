/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tree;

import com.yeferal.desktopreproductor.ast.main.ArithType;
import com.yeferal.desktopreproductor.ast.main.AsignationArray;
import com.yeferal.desktopreproductor.ast.main.AsignationVar;
import com.yeferal.desktopreproductor.ast.main.DataArreglo;
import com.yeferal.desktopreproductor.ast.main.DeclaracionArray;
import com.yeferal.desktopreproductor.ast.main.DeclarationVar;
import com.yeferal.desktopreproductor.ast.main.FunctionProc;
import com.yeferal.desktopreproductor.ast.main.Identifier;
import com.yeferal.desktopreproductor.ast.main.ListDeclaration;
import com.yeferal.desktopreproductor.ast.main.LogicalTypes;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.NodeFinally;
import com.yeferal.desktopreproductor.ast.main.OperationArithmetic;
import com.yeferal.desktopreproductor.ast.main.OperationLogical;
import com.yeferal.desktopreproductor.ast.main.OperationRational;
import com.yeferal.desktopreproductor.ast.main.Primitive;
import com.yeferal.desktopreproductor.ast.main.Principal;
import com.yeferal.desktopreproductor.ast.main.RationalTypes;
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
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.TableSymbol;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class RunOperator implements Serializable{
    private ConverterDataType cdt = new ConverterDataType();
    
    public boolean runOpRational(Object val1, Object val2, DataType typeVar1, DataType typeVar2, RationalTypes rationalType){
        switch(rationalType){
            case EQUAL:// ==
                if (typeVar1 == DataType.CADENA || typeVar2 == DataType.CADENA) {
                    return val1.equals(val2);
                }
                return val1 == val2;
            case NOT_EQUAL:// !=
                if (typeVar1 == DataType.CADENA || typeVar2 == DataType.CADENA) {
                    return !val1.equals(val2);
                }
                return val1 != val2;
            case GRATE_THAN:// >
                if (typeVar1 == DataType.CADENA || typeVar2 == DataType.CADENA) {
                    return (val1+"").length() > (val1+"").length();
                }
                
                else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (cdt.castDoble(val1) > cdt.castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (cdt.castDoble(val1) > cdt.castEntero(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.CARACTER) {
                    return (cdt.castDoble(val1) > cdt.castChar(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castDoble(val1) > (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (cdt.castEntero(val1) > cdt.castDoble(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    return (cdt.castEntero(val1) > cdt.castEntero(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.CARACTER) {
                    return (cdt.castEntero(val1) > cdt.castChar(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castEntero(val1) > (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.DOBLE) {
                    return (cdt.castChar(val1) > cdt.castDoble(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.ENTERO) {
                    return (cdt.castChar(val1) > cdt.castEntero(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.CARACTER) {
                    return (cdt.castChar(val1) > cdt.castChar(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castChar(val1) > (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.DOBLE) {
                    return ((cdt.castBoolean(val1)? 1 : 0) > cdt.castDoble(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.ENTERO) {
                    return ((cdt.castBoolean(val1)? 1 : 0) > cdt.castEntero(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.CARACTER) {
                    return ((cdt.castBoolean(val1)? 1 : 0) > cdt.castChar(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.BOOLEAN) {
                    return ((cdt.castBoolean(val1)? 1 : 0) > (cdt.castBoolean(val2)? 1 : 0));
                }
                return false;
            case GRATE_THAN_EQUAL:// >=
                if (typeVar1 == DataType.CADENA || typeVar2 == DataType.CADENA) {
                    return (val1+"").length() >= (val1+"").length();
                }
                
                else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (cdt.castDoble(val1) >= cdt.castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (cdt.castDoble(val1) >= cdt.castEntero(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.CARACTER) {
                    return (cdt.castDoble(val1) >= cdt.castChar(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castDoble(val1) >= (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (cdt.castEntero(val1) >= cdt.castDoble(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    return (cdt.castEntero(val1) >= cdt.castEntero(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.CARACTER) {
                    return (cdt.castEntero(val1) >= cdt.castChar(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castEntero(val1) >= (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.DOBLE) {
                    return (cdt.castChar(val1) >= cdt.castDoble(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.ENTERO) {
                    return (cdt.castChar(val1) >= cdt.castEntero(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.CARACTER) {
                    return (cdt.castChar(val1) >= cdt.castChar(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castChar(val1) >= (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.DOBLE) {
                    return ((cdt.castBoolean(val1)? 1 : 0) >= cdt.castDoble(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.ENTERO) {
                    return ((cdt.castBoolean(val1)? 1 : 0) >= cdt.castEntero(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.CARACTER) {
                    return ((cdt.castBoolean(val1)? 1 : 0) >= cdt.castChar(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.BOOLEAN) {
                    return ((cdt.castBoolean(val1)? 1 : 0) >= (cdt.castBoolean(val2)? 1 : 0));
                }
                return false;
            case LESS_THAN:// <
                if (typeVar1 == DataType.CADENA || typeVar2 == DataType.CADENA) {
                    return (val1+"").length() <= (val1+"").length();
                }
                
                else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (cdt.castDoble(val1) <= cdt.castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (cdt.castDoble(val1) <= cdt.castEntero(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.CARACTER) {
                    return (cdt.castDoble(val1) <= cdt.castChar(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castDoble(val1) <= (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (cdt.castEntero(val1) <= cdt.castDoble(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    return (cdt.castEntero(val1) <= cdt.castEntero(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.CARACTER) {
                    return (cdt.castEntero(val1) <= cdt.castChar(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castEntero(val1) <= (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.DOBLE) {
                    return (cdt.castChar(val1) <= cdt.castDoble(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.ENTERO) {
                    return (cdt.castChar(val1) <= cdt.castEntero(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.CARACTER) {
                    return (cdt.castChar(val1) <= cdt.castChar(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castChar(val1) <= (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.DOBLE) {
                    return ((cdt.castBoolean(val1)? 1 : 0) <= cdt.castDoble(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.ENTERO) {
                    return ((cdt.castBoolean(val1)? 1 : 0) <= cdt.castEntero(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.CARACTER) {
                    return ((cdt.castBoolean(val1)? 1 : 0) <= cdt.castChar(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.BOOLEAN) {
                    return ((cdt.castBoolean(val1)? 1 : 0) <= (cdt.castBoolean(val2)? 1 : 0));
                }
                return false;
            case LESS_THAN_EQUAL:// <=
                if (typeVar1 == DataType.CADENA || typeVar2 == DataType.CADENA) {
                    return (val1+"").length() < (val1+"").length();
                }
                
                else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (cdt.castDoble(val1) < cdt.castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (cdt.castDoble(val1) < cdt.castEntero(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.CARACTER) {
                    return (cdt.castDoble(val1) < cdt.castChar(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castDoble(val1) < (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (cdt.castEntero(val1) < cdt.castDoble(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    return (cdt.castEntero(val1) < cdt.castEntero(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.CARACTER) {
                    return (cdt.castEntero(val1) < cdt.castChar(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castEntero(val1) < (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.DOBLE) {
                    return (cdt.castChar(val1) < cdt.castDoble(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.ENTERO) {
                    return (cdt.castChar(val1) < cdt.castEntero(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.CARACTER) {
                    return (cdt.castChar(val1) < cdt.castChar(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.BOOLEAN) {
                    return (cdt.castChar(val1) < (cdt.castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.DOBLE) {
                    return ((cdt.castBoolean(val1)? 1 : 0) < cdt.castDoble(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.ENTERO) {
                    return ((cdt.castBoolean(val1)? 1 : 0) < cdt.castEntero(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.CARACTER) {
                    return ((cdt.castBoolean(val1)? 1 : 0) < cdt.castChar(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.BOOLEAN) {
                    return ((cdt.castBoolean(val1)? 1 : 0) < (cdt.castBoolean(val2)? 1 : 0));
                }
                return false;
            case IS_NULL:// !!
                return val1!=null;
        }
        
        return false;
    }
    
    public boolean runOpLogical(Object val1, Object val2, DataType typeVar1, DataType typeVar2, LogicalTypes logicalTypes){
        switch(logicalTypes){
            case AND:
                return ((boolean)val1 && (boolean)val2);
            case NAND:
                return !((boolean)val1 && (boolean)val2);
            case OR:
                return ((boolean)val1 || (boolean)val2);
            case NOR:
                return !((boolean)val1 || (boolean)val2);
            case XOR:
                return ((boolean)val1 ^ (boolean)val2);
            case NOT:
                return !((boolean)val1);
            default:
                return false;
        }
    }
    
    
}
