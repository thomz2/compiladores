package syntaxtree;

import java.util.Vector;

import IRTree.ExpEnc;
import IRTree.IRVisitor;
import syntaxtree.visitor.*;

public abstract class Exp {
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);

    public abstract ExpEnc accept(IRVisitor irVisitor);
}




