package ast;

import errors.*;
import state.State;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by noye on 05/05/2017.
 */
public abstract class AST {
    public String toString() {
        // retrieve class of node
        Class<? extends AST> tclass = this.getClass();
        // isolate relative name (starting after the rightmost '.')
        String absoluteClassName = tclass.toString();
        int dotIndex = absoluteClassName.lastIndexOf(".", absoluteClassName.length());
        String relativeClassName = absoluteClassName.substring(dotIndex+1);
        // retrieving fields (note that, unfortunately, they are not ordered)
        Field[] fields = tclass.getFields();
        // building string representation of the arguments of the nodes
        int arity = fields.length;
        String args = "";
        for(int index = 0; index < arity; index++) {
            String arg;
            try {
                arg = fields[index].get(this).toString(); // retrieve string representation of all arguments
            } catch (Exception e) {
                arg = "?"; // IllegalArgument or IllegalAccess Exception (this shouldn't happen)
            }
            if (index != 0) // not first argument, a separator is required
                args = args + ", " + arg;
            else
                args = args + arg;
        }
        return relativeClassName + "(" + args + ")";
    }
    abstract public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError;
    abstract public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError, AssignError;
    abstract public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeError, TypeModifiedError, UndefinedVariableError;
    abstract public String gen() throws CompilationError;
}

