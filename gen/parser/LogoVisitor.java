// Generated from C:/Users/Antoine/IdeaProjects/CompilateurLOGO/src/parser\Logo.g4 by ANTLR 4.6
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LogoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LogoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LogoParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(LogoParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoParser#procDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcDecl(LogoParser.ProcDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoParser#formals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormals(LogoParser.FormalsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(LogoParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RepeatCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatCmd(LogoParser.RepeatCmdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfCmd(LogoParser.IfCmdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfElseCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseCmd(LogoParser.IfElseCmdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(LogoParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallCmd}
	 * labeled alternative in {@link LogoParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallCmd(LogoParser.CallCmdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncCall1}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall1(LogoParser.FuncCall1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code Symbol}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbol(LogoParser.SymbolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParExpr}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpr(LogoParser.ParExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ListLit}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLit(LogoParser.ListLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinExpr}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinExpr(LogoParser.BinExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumLit}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumLit(LogoParser.NumLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolLit}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLit(LogoParser.BoolLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Deref}
	 * labeled alternative in {@link LogoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeref(LogoParser.DerefContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(LogoParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimCall0}
	 * labeled alternative in {@link LogoParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimCall0(LogoParser.PrimCall0Context ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimCall1}
	 * labeled alternative in {@link LogoParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimCall1(LogoParser.PrimCall1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code UserCall}
	 * labeled alternative in {@link LogoParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserCall(LogoParser.UserCallContext ctx);
}