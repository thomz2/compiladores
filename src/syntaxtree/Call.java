package syntaxtree;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;
public class Call extends Exp {
    private Exp e;
    private Identifier i;
    private ExpList el;

    public Call(Exp _e, Identifier _i, ExpList _el) {
        //
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
