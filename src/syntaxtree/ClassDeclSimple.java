package syntaxtree;

public class ClassDeclSimple extends ClassDecl {
    public ClassDeclSimple(Identifier _i, VarDeclList _vl, MethodDeclList _ml) {
        class_identifier = _i;
        vl = _vl;
        ml = _ml;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
