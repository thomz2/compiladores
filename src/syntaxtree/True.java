package syntaxtree;

public class True extends Exp {
    public True() {
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
