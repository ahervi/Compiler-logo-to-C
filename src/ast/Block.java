package ast;

import errors.*;
import state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noye on 05/05/2017.
 */
public class Block extends AST {
    public List<Cmd> cmds;

    public Block(List<Cmd> cmds) {
        this.cmds = cmds;
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError {
        for(Cmd cmd : cmds) {
            cmd.check1(procedures);
        }
    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError, AssignError {
        for(Cmd cmd : cmds) {
            cmd.check2(global, globals, locals);
        }
    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeError, TypeModifiedError, UndefinedVariableError {
        for (Cmd cmd : cmds) {

            cmd.check3(global, procedures, globals, locals);
        }
    }

    @Override
    public String gen() throws CompilationError {
        StringBuilder res = new StringBuilder();
        for (Cmd cmd : this.cmds) {
            StringBuilder append = res.append("\t" + cmd.gen() + "\n");
        }
        return res.toString();
    }
}
