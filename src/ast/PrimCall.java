package ast;

import errors.*;
import org.omg.CORBA.UNKNOWN;
import state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by noye on 09/05/2017.
 */
public class PrimCall extends CallCmd {


    public PrimCall(String name, List<Expr> exprs) {
        this.name = name;
        this.exprs = exprs;
    }

    public ArrayList<Type> lookup() throws FuncCallLookupError {
        ArrayList<Type> res = new ArrayList<Type>();
        switch (name) {
            case ("stop"):
                res.add(Type.VOID);
                res.add(Type.VOID);
                return res;
            case ("show"):
                res.add(Type.VOID);
                res.add(Type.ANY);
                return res;
            case ("fd"):
                res.add(Type.VOID);
                res.add(Type.NUM);
                return res;
            case ("forward"):
                res.add(Type.VOID);
                res.add(Type.NUM);
                return res;
            case ("bk"):
                res.add(Type.VOID);
                res.add(Type.NUM);
                return res;
            case ("back"):
                res.add(Type.VOID);
                res.add(Type.NUM);
                return res;
            case ("left"):
                res.add(Type.VOID);
                res.add(Type.NUM);
                return res;
            case ("lt"):
                res.add(Type.VOID);
                res.add(Type.NUM);
                return res;
            case ("right"):
                res.add(Type.VOID);
                res.add(Type.NUM);
                return res;
            case ("rt"):
                res.add(Type.VOID);
                res.add(Type.NUM);
                return res;
            case ("cs"):
                res.add(Type.VOID);
                res.add(Type.VOID);
                return res;
            case ("clearscreen"):
                res.add(Type.VOID);
                res.add(Type.VOID);
                return res;
            case ("setpencolor"):
                res.add(Type.VOID);
                res.add(Type.STR);
                return res;


            default:
                throw new FuncCallLookupError("La procédure "+ name +" n'est pas définie en dur dans la classe FuncCall.");

        }
    }


    @Override
    public void check1(State<ArrayList<Type>> procedures) throws FormalsDuplicateError, ProcedureAlreadyDefinedError, UndefinedProcError, FuncCallLookupError, NumberOfArgumentsError {

        if (name.equals("stop") && !procedures.containsKey("stop")) {
            procedures.bind("stop", lookup());
        }
        if (name.equals("cs") && !procedures.containsKey("cs")) {
            procedures.bind("cs", lookup());
        }
        if (name.equals("clearscreen") && !procedures.containsKey("clearscreen")) {
            procedures.bind("clearscreen", lookup());
        }

        if (name.equals("fd") && !procedures.containsKey("fd")) {
            procedures.bind("fd", lookup());
        }

        if (name.equals("forward") && !procedures.containsKey("forward")) {
            procedures.bind("forward", lookup());
        }
        if (name.equals("back") && !procedures.containsKey("back")) {
            procedures.bind("back", lookup());
        }
        if (name.equals("bk") && !procedures.containsKey("bk")) {
            procedures.bind("bk", lookup());
        }
        if (name.equals("lt") && !procedures.containsKey("lt")) {
            procedures.bind("lt", lookup());
        }
        if (name.equals("left") && !procedures.containsKey("left")) {
            procedures.bind("left", lookup());
        }
        if (name.equals("rt") && !procedures.containsKey("rt")) {
            procedures.bind("rt", lookup());
        }
        if (name.equals("right") && !procedures.containsKey("right")) {
            procedures.bind("right", lookup());
        }
        if (name.equals("setpencolor") &&!procedures.containsKey("setpencolor")) {
            procedures.bind("setpencolor", lookup());
        }
        if (name.equals("show") &&!procedures.containsKey("show")) {
            procedures.bind("show", lookup());
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
            if (!expr.type.equals(procedures.lookup(name).get(i + 1)) && !procedures.lookup(name).get(i + 1).equals(Type.UNKNOWN) && (!procedures.lookup(name).get(i + 1).equals(Type.ANY))) {
                throw new TypeModifiedError("Appel de méthode avec un mauvais type sur la procédure "+ name + " a tenté d'être modifié de "+procedures.lookup(name).get(i + 1) + " à " + expr.type.toString() );
            }
            if (!procedures.lookup(name).get(i + 1).equals(Type.ANY)) {
                procedures.lookup(name).set(i + 1, expr.type);

            }
            i++;
        }
    }

    @Override
    public String gen() throws CompilationError {
        StringBuilder res = new StringBuilder();
        if(this.exprs.size()==0) {
            switch (name) {
                case ("stop"):
                    return "return;";
            }
        }
        else if (exprs.size() == 1 ) {

            Expr exp = exprs.get(0);

            switch (name) {

                case ("show"):

                    switch (exp.type) {

                        case NUM:
                            res.append("showNUM(" + exp.gen() + ");\n");
                            break;

                        case BOOL:
                            res.append("showBOOL(" + exp.gen() + ");\n");
                            break;

                        case LIST:
                            res.append("showLIST(" + exp.gen() + ");\n");
                            break;

                        case STR:
                            res.append("showSTR("+  exp.gen() + ");\n");
                            break;

                        case UNKNOWN:
                            throw new CompilationError();
                    }

                case ("clearscreen"):
                case ("cs"):
                    res.append("clearScreen();");
                    break;

                case ("forward"):
                case ("fd"):
                    res.append("x = xcor - " + exp.gen() + "*sin(rotation*pi/180);\n" +
                            "y = ycor - " + exp.gen() + "*cos(rotation*pi/180);\n" +
                            "SDL_RenderDrawLine(renderer, xcor, ycor, x, y);\n" +
                            "xcor = x;\n" +
                            "ycor = y;\n");
                    break;

                case ("bk"):
                case ("back"):
                    res.append("x =  xcor + " + exp.gen() + "*sin(rotation*pi/180);\n" +
                            "y = ycor + " + exp.gen() + "*cos(rotation*pi/180);\n" +
                            "SDL_RenderDrawLine(renderer, xcor, ycor, x, y);\n" +
                            "xcor = x;\n" +
                            "ycor = y;\n");
                    break;

                case ("lt"):
                case ("left"):
                    res.append("rotation = rotation + " + exp.gen() + ";\n");
                    break;

                case ("right"):
                case ("rt"):
                    res.append("rotation = rotation - " + exp.gen() + ";\n");
                    break;

                case ("setpencolor"):
                    res.append("setpencolor(" + exp.gen() + ", renderer);");
                    break;

                case ("stop"):
                    res.append("return;");
                    break;

                default:
                    res.append("");
                    break;
            }





        }
        return res.toString();
    }
}