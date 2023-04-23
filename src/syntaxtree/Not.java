package syntaxtree;

public class Not extends Exp {
    private Exp e;

    public Not(Exp _e) {
        e = _e;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
