package com.yeferal.desktopreproductor.gramm.editor;

import java_cup.runtime.*;
import java.util.ArrayList;
import java.util.Stack;

%%
%{

    //coidgo de usuario en sintaxis java
    public void printConsole(String s){
        //System.out.print(s);
    }
    String cadena="";
    public Pintar pintar = new Pintar();

    

%}

    //directivas
%public 
%class LexicalAnalyzerPaint
%cupsym SymbolPaintCode
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
    ([>][>][>]*[^\n]*[\n]?)                     {printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SymbolPaintCode.COMENTARIO_LINEA , yycolumn, yyline, yytext());}
    ("<-" [^*] ~"->" | "<-" "-"+ ">")           {printConsole("COMENTARIO: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SymbolPaintCode.COMENTARIO_BLOQUE , yycolumn, yyline, yytext());}

    //signos o simbolos reservados
    "("                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PA_A , yycolumn, yyline, yytext());}
    ")"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PA_C , yycolumn, yyline, yytext());}
    "["                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.CORCHETE_A , yycolumn, yyline, yytext());}
    "]"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.CORCHETE_C , yycolumn, yyline, yytext());}
    "{"                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.LLAVE_A , yycolumn, yyline, yytext());}
    "}"                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.LLAVE_C , yycolumn, yyline, yytext());}
    ","                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.COMA , yycolumn, yyline, yytext());}
    ";"                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PUNTO_COMA , yycolumn, yyline, yytext());}
    "="                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.IGUAL , yycolumn, yyline, yytext());}

    //operadores aritmeticos
    "+"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MAS , yycolumn, yyline, yytext());}
    "-"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MENOS , yycolumn, yyline, yytext());}
    "*"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.POR , yycolumn, yyline, yytext());}
    "/"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.DIVISION , yycolumn, yyline, yytext());}
    "%"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MODULO , yycolumn, yyline, yytext());}
    "^"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.POTENCIA , yycolumn, yyline, yytext());}

    //operadores relacionales
    "=="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.IGUAL_IGUAL , yycolumn, yyline, yytext());}
    "!="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.DIFERENTE , yycolumn, yyline, yytext());}
    ">"                                     {printConsole("Mayor"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MAYOR_Q , yycolumn, yyline, yytext());}
    "<"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MENOR_Q , yycolumn, yyline, yytext());}
    ">="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MAYOR_IGUAL , yycolumn, yyline, yytext());}
    "<="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MENOR_IGUAL , yycolumn, yyline, yytext());}
    "!!"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.IS_NULL , yycolumn, yyline, yytext());}

    //operadores logicos
    "&&"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.AND , yycolumn, yyline, yytext());}
    "!&&"                                   {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.NAND , yycolumn, yyline, yytext());}
    "||"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.OR , yycolumn, yyline, yytext());}
    "!||"                                   {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.NOR , yycolumn, yyline, yytext());}
    "&|"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.XOR , yycolumn, yyline, yytext());}
    "!"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.NOT , yycolumn, yyline, yytext());}

    //Operadores de incremento/decremento
    "+="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MAS_IGUAL , yycolumn, yyline, yytext());}
    "++"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MAS_MAS , yycolumn, yyline, yytext());}
    "--"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MENOS_MENOS , yycolumn, yyline, yytext());}

    //Notas musicales
    "Do"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.DO , yycolumn, yyline, yytext());}
    "Do#"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.DO_S , yycolumn, yyline, yytext());}
    "Re"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.RE , yycolumn, yyline, yytext());}
    "Re#"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.RE_S , yycolumn, yyline, yytext());}
    "Mi"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MI , yycolumn, yyline, yytext());}
    "Fa"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.FA , yycolumn, yyline, yytext());}
    "Fa#"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.FA_S , yycolumn, yyline, yytext());}
    "Sol"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.SOL , yycolumn, yyline, yytext());}
    "Sol#"                                  {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.SOL_S , yycolumn, yyline, yytext());}
    "La"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.LA , yycolumn, yyline, yytext());}
    "La#"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.LA_S , yycolumn, yyline, yytext());}
    "Si"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.SI , yycolumn, yyline, yytext());}
    
    //Palabras Reservadas
    (("P"|"p") "ista")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PISTA, yycolumn, yyline, yytext());}
    (("E"|"e")"xtiende")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.EXTIENDE , yycolumn, yyline, yytext());}
    (("K"|"k") "eep")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.KEEP , yycolumn, yyline, yytext());}
    (("V"|"v")"ar" )                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.VAR , yycolumn, yyline, yytext());}
    (("C"|"c") "adena")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.CADENA_RSV , yycolumn, yyline, yytext());}
    (("E"|"e")"ntero" )                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.ENTERO , yycolumn, yyline, yytext());}
    (("D"|"d") "oble")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.DOBLE , yycolumn, yyline, yytext());}
    (("B"|"b") "oolean")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.BOOLEAN , yycolumn, yyline, yytext());}
    (("C"|"c") "aracter")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.CARACTER_RSV , yycolumn, yyline, yytext());}
    (("A"|"a") "rreglo")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.ARREGLO , yycolumn, yyline, yytext());}
    (("S"|"s")"i")                          {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.SI , yycolumn, yyline, yytext());}
    
    (("S"|"s")"ino")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.SINO , yycolumn, yyline, yytext());}
    (("S"|"s")"ino si")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.SINO_SI , yycolumn, yyline, yytext());}
    
    (("S"|"s")"witch")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.SWITCH , yycolumn, yyline, yytext());}
    (("C"|"c")"aso")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.CASO , yycolumn, yyline, yytext());}
    (("S"|"s")"alir")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.SALIR , yycolumn, yyline, yytext());}
    (("D"|"d")"efault")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.DEFAULT , yycolumn, yyline, yytext());}
    (("P"|"p")"ara")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PARA , yycolumn, yyline, yytext());}
    (("M"|"m")"ientras")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MIENTRAS , yycolumn, yyline, yytext());}
    (("H"|"h")"acer")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.HACER , yycolumn, yyline, yytext());}
    (("C"|"c")"ontinuar")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.CONTINUAR , yycolumn, yyline, yytext());}
    (("R"|"r")"etornar")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.RETORNAR , yycolumn, yyline, yytext());}
    (("V"|"v")"oid")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.VOID , yycolumn, yyline, yytext());}
    (("R"|"r")"eproducir")                  {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.REPRODUCIR , yycolumn, yyline, yytext());}
    (("E"|"e")"sperar")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.ESPERAR , yycolumn, yyline, yytext());}
    (("O"|"o")"rdenar")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.ORDENAR , yycolumn, yyline, yytext());}
    (("A"|"a")"scendente")                  {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.ASCENDENTE , yycolumn, yyline, yytext());}
    (("D"|"d")"escendente")                 {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.DESCENDENTE , yycolumn, yyline, yytext());}
    (("P"|"p")"ares")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PARES , yycolumn, yyline, yytext());}
    (("I"|"i")"mpares")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.IMPARES , yycolumn, yyline, yytext());}
    (("P"|"p")"rimos")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PRIMOS , yycolumn, yyline, yytext());}
    (("S"|"s")"umarizar")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.SUMARIZAR , yycolumn, yyline, yytext());}
    (("L"|"l")"ongitud")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.LONGITUD , yycolumn, yyline, yytext());}
    (("M"|"m")"ensaje")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.MENSAJE , yycolumn, yyline, yytext());}
    (("P"|"p")"rincipal")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PRINCIPAL , yycolumn, yyline, yytext());}
 
    (("F"|"f")"alse")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.FALSE , yycolumn, yyline, yytext());}
    (("T"|"t")"rue")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.TRUE , yycolumn, yyline, yytext());}
    (("F"|"f")"also")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.FALSE , yycolumn, yyline, yytext());}
    (("V"|"v")"erdadero")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.TRUE , yycolumn, yyline, yytext());}

 /*   ("lista"|"nombre")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("random"|"circular")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("pistas"|"true")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("false")                               {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SymbolPaintCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
*/
    //REGULARES
    ({Letra}("_"|{Letra}|{Numero})*)        {printConsole(yytext()); pintar.pintaVerde((int) yychar,yylength()); return new Symbol(SymbolPaintCode.IDENTIFICADOR , yycolumn, yyline, yytext());}
    ({Numero})                              {printConsole(yytext()); pintar.pintaMorado((int) yychar,yylength()); return new Symbol(SymbolPaintCode.NUMERO , yycolumn, yyline, yytext());}
    ({Numero}("\."){Numero})                {printConsole(yytext()); pintar.pintaMorado((int) yychar,yylength()); return new Symbol(SymbolPaintCode.DECIMAL , yycolumn, yyline, yytext());}
    (\"[^\"]*\")                            {printConsole(yytext()); pintar.pintaNaranja((int) yychar,yylength()); return new Symbol(SymbolPaintCode.CADENA , yycolumn, yyline, yytext());}
    ("'"("#'"|"##"|"#r"|"#t"|"#n")"'")      {printConsole(yytext()); pintar.pintaNaranja((int) yychar,yylength()); return new Symbol(SymbolPaintCode.CARACTER , yycolumn, yyline, yytext());}
    (\'[^\']?\')                            {printConsole(yytext()); pintar.pintaNaranja((int) yychar,yylength()); return new Symbol(SymbolPaintCode.CARACTER , yycolumn, yyline, yytext());}


    {Espacio}                               {/*Ignore*/}
    .                           {//System.out.println("CUALQUIER_SIM: "+yytext()); 
                                    //ErrorG e = new ErrorG(yyline+1, yycolumn+1,yytext(),"Lexico","Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                    //listaErrores.add(e);
                                    //return new Symbol(Simbolos.CUALQUIER_SIM , yycolumn, yyline, yytext());
                                            //printConsole("ERR: "+yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); /*return new Symbol(SymbolPaintCode.OTROS , yycolumn, yyline, yytext());*/
                                    }
    
}