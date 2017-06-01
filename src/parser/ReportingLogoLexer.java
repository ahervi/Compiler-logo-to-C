package parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;

/**
 * Created by noye on 09/05/2017.
 */
public class ReportingLogoLexer extends LogoLexer {
    public ReportingLogoLexer(CharStream input) {
        super(input);
    }
    public void recover(LexerNoViableAltException e) {
        ErrorFlag.setFlag(); // report error
        super.recover(e);
    }
}
