package syntaxtree;
import syntaxtree.visitor.*;

public class IdentifierExp extends Exp {
    private String s;

    public IdentifierExp(String _s) {
        s = _s;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
