/* Generated By:JavaCC: Do not edit this line. MyParser.java */
import syntaxtree.*;
import syntaxtree.visitor.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MyParser implements MyParserConstants {
    public static void main(String[] args) throws Exception, ParseException, TokenMgrError{

        ArrayList<String> filenames = new ArrayList<String>();
        filenames.add("./src/testes/Factorial.txt");
//        filenames.add("./src/testes/TreeVisitor.txt");
//        filenames.add("./src/testes/BinaryTree.txt");
//        filenames.add("./src/testes/BubbleSort.txt");
//        filenames.add("./src/testes/BynarySearch.txt");
//        filenames.add("./src/testes/LinearSearch.txt");
//        filenames.add("./src/testes/LinkedList.txt");
//        filenames.add("./src/testes/QuickSort.txt");

//        testaArquivos(filenames);

        try {
            Program raiz = new MyParser(new StringReader(readFile(filenames.get(0)))).Prog();
            raiz.accept(new TypeDepthFirstVisitor());
        } catch (ParseException e) {
            System.out.println(e.toString());
        }

    }

    private static void testaArquivos(ArrayList<String> filenames) throws Exception {
        MyParser parser = new MyParser(new StringReader(""));
        for (String nome : filenames) {
            try {
                String file = readFile(nome);
                parser.ReInit(new StringReader(file));

                System.out.println("\nLENDO " + nome + "\n");

                List<Token> tokens = analiseLexica(parser, file);
                String resultadoAnaliseSintatica = analiseSintatica(parser, file);
                if (resultadoAnaliseSintatica.equals("")) {
                    System.out.println("\nSINTAXE OK\n");
                } else {
                    System.out.println("\nSINTAXE FALHA: " + resultadoAnaliseSintatica + "\n");
                }
                parser.ReInit(new StringReader(file));
            } catch (Exception e) {
                System.out.println("erro: " + e.getCause());
            }
        }
    }

    private static ArrayList<Token> analiseLexica(MyParser parser, String input) throws TokenMgrError {
        parser.ReInit(new StringReader(input));
        ArrayList<Token> tokens = new ArrayList<Token>();

        // analise lexica
        try {
            while (true) {
                Token token = parser.getNextToken();
                if (token.kind == 0) {
                    break;
                }
                tokens.add(token);
                // exibir token atual
                System.out.println("Token atual: " + token.image);
            }
        } catch (TokenMgrError e) {
            // informações do token quando ocorrer um erro léxico
            System.out.println("Erro lexico: " + e.getMessage());
            System.out.println("Tokens correspondidos:");
            for (Token token : tokens) {
                System.out.println("Linha " + token.beginLine + ", Coluna " + token.beginColumn +
                        ": Imagem: \"" + token.image + "\", Kind: " + token.kind);
            }
        }

        return tokens;
    }

    private static String analiseSintatica(MyParser parser, String input) {
        String res = "";
        try {
            parser.ReInit(new StringReader(input));
            parser.Prog();
            return res;
        } catch (Throwable e) {
            return e.getMessage();
        }
    }

    private static String readFile(String filePath) throws Exception {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }
        reader.close();
        return stringBuilder.toString();
    }

// FEITO
  static final public Program Prog() throws ParseException {
    MainClass mc;
    ClassDecl cd;
    ClassDeclList cl = new ClassDeclList();
    mc = MainClass();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CLASS:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      cd = ClassDeclaration();
                              cl.addElement(cd);
    }
    jj_consume_token(0);
     {if (true) return new Program(mc, cl);}
    throw new Error("Missing return statement in function");
  }

// FEITO
  static final public MainClass MainClass() throws ParseException {
    Identifier ci, ma;
    Statement s;
    jj_consume_token(CLASS);
    ci = ID();
    jj_consume_token(37);
    jj_consume_token(PUBLIC);
    jj_consume_token(STATIC);
    jj_consume_token(VOID);
    jj_consume_token(MAIN);
    jj_consume_token(38);
    jj_consume_token(STRING);
    jj_consume_token(39);
    jj_consume_token(40);
    ma = ID();
    jj_consume_token(41);
    jj_consume_token(37);
    s = Statement();
    jj_consume_token(42);
    jj_consume_token(42);
     {if (true) return new MainClass(ci, ma, s);}
    throw new Error("Missing return statement in function");
  }

