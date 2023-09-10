package com.yeferal.desktopreproductor.gramm.comunication;

import java_cup.runtime.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.yeferal.desktopreproductor.ast.errors.*;

%%
%{

    List<ErrorGramm> lexialErrors = new ArrayList<>();

    //coidgo de usuario en sintaxis java
    public void printConsole(String s){
        // System.out.print(s+" ");
    }

    public void addError(int row, int column, String token, String description){
        lexialErrors.add(new ErrorGramm(new PositionToken(row, column), ErrorType.LEXICAL, token, description));
    }

    public List<ErrorGramm> getLexicalErrors(){
        return lexialErrors;
    }

    String cadena="";

    // private void cambiarEstado(){
    //     switch(seccionActual){
    //         case 0: yybegin(YYINITIAL); break;
    //         case 1: yybegin(REQUEST); break;
    //     }
    // }
    

%}

    //directivas
%public 
%class LexicalAnalyzerCom
%cupsym SymbolComCode
%cup
%char
%unicode
%line
%column
%full
//%ignorecase
//%unicode

//expreciones regulares

// Color           Token
// Azul        ->  Palabras reservadas
// Verde       ->  Identificadores
// Naranja     ->  Cadenas, caracteres
// Morado      ->  Números
// Gris        ->  Comentario
// Negro       ->  Otro

LineTerminator = \r|\n|\r\n
InputCaracter = [^\r\n]
//WhiteSpace = {LineTerminator} | [ \t\f]

/*Comentarios*/
//Comment = {ComentarioTradicional} | {FinLineaComment} | {DocumentoComentado}
CommentBloque = ("<-" [^*] ~"->" | "<-" "-"+ ">")
CommentSimple = (">" ">" {InputCaracter}* {LineTerminator}?)
//CommentContenido = ( [^*] | \*+ [^/*] )*


