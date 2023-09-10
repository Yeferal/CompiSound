/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.gui;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.main.tree.Environment;
import com.yeferal.desktopreproductor.ast.main.tree.PlayerSound;
import com.yeferal.desktopreproductor.ast.main.tree.TraslatorMain;
import com.yeferal.desktopreproductor.ast.main.tree.TreeMain;
import com.yeferal.desktopreproductor.gramm.editor.LexicalAnalyzerPaint;
import com.yeferal.desktopreproductor.gramm.editor.SyntaxAnalyzerCode;
import com.yeferal.desktopreproductor.gramm.list.LexicalAnalyzerList;
import com.yeferal.desktopreproductor.gramm.list.SyntaxAnalyzerList;
import com.yeferal.desktopreproductor.gramm.list.TrackList;
import com.yeferal.desktopreproductor.gramm.main.LexicalAnalyzerMain;
import com.yeferal.desktopreproductor.gramm.main.SyntaxAnalyzerMain;
import com.yeferal.desktopreproductor.gui.utils.LineasText;
import com.yeferal.desktopreproductor.utils.filesadm.AllTrackList;
import com.yeferal.desktopreproductor.utils.filesadm.FileAccess;
import com.yeferal.desktopreproductor.utils.filesadm.ListaFile;
import com.yeferal.desktopreproductor.utils.filesadm.PistaFile;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import java.util.List;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Usuario
 */
public class MainWindow extends javax.swing.JFrame {
    
    PlayerSound playerSound;
    public LineasText lines;
    private int posicionCursor;
    private FileAccess fileAccess = new FileAccess();
    private TreeMain treeMain = new TreeMain();
    private TraslatorMain traslatorMain = new TraslatorMain();
    private String typeArchivo = "";
    private String routeFileNow = null;
    private String nameFile = null;
    private int indexList = 0;
    private boolean pistaSelected = false;
    DefaultListModel<String> listModel = new DefaultListModel<>();
    DefaultListModel<String> listModelList = new DefaultListModel<>();
    DefaultListModel<String> listModelListPista = new DefaultListModel<>();
    Environment envActual = null;
    AllTrackList atl = new AllTrackList(new ArrayList<>());
    ListaFile listFileActual = new ListaFile(new ArrayList<>());
    boolean listaSelected = false;
    int indexlistaSelected = 0;
    XYSeries series = new XYSeries("");

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
//        tabbedPanelMain.setSelectedIndex(1);
        setPositionPointer();
        lines.pane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paneCodeKeyReleased(evt);
            }
        });
//        readFileTest();
        readFileTestList();
        
