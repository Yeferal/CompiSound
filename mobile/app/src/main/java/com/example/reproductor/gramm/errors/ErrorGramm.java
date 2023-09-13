/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.reproductor.gramm.errors;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class ErrorGramm implements Serializable{
    private PositionToken positionToken;
    private ErrorType errorType;
    private String token, description;

    public ErrorGramm(PositionToken positionToken, ErrorType errorType, String token, String description) {
        this.positionToken = positionToken;
        this.errorType = errorType;
        this.token = token;
        this.description = description;
    }

    public PositionToken getPositionToken() {
        return positionToken;
    }

    public void setPositionToken(PositionToken positionToken) {
        this.positionToken = positionToken;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStringError(){
        return "Fila: "+positionToken.getRow()+", Columan: "+positionToken.getColumn()+", Token: "+token+", Tipo: "+errorType+", Descripcion: "+description;
    }
}
