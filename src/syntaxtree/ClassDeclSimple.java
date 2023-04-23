package syntaxtree;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;
public class ClassDeclSimple extends ClassDecl {
    public ClassDeclSimple(Identifier _i, VarDeclList _vl, MethodDeclList _ml) {
        class_identifier = _i;
        vl = _vl;
        ml = _ml;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
