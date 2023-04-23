package syntaxtree;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;
public class Minus extends Exp {
    private Exp e1, e2;

    public Minus(Exp _e1, Exp _e2) {
        e1 = _e1;
        e2 = _e2;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
