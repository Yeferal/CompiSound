/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.instructions.notas.MusicalNotes;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;

/**
 *
 * @author Usuario
 */
public class InstReproducir extends Node{
    private MusicalNotes note;
    private Node Octave;
    private Node time;
    private Node chanel;

    public InstReproducir(MusicalNotes note, Node Octave, Node time, Node chanel, PositionToken positionToken) {
        super(positionToken, null);
        this.note = note;
        this.Octave = Octave;
        this.time = time;
        this.chanel = chanel;
    }

    public MusicalNotes getNote() {
        return note;
    }

    public void setNote(MusicalNotes note) {
        this.note = note;
    }

    public Node getOctave() {
        return Octave;
    }

    public void setOctave(Node Octave) {
        this.Octave = Octave;
    }

    public Node getTime() {
        return time;
    }

    public void setTime(Node time) {
        this.time = time;
    }

    public Node getChanel() {
        return chanel;
    }

    public void setChanel(Node chanel) {
        this.chanel = chanel;
    }
    
    @Override
    public Object execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
