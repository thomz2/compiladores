package syntaxtree;
import IRTree.IRVisitor;
import syntaxtree.visitor.*;

public class IntegerLiteral extends Exp {
    public int i;

    public IntegerLiteral(int ai) {
        i=ai;
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