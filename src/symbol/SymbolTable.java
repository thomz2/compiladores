package symbol;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import symbol.Pair;
import syntaxtree.Identifier;
import syntaxtree.Type;

public class SymbolTable {
    private Hashtable<Symbol, Object> inner_table;

    public SymbolTable() {
        inner_table = new Hashtable<>();
    }

    //Retorna o próprio objeto, pra concatenar function calls
    //table.put(a, b).put(c, d).put(e, f)...
    public SymbolTable put(Symbol key, Object value){
        inner_table.put(key, value);
        return this;
    }
    public Object get(Symbol key){
        return inner_table.get(key);
    }
    public java.util.Enumeration keys(){
        return inner_table.keys();
    }
}


class ClassTable extends SymbolTable {

    private ArrayList<Pair<Identifier, Type>> fields;

    public ClassTable(){
        fields = new ArrayList<Pair<Identifier, Type>>();
    }
    public addVar(id, t) {
        fields.add(new Pair<Identifier, Type>(id, t));
    }
}