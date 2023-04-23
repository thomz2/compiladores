package syntaxtree;

public class This extends Exp {
    public This() {
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
