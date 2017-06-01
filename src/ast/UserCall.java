package ast;

import errors.*;
import state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by noye on 09/05/2017.
 */
public class UserCall extends CallCmd {
    public UserCall(String name, List<Expr> exprs) {
        this.name = name;
        this.exprs = exprs;





    }

    public ArrayList<Type> lookup() throws FuncCallLookupError {
        ArrayList<Type> res = new ArrayList<Type>();
        switch (name) {

            case ("setxy"):
                res.add(Type.VOID);
                res.add(Type.NUM);
                res.add(Type.NUM);
                return res;


            default:
                throw new FuncCallLookupError("La procédure "+ name +" n'est pas définie en dur dans la classe FuncCall.");

        }
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError {
        if (name.equals("setxy") && !procedures.containsKey("setxy")) {
            procedures.bind("setxy", lookup());
        }
        if(!procedures.containsKey(name)) {
            throw new UndefinedProcError("Call to an undefined procedure : "+name);
        } else {

            if ((!procedures.lookup(name).get(1).equals(Type.VOID) && exprs.size() != procedures.lookup(name).size() - 1) || (procedures.lookup(name).get(1).equals(Type.VOID) && exprs.size() != procedures.lookup(name).size() - 2)) {
                throw new NumberOfArgumentsError("La procédure " + name + " n'a pas le bon nombre d'arguments.");
            } else {
                for (Expr exp : exprs) {
                    exp.check1(procedures);
                }
            }
        }
    }

    @Override
    public void check2(boolean global, State<Type> globals, State<Type> locals) throws UndefinedVariableError, AssignError {
        for (Expr exp : exprs) {
            exp.check2(global, globals, locals);
        }
    }

    @Override
    public void check3(boolean global, State<ArrayList<Type>> procedures, State<Type> globals, State<Type> locals) throws AssignError, TypeError, TypeModifiedError, UndefinedVariableError {
        int i = 0;

        for (Expr expr : exprs) {

            expr.check3(global, procedures, globals, locals);


            if (!expr.type.equals(procedures.lookup(name).get(i + 1)) && !procedures.lookup(name).get(i + 1).equals(Type.UNKNOWN)) {
                throw new TypeModifiedError("Appel de méthode avec un mauvais type sur la procédure "+ name + " " + expr.type.toString() + " à la place de " + procedures.lookup(name).get(i + 1) );
            }
            procedures.lookup(name).set(i + 1, expr.type);
            i++;
        }

    }


    @Override
    public String gen() throws CompilationError {
        StringBuilder res = new StringBuilder();

        switch (name) {
            case("setxy"):
                return "xcor = "+exprs.get(0).gen()+";\n"+"ycor = "+exprs.get(1).gen()+";\n";

            default:
            res.append(name + "(");
            for (Expr exp : exprs) {
                res.append(exp.gen() + ", ");
            }
            if (exprs.size() != 0) {
                res.deleteCharAt(res.length() - 1);
                res.deleteCharAt(res.length() - 1);
            }
            res.append(");\n");
            return res.toString();
        }
    }
}
