import gen.JythonListener;
import gen.JythonParser;
import items.Class;
import items.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class SymbolTableGenerator implements JythonListener {

    private final Scope globalScope = new Scope();
    private Class currentClass;
    private Import currentImport;
    private Method currentMethod;
    private Block currentBlock;

    private final String finalResult = "";

    @Override
    public void enterProgram(JythonParser.ProgramContext ctx) {
        globalScope.name = "program";
        globalScope.scopeNumber = ctx.start.getLine();

    }

    @Override
    public void exitProgram(JythonParser.ProgramContext ctx) {
        globalScope.printScope();

    }

    @Override
    public void enterImportclass(JythonParser.ImportclassContext ctx) {
        currentImport = new Import(ctx.CLASSNAME().getText());
        globalScope.insert("import_" + ctx.CLASSNAME().getText(), currentImport);


    }

    @Override
    public void exitImportclass(JythonParser.ImportclassContext ctx) {
        currentImport=null;
    }

    @Override
    public void enterClassDef(JythonParser.ClassDefContext ctx) {
        currentClass = new Class(ctx.className.getText(), ctx.classParent.getText() + "," + ctx.classParent2.getText());
        currentClass.scopeNumber = ctx.start.getLine();
        var id = "class_" + ctx.className.getText();
        globalScope.insert(id, currentClass);

    }

    @Override
    public void exitClassDef(JythonParser.ClassDefContext ctx) {
        currentClass.printScope();
        currentClass=null;
    }

    @Override
    public void enterClass_body(JythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void exitClass_body(JythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void enterVarDec(JythonParser.VarDecContext ctx) {
//            var field = new Field()
    }

    @Override
    public void exitVarDec(JythonParser.VarDecContext ctx) {

    }

    @Override
    public void enterArrayDec(JythonParser.ArrayDecContext ctx) {

    }

    @Override
    public void exitArrayDec(JythonParser.ArrayDecContext ctx) {

    }

    @Override
    public void enterMethodDec(JythonParser.MethodDecContext ctx) {

    }

    @Override
    public void exitMethodDec(JythonParser.MethodDecContext ctx) {

    }

    @Override
    public void enterConstructor(JythonParser.ConstructorContext ctx) {

    }

    @Override
    public void exitConstructor(JythonParser.ConstructorContext ctx) {

    }

    @Override
    public void enterParameter(JythonParser.ParameterContext ctx) {

    }

    @Override
    public void exitParameter(JythonParser.ParameterContext ctx) {

    }

    @Override
    public void enterStatement(JythonParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(JythonParser.StatementContext ctx) {

    }

    @Override
    public void enterReturn_statment(JythonParser.Return_statmentContext ctx) {

    }

    @Override
    public void exitReturn_statment(JythonParser.Return_statmentContext ctx) {

    }

    @Override
    public void enterCondition_list(JythonParser.Condition_listContext ctx) {

    }

    @Override
    public void exitCondition_list(JythonParser.Condition_listContext ctx) {

    }

    @Override
    public void enterCondition(JythonParser.ConditionContext ctx) {

    }

    @Override
    public void exitCondition(JythonParser.ConditionContext ctx) {

    }

    @Override
    public void enterIf_statment(JythonParser.If_statmentContext ctx) {

    }

    @Override
    public void exitIf_statment(JythonParser.If_statmentContext ctx) {

    }

    @Override
    public void enterWhile_statment(JythonParser.While_statmentContext ctx) {

    }

    @Override
    public void exitWhile_statment(JythonParser.While_statmentContext ctx) {

    }

    @Override
    public void enterIf_else_statment(JythonParser.If_else_statmentContext ctx) {

    }

    @Override
    public void exitIf_else_statment(JythonParser.If_else_statmentContext ctx) {

    }

    @Override
    public void enterPrint_statment(JythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(JythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(JythonParser.For_statmentContext ctx) {

    }

    @Override
    public void exitFor_statment(JythonParser.For_statmentContext ctx) {

    }

    @Override
    public void enterMethod_call(JythonParser.Method_callContext ctx) {

    }

    @Override
    public void exitMethod_call(JythonParser.Method_callContext ctx) {

    }

    @Override
    public void enterAssignment(JythonParser.AssignmentContext ctx) {

    }

    @Override
    public void exitAssignment(JythonParser.AssignmentContext ctx) {

    }

    @Override
    public void enterExp(JythonParser.ExpContext ctx) {

    }

    @Override
    public void exitExp(JythonParser.ExpContext ctx) {

    }

    @Override
    public void enterPrefixexp(JythonParser.PrefixexpContext ctx) {

    }

    @Override
    public void exitPrefixexp(JythonParser.PrefixexpContext ctx) {

    }

    @Override
    public void enterArgs(JythonParser.ArgsContext ctx) {

    }

    @Override
    public void exitArgs(JythonParser.ArgsContext ctx) {

    }

    @Override
    public void enterExplist(JythonParser.ExplistContext ctx) {

    }

    @Override
    public void exitExplist(JythonParser.ExplistContext ctx) {

    }

    @Override
    public void enterArithmetic_operator(JythonParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void exitArithmetic_operator(JythonParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void enterRelational_operators(JythonParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void exitRelational_operators(JythonParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void enterAssignment_operators(JythonParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void exitAssignment_operators(JythonParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
