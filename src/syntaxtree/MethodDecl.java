package syntaxtree;

public class MethodDecl {
    private Type t;
    private Identifier id;
    private FormalList fl;
    private VarDeclList vl;
    private StatementList sl;
    private Exp return_exp;

    public MethodDecl(Type _t, Identifier _i, FormalList _fl,
                      VarDeclList _vl, StatementList _sl, Exp _ret) {
        t = _t;
        id = _i;
        fl = _fl;
        vl = _vl;
        sl = _sl;
        return_exp = _ret;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
