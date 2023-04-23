package syntaxtree;
import syntaxtree.visitor.*;

public abstract class ClassDecl {
    protected Identifier class_identifier;
    protected VarDeclList vl;
    protected MethodDeclList ml;
}
