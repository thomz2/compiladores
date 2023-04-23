package syntaxtree;

public class Assign extends Statement {
    private Identifier id;
    private Exp value;

    public Assign(Identifier _i, Exp _e) {
        id = _i;
        value = _e;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
