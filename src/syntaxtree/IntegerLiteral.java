package syntaxtree;

public class IntegerLiteral extends Exp {
    private int i;

    public IntegerLiteral(int _i) {
        i = _i;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
