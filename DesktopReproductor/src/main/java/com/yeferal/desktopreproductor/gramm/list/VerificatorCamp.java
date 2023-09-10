/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor.gramm.list;

import com.yeferal.desktopreproductor.ast.errors.ErrorType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class VerificatorCamp {
    
    public TrackList fromOne(Object a){
        String name = null;
        int random = 0;
        boolean circular = false;
        List<String> list = new ArrayList<>();
        if (a==null) {
            return null;
        }
         // Verifica y asigna valores según el tipo de dato de 'a'
        // Verifica si a, b, c y d son de tipos diferentes antes de asignar valores
        if (a instanceof String) {
            // Verifica y asigna valores según el tipo de dato de 'a'
            name = (String) a;
            // Aquí puedes realizar cualquier otra lógica que necesites con las variables asignadas

            return new TrackList(name, (random==0? false : true), circular, list);
        } else {
            return null;
        }
    }
    
    public TrackList fromTwo(Object a, Object b){
        String name = null;
        int random = 0;
        boolean circular = false;
        List<String> list = new ArrayList<>();
        if (a==null || b==null) {
            return null;
        }
         // Verifica y asigna valores según el tipo de dato de 'a'
        // Verifica si a, b son de tipos diferentes antes de asignar valores
        if (a.getClass() != b.getClass()) {
            // Verifica y asigna valores según el tipo de dato de 'a'
            if (a instanceof String) {
                name = (String) a;
            } else if (a instanceof Integer) {
                random = (Integer) a;
            } else if (a instanceof Boolean) {
                circular = (Boolean) a;
            } else if (a instanceof List<?>) {
                list = (List<String>) a;
            }

            // Verifica y asigna valores según el tipo de dato de 'b'
            if (b instanceof String) {
                name = (String) b;
            } else if (b instanceof Integer) {
                random = (Integer) b;
            } else if (b instanceof Boolean) {
                circular = (Boolean) b;
            } else if (b instanceof List<?>) {
                list = (List<String>) b;
            }

            // Aquí puedes realizar cualquier otra lógica que necesites con las variables asignadas

            return new TrackList(name, (random==0? false : true), circular, list);
        } else {
            return null;
        }
    }
    
    public TrackList fromThree(Object a, Object b, Object c){
        String name = null;
        int random = 0;
        boolean circular = false;
        List<String> list = new ArrayList<>();
        if (a==null || b==null || c==null) {
            return null;
        }
         // Verifica y asigna valores según el tipo de dato de 'a'
        // Verifica si a, b, c son de tipos diferentes antes de asignar valores
        if (a.getClass() != b.getClass() && a.getClass() != c.getClass() && b.getClass() != c.getClass()) {
            // Verifica y asigna valores según el tipo de dato de 'a'
            if (a instanceof String) {
                name = (String) a;
            } else if (a instanceof Integer) {
                random = (Integer) a;
            } else if (a instanceof Boolean) {
                circular = (Boolean) a;
            } else if (a instanceof List<?>) {
                list = (List<String>) a;
            }

            // Verifica y asigna valores según el tipo de dato de 'b'
            if (b instanceof String) {
                name = (String) b;
            } else if (b instanceof Integer) {
                random = (Integer) b;
            } else if (b instanceof Boolean) {
                circular = (Boolean) b;
            } else if (b instanceof List<?>) {
                list = (List<String>) b;
            }

            // Verifica y asigna valores según el tipo de dato de 'c'
            if (c instanceof String) {
                name = (String) c;
            } else if (c instanceof Integer) {
                random = (Integer) c;
            } else if (c instanceof Boolean) {
                circular = (Boolean) c;
            } else if (c instanceof List<?>) {
                list = (List<String>) c;
            }

            // Aquí puedes realizar cualquier otra lógica que necesites con las variables asignadas

            return new TrackList(name, (random==0? false : true), circular, list);
        } else {
            return null;
        }
    }
    
    public TrackList fromFour(Object a, Object b, Object c, Object d){
        String name = null;
        int random = 0;
        boolean circular = false;
        List<String> list = new ArrayList<>();
        if (a==null || b==null || c==null || d==null) {
            return null;
        }
         // Verifica y asigna valores según el tipo de dato de 'a'
        // Verifica si a, b, c y d son de tipos diferentes antes de asignar valores
        if (a.getClass() != b.getClass() && a.getClass() != c.getClass() && a.getClass() != d.getClass() &&
                b.getClass() != c.getClass() && b.getClass() != d.getClass() && c.getClass() != d.getClass()) {
            // Verifica y asigna valores según el tipo de dato de 'a'
            if (a instanceof String) {
                name = (String) a;
            } else if (a instanceof Integer) {
                random = (Integer) a;
            } else if (a instanceof Boolean) {
                circular = (Boolean) a;
            } else if (a instanceof List<?>) {
                list = (List<String>) a;
            }

            // Verifica y asigna valores según el tipo de dato de 'b'
            if (b instanceof String) {
                name = (String) b;
            } else if (b instanceof Integer) {
                random = (Integer) b;
            } else if (b instanceof Boolean) {
                circular = (Boolean) b;
            } else if (b instanceof List<?>) {
                list = (List<String>) b;
            }

            // Verifica y asigna valores según el tipo de dato de 'c'
            if (c instanceof String) {
                name = (String) c;
            } else if (c instanceof Integer) {
                random = (Integer) c;
            } else if (c instanceof Boolean) {
                circular = (Boolean) c;
            } else if (c instanceof List<?>) {
                list = (List<String>) c;
            }

            // Verifica y asigna valores según el tipo de dato de 'd'
            if (d instanceof String) {
                name = (String) d;
            } else if (d instanceof Integer) {
                random = (Integer) d;
            } else if (d instanceof Boolean) {
                circular = (Boolean) d;
            } else if (d instanceof List<?>) {
                list = (List<String>) d;
            }

            // Aquí puedes realizar cualquier otra lógica que necesites con las variables asignadas

            return new TrackList(name, (random==0? false : true), circular, list);
        } else {
            return null;
        }
    }
    
}
