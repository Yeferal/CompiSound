/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tablesymbol;

import com.yeferal.desktopreproductor.ast.main.Node;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class SymbolFuncProc {
    private boolean function; // si no es un metodo
    private String id;
    DataType type;
    private List<Node> params;
    private List<Node> instructions;
}
