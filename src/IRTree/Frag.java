package IRTree;

import Tree.*;
public class Frag {
    public Stm body;
    public Frame.Frame frame;

    public Frag(Stm body, Frame.Frame frame) {
        this.body = body;
        this.frame = frame;
    }
}
