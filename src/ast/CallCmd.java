package ast;

import java.util.List;

/**
 * Created by noye on 05/05/2017.
 */
abstract public class CallCmd extends Cmd {
    public String name;
    public List<Expr> exprs;
}
