package syntaxtree;
import IRTree.IRVisitor;
import syntaxtree.visitor.*;

public class VarDecl {
    public Type t;
    public Identifier i;

    public VarDecl(Type at, Identifier ai) {
        t=at; i=ai;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public IRTree.ExpEnc accept(IRVisitor irVisitor) {
        return irVisitor.visit(this);
    }
}