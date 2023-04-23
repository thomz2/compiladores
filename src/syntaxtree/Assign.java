package syntaxtree;
import syntaxtree.visitor.*;

public class Assign extends Statement {
    private Identifier id;
    private Exp value;

    public Assign(Identifier _i, Exp _e) {
        id = _i;
        value = _e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
