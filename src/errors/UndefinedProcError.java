package errors;

import ast.Expr;

/**
 * Created by Antoine on 18/05/2017.
 */
public class UndefinedProcError extends Exception {
    public UndefinedProcError(String message) {
        super(message);
    }
}
