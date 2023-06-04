package IRTree;

import Frame.Frame;
import symbol.*;
import Tree.*;

import java.util.*;

import syntaxtree.*;
import syntaxtree.visitor.DepthFirstVisitor;
import syntaxtree.visitor.TypeDepthFirstVisitor;
import Temp.*;
import utils.Converter;

public class IRVisitor implements IRTree.Visitor {

    Stack<Frame> frames;
    Frame frameAtual;
    public ClassTable mainclass;
    public Hashtable<Symbol, ClassTable> classList;
    MethodTable metodoAtual;
    ClassTable classeAtual;
    public ArrayList <Frag> fragmentos;
    ClassTable classeCallStack;
    MethodTable metodoCallStack;


    public IRVisitor(TypeDepthFirstVisitor v, Frame frameAtual) {
        mainclass = v.mainClass;
        classList = v.classList;

        this.frameAtual = frameAtual;
        frames = new Stack<Frame>();
        frames.push(frameAtual);
        fragmentos = new ArrayList<Frag>();
    }

    public IRVisitor(DepthFirstVisitor v, Frame frameAtual) {
        mainclass = v.mainClass;
        classList = v.classList;

        this.frameAtual = frameAtual;
        frames = new Stack<Frame>();
        frames.push(frameAtual);
        fragmentos = new ArrayList<Frag>();
    }

    public ExpEnc pegarEndereco(Symbol var) {

        Field varEnd;

        // testando para metodo atual
        if((varEnd = metodoAtual.getInParams(var.toString())) != null);
        else if((varEnd = metodoAtual.getInLocals(var.toString()))!= null);

        // testando para classe
        else if ((varEnd = mainclass.getInAtb(var.toString())) != null);
        else varEnd= classeAtual.getInAtb(var.toString());

        return new ExpEnc(varEnd.access.exp(new TEMP(frameAtual.FP())));
    }

    @Override
    public ExpEnc visit(Program n) {

        n.m.accept(this);

        for (int i = 0; i < n.cl.size(); i++) {
            this.classeAtual = this.classList.get(Symbol.symbol(n.cl.elementAt(i).toString()));
            n.cl.elementAt(i).accept(this);
        }

        Tree.Print h = new Tree.Print(System.out);

        // DEBUG
        Stm temp;
        for (int i = 0; i < fragmentos.size(); i++) {
            temp = fragmentos.get(i).body;
            h.prStm(temp);
        }

        return null;
    }

    @Override
    public ExpEnc visit(MainClass n) {

        this.classeAtual = mainclass;

        ArrayList<Boolean> j = new ArrayList<Boolean>();
        j.add(false);

        frameAtual = frameAtual.newFrame(Symbol.symbol("principal"),j); //new ArrayList<Boolean>());
        frames.push(frameAtual);

        Stm body = new EXPR(n.s.accept(this).getExp());
        ArrayList<Stm> lista = new ArrayList<Stm>();
        lista.add(body);

        frameAtual.procEntryExit1(lista);
        fragmentos.add(new Frag(body,frameAtual));
        frames.pop();

        return null;
    }

