package syntaxtree;
import IRTree.IRVisitor;
import syntaxtree.visitor.*;

public abstract class ClassDecl {
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
    public abstract IRTree.ExpEnc accept(IRVisitor irVisitor);
}
