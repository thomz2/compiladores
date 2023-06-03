package syntaxtree;
import IRTree.IRVisitor;
import syntaxtree.visitor.*;

public class Block extends Statement {
    public StatementList sl;

    public Block(StatementList asl) {
        sl=asl;
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
