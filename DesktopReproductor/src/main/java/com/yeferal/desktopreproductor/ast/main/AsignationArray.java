/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.ConverterDataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class AsignationArray extends Node{
    private String id;
    private List<Node> dimensions;
    private int dimesionsPos;
    private Node asignation;
    private ConverterDataType cdt = new ConverterDataType();

    public AsignationArray(String id, List<Node> dimensions, Node asignation, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.id = id;
        this.dimensions = dimensions;
        this.asignation = asignation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Node> dimensions) {
        this.dimensions = dimensions;
    }

    public int getDimesionsPos() {
        return dimesionsPos;
    }

    public void setDimesionsPos(int dimesionsPos) {
        this.dimesionsPos = dimesionsPos;
    }

    public Node getAsignation() {
        return asignation;
    }

    public void setAsignation(Node asignation) {
        this.asignation = asignation;
    }
    
    public Deque<Integer> setAmbitPileS(Deque<Integer> ambitsPile){
        Deque<Integer> tempPile = new LinkedList<>(ambitsPile);
        return tempPile;
    }

    @Override
    public Object execute(Environment env) {
        Symbol symbol = env.getTableSymbol().searchSymbol(id, env.currentAmbit);
        if (symbol == null || symbol.getRol() != DataType.ARREGLO) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "No sea a declarado un arreglo con el nombre "+id+"."));
            return null;
        }
        
        setType(symbol.getType());
        
        if (asignation == null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "No se aceptan valores nulos en la asignacion."));
            return null;
        }
        
        
        Object valAsignation = asignation.execute(env);
        Object valAsigT = valAsignation;
        if (asignation instanceof Identifier) {
            Symbol s = (Symbol) valAsignation;
            valAsigT = s.getValue();
        }
        if (valAsigT == null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "No se aceptan valores nulos en la asignacion."));
            return null;
        }
        
        Object valConverter = cdt.converterFromAsig(getType(), asignation.getType(), valAsigT);
        if (valConverter==null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "El tipo de dato no conicide con el tipo de la variable."));
            return null;
        }
        if (dimensions != null && dimensions.size()>0) {
            boolean dimEmpty = isDimNull(dimensions);
            boolean dimFull = isDimNotNull(dimensions);
            if (!dimFull && !dimEmpty) {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Dimensiones incorrectas del arreglo."));
            }else {
                List<Integer> listDim = new ArrayList<>();
                for (Node dimension : dimensions) {
                    Object valDim = dimension.execute(env);
                    if (dimension instanceof Identifier) {
                        if (dimension.getType() == DataType.ENTERO) {
                            Object valConverter2 = cdt.castEntero(valDim);
                            listDim.add((Integer) valConverter2);
                        }else {
                            env.getErrorsSemantic().add(new ErrorGramm(dimension.getPositionToken(),ErrorType.SEMANTIC, "", "El valor de la dimension no es un entero."));
                            break;
                        }
                    }else {
                        if (dimension.getType() == DataType.ENTERO || dimension.getType() == DataType.DOBLE) {
                            Object valConverter2 = cdt.castEntero(valDim);
                            listDim.add((Integer) valConverter2);
                        }else {
                            env.getErrorsSemantic().add(new ErrorGramm(dimension.getPositionToken(),ErrorType.SEMANTIC, "", "El valor de la dimension no es un entero."));
                            break;
                        }
                    }

                }
                System.out.println(listDim);
                if (asignation != null) {
                    dimesionsPos = calculatedDirStack(env, symbol.getListDim(), listDim);
//                    System.out.println(dimesionsPos+"________________dfsfsd: "+symbol.getValue());
                    List<Object> newVal = (List<Object>) symbol.getValue();
                    newVal.set(dimesionsPos, valAsigT);
//                    System.out.println(symbol.getValue().toString());
                    env.getTableSymbol().setValueSymbol(id, env.currentAmbit, newVal);
                }else {
                    env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, "", "Para poder asignar datos a un arreglo la asignacion no tiene que nula."));

                }
            }
        }
        
        return null;
    }
    
    private boolean isDimNull(List<Node> listDims){
        boolean isNull = listDims.get(0)==null;
        
        for (int i = 1; i < listDims.size(); i++) {
            if (listDims.get(i) != null) {
                isNull = false;
                break;
            }
        }
        return isNull;
    }
    
    private boolean isDimNotNull(List<Node> listDims){
        boolean isNotNull = listDims.get(0)!=null;
        
        for (int i = 1; i < listDims.size(); i++) {
            if (listDims.get(i) == null) {
                isNotNull = false;
                break;
            }
        }
        return isNotNull;
    }
    
    int calculatedDirStack(Environment env, List<Integer> tamanosDimensiones, List<Integer> coordenadas) {
        if (tamanosDimensiones.size() != coordenadas.size()) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getId(), "Los valores de las coordenadas superan las dimensiones del arreglo."));
            return 0;
        }

        int direccion = 0;
        int multiplicador = 1; // Inicializar el multiplicador en 1

        for (int i = tamanosDimensiones.size() - 1; i >= 0; i--) {
            int tamañoDimension = tamanosDimensiones.get(i);
            int coordenada = coordenadas.get(i);

            if (coordenada < 0 || coordenada >= tamañoDimension) {
                env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, getId(), "Las Coordenadas superan la dimension del arreglo."));
                return 0;
            }

            direccion += coordenada * multiplicador; // Sumar el producto de coordenada y multiplicador
            multiplicador *= tamañoDimension; // Actualizar el multiplicador para la siguiente dimensión
        }

        return direccion;
    }
    
}
