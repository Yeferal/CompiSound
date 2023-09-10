package com.yeferal.desktopreproductor.gramm.main;

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
%class LexicalAnalyzerMain
%cupsym SymbolMainCode
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
    ([>][>][>]*[^\n]*[\n]?)                     {printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n"); return new Symbol(SymbolMainCode.comentario_linea , yycolumn, yyline, yytext());}
    ("<-" [^*] ~"->" | "<-" "-"+ ">")           {printConsole("COMENTARIO: "+yytext()+"\n"); return new Symbol(SymbolMainCode.comentario_bloque , yycolumn, yyline, yytext());}

    //signos o simbolos reservados
    "("                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolMainCode.pa_a , yycolumn, yyline, yytext());}
    ")"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.pa_c , yycolumn, yyline, yytext());}
    "["                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolMainCode.corchete_a , yycolumn, yyline, yytext());}
    "]"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.corchete_c , yycolumn, yyline, yytext());}
    "{"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolMainCode.llave_a , yycolumn, yyline, yytext());}
    "}"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolMainCode.llave_c , yycolumn, yyline, yytext());}
    ","                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.coma , yycolumn, yyline, yytext());}
    ";"                                     {printConsole(yytext()+"\n"); return new Symbol(SymbolMainCode.punto_coma , yycolumn, yyline, yytext());}
    ":"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.dos_puntos , yycolumn, yyline, yytext());}
    "="                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.igual , yycolumn, yyline, yytext());}

    //operadores aritmeticos
    "+"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.mas , yycolumn, yyline, yytext());}
    "-"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.menos , yycolumn, yyline, yytext());}
    "*"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.por , yycolumn, yyline, yytext());}
    "/"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.div , yycolumn, yyline, yytext());}
    "%"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.modulo , yycolumn, yyline, yytext());}
    "^"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.potencia , yycolumn, yyline, yytext());}

    //operadores relacionales
    "=="                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.igual_igual , yycolumn, yyline, yytext());}
    "!="                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.diferente , yycolumn, yyline, yytext());}
    ">"                                     {printConsole("Mayor"); return new Symbol(SymbolMainCode.mayor_q , yycolumn, yyline, yytext());}
    "<"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.menor_q , yycolumn, yyline, yytext());}
    ">="                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.mayor_igual , yycolumn, yyline, yytext());}
    "<="                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.menor_igual , yycolumn, yyline, yytext());}
    "!!"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.is_null , yycolumn, yyline, yytext());}

    //operadores logicos
    "&&"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.and , yycolumn, yyline, yytext());}
    "!&&"                                   {printConsole(yytext()); return new Symbol(SymbolMainCode.nand , yycolumn, yyline, yytext());}
    "||"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.or , yycolumn, yyline, yytext());}
    "!||"                                   {printConsole(yytext()); return new Symbol(SymbolMainCode.nor , yycolumn, yyline, yytext());}
    "&|"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.xor , yycolumn, yyline, yytext());}
    "!"                                     {printConsole(yytext()); return new Symbol(SymbolMainCode.not , yycolumn, yyline, yytext());}

    //Operadores de incremento/decremento
    "+="                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.mas_igual , yycolumn, yyline, yytext());}
    "++"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.mas_mas , yycolumn, yyline, yytext());}
    "--"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.menos_menos , yycolumn, yyline, yytext());}

    //Notas musicales
    "Do"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.do_rsv , yycolumn, yyline, yytext());}
    "Do#"                                   {printConsole(yytext()); return new Symbol(SymbolMainCode.do_s , yycolumn, yyline, yytext());}
    "Re"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.re , yycolumn, yyline, yytext());}
    "Re#"                                   {printConsole(yytext()); return new Symbol(SymbolMainCode.re_s , yycolumn, yyline, yytext());}
    "Mi"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.mi , yycolumn, yyline, yytext());}
    "Fa"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.fa , yycolumn, yyline, yytext());}
    "Fa#"                                   {printConsole(yytext()); return new Symbol(SymbolMainCode.fa_s , yycolumn, yyline, yytext());}
    "Sol"                                   {printConsole(yytext()); return new Symbol(SymbolMainCode.sol , yycolumn, yyline, yytext());}
    "Sol#"                                  {printConsole(yytext()); return new Symbol(SymbolMainCode.sol_s , yycolumn, yyline, yytext());}
    "La"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.la , yycolumn, yyline, yytext());}
    "La#"                                   {printConsole(yytext()); return new Symbol(SymbolMainCode.la_s , yycolumn, yyline, yytext());}
    "Si"                                    {printConsole(yytext()); return new Symbol(SymbolMainCode.si , yycolumn, yyline, yytext());}
    
    //Palabras Reservadas
    (("P"|"p") "ista")                      {printConsole(yytext()); return new Symbol(SymbolMainCode.pista, yycolumn, yyline, yytext());}
    (("E"|"e")"xtiende")                    {printConsole(yytext()); return new Symbol(SymbolMainCode.extiende , yycolumn, yyline, yytext());}
    (("K"|"k") "eep")                       {printConsole(yytext()); return new Symbol(SymbolMainCode.keep , yycolumn, yyline, yytext());}
    (("V"|"v")"ar" )                        {printConsole(yytext()); return new Symbol(SymbolMainCode.var , yycolumn, yyline, yytext());}
    (("C"|"c") "adena")                     {printConsole(yytext()); return new Symbol(SymbolMainCode.cadena_rsv , yycolumn, yyline, yytext());}
    (("E"|"e")"ntero" )                     {printConsole(yytext()); return new Symbol(SymbolMainCode.entero , yycolumn, yyline, yytext());}
    (("D"|"d") "oble")                      {printConsole(yytext()); return new Symbol(SymbolMainCode.doble , yycolumn, yyline, yytext());}
    (("B"|"b") "oolean")                    {printConsole(yytext()); return new Symbol(SymbolMainCode.boolean_rsv , yycolumn, yyline, yytext());}
    (("C"|"c") "aracter")                   {printConsole(yytext()); return new Symbol(SymbolMainCode.caracter_rsv , yycolumn, yyline, yytext());}
    (("A"|"a") "rreglo")                    {printConsole(yytext()); return new Symbol(SymbolMainCode.arreglo , yycolumn, yyline, yytext());}
    (("S"|"s")"i")                          {printConsole(yytext()); return new Symbol(SymbolMainCode.si , yycolumn, yyline, yytext());}
    
    (("S"|"s")"ino")                        {printConsole(yytext()); return new Symbol(SymbolMainCode.sino , yycolumn, yyline, yytext());}
    (("S"|"s")"ino si")                     {printConsole(yytext()); return new Symbol(SymbolMainCode.sino_si , yycolumn, yyline, yytext());}
    
    (("S"|"s")"witch")                      {printConsole(yytext()); return new Symbol(SymbolMainCode.switch_rsv , yycolumn, yyline, yytext());}
    (("C"|"c")"aso")                        {printConsole(yytext()); return new Symbol(SymbolMainCode.caso , yycolumn, yyline, yytext());}
    (("S"|"s")"alir")                       {printConsole(yytext()); return new Symbol(SymbolMainCode.salir , yycolumn, yyline, yytext());}
    (("D"|"d")"efault")                     {printConsole(yytext()); return new Symbol(SymbolMainCode.default_rsv , yycolumn, yyline, yytext());}
    (("P"|"p")"ara")                        {printConsole(yytext()); return new Symbol(SymbolMainCode.para , yycolumn, yyline, yytext());}
    (("M"|"m")"ientras")                    {printConsole(yytext()); return new Symbol(SymbolMainCode.mientras , yycolumn, yyline, yytext());}
    (("H"|"h")"acer")                       {printConsole(yytext()); return new Symbol(SymbolMainCode.hacer , yycolumn, yyline, yytext());}
    (("C"|"c")"ontinuar")                   {printConsole(yytext()); return new Symbol(SymbolMainCode.continuar , yycolumn, yyline, yytext());}
    (("R"|"r")"etornar")                    {printConsole(yytext()); return new Symbol(SymbolMainCode.retornar , yycolumn, yyline, yytext());}
    (("V"|"v")"oid")                        {printConsole(yytext()); return new Symbol(SymbolMainCode.void_rsv , yycolumn, yyline, yytext());}
    (("R"|"r")"eproducir")                  {printConsole(yytext()); return new Symbol(SymbolMainCode.reproducir , yycolumn, yyline, yytext());}
    (("E"|"e")"sperar")                     {printConsole(yytext()); return new Symbol(SymbolMainCode.esperar , yycolumn, yyline, yytext());}
    (("O"|"o")"rdenar")                     {printConsole(yytext()); return new Symbol(SymbolMainCode.ordenar , yycolumn, yyline, yytext());}
    (("A"|"a")"scendente")                  {printConsole(yytext()); return new Symbol(SymbolMainCode.ascendente , yycolumn, yyline, yytext());}
    (("D"|"d")"escendente")                 {printConsole(yytext()); return new Symbol(SymbolMainCode.descendente , yycolumn, yyline, yytext());}
    (("P"|"p")"ares")                       {printConsole(yytext()); return new Symbol(SymbolMainCode.pares , yycolumn, yyline, yytext());}
    (("I"|"i")"mpares")                     {printConsole(yytext()); return new Symbol(SymbolMainCode.impares , yycolumn, yyline, yytext());}
    (("P"|"p")"rimos")                      {printConsole(yytext()); return new Symbol(SymbolMainCode.primos , yycolumn, yyline, yytext());}
    (("S"|"s")"umarizar")                   {printConsole(yytext()); return new Symbol(SymbolMainCode.sumarizar , yycolumn, yyline, yytext());}
    (("L"|"l")"ongitud")                    {printConsole(yytext()); return new Symbol(SymbolMainCode.longitud , yycolumn, yyline, yytext());}
    (("M"|"m")"ensaje")                     {printConsole(yytext()); return new Symbol(SymbolMainCode.mensaje , yycolumn, yyline, yytext());}
    (("P"|"p")"rincipal")                   {printConsole(yytext()); return new Symbol(SymbolMainCode.principal , yycolumn, yyline, yytext());}
 
    (("F"|"f")"alse")                       {printConsole(yytext()); return new Symbol(SymbolMainCode.false_rsv , yycolumn, yyline, false);}
    (("T"|"t")"rue")                        {printConsole(yytext()); return new Symbol(SymbolMainCode.true_rsv , yycolumn, yyline, true);}
    (("F"|"f")"also")                       {printConsole(yytext()); return new Symbol(SymbolMainCode.false_rsv , yycolumn, yyline, false);}
    (("V"|"v")"erdadero")                   {printConsole(yytext()); return new Symbol(SymbolMainCode.true_rsv , yycolumn, yyline, true);}

    //REGULARES
    ({Letra}("_"|{Letra}|{Numero})*)        {printConsole(yytext()); return new Symbol(SymbolMainCode.identificador , yycolumn, yyline, yytext());}
    ({Numero})                              {printConsole(yytext()); return new Symbol(SymbolMainCode.numero , yycolumn, yyline, Integer.parseInt(yytext()));}
    ({Numero}("\."){Numero})                {printConsole(yytext()); return new Symbol(SymbolMainCode.decimal , yycolumn, yyline, Double.parseDouble(yytext()));}
    (\"[^\"]*\")                            {printConsole(yytext()); return new Symbol(SymbolMainCode.cadena , yycolumn, yyline, yytext().substring(1, yytext().length() - 1));}
    // ("'"("#'"|"##"|"#r"|"#t"|"#n")"'")      {printConsole(yytext()); return new Symbol(SymbolMainCode.caracter_special , yycolumn, yyline, yytext());}
    ("'"("#'")"'")                          {printConsole(yytext()); return new Symbol(SymbolMainCode.caracter_special_comilla_simple , yycolumn, yyline, yytext().substring(2, yytext().length() - 1));}
    ("'"("##")"'")                          {printConsole(yytext()); return new Symbol(SymbolMainCode.caracter_special_hashtag , yycolumn, yyline, yytext().substring(2, yytext().length() - 1));}
    ("'"("#r")"'")                          {printConsole(yytext()); return new Symbol(SymbolMainCode.caracter_special_r , yycolumn, yyline, yytext().substring(2, yytext().length() - 1));}
    ("'"("#t")"'")                          {printConsole(yytext()); return new Symbol(SymbolMainCode.caracter_special_t , yycolumn, yyline, yytext().substring(2, yytext().length() - 1));}
    ("'"("#n")"'")                          {printConsole(yytext()); return new Symbol(SymbolMainCode.caracter_special_n , yycolumn, yyline, yytext().substring(2, yytext().length() - 1));}

    (\'[^\']?\')                            {printConsole(yytext()); return new Symbol(SymbolMainCode.caracter , yycolumn, yyline, yytext().substring(1, yytext().length() - 1));}


    {Espacio}                               {/*Ignore*/}
    .                                       {addError(yyline+1, yycolumn+1, yytext(), "Caracter o Token no reconocido");
                                            System.out.println("Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                            //ErrorG e = new ErrorG(yyline+1, yycolumn+1,yytext(),"Lexico","Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                            }
    
}