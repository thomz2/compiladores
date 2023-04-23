package syntaxtree;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;
public class Not extends Exp {
    private Exp e;

    public Not(Exp _e) {
        e = _e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
