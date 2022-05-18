import gen.JythonListener;
import gen.JythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class ProgramPrinter implements JythonListener {

    private int indentLevel = 0;

    private void tab() {
        indentLevel += 4;
    }

    private void untab() {
        indentLevel -= 4;
    }

    private void printSpace() {
        System.out.print(" ".repeat(indentLevel));
    }

    private <t> void newLine(t sth) {
        System.out.println();
        printSpace();
        System.out.print(sth);
    }

    private <t> void sameLine(t sth) {
        System.out.print(sth);
    }

    @Override
    public void enterProgram(JythonParser.ProgramContext ctx) {
        newLine("program start{");
        tab();

    }

    @Override
    public void exitProgram(JythonParser.ProgramContext ctx) {
        untab();
        newLine("}");
    }

    @Override
    public void enterImportclass(JythonParser.ImportclassContext ctx) {
        newLine("import class : " + ctx.CLASSNAME());
    }

    @Override
    public void exitImportclass(JythonParser.ImportclassContext ctx) {

    }

    @Override
    public void enterClassDef(JythonParser.ClassDefContext ctx) {
        newLine("class: " + ctx.className.getText() + "/class parents:");
        List<TerminalNode> parents = ctx.CLASSNAME();
        if (parents.size() < 2) {
            sameLine("object,{");
        } else {
            parents.remove(0);
            for (TerminalNode parent : parents) {
                sameLine(parent.getText() + ",");
            }
            sameLine("{");
        }
        tab();
    }

    @Override
    public void exitClassDef(JythonParser.ClassDefContext ctx) {
        untab();
        newLine("}");
    }

    @Override
    public void enterClass_body(JythonParser.Class_bodyContext ctx) {
//        newLine(ctx.getText());
    }

    @Override
    public void exitClass_body(JythonParser.Class_bodyContext ctx) {
    }

    @Override
    public void enterVarDec(JythonParser.VarDecContext ctx) {
//        newLine(ctx.getText());
        if (ctx.parent.getChildCount() <= 2) {
            newLine("field: " + ctx.varId.getText() + "/");
            if (ctx.varType == null) {
                sameLine("type=" + ctx.varClassName.getText());
            } else if (ctx.varClassName == null) {
                sameLine("type=" + ctx.varType.getText());
            }
        }


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
        if (ctx.methodId.getText() == "main") {
            newLine("main method{");
        } else if (ctx.methodType == null) {
            newLine("class method: " + ctx.methodId.getText() + "/" + " retrun type:" + "void" + "{");
        } else {
            newLine("class method: " + ctx.methodId.getText() + "/" + " retrun type:" + ctx.methodType.getText() + "{");
        }
        tab();
    }

    @Override
    public void exitMethodDec(JythonParser.MethodDecContext ctx) {
        untab();
        newLine("}");

    }

    @Override
    public void enterConstructor(JythonParser.ConstructorContext ctx) {
        String className = "";
        if (ctx.CLASSNAME() != null) {
            className = ctx.CLASSNAME().getText();
        } else if (ctx.TYPE() != null) {
            className = ctx.TYPE().getText();

        }
        newLine("class constructor: " + className + "{");
        tab();

    }

    @Override
    public void exitConstructor(JythonParser.ConstructorContext ctx) {
        untab();
        newLine("}");
    }

    @Override
    public void enterParameter(JythonParser.ParameterContext ctx) {
        List<String> params = new ArrayList<>();
        for (JythonParser.VarDecContext var : ctx.varDec()) {
            String varType = "";
            if (var.varType != null) {
                varType = var.varType.getText();
            } else if (var.varClassName != null) {
                varType = var.varClassName.getText();
            }
            params.add(varType + " " + var.varId.getText());
        }
        newLine("parameters list " + params);
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
        newLine("nested statement{");
        tab();
    }

    @Override
    public void exitIf_statment(JythonParser.If_statmentContext ctx) {
        untab();
        newLine("}");
    }

    @Override
    public void enterWhile_statment(JythonParser.While_statmentContext ctx) {
        newLine("nested statement{");
        tab();
    }

    @Override
    public void exitWhile_statment(JythonParser.While_statmentContext ctx) {
        untab();
        newLine("}");
    }

    @Override
    public void enterIf_else_statment(JythonParser.If_else_statmentContext ctx) {
        newLine("nested statement{");
        tab();
    }

    @Override
    public void exitIf_else_statment(JythonParser.If_else_statmentContext ctx) {
        untab();
        newLine("}");
    }

    @Override
    public void enterPrint_statment(JythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(JythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(JythonParser.For_statmentContext ctx) {
        newLine("nested statement{");
        tab();
    }

    @Override
    public void exitFor_statment(JythonParser.For_statmentContext ctx) {
        untab();
        newLine("}");
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
