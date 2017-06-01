package ast;

import errors.AssignError;
import errors.FormalsDuplicateError;
import errors.ProcedureAlreadyDefinedError;
import errors.UndefinedVariableError;
import state.State;

import java.util.ArrayList;

/**
 * Created by noye on 05/05/2017.
 */
public class Deref extends Expr {
    public String name;

    public Deref(String name) {
        this.name = name;
        this.type = Type.UNKNOWN;
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError {

    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError {
        if(!globals.containsKey(name) && !locals.containsKey(name)) {
            throw new UndefinedVariableError("la variable "+ name + " a été utilisé sans être définie.");
        }
    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, UndefinedVariableError {
        if(locals.containsKey(name)) {
            this.type = locals.get(name);
        } else if(globals.containsKey(name)) {
            this.type = globals.get(name);
        } else {
            throw new UndefinedVariableError("La variable "+ name + " n'est pas définie");
        }

    }

    @Override
    public String gen() {
        return name;
    }
}
