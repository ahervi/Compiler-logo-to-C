package ast;

import errors.*;
import org.omg.CORBA.UNKNOWN;
import state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noye on 10/05/2017.
 */
public class FuncCall extends Expr {
    public String name;
    public List<Expr> exprs;

    public FuncCall(String name, List<Expr> exprs) {
        this.name = name;
        this.exprs = exprs;


    }

    public ArrayList<Type> lookup() throws FuncCallLookupError {
        ArrayList<Type> res = new ArrayList<Type>();
        switch (name) {
            case ("random"):
                res.add(Type.NUM);
                res.add(Type.NUM);

                return res;
            case ("pick"):
                res.add(Type.STR);
                res.add(Type.LIST);
                return res;
            default:
                throw new FuncCallLookupError("La procédure "+ name +" n'est pas définie en dur dans la classe FuncCall.");

        }
    }

    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError {
        if (name.equals("pick") && !procedures.containsKey("pick")) {
            procedures.bind("pick", lookup());
        }
        if (name.equals("random") && !procedures.containsKey("random")) {
            procedures.bind("random", lookup());

        }
        if(!procedures.containsKey(name)) {
            throw new UndefinedProcError("Call to an undefined procedure : "+name);
        }
        else {
            if (exprs.size() != procedures.lookup(name).size() - 1) {
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
        switch (name) {
            case ("random"):
                this.type = Type.NUM;

                break;
            case ("pick"):
                this.type = Type.STR;
                break;

            default:
                int i = 0;
                for (Expr expr : exprs) {
                    expr.check3(global, procedures, globals, locals);

                    if (!expr.type.equals(procedures.lookup(name).get(i + 1)) && !procedures.lookup(name).get(i + 1).equals(Type.UNKNOWN)) {
                        throw new TypeModifiedError("Appel de méthode avec un mauvais type sur la procédure "+ name + " a tenté d'être modifié de "+procedures.lookup(name).get(i) + " à  " + expr.type.toString() );
                    }
                    procedures.lookup(name).set(i + 1, expr.type);
                    i++;
                }
        }



    }


    @Override
    public String gen() throws CompilationError {
        switch(name){
            case ("pick"):

                return "pick(" + exprs.get(0).gen() + ")";

            case("random"):

               return "random((int)" +  this.exprs.get(0).gen() + ")";
            default:
                StringBuilder res = new StringBuilder();
                res.append(this.name +"(");
                for (Expr expr: this.exprs) {
                    res.append(expr.gen()+", ");
                }
                res.deleteCharAt(res.length()-1);
                res.deleteCharAt(res.length()-1);
                res.append(")");
                return res.toString();
        }

    }
}