Signo               = [-_.!@#%&*|/]
Letra               = [a-záéíóúA-ZÁÉÍÓÚÑñ]
Digito              = [0-9]
Numero              = {Digito} {Digito}*
Palabra             = {Letra}+
Cadena              = ("\"" ([^\"]* | {Letra}) "\"" )
LqSea               =  ({Signo}|{Letra}|{Numero}|{Cadena})*
Espacio             = [" "\r\t\b\n]+
Blancos               = [" "\r\t\b\f""]
%%


<YYINITIAL> {

    "<solicitud>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.solicitud_a , yycolumn, yyline, yytext());}
    "</solicitud>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.solicitud_c , yycolumn, yyline, yytext());}
    "Lista"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.lista , yycolumn, yyline, yytext());}
    "Pista"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pista , yycolumn, yyline, yytext());}
    "<tipo>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.tipo_a , yycolumn, yyline, yytext());}
    "</tipo>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.tipo_c , yycolumn, yyline, yytext());}
    "<nombre>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nombre_a , yycolumn, yyline, yytext());}
    "</nombre>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nombre_c , yycolumn, yyline, yytext());}
    "<listas>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.listas_a , yycolumn, yyline, yytext());}
    "</listas>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.listas_c , yycolumn, yyline, yytext());}
    "<lista"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.lista_a , yycolumn, yyline, yytext());}
    "</lista>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.lista_c , yycolumn, yyline, yytext());}
    "nombre"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nombre , yycolumn, yyline, yytext());}
    "pistas"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pistas , yycolumn, yyline, yytext());}
    "<pista"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pista_ab , yycolumn, yyline, yytext());}
    "duracion"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.duracion , yycolumn, yyline, yytext());}
    "aleatoria"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.aleatorio , yycolumn, yyline, yytext());}
    (\""SI"\")                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.si_rsv , yycolumn, yyline, yytext());}
    (\""NO"\")                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.no , yycolumn, yyline, yytext());}
    "<pistas>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pistas_a , yycolumn, yyline, yytext());}
    "</pistas>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pistas_c , yycolumn, yyline, yytext());}
    "<canal"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.canal_ab , yycolumn, yyline, yytext());}
    "numero"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.numero_rsv , yycolumn, yyline, yytext());}
    "</canal>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.canal_c , yycolumn, yyline, yytext());}
    "<nota"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nota_ab , yycolumn, yyline, yytext());}
    "frecuencia"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.frecuencia , yycolumn, yyline, yytext());}
    "pistanueva"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pistanueva , yycolumn, yyline, yytext());}
    "<datos>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.datos_a , yycolumn, yyline, yytext());}
    "</datos>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.datos_c , yycolumn, yyline, yytext());}
    "<canal>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.canal_a , yycolumn, yyline, yytext());}
    "<nota>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nota_a , yycolumn, yyline, yytext());}
    "</nota>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nota_c , yycolumn, yyline, yytext());}
    "<octava>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.octava_a , yycolumn, yyline, yytext());}
    "</octava>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.octava_c , yycolumn, yyline, yytext());}
    "<duracion>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.duracion_a , yycolumn, yyline, yytext());}
    "</duracion>"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.duracion_c , yycolumn, yyline, yytext());}

    //signos o simbolos reservados
    ">"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.cerrar , yycolumn, yyline, yytext());}
    "="                                     {printConsole(yytext()); return new Symbol(SymbolComCode.igual , yycolumn, yyline, yytext());}
    
    //Notas musicales
    "Do"                                    {printConsole(yytext()); return new Symbol(SymbolComCode.do_rsv , yycolumn, yyline, yytext());}
    "Do#"                                   {printConsole(yytext()); return new Symbol(SymbolComCode.do_s , yycolumn, yyline, yytext());}
    "Re"                                    {printConsole(yytext()); return new Symbol(SymbolComCode.re , yycolumn, yyline, yytext());}
    "Re#"                                   {printConsole(yytext()); return new Symbol(SymbolComCode.re_s , yycolumn, yyline, yytext());}
    "Mi"                                    {printConsole(yytext()); return new Symbol(SymbolComCode.mi , yycolumn, yyline, yytext());}
    "Fa"                                    {printConsole(yytext()); return new Symbol(SymbolComCode.fa , yycolumn, yyline, yytext());}
    "Fa#"                                   {printConsole(yytext()); return new Symbol(SymbolComCode.fa_s , yycolumn, yyline, yytext());}
    "Sol"                                   {printConsole(yytext()); return new Symbol(SymbolComCode.sol , yycolumn, yyline, yytext());}
    "Sol#"                                  {printConsole(yytext()); return new Symbol(SymbolComCode.sol_s , yycolumn, yyline, yytext());}
    "La"                                    {printConsole(yytext()); return new Symbol(SymbolComCode.la , yycolumn, yyline, yytext());}
    "La#"                                   {printConsole(yytext()); return new Symbol(SymbolComCode.la_s , yycolumn, yyline, yytext());}
    "Si"                                    {printConsole(yytext()); return new Symbol(SymbolComCode.si , yycolumn, yyline, yytext());}
    

    // ({Letra}("_"|{Letra}|{Numero})*)        {printConsole(yytext()); return new Symbol(SymbolComCode.identificador , yycolumn, yyline, yytext());}
    ({Numero})                              {printConsole(yytext()); return new Symbol(SymbolComCode.numero , yycolumn, yyline, Integer.parseInt(yytext()));}
    ({Numero}("\."){Numero})                {printConsole(yytext()); return new Symbol(SymbolComCode.decimal , yycolumn, yyline, Double.parseDouble(yytext()));}
    (\"[^\"]*\")                            {printConsole(yytext()); return new Symbol(SymbolComCode.cadena , yycolumn, yyline, yytext().substring(1, yytext().length() - 1));}
    
    

    {Espacio}                               {/*Ignore*/}
    .                                       {addError(yyline+1, yycolumn+1, yytext(), "Caracter o Token no reconocido");
                                            System.out.println("Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                            //ErrorG e = new ErrorG(yyline+1, yycolumn+1,yytext(),"Lexico","Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                            }
    
}