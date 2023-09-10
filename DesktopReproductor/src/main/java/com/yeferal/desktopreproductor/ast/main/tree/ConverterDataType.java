/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tree;

import com.yeferal.desktopreproductor.ast.main.ArithType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class ConverterDataType implements Serializable{
    
    /**
     * TipoVariable TipoValorAsignar    Resultado
     * entero       entero              entero
     * entero       cadena              error
     * entero       boolean             entero
     * entero       doble               entero
     * entero       caracter            entero ASCII
     * cadena       <CualquierTipo>     cadena
     * boolean      <CualquierTipo>     error
     * doble        entero              doble
     * doble        doble               doble
     * doble        cadena              error
     * doble        boolean             error
     * caracter     entero              caracter ASCII
     * caracter     caracter            caracter
     * caracter     <CualquierTipo>     error
     */
    
    
    public Object converterFromAsig(DataType typeVar, DataType typeAsig, Object value){
        if (null != typeVar) switch (typeVar) {
            case ENTERO:
                if (null != typeAsig) switch (typeAsig) {
                    case ENTERO:
                    case BOOLEAN:
                    case DOBLE:
                        //RETORNA ENTERO
                        return castEntero(value);
                    case CARACTER:
                        //RETORNA ENTERO ASCII
                        return castEntero(value);
                    case CADENA:
                        //Error
                        return null;
                    default:
                        //Error
                        return null;
                }   
                break;
            case CADENA:
                if (null != typeAsig) switch (typeAsig) {
                    case ENTERO:
                    case CADENA:
                    case BOOLEAN:
                    case DOBLE:
                    case CARACTER:
                        //RETORNA CADENA
                        return castCadena(value);
                    default:
                        //Error
                        return null;
                }   
                break;
            case BOOLEAN:
                if (null != typeAsig) switch (typeAsig) {
                    case BOOLEAN:
                        //RETORNA BOOLEAN
                        return castBoolean(value);
                    case ENTERO:
                    case CADENA:
                    case DOBLE:
                    case CARACTER:
                        //Error
                        return null;
                    default:
                        //Error
                        return null;
                }  
                break;
            case DOBLE:
                if (null != typeAsig) switch (typeAsig) {
                    case ENTERO:
                    case DOBLE:
                        //RETORNA DOBLE
                        return castDoble(value);
                    case CADENA:
                    case CARACTER:
                    case BOOLEAN:
                        //Error
                        return null;
                    default:
                        //Error
                        return null;
                }   
                break;
            case CARACTER:
                if (null != typeAsig) switch (typeAsig) {
                    case ENTERO:
                        //RETORNA CARACTER ASCII
                        return castChar(value);
                    case CARACTER:
                        //RETORNA CARACTER
                        return castChar(value);
                    case DOBLE:
                    case CADENA:
                    case BOOLEAN:
                        //Error
                        return null;
                    default:
                        //Error
                        return null;
                }   
                break;
            default:
                //Error
                return null;
        }
        
        return null;
    }
    
    
    public boolean isConverterOpArith(DataType typeVar1, DataType typeVar2, ArithType arithType){
        switch(arithType){
            case ADD:
                return true;
            case SUBTRAC:
            case MULTI:
            case DIV:
            case MOD:
            case POW:
                if(((typeVar1 == DataType.ENTERO || typeVar1 == DataType.DOBLE) && (typeVar2 == DataType.ENTERO || typeVar2 == DataType.DOBLE))){
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
    
    public Object operateArith(Object val1, Object val2, DataType typeVar1, DataType typeVar2, ArithType arithType){
        switch(arithType){
            case ADD:
                if (typeVar1 == DataType.CADENA && typeVar2 == DataType.CADENA) {
                    return (String) (castCadena(val1) + castCadena(val2));
                }else if (typeVar1 == DataType.CADENA && typeVar2 == DataType.DOBLE) {
                    return (String) (castCadena(val1) + castDoble(val2));
                }else if (typeVar1 == DataType.CADENA && typeVar2 == DataType.ENTERO) {
                    return (String) (castCadena(val1) + castEntero(val2));
                }else if (typeVar1 == DataType.CADENA && typeVar2 == DataType.CARACTER) {
                    return (String) (castCadena(val1) + castChar(val2));
                }else if (typeVar1 == DataType.CADENA && typeVar2 == DataType.BOOLEAN) {
                    return (String) (castCadena(val1) + castBoolean(val2));
                }
                
                
                else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.CADENA) {
                    return (String) (castDoble(val1) + castCadena(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (double) (castDoble(val1) + castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (double) (castDoble(val1) + castEntero(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.CARACTER) {
                    return (double) (castDoble(val1) + castChar(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.BOOLEAN) {
                    return (String) (castDoble(val1) + (castBoolean(val2)? "true" : "false"));
                }
                
                else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.CADENA) {
                    return (String) (castEntero(val1) + castCadena(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (double) (castEntero(val1) + castDoble(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    return (int) (castEntero(val1) + castEntero(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.CARACTER) {
                    return (int) (castEntero(val1) + castChar(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.BOOLEAN) {
                    return (int) (castEntero(val1) + (castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.CADENA) {
                    return (String) (castChar(val1) + castCadena(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.DOBLE) {
                    return (double) (castChar(val1) + castDoble(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.ENTERO) {
                    return (int) (castChar(val1) + castEntero(val2));
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.CARACTER) {
                    return (String) (castChar(val1) + castChar(val2) + "");
                }else if (typeVar1 == DataType.CARACTER && typeVar2 == DataType.BOOLEAN) {
                    return (int) (castChar(val1) + (castBoolean(val2)? 1 : 0));
                }
                
                else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.CADENA) {
                    return (String) ((castBoolean(val2)? "true" : "false") + castCadena(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.DOBLE) {
                    return (double) ((castBoolean(val1)? 1 : 0) + castDoble(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.ENTERO) {
                    return (int) ((castBoolean(val1)? 1 : 0) + castEntero(val2));
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.CARACTER) {
                    return (String) ((castBoolean(val1)? 1 : 0) + castChar(val2) + "");
                }else if (typeVar1 == DataType.BOOLEAN && typeVar2 == DataType.BOOLEAN) {
                    return (int) ((castBoolean(val1)? 1 : 0) + (castBoolean(val2)? 1 : 0));
                }
                
                return null;
            case SUBTRAC:
                if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    return (int) (castEntero(val1) - castEntero(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (double) (castEntero(val1) - castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (double) (castDoble(val1) - castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (double) (castDoble(val1) - castEntero(val2));
                }
                return null;
            case MULTI:
                if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    return (int) (castEntero(val1) * castEntero(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (double) (castEntero(val1) * castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (double) (castDoble(val1) * castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (double) (castDoble(val1) * castEntero(val2));
                }
                return null;
            case DIV:
                if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    double resultDiv = (double) (castDoble(val1) / castDoble(val2));
                    return (resultDiv != (int) resultDiv)? resultDiv : (int) resultDiv;
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (double) (castEntero(val1) / castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (double) (castDoble(val1) / castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (double) (castDoble(val1) / castEntero(val2));
                }
                return null;
            case MOD:
                if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    double resultDiv = (double) (castDoble(val1) % castDoble(val2));
                    return (resultDiv != (int) resultDiv)? resultDiv : (int) resultDiv;
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (double) (castEntero(val1) % castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (double) (castDoble(val1) % castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (double) (castDoble(val1) % castEntero(val2));
                }
                return null;
            case POW:
                if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.ENTERO) {
                    return (int) Math.pow(castEntero(val1), castEntero(val2));
                }else if (typeVar1 == DataType.ENTERO && typeVar2 == DataType.DOBLE) {
                    return (double) Math.pow(castEntero(val1), castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.DOBLE) {
                    return (double) (castDoble(val1) - castDoble(val2));
                }else if (typeVar1 == DataType.DOBLE && typeVar2 == DataType.ENTERO) {
                    return (double) Math.pow(castDoble(val1), castEntero(val2));
                }
                return null;
            default:
                return null;
        }
    }
    
    public DataType getTypeData(DataType typeVar1, DataType typeVar2, ArithType arithType){
        
        if (typeVar1 == DataType.CADENA || typeVar2 == DataType.CADENA) {
            return DataType.CADENA;
        }else if (typeVar1 == DataType.DOBLE || typeVar2 == DataType.DOBLE) {
            return DataType.DOBLE;
        }else if (typeVar1 == DataType.ENTERO || typeVar2 == DataType.ENTERO) {
            if (arithType == ArithType.DIV || arithType == ArithType.MOD) {
                return DataType.DOBLE;
            }
            return DataType.ENTERO;
        }else if (typeVar1 == DataType.CARACTER || typeVar2 == DataType.CARACTER) {
            return DataType.CARACTER;
        }else if (typeVar1 == DataType.BOOLEAN || typeVar2 == DataType.BOOLEAN) {
            return DataType.BOOLEAN;
        }
        return null;
    }
    
    
    public int castEntero(Object value){
        if (value instanceof Boolean) {
            return ((Boolean) value) ? 1 : 0;
        } else if (value instanceof Character) {
            return (int) ((Character) value); // Convierte Character a int (valor ASCII)
        } else if (value instanceof Number) {
            return ((Number) value).intValue(); // Convierte Number a int
        }
        throw new IllegalArgumentException("No se puede convertir el valor a int");
    }
    
    public String castCadena(Object value){
        return String.valueOf(value);
    }
    
    public boolean castBoolean(Object value){
        return (boolean) value;
    }
    
    public double castDoble(Object value){
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue(); // Convierte Integer a double
        } else if (value instanceof Double) {
            return ((Double) value).doubleValue(); // Convierte Double a double
        } else {
            throw new IllegalArgumentException("No se puede convertir el valor a double");
        }
    }
    
    public char castChar(Object value){
        if (value instanceof Character) {
            return ((Character) value).charValue(); // Convierte Character a char
        } else if (value instanceof Integer) {
            int intValue = (int) value;
            if (intValue >= 0 && intValue <= 65535) {
                return (char) intValue; // Convierte int a char (valor ASCII)
            }
        }
        throw new IllegalArgumentException("No se puede convertir el valor a char");
    }
    
    public void testData(){
        System.out.println("Con ENTEROS");
        System.out.println(converterFromAsig(DataType.ENTERO, DataType.ENTERO, 5));
        System.out.println(converterFromAsig(DataType.CADENA, DataType.ENTERO, 5));
        System.out.println(converterFromAsig(DataType.BOOLEAN, DataType.ENTERO, 5));
        System.out.println(converterFromAsig(DataType.DOBLE, DataType.ENTERO, 5));
        System.out.println(converterFromAsig(DataType.CARACTER, DataType.ENTERO, 53));
        
        System.out.println("Con CADENA");
        System.out.println(converterFromAsig(DataType.ENTERO, DataType.CADENA, "5"));
        System.out.println(converterFromAsig(DataType.CADENA, DataType.CADENA, "5"));
        System.out.println(converterFromAsig(DataType.BOOLEAN, DataType.CADENA, "5"));
        System.out.println(converterFromAsig(DataType.DOBLE, DataType.CADENA, "5"));
        System.out.println(converterFromAsig(DataType.CARACTER, DataType.CADENA, "5"));
        
        System.out.println("Con BOOLEAN");
        System.out.println(converterFromAsig(DataType.ENTERO, DataType.BOOLEAN, true));
        System.out.println(converterFromAsig(DataType.CADENA, DataType.BOOLEAN, true));
        System.out.println(converterFromAsig(DataType.BOOLEAN, DataType.BOOLEAN, true));
        System.out.println(converterFromAsig(DataType.DOBLE, DataType.BOOLEAN, true));
        System.out.println(converterFromAsig(DataType.CARACTER, DataType.BOOLEAN, true));
        
        System.out.println("Con DOBLE");
        System.out.println(converterFromAsig(DataType.ENTERO, DataType.DOBLE, 5.51));
        System.out.println(converterFromAsig(DataType.CADENA, DataType.DOBLE, 5.51));
        System.out.println(converterFromAsig(DataType.BOOLEAN, DataType.DOBLE, 5.51));
        System.out.println(converterFromAsig(DataType.DOBLE, DataType.DOBLE, 5.51));
        System.out.println(converterFromAsig(DataType.CARACTER, DataType.DOBLE, 5.51));
        
        System.out.println("Con CHAR");
        System.out.println(converterFromAsig(DataType.ENTERO, DataType.CARACTER, '5'));
        System.out.println(converterFromAsig(DataType.CADENA, DataType.CARACTER, '5'));
        System.out.println(converterFromAsig(DataType.BOOLEAN, DataType.CARACTER, '5'));
        System.out.println(converterFromAsig(DataType.DOBLE, DataType.CARACTER, '5'));
        System.out.println(converterFromAsig(DataType.CARACTER, DataType.CARACTER, '5'));
    }
}
