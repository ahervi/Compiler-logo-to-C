package ast;

import errors.*;
import state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noye on 05/05/2017.
 */
public class ListLit extends Expr {
    public List<String> elements;

    public ListLit(List<String> elements) {
        this.elements = elements;
        this.type = Type.LIST;
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError {

    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError {

    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError {

    }

    @Override
    public String gen() {
        StringBuilder res = new StringBuilder();
        res.append("&(struct list){");
        res.append(elements.size()+", (char*[]){");
        for(String element : elements) {
            res.append("\"" + element +"\"" + ", ");
        }
        res.deleteCharAt(res.length()-1);
        res.deleteCharAt(res.length()-1);
        res.append("}}");
        return res.toString();
    }
}
