/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.gui;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.main.tree.TraslatorMain;
import com.yeferal.desktopreproductor.ast.main.tree.TreeMain;
import com.yeferal.desktopreproductor.gramm.editor.LexicalAnalyzerPaint;
import com.yeferal.desktopreproductor.gramm.editor.SyntaxAnalyzerCode;
import com.yeferal.desktopreproductor.gramm.main.LexicalAnalyzerMain;
import com.yeferal.desktopreproductor.gramm.main.SyntaxAnalyzerMain;
import com.yeferal.desktopreproductor.gui.utils.LineasText;
import com.yeferal.desktopreproductor.utils.filesadm.FileAccess;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class MainWindow extends javax.swing.JFrame {
    
    public LineasText lines;
    private int posicionCursor;
    private FileAccess fileAccess = new FileAccess();
    private TreeMain treeMain = new TreeMain();
    private TraslatorMain traslatorMain = new TraslatorMain();

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(1200, 700);
        lines =new LineasText();
        panelCodePane.add(lines,BorderLayout.WEST);
        panelCodePane.add(lines.scrollPane,BorderLayout.CENTER);
        tabbedPanelMain.setSelectedIndex(1);
        setPositionPointer();
        lines.pane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paneCodeKeyReleased(evt);
            }
        });
        readFileTest();
    }

    
    
    private void paneCodeKeyReleased(java.awt.event.KeyEvent evt){
//        System.out.println("PosC: "+posicionCursor);
        int posCaret = posicionCursor;
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//            paintText();
            posicionCursor = posCaret;
//            System.out.println("pintadoCE: "+posicionCursor + " vs "+posCaret);
            lines.pane.setCaretPosition(posCaret-1);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            paintText(); 
            posicionCursor = posCaret;
//            System.out.println("pintadoC: "+posicionCursor + " vs "+posCaret);
            lines.pane.setCaretPosition(posCaret-1);
        }
    }
    
    private void setPositionPointer() {
        lines.pane.addCaretListener(new CaretListener(){
            
            @Override
            public void caretUpdate(CaretEvent e) {
                int pos = e.getDot();
		int fila = 1, columna=1;
		int ultimalinea=-1;
                posicionCursor = 1;
		String text = lines.pane.getText().replaceAll("\r","");	
		for(int i=0;i<pos;i++){
                    try{
                        if(text.charAt(i)==10){
                            fila++;
                            ultimalinea=i;
                        }
                        posicionCursor ++;
                    }catch(Exception ex){
                        //lines.pane.repaint();
                        //ex.printStackTrace();
                    }
                }		
		columna=pos-ultimalinea;                
                labelFilaInfo.setText(fila +"");
                labelColumnInfo.setText(columna+"");
//                System.out.println("PosicionC: "+posicionCursor);
            }
        });
        
    }
    
    public void paintText(){
        String texto = lines.pane.getText();
        LexicalAnalyzerPaint analizadorLexicoCode = new LexicalAnalyzerPaint(new StringReader(texto));
        analizadorLexicoCode.pintar.insertar(lines.pane.getText());
        lines.pane.setDocument(analizadorLexicoCode.pintar.caja2.getDocument());
        SyntaxAnalyzerCode analizadorSintacticoCode = new SyntaxAnalyzerCode(analizadorLexicoCode);
        
        try {
            analizadorSintacticoCode.parse();
        } catch (Exception ex) {
            //Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void readFileTest(){
        String text = fileAccess.readFile("pistasinerrores.txt");
        lines.pane.setText(text);
        paintText();
    }
    
    public void compile(){
        String txt = lines.pane.getText();
        LexicalAnalyzerMain lexicalAnalyzerMain = new LexicalAnalyzerMain(new StringReader(txt));
        SyntaxAnalyzerMain syntaxAnalyzerMain = new SyntaxAnalyzerMain(lexicalAnalyzerMain);
        
        try {
            syntaxAnalyzerMain.parse();
            List<ErrorGramm> lexicalErrors = lexicalAnalyzerMain.getLexicalErrors();
            List<ErrorGramm> syntaxErrors = syntaxAnalyzerMain.getSyntaxErrors();
            if (lexicalErrors.size()==0 && syntaxErrors.size()==0) {
                treeMain.setListTrack(syntaxAnalyzerMain.getListTracks());
                traslatorMain.initMain(treeMain.getListTrack());
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            List<ErrorGramm> lexicalErrors = lexicalAnalyzerMain.getLexicalErrors();
            List<ErrorGramm> syntaxErrors = syntaxAnalyzerMain.getSyntaxErrors();
            for (ErrorGramm lexicalError : lexicalErrors) {
                System.out.println(lexicalError.getStringError());
            }
            for (ErrorGramm syntaxError : syntaxErrors) {
                System.out.println(syntaxError.getStringError());
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelCentral = new javax.swing.JPanel();
        tabbedPanelMain = new javax.swing.JTabbedPane();
        panelLibrary = new javax.swing.JPanel();
        panelCentralLibrary = new javax.swing.JPanel();
        panelLeft = new javax.swing.JPanel();
        panelTracks = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollPaneTraks = new javax.swing.JScrollPane();
        listTraks = new javax.swing.JList<>();
        buttonCreate = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonPlay = new javax.swing.JButton();
        panelLists = new javax.swing.JPanel();
        panelListPlayer = new javax.swing.JPanel();
        labelListPlayer = new javax.swing.JLabel();
        buttonEditList = new javax.swing.JButton();
        buttonDeletList = new javax.swing.JButton();
        scrollPaneListPlayer = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        paneListsPlayer = new javax.swing.JPanel();
        labelListsPlayers = new javax.swing.JLabel();
        scrollPaneListsPlayers = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        panelEditor = new javax.swing.JPanel();
        panelConsole = new javax.swing.JPanel();
        scrollPanelConsole = new javax.swing.JScrollPane();
        textPaneConsole = new javax.swing.JTextPane();
        panelEditorCode = new javax.swing.JPanel();
        labelFila = new javax.swing.JLabel();
        labelFilaInfo = new javax.swing.JLabel();
        labelColumn = new javax.swing.JLabel();
        labelColumnInfo = new javax.swing.JLabel();
        buttonCompile = new javax.swing.JButton();
        buttonPlayTrankCode = new javax.swing.JButton();
        panelCodePane = new javax.swing.JPanel();
        panelReproductor = new javax.swing.JPanel();
        PanelRight = new javax.swing.JPanel();
        panelGraph = new javax.swing.JPanel();
        panelInfoFreq = new javax.swing.JPanel();
        labelTopHz = new javax.swing.JLabel();
        labelButtonHz = new javax.swing.JLabel();
        panelGraphFreq = new javax.swing.JPanel();
        panelGraphNow = new javax.swing.JPanel();
        labelSegLeft = new javax.swing.JLabel();
        labelSegRight = new javax.swing.JLabel();
        panelControl = new javax.swing.JPanel();
        panelTime = new javax.swing.JPanel();
        panelTimeLeft = new javax.swing.JPanel();
        panelTimeRight = new javax.swing.JPanel();
        checkBoxRepeat = new javax.swing.JCheckBox();
        panelTimeCenter = new javax.swing.JPanel();
        labelTimeNow = new javax.swing.JLabel();
        labelTimeEnd = new javax.swing.JLabel();
        sliderTimeTrak = new javax.swing.JSlider();
        panelButtons = new javax.swing.JPanel();
        panelButtonLeft = new javax.swing.JPanel();
        buttonControlPlay = new javax.swing.JButton();
        panelButtonCenter = new javax.swing.JPanel();
        buttonControlPause = new javax.swing.JButton();
        panelButtonRight = new javax.swing.JPanel();
        buttonControlStop = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelCentral.setLayout(new java.awt.GridLayout(1, 0));

        panelLibrary.setLayout(new java.awt.BorderLayout());

        panelCentralLibrary.setBackground(new java.awt.Color(153, 204, 255));
        panelCentralLibrary.setLayout(new java.awt.GridLayout(1, 0));

        panelLeft.setOpaque(false);
        panelLeft.setLayout(new java.awt.GridLayout(1, 0));

        panelTracks.setOpaque(false);

        jLabel1.setText("Canciones");

        listTraks.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollPaneTraks.setViewportView(listTraks);

        buttonCreate.setText("Crear");

        buttonEdit.setText("Editar");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonDelete.setText("Eliminar");

        buttonPlay.setText("Reproducir");

        javax.swing.GroupLayout panelTracksLayout = new javax.swing.GroupLayout(panelTracks);
        panelTracks.setLayout(panelTracksLayout);
        panelTracksLayout.setHorizontalGroup(
            panelTracksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTracksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTracksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPaneTraks)
                    .addGroup(panelTracksLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelTracksLayout.createSequentialGroup()
                        .addComponent(buttonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTracksLayout.setVerticalGroup(
            panelTracksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTracksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneTraks, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPlay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTracksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCreate)
                    .addComponent(buttonDelete)
                    .addComponent(buttonEdit))
                .addContainerGap())
        );

        panelLeft.add(panelTracks);

        panelLists.setOpaque(false);
        panelLists.setLayout(new java.awt.GridLayout(2, 0));

        panelListPlayer.setOpaque(false);

        labelListPlayer.setText("Lista de Reproduccion");

        buttonEditList.setText("Modificar");
        buttonEditList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditListActionPerformed(evt);
            }
        });

        buttonDeletList.setText("Eliminar");

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollPaneListPlayer.setViewportView(jList2);

        javax.swing.GroupLayout panelListPlayerLayout = new javax.swing.GroupLayout(panelListPlayer);
        panelListPlayer.setLayout(panelListPlayerLayout);
        panelListPlayerLayout.setHorizontalGroup(
            panelListPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelListPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelListPlayerLayout.createSequentialGroup()
                        .addComponent(buttonEditList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addComponent(buttonDeletList))
                    .addComponent(scrollPaneListPlayer))
                .addContainerGap())
        );
        panelListPlayerLayout.setVerticalGroup(
            panelListPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelListPlayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneListPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelListPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEditList)
                    .addComponent(buttonDeletList)))
        );

        panelLists.add(panelListPlayer);

        paneListsPlayer.setOpaque(false);

        labelListsPlayers.setText("Listas de Reproduccion");

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollPaneListsPlayers.setViewportView(jList3);

        javax.swing.GroupLayout paneListsPlayerLayout = new javax.swing.GroupLayout(paneListsPlayer);
        paneListsPlayer.setLayout(paneListsPlayerLayout);
        paneListsPlayerLayout.setHorizontalGroup(
            paneListsPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneListsPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneListsPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelListsPlayers, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(scrollPaneListsPlayers))
                .addContainerGap())
        );
        paneListsPlayerLayout.setVerticalGroup(
            paneListsPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneListsPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelListsPlayers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneListsPlayers, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelLists.add(paneListsPlayer);

        panelLeft.add(panelLists);

        panelCentralLibrary.add(panelLeft);

        panelLibrary.add(panelCentralLibrary, java.awt.BorderLayout.CENTER);

        tabbedPanelMain.addTab("Biblioteca", panelLibrary);

        panelEditor.setBackground(new java.awt.Color(153, 204, 255));
        panelEditor.setLayout(new java.awt.BorderLayout());

        panelConsole.setOpaque(false);

        scrollPanelConsole.setViewportView(textPaneConsole);

        javax.swing.GroupLayout panelConsoleLayout = new javax.swing.GroupLayout(panelConsole);
        panelConsole.setLayout(panelConsoleLayout);
        panelConsoleLayout.setHorizontalGroup(
            panelConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsoleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPanelConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConsoleLayout.setVerticalGroup(
            panelConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanelConsole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        panelEditor.add(panelConsole, java.awt.BorderLayout.PAGE_END);

        panelEditorCode.setOpaque(false);

        labelFila.setText("FILA: ");

        labelFilaInfo.setText("0");

        labelColumn.setText("COLUMNA: ");

        labelColumnInfo.setText("0");

        buttonCompile.setText("Compilar");
        buttonCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCompileActionPerformed(evt);
            }
        });

        buttonPlayTrankCode.setText("Reproducir Pista");

        panelCodePane.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelEditorCodeLayout = new javax.swing.GroupLayout(panelEditorCode);
        panelEditorCode.setLayout(panelEditorCodeLayout);
        panelEditorCodeLayout.setHorizontalGroup(
            panelEditorCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditorCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEditorCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCodePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelEditorCodeLayout.createSequentialGroup()
                        .addComponent(labelFila)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelFilaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelColumn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelColumnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(buttonPlayTrankCode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCompile, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelEditorCodeLayout.setVerticalGroup(
            panelEditorCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditorCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCodePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEditorCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFila)
                    .addComponent(buttonCompile)
                    .addComponent(buttonPlayTrankCode)
                    .addComponent(labelFilaInfo)
                    .addComponent(labelColumn)
                    .addComponent(labelColumnInfo))
                .addContainerGap())
        );

        panelEditor.add(panelEditorCode, java.awt.BorderLayout.CENTER);

        tabbedPanelMain.addTab("Editor de Codigo", panelEditor);

        panelCentral.add(tabbedPanelMain);

        panelReproductor.setBackground(new java.awt.Color(153, 204, 255));

        PanelRight.setOpaque(false);
        PanelRight.setLayout(new java.awt.GridLayout(2, 0));

        panelGraph.setOpaque(false);
        panelGraph.setLayout(new java.awt.BorderLayout());

        panelInfoFreq.setOpaque(false);

        labelTopHz.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTopHz.setText("4000 HZ");

        labelButtonHz.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelButtonHz.setText("0 HZ");

        javax.swing.GroupLayout panelInfoFreqLayout = new javax.swing.GroupLayout(panelInfoFreq);
        panelInfoFreq.setLayout(panelInfoFreqLayout);
        panelInfoFreqLayout.setHorizontalGroup(
            panelInfoFreqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelButtonHz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoFreqLayout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addComponent(labelTopHz, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelInfoFreqLayout.setVerticalGroup(
            panelInfoFreqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoFreqLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTopHz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(labelButtonHz, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelGraph.add(panelInfoFreq, java.awt.BorderLayout.LINE_START);

        panelGraphFreq.setOpaque(false);

        panelGraphNow.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelGraphNowLayout = new javax.swing.GroupLayout(panelGraphNow);
        panelGraphNow.setLayout(panelGraphNowLayout);
        panelGraphNowLayout.setHorizontalGroup(
            panelGraphNowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGraphNowLayout.setVerticalGroup(
            panelGraphNowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );

        labelSegLeft.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSegLeft.setText("60 seg");

        labelSegRight.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSegRight.setText("0 seg");

        javax.swing.GroupLayout panelGraphFreqLayout = new javax.swing.GroupLayout(panelGraphFreq);
        panelGraphFreq.setLayout(panelGraphFreqLayout);
        panelGraphFreqLayout.setHorizontalGroup(
            panelGraphFreqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraphFreqLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGraphFreqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGraphNow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelGraphFreqLayout.createSequentialGroup()
                        .addComponent(labelSegLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
                        .addComponent(labelSegRight, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelGraphFreqLayout.setVerticalGroup(
            panelGraphFreqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraphFreqLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraphNow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGraphFreqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSegLeft)
                    .addComponent(labelSegRight))
                .addContainerGap())
        );

        panelGraph.add(panelGraphFreq, java.awt.BorderLayout.CENTER);

        PanelRight.add(panelGraph);

        panelControl.setOpaque(false);
        panelControl.setLayout(new java.awt.BorderLayout());

        panelTime.setOpaque(false);
        panelTime.setLayout(new java.awt.BorderLayout());

        panelTimeLeft.setOpaque(false);

        javax.swing.GroupLayout panelTimeLeftLayout = new javax.swing.GroupLayout(panelTimeLeft);
        panelTimeLeft.setLayout(panelTimeLeftLayout);
        panelTimeLeftLayout.setHorizontalGroup(
            panelTimeLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelTimeLeftLayout.setVerticalGroup(
            panelTimeLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelTime.add(panelTimeLeft, java.awt.BorderLayout.LINE_START);

        panelTimeRight.setOpaque(false);

        checkBoxRepeat.setText("Repetir");

        javax.swing.GroupLayout panelTimeRightLayout = new javax.swing.GroupLayout(panelTimeRight);
        panelTimeRight.setLayout(panelTimeRightLayout);
        panelTimeRightLayout.setHorizontalGroup(
            panelTimeRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimeRightLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(checkBoxRepeat)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelTimeRightLayout.setVerticalGroup(
            panelTimeRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimeRightLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(checkBoxRepeat)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        panelTime.add(panelTimeRight, java.awt.BorderLayout.LINE_END);

        panelTimeCenter.setOpaque(false);

        labelTimeNow.setText("0:00");

        labelTimeEnd.setText("12:00:00");

        javax.swing.GroupLayout panelTimeCenterLayout = new javax.swing.GroupLayout(panelTimeCenter);
        panelTimeCenter.setLayout(panelTimeCenterLayout);
        panelTimeCenterLayout.setHorizontalGroup(
            panelTimeCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimeCenterLayout.createSequentialGroup()
                .addComponent(labelTimeNow, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sliderTimeTrak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTimeEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelTimeCenterLayout.setVerticalGroup(
            panelTimeCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimeCenterLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelTimeCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderTimeTrak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTimeCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTimeNow, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelTimeEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        panelTime.add(panelTimeCenter, java.awt.BorderLayout.CENTER);

        panelControl.add(panelTime, java.awt.BorderLayout.PAGE_END);

        panelButtons.setOpaque(false);
        panelButtons.setLayout(new java.awt.GridLayout(1, 0));

        panelButtonLeft.setOpaque(false);

        buttonControlPlay.setIcon(new javax.swing.ImageIcon("C:\\Users\\Usuario\\Desktop\\Compi2\\projects\\Project1\\CompiSound\\DesktopReproductor\\src\\main\\java\\com\\yeferal\\desktopreproductor\\gui\\picture\\buttonPlay.png")); // NOI18N
        buttonControlPlay.setBorder(null);
        buttonControlPlay.setSelected(true);
        buttonControlPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonControlPlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonLeftLayout = new javax.swing.GroupLayout(panelButtonLeft);
        panelButtonLeft.setLayout(panelButtonLeftLayout);
        panelButtonLeftLayout.setHorizontalGroup(
            panelButtonLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonControlPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelButtonLeftLayout.setVerticalGroup(
            panelButtonLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonControlPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelButtons.add(panelButtonLeft);

        panelButtonCenter.setOpaque(false);

        buttonControlPause.setIcon(new javax.swing.ImageIcon("C:\\Users\\Usuario\\Desktop\\Compi2\\projects\\Project1\\CompiSound\\DesktopReproductor\\src\\main\\java\\com\\yeferal\\desktopreproductor\\gui\\picture\\buttonPause.png")); // NOI18N
        buttonControlPause.setBorder(null);
        buttonControlPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonControlPauseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCenterLayout = new javax.swing.GroupLayout(panelButtonCenter);
        panelButtonCenter.setLayout(panelButtonCenterLayout);
        panelButtonCenterLayout.setHorizontalGroup(
            panelButtonCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonControlPause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelButtonCenterLayout.setVerticalGroup(
            panelButtonCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonControlPause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelButtons.add(panelButtonCenter);

        panelButtonRight.setOpaque(false);

        buttonControlStop.setIcon(new javax.swing.ImageIcon("C:\\Users\\Usuario\\Desktop\\Compi2\\projects\\Project1\\CompiSound\\DesktopReproductor\\src\\main\\java\\com\\yeferal\\desktopreproductor\\gui\\picture\\buttonStop.png")); // NOI18N
        buttonControlStop.setToolTipText("");
        buttonControlStop.setBorder(null);

        javax.swing.GroupLayout panelButtonRightLayout = new javax.swing.GroupLayout(panelButtonRight);
        panelButtonRight.setLayout(panelButtonRightLayout);
        panelButtonRightLayout.setHorizontalGroup(
            panelButtonRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonControlStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelButtonRightLayout.setVerticalGroup(
            panelButtonRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonControlStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelButtons.add(panelButtonRight);

        panelControl.add(panelButtons, java.awt.BorderLayout.CENTER);

        PanelRight.add(panelControl);

        javax.swing.GroupLayout panelReproductorLayout = new javax.swing.GroupLayout(panelReproductor);
        panelReproductor.setLayout(panelReproductorLayout);
        panelReproductorLayout.setHorizontalGroup(
            panelReproductorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
            .addGroup(panelReproductorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelReproductorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelReproductorLayout.setVerticalGroup(
            panelReproductorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
            .addGroup(panelReproductorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReproductorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelRight, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        panelCentral.add(panelReproductor);

        jMenu1.setText("Archivo");

        jMenu4.setText("Pista");

        jMenuItem3.setText("Crear Pista");
        jMenu4.add(jMenuItem3);

        jMenuItem4.setText("Modificar Pista");
        jMenu4.add(jMenuItem4);

        jMenuItem5.setText("Eliminar Pista");
        jMenu4.add(jMenuItem5);

        jMenuItem7.setText("Guardar Pista");
        jMenu4.add(jMenuItem7);

        jMenuItem6.setText("Recuperar Pista");
        jMenu4.add(jMenuItem6);

        jMenu1.add(jMenu4);

        jMenu5.setText("Lista");

        jMenuItem8.setText("Guardar Lista de Reproduccion");
        jMenu5.add(jMenuItem8);

        jMenuItem9.setText("Recuperar Lista de Reproduccion");
        jMenu5.add(jMenuItem9);

        jMenu1.add(jMenu5);

        jMenuItem1.setText("Nueva Pista");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Nuevo Archivo");
        jMenu1.add(jMenuItem2);

        menuBar.add(jMenu1);

        jMenu2.setText("Reporte");
        menuBar.add(jMenu2);

        jMenu3.setText("Ayuda");
        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 1205, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonEditListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEditListActionPerformed

    private void buttonControlPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonControlPlayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonControlPlayActionPerformed

    private void buttonControlPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonControlPauseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonControlPauseActionPerformed

    private void buttonCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCompileActionPerformed
        // TODO add your handling code here:
        compile();
    }//GEN-LAST:event_buttonCompileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelRight;
    private javax.swing.JButton buttonCompile;
    private javax.swing.JButton buttonControlPause;
    public javax.swing.JButton buttonControlPlay;
    private javax.swing.JButton buttonControlStop;
    private javax.swing.JButton buttonCreate;
    private javax.swing.JButton buttonDeletList;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonEditList;
    private javax.swing.JButton buttonPlay;
    private javax.swing.JButton buttonPlayTrankCode;
    private javax.swing.JCheckBox checkBoxRepeat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelButtonHz;
    private javax.swing.JLabel labelColumn;
    private javax.swing.JLabel labelColumnInfo;
    private javax.swing.JLabel labelFila;
    private javax.swing.JLabel labelFilaInfo;
    private javax.swing.JLabel labelListPlayer;
    private javax.swing.JLabel labelListsPlayers;
    private javax.swing.JLabel labelSegLeft;
    private javax.swing.JLabel labelSegRight;
    private javax.swing.JLabel labelTimeEnd;
    private javax.swing.JLabel labelTimeNow;
    private javax.swing.JLabel labelTopHz;
    private javax.swing.JList<String> listTraks;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel paneListsPlayer;
    private javax.swing.JPanel panelButtonCenter;
    private javax.swing.JPanel panelButtonLeft;
    private javax.swing.JPanel panelButtonRight;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelCentralLibrary;
    private javax.swing.JPanel panelCodePane;
    private javax.swing.JPanel panelConsole;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelEditor;
    private javax.swing.JPanel panelEditorCode;
    private javax.swing.JPanel panelGraph;
    private javax.swing.JPanel panelGraphFreq;
    private javax.swing.JPanel panelGraphNow;
    private javax.swing.JPanel panelInfoFreq;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelLibrary;
    private javax.swing.JPanel panelListPlayer;
    private javax.swing.JPanel panelLists;
    private javax.swing.JPanel panelReproductor;
    private javax.swing.JPanel panelTime;
    private javax.swing.JPanel panelTimeCenter;
    private javax.swing.JPanel panelTimeLeft;
    private javax.swing.JPanel panelTimeRight;
    private javax.swing.JPanel panelTracks;
    private javax.swing.JScrollPane scrollPaneListPlayer;
    private javax.swing.JScrollPane scrollPaneListsPlayers;
    private javax.swing.JScrollPane scrollPaneTraks;
    private javax.swing.JScrollPane scrollPanelConsole;
    private javax.swing.JSlider sliderTimeTrak;
    private javax.swing.JTabbedPane tabbedPanelMain;
    private javax.swing.JTextPane textPaneConsole;
    // End of variables declaration//GEN-END:variables
}
