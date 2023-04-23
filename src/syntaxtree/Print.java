package syntaxtree;

public class Print extends Statement {
    private Exp contents;

    public Print(Exp _e) {
        contents = _e;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
