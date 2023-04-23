package syntaxtree;

public class ArrayLength extends Exp {
    private Exp e;

    public ArrayLength(Exp _e) {
        e = _e;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }

}