// FEITO
  static final public ClassDecl ClassDeclaration() throws ParseException {
    Identifier id1, id2 = null;
    VarDecl vd; VarDeclList vl = new VarDeclList();
    MethodDecl md; MethodDeclList ml = new MethodDeclList();
    jj_consume_token(CLASS);
    id1 = ID();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EXTENDS:
      jj_consume_token(EXTENDS);
      id2 = ID();
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(37);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
      case BOOLEAN:
      case ID:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      vd = VarDeclaration();
                                vl.addElement(vd);
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PUBLIC:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      md = MethodDeclaration();
                                                                                 ml.addElement(md);
    }
    jj_consume_token(42);
     {if (true) return (id2 == null) ? new ClassDeclSimple(id1, vl, ml) : new ClassDeclExtends(id1, id2, vl, ml);}
    throw new Error("Missing return statement in function");
  }

// FEITO
  static final public VarDecl VarDeclaration() throws ParseException {
    Type t; Identifier i;
    t = Type();
    i = ID();
    jj_consume_token(SEMI);
     {if (true) return new VarDecl(t, i);}
    throw new Error("Missing return statement in function");
  }

// FEITO
  static final public MethodDecl MethodDeclaration() throws ParseException {
    Type t, taux; Identifier i, iaux; Exp e; MethodDecl md; VarDecl vd; Statement st;

    FormalList fl = new FormalList(); VarDeclList vl = new VarDeclList(); StatementList sl = new StatementList();
    jj_consume_token(PUBLIC);
    t = Type();
    i = ID();
    jj_consume_token(38);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOLEAN:
    case ID:
      taux = Type();
      iaux = ID();
                                    fl.addElement(new Formal(taux, iaux));
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 43:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_4;
        }
        jj_consume_token(43);
        taux = Type();
        iaux = ID();
                                                                                                             fl.addElement(new Formal(taux, iaux));
      }
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(41);
    jj_consume_token(37);
    label_5:
    while (true) {
      if (jj_2_1(2)) {
        ;
      } else {
        break label_5;
      }
      vd = VarDeclaration();
                                             vl.addElement(vd);
    }
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case WHILE:
      case IF:
      case PRINT:
      case ID:
      case 37:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_6;
      }
      st = Statement();
                           sl.addElement(st);
    }
    jj_consume_token(RETURN);
    e = Expression();
    jj_consume_token(SEMI);
    jj_consume_token(42);
     {if (true) return new MethodDecl(t, i, fl, vl, sl, e);}
    throw new Error("Missing return statement in function");
  }

