package syntaxtree;

public class NewObject extends Exp {
    private Identifier i;

    public NewObject(Identifier _i) {
        i = _i;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
