package IRTree;

import syntaxtree.*;
public interface Visitor {
    public ExpEnc visit(Program n);
    public ExpEnc visit(MainClass n);
    public ExpEnc visit(ClassDeclSimple n);
    public ExpEnc visit(ClassDeclExtends n);
    public ExpEnc visit(VarDecl n);
    public ExpEnc visit(MethodDecl n);
    public ExpEnc visit(Formal n);
    public ExpEnc visit(IntArrayType n);
    public ExpEnc visit(BooleanType n);
    public ExpEnc visit(IntegerType n);
    public ExpEnc visit(IdentifierType n);
    public ExpEnc visit(Block n);
    public ExpEnc visit(If n);
    public ExpEnc visit(While n);
    public ExpEnc visit(Print n);
    public ExpEnc visit(Assign n);
    public ExpEnc visit(ArrayAssign n);
    public ExpEnc visit(And n);
    public ExpEnc visit(LessThan n);
    public ExpEnc visit(Plus n);
    public ExpEnc visit(Minus n);
    public ExpEnc visit(Times n);
    public ExpEnc visit(ArrayLookup n);
    public ExpEnc visit(ArrayLength n);
    public ExpEnc visit(Call n);
    public ExpEnc visit(IntegerLiteral n);
    public ExpEnc visit(True n);
    public ExpEnc visit(False n);
    public ExpEnc visit(IdentifierExp n);
    public ExpEnc visit(This n);
    public ExpEnc visit(NewArray n);
    public ExpEnc visit(NewObject n);
    public ExpEnc visit(Not n);
    public ExpEnc visit(Identifier n);
}
