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
import com.yeferal.desktopreproductor.ast.main.Identifier;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.instructions.notas.OrderType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.Symbol;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class InstOrdenar extends Node{
    private Node arreglo;
    private OrderType orderType;

    public InstOrdenar(Node arreglo, OrderType orderType, PositionToken positionToken) {
        super(positionToken, DataType.ENTERO);
        this.arreglo = arreglo;
        this.orderType = orderType;
    }

    public Node getArreglo() {
        return arreglo;
    }

    public void setArreglo(Node arreglo) {
        this.arreglo = arreglo;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    
    public int runTreeDataArray(Environment env, List<Node> listNode){
        int levelFlag = 0;
        if (listNode!=null) {
            for (int i = 0; i < listNode.size(); i++) {
                if (listNode.get(i) instanceof DataArreglo) {
                    DataArreglo da = (DataArreglo) listNode.get(i);
                    if (i == 0) {
                        levelFlag = runTreeDataArray(env, da.getContentList()) + 1;
                        
                    }else {
                        int level = runTreeDataArray(env, da.getContentList()) + 1;
                        if (levelFlag != level) {
                            env.getErrorsSemantic().add(new ErrorGramm(listNode.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "La jerarquia de los valores de asignacion del arreglo son incorrectos."));
                            break;
                        }
                    }
                }else {
                    return 1;
                }
            }
        }
        return levelFlag;
    }
    
    private List<Integer> getDimArray(Environment env, List<Node> listNode){
        List<Integer> listDimTemp = new ArrayList<>();
        int dimCount = 0;
        if (listNode!=null) {
            for (int i = 0; i < listNode.size(); i++) {
                if (listNode.get(i) instanceof DataArreglo) {
                    DataArreglo da = (DataArreglo) listNode.get(i);
                    if (i == 0) {
                        dimCount = da.getContentList().size();
                        List<Integer> listTemp = getDimArray(env, da.getContentList());
                        listDimTemp = listTemp;
                    }else {
                        if (dimCount != da.getContentList().size()) {
                            env.getErrorsSemantic().add(new ErrorGramm(listNode.get(i).getPositionToken(),ErrorType.SEMANTIC, "", "La las dimensiones del arreglo no coincidem."));
                            break;
                        }
                        getDimArray(env, da.getContentList());
                    }
                }else {
                    dimCount = listNode.size();
                    listDimTemp.add(dimCount);
                    return listDimTemp;
                }
            }
            
        }
        listDimTemp.add(listNode.size());
        
        return listDimTemp;
    }
    
    private List<Object> getDataArray(Environment env, List<Node> listNode){
        List<Object> listDimTemp = new ArrayList<>();
        if (listNode!=null) {
            for (int i = 0; i < listNode.size(); i++) {
                if (listNode.get(i) instanceof DataArreglo) {
                    DataArreglo da = (DataArreglo) listNode.get(i);
                    List<Object> listTemp = getDataArray(env, da.getContentList());
                    listDimTemp.addAll(listTemp);
                }else {
                    if (listNode.get(i) instanceof Identifier) {
                        Symbol s = (Symbol) listNode.get(i).execute(env);
                        listDimTemp.add(s.getValue());
                    }else {
                        Object obj = listNode.get(i).execute(env);
                        listDimTemp.add(obj);
                    }
                }
            }
        }
        return listDimTemp;
    }
    
    @Override
    public Object execute(Environment env) {
        setType(DataType.ENTERO);
        
        if (arreglo instanceof DataArreglo) {
            DataArreglo dataArreglo = (DataArreglo) arreglo ;
            runTreeDataArray(env, dataArreglo.getContentList());
            List<Object> listData = getDataArray(env, dataArreglo.getContentList());
            //ENVIAR A ORDENAR;
            if (listData.size()>0 && ((listData.get(0) instanceof Double) || (listData.get(0) instanceof Integer))) {
                return order(listData, DataType.ENTERO);
            }else if(listData.size()>0 && (listData.get(0) instanceof Character) ) {
                return order(listData, DataType.CARACTER);
            }
            return 0;
        } else{
            Object valAsignation = arreglo.execute(env);
            Object valAsigT = valAsignation;
            
            if (valAsigT == null) {
                env.getErrorsSemantic().add(new ErrorGramm(arreglo.getPositionToken(),ErrorType.SEMANTIC, "Ordenar", "El valor del parametro es nulo."));
                    return 0;
            }
            
            if (arreglo instanceof Identifier) {
                Symbol s = (Symbol) valAsignation;
                valAsigT = s.getValue();
                if (s.getRol() != DataType.ARREGLO) {
                    env.getErrorsSemantic().add(new ErrorGramm(arreglo.getPositionToken(),ErrorType.SEMANTIC, "Ordenar", "El valor "+s.getName()+" no es un arreglo."));
                    return 0;
                }
            }
            
            if (valAsigT == null) {
                env.getErrorsSemantic().add(new ErrorGramm(arreglo.getPositionToken(),ErrorType.SEMANTIC, "Ordenar", "El valor del parametro es nulo."));
                    return 0;
            }
            
            
            //ENVIAR A ORDENAR;
            List<Object> listData = (List<Object>) valAsigT;
            //ENVIAR A ORDENAR;
            if (listData.size()>0 && ((listData.get(0) instanceof Double) || (listData.get(0) instanceof Integer))) {
                return order(listData, DataType.ENTERO);
            }else if(listData.size()>0 && (listData.get(0) instanceof Character) ) {
                return order(listData, DataType.CARACTER);
            }
        }

        return 0;
    }
    
    public int order(List<Object> listData, DataType dataType){
        switch(orderType){
            case ASCENDENTE:
                return orderASC(listData, dataType);
            case DESCENDENTE:
                return orderDES(listData, dataType);
            case PARES:
                return orderPAR(listData, dataType);
            case IMPARES:
                return ordeIMP(listData, dataType);
            case PRIMOS:
                return orderPRIM(listData, dataType);
        }
        return 0;
    }
    
    public int orderASC(List<Object> listData, DataType dataType){
        try {
            Collections.sort(listData, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    if (o1 instanceof Integer && o2 instanceof Integer) {
                        return Integer.compare((int) o1, (int) o2);
                    } else if (o1 instanceof Double && o2 instanceof Double) {
                        return Double.compare((double) o1, (double) o2);
                    } else if (o1 instanceof Character && o2 instanceof Character) {
                        return Character.compare((char) o1, (char) o2);
                    }
                    return 0; // Manejar otros tipos de objetos si es necesario
                }
            });
            return 1; // El ordenamiento fue exitoso
        } catch (Exception e) {
            return 0; // El ordenamiento falló
        }
    }
    
    public int orderDES(List<Object> listData, DataType dataType){
        try {
            Collections.sort(listData, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    if (o1 instanceof Integer && o2 instanceof Integer) {
                        return Integer.compare((int) o1, (int) o2);
                    } else if (o1 instanceof Double && o2 instanceof Double) {
                        return Double.compare((double) o1, (double) o2);
                    } else if (o1 instanceof Character && o2 instanceof Character) {
                        return Character.compare((char) o1, (char) o2);
                    }
                    return 0; // Manejar otros tipos de objetos si es necesario
                }
            });
            Collections.sort(listData, Collections.reverseOrder());
            return 1; // El ordenamiento fue exitoso
        } catch (Exception e) {
            return 0; // El ordenamiento falló
        }
    }
    
    public int orderPAR(List<Object> listData, DataType dataType){
        try {
            Collections.sort(listData, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    boolean esPar1 = o1 instanceof Integer && (int) o1 % 2 == 0;
                    boolean esPar2 = o2 instanceof Integer && (int) o2 % 2 == 0;
                    
                    if (esPar1 && !esPar2) {
                        return -1; // o1 es par, o2 es impar, o1 viene antes
                    } else if (!esPar1 && esPar2) {
                        return 1; // o1 es impar, o2 es par, o2 viene antes
                    } else {
                        return 0; // Ambos son pares o ambos son impares, no cambia el orden
                    }
                }
            });
            return 1; // El ordenamiento fue exitoso
        } catch (Exception e) {
            return 0; // El ordenamiento falló
        }
    }
    
    public int ordeIMP(List<Object> listData, DataType dataType){
        try {
            Collections.sort(listData, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    boolean esPar1 = o1 instanceof Integer && (int) o1 % 2 == 0;
                    boolean esPar2 = o2 instanceof Integer && (int) o2 % 2 == 0;
                    
                    if (!esPar1 && esPar2) {
                        return -1; // o1 es impar, o2 es par, o1 viene antes
                    } else if (esPar1 && !esPar2) {
                        return 1; // o1 es par, o2 es impar, o2 viene antes
                    } else {
                        return 0; // Ambos son pares o ambos son impares, no cambia el orden
                    }
                }
            });
            return 1; // El ordenamiento fue exitoso
        } catch (Exception e) {
            return 0; // El ordenamiento falló
        }
    }
    
    public int orderPRIM(List<Object> listData, DataType dataType){
        try {
            Collections.sort(listData, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    boolean isPrimo1 = o1 instanceof Integer && isPrimo((int) o1);
                    boolean isPrimo2 = o2 instanceof Integer && isPrimo((int) o2);
                    
                    if (isPrimo1 && !isPrimo2) {
                        return -1; // o1 es primo, o2 no es primo, o1 viene antes
                    } else if (!isPrimo1 && isPrimo2) {
                        return 1; // o1 no es primo, o2 es primo, o2 viene antes
                    } else {
                        return 0; // Ambos son primos o ambos no son primos, no cambia el orden
                    }
                }
            });
            return 1; // El ordenamiento fue exitoso
        } catch (Exception e) {
            return 0; // El ordenamiento falló
        }
    }
    
    public static boolean isPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
