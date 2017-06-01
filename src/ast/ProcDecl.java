package ast;

import errors.*;

import state.State;

import java.util.*;

/**
 * Created by noye on 05/05/2017.
 */
public class ProcDecl extends AST {
    public String name;
    public List<String> formals;

    public List<Cmd> cmds;
    public State<Type> locals;
    public ProcDecl(String name, List<String> formals, List<Cmd> cmds) {
        this.name = name;
        this.formals = formals;
        this.cmds = cmds;
        this.locals = new State<Type>();

    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, FuncCallLookupError, UndefinedProcError, NumberOfArgumentsError {
        ArrayList<String> liste = new ArrayList<String>();
        for (String formal : formals) {
            liste.add(formal);
        }
        Set<String> set = new HashSet<String>(liste);
        if (set.size() < formals.size()) {
            throw new FormalsDuplicateError("Deux fois le même nom pour des formels de la procédures" + name);
        } else {
            for (Cmd cmd : cmds) {
                cmd.check1(procedures);
            }
        }
    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError, AssignError {
        for(String formal : formals) {
            this.locals.bind(formal, Type.UNKNOWN);
        }
        for (Cmd cmd : cmds) {
            cmd.check2(false, globals, this.locals);
        }



    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeError, TypeModifiedError, UndefinedVariableError {
        int i = 0;

        for(String formal : formals) {
            this.locals.replace(formal, procedures.lookup(name).get(i + 1));
            i++;
        }


        for (Cmd cmd : cmds) {
            cmd.check3(false, procedures, globals, this.locals);
        }

    }

    @Override
    public String gen() throws CompilationError {

        StringBuilder res = new StringBuilder();
        res.append("void " + name);
        res.append(" (");

        if (formals.size() != 0) {

            for (String formal : this.formals) {

                if (this.locals.lookup(formal) != Type.UNKNOWN) {

                    res.append(this.locals.lookup(formal).toString(formal)+", ");
                }
                else {
                  throw new CompilationError();

                }
            }

            res.deleteCharAt(res.length() - 1);
            res.deleteCharAt(res.length() - 1);
        } else {

            res.append("void");
        }

        res.append(")");
        res.append(" {\n");

        for (String local : this.locals.keySet()) {
            if(!formals.contains(local))
                res.append(this.locals.lookup(local).toString(local)+";\n");
        }

        for (Cmd cmd : cmds) {
            res.append(cmd.gen());
        }
        res.append("}\n");
        return res.toString();


    }
}
