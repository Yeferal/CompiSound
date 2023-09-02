/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

/**
 *
 * @author Usuario
 */
public enum NodeType {
//    SIMBOLO,
    OP_ARITMETICO, 
    OP_RACIONAL, 
    OP_LOGICO, 
    DECLARACION_VAR, 
    DECLARACION_VAR_ARREGLO,
    ASIG_VAR, 
    ASIG_VAR_ARREGLO, 
    ASIG_INCREMENTAL, 
    ASIG_DECREMENTAL, 
    ASIG_SUM_SIMP,
    SI, 
    SINO_SI, 
    SINO, 
    SWITCH, 
    CASO, 
    SALIR, 
    DEFAULT, 
    PARA, 
    MIENTRAS, 
    HACER_MIENTRAS, 
    CONTINUAR, 
    FUNCION, 
    PROCEDIMIENTO, 
    RETURNAR, 
    PARAMETRO, 
    REPRODUCIR, 
    ESPERAR, 
    ORDENAR, 
    SUMARIZAR, 
    LONGITUD, 
    MENSAJE, 
    PRINCIPAL
}
