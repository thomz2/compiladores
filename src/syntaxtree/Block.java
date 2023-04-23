package syntaxtree;

public class Block extends Statement {
    private StatementList sl;

    public Block(StatementList _sl) {
        sl = _sl;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
