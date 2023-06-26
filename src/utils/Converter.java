package utils;

import java.util.ArrayList;
import java.util.List;

import Assem.Instr;
import Assem.InstrList;
import Temp.TempList;
import Tree.*;

public class Converter {

    public static List<Tree.Exp> ExpListToList(Tree.ExpList expList){

        ArrayList<Tree.Exp> list = new ArrayList<Tree.Exp>();

        ExpList temp = expList;
        while (temp != null) {
            list.add(temp.head);
            temp = temp.tail;
        }

        return list;
    }
    public static Tree.ExpList ListToExpList(List<Tree.Exp> list) {

        Tree.ExpList expList = null;

        for (int i = list.size()-1; i >= 0; --i) {
            expList = new Tree.ExpList(list.get(i), expList);
        }

        return expList;
    }

    public static List<Temp.Temp> TempListToList(Temp.TempList tempList) {

        ArrayList<Temp.Temp> list = new ArrayList<Temp.Temp>();

        TempList temp = tempList;
        while (temp != null) {
            list.add(temp.head);
            temp = temp.tail;
        }

        return list;
    }
    public static Temp.TempList ListToTempList(List<Temp.Temp> list) {

        Temp.TempList tempList = null;

        for (int i = list.size()-1; i >= 0; --i) {
            tempList = new Temp.TempList(list.get(i), tempList);
        }

        return tempList;
    }

    public static Temp.Temp[] TempListToArray(TempList tempList) {

        Temp.Temp array[] = new Temp.Temp[Converter.TempListToList(tempList).size()];
        TempList temp = tempList;

        for (int i = 0; i < array.length; i++) {
            array[i] = temp.head;
            temp = temp.tail;
        }

        return array;
    }

    public static Temp.TempList ArrayToTempList(Temp.Temp array[]) {

        Temp.TempList tempList = null;

        for (int i = array.length-1; i >= 0; --i) {
            tempList = new Temp.TempList(array[i], tempList);
        }

        return tempList;
    }

    public static List<Stm> StmListToArray (StmList ht) {
        ArrayList<Stm> r = new ArrayList<Stm>();

        StmList h = ht;
        while (h != null) {
            r.add(h.head);
            h = h.tail;
        }

        return r;
    }

    public static List<Instr> InstrListToArray(InstrList ht) {
        ArrayList<Instr> r = new ArrayList<Instr>();

        InstrList h = ht;
        while (h != null) {
            r.add(h.head);
            h = h.tail;
        }

        return r;
    }
}