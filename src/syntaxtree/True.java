package syntaxtree;
import syntaxtree.visitor.*;

public class True extends Exp {
    public True() {
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
