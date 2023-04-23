package syntaxtree;

public class Formal {
    private Type t;
    private Identifier id;

    public Formal(Type _t, Identifier _i) {
        t = _t;
        id = _i;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