    @Override
    public ExpEnc visit(ClassDeclSimple n) {

        this.classeAtual = classList.get(Symbol.symbol(n.i.toString()));
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }

        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
        }

        return null;
    }

    @Override
    public ExpEnc visit(ClassDeclExtends n) {

        this.classeAtual = classList.get(Symbol.symbol(n.i.toString()));
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }

        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
        }

        return null;
    }

    @Override
    public ExpEnc visit(VarDecl n) {

        Field varEnd;

        if(metodoAtual != null) {
            varEnd= metodoAtual.getInParams(n.i.toString());

            if(varEnd != null) {
                varEnd.access = frameAtual.allocLocal(false);
                return null;
            }

            varEnd = metodoAtual.getInLocals(n.i.toString());

            if(varEnd!= null) {
                varEnd.access = frameAtual.allocLocal(false);
                return null;
            }
        }

        varEnd = classeAtual.getInAtb(n.i.toString());

        if(varEnd!= null) varEnd.access = frameAtual.allocLocal(false);

        return new ExpEnc(varEnd.access.exp((new TEMP(frameAtual.FP()))));
    }

    @Override
    public ExpEnc visit(MethodDecl n) {

        Stm corpo = new EXPR(new CONST(0));

        ArrayList<Boolean> j = new ArrayList<Boolean>();

        metodoAtual = classeAtual.getInMethods(n.i.toString());

        for (int i = 0; i <= n.fl.size(); i++) {
            j.add(false);
        }

        frameAtual = frameAtual.newFrame(Symbol.symbol(classeAtual.toString()+"$"+ metodoAtual.toString()), (java.util.List<Boolean>)j);
        frames.push(frameAtual);

        for (int i = 0; i < n.fl.size(); i++) {
            n.fl.elementAt(i).accept(this);
        }

        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }

        for (int i = 0; i < n.sl.size(); i++) {
            corpo = new SEQ(corpo,new EXPR(n.sl.elementAt(i).accept(this).getExp()));
        }

        ArrayList<Stm> l = new ArrayList<Stm>();
        l.add(corpo);
        frameAtual.procEntryExit1(l);
        fragmentos.add(new Frag(corpo,frameAtual));
        metodoAtual = null;
        frames.pop();

        return null;
    }

    @Override
    public ExpEnc visit(Formal n) {

        Field varEnd = metodoAtual.getInParams(n.i.toString());
        varEnd.access = frameAtual.allocLocal(false);

        return null;
    }

    @Override
    public ExpEnc visit(IntArrayType n) {
        return null;
    }

    @Override
    public ExpEnc visit(BooleanType n) {
        return null;
    }

    @Override
    public ExpEnc visit(IntegerType n) {
        return null;
    }

    @Override
    public ExpEnc visit(IdentifierType n) {
        return null;
    }

    @Override
    public ExpEnc visit(Block n) {
        Tree.Exp stm = new CONST(0);
        for (int i = 0; i < n.sl.size(); i++) {
            stm = new ESEQ(new SEQ(new EXPR(stm),new EXPR(n.sl.elementAt(i).accept(this).getExp())),new CONST(0));
        }

        return new ExpEnc(stm);
    }

    @Override
    public ExpEnc visit(If n) {

        Label ifF = new Label();
        Label elseE = new Label();
        Label fim = new Label();

        Tree.Exp cond = n.e.accept(this).getExp();

        ExpEnc label1 = n.s1.accept(this);
        ExpEnc label2 = n.s2.accept(this);

        Tree.Exp Cx = new ESEQ(new SEQ(new CJUMP(CJUMP.GT,cond,new CONST(0),ifF,elseE),new SEQ(new SEQ(new LABEL(ifF),new SEQ(new EXPR(label1.getExp()), new JUMP(fim))),new SEQ(new LABEL(elseE),new SEQ(new EXPR(label2.getExp()),new LABEL(fim))))),new CONST(0));

        return new ExpEnc(Cx);
    }

    @Override
    public ExpEnc visit(While n) {

        Label teste = new Label();
        Label corpo = new Label();
        Label fim = new Label();

        ExpEnc cond = n.e.accept(this);
        ExpEnc stm = n.s.accept(this);

        return new ExpEnc (new ESEQ(new SEQ (new SEQ(new LABEL(teste), new SEQ( new CJUMP(CJUMP.GT,cond.getExp(),new CONST(0), corpo, fim), new SEQ(new LABEL(corpo), new SEQ(new EXPR(stm.getExp()),new JUMP(teste))))), new LABEL(fim)), new CONST(0)));
    }

    @Override
    public ExpEnc visit(syntaxtree.Print n) {
        ExpEnc exp = n.e.accept(this);
        Tree.ExpList parametros= new Tree.ExpList(exp.getExp(),null);

        return new ExpEnc( frameAtual.externalCall("print", Converter.ExpListToList(parametros)));
    }

    @Override
    public ExpEnc visit(Assign n) {
        ExpEnc i = n.i.accept(this);
        ExpEnc e = n.e.accept(this);

        return new ExpEnc(new ESEQ(new MOVE( i.getExp(), e.getExp() ), new CONST(0)));
    }

    @Override
    public ExpEnc visit(ArrayAssign n) {
        ExpEnc i = n.i.accept(this);
        ExpEnc e1 = n.e1.accept(this);
        ExpEnc e2 = n.e2.accept(this);

        return new ExpEnc( new ESEQ(new MOVE(new MEM(new BINOP(BINOP.PLUS, i.getExp(), new BINOP(BINOP.MUL, e1.getExp(), new CONST(frameAtual.wordSize())))), e2.getExp()), new CONST(0)) );
    }

    @Override
    public ExpEnc visit(And n) {
        ExpEnc e1 = n.e1.accept(this);
        ExpEnc e2 = n.e2.accept(this);

        return new ExpEnc(new BINOP(BINOP.AND, e1.getExp(), e2.getExp()));
    }

    @Override
    public ExpEnc visit(LessThan n) {
        ExpEnc e1 = n.e1.accept(this);
        ExpEnc e2 = n.e2.accept(this);

        return new ExpEnc(new BINOP(BINOP.MINUS, e2.getExp(), e1.getExp()));
    }

    @Override
    public ExpEnc visit(Plus n) {
        ExpEnc e1 = n.e1.accept(this);
        ExpEnc e2 = n.e2.accept(this);

        return new ExpEnc(new BINOP(BINOP.PLUS, e1.getExp(), e2.getExp()));
    }

    @Override
    public ExpEnc visit(Minus n) {
        ExpEnc e1 = n.e1.accept(this);
        ExpEnc e2 = n.e2.accept(this);

        return new ExpEnc(new BINOP(BINOP.MINUS, e1.getExp(), e2.getExp()));
    }

    @Override
    public ExpEnc visit(Times n) {
        ExpEnc e1 = n.e1.accept(this);
        ExpEnc e2 = n.e2.accept(this);

        return new ExpEnc(new BINOP(BINOP.MUL, e1.getExp(), e2.getExp()));
    }

    @Override
    public ExpEnc visit(ArrayLookup n) {
        ExpEnc e1 = n.e1.accept(this);
        ExpEnc e2 = n.e2.accept(this);

        return new ExpEnc(new MEM(new BINOP(BINOP.PLUS, e1.getExp(), new BINOP(BINOP.MUL, new BINOP(BINOP.PLUS, new CONST(1), e2.getExp()), new CONST(frameAtual.wordSize())))));

    }

    @Override
    public ExpEnc visit(ArrayLength n) {
        return new ExpEnc(new MEM(pegarEndereco(Symbol.symbol(( (IdentifierExp) n.e).s)).getExp()));
    }

    // TODO: FAZER POR ULTIMO
    @Override
    public ExpEnc visit(Call n) {
        ClassTable j = null;

        Tree.ExpList lista = null;
        for (int i = n.el.size()-1; i >= 0 ; i--) {
            lista = new Tree.ExpList(n.el.elementAt(i).accept(this).getExp(), lista);
        }

        lista = new Tree.ExpList(n.e.accept(this).getExp(),lista);

        if (n.e instanceof This) {
            j = classeAtual;
        }

        if (n.e instanceof NewObject) {
            //System.out.println("new object to string: " + n.e.toString());
            j = classList.get(Symbol.symbol(n.e.toString()));
        }

        if (n.e instanceof IdentifierExp) {
            Field var;

            // procurando v's por metodos
            var = metodoAtual.getInParams(n.e.toString());
            if (var == null) var = metodoAtual.getInLocals(n.e.toString());

            if (var == null) {
                j = classeAtual;
            }
            else {
                j = classList.get(Symbol.symbol(var.getNome()));
            }

        }

        if(n.e instanceof Call) {
            var tipoRetorno = metodoCallStack.getTipo();

            Iterator<Symbol> iterator = classList.keySet().iterator();
            while (iterator.hasNext()) {
                ClassTable iteratorClass = classList.get(iterator.next());
                if (iteratorClass.getNome().equals(tipoRetorno)) {
                    j = iteratorClass;
                    break;
                }
            }
        }

        if(j == null)
        {
            System.out.println("TESTE");
            if(classeAtual != null)
                System.out.println(classeAtual.getNome());
            if(metodoAtual != null)
                System.out.println(metodoAtual.getNome());
            System.out.println(n.e);
            System.out.println(n.i);
            System.out.println(n.e.getClass());
        }

        classeCallStack = j;
        metodoCallStack = j.getInMethods(n.i.toString());
        return new ExpEnc(new CALL(new NAME(new Label(j.getNome()+"$"+n.i.toString())),lista));
    }

    @Override
    public ExpEnc visit(IntegerLiteral n) {
        return new ExpEnc(new CONST(n.i));
    }

    @Override
    public ExpEnc visit(True n) {
        return new ExpEnc(new CONST(1));
    }

    @Override
    public ExpEnc visit(False n) {
        return new ExpEnc(new CONST(0));
    }

    @Override
    public ExpEnc visit(IdentifierExp n) {
        return pegarEndereco(Symbol.symbol(n.s));
    }

    @Override
    public ExpEnc visit(This n) {
        return new ExpEnc(new MEM(new TEMP(frameAtual.FP())));
    }

    @Override
    public ExpEnc visit(NewArray n) {

        ExpEnc tam = n.e.accept(this);

        Tree.Exp aloc = new BINOP(BINOP.MUL, new BINOP(BINOP.PLUS, tam.getExp(), new CONST(1)) ,new CONST(frameAtual.wordSize()));

        Tree.ExpList parametros = new Tree.ExpList(aloc,null);

        List<Tree.Exp> listaConvertida = Converter.ExpListToList(parametros);

        Tree.Exp retorno = frameAtual.externalCall("initArray", listaConvertida);

        return new ExpEnc(retorno);
    }

    @Override
    public ExpEnc visit(NewObject n) {
        ClassTable j = classList.get(Symbol.symbol(n.i.toString()));
        int tam = j.getAtributos().size();

        Tree.ExpList parametros = new Tree.ExpList(new BINOP(BINOP.MUL,new CONST(1+tam) , new CONST(frameAtual.wordSize())), null);
        List<Tree.Exp> lista = Converter.ExpListToList(parametros);
        return new ExpEnc(frameAtual.externalCall("malloc", lista));
    }

    @Override
    public ExpEnc visit(Not n) {
        ExpEnc e = n.e.accept(this);

        return new ExpEnc(new BINOP(BINOP.MINUS,new CONST(1), e.getExp()));
    }

    @Override
    public ExpEnc visit(Identifier n) {
        return pegarEndereco(Symbol.symbol(n.s));
    }
}
