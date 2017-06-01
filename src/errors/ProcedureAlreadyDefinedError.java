package errors;

/**
 * Created by Antoine on 17/05/2017.
 */
public class ProcedureAlreadyDefinedError extends Exception {
    public ProcedureAlreadyDefinedError (String message) {
        super(message);
    }
}
