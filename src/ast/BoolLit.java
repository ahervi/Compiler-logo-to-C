package ast;

import errors.AssignError;
import errors.FormalsDuplicateError;
import errors.ProcedureAlreadyDefinedError;
import state.State;

import java.util.ArrayList;

/**
 * Created by noye on 05/05/2017.
 */
public class BoolLit extends Expr {
    public boolean value;

    public BoolLit(boolean value)
    {
        this.value = value;
        this.type = Type.BOOL;
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError {

    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) {

    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError {

    }

    @Override
    public String gen() {
        return value+"";
    }
}
