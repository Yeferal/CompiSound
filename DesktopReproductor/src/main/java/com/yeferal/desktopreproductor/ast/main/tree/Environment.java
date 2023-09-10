/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.ast.main.tree;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.instructions.ExeEsperar;
import com.yeferal.desktopreproductor.ast.main.instructions.ExeReproducir;
import com.yeferal.desktopreproductor.ast.main.instructions.notas.MusicalNotes;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.TableSymbol;
import com.yeferal.desktopreproductor.utils.filesadm.FrecuencysNotes;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.JTextPane;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Usuario
 */
public class Environment implements Runnable, Serializable{
    private TableSymbol tableSymbol;
    private List<ErrorGramm> errorsLexical, errorsSyntact, errorsSemantic;
    private Deque<Integer> pileAmbitTemp;
    public int currentAmbit;
    public boolean active;
    private List<Object> listExecutes = new ArrayList<>();
    Synthesizer synth;
    Thread th;
    public JTextPane textPane;
    public String nameTrac = "";
    public Node nodeRoot;
    boolean Running = true;
    boolean Pause = false;
    XYSeries series = new XYSeries("");
    FrecuencysNotes fn = new FrecuencysNotes();

    public Environment() throws MidiUnavailableException {
        this.synth = MidiSystem.getSynthesizer();
        tableSymbol = new TableSymbol();
        errorsLexical = new ArrayList<>();
        errorsSyntact = new ArrayList<>();
        errorsSemantic = new ArrayList<>();
        pileAmbitTemp  = new ArrayDeque<>();
        currentAmbit = 0;
        tableSymbol.addAmbit(currentAmbit);
        th = new Thread(this);
    }

    public Environment(TableSymbol tableSymbol, List<ErrorGramm> errorsLexical, List<ErrorGramm> errorsSyntact, List<ErrorGramm> errorsSemantic) throws MidiUnavailableException {
        this.synth = MidiSystem.getSynthesizer();
        this.tableSymbol = tableSymbol;
        this.errorsLexical = errorsLexical;
        this.errorsSyntact = errorsSyntact;
        this.errorsSemantic = errorsSemantic;
        pileAmbitTemp = new ArrayDeque<>();
        currentAmbit = 0;
        th = new Thread(this);
    }
    
    public void resetData() throws MidiUnavailableException{
        synth = MidiSystem.getSynthesizer();
        tableSymbol = new TableSymbol();
        pileAmbitTemp  = new ArrayDeque<>();
        currentAmbit = 0;
        tableSymbol.addAmbit(currentAmbit);
        th = new Thread(this);
    }

    public XYSeries getSeries() {
        return series;
    }

    public void setSeries(XYSeries series) {
        this.series = series;
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

    public Deque<Integer> getPileAmbit() {
        return pileAmbitTemp;
    }

    public void setPileAmbit(Deque<Integer> pileAmbit) {
        this.pileAmbitTemp = pileAmbit;
    }
    public void addNewAmbit(){
        currentAmbit++;
        tableSymbol.addAmbit(currentAmbit);
    }
    public void backAmbit(){
        tableSymbol.popAmbit();
    }
    
    public void addNewAmbitTemp(){
        pileAmbitTemp.add(currentAmbit);
    }
    public void backAmbitTemp(){
        if (!pileAmbitTemp.isEmpty()) {
            pileAmbitTemp.pollLast();
        }
    }
    
    public void backAllAmbitTemp(){
        while (!pileAmbitTemp.isEmpty()) {
            pileAmbitTemp.pollLast();
        }
    }
    
    public Deque<Integer> setAmbitPileCpopy(Deque<Integer> ambitsPile){
        Deque<Integer> tempPile = new LinkedList<>(ambitsPile);
        return tempPile;
    }

    public List<Object> getListExecutes() {
        return listExecutes;
    }

    public void setListExecutes(List<Object> listExecutes) {
        this.listExecutes = listExecutes;
    }

    public Synthesizer getSynth() {
        return synth;
    }

    public void setSynth(Synthesizer synth) {
        this.synth = synth;
    }

    public Thread getTh() {
        return th;
    }

    public void setTh(Thread th) {
        this.th = th;
    }
    
    public void paintConsoleErros(){
        StringBuilder txt = new StringBuilder();
        for (ErrorGramm errorGramm : errorsLexical) {
            txt.append(errorGramm.getStringError()).append("\n");
        }
        
        for (ErrorGramm errorGramm : errorsSyntact) {
            txt.append(errorGramm.getStringError()).append("\n");
        }
        
        for (ErrorGramm errorGramm : errorsSemantic) {
            txt.append(errorGramm.getStringError()).append("\n");
        }
        
        if (textPane!= null) {
            textPane.setText(txt.toString());
        }
    }
    
    public void runInst(){
        try {
            synth.open();
            Map<Integer, Thread> channelThreads = new HashMap<>();
            for (Object listExecute : listExecutes) {
                if (listExecute instanceof ExeReproducir) {
                    ExeReproducir er = (ExeReproducir) listExecute;
                    int channel = er.getChannel();
//                    if (channelThreads.containsKey(channel)) {
//                        // Si ya hay un hilo ejecutándose en el mismo canal, espera a que termine
//                        Thread thread = channelThreads.get(channel);
//                        thread.join();
//                    }
                    Thread thread = new Thread(er);
                    thread.start();
                    thread.join(); // Espera a que este sonido termine antes de iniciar el siguiente
//                    er.execute();
                }else {
                    ExeEsperar ee = (ExeEsperar) listExecute;
                    int channel = ee.getChannel();
//                    if (channelThreads.containsKey(channel)) {
//                        // Si ya hay un hilo ejecutándose en el mismo canal, espera a que termine
//                        Thread thread = channelThreads.get(channel);
//                        thread.join();
//                    }
                    
                    Thread thread = new Thread((Runnable) listExecute);
                    thread.start();
                    channelThreads.put(channel, thread);
                }
            }
            for (Thread thread : channelThreads.values()) {
                thread.join();
            }  

            synth.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            synth.open();
            double timeT = 0;
            Map<Integer, Thread> channelThreads = new HashMap<>();
            for (Object listExecute : listExecutes) {
                while (Pause) {   
                    System.out.println("Pause");
                    Thread.sleep(1000);
                }
                if (!Running) {
                    System.out.println("KILL");
                    break;
                }
                if (listExecute instanceof ExeReproducir) {
                    ExeReproducir er = (ExeReproducir) listExecute;
                    int channel = er.getChannel();
                    if (channelThreads.containsKey(channel)) {
                        // Si ya hay un hilo ejecutándose en el mismo canal, espera a que termine
                        Thread thread = channelThreads.get(channel);
                        thread.join();
                        timeT += er.getTime()/1000;
                    }
                    series.add(timeT, fn.getFrecency(er.getNote(), er.getOctave()));
                    Thread thread = new Thread(er);
                    thread.start();
                    channelThreads.put(channel, thread);
                }else {
                    ExeEsperar ee = (ExeEsperar) listExecute;
                    int channel = ee.getChannel();
                    if (channelThreads.containsKey(channel)) {
                        // Si ya hay un hilo ejecutándose en el mismo canal, espera a que termine
                        Thread thread = channelThreads.get(channel);
                        thread.join();
                    }
                }
            }
            for (Thread thread : channelThreads.values()) {
                thread.join();
            }  

            synth.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void kill() {
        this.Running = false;
        this.Pause = false;
    }
    
    public void pause() {
        this.Pause = true;
    }
    
    public void reanude() {
        this.Pause = false;
    }
    
}
