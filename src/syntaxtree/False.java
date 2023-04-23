package syntaxtree;

public class False extends Exp {
    public False() {
    }

    public void accept(Visitor v) {
        return v.visit(this);
    }
}
