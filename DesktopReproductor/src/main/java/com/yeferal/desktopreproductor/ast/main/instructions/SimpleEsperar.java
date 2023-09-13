/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class SimpleEsperar implements Serializable{
    private int value;
    private int channel;

    public SimpleEsperar(int value, int channel) {
        this.value = value;
        this.channel = channel;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getChannel() {
        return channel;
    }

    public void setChanel(int chanel) {
        this.channel = chanel;
    }
}
