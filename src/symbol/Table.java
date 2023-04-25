package symbol;

import java.util.Hashtable;

public class Table {
    private Hashtable<Symbol, Object> inner_table;

    public Table() {
        inner_table = new Hashtable<Symbol, Object>();
    }

    //Retorna o próprio objeto, pra concatenar function calls
    //table.put(a, b).put(c, d).put(e, f)...
    public Table put(Symbol key, Object value){
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


