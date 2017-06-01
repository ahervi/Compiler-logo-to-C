package ast;

/**
 * Created by noye on 05/05/2017.
 */
public enum OP {
    PLUS, MINUS, TIMES, DIVIDE, LESS, MORE, LESSEQUAL, MOREEQUAL, EQUAL, NOTEQUAL;
    public static OP parse(String op){
        switch (op){
            case("+"): return PLUS;
            case("-"): return MINUS;
            case("*"): return TIMES;
            case("/"): return DIVIDE;
            case("<"): return LESS;
            case(">"): return MORE;
            case("<="): return LESSEQUAL;
            case(">="): return MOREEQUAL;
            case("="): return EQUAL;
            case("<>"): return NOTEQUAL;
            default:
                throw new RuntimeException("Unexpected Exception");
        }
    }
    public String gen() {
        switch (this.toString()){
            case("PLUS"): return "+";
            case("MINUS"): return "-";
            case("TIMES"): return "*";
            case("DIVIDE"): return "/";
            case("LESS"): return "<";
            case("MORE"): return ">";
            case("LESSEQUAL"): return "<=";
            case("MOREEQUAL"): return ">=";
            case("EQUAL"): return "==";
            case("NOTEQUAL"): return "!=";
            default:
                return"WEIRDOP";
        }
    }
}
