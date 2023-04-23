package syntaxtree;

import java.util.Vector;
import syntaxtree.visitor.*;

//list classes
public class ClassDeclList {
    private Vector list;

    public ClassDeclList() {
        list = new Vector();
    }

    public void addElement(ClassDecl n) {
        list.addElement(n);
    }

    public ClassDecl elementAt(int i) {
        return (ClassDecl) list.elementAt(i);
    }

    public int size() {
        return list.size();
    }
}
