/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DataArreglo extends Node{
    private List<Node> contentList;

    public DataArreglo(List<Node> contentList, PositionToken positionToken) {
        super(positionToken, null);
        this.contentList = contentList;
    }

    public List<Node> getContentList() {
        return contentList;
    }

    public void setContentList(List<Node> contentList) {
        this.contentList = contentList;
    }
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
