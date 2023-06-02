package syntaxtree;

import IRTree.IRVisitor;
import Tree.Exp;
import syntaxtree.visitor.*;

public abstract class Statement {
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);

    public abstract IRTree.ExpEnc accept(IRVisitor irVisitor);
}