// FEITO, VER SE O ID FUNCIONA
  static final public Type Type() throws ParseException {
    Identifier id;
    if (jj_2_2(3)) {
      jj_consume_token(INT);
      jj_consume_token(39);
      jj_consume_token(40);
                   {if (true) return new IntArrayType();}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
        jj_consume_token(INT);
             {if (true) return new IntegerType();}
        break;
      case BOOLEAN:
        jj_consume_token(BOOLEAN);
                 {if (true) return new BooleanType();}
        break;
      case ID:
        id = ID();
                 {if (true) return new IdentifierType(id.toString());}
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  static final public Statement Statement() throws ParseException {
   Statement s, s2;
   Exp e, e2; Identifier id;
   StatementList sl = new StatementList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 37:
      jj_consume_token(37);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case WHILE:
        case IF:
        case PRINT:
        case ID:
        case 37:
          ;
          break;
        default:
          jj_la1[8] = jj_gen;
          break label_7;
        }
        s = Statement();
                          sl.addElement(s);
      }
      jj_consume_token(42);
                                                     {if (true) return new Block(sl);}
      break;
    case IF:
      jj_consume_token(IF);
      jj_consume_token(38);
      e = Expression();
      jj_consume_token(41);
      s = Statement();
      jj_consume_token(ELSE);
      s2 = Statement();
                                                                              {if (true) return new If(e, s, s2);}
      break;
    case WHILE:
      jj_consume_token(WHILE);
      jj_consume_token(38);
      e = Expression();
      jj_consume_token(41);
      s = Statement();
                                                         {if (true) return new While(e, s);}
      break;
    case PRINT:
      jj_consume_token(PRINT);
      jj_consume_token(38);
      e = Expression();
      jj_consume_token(41);
      jj_consume_token(SEMI);
                                                {if (true) return new Print(e);}
      break;
    default:
      jj_la1[9] = jj_gen;
      if (jj_2_3(2)) {
        // coloquei de 6 para 2
              id = ID();
        jj_consume_token(ASSIGN);
        e = Expression();
        jj_consume_token(SEMI);
                                              {if (true) return new Assign(id, e);}
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ID:
          id = ID();
          jj_consume_token(39);
          e = Expression();
          jj_consume_token(40);
          jj_consume_token(ASSIGN);
          e2 = Expression();
          jj_consume_token(SEMI);
                                                                        {if (true) return new ArrayAssign(id, e, e2);}
          break;
        default:
          jj_la1[10] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp Expression() throws ParseException {
    Exp e1;
    e1 = Expr1();
    label_8:
    while (true) {
      if (jj_2_4(2)) {
        ;
      } else {
        break label_8;
      }
      e1 = Expr2(e1);
    }
      {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  static final public Exp Expr1() throws ParseException {
    Exp e, e2;
    Token t;
    Identifier id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case THIS:
      jj_consume_token(THIS);
             e = new This(); {if (true) return e;}
      break;
    case TRUE:
      jj_consume_token(TRUE);
               e = new True(); {if (true) return e;}
      break;
    case FALSE:
      jj_consume_token(FALSE);
                e = new False(); {if (true) return e;}
      break;
    case INTEGER_LITERAL:
      t = jj_consume_token(INTEGER_LITERAL);
                              e = new IntegerLiteral(Integer.parseInt(t.image)); {if (true) return e;}
      break;
    case ID:
      id = ID();
                  e = new IdentifierExp(id.toString()); {if (true) return e;}
      break;
    case 44:
      jj_consume_token(44);
      e2 = Expression();
                              e = new Not(e2); {if (true) return e;}
      break;
    case 38:
      jj_consume_token(38);
      e2 = Expression();
      jj_consume_token(41);
                                  e = e2; {if (true) return e;}
      break;
    default:
      jj_la1[11] = jj_gen;
      if (jj_2_5(2)) {
        jj_consume_token(NEW);
        jj_consume_token(INT);
        jj_consume_token(39);
        e2 = Expression();
        jj_consume_token(40);
                                              e = new NewArray(e2); {if (true) return e;}
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NEW:
          jj_consume_token(NEW);
          id = ID();
          jj_consume_token(38);
          jj_consume_token(41);
                                e = new NewObject(id); {if (true) return e;}
          break;
        default:
          jj_la1[12] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp Expr2(Exp left_exp) throws ParseException {
    Exp right_exp; Exp final_exp;
    Identifier id;
    ExpList el = new ExpList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OP_AND:
      jj_consume_token(OP_AND);
      right_exp = Expression();
                                    {if (true) return new And(left_exp, right_exp);}
      break;
    case OP_LESS:
      jj_consume_token(OP_LESS);
      right_exp = Expression();
                                     {if (true) return new LessThan(left_exp, right_exp);}
      break;
    case OP_ADD:
      jj_consume_token(OP_ADD);
      right_exp = Expression();
                                     {if (true) return new Plus(left_exp, right_exp);}
      break;
    case OP_SUB:
      jj_consume_token(OP_SUB);
      right_exp = Expression();
                                     {if (true) return new Minus(left_exp, right_exp);}
      break;
    case OP_MULT:
      jj_consume_token(OP_MULT);
      right_exp = Expression();
                                     {if (true) return new Times(left_exp, right_exp);}
      break;
    case 39:
      jj_consume_token(39);
      right_exp = Expression();
      jj_consume_token(40);
                                         {if (true) return new ArrayLookup(left_exp, right_exp);}
      break;
    default:
      jj_la1[15] = jj_gen;
      if (jj_2_6(2)) {
        jj_consume_token(45);
        jj_consume_token(LENGTH);
                     {if (true) return new ArrayLength(left_exp);}
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 45:
          jj_consume_token(45);
          id = ID();
          jj_consume_token(38);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case NEW:
          case TRUE:
          case FALSE:
          case THIS:
          case ID:
          case INTEGER_LITERAL:
          case 38:
          case 44:
            right_exp = Expression();
                                                     el.addElement(right_exp);
            label_9:
            while (true) {
              switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
              case 43:
                ;
                break;
              default:
                jj_la1[13] = jj_gen;
                break label_9;
              }
              jj_consume_token(43);
              right_exp = Expression();
                                         el.addElement(right_exp);
            }
            break;
          default:
            jj_la1[14] = jj_gen;
            ;
          }
          jj_consume_token(41);
                                                                                {if (true) return new Call(left_exp, id, el);}
          break;
        default:
          jj_la1[16] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    throw new Error("Missing return statement in function");
  }

// FEITO
  static final public Identifier ID() throws ParseException {
                   Token t;
    t = jj_consume_token(ID);
            {if (true) return new Identifier(t.image);}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_3R_24() {
    if (jj_3R_25()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    if (jj_scan_token(ID)) return true;
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_scan_token(45)) return true;
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_11()) return true;
    if (jj_scan_token(ASSIGN)) return true;
    return false;
  }

  static private boolean jj_3_6() {
    if (jj_scan_token(45)) return true;
    if (jj_scan_token(LENGTH)) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_3R_13()) return true;
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_19() {
    if (jj_scan_token(39)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_18() {
    if (jj_scan_token(OP_MULT)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_17() {
    if (jj_scan_token(OP_SUB)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_16() {
    if (jj_scan_token(OP_ADD)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(OP_LESS)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_14()) {
    jj_scanpos = xsp;
    if (jj_3R_15()) {
    jj_scanpos = xsp;
    if (jj_3R_16()) {
    jj_scanpos = xsp;
    if (jj_3R_17()) {
    jj_scanpos = xsp;
    if (jj_3R_18()) {
    jj_scanpos = xsp;
    if (jj_3R_19()) {
    jj_scanpos = xsp;
    if (jj_3_6()) {
    jj_scanpos = xsp;
    if (jj_3R_20()) return true;
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_scan_token(OP_AND)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_23() {
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_22() {
    if (jj_scan_token(BOOLEAN)) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    if (jj_scan_token(INT)) return true;
    return false;
  }

  static private boolean jj_3R_33() {
    if (jj_scan_token(NEW)) return true;
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_scan_token(NEW)) return true;
    if (jj_scan_token(INT)) return true;
    return false;
  }

  static private boolean jj_3R_32() {
    if (jj_scan_token(38)) return true;
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_scan_token(INT)) return true;
    if (jj_scan_token(39)) return true;
    if (jj_scan_token(40)) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3R_21()) {
    jj_scanpos = xsp;
    if (jj_3R_22()) {
    jj_scanpos = xsp;
    if (jj_3R_23()) return true;
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_31() {
    if (jj_scan_token(44)) return true;
    return false;
  }

  static private boolean jj_3R_30() {
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_29() {
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  static private boolean jj_3R_28() {
    if (jj_scan_token(FALSE)) return true;
    return false;
  }

  static private boolean jj_3R_27() {
    if (jj_scan_token(TRUE)) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_3R_12()) return true;
    return false;
  }

  static private boolean jj_3R_26() {
    if (jj_scan_token(THIS)) return true;
    return false;
  }

  static private boolean jj_3R_25() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_26()) {
    jj_scanpos = xsp;
    if (jj_3R_27()) {
    jj_scanpos = xsp;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) {
    jj_scanpos = xsp;
    if (jj_3R_31()) {
    jj_scanpos = xsp;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public MyParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  /** Whether we are looking ahead. */
  static private boolean jj_lookingAhead = false;
  static private boolean jj_semLA;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[17];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2000,0x80000,0xc00000,0x4000,0x0,0xc00000,0x1000180,0xc00000,0x1000180,0x1000180,0x0,0x1c000000,0x40000,0x0,0x1c040000,0xe0000000,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x8,0x0,0x800,0x8,0x28,0x8,0x28,0x20,0x8,0x1058,0x0,0x800,0x1058,0x83,0x2000,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[6];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public MyParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MyParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MyParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public MyParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MyParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public MyParser(MyParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(MyParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
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

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
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
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = jj_lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Iterator it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.add(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[46];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 17; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 46; i++) {
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
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 6; i++) {
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
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
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
