package Tree;

import Mips.Codegen;

abstract public class Stm {
	abstract public ExpList kids();
	abstract public Stm build(ExpList kids);

    public void accept(Codegen cg) {
    }
}

