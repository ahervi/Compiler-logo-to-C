package ast;

import errors.*;
import state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by noye on 10/05/2017.
 */
public class Assign extends Cmd {
    public String name;
    public Expr expr;
    public Assign(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError {
        expr.check1(procedures);
    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws AssignError {
        if (global && !globals.containsKey(name) && !expr.type.equals(Type.VOID)) {
            globals.bind(name, expr.type);

        } else if (!global && !globals.containsKey(name) && !locals.containsKey(name) && !expr.type.equals(Type.VOID)) {
            locals.bind(name, expr.type);
        }
    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeModifiedError, UndefinedVariableError, TypeError {

        expr.check3(global, procedures, globals, locals);

        if (global && globals.containsKey(name) ) {
            if (!expr.type.equals(globals.lookup(name)) && !expr.type.equals(Type.UNKNOWN)) {
                throw new TypeModifiedError("Le type de la variable "+ name + "a tenté d'être modifié de "+globals.lookup(name).toString() + " à  " + expr.type.toString() );
            }
            globals.replace(name, expr.type);

        } else if (!global && locals.containsKey(name) ) {
            if (!expr.type.equals(locals.lookup(name)) && !expr.type.equals(Type.UNKNOWN)) {
                throw new TypeModifiedError("Le type de la variable "+ name + "a tenté d'être modifié de "+globals.lookup(name).toString() + " à  " + expr.type.toString() );
            }
            locals.replace(name, expr.type);
        } else if (!global && globals.containsKey(name) ) {
            if (!expr.type.equals(globals.lookup(name)) && !expr.type.equals(Type.UNKNOWN)) {
                throw new TypeModifiedError("Le type de la variable "+ name + "a tenté d'être modifié de "+globals.lookup(name).toString() + " à  " + expr.type.toString() );
            }
            globals.replace(name, expr.type);
        } else {
            throw new AssignError("La variable " + name + " n'est pas définie.");
        }
    }

    @Override
    public String gen() throws CompilationError {

            return name+ " = " + expr.gen()+";\n";

        }


    }
