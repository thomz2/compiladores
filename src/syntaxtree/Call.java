package syntaxtree;

public class Call extends Exp {
    private Exp e;
    private Identifier i;
    private ExpList el;

    public Call(Exp _e, Identifier _i, ExpList _el) {
        //
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
