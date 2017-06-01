package ast;

import errors.*;
import state.State;

import java.util.ArrayList;

/**
 * Created by noye on 05/05/2017.
 */
public class BinExpr extends Expr {
    public OP op;
    public Expr expr1;
    public Expr expr2;

    public BinExpr(OP op, Expr expr1, Expr expr2) {
        this.op = op;
        this.expr1 = expr1;
        this.expr2 = expr2;

    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError {
        expr1.check1(procedures);
        expr2.check1(procedures);
    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError, AssignError {
        expr1.check2(global, globals, locals);;
        expr2.check2(global, globals, locals);
    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeError, TypeModifiedError, UndefinedVariableError {
       expr1.check3(global, procedures, globals, locals);
       expr2.check3(global, procedures, globals, locals);

       if(!expr2.type.equals(expr1.type)) {

            throw new TypeError("Different types from both sides of a binary operator :"+ expr1.type.toString()+" et " + expr2.type.toString());
        } else {

           if (!expr1.type.equals(Type.NUM) && (op.equals(OP.DIVIDE) || op.equals(OP.TIMES) || op.equals(OP.PLUS) || op.equals(OP.MINUS))) {
               throw new TypeError("Expression without Numeric type used in arithmetic operation :" + expr1.type.toString() + " et " + expr2.type.toString());
           }

           if(this.op.equals(OP.LESS) || this.op.equals(OP.LESSEQUAL) || this.op.equals(OP.EQUAL) || this.op.equals(OP.NOTEQUAL) || this.op.equals(OP.MORE) || this.op.equals(OP.MOREEQUAL)) {

               this.type = Type.BOOL;
           } else{

               if(this.op.equals(OP.LESS) || this.op.equals(OP.LESSEQUAL) || this.op.equals(OP.EQUAL) || this.op.equals(OP.NOTEQUAL) || this.op.equals(OP.MORE) || this.op.equals(OP.MOREEQUAL)) {
                   this.type = Type.BOOL;
               } else{

                   this.type = expr1.type;
               }           }

        }
    }

    @Override
    public String gen() throws CompilationError {
        if (this.op.equals(OP.LESS) || this.op.equals(OP.LESSEQUAL) || this.op.equals(OP.EQUAL) || this.op.equals(OP.NOTEQUAL) || this.op.equals(OP.MORE) || this.op.equals(OP.MOREEQUAL)) {
            return expr1.gen() + " " + op.gen() + " " + expr2.gen();
        } else {
            return expr1.gen() + " " + op.gen() + " " + expr2.gen();
        }

    }
}
