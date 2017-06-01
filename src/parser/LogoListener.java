// Generated from C:/Users/Antoine/IdeaProjects/CompilateurLOGO/src/parser\Logo.g4 by ANTLR 4.6
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogoParser}.
 */
public interface LogoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogoParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(LogoParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(LogoParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoParser#procDecl}.
	 * @param ctx the parse tree
	 */
	void enterProcDecl(LogoParser.ProcDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoParser#procDecl}.
	 * @param ctx the parse tree
	 */
	void exitProcDecl(LogoParser.ProcDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoParser#formals}.
	 * @param ctx the parse tree
	 */
	void enterFormals(LogoParser.FormalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoParser#formals}.
	 * @param ctx the parse tree
	 */
	void exitFormals(LogoParser.FormalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(LogoParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(LogoParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RepeatCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterRepeatCmd(LogoParser.RepeatCmdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RepeatCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitRepeatCmd(LogoParser.RepeatCmdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterIfCmd(LogoParser.IfCmdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitIfCmd(LogoParser.IfCmdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfElseCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterIfElseCmd(LogoParser.IfElseCmdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfElseCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitIfElseCmd(LogoParser.IfElseCmdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterAssign(LogoParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitAssign(LogoParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCallCmd(LogoParser.CallCmdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCallCmd(LogoParser.CallCmdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code YCor}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterYCor(LogoParser.YCorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code YCor}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitYCor(LogoParser.YCorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncCall1}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall1(LogoParser.FuncCall1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncCall1}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall1(LogoParser.FuncCall1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code Symbol}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(LogoParser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Symbol}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(LogoParser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParExpr}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(LogoParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParExpr}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(LogoParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XCor}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterXCor(LogoParser.XCorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XCor}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitXCor(LogoParser.XCorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ListLit}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterListLit(LogoParser.ListLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ListLit}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitListLit(LogoParser.ListLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinExpr}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinExpr(LogoParser.BinExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinExpr}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinExpr(LogoParser.BinExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumLit}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumLit(LogoParser.NumLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumLit}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumLit(LogoParser.NumLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolLit}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolLit(LogoParser.BoolLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolLit}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolLit(LogoParser.BoolLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Deref}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDeref(LogoParser.DerefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Deref}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDeref(LogoParser.DerefContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(LogoParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(LogoParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimCall0}
	 * labeled alternative in {@link LogoParser#call}.
	 * @param ctx the parse tree
	 */
	void enterPrimCall0(LogoParser.PrimCall0Context ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimCall0}
	 * labeled alternative in {@link LogoParser#call}.
	 * @param ctx the parse tree
	 */
	void exitPrimCall0(LogoParser.PrimCall0Context ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimCall1}
	 * labeled alternative in {@link LogoParser#call}.
	 * @param ctx the parse tree
	 */
	void enterPrimCall1(LogoParser.PrimCall1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimCall1}
	 * labeled alternative in {@link LogoParser#call}.
	 * @param ctx the parse tree
	 */
	void exitPrimCall1(LogoParser.PrimCall1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code UserCall}
	 * labeled alternative in {@link LogoParser#call}.
	 * @param ctx the parse tree
	 */
	void enterUserCall(LogoParser.UserCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UserCall}
	 * labeled alternative in {@link LogoParser#call}.
	 * @param ctx the parse tree
	 */
	void exitUserCall(LogoParser.UserCallContext ctx);
}