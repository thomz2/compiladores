package syntaxtree;

public class ArrayLookup extends Exp {
    private Exp e1, e2;

    public ArrayLookup(Exp _e1, Exp _e2) {
        e1 = _e1;
        e2 = _e2;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
