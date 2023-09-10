package com.yeferal.desktopreproductor.gramm.list;

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

    

%}

    //directivas
%public 
%class LexicalAnalyzerList
%cupsym SymbolListCode
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

    "lista"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolListCode.lista , yycolumn, yyline, yytext());}
    "nombre"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolListCode.nombre , yycolumn, yyline, yytext());}
    "random"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolListCode.random , yycolumn, yyline, yytext());}
    "circular"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolListCode.circular , yycolumn, yyline, yytext());}
    "pistas"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolListCode.pistas , yycolumn, yyline, yytext());}

    //signos o simbolos reservados
    "["                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolListCode.corchete_a , yycolumn, yyline, yytext());}
    "]"                                     {printConsole(yytext()); return new Symbol(SymbolListCode.corchete_c , yycolumn, yyline, yytext());}
    "{"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolListCode.llave_a , yycolumn, yyline, yytext());}
    "}"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolListCode.llave_c , yycolumn, yyline, yytext());}
    ","                                     {printConsole(yytext()); return new Symbol(SymbolListCode.coma , yycolumn, yyline, yytext());}
    ":"                                     {printConsole(yytext()); return new Symbol(SymbolListCode.dos_puntos , yycolumn, yyline, yytext());}
    
    (("F"|"f")"alse")                       {printConsole(yytext()); return new Symbol(SymbolListCode.false_rsv , yycolumn, yyline, false);}
    (("T"|"t")"rue")                        {printConsole(yytext()); return new Symbol(SymbolListCode.true_rsv , yycolumn, yyline, true);}
    
    ({Letra}("_"|{Letra}|{Numero})*)        {printConsole(yytext()); return new Symbol(SymbolListCode.identificador , yycolumn, yyline, yytext());}
    (\"[^\"]*\")                            {printConsole(yytext()); return new Symbol(SymbolListCode.cadena , yycolumn, yyline, yytext().substring(1, yytext().length() - 1));}
    
    

    {Espacio}                               {/*Ignore*/}
    .                                       {addError(yyline+1, yycolumn+1, yytext(), "Caracter o Token no reconocido");
                                            System.out.println("Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                            //ErrorG e = new ErrorG(yyline+1, yycolumn+1,yytext(),"Lexico","Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                            }
    
}