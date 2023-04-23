package syntaxtree;

public class NewArray extends Exp {
    private Exp e;

    public NewArray(Exp _e) {
        e = _e;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
