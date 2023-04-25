package symbol;

import utils.Pair;
import utils.PrintUtil;

import java.util.ArrayList;

public class MethodTable extends Field {

    private ArrayList<Pair<Symbol, String>> parametros;
    private ArrayList<Pair<Symbol, String>> vlocais;

    public MethodTable(Pair<Symbol, String> formal) {
        super(formal);
        parametros = new ArrayList<Pair<Symbol, String>>();
        vlocais    = new ArrayList<Pair<Symbol, String>>();

    }

    public boolean addParam(String id, String t) {
        for (int i = 0; i < parametros.size(); ++i) {
            if (parametros.get(i).first.toString().equals(id)) {
                error.complain("Erro ao adicionar parametro" + PrintUtil.typeId(id, t) + "no metodo" + PrintUtil.typeId(getNome(), getTipo()) + ".");
                return false;
            }
        }

        parametros.add(Pair.of(Symbol.symbol(id), t));
        return true;
    }

    public boolean addLocal(String id, String t) {
        for (int i = 0; i < vlocais.size(); ++i) {
            if (vlocais.get(i).first.toString().equals(id)) {
                error.complain("Erro ao adicionar variavel local" + PrintUtil.typeId(id, t) + "no metodo" + PrintUtil.typeId(getNome(), getTipo()) + ".");
                return false;
            }
        }

        vlocais.add(Pair.of(Symbol.symbol(id), t));
        return true;
    }

    public ArrayList<Pair<Symbol, String>> getParametros() {
        return parametros;
    }

    public ArrayList<Pair<Symbol, String>> getVlocais() {
        return vlocais;
    }
}
