package syntaxtree.visitor;

import symbol.ClassTable;
import symbol.MethodTable;
import symbol.Symbol;
import symbol.Table;
import syntaxtree.*;
import utils.ErrorMsg;

import java.util.Hashtable;
import java.util.Iterator;

public class TypeDepthFirstVisitor implements TypeVisitor {

    private ErrorMsg error = new ErrorMsg();
    public ClassTable mainClass;
    public Hashtable<Symbol, ClassTable> classList;// = new Hashtable<Symbol, ClassTable>();



    public ClassTable currentClass;

    public TypeDepthFirstVisitor(DepthFirstVisitor v) {
        mainClass = v.mainClass;
        classList = v.classList;
    }

    // MainClass m;
    // ClassDeclList cl;
    public Type visit(Program n) {
        n.m.accept(this);
        for ( int i = 0; i < n.cl.size(); i++ ) {
            n.cl.elementAt(i).accept(this);
        }
        return null;
    }

    // Identifier i1,i2;
    // Statement s;
    public Type visit(MainClass n) {
        currentClass = mainClass;

        n.i1.accept(this);
        n.i2.accept(this);
        n.s.accept(this);
        return null;
    }

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public Type visit(ClassDeclSimple n) {
        currentClass = classList.get(Symbol.symbol(n.i.s));

        n.i.accept(this);
        for ( int i = 0; i < n.vl.size(); i++ ) {
            n.vl.elementAt(i).accept(this);
        }
        for ( int i = 0; i < n.ml.size(); i++ ) {
            n.ml.elementAt(i).accept(this);
        }
        return null;
    }

    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public Type visit(ClassDeclExtends n) {
        currentClass = classList.get(Symbol.symbol(n.i.s));

        n.i.accept(this);
        n.j.accept(this);
        for ( int i = 0; i < n.vl.size(); i++ ) {
            n.vl.elementAt(i).accept(this);
        }
        for ( int i = 0; i < n.ml.size(); i++ ) {
            n.ml.elementAt(i).accept(this);
        }
        return null;
    }

    // Type t;
    // Identifier i;
    public Type visit(VarDecl n) {
        n.t.accept(this);
        n.i.accept(this);
        return null;
    }

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public Type visit(MethodDecl n) {
        n.t.accept(this);
        n.i.accept(this);
        for ( int i = 0; i < n.fl.size(); i++ ) {
            n.fl.elementAt(i).accept(this);
        }
        for ( int i = 0; i < n.vl.size(); i++ ) {
            n.vl.elementAt(i).accept(this);
        }
        for ( int i = 0; i < n.sl.size(); i++ ) {
            n.sl.elementAt(i).accept(this);
        }
        n.e.accept(this);
        return null;
    }

    // Type t;
    // Identifier i;
    public Type visit(Formal n) {
        n.t.accept(this);
        n.i.accept(this);
        return null;
    }



    // StatementList sl;
    public Type visit(Block n) {
        for ( int i = 0; i < n.sl.size(); i++ ) {
            n.sl.elementAt(i).accept(this);
        }
        return null;
    }

    // Exp e;
    // Statement s1,s2;
    public Type visit(If n) {
        if (!( n.e.accept(this) instanceof BooleanType)) {
            error.complain("Condição do 'If' deve ser de tipo 'boolean'.");
        }
        n.s1.accept(this);
        n.s2.accept(this);
        return null;
    }

    // Exp e;
    // Statement s;
    public Type visit(While n) {
        if (!( n.e.accept(this) instanceof BooleanType)) {
            error.complain("Condição do 'While' deve ser de tipo 'boolean'.");
        }
        n.s.accept(this);
        return null;
    }

    // Exp e;
    public Type visit(Print n) {
        Type t = n.e.accept(this);
        if (!error.anyErrors) // TODO: ver se isso aqui eh encaixavel, colocando apenas para evitar erro do java
            if (!(t instanceof IntegerType || t instanceof BooleanType || t instanceof IdentifierType)) {
                error.complain("Print recebeu valor de tipo inválido: '"+ t.toString() +"'.");
            }
        return null;
    }

    // Identifier i;
    // Exp e;
    public Type visit(Assign n) {
        IdentifierType idType = (IdentifierType) n.i.accept(this); //erro de não existir vai aqui
        Type expType = n.e.accept(this);

        if (!(expType.toString().equals(Table.get(Symbol.symbol(idType.s))))) {
            error.complain("Tipo da variável não é o mesmo tipo atribuído: variável '"+idType.s
                    +"' de tipo '"+Table.get(Symbol.symbol(idType.s))+"' e expressão '"+expType.toString()+"'.");
        }

        return null;
    }

