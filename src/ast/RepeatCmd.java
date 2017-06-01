package ast;

import errors.*;
import state.State;

import java.util.ArrayList;

/**
 * Created by noye on 09/05/2017.
 */
public class RepeatCmd extends Cmd {
    public Expr expr;
    public Block block;

    public RepeatCmd(Expr expr, Block block) {
        this.expr = expr;
        this.block = block;
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError {
        expr.check1(procedures);
        block.check1(procedures);
    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError, AssignError {
        expr.check2(global, globals, locals);
        block.check2(global, globals, locals);
    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeError, TypeModifiedError, UndefinedVariableError {
        expr.check3(global, procedures, globals, locals);
        block.check3(global, procedures, globals, locals);
    }

    @Override
    public String gen() throws CompilationError {
        StringBuilder res = new StringBuilder();
        res.append("for (int i = 0; i < (int) " + this.expr.gen() + "; i++) {"+"\n"+ this.block.gen()+"}");
        return res.toString();
    }
}
