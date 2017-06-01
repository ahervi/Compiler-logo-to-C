package ast;

import errors.*;
import state.State;

import java.util.ArrayList;

/**
 * Created by Antoine on 24/05/2017.
 */
public class XCor extends Expr {

    public XCor () {
        this.type = Type.NUM;
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError {

    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError, AssignError {
        if (!globals.containsKey("xcor")) {
            globals.bind("xcor", this.type);
        }
    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeError, TypeModifiedError, UndefinedVariableError {
        if (!globals.containsKey("xcor")) {
            throw new UndefinedVariableError("xcor" + " aurait du être détécté à l'analyse des variables !");
        } else {
            if (!globals.lookup("xcor").equals(Type.NUM)) {
                throw new TypeError("xcor" + " est de type NUM et pas " + globals.lookup("xcor"));
            }
        }
    }

    @Override
    public String gen() throws CompilationError {
        return "xcor";
    }
}
