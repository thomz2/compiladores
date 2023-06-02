package syntaxtree;

import IRTree.ExpEnc;
import IRTree.IRVisitor;
import syntaxtree.visitor.*;

public class Identifier {
    public String s;

    public Identifier(String as) {
        s=as;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public String toString(){
        return s;
    }

    public IRTree.ExpEnc accept(IRVisitor irVisitor) {
        return irVisitor.visit(this);
    }
}