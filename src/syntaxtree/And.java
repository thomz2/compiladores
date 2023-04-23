package syntaxtree;

public class And extends Exp {
    private Exp e1, e2;

    public And(Exp _e1, Exp _e2) {
        e1 = _e1;
        e2 = _e2;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
