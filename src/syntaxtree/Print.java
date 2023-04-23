package syntaxtree;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;
public class Print extends Statement {
    private Exp contents;

    public Print(Exp _e) {
        contents = _e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
