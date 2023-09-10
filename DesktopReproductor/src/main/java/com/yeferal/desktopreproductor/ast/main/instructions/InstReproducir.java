/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.instructions;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.instructions.notas.MusicalNotes;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;

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
    public Object execute(Environment env) {
        setType(DataType.ENTERO);
        Object intOctave = Octave.execute(env);
        Object intTime = time.execute(env);
        Object intChanel = chanel.execute(env);
        
        if (note==null || intOctave==null || intTime==null || intChanel==null ) {
            env.getErrorsSyntact().add(new ErrorGramm(Octave.getPositionToken(),ErrorType.SYNTACTIC, "Reproducir", "El valor del parametros es invalid, no se aceptan nulos"));
            return 0;
        }
        
        if (Octave.getType() != DataType.ENTERO) {
            env.getErrorsSyntact().add(new ErrorGramm(Octave.getPositionToken(),ErrorType.SYNTACTIC, "Reproducir", "El valor del parametro OCTAVA ES INCORRECTO, solo se admiten enteros"));
        }
        
        if (time.getType() != DataType.ENTERO) {
            env.getErrorsSyntact().add(new ErrorGramm(time.getPositionToken(),ErrorType.SYNTACTIC, "Reproducir", "El valor del parametro TIEMPO ES INCORRECTO, solo se admiten enteros"));
        }
        
        if (chanel.getType() != DataType.ENTERO) {
            env.getErrorsSyntact().add(new ErrorGramm(chanel.getPositionToken(),ErrorType.SYNTACTIC, "Reproducir", "El valor del parametro CANAL ES INCORRECTO, solo se admiten enteros"));
        }
        
        if (((int) intOctave) <0 && ((int) intOctave) > 8) {
            env.getErrorsSyntact().add(new ErrorGramm(Octave.getPositionToken(),ErrorType.SYNTACTIC, "Reproducir", "El valor del parametro OCTAVA ES INCORRECTO debe esta entre 0 a 8"));
        }

        ExeReproducir er = new ExeReproducir(note,(int) intOctave,(int) intTime,(int) intChanel, env.getSynth());
        env.getListExecutes().add(er);
        if (env.active) {
            Thread thread1 = new Thread(er);
            thread1.start();
//            er.execute();
        }
        
        return intTime;
    }
}
