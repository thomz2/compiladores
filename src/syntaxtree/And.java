package syntaxtree;

import IRTree.IRVisitor;
import syntaxtree.visitor.*;

public class And extends Exp {
    public Exp e1,e2;

    public And(Exp ae1, Exp ae2) {
        e1=ae1; e2=ae2;
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