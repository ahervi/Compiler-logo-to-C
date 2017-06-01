package logo;

import ast.AST;
import ast.Prog;
import ast.Type;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import parser.*;
import state.State;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by noye on 09/05/2017.
 */

public class Logo {
    static boolean verbose = false;

    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        if (args.length>1 && args[1].equals("-v")) verbose = true;
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        // using Reporting lexer
        LogoLexer lexer = new ReportingLogoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogoParser parser = new LogoParser(tokens);
        // record error occurrence in parser
        parser.removeErrorListeners();
        parser.addErrorListener(new ErrorListener());
        ParseTree tree = parser.prog();
        if (! ErrorFlag.getFlag()) {
            if (verbose) System.out.println(tree.toStringTree(parser));
            ParseTreeWalker walker = new ParseTreeWalker();
            ASTEmitter emitter = new ASTEmitter();
            walker.walk(emitter, tree);
            AST ast = emitter.getAST(tree);
            ((Prog)ast).filename = inputFile;
            if (verbose) System.out.println(ast);
            ast.check1(new State<ArrayList<Type>>());
            ast.check2(true, new State<Type>(), new State<Type>());
            ast.check3(true, new State<ArrayList<Type>>(),new State<Type>(), new State<Type>());

            String code = ast.gen();

            if (inputFile != null)
                write(code, inputFile);
            else
                System.out.println(code);
        } else
            throw new SyntaxError();
    }
    // write code to .c file associated to .calc file passed as argument,
    // returning .c file relative filename
    static String write(String code, String filename) throws IOException {
        String CFilename = filename.replaceFirst("\\.logo\\z", ".c");
        if (verbose) System.out.println("writing C code to " + CFilename);
        FileWriter out = new FileWriter(CFilename);
        out.write(code);
        out.flush();
        out.close();
        return CFilename;
    }
}