//        listTraks = new JList<>(listModel);
        
        updateListas();
        updatePistas();
        resetGraph();
        
        
    }
    
    private XYSeriesCollection createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        // Crea una serie de datos XY
        series = new XYSeries("");
        dataset.addSeries(series);
        return dataset;
    }
    
    private void resetGraph(){
        XYSeriesCollection dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(panelGraphNow.getSize());
        System.out.println("Pnale: "+panelGraphNow.getComponentCount());
        if (panelGraphNow.getComponentCount()>0) {
            panelGraphNow.removeAll();
        }
        panelGraphNow.add(chartPanel);
        panelGraphNow.updateUI();
        panelGraphNow.revalidate();
        panelGraphNow.repaint();
    }

    private JFreeChart createChart(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            "",
            "Seg",
            "HZ",
            dataset,
            PlotOrientation.VERTICAL,
            false,
            false,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        // Personaliza la apariencia del gráfico si es necesario

        return chart;
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
        typeArchivo = "Pista";
    }
    
    public void readFileTestList(){
        String text = fileAccess.readFile("lista.txt");
        lines.pane.setText(text);
        paintText();
        typeArchivo = "Lista";
    }
    
    public void readFile(String path){
        String text = fileAccess.readFile(path);
        lines.pane.setText(text);
        paintText();
        typeArchivo = "Pista";
    }
    
    public void saveFile(Environment env){
        int opcion = JOptionPane.showConfirmDialog(this, "Archivo compilado correctamente \t¿Deseas guardar la Pista?");
        if (opcion == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                // Aquí puedes guardar el archivo en la ubicación seleccionada
                if (typeArchivo.equals("Pista")) {
//                    PistaFile pf = new PistaFile();
                }
                JOptionPane.showMessageDialog(this, "Archivo guardado en: " + archivoSeleccionado.getAbsolutePath());
            }
        }
        
    }
    
    public void saveFilePista(Environment env){
        int opcion = JOptionPane.showConfirmDialog(this, "Archivo compilado correctamente \t¿Deseas guardar la Pista?");
        if (opcion == JOptionPane.YES_OPTION) {
            // Aquí puedes guardar el archivo en la ubicación seleccionada
            if (typeArchivo.equals("Pista")) {
                PistaFile pf = new PistaFile(env.nameTrac, lines.pane.getText(), env.nodeRoot);
//                pf.setListExecutes(env.getListExecutes());
                fileAccess.guardarClaseEnArchivoPista(pf, "data/pistas/"+env.nameTrac);
                JOptionPane.showMessageDialog(this, "Archivo guardado en: " + "/data/pistas");
            }else if (typeArchivo.equals("Lista")) {
                PistaFile pf = new PistaFile(env.nameTrac, lines.pane.getText(), env.nodeRoot);
                fileAccess.guardarClaseEnArchivoPista(pf, "data/listas/"+env.nameTrac);
                JOptionPane.showMessageDialog(this, "Archivo guardado en: " + "/data/pistas");
            }
        }
        updatePistas();
    }
    
    public void saveFileList(List<TrackList> list){
        int opcion = JOptionPane.showConfirmDialog(this, "Archivo compilado correctamente \t¿Deseas guardar la Pista?");
        if (opcion == JOptionPane.YES_OPTION) {
            ListaFile lf = new ListaFile(list);
            fileAccess.guardarClaseEnArchivoLista(lf, "data/alllistas");
            JOptionPane.showMessageDialog(this, "Archivo guardado en: " + "/data/pistas");
        }
        updateListas();
    }
    
    public void saveFileOnAllPist(Environment env){
        int opcion = JOptionPane.showConfirmDialog(this, "Desea guardar la ultima pista compilada \t¿Deseas guardar la Pista?");
        if (opcion == JOptionPane.YES_OPTION) {
            // Aquí puedes guardar el archivo en la ubicación seleccionada
            PistaFile pf = new PistaFile(env.nameTrac, lines.pane.getText(), env.nodeRoot);
            atl.addPista(pf);
            fileAccess.guardarClaseEnArchivoAllPista(atl, "data/allpistas");
            JOptionPane.showMessageDialog(this, "Pista Agregada O Guardada ");
        }
        updatePistas();
    }
    
    public void compile() throws MidiUnavailableException{
        String txt = lines.pane.getText();
        LexicalAnalyzerMain lexicalAnalyzerMain = new LexicalAnalyzerMain(new StringReader(txt));
        SyntaxAnalyzerMain syntaxAnalyzerMain = new SyntaxAnalyzerMain(lexicalAnalyzerMain);
        Environment env = new Environment();
        env.textPane = textPaneConsole;
        try {
            syntaxAnalyzerMain.parse();
            List<ErrorGramm> lexicalErrors = lexicalAnalyzerMain.getLexicalErrors();
            List<ErrorGramm> syntaxErrors = syntaxAnalyzerMain.getSyntaxErrors();
            env.setErrorsLexical(lexicalErrors);
            env.setErrorsSyntact(syntaxErrors);
            if (lexicalErrors.size()==0 && syntaxErrors.size()==0) {
                treeMain.setListTrack(syntaxAnalyzerMain.getListTracks());
                env = traslatorMain.initMain(treeMain.getListTrack());
                if (env.getErrorsLexical().size()>0 || env.getErrorsSyntact().size()>0 || env.getErrorsSemantic().size()>0) {
                    env.paintConsoleErros();
                }else {
                    envActual = env;
                    saveFilePista(env);
                }
            }else {
                env.paintConsoleErros();
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            env.paintConsoleErros();
            
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
    
    public void paintConsoleErros(List<ErrorGramm> errorsLexical, List<ErrorGramm> errorsSyntact, List<ErrorGramm> errorsSemantic){
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
        textPaneConsole.setText(txt.toString());
    }
    
    public void compileList() throws MidiUnavailableException{
        String txt = lines.pane.getText();
        LexicalAnalyzerList lexicalAnalyzerList = new LexicalAnalyzerList(new StringReader(txt));
        SyntaxAnalyzerList syntaxAnalyzerList = new SyntaxAnalyzerList(lexicalAnalyzerList);
        try {
            syntaxAnalyzerList.parse();
            List<ErrorGramm> lexicalErrors = lexicalAnalyzerList.getLexicalErrors();
            List<ErrorGramm> syntaxErrors = syntaxAnalyzerList.getSyntaxErrors();
            if (lexicalErrors.size()==0 && syntaxErrors.size()==0) {
                for (int i = 0; i < syntaxAnalyzerList.getListTracks().size(); i++) {
                    syntaxAnalyzerList.getListTracks().get(i).setTxt(txt);
                }
                listFileActual = new ListaFile(syntaxAnalyzerList.getListTracks());
                saveFileList(syntaxAnalyzerList.getListTracks());
            }else {
                paintConsoleErros(lexicalErrors, syntaxErrors, new ArrayList<>());
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            
            List<ErrorGramm> lexicalErrors = lexicalAnalyzerList.getLexicalErrors();
            List<ErrorGramm> syntaxErrors = syntaxAnalyzerList.getSyntaxErrors();
            paintConsoleErros(lexicalErrors, syntaxErrors, new ArrayList<>());
            
        }
    }
    
    public void setInfoFile(){
        if (typeArchivo != null) {
            if (typeArchivo.equals("Pista")) {
                labelInfoFile.setText("Pista: "+"Untitled-1");
            }else if (typeArchivo.equals("Lista")) {
                labelInfoFile.setText("Lista: "+"Untitled-1");
            }
        }
    }
    
    public void updateListas(){
        ListaFile lf = fileAccess.cargarClaseDesdeArchivoLista("data/alllistas");
        listFileActual = lf;
        listModelList.clear();
        for (int i = 0; i < lf.getList().size() ; i++) {
            if (lf.getList().get(i) != null) {
                System.out.println(lf.getList().get(i).getName()+" => "+lf.getList().get(i).getListTracks().toString());
                listModelList.addElement(lf.getList().get(i).getName());
            }
        }
        jList3.updateUI();
        jList3.revalidate();
        jList3.repaint();
    }
    
    public void updatePistas(){
        AllTrackList listList = fileAccess.cargarClaseDesdeArchivoAllPista("data/allpistas");
        System.out.println("Listado: "+listList.getList().size());
        atl = listList;
        listModel.clear();
        for (int i = 0; i < atl.getList().size() ; i++) {
            System.out.println(atl.getList().get(i).getName());
            listModel.addElement(atl.getList().get(i).getName());
        }
        listTraks.updateUI();
        listTraks.revalidate();
        listTraks.repaint();
    }
    
    public void setPistaLista(int index){
        listModelListPista.clear();
        System.out.println("Tam: "+listFileActual.getList().size()+" > "+index);
        if (index>0 && listFileActual.getList().size()>index) {
            for (String listTrack : listFileActual.getList().get(index).getListTracks()) {
                for (int i = 0; i < atl.getList().size(); i++) {
                    if (listTrack.equals(atl.getList().get(i).getName())) {
                        listModelListPista.addElement(listTrack);
                    }
                }
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
        labelInfoFile = new javax.swing.JLabel();
        panelReproductor = new javax.swing.JPanel();
        PanelRight = new javax.swing.JPanel();
        panelGraph = new javax.swing.JPanel();
        panelGraphFreq = new javax.swing.JPanel();
        panelGraphNow = new javax.swing.JPanel();
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
        menuItemCrearPista = new javax.swing.JMenuItem();
        menuItemModificarPista = new javax.swing.JMenuItem();
        menuItemEliminarPista = new javax.swing.JMenuItem();
        menuItemGuardarPista = new javax.swing.JMenuItem();
        menuItemRecuperarPista = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuItemNuevaPista = new javax.swing.JMenuItem();
        menuItemGuardarListaRep = new javax.swing.JMenuItem();
        menuItemRecuperarListaRep = new javax.swing.JMenuItem();
        menuItemNuevoArchivo = new javax.swing.JMenuItem();
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

        listTraks.setModel(listModel);
        listTraks.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listTraksValueChanged(evt);
            }
        });
        scrollPaneTraks.setViewportView(listTraks);

        buttonCreate.setText("Crear");
        buttonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateActionPerformed(evt);
            }
        });

        buttonEdit.setText("Editar");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonDelete.setText("Eliminar");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonPlay.setText("Reproducir");
        buttonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPlayActionPerformed(evt);
            }
        });

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
        buttonDeletList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeletListActionPerformed(evt);
            }
        });

        jList2.setModel(listModelListPista);
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
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

        jList3.setModel(listModelList);
        jList3.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList3ValueChanged(evt);
            }
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
        buttonPlayTrankCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPlayTrankCodeActionPerformed(evt);
            }
        });

        panelCodePane.setLayout(new java.awt.BorderLayout());

        labelInfoFile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelInfoFile.setText("jLabel2");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelInfoFile, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonPlayTrankCode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCompile, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelEditorCodeLayout.setVerticalGroup(
            panelEditorCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditorCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCodePane, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEditorCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFila)
                    .addComponent(buttonCompile)
                    .addComponent(buttonPlayTrankCode)
                    .addComponent(labelFilaInfo)
                    .addComponent(labelColumn)
                    .addComponent(labelColumnInfo)
                    .addComponent(labelInfoFile, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
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

        panelGraphFreq.setOpaque(false);

        panelGraphNow.setBackground(new java.awt.Color(255, 255, 255));
        panelGraphNow.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelGraphFreqLayout = new javax.swing.GroupLayout(panelGraphFreq);
        panelGraphFreq.setLayout(panelGraphFreqLayout);
        panelGraphFreqLayout.setHorizontalGroup(
            panelGraphFreqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraphFreqLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraphNow, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelGraphFreqLayout.setVerticalGroup(
            panelGraphFreqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraphFreqLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraphNow, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
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
        buttonControlStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonControlStopActionPerformed(evt);
            }
        });

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

        menuItemCrearPista.setText("Crear Pista");
        menuItemCrearPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCrearPistaActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemCrearPista);

        menuItemModificarPista.setText("Modificar Pista");
        menuItemModificarPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemModificarPistaActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemModificarPista);

        menuItemEliminarPista.setText("Eliminar Pista");
        menuItemEliminarPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarPistaActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemEliminarPista);

        menuItemGuardarPista.setText("Guardar Pista");
        menuItemGuardarPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGuardarPistaActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemGuardarPista);

        menuItemRecuperarPista.setText("Recuperar Pista");
        jMenu4.add(menuItemRecuperarPista);

        jMenu1.add(jMenu4);

        jMenu5.setText("Lista");

        menuItemNuevaPista.setText("Nueva Lista");
        menuItemNuevaPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNuevaPistaActionPerformed(evt);
            }
        });
        jMenu5.add(menuItemNuevaPista);

        menuItemGuardarListaRep.setText("Guardar Lista de Reproduccion");
        menuItemGuardarListaRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGuardarListaRepActionPerformed(evt);
            }
        });
        jMenu5.add(menuItemGuardarListaRep);

        menuItemRecuperarListaRep.setText("Recuperar Lista de Reproduccion");
        jMenu5.add(menuItemRecuperarListaRep);

        jMenu1.add(jMenu5);

        menuItemNuevoArchivo.setText("Servir Pista");
        jMenu1.add(menuItemNuevoArchivo);

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

    private void menuItemNuevaPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNuevaPistaActionPerformed
        // TODO add your handling code here:
        typeArchivo = "Lista";
        if (panelCodePane.getComponents().length==0) {
            panelCodePane.add(lines,BorderLayout.WEST);
            panelCodePane.add(lines.scrollPane,BorderLayout.CENTER);
        }
        lines.pane.setText("");
        setInfoFile();
        
    }//GEN-LAST:event_menuItemNuevaPistaActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        // TODO add your handling code here:
        if (pistaSelected && nameFile!=null) {
//            PistaFile pf = fileAccess.cargarClaseDesdeArchivoPista("data/pistas/"+nameFile);
            PistaFile pf = atl.getList().get(indexList);
            lines.pane.setText(pf.getInputText());
            paintText();
            typeArchivo = "Pista";
            tabbedPanelMain.setSelectedIndex(1);
        }
        
        //BUSCA EL INDEX DE LA PISTA
        //Busca la data de la misma
        //SETEA Y ENVIA AL EDITOR
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonEditListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditListActionPerformed
        // TODO add your handling code here:
        if (listFileActual!=null && nameFile!=null && nameFile.equals("Lista")) {
//            PistaFile pf = fileAccess.cargarClaseDesdeArchivoPista("data/pistas/"+nameFile);
            TrackList trl = listFileActual.getList().get(indexList);
            lines.pane.setText(trl.getTxt());
            paintText();
            typeArchivo = "Lista";
            tabbedPanelMain.setSelectedIndex(1);
        }
    }//GEN-LAST:event_buttonEditListActionPerformed

    private void buttonControlPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonControlPlayActionPerformed
        // TODO add your handling code here:
        if (pistaSelected && nameFile!=null) {
            try {
                if (playerSound!=null && playerSound.isAlive()) {
                    playerSound.reanude();
                } else {
                    PistaFile pf = atl.getList().get(indexList);
                    Environment envTemp = new Environment();
                    resetGraph();
                    envTemp.setSeries(series);
                    envTemp.active = true;
    //                traslatorMain.initMainEnv(pf.getNodeRoot(), envTemp);
                    playerSound = new PlayerSound(envTemp, pf.getNodeRoot());

                    playerSound.start();
                }
                
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonControlPlayActionPerformed

    private void buttonControlPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonControlPauseActionPerformed
        // TODO add your handling code here:
        if (playerSound != null) {
            if (playerSound.isAlive()) {
                System.out.println("El hilo está en ejecución.");
                playerSound.pause();
            } else {
                System.out.println("El hilo no está en ejecución.");
                playerSound.reanude();
            }
        }
        
    }//GEN-LAST:event_buttonControlPauseActionPerformed

    private void buttonCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCompileActionPerformed
        try {
            // TODO add your handling code here:
            if (typeArchivo.equals("Pista")) {
                compile();
            }else if(typeArchivo.equals("Lista")) {
                compileList();
            }
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCompileActionPerformed

    private void menuItemCrearPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCrearPistaActionPerformed
        // TODO add your handling code here:
        typeArchivo = "Pista";
        System.out.println("panelCodePane.getComponents().length: "+panelCodePane.getComponents().length);
        if (panelCodePane.getComponents().length==0) {
            panelCodePane.add(lines,BorderLayout.WEST);
            panelCodePane.add(lines.scrollPane,BorderLayout.CENTER);
        }
        lines.pane.setText("");
        System.out.println("panelCodePane.getComponents().length: "+panelCodePane.getComponents().length);
        setInfoFile();
        
        //Si se esta reproduciendo algo detenerlo
        
        
    }//GEN-LAST:event_menuItemCrearPistaActionPerformed

    private void buttonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateActionPerformed
        // TODO add your handling code here:
        typeArchivo = "Pista";
        System.out.println("panelCodePane.getComponents().length: "+panelCodePane.getComponents().length);
        if (panelCodePane.getComponents().length==0) {
            panelCodePane.add(lines,BorderLayout.WEST);
            panelCodePane.add(lines.scrollPane,BorderLayout.CENTER);
        }
        lines.pane.setText("");
        System.out.println("panelCodePane.getComponents().length: "+panelCodePane.getComponents().length);
        setInfoFile();
        tabbedPanelMain.setSelectedIndex(1);
    }//GEN-LAST:event_buttonCreateActionPerformed

    private void listTraksValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listTraksValueChanged
        // TODO add your handling code here:
        if (!evt.getValueIsAdjusting()) {
            // Obtener el elemento seleccionado
            String elementoSeleccionado = listTraks.getSelectedValue();
            nameFile = elementoSeleccionado;
            pistaSelected = true;
            indexList = listTraks.getSelectedIndex();
            // Realizar la acción deseada con el elemento seleccionado
            System.out.println("Elemento seleccionado: " + elementoSeleccionado+", Index: "+listTraks.getSelectedIndex());
            // Agrega aquí tu acción específica
        }
    }//GEN-LAST:event_listTraksValueChanged

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        // TODO add your handling code here:
        if (!evt.getValueIsAdjusting()) {
            // Obtener el elemento seleccionado
            String elementoSeleccionado = jList2.getSelectedValue();
            nameFile = elementoSeleccionado;
            listaSelected = true;
            pistaSelected = true;
            indexList = listTraks.getSelectedIndex();
            for (int i = 0; i < atl.getList().size(); i++) {
                if (atl.getList().get(i).getName().equals(nameFile)) {
                    indexList = i;
                    break;
                }
            }
            // Realizar la acción deseada con el elemento seleccionado
            System.out.println("Elemento seleccionado: " + elementoSeleccionado);
            
            
            
            // Agrega aquí tu acción específica
        }
    }//GEN-LAST:event_jList2ValueChanged

    private void jList3ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList3ValueChanged
        // TODO add your handling code here:
        if (!evt.getValueIsAdjusting()) {
            // Obtener el elemento seleccionado
            String elementoSeleccionado = jList3.getSelectedValue();
            nameFile = elementoSeleccionado;
            pistaSelected = true;
            indexList = jList3.getSelectedIndex();
            // Realizar la acción deseada con el elemento seleccionado
            
            setPistaLista(indexList);
            
            System.out.println("Elemento seleccionado: " + elementoSeleccionado+", index: "+jList3.getSelectedIndex());
            // Agrega aquí tu acción específica
        }
    }//GEN-LAST:event_jList3ValueChanged

    private void buttonPlayTrankCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPlayTrankCodeActionPerformed
        // TODO add your handling code here:
        if (envActual!=null) {
            try {
                envActual.setSynth(MidiSystem.getSynthesizer());
                envActual.getTh().run();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_buttonPlayTrankCodeActionPerformed

    private void buttonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPlayActionPerformed
        // TODO add your handling code here:
        if (pistaSelected && nameFile!=null) {
            try {
                if (playerSound != null && playerSound.isAlive()) {
                    playerSound.reanude();
                } else {
                    PistaFile pf = atl.getList().get(indexList);
                    Environment envTemp = new Environment();
                    resetGraph();
                    envTemp.setSeries(series);
                    envTemp.active = true;
    //                traslatorMain.initMainEnv(pf.getNodeRoot(), envTemp);
                    playerSound = new PlayerSound(envTemp, pf.getNodeRoot());

                    playerSound.start();
                }
                
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonPlayActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        // TODO add your handling code here:
        if (pistaSelected && nameFile!=null) {
            fileAccess.deleteFile("data/pistas/"+nameFile);
            atl.getList().remove(indexList);
            fileAccess.guardarClaseEnArchivoAllPista(atl, "data/allpistas");
            updatePistas();
        }else if (!listaSelected && nameFile!=null) {
//            listFileActual.getList().remove(indexList);
//            fileAccess.guardarClaseEnArchivoLista(listFileActual, "data/alllistas");
//            updateListas();
        }
        
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void menuItemModificarPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemModificarPistaActionPerformed
        // TODO add your handling code here:
        if (pistaSelected && nameFile!=null) {
//            PistaFile pf = fileAccess.cargarClaseDesdeArchivoPista("data/pistas/"+nameFile);
            PistaFile pf = atl.getList().get(indexList);
            lines.pane.setText(pf.getInputText());
            paintText();
            typeArchivo = "Pista";
            tabbedPanelMain.setSelectedIndex(1);
        }
    }//GEN-LAST:event_menuItemModificarPistaActionPerformed

    private void menuItemEliminarPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarPistaActionPerformed
        // TODO add your handling code here:
        if (pistaSelected && nameFile!=null) {
            fileAccess.deleteFile("data/pistas/"+nameFile);
            atl.getList().remove(indexList);
            updatePistas();
            JOptionPane.showMessageDialog(this, "Pista eliminado ");
        }else if (!pistaSelected && nameFile!=null) {
            fileAccess.deleteFile("data/listas/"+nameFile);
            updateListas();
            JOptionPane.showMessageDialog(this, "Lista eliminada");
        }
    }//GEN-LAST:event_menuItemEliminarPistaActionPerformed

    private void menuItemGuardarPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGuardarPistaActionPerformed
        // TODO add your handling code here:
        if (envActual!=null) {
            saveFileOnAllPist(envActual);
        }
        
    }//GEN-LAST:event_menuItemGuardarPistaActionPerformed

    private void buttonControlStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonControlStopActionPerformed
        // TODO add your handling code here:
        if (playerSound!=null) {
            playerSound.kill();
        }
    }//GEN-LAST:event_buttonControlStopActionPerformed

    private void menuItemGuardarListaRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGuardarListaRepActionPerformed
        // TODO add your handling code here:
        if(typeArchivo.equals("Lista")) {
            try {
                compileList();
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        updateListas();
    }//GEN-LAST:event_menuItemGuardarListaRepActionPerformed

    private void buttonDeletListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeletListActionPerformed
        // TODO add your handling code here:
        if (!listaSelected && nameFile!=null) {
            listFileActual.getList().remove(indexList);
            fileAccess.guardarClaseEnArchivoLista(listFileActual, "data/alllistas");
            updateListas();
        }
    }//GEN-LAST:event_buttonDeletListActionPerformed


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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelColumn;
    private javax.swing.JLabel labelColumnInfo;
    private javax.swing.JLabel labelFila;
    private javax.swing.JLabel labelFilaInfo;
    private javax.swing.JLabel labelInfoFile;
    private javax.swing.JLabel labelListPlayer;
    private javax.swing.JLabel labelListsPlayers;
    private javax.swing.JLabel labelTimeEnd;
    private javax.swing.JLabel labelTimeNow;
    private javax.swing.JList<String> listTraks;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemCrearPista;
    private javax.swing.JMenuItem menuItemEliminarPista;
    private javax.swing.JMenuItem menuItemGuardarListaRep;
    private javax.swing.JMenuItem menuItemGuardarPista;
    private javax.swing.JMenuItem menuItemModificarPista;
    private javax.swing.JMenuItem menuItemNuevaPista;
    private javax.swing.JMenuItem menuItemNuevoArchivo;
    private javax.swing.JMenuItem menuItemRecuperarListaRep;
    private javax.swing.JMenuItem menuItemRecuperarPista;
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
