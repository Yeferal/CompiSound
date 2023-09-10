/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.DataArreglo;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CallArreglo extends Node{
    private String id;
    private List<Node> dimensions;

    public CallArreglo(String id, List<Node> dimensions, PositionToken positionToken, DataType type) {
        super(positionToken, type);
        this.id = id;
        this.dimensions = dimensions;
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
    
    public int getDimTotal(List<Integer> list){
        int result = 1;
        for (Integer dim : list) {
            result = result * dim;
        }
        return result;
    }
    
    public boolean isDimRange(List<Integer> list, List<Integer> dims){
        if (list.size() == dims.size()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)<dims.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    
    @Override
    public Object execute(Environment env) {
        //Buscamos la funcion si existe
        Symbol symbol = env.getTableSymbol().searchSymbolArreglo(id, env.currentAmbit);
        if (symbol == null) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "No sea a declarado un arreglo con el nombre "+id+"."));
            return null;
        }
        
        //Verificar su tamanio con los datos de la dimension. si estam dentro del rango
        List<Integer> tempDims = new ArrayList<>();
        for (Node dimension : dimensions) {
            Object result = dimension.execute(env);
            if (dimension.getType() != DataType.ENTERO) {
                env.getErrorsSemantic().add(new ErrorGramm(dimension.getPositionToken(),ErrorType.SEMANTIC, id, "Error en la dimension del arreglo:"+id+", con cumple con la dimension del arreglo."));
                return null;
            }
            
            if (result instanceof Integer) {
                tempDims.add((Integer) result);
            }else {
                env.getErrorsSemantic().add(new ErrorGramm(dimension.getPositionToken(),ErrorType.SEMANTIC, id, "Error en la dimension del arreglo:"+id+", el resultado obtenido no es un entero."));
                return null;
            }
        }
        
        
        if (!isDimRange(symbol.getListDim(), tempDims)) {
            env.getErrorsSemantic().add(new ErrorGramm(getPositionToken(),ErrorType.SEMANTIC, id, "Error en la dimension del arreglo:"+id+", las dimensiones no cumplen con la dimension maxima."));
            return null;
        }
        //Obtener el dato de la variable[]...
        setType(symbol.getType());
        int position = calculatedDirStack(env, symbol.getListDim(), tempDims);
        Object data = symbol.searchValueArrayFromPosition(env, position);
        
        return data;
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
