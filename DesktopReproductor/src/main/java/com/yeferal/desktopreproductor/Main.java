/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yeferal.desktopreproductor;

import com.yeferal.desktopreproductor.ast.main.Node;
import com.yeferal.desktopreproductor.ast.main.Primitive;
import com.yeferal.desktopreproductor.ast.main.tablesymbol.DataType;
import com.yeferal.desktopreproductor.ast.main.tree.ConverterDataType;
import com.yeferal.desktopreproductor.gui.MainWindow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
//        List<Node> list = new ArrayList<>();
//        list.add(new Primitive(null, DataType.ENTERO, 1));
//        list.add((Node) null);
//        list.add(new Primitive(null, DataType.ENTERO, 3));
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }   

//        int [] ar = new int[10/3];
//        System.out.println(ar.length);
        int [][] ar = new int[3][2];
        ar[0][0]= 0;
        ar[0][1]= 1;
        ar[1][0]= 2;
        ar[1][1]= 3;
        ar[2][0]= 4;
        ar[2][1]= 5;
        /*
        {{0,1},{2,3},{4,5}}
        */
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                System.out.print(ar[i][j] + " ");
            }
            System.out.println(); // Agrega un salto de línea después de cada fila
        }
//        ar = {{2,2},{2,4},{2,6}};
        System.out.println(Arrays.toString(ar));
        
//        System.out.println((val1+val2));

        
    }
    
}
