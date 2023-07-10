package Graph;

import java.util.Hashtable;

import Assem.Instr;
import Assem.InstrList;
import Temp.TempList;

public class AssemFlowGraph extends FlowGraph {
    private Hashtable<Node, Instr> instructionMap;
    private Hashtable<Node, Temp.Label> labelMap;
    private Hashtable<Temp.Label, Node> jumpMap;

    public AssemFlowGraph(Assem.InstrList instructions) {
        this.instructionMap = new Hashtable<>();
        this.labelMap = new Hashtable<>();
        this.jumpMap = new Hashtable<>();

        buildGraph(instructions);
    }

    public void buildGraph(Assem.InstrList instructions) {

        Node currentNode = null;
        Node lastNode = null;
        Instr currentLabel = null;
        Instr currentBranch = null;

        for (InstrList list = instructions; list != null; list = list.tail) {
            if (list.head instanceof Assem.LABEL) {
                currentLabel = list.head;
            } else {

                currentNode = this.newNode();
                currentBranch = list.head;
                instructionMap.put(currentNode, currentBranch);

                if (currentLabel != null) {
                    labelMap.put(currentNode, ((Assem.LABEL) currentLabel).label);
                    jumpMap.put(((Assem.LABEL) currentLabel).label, currentNode);

                    currentLabel = null;
                } if (lastNode != null) {
                    if (currentBranch.jumps() == null) {
                        this.addEdge(lastNode, currentNode);
                    }
                }

                lastNode = currentNode;
            }
        }

        currentNode = this.newNode();
        this.addEdge(lastNode, currentNode);
        lastNode = currentNode;
        instructionMap.put(lastNode, currentLabel);

        int index = 0;
        for (InstrList list = instructions; list != null; list = list.tail) {
            if (list.head instanceof Assem.OPER) {

                if (((Assem.OPER) list.head).jump != null) {

                    Temp.LabelList jumpLabels = ((Assem.OPER) list.head).jump.labels;
                    while (jumpLabels != null) {

                        if (jumpMap.get(jumpLabels.head) == null) {
                            this.addEdge(findNodeByIndex(index), lastNode);
                        }
                        else {
                            this.addEdge(findNodeByIndex(index), jumpMap.get(jumpLabels.head));
                        }

                        jumpLabels = jumpLabels.tail;

                    }

                }
                index++;

            }
        }
    }

    public Node findNodeByIndex(int index) {
        NodeList nodeIterator = this.nodes();

        while (nodeIterator != null) {
            if (nodeIterator.head.getValue() == index) {
                return nodeIterator.head;
            }
            nodeIterator = nodeIterator.tail;
        }
        return null;
    }

    @Override
    public TempList def(Node node) { return instructionMap.get(node).def(); }
    @Override
    public TempList use(Node node) { return instructionMap.get(node).use(); }
    @Override
    public boolean isMove(Node node) { return instructionMap.get(node) instanceof Assem.MOVE ? true : false; }
}
