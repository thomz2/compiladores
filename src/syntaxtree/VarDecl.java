package syntaxtree;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;
public class VarDecl {
    private Type t;
    private Identifier id;

    public VarDecl(Type _t, Identifier _i) {
        t = _t;
        id = _i;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
