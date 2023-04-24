package syntaxtree;

import syntaxtree.visitor.*;


public class Identifier {
    private String name;

    public Identifier(String _s) {
        name = _s;
    }

    @Override
    public String toString() {
        return name;
    }
}
