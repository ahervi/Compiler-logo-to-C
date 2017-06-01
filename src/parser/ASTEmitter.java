package parser;

import ast.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by noye on 09/05/2017.
 */
public class ASTEmitter extends LogoBaseListener {
    ParseTreeProperty<AST> ast = new ParseTreeProperty<AST>();
    public AST getAST(ParseTree ctx) { return ast.get(ctx); }
    void setAST(ParseTree ctx, AST node) { ast.put(ctx, node); }

    @Override
    public void exitProg(LogoParser.ProgContext ctx) {
        List<ProcDecl> procDecls = new LinkedList<>();
        List<Cmd> cmds = new LinkedList<>();

        for (LogoParser.ProcDeclContext procDeclCtx:  ctx.procDecl())
            procDecls.add((ProcDecl)getAST(procDeclCtx));
        for (LogoParser.CmdContext cmdCtx:  ctx.cmd())
            cmds.add((Cmd)getAST(cmdCtx));

        setAST(ctx, new Prog(procDecls, cmds));
    }

    @Override
    public void exitXCor(LogoParser.XCorContext ctx) {
        setAST(ctx, new XCor());
    }

    public void exitYCor(LogoParser.YCorContext ctx) {
        setAST(ctx, new YCor());

    }

    @Override public void exitProcDecl(LogoParser.ProcDeclContext ctx) {
        String name = ctx.NAME().getText();
        List<String> formals = new LinkedList<>();
        for (TerminalNode node: ctx.formals().NAME())
            formals.add(node.getText());
        List<Cmd> cmds = new LinkedList<>();
        for (LogoParser.CmdContext cmdCtx: ctx.body().cmd())
            cmds.add((Cmd)getAST(cmdCtx));

        setAST(ctx, new ProcDecl(name, formals, cmds));
    }
    // commands
    @Override
    public void exitRepeatCmd(LogoParser.RepeatCmdContext ctx) {
        Expr expr = (Expr) getAST(ctx.expr());
        Block block = (Block) getAST(ctx.block());

        setAST(ctx, new RepeatCmd(expr, block));
    }
    @Override public void exitIfCmd(LogoParser.IfCmdContext ctx) {
        Expr expr = (Expr) getAST(ctx.expr());
        Block block = (Block) getAST(ctx.block());

        setAST(ctx, new IfCmd(expr, block));
    }
    @Override public void exitIfElseCmd(LogoParser.IfElseCmdContext ctx) {
        Expr expr = (Expr) getAST(ctx.expr());
        Block block1 = (Block) getAST(ctx.block(0));
        Block block2 = (Block) getAST(ctx.block(1));

        setAST(ctx, new IfElseCmd(expr, block1, block2));
    }
    @Override
    public void exitCallCmd(LogoParser.CallCmdContext ctx) {
        setAST(ctx, getAST(ctx.getChild(0)));
    }
    @Override
    public void exitAssign(LogoParser.AssignContext ctx) {
        String name = ctx.NAME().getText();
        Expr expr = (Expr)getAST(ctx.expr());

        setAST(ctx, new Assign(name, expr));
    }
    // calls
    @Override
    public void exitPrimCall0(LogoParser.PrimCall0Context ctx) {
        String name = ctx.PRIMPROC0().getText();
        List<Expr> exprs = new LinkedList<>();
        setAST(ctx, new PrimCall(name, exprs));
    }
    @Override
    public void exitPrimCall1(LogoParser.PrimCall1Context ctx) {
        String name = ctx.PRIMPROC1().getText();
        List<Expr> exprs = new LinkedList<>();
        exprs.add((Expr)getAST(ctx.expr()));
        setAST(ctx, new PrimCall(name, exprs));
    }
    @Override public void exitUserCall(LogoParser.UserCallContext ctx) {
        String name = ctx.NAME().getText();
        List<Expr> exprs = new LinkedList<>();
        for (LogoParser.ExprContext exprCtx: ctx.expr())
            exprs.add((Expr)getAST(exprCtx));

        setAST(ctx, new UserCall(name, exprs));
    }
    // block
    @Override public void exitBlock(LogoParser.BlockContext ctx) {
        List<Cmd> cmds = new LinkedList<>();
        for (LogoParser.CmdContext cmdCtx: ctx.cmd())
            cmds.add((Cmd)getAST(cmdCtx));

        setAST(ctx, new Block(cmds));
    }
    // expressions
    @Override
    public void exitFuncCall1(LogoParser.FuncCall1Context ctx) {
        String name = ctx.PRIMFUNC1().getText();
        List<Expr> exprs = new LinkedList<>();
        exprs.add((Expr)getAST(ctx.expr()));

        setAST(ctx, new FuncCall(name, exprs));
    }
    @Override
    public void exitBinExpr(LogoParser.BinExprContext ctx) {
        Expr expr1 = (Expr)getAST(ctx.expr(0));
        Expr expr2 = (Expr)getAST(ctx.expr(1));
        OP op = OP.parse(ctx.getChild(1).getText());

        setAST(ctx, new BinExpr(op, expr1, expr2));
    }
    @Override
    public void exitParExpr(LogoParser.ParExprContext ctx) {
        setAST(ctx, getAST(ctx.expr()));
    }

    @Override
    public void exitNumLit(LogoParser.NumLitContext ctx) {
        float value = Float.parseFloat(ctx.getText());
        setAST(ctx, new NumLit(value));
    }
    @Override
    public void exitBoolLit(LogoParser.BoolLitContext ctx) {
        boolean value = Boolean.parseBoolean(ctx.getText());
        setAST(ctx, new BoolLit(value));
    }
    @Override
    public void exitListLit(LogoParser.ListLitContext ctx) {
        List<String> names = new LinkedList<>();
        for (TerminalNode node: ctx.NAME())
            names.add(node.getText());
        setAST(ctx, new ListLit(names));
    }


    @Override
    public void exitSymbol(LogoParser.SymbolContext ctx) {
        setAST(ctx, new Symbol(ctx.NAME().getText()));
    }
    @Override
    public void exitDeref(LogoParser.DerefContext ctx) {
        String name = ctx.NAME().getText();
        setAST(ctx, new Deref(name));
    }
}
