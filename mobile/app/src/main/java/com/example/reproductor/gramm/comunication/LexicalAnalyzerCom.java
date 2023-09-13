/* The following code was generated by JFlex 1.4.3 on 12/9/23 21:53 */

package com.example.reproductor.gramm.comunication;

import java_cup.runtime.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.example.reproductor.gramm.errors.*;
import com.example.reproductor.gramm.notas.*;
import com.example.reproductor.gramm.comunication.obj.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 12/9/23 21:53 from the specification file
 * <tt>LexCom.jflex</tt>
 */
public class LexicalAnalyzerCom implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  1,  1,  1,  0,  0,  1,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     1,  0,  6, 33,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 38, 15, 
     5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  0,  0,  2, 31,  3,  0, 
     0,  4,  4,  4, 32,  4, 36,  4,  4, 26,  4,  4, 16, 35, 27, 28, 
    18,  4, 34, 25,  4,  4,  4,  4,  4,  4,  4,  0,  0,  0,  0, 37, 
     0, 17, 22, 11, 14, 24, 29,  4,  4, 10,  4,  4,  9, 21, 20,  8, 
    19,  4, 23,  7, 12, 13, 30,  4,  4,  4,  4,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  4,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  4,  0,  0, 
     0,  4,  0,  4,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0, 
     0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\1\1\3\1\4\1\5\1\1"+
    "\10\4\1\6\4\4\13\0\1\7\2\0\2\4\1\10"+
    "\6\4\1\11\1\4\1\12\1\13\1\14\1\15\21\0"+
    "\1\16\2\0\2\4\1\17\5\4\1\20\1\4\1\21"+
    "\1\22\1\23\23\0\1\24\1\25\7\4\1\26\1\4"+
    "\22\0\1\27\1\0\1\4\1\30\1\4\1\31\4\4"+
    "\2\0\1\32\1\33\1\34\14\0\1\35\1\36\1\0"+
    "\2\4\1\37\1\40\1\41\1\4\3\0\1\42\1\0"+
    "\1\43\4\0\1\44\3\0\1\45\3\0\3\4\1\0"+
    "\1\46\1\47\3\0\1\50\1\0\1\51\1\0\1\52"+
    "\1\53\2\0\1\54\1\55\1\56\2\4\3\0\1\57"+
    "\1\60\1\0\1\61\1\62\1\63\1\4\1\0\1\64"+
    "\2\0\1\65\1\66\1\0\1\67\1\70";

  private static int [] zzUnpackAction() {
    int [] result = new int[226];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\47\0\116\0\165\0\47\0\234\0\303\0\352"+
    "\0\u0111\0\u0138\0\u015f\0\u0186\0\u01ad\0\u01d4\0\u01fb\0\u0222"+
    "\0\47\0\u0249\0\u0270\0\u0297\0\u02be\0\u02e5\0\u030c\0\u0333"+
    "\0\u035a\0\u0381\0\u03a8\0\u03cf\0\u03f6\0\u041d\0\u0444\0\u046b"+
    "\0\47\0\u0492\0\u04b9\0\u04e0\0\u0507\0\u052e\0\u0555\0\u057c"+
    "\0\u05a3\0\u05ca\0\u05f1\0\u0618\0\234\0\u063f\0\u0666\0\u068d"+
    "\0\234\0\u06b4\0\u06db\0\u0702\0\u0729\0\u0750\0\u0777\0\u079e"+
    "\0\u07c5\0\u07ec\0\u0813\0\u083a\0\u0861\0\u0888\0\u08af\0\u08d6"+
    "\0\u08fd\0\u0924\0\u094b\0\u0444\0\u0972\0\u0999\0\u09c0\0\u09e7"+
    "\0\47\0\u0a0e\0\u0a35\0\u0a5c\0\u0a83\0\u0aaa\0\u0ad1\0\u0af8"+
    "\0\47\0\47\0\47\0\u0b1f\0\u0b46\0\u0b6d\0\u0b94\0\u0bbb"+
    "\0\u0be2\0\u0c09\0\u0c30\0\u0c57\0\u0c7e\0\u0ca5\0\u0ccc\0\u0cf3"+
    "\0\u0d1a\0\u0d41\0\u0d68\0\u0d8f\0\u0db6\0\u0ddd\0\47\0\47"+
    "\0\u0e04\0\u0e2b\0\u0e52\0\u0e79\0\u0ea0\0\u0ec7\0\u0eee\0\47"+
    "\0\u0f15\0\u0f3c\0\u0f63\0\u0f8a\0\u0fb1\0\u0fd8\0\u0fff\0\u1026"+
    "\0\u104d\0\u1074\0\u109b\0\u10c2\0\u10e9\0\u1110\0\u1137\0\u115e"+
    "\0\u1185\0\u11ac\0\u11d3\0\u11fa\0\u1221\0\u1248\0\234\0\u126f"+
    "\0\234\0\u1296\0\u12bd\0\u12e4\0\u130b\0\u1332\0\u1359\0\u1380"+
    "\0\u13a7\0\47\0\u13ce\0\u13f5\0\u141c\0\u1443\0\u146a\0\u1491"+
    "\0\u14b8\0\u14df\0\u1506\0\u152d\0\u1554\0\u157b\0\u15a2\0\47"+
    "\0\u15c9\0\u15f0\0\u1617\0\234\0\234\0\234\0\u163e\0\u1665"+
    "\0\u168c\0\u16b3\0\47\0\u16da\0\47\0\u1701\0\u1728\0\u174f"+
    "\0\u1776\0\47\0\u179d\0\u17c4\0\u17eb\0\47\0\u1812\0\u1839"+
    "\0\u1860\0\u1887\0\u18ae\0\u18d5\0\u18fc\0\47\0\47\0\u1923"+
    "\0\u194a\0\u1971\0\47\0\u1998\0\47\0\u19bf\0\47\0\47"+
    "\0\u19e6\0\u1a0d\0\47\0\47\0\234\0\u1a34\0\u1a5b\0\u1a82"+
    "\0\u1aa9\0\u1ad0\0\47\0\47\0\u1af7\0\47\0\47\0\234"+
    "\0\u1b1e\0\u1b45\0\47\0\u1b6c\0\u1b93\0\234\0\47\0\u1bba"+
    "\0\47\0\47";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[226];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\7\6"+
    "\1\11\1\2\1\12\1\13\1\14\1\15\1\16\4\6"+
    "\1\17\3\6\1\20\1\6\1\21\1\22\1\2\1\23"+
    "\1\24\1\25\2\2\50\0\1\3\54\0\1\26\1\27"+
    "\1\30\1\0\1\31\1\32\1\0\1\33\1\34\3\0"+
    "\1\35\1\36\26\0\2\6\1\0\10\6\1\0\17\6"+
    "\1\0\1\6\1\0\4\6\6\0\1\7\40\0\1\37"+
    "\6\40\1\41\22\40\1\42\1\40\1\43\13\40\4\0"+
    "\2\6\1\0\6\6\1\44\1\6\1\0\17\6\1\0"+
    "\1\6\1\0\4\6\5\0\2\6\1\0\3\6\1\45"+
    "\4\6\1\0\1\6\1\46\15\6\1\0\1\6\1\0"+
    "\4\6\5\0\2\6\1\0\2\6\1\47\5\6\1\0"+
    "\17\6\1\0\1\6\1\0\4\6\5\0\2\6\1\0"+
    "\3\6\1\50\4\6\1\0\17\6\1\0\1\6\1\0"+
    "\4\6\5\0\2\6\1\0\3\6\1\51\4\6\1\0"+
    "\17\6\1\0\1\6\1\0\4\6\5\0\2\6\1\0"+
    "\1\6\1\52\4\6\1\53\1\6\1\0\17\6\1\0"+
    "\1\6\1\0\4\6\5\0\2\6\1\0\1\6\1\54"+
    "\1\6\1\55\4\6\1\0\17\6\1\0\1\6\1\0"+
    "\4\6\5\0\2\6\1\0\10\6\1\0\7\6\1\56"+
    "\7\6\1\0\1\6\1\0\4\6\5\0\2\6\1\0"+
    "\1\6\1\57\6\6\1\0\17\6\1\0\1\6\1\0"+
    "\4\6\5\0\2\6\1\0\10\6\1\0\10\6\1\60"+
    "\6\6\1\0\1\6\1\0\4\6\5\0\2\6\1\0"+
    "\3\6\1\61\4\6\1\0\17\6\1\0\1\6\1\0"+
    "\4\6\5\0\2\6\1\0\10\6\1\0\1\6\1\62"+
    "\15\6\1\0\1\6\1\0\4\6\11\0\1\63\51\0"+
    "\1\64\45\0\1\65\55\0\1\66\37\0\1\67\51\0"+
    "\1\70\3\0\1\71\34\0\1\72\1\73\1\74\1\0"+
    "\1\75\1\76\1\0\1\77\4\0\1\100\1\101\34\0"+
    "\1\102\44\0\1\103\43\0\1\104\41\0\6\40\1\41"+
    "\46\40\1\41\23\40\1\105\22\40\1\41\25\40\1\106"+
    "\12\40\4\0\2\6\1\0\10\6\1\0\7\6\1\107"+
    "\7\6\1\0\1\6\1\0\4\6\5\0\2\6\1\0"+
    "\1\110\7\6\1\0\17\6\1\0\1\6\1\0\4\6"+
    "\5\0\2\6\1\0\10\6\1\0\17\6\1\0\1\6"+
    "\1\111\4\6\5\0\2\6\1\0\10\6\1\0\10\6"+
    "\1\112\6\6\1\0\1\6\1\0\4\6\5\0\2\6"+
    "\1\0\1\113\7\6\1\0\17\6\1\0\1\6\1\0"+
    "\4\6\5\0\2\6\1\0\1\114\7\6\1\0\17\6"+
    "\1\0\1\6\1\0\4\6\5\0\2\6\1\0\10\6"+
    "\1\0\5\6\1\115\11\6\1\0\1\6\1\0\4\6"+
    "\5\0\2\6\1\0\10\6\1\0\5\6\1\116\11\6"+
    "\1\0\1\6\1\0\4\6\5\0\2\6\1\0\2\6"+
    "\1\117\5\6\1\0\17\6\1\0\1\6\1\0\4\6"+
    "\5\0\2\6\1\0\10\6\1\0\10\6\1\120\6\6"+
    "\1\0\1\6\1\0\4\6\5\0\2\6\1\0\10\6"+
    "\1\0\17\6\1\0\1\6\1\121\4\6\5\0\2\6"+
    "\1\0\10\6\1\0\17\6\1\0\1\6\1\122\4\6"+
    "\5\0\2\6\1\0\10\6\1\0\17\6\1\0\1\6"+
    "\1\123\4\6\12\0\1\124\51\0\1\125\41\0\1\126"+
    "\63\0\1\127\45\0\1\130\52\0\1\131\33\0\1\132"+
    "\42\0\1\133\51\0\1\134\45\0\1\135\55\0\1\136"+
    "\37\0\1\137\51\0\1\140\3\0\1\141\37\0\1\142"+
    "\44\0\1\143\45\0\1\144\53\0\1\145\10\0\1\146"+
    "\21\0\6\40\1\147\46\40\1\150\40\40\4\0\2\6"+
    "\1\0\10\6\1\0\1\6\1\151\15\6\1\0\1\6"+
    "\1\0\4\6\5\0\2\6\1\0\5\6\1\152\2\6"+
    "\1\0\17\6\1\0\1\6\1\0\4\6\5\0\2\6"+
    "\1\0\10\6\1\0\1\6\1\153\15\6\1\0\1\6"+
    "\1\0\4\6\5\0\2\6\1\0\5\6\1\154\2\6"+
    "\1\0\17\6\1\0\1\6\1\0\4\6\5\0\2\6"+
    "\1\0\5\6\1\155\2\6\1\0\17\6\1\0\1\6"+
    "\1\0\4\6\5\0\2\6\1\0\10\6\1\0\6\6"+
    "\1\156\10\6\1\0\1\6\1\0\4\6\5\0\2\6"+
    "\1\0\10\6\1\0\10\6\1\157\6\6\1\0\1\6"+
    "\1\0\4\6\5\0\2\6\1\0\10\6\1\0\17\6"+
    "\1\0\1\6\1\160\4\6\5\0\2\6\1\0\4\6"+
    "\1\161\3\6\1\0\17\6\1\0\1\6\1\0\4\6"+
    "\13\0\1\162\55\0\1\163\41\0\1\164\53\0\1\165"+
    "\35\0\1\166\57\0\1\167\35\0\1\170\47\0\1\171"+
    "\51\0\1\172\41\0\1\173\63\0\1\174\45\0\1\175"+
    "\52\0\1\176\33\0\1\177\41\0\1\200\53\0\1\201"+
    "\10\0\1\202\35\0\1\203\53\0\1\204\53\0\1\205"+
    "\24\0\2\6\1\0\4\6\1\206\3\6\1\0\17\6"+
    "\1\0\1\6\1\0\4\6\5\0\2\6\1\0\10\6"+
    "\1\0\1\6\1\207\15\6\1\0\1\6\1\0\4\6"+
    "\5\0\2\6\1\0\5\6\1\210\2\6\1\0\17\6"+
    "\1\0\1\6\1\0\4\6\5\0\2\6\1\0\10\6"+
    "\1\0\1\6\1\211\15\6\1\0\1\6\1\0\4\6"+
    "\5\0\2\6\1\0\10\6\1\0\1\6\1\212\15\6"+
    "\1\0\1\6\1\0\4\6\5\0\2\6\1\0\10\6"+
    "\1\0\7\6\1\213\7\6\1\0\1\6\1\0\4\6"+
    "\5\0\2\6\1\0\10\6\1\0\7\6\1\214\7\6"+
    "\1\0\1\6\1\0\4\6\5\0\2\6\1\0\6\6"+
    "\1\215\1\6\1\0\17\6\1\0\1\6\1\0\4\6"+
    "\14\0\1\216\71\0\1\217\31\0\1\220\36\0\1\221"+
    "\40\0\1\222\56\0\1\223\42\0\1\224\51\0\1\225"+
    "\55\0\1\226\41\0\1\227\53\0\1\230\35\0\1\231"+
    "\57\0\1\232\35\0\1\233\52\0\1\234\53\0\1\235"+
    "\53\0\1\236\41\0\1\237\30\0\1\240\72\0\1\241"+
    "\23\0\2\6\1\0\3\6\1\242\4\6\1\0\17\6"+
    "\1\0\1\6\1\0\4\6\5\0\2\6\1\0\1\6"+
    "\1\243\6\6\1\0\17\6\1\0\1\6\1\0\4\6"+
    "\5\0\2\6\1\0\1\244\7\6\1\0\17\6\1\0"+
    "\1\6\1\0\4\6\5\0\2\6\1\0\10\6\1\0"+
    "\10\6\1\245\6\6\1\0\1\6\1\0\4\6\5\0"+
    "\2\6\1\0\1\6\1\246\6\6\1\0\17\6\1\0"+
    "\1\6\1\0\4\6\5\0\2\6\1\0\10\6\1\0"+
    "\10\6\1\247\6\6\1\0\1\6\1\0\4\6\13\0"+
    "\1\250\55\0\1\251\34\0\1\252\42\0\1\253\55\0"+
    "\1\254\37\0\1\255\56\0\1\256\71\0\1\257\31\0"+
    "\1\260\36\0\1\261\40\0\1\262\56\0\1\263\42\0"+
    "\1\264\60\0\1\265\30\0\1\266\72\0\1\267\26\0"+
    "\1\270\67\0\1\271\22\0\2\6\1\0\1\6\1\272"+
    "\6\6\1\0\17\6\1\0\1\6\1\0\4\6\5\0"+
    "\2\6\1\0\10\6\1\0\7\6\1\273\7\6\1\0"+
    "\1\6\1\0\4\6\5\0\2\6\1\0\10\6\1\0"+
    "\4\6\1\274\12\6\1\0\1\6\1\0\4\6\15\0"+
    "\1\275\35\0\1\276\46\0\1\277\53\0\1\300\50\0"+
    "\1\301\55\0\1\302\30\0\1\303\3\0\1\304\42\0"+
    "\1\305\55\0\1\306\37\0\1\307\46\0\1\310\3\0"+
    "\1\311\67\0\1\312\21\0\1\313\46\0\1\314\47\0"+
    "\2\6\1\0\10\6\1\0\4\6\1\315\12\6\1\0"+
    "\1\6\1\0\4\6\5\0\2\6\1\0\3\6\1\316"+
    "\4\6\1\0\17\6\1\0\1\6\1\0\4\6\5\0"+
    "\2\6\1\0\4\6\1\317\3\6\1\0\17\6\1\0"+
    "\1\6\1\0\4\6\16\0\1\320\55\0\1\321\36\0"+
    "\1\322\35\0\1\323\46\0\1\324\53\0\1\325\41\0"+
    "\1\326\46\0\1\327\47\0\2\6\1\0\10\6\1\0"+
    "\1\6\1\330\15\6\1\0\1\6\1\0\4\6\5\0"+
    "\2\6\1\0\3\6\1\331\4\6\1\0\17\6\1\0"+
    "\1\6\1\0\4\6\17\0\1\332\33\0\1\333\60\0"+
    "\1\334\55\0\1\335\26\0\2\6\1\0\10\6\1\0"+
    "\1\6\1\336\15\6\1\0\1\6\1\0\4\6\4\0"+
    "\1\337\61\0\1\340\33\0\1\341\46\0\1\342\43\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7137];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\2\1\1\11\13\1\1\11\4\1\13\0"+
    "\1\11\2\0\17\1\21\0\1\1\2\0\2\1\1\11"+
    "\7\1\3\11\23\0\2\11\7\1\1\11\1\1\22\0"+
    "\1\1\1\0\10\1\2\0\2\1\1\11\14\0\1\1"+
    "\1\11\1\0\6\1\3\0\1\11\1\0\1\11\4\0"+
    "\1\11\3\0\1\11\3\0\3\1\1\0\2\11\3\0"+
    "\1\11\1\0\1\11\1\0\2\11\2\0\2\11\3\1"+
    "\3\0\2\11\1\0\2\11\2\1\1\0\1\11\2\0"+
    "\1\1\1\11\1\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[226];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

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
    



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexicalAnalyzerCom(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public LexicalAnalyzerCom(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 40: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.lista_c , yycolumn, yyline, yytext());
          }
        case 57: break;
        case 13: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.fa , yycolumn, yyline, yytext());
          }
        case 58: break;
        case 43: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pista_c , yycolumn, yyline, yytext());
          }
        case 59: break;
        case 54: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.solicitud_a , yycolumn, yyline, yytext());
          }
        case 60: break;
        case 1: 
          { addError(yyline+1, yycolumn+1, yytext(), "Caracter o Token no reconocido");
                                            System.out.println("Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                            //ErrorG e = new ErrorG(yyline+1, yycolumn+1,yytext(),"Lexico","Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
          }
        case 61: break;
        case 4: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.identificador , yycolumn, yyline, yytext());
          }
        case 62: break;
        case 34: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.canal_a , yycolumn, yyline, yytext());
          }
        case 63: break;
        case 39: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.listas_a , yycolumn, yyline, yytext());
          }
        case 64: break;
        case 44: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pistas_a , yycolumn, yyline, yytext());
          }
        case 65: break;
        case 45: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nombre_a , yycolumn, yyline, yytext());
          }
        case 66: break;
        case 24: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.lista , yycolumn, yyline, yytext());
          }
        case 67: break;
        case 3: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.cerrar , yycolumn, yyline, yytext());
          }
        case 68: break;
        case 20: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.si_rsv , yycolumn, yyline, yytext());
          }
        case 69: break;
        case 53: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.frecuencia , yycolumn, yyline, yytext());
          }
        case 70: break;
        case 47: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.octava_c , yycolumn, yyline, yytext());
          }
        case 71: break;
        case 16: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.sol , yycolumn, yyline, yytext());
          }
        case 72: break;
        case 28: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.tipo_a , yycolumn, yyline, yytext());
          }
        case 73: break;
        case 52: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.duracion_a , yycolumn, yyline, yytext());
          }
        case 74: break;
        case 14: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.decimal , yycolumn, yyline, Double.parseDouble(yytext()));
          }
        case 75: break;
        case 19: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.fa_s , yycolumn, yyline, yytext());
          }
        case 76: break;
        case 37: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nota_c , yycolumn, yyline, yytext());
          }
        case 77: break;
        case 8: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.la , yycolumn, yyline, yytext());
          }
        case 78: break;
        case 35: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.datos_a , yycolumn, yyline, yytext());
          }
        case 79: break;
        case 12: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.mi , yycolumn, yyline, yytext());
          }
        case 80: break;
        case 17: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.do_s , yycolumn, yyline, yytext());
          }
        case 81: break;
        case 27: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.canal_ab , yycolumn, yyline, yytext());
          }
        case 82: break;
        case 10: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.do_rsv , yycolumn, yyline, yytext());
          }
        case 83: break;
        case 18: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.re_s , yycolumn, yyline, yytext());
          }
        case 84: break;
        case 7: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.cadena , yycolumn, yyline, yytext().substring(1, yytext().length() - 1));
          }
        case 85: break;
        case 6: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.igual , yycolumn, yyline, yytext());
          }
        case 86: break;
        case 26: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.lista_a , yycolumn, yyline, yytext());
          }
        case 87: break;
        case 32: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nombre , yycolumn, yyline, yytext());
          }
        case 88: break;
        case 51: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.aleatorio , yycolumn, yyline, yytext());
          }
        case 89: break;
        case 56: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.solicitud_c , yycolumn, yyline, yytext());
          }
        case 90: break;
        case 15: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.la_s , yycolumn, yyline, yytext());
          }
        case 91: break;
        case 23: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nota_ab , yycolumn, yyline, yytext());
          }
        case 92: break;
        case 41: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.canal_c , yycolumn, yyline, yytext());
          }
        case 93: break;
        case 48: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.listas_c , yycolumn, yyline, yytext());
          }
        case 94: break;
        case 31: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pistas , yycolumn, yyline, yytext());
          }
        case 95: break;
        case 38: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.octava_a , yycolumn, yyline, yytext());
          }
        case 96: break;
        case 33: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.numero_rsv , yycolumn, yyline, yytext());
          }
        case 97: break;
        case 9: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.si , yycolumn, yyline, yytext());
          }
        case 98: break;
        case 50: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nombre_c , yycolumn, yyline, yytext());
          }
        case 99: break;
        case 49: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pistas_c , yycolumn, yyline, yytext());
          }
        case 100: break;
        case 11: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.re , yycolumn, yyline, yytext());
          }
        case 101: break;
        case 21: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.no , yycolumn, yyline, yytext());
          }
        case 102: break;
        case 5: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.numero , yycolumn, yyline, Integer.parseInt(yytext()));
          }
        case 103: break;
        case 25: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pista , yycolumn, yyline, yytext());
          }
        case 104: break;
        case 30: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.nota_a , yycolumn, yyline, yytext());
          }
        case 105: break;
        case 29: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.pista_ab , yycolumn, yyline, yytext());
          }
        case 106: break;
        case 46: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.duracion , yycolumn, yyline, yytext());
          }
        case 107: break;
        case 36: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.tipo_c , yycolumn, yyline, yytext());
          }
        case 108: break;
        case 55: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.duracion_c , yycolumn, yyline, yytext());
          }
        case 109: break;
        case 42: 
          { printConsole(yytext()+"\n"); return new Symbol(SymbolComCode.datos_c , yycolumn, yyline, yytext());
          }
        case 110: break;
        case 2: 
          { /*Ignore*/
          }
        case 111: break;
        case 22: 
          { printConsole(yytext()); return new Symbol(SymbolComCode.sol_s , yycolumn, yyline, yytext());
          }
        case 112: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(SymbolComCode.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}