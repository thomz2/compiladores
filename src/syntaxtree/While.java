package syntaxtree;

public class While extends Statement {
    private Exp check;
    private Statement body;

    public While(Exp _e, Statement _s) {
        check = _e;
        body = _s;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
