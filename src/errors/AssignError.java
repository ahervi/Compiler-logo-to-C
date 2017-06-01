package errors;

import ast.Assign;

/**
 * Created by Antoine on 18/05/2017.
 */
public class AssignError extends Exception {
    public AssignError(String message) {
        super(message);
    }
}
