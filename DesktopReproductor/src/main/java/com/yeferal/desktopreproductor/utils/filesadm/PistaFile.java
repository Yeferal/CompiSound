/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.utils.filesadm;

import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PistaFile implements Serializable {
    private String name;
    private String inputText;
//    private Environment environment;
    private Node nodeRoot;
    private List<Object> listExecutes = new ArrayList<>();

    public PistaFile(String name, String inputText, Node nodeRoot) {
        this.name = name;
        this.inputText = inputText;
//        this.environment = environment;
        this.nodeRoot = nodeRoot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

//    public Environment getEnvironment() {
//        return environment;
//    }
//
//    public void setEnvironment(Environment environment) {
//        this.environment = environment;
//    }

    public Node getNodeRoot() {
        return nodeRoot;
    }

    public void setNodeRoot(Node nodeRoot) {
        this.nodeRoot = nodeRoot;
    }
    
    public List<Object> getListExecutes() {
        return listExecutes;
    }

    public void setListExecutes(List<Object> listExecutes) {
        this.listExecutes = listExecutes;
    }
}
