package syntaxtree;

public class If extends Statement {
    private Exp condition;
    private Statement statement_true;
    private Statement statement_false;

    public If(Exp _e, Statement _s1, Statement _s2) {
        condition = _e;
        statement_true = _s1;
        statement_false = _s2;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
