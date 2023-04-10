import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/* Generated By:JavaCC: Do not edit this line. MyParser.java */
public class MyParser implements MyParserConstants {
    public static void main(String[] args) throws ParseException, TokenMgrError{
        try {
            String input = readFile(args[0]);
            MyParser parser = new MyParser(new StringReader(input));
            List<Token> tokens = new ArrayList<Token>();

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

            // analise sintatica
            try {
                parser.ReInit(new StringReader(input));
                parser.Prog();
                System.out.println("Sintaxe ok");
            } catch (Throwable e) {
                System.out.println("Sintaxe falhou: " + e.getMessage());
            }

        } catch (Throwable e) {
            System.out.println("Erro ao ler o arquivo");
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

  static final public void Prog() throws ParseException {
    MainClass();
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
      ClassDeclaration();
    }
    jj_consume_token(0);
  }

  static final public void MainClass() throws ParseException {
    jj_consume_token(CLASS);
    ID();
    jj_consume_token(37);
    jj_consume_token(PUBLIC);
    jj_consume_token(STATIC);
    jj_consume_token(VOID);
    jj_consume_token(MAIN);
    jj_consume_token(38);
    jj_consume_token(STRING);
    jj_consume_token(39);
    jj_consume_token(40);
    ID();
    jj_consume_token(41);
    jj_consume_token(37);
    Statement();
    jj_consume_token(42);
    jj_consume_token(42);
  }

  static final public void ClassDeclaration() throws ParseException {
    jj_consume_token(CLASS);
    ID();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EXTENDS:
      jj_consume_token(EXTENDS);
      ID();
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
      VarDeclaration();
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
      MethodDeclaration();
    }
    jj_consume_token(42);
  }

  static final public void VarDeclaration() throws ParseException {
    Type();
    ID();
    jj_consume_token(SEMI);
  }

