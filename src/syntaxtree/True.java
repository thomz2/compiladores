package syntaxtree;
import IRTree.ExpEnc;
import IRTree.IRVisitor;
import syntaxtree.visitor.*;

public class True extends Exp {
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public IRTree.ExpEnc accept(IRVisitor irVisitor) {
        return irVisitor.visit(this);
    }
}