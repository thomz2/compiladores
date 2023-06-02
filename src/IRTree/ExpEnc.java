package IRTree;

import Tree.*;

public class ExpEnc {
        private Exp e;
        public ExpEnc(Exp e) {
            this.e = e;
        }
        public Exp unEx() {
            return e;
        }
}