  static final public void MethodDeclaration() throws ParseException {
    jj_consume_token(PUBLIC);
    Type();
    ID();
    jj_consume_token(38);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOLEAN:
    case ID:
      Type();
      ID();
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
        Type();
        ID();
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
      VarDeclaration();
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
      Statement();
    }
    jj_consume_token(RETURN);
    Expression();
    jj_consume_token(SEMI);
    jj_consume_token(42);
  }

  static final public void Type() throws ParseException {
    if (jj_2_2(3)) {
      jj_consume_token(INT);
      jj_consume_token(39);
      jj_consume_token(40);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
        jj_consume_token(INT);
        break;
      case BOOLEAN:
        jj_consume_token(BOOLEAN);
        break;
      case ID:
        ID();
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void Statement() throws ParseException {
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
        Statement();
      }
      jj_consume_token(42);
      break;
    case IF:
      jj_consume_token(IF);
      jj_consume_token(38);
      Expression();
      jj_consume_token(41);
      Statement();
      jj_consume_token(ELSE);
      Statement();
      break;
    case WHILE:
      jj_consume_token(WHILE);
      jj_consume_token(38);
      Expression();
      jj_consume_token(41);
      Statement();
      break;
    case PRINT:
      jj_consume_token(PRINT);
      jj_consume_token(38);
      Expression();
      jj_consume_token(41);
      jj_consume_token(SEMI);
      break;
    default:
      jj_la1[9] = jj_gen;
      if (jj_2_3(2)) {
        ID();
        jj_consume_token(ASSIGN);
        Expression();
        jj_consume_token(SEMI);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ID:
          ID();
          jj_consume_token(39);
          Expression();
          jj_consume_token(40);
          jj_consume_token(ASSIGN);
          Expression();
          jj_consume_token(SEMI);
          break;
        default:
          jj_la1[10] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

/*
void Expression() : {}
{
    <THIS>
    | <TRUE>
    | <FALSE>
    | <INTEGER_LITERAL>
    | ID()
    | LOOKAHEAD(2)
      <NEW> <INT> "[" Expression() "]"
    | <NEW> ID() "(" ")"
    | "!" Expression()
    | "(" Expression() ")"
    | LOOKAHEAD(3)
      Expression() "." <LENGTH>
    | Expression() "[" Expression() "]"
    | Expression() BinaryOperator() Expression()
    | Expression() "." ID() "(" ( Expression() ( "," Expression() )* )? ")"
}
*/

/*
void Expression() : {}
{
    LOOKAHEAD(2) // lookahead aumentado em 1
      Expr1()
    | Expr1() Expr2()
}
 */
  static final public void Expression() throws ParseException {
    Expr1();
    label_8:
    while (true) {
      if (jj_2_4(2)) {
        ;
      } else {
        break label_8;
      }
      Expr2();
    }
  }

  static final public void Expr1() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case THIS:
      jj_consume_token(THIS);
      break;
    case TRUE:
      jj_consume_token(TRUE);
      break;
    case FALSE:
      jj_consume_token(FALSE);
      break;
    case INTEGER_LITERAL:
      jj_consume_token(INTEGER_LITERAL);
      break;
    case ID:
      ID();
      break;
    case 44:
      jj_consume_token(44);
      Expression();
      break;
    case 38:
      jj_consume_token(38);
      Expression();
      jj_consume_token(41);
      break;
    default:
      jj_la1[11] = jj_gen;
      if (jj_2_5(2)) {
        jj_consume_token(NEW);
        jj_consume_token(INT);
        jj_consume_token(39);
        Expression();
        jj_consume_token(40);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NEW:
          jj_consume_token(NEW);
          ID();
          jj_consume_token(38);
          jj_consume_token(41);
          break;
        default:
          jj_la1[12] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  static final public void Expr2() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OP_AND:
    case OP_LESS:
    case OP_ADD:
    case OP_SUB:
    case OP_MULT:
      BinaryOperator();
      Expression();
      break;
    case 39:
      jj_consume_token(39);
      Expression();
      jj_consume_token(40);
      break;
    default:
      jj_la1[15] = jj_gen;
      if (jj_2_6(2)) {
        jj_consume_token(45);
        jj_consume_token(LENGTH);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 45:
          jj_consume_token(45);
          ID();
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
            Expression();
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
              Expression();
            }
            break;
          default:
            jj_la1[14] = jj_gen;
            ;
          }
          jj_consume_token(41);
          break;
        default:
          jj_la1[16] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  static final public void BinaryOperator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OP_AND:
      jj_consume_token(OP_AND);
      break;
    case OP_LESS:
      jj_consume_token(OP_LESS);
      break;
    case OP_ADD:
      jj_consume_token(OP_ADD);
      break;
    case OP_SUB:
      jj_consume_token(OP_SUB);
      break;
    case OP_MULT:
      jj_consume_token(OP_MULT);
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void ID() throws ParseException {
    jj_consume_token(ID);
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

  static private boolean jj_3R_16() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(29)) {
    jj_scanpos = xsp;
    if (jj_scan_token(30)) {
    jj_scanpos = xsp;
    if (jj_scan_token(31)) {
    jj_scanpos = xsp;
    if (jj_scan_token(32)) {
    jj_scanpos = xsp;
    if (jj_scan_token(33)) return true;
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_3R_12()) return true;
    if (jj_scan_token(35)) return true;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(45)) return true;
    if (jj_scan_token(35)) return true;
    return false;
  }

  static private boolean jj_3_6() {
    if (jj_scan_token(45)) return true;
    if (jj_scan_token(LENGTH)) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_scan_token(39)) return true;
    if (jj_3R_17()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_13()) {
    jj_scanpos = xsp;
    if (jj_3R_14()) {
    jj_scanpos = xsp;
    if (jj_3_6()) {
    jj_scanpos = xsp;
    if (jj_3R_15()) return true;
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_3R_16()) return true;
    if (jj_3R_17()) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    if (jj_scan_token(NEW)) return true;
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_scan_token(NEW)) return true;
    if (jj_scan_token(INT)) return true;
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_scan_token(38)) return true;
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_scan_token(35)) return true;
    if (jj_scan_token(ASSIGN)) return true;
    return false;
  }

  static private boolean jj_3R_19() {
    if (jj_scan_token(44)) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_18() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(28)) {
    jj_scanpos = xsp;
    if (jj_scan_token(26)) {
    jj_scanpos = xsp;
    if (jj_scan_token(27)) {
    jj_scanpos = xsp;
    if (jj_scan_token(36)) {
    jj_scanpos = xsp;
    if (jj_scan_token(35)) {
    jj_scanpos = xsp;
    if (jj_3R_19()) {
    jj_scanpos = xsp;
    if (jj_3R_20()) {
    jj_scanpos = xsp;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3R_21()) return true;
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

  static private boolean jj_3R_17() {
    if (jj_3R_18()) return true;
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_scan_token(INT)) return true;
    if (jj_scan_token(39)) return true;
    if (jj_scan_token(40)) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_scan_token(22)) {
    jj_scanpos = xsp;
    if (jj_scan_token(23)) {
    jj_scanpos = xsp;
    if (jj_scan_token(35)) return true;
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
  static final private int[] jj_la1 = new int[18];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2000,0x80000,0xc00000,0x4000,0x0,0xc00000,0x1000180,0xc00000,0x1000180,0x1000180,0x0,0x1c000000,0x40000,0x0,0x1c040000,0xe0000000,0x0,0xe0000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x8,0x0,0x800,0x8,0x28,0x8,0x28,0x20,0x8,0x1058,0x0,0x800,0x1058,0x83,0x2000,0x3,};
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
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(MyParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 18; i++) {
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
