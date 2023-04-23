package syntaxtree;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;
public class Block extends Statement {
    private StatementList sl;

    public Block(StatementList _sl) {
        sl = _sl;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