    // Identifier i;
    // Exp e1,e2;
    public Type visit(ArrayAssign n) {
        IdentifierType idType = (IdentifierType) n.i.accept(this); //erro de não existir vai aqui
        if (!(Table.inner_table.get(Symbol.symbol(idType.s)).equals("int[]"))) {
            error.complain("Identificador '"+ idType.s +"' deve ser do tipo 'int[]'.");
        }

        if(!(n.e1.accept(this) instanceof IntegerType)) {
            error.complain("Posição de um array deve ser de tipo 'int'.");
        } else if(!(n.e2.accept(this) instanceof IntegerType)) {
            error.complain("Valor a inserir dentro do array deve ser de tipo 'int'.");
        }

        return null; //statements não tem tipo
    }

    // Exp e1,e2;
    public Type visit(And n) {
        if(!(n.e1.accept(this) instanceof BooleanType)) {
            error.complain("Valor à esquerda da operação '&&' deve ser de tipo 'boolean'.");
        } else if(!(n.e2.accept(this) instanceof BooleanType)) {
            error.complain("Valor à direita da operação '&&' deve ser de tipo 'boolean'.");
        }
        return new BooleanType();
    }

    // Exp e1,e2;
    public Type visit(LessThan n) {
        if(!(n.e1.accept(this) instanceof IntegerType)) {
            error.complain("Valor à esquerda da operação '*' deve ser de tipo 'int'.");
        } else if(!(n.e2.accept(this) instanceof IntegerType)) {
            error.complain("Valor à direita da operação '*' deve ser de tipo 'int'.");
        }
        return new BooleanType();
    }

    // Exp e1,e2;
    public Type visit(Plus n) {
        if(!(n.e1.accept(this) instanceof IntegerType)) {
            error.complain("Valor à esquerda da operação '+' deve ser de tipo 'int'.");
        } else if(!(n.e2.accept(this) instanceof IntegerType)) {
            error.complain("Valor à direita da operação '+' deve ser de tipo 'int'.");
        }
        return new IntegerType();
    }

    // Exp e1,e2;
    public Type visit(Minus n) {
        if(!(n.e1.accept(this) instanceof IntegerType)) {
            error.complain("Valor à esquerda da operação '-' deve ser de tipo 'int'.");
        } else if(!(n.e2.accept(this) instanceof IntegerType)) {
            error.complain("Valor à direita da operação '-' deve ser de tipo 'int'.");
        }
        return new IntegerType();
    }

    // Exp e1,e2;
    public Type visit(Times n) {
        if(!(n.e1.accept(this) instanceof IntegerType)) {
            error.complain("Valor à esquerda da operação '*' deve ser de tipo 'int'.");
        } else if(!(n.e2.accept(this) instanceof IntegerType)) {
            error.complain("Valor à direita da operação '*' deve ser de tipo 'int'.");
        }
        return new IntegerType();
    }

    // Exp e1,e2;
    public Type visit(ArrayLookup n) {

        if(!(n.e1.accept(this) instanceof IdentifierType)) {
            error.complain("Identificador não válido.");
        } else if(!(n.e2.accept(this) instanceof IntegerType)) {
            error.complain("Posição do array deve ser de tipo 'int'.");
        }

        return new IntegerType();
    }

    // Exp e;
    public Type visit(ArrayLength n) {
        if(!(n.e.accept(this) instanceof IdentifierType)) {
            error.complain("Identificador não válido.");
        }
        return new IntegerType();
    }

