package symbol;

public class Table {
    public Table() {

    }
    public void put(Symbol key, Object value);
    public Object get(Symbol key);
    public void beginScope();
    public void endScope();
    public java.util.Enumeration keys();
}
