package syntaxtree;
import IRTree.IRVisitor;
import syntaxtree.visitor.*;

public class While extends Statement {
    public Exp e;
    public Statement s;

    public While(Exp ae, Statement as) {
        e=ae; s=as;
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
