/* Generated By:JavaCC: Do not edit this line. CommandParser.java */
package com.adgsoftware.mydomo.engine.parser;

/*
 * #%L
 * MyDomoEngine
 * %%
 * Copyright (C) 2011 - 2013 A. de Giuli
 * %%
 * This file is part of MyDomo done by A. de Giuli (arnaud.degiuli(at)free.fr).
 * 
 *     MyDomo is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     MyDomo is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with MyDomo.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import com.adgsoftware.mydomo.engine.CommandEnum;

public class CommandParser implements CommandParserConstants {
        private String who;
        private String what;
        private String where;
        private String dimension;
        private CommandEnum type;
        private List<String> dimensionList = new ArrayList<String>();

    public static CommandParser parse(String command) throws ParseException
    {
        CommandParser parser = new CommandParser(new ByteArrayInputStream(command.getBytes()));
        parser.parseOneLine();

        return parser;
    }

    public String getWho() {
         return who;
        }

        public String getWhat() {
         return what;
        }

        public String getWhere() {
         return where;
        }

        public String getDimension() {
         return dimension;
        }

        public CommandEnum getType() {
         return type;
        }

        public List<String> getDimensionList() {
         return dimensionList;
        }

  final private void parseOneLine() throws ParseException {
    if (jj_2_1(2)) {
      jj_consume_token(6);
      other();
               {if (true) return;}
    } else if (jj_2_2(2)) {
      jj_consume_token(7);
      standard();
                   {if (true) return;}
    } else if (jj_2_3(2)) {
      jj_consume_token(0);
          {if (true) return;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void standard() throws ParseException {
    Token who;
    Token what;
    Token where;
    who = jj_consume_token(VAL);
    jj_consume_token(7);
    what = jj_consume_token(VAL);
    jj_consume_token(7);
    where = jj_consume_token(VAL);
    jj_consume_token(8);
                                                       this.type=CommandEnum.STANDARD_COMMAND; this.who = who.toString(); this.what=what.toString(); this.where = where.toString();
  }

  final public void other() throws ParseException {
    Token who;
    Token where;
    Token dimension;
    Token val;
    if (jj_2_9(2)) {
      who = jj_consume_token(VAL);
      jj_consume_token(7);
      where = jj_consume_token(VAL);
                                     this.who = who.toString(); this.where=where.toString();
      if (jj_2_4(2)) {
        jj_consume_token(8);
                        this.type=CommandEnum.STANDARD_STATUS;
      } else if (jj_2_5(2)) {
        dimension();

      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    } else if (jj_2_10(2)) {
      if (jj_2_7(2)) {
        who = jj_consume_token(VAL);
        jj_consume_token(9);
                          this.who = who.toString();  this.where=""; /* Gateway case */
        dimension = jj_consume_token(VAL);
                           this.type=CommandEnum.DIMENSION_STATUS;this.dimension=dimension.toString();
      } else if (jj_2_8(2)) {
        jj_consume_token(10);
        dimension = jj_consume_token(VAL);
                                 this.type=CommandEnum.DIMENSION_COMMAND;this.dimension=dimension.toString();
        label_1:
        while (true) {
          if (jj_2_6(2)) {
            ;
          } else {
            break label_1;
          }
          jj_consume_token(7);
          val = jj_consume_token(VAL);
                              this.dimensionList.add(val.toString());
        }
        jj_consume_token(8);
                    String str =""; for (int i = 0; i < dimensionList.size(); i++) {str = str + ";" + dimensionList.get(i);}
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void dimension() throws ParseException {
Token dimension;
Token val;
    if (jj_2_12(2)) {
      jj_consume_token(7);
      dimension = jj_consume_token(VAL);
                               this.type=CommandEnum.DIMENSION_STATUS;this.dimension=dimension.toString();
    } else if (jj_2_13(2)) {
      jj_consume_token(6);
      dimension = jj_consume_token(VAL);
                                this.type=CommandEnum.DIMENSION_COMMAND;this.dimension=dimension.toString();
      label_2:
      while (true) {
        if (jj_2_11(2)) {
          ;
        } else {
          break label_2;
        }
        jj_consume_token(7);
        val = jj_consume_token(VAL);
                      this.dimensionList.add(val.toString());
      }
      jj_consume_token(8);
            String str =""; for (int i = 0; i < dimensionList.size(); i++) {str = str + ";" + dimensionList.get(i);}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  private boolean jj_3R_5() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_12()) {
    jj_scanpos = xsp;
    if (jj_3_13()) return true;
    }
    return false;
  }

  private boolean jj_3_12() {
    if (jj_scan_token(7)) return true;
    if (jj_scan_token(VAL)) return true;
    return false;
  }

  private boolean jj_3_11() {
    if (jj_scan_token(7)) return true;
    if (jj_scan_token(VAL)) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(7)) return true;
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3_6() {
    if (jj_scan_token(7)) return true;
    if (jj_scan_token(VAL)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(6)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3R_4() {
    if (jj_scan_token(VAL)) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_3R_5()) return true;
    return false;
  }

  private boolean jj_3_8() {
    if (jj_scan_token(10)) return true;
    if (jj_scan_token(VAL)) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(8)) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_scan_token(VAL)) return true;
    if (jj_scan_token(9)) return true;
    return false;
  }

  private boolean jj_3_10() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_7()) {
    jj_scanpos = xsp;
    if (jj_3_8()) return true;
    }
    return false;
  }

  private boolean jj_3R_3() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_9()) {
    jj_scanpos = xsp;
    if (jj_3_10()) return true;
    }
    return false;
  }

  private boolean jj_3_13() {
    if (jj_scan_token(6)) return true;
    if (jj_scan_token(VAL)) return true;
    return false;
  }

  private boolean jj_3_9() {
    if (jj_scan_token(VAL)) return true;
    if (jj_scan_token(7)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public CommandParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[0];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[13];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public CommandParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public CommandParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CommandParserTokenManager(jj_input_stream);
    token = new Token();
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public CommandParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CommandParserTokenManager(jj_input_stream);
    token = new Token();
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public CommandParser(CommandParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(CommandParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }
  
  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[11];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 0; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 11; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 13; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
            case 10: jj_3_11(); break;
            case 11: jj_3_12(); break;
            case 12: jj_3_13(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
