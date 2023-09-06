/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tree;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.TableSymbol;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Usuario
 */
public class Environment {
    private TableSymbol tableSymbol;
    private List<ErrorGramm> errorsLexical, errorsSyntact, errorsSemantic;
    private Stack<Integer> pileAmbit;
    public int currentAmbit;
    public boolean active;

    public Environment() {
        tableSymbol = new TableSymbol();
        errorsLexical = new ArrayList<>();
        errorsSyntact = new ArrayList<>();
        errorsSemantic = new ArrayList<>();
        pileAmbit  = new Stack<>();
        currentAmbit = 0;
        tableSymbol.addAmbit(currentAmbit);
    }

    public Environment(TableSymbol tableSymbol, List<ErrorGramm> errorsLexical, List<ErrorGramm> errorsSyntact, List<ErrorGramm> errorsSemantic) {
        this.tableSymbol = tableSymbol;
        this.errorsLexical = errorsLexical;
        this.errorsSyntact = errorsSyntact;
        this.errorsSemantic = errorsSemantic;
        pileAmbit = new Stack<>();
        currentAmbit = 0;
    }
    

    public TableSymbol getTableSymbol() {
        return tableSymbol;
    }

    public void setTableSymbol(TableSymbol tableSymbol) {
        this.tableSymbol = tableSymbol;
    }

    public List<ErrorGramm> getErrorsLexical() {
        return errorsLexical;
    }

    public void setErrorsLexical(List<ErrorGramm> errorsLexical) {
        this.errorsLexical = errorsLexical;
    }

    public List<ErrorGramm> getErrorsSyntact() {
        return errorsSyntact;
    }

    public void setErrorsSyntact(List<ErrorGramm> errorsSyntact) {
        this.errorsSyntact = errorsSyntact;
    }

    public List<ErrorGramm> getErrorsSemantic() {
        return errorsSemantic;
    }

    public void setErrorsSemantic(List<ErrorGramm> errorsSemantic) {
        this.errorsSemantic = errorsSemantic;
    }

    public Stack<Integer> getPileAmbit() {
        return pileAmbit;
    }

    public void setPileAmbit(Stack<Integer> pileAmbit) {
        this.pileAmbit = pileAmbit;
    }
    
    
}
