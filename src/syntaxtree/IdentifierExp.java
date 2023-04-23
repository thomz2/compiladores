package syntaxtree;

public class IdentifierExp extends Exp {
    private String s;

    public IdentifierExp(String _s) {
        s = _s;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
