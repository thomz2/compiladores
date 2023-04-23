package syntaxtree;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;
public class While extends Statement {
    private Exp check;
    private Statement body;

    public While(Exp _e, Statement _s) {
        check = _e;
        body = _s;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
