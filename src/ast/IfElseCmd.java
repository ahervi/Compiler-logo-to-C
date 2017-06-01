package ast;

import errors.*;
import state.State;

import java.util.ArrayList;

/**
 * Created by noye on 09/05/2017.
 */
public class IfElseCmd extends Cmd {
    public Expr expr;
    public Block tBlock;
    public Block fBlock;

    public IfElseCmd(Expr expr, Block tBlock, Block fBlock) {
        this.expr = expr;
        this.tBlock = tBlock;
        this.fBlock = fBlock;
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError {
        expr.check1(procedures);
        tBlock.check1(procedures);
        fBlock.check1(procedures);
    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError, AssignError {
        expr.check2(global, globals, locals);
        tBlock.check2(global, globals, locals);
        fBlock.check2(global, globals, locals);
    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeError, TypeModifiedError, UndefinedVariableError {

        expr.check3(global, procedures, globals, locals);

        if (!expr.type.equals(Type.BOOL)) {
            throw new TypeError("Une condition ifelse n'est pas dee type BOOL mais " + expr.type);
        }

        tBlock.check3(global, procedures, globals, locals);

        fBlock.check3(global, procedures, globals, locals);

    }

    @Override
    public String gen() throws CompilationError {
        StringBuilder res = new StringBuilder();
        res.append("if (" + expr.gen() + ") {\n"+ tBlock.gen() + "\n}\n");
        res.append("else { "+ fBlock.gen() + "\n}\n");


        return res.toString();
    }
}
