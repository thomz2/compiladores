package syntaxtree;

public class ClassDeclExtends extends ClassDecl {
    private Identifier extended_identifier;

    public ClassDeclExtends(Identifier _i, Identifier _j, VarDeclList _vl, MethodDeclList _ml) {
        class_identifier = _i;
        extended_identifier = _j;
        vl = _vl;
        ml = _ml;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
