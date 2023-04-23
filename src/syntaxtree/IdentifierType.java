package syntaxtree;

import syntaxtree.visitor.*;

public class IdentifierType extends Type {
    private String identifier_name;

    public IdentifierType(String _s) {
        identifier_name = _s;
    }
}