    // Exp e;
    // Identifier i;
    // ExpList el;
    public Type visit(Call n) {

        if (!(n.e.accept(this) instanceof IdentifierType)) {
            error.complain("A expressão não é um identificador");
        }
        //se for um identificador não valido, o IdentifierExp vai mandar um erro
        IdentifierType objIdType = (IdentifierType) n.e.accept(this);

        //descobrir que classe pertence a expressão n.e
        ClassTable objClassTable = null;
        Iterator<Symbol> classIt = classList.keySet().iterator();
        while (classIt.hasNext()) {
            ClassTable c = classList.get(classIt.next());
            if (c.getNome().equals(objIdType.toString())) {
                objClassTable = c;
                break;
            }
        }
        if (objClassTable == null) {
            error.complain("A classe de '" + objIdType.s + "' não foi declarada.");
            return null;
        }

        //saber se n.i, o método, é um método válido da classe de n.e
        MethodTable objMethodTable = null;
        for (MethodTable m : objClassTable.getMetodos()) {
            if (m.getNome().equals(n.i.s)) {
                objMethodTable = m;
                break;
            }
        }
        if (objMethodTable == null) {
            error.complain("O método '" + n.i.s + "' não existe na classe '" + objClassTable.getNome() + ".");
            return null;
        }

        //TODO: testar os parametros
        n.i.accept(this);
        //avaliar se os parametros são válidos

        if (n.el.size() != objMethodTable.getParametros().size()) {
            error.complain("Quantidade incorreta de parâmetros; foram dados " + n.el.size() +
                    ", esperava-se " +  objMethodTable.getParametros().size() + ".");
            // return null; // ver se eh necessario esse null
        }


        for ( int i = 0; i < n.el.size(); i++ ) {
            Type paramType = n.el.elementAt(i).accept(this);
            String tipoPAtual = "";
            if (i < objMethodTable.getParametros().size()) {
                // testar se o parametro atual ta dentro da lista de parametros da declaracao do metodo
                tipoPAtual = objMethodTable.getParametros().get(i).getTipo();
            }
            // apontando dois erros ao mesmo tempo
            if (!tipoPAtual.equals(paramType.toString()) && tipoPAtual != "") {
                error.complain("Parâmetro número '" + i + "' não é do tipo '" + tipoPAtual + "'." );

            }

            //TODO: ver se isso aqui eh necessario
//            else {
//                if (paramType instanceof IdentifierType) {
//                    //validado no visit(IdentifierExp)
//                }
//            }
        }

        if (objMethodTable.getTipo().equals("int")) {
            return new IntegerType();
        } else if (objMethodTable.getTipo().equals("int[]")) {
            return new IntArrayType();
        } else if (objMethodTable.getTipo().equals("boolean")) {
            return new BooleanType();
        } else {
            return new IdentifierType(objMethodTable.getTipo());
        }

    }

    // int i;
    public Type visit(IntegerLiteral n) {
        return new IntegerType();
    }

    public Type visit(True n) {
        return new BooleanType();
    }

    public Type visit(False n) {
        return new BooleanType();
    }

    // String s;
    public Type visit(IdentifierExp n) {

        //se o identifier atual não foi declarado ainda
        if (!(Table.inner_table.containsKey(Symbol.symbol(n.s)))) {
            error.complain("Identificador '" + n.s + "' não foi declarado.");
        }

        String typeStr = Table.get(Symbol.symbol(n.s));
        if (typeStr.equals("int[]")) {
            return new IntArrayType();
        }
        else if (typeStr.equals("int")) {
            return new IntegerType();
        }
        else if (typeStr.equals("boolean")) {
            return new BooleanType();
        }

        return new IdentifierType(n.s);
    }

    public Type visit(This n) {
        return new IdentifierType(currentClass.getNome());
    }

    // Exp e;
    public Type visit(NewArray n) {
        if (!(n.e.accept(this) instanceof IntegerType)) {
            error.complain("Expressão dentro do novo array deve ser de tipo 'int'.");
        }
        return new IntArrayType();
    }

    // Identifier i;
    public Type visit(NewObject n) {
        Type objType = n.i.accept(this);

        boolean classExists = false;
        Iterator<Symbol> iterator = classList.keySet().iterator();
        while (iterator.hasNext()) {
            ClassTable iteratorClass = classList.get(iterator.next());
            if (iteratorClass.getNome().equals(objType.toString())) {
                classExists = true;
                break;
            }
        }

        if (!classExists) {
            error.complain("Objeto não pôde ser criado pois a classe '" + objType.toString() + "' não foi declarada.");
        }

        if (!(objType instanceof IdentifierType)) {
            error.complain("Identificador '" + objType.toString() + "' do objeto é inválido.");
        }

        return new IdentifierType(objType.toString());
    }

    // Exp e;
    public Type visit(Not n) {
        if (!(n.e.accept(this) instanceof BooleanType)) {
            error.complain("Expressão depois do 'Not' deve ser de tipo 'boolean'.");
        }
        return new BooleanType();
    }


    public Type visit(IntArrayType n) {
        return new IntArrayType();
    }

    public Type visit(BooleanType n) {
        return new BooleanType();
    }

    public Type visit(IntegerType n) {
        return new IntegerType();
    }

    // String s;
    public Type visit(IdentifierType n) {
        return n;
    }


    // String s;
    public Type visit(Identifier n) {
        // erro tirado por gambiarra
        if (!(Table.inner_table.containsKey(Symbol.symbol(n.s))) && !mainClass.mainArgs.get(0).equals(n.s) ) {
            error.complain("Identificador '" + n.s + "' não foi declarado.");
        }
        return new IdentifierType(n.s);
    }
}
