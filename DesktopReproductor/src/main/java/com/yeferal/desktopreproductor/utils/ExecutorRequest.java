/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.utils;

import com.yeferal.desktopreproductor.ast.errors.ErrorGramm;
import com.yeferal.desktopreproductor.ast.errors.PositionToken;
import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.Primitive;
import com.yeferal.desktopreproductor.ast.main.Principal;
import com.yeferal.desktopreproductor.ast.main.TrackNode;
import com.yeferal.desktopreproductor.ast.main.instructions.ExeEsperar;
import com.yeferal.desktopreproductor.ast.main.instructions.ExeReproducir;
import com.yeferal.desktopreproductor.ast.main.instructions.InstReproducir;
import com.yeferal.desktopreproductor.ast.main.instructions.SimpleEsperar;
import com.yeferal.desktopreproductor.ast.main.instructions.SimpleReproducir;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.gramm.comunication.LexicalAnalyzerCom;
import com.yeferal.desktopreproductor.gramm.comunication.SyntaxAnalyzerCom;
import com.yeferal.desktopreproductor.gramm.comunication.obj.DataPista;
import com.yeferal.desktopreproductor.gramm.comunication.obj.Solicitud;
import com.yeferal.desktopreproductor.utils.filesadm.AllTrackList;
import com.yeferal.desktopreproductor.utils.filesadm.FileAccess;
import com.yeferal.desktopreproductor.utils.filesadm.FrecuencysNotes;
import com.yeferal.desktopreproductor.utils.filesadm.ListaFile;
import com.yeferal.desktopreproductor.utils.filesadm.PistaFile;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ExecutorRequest {
    
    private FileAccess fileAccess = new FileAccess();
    private FrecuencysNotes frecuencysNotes = new FrecuencysNotes();
    
    //Procesador de Solicitude
    public String executeReques(String input){
        System.out.println("Entrada:\n"+input);
        LexicalAnalyzerCom lexicalAnalyzerCom = new LexicalAnalyzerCom(new StringReader(input));
        SyntaxAnalyzerCom syntaxAnalyzerCom = new SyntaxAnalyzerCom(lexicalAnalyzerCom);
        try {
            syntaxAnalyzerCom.parse();
            
            List<ErrorGramm> lexicalErrors = lexicalAnalyzerCom.getLexicalErrors();
            List<ErrorGramm> syntaxErrors = syntaxAnalyzerCom.getSyntaxErrors();
            
            if (lexicalErrors.size()==0 && syntaxErrors.size()==0) {
                Object obj = syntaxAnalyzerCom.getRes();
                System.out.println(obj);
                return runRequest(obj);
            }else {
                StringBuilder txt = new StringBuilder();
                for (ErrorGramm lexicalError : lexicalErrors) {
                    txt.append(lexicalError.getStringError()).append("\n");
                }
                for (ErrorGramm syntaxError : syntaxErrors) {
                    txt.append(syntaxError.getStringError()).append("\n");
                }
                System.out.println(txt.toString());
                return txt.toString();
            }
            
            //Ahota toca hacer la funcionalidad de cada solicitud
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Hola a todos como estan";
    }
    
    public String runRequest(Object obj){
        if (obj instanceof Solicitud) {
            Solicitud solicitud = (Solicitud) obj;
            switch(solicitud.getRequestType()){
                case LISTA:
                    return getListasListas();
                case PISTA:
                    return getListaPistas();
                case PISTANUEVA:
                    return runPistaNueva(solicitud);
            }
        }else {
            
        }
        return "";
    }
    
    public String getListasListas(){
        ListaFile lf = fileAccess.cargarClaseDesdeArchivoLista("data/alllistas");
        AllTrackList listPistas = fileAccess.cargarClaseDesdeArchivoAllPista("data/allpistas");
        StringBuilder txt = new StringBuilder();
        StringBuilder txt2 = new StringBuilder();
        txt.append("<listas>").append("\n");
        
        int contador = 0;
        for (int i = 0; i < lf.getList().size(); i++) {
            
            txt2.append("<lista nombre = \"").append(lf.getList().get(i).getName()).append("\" aleatoria = \"");
            txt2.append(lf.getList().get(i).isRandom()? "SI" : "NO").append("\" >\n");
            
            for (int j = 0; j < lf.getList().get(i).getListTracks().size(); j++) {
                for (int k = 0; k < listPistas.getList().size(); k++) {
                    if (lf.getList().get(i).getListTracks().get(j).equals(listPistas.getList().get(k).getName())) {
                        txt2.append("\t<pista nombre = \"").append(listPistas.getList().get(k).getName()).append("\" ");
                        txt2.append("duracion = 14500 >\n");
                        break;
                    }
                }
                
            }
            
            txt2.append("</lista>\n");
            
            txt.append("\t<lista nombre = \"").append(lf.getList().get(i).getName()).append("\" ");
            txt.append("pistas = ").append(lf.getList().get(i).getListTracks().size()).append(">\n");
            
            
            
        }
        txt.append("</listas>\n");
        txt.append(txt2);
        
        return txt.toString();
    }
    
    public String getListaPistas(){
        AllTrackList listPistas = fileAccess.cargarClaseDesdeArchivoAllPista("data/allpistas");
        StringBuilder txt = new StringBuilder();
        StringBuilder txt2 = new StringBuilder();
        txt.append("<pistas>").append("\n");
        for (int i = 0; i < listPistas.getList().size(); i++) {
            txt.append("\t<pista nombre = \"").append(listPistas.getList().get(i).getName());
            txt.append("\" duracion = 14500 >\n");
            
            txt2.append("<pista nombre = \"").append(listPistas.getList().get(i).getName()).append("\" >\n");
            for (int k = 0; k < 9; k++) {
                txt2.append("\t<canal numero = ").append(k+">\n");
                System.out.println("Tamanio: "+listPistas.getList().get(i).getListExecutes().size());
                for (int j = 0; j < listPistas.getList().get(i).getListExecutes().size(); j++) {
                    if (listPistas.getList().get(i).getListExecutes().get(j) instanceof SimpleReproducir) {
                        SimpleReproducir er = (SimpleReproducir) listPistas.getList().get(i).getListExecutes().get(j);
                        System.out.println(er.getChannel() + " == " + k);
                        if (er.getChannel() == k) {
                            txt2.append("\t\t<nota duracion = ").append(er.getTime());
                            txt2.append(" frecuencia = ").append(frecuencysNotes.getFrecency(er.getNote(), er.getOctave()));
                            txt2.append(" >\n");
                        }
                        
                    }else {
                        SimpleEsperar ee = (SimpleEsperar) listPistas.getList().get(i).getListExecutes().get(j);
                        if (ee.getChannel() == k) {
                            txt2.append("\t\t<nota duracion = ").append(ee.getValue());
                            txt2.append(" frecuencia = ").append(0.00);
                            txt2.append(" >\n");
                        }
                        

                    }
                }
                txt2.append("\t</canal>\n");
            }
            txt2.append("</pista>\n");
        }
        txt.append("</pistas>").append("\n");
        txt.append(txt2);
        return txt.toString();
    }
    
    public String runPistaNueva(Solicitud solicitud){
        String namePista = solicitud.getName();
        List<DataPista> listData = solicitud.getListData();
        List<Object> listExecutes = new ArrayList<>();
        List<Node> instructions = new ArrayList<>();
        List<Node> instructionsPrin = new ArrayList<>();
        AllTrackList atl = new AllTrackList(new ArrayList<>());
        
        StringBuilder txt = new StringBuilder();
        
        txt.append("Pista "+namePista+"{\n");
        txt.append("\tPrincipal(){\n");
        for (int i = 0; i < listData.size(); i++) {
            DataPista dp = listData.get(i);
            listExecutes.add(new SimpleReproducir(dp.getNote(), dp.getOctave(), dp.getDuracion(), dp.getChannel()));
            instructionsPrin.add(new InstReproducir(
                    dp.getNote(), 
                    new Primitive(
                            new PositionToken(1, 1), 
                            DataType.ENTERO, 
                            dp.getOctave()), 
                    new Primitive(
                            new PositionToken(1, 1), 
                            DataType.ENTERO, 
                            dp.getDuracion()) , 
                    new Primitive(
                            new PositionToken(1, 1), 
                            DataType.ENTERO, 
                            dp.getChannel()), 
                    new PositionToken(1, 1)
            ));
            txt.append("\t\tReproducir(");
            txt.append(dp.getValNoteString()+", ").append(dp.getOctave()+", ").append(dp.getDuracion()+", ").append(dp.getChannel());
            txt.append(");\n");
        }
        txt.append("\t}\n");
        txt.append("}\n");
        try {
            Node nodePrincipal = new Principal(instructionsPrin, new PositionToken(1,1));
            instructions.add(nodePrincipal);
            TrackNode track = new TrackNode(namePista, new ArrayList<>(), instructions, new PositionToken(1,1), null);
            PistaFile pf = new PistaFile(namePista, txt.toString(), track);
            pf.setListExecutes(listExecutes);
            atl = fileAccess.cargarClaseDesdeArchivoAllPista("data/allpistas");
            atl.addPista(pf);
            fileAccess.guardarClaseEnArchivoAllPista(atl, "data/allpistas");
            return "Pista Nueva agregada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se puedo crear la pista nueva";
    }
    
}
