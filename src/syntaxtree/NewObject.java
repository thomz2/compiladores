package syntaxtree;
import syntaxtree.visitor.*;

public class NewObject extends Exp {
    private Identifier i;

    public NewObject(Identifier _i) {
        i = _i;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
