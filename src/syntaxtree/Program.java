package syntaxtree;

public class Program {
    private MainClass mc;
    private ClassDeclList cl;

    public Program(MainClass _mc, ClassDeclList _cl) {
        mc = _mc;
        cl = _cl;
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
