package syntaxtree;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;
public class MainClass {
    private Identifier class_identifier;
    private Identifier main_args;
    private Statement s;

    public MainClass(Identifier _i1, Identifier _i2, Statement _s) {
        class_identifier = _i1;
        main_args = _i2;
        s = _s;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
