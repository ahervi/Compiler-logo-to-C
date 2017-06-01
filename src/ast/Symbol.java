package ast;

import errors.*;
import state.State;

import java.util.ArrayList;

/**
 * Created by noye on 05/05/2017.
 */
public class Symbol extends Expr {
    public String name;

    public Symbol(String name) {
        this.name = name;
        this.type = Type.STR;
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

        return "\""+name+"\"";
    }
}
