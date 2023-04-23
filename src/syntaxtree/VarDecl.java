package syntaxtree;

public class VarDecl {
    private Type t;
    private Identifier id;

    public VarDecl(Type _t, Identifier _i) {
        t = _t;
        id = _i;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
