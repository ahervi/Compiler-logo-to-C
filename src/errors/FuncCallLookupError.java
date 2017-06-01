package errors;

import ast.FuncCall;

/**
 * Created by Antoine on 18/05/2017.
 */
public class FuncCallLookupError extends Exception {
    public FuncCallLookupError(String message) {
        super(message);
    }
}
