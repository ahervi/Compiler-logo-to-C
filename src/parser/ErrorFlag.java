package parser;

/**
 * Created by noye on 09/05/2017.
 */
public class ErrorFlag {
    private static Boolean flag = false; // true if there is an error

    public static boolean getFlag() {
        return flag;
    }

    public static void setFlag() {
        flag = true;
    }

    public static void reset() {
        flag = false;
    }
}

