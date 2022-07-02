import gen.JythonListener;
import gen.JythonParser;
import items.Class;
import items.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class SymbolTableGenerator implements JythonListener {

    private final Scope globalScope = new Scope();
    private Class currentClass;
    private Import currentImport;
    private Method currentMethod;
    private Constructor currentConstructor;

    private ConditionalBlock currentConditionalBlock;
    private Nested currentNested;

    private final String finalResult = "";
    private final ArrayList<String> isDefiend = new ArrayList<>();


    @Override
    public void enterProgram(JythonParser.ProgramContext ctx) {
        isDefiend.add("int");
        isDefiend.add("float");
        isDefiend.add("bool");
        isDefiend.add("string");
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
        isDefiend.add(ctx.CLASSNAME().getText());
        globalScope.insert("import_" + ctx.CLASSNAME().getText(), currentImport);


    }

    @Override
    public void exitImportclass(JythonParser.ImportclassContext ctx) {
        currentImport = null;
    }

    @Override
    public void enterClassDef(JythonParser.ClassDefContext ctx) {
        currentClass = new Class(ctx.className.getText(), ctx.classParent.getText() + "," + ctx.classParent2.getText());
        isDefiend.add(ctx.className.getText());
        currentClass.scopeNumber = ctx.start.getLine();
        var id = "class_" + ctx.className.getText();
        globalScope.insert(id, currentClass);

    }

    @Override
    public void exitClassDef(JythonParser.ClassDefContext ctx) {
        currentClass.printScope();
        currentClass = null;
    }

    @Override
    public void enterClass_body(JythonParser.Class_bodyContext ctx) {
        ClassField classField = null;
        ClassArrayField classArrayField = null;
        if (ctx.varDec() != null) {
            if (ctx.varDec().varType == null) {
                classField = new ClassField(ctx.varDec().varId.getText(), ctx.varDec().varClassName.getText(), isDefiend.contains(ctx.varDec().varClassName.getText()));
            } else {
                classField = new ClassField(ctx.varDec().varId.getText(), ctx.varDec().varType.getText(), isDefiend.contains(ctx.varDec().varType.getText()));
            }
            currentClass.insert("Field_" + ctx.varDec().varId.getText(), classField);
        } else if (ctx.arrayDec() != null) {
            if (ctx.arrayDec().arrType == null) {
                classArrayField = new ClassArrayField(ctx.arrayDec().arrId.getText(), ctx.arrayDec().arrClassName.getText(), isDefiend.contains(ctx.arrayDec().arrClassName.getText()));
            } else {
                classArrayField = new ClassArrayField(ctx.arrayDec().arrId.getText(), ctx.arrayDec().arrType.getText(), isDefiend.contains(ctx.arrayDec().arrType.getText()));
            }
            currentClass.insert("Field_" + ctx.arrayDec().arrId.getText(), classArrayField);
        } else if (ctx.constructor() != null) {


        }
    }

    @Override
    public void exitClass_body(JythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void enterVarDec(JythonParser.VarDecContext ctx) {
//            var field = new Field()
        //todo az koja befahmam kodom n o var e ?
        if (ctx.varType != null) {
            isDefiend.add(ctx.varType.getText());
        }
//        ClassField classField = null;
//        if (currentClass != null) {
//            if (ctx.varType == null) {
//                classField = new ClassField(ctx.varId.getText(), ctx.varClassName.getText(), false);
//            } else {
//                classField = new ClassField(ctx.varId.getText(), ctx.varType.getText(), false);
//            }
//            currentClass.insert("Field_" + ctx.varId.getText(), classField);
//        }

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
        if (ctx.methodType != null) currentMethod = new Method(ctx.methodId.getText(), ctx.methodType.getText());
        else currentMethod = new Method(ctx.methodId.getText(), ctx.VOID().getText());
        currentMethod.scopeNumber = ctx.start.getLine();
        for (JythonParser.ParameterContext p : ctx.parameter()) {
            for (int i = 0; i < p.varDec().size(); i++) {
                if (p.varDec(i).varType == null)
                    currentMethod.addParameter(new Parameter(p.varDec(i).varId.getText(), p.varDec(i).varClassName.getText(), isDefiend.contains(p.varDec(i).varClassName.getText()), i));
                else
                    currentMethod.addParameter(new Parameter(p.varDec(i).varId.getText(), p.varDec(i).varType.getText(), isDefiend.contains(p.varDec(i).varType.getText()), i));
            }
        }

        for (int j = 0; j < ctx.statement().size(); j++) {
            if (ctx.statement(j).varDec() != null) {
                if (ctx.statement(j).varDec().varType == null) {
                    currentMethod.addMethodField(new MethodField(ctx.statement(j).varDec().varId.getText(), ctx.statement(j).varDec().varClassName.getText(), isDefiend.contains(ctx.statement(j).varDec().varClassName.getText())));
                } else {
                    currentMethod.addMethodField(new MethodField(ctx.statement(j).varDec().varId.getText(), ctx.statement(j).varDec().varType.getText(), isDefiend.contains(ctx.statement(j).varDec().varType.getText())));
                }
            }
        }


        if (currentClass != null) {
            currentClass.insert("Method_" + ctx.methodId, currentMethod);
        }

    }

    @Override
    public void exitMethodDec(JythonParser.MethodDecContext ctx) {
        currentMethod.printScope();
        currentMethod = null;
    }

    @Override
    public void enterConstructor(JythonParser.ConstructorContext ctx) {
        String className = "";
        if (ctx.CLASSNAME() != null) {
            className = ctx.CLASSNAME().getText();
        } else if (ctx.TYPE() != null) {
            className = ctx.TYPE().getText();

        }
        currentConstructor = new Constructor(className);
        for (JythonParser.ParameterContext p : ctx.parameter()) {
            for (int i = 0; i < p.varDec().size(); i++) {
                if (p.varDec(i).varType == null)
                    currentConstructor.addParameter(new Parameter(p.varDec(i).varId.getText(), p.varDec(i).varClassName.getText(), isDefiend.contains(p.varDec(i).varClassName.getText()), i));
                else
                    currentConstructor.addParameter(new Parameter(p.varDec(i).varId.getText(), p.varDec(i).varType.getText(), isDefiend.contains(p.varDec(i).varType.getText()), i));
            }
        }
        currentConstructor.scopeNumber = ctx.start.getLine();
        currentClass.insert("Constructor_" + className, currentConstructor);
    }

    @Override
    public void exitConstructor(JythonParser.ConstructorContext ctx) {
        currentConstructor.printScope();
        currentConstructor = null;
    }

    @Override
    public void enterParameter(JythonParser.ParameterContext ctx) {
        var id = "Field_" + ctx.varDec(0); //TODO REFACTOR

    }

    @Override
    public void exitParameter(JythonParser.ParameterContext ctx) {

    }

    @Override
    public void enterStatement(JythonParser.StatementContext ctx) {
        if (currentConditionalBlock != null) { //it is nested
            if (ctx.if_statment() != null) {
                currentNested = new Nested(ctx.if_statment().getText());
                currentNested.scopeNumber = ctx.start.getLine();
                for (int j = 0; j < ctx.if_statment().statement().size(); j++) {
                    if (ctx.if_statment().statement(j).varDec() != null) {
                        if (ctx.if_statment().statement(j).varDec().varType == null) {
                            currentNested.addMethodField(new MethodField(ctx.if_statment().statement(j).varDec().varId.getText(), ctx.if_statment().statement(j).varDec().varClassName.getText(), isDefiend.contains(ctx.if_statment().statement(j).varDec().varClassName.getText())));
                        } else {
                            currentNested.addMethodField(new MethodField(ctx.if_statment().statement(j).varDec().varId.getText(), ctx.if_statment().statement(j).varDec().varType.getText(), isDefiend.contains(ctx.if_statment().statement(j).varDec().varType.getText())));
                        }
                    }
                }

            } else if (ctx.while_statment() != null) {
                currentNested = new Nested(ctx.while_statment().getText());
                currentNested.scopeNumber = ctx.start.getLine();
                for (int j = 0; j < ctx.while_statment().statement().size(); j++) {
                    if (ctx.while_statment().statement(j).varDec() != null) {
                        if (ctx.while_statment().statement(j).varDec().varType == null) {
                            currentNested.addMethodField(new MethodField(ctx.while_statment().statement(j).varDec().varId.getText(), ctx.while_statment().statement(j).varDec().varClassName.getText(), isDefiend.contains(ctx.while_statment().statement(j).varDec().varClassName.getText())));
                        } else {
                            currentNested.addMethodField(new MethodField(ctx.while_statment().statement(j).varDec().varId.getText(), ctx.while_statment().statement(j).varDec().varType.getText(), isDefiend.contains(ctx.while_statment().statement(j).varDec().varType.getText())));
                        }
                    }
                }

            } else if (ctx.for_statment() != null) {
                currentNested = new Nested(ctx.for_statment().getText());
                currentNested.scopeNumber = ctx.start.getLine();
                for (int j = 0; j < ctx.while_statment().statement().size(); j++) {
                    if (ctx.for_statment().statement(j).varDec() != null) {
                        if (ctx.for_statment().statement(j).varDec().varType == null) {
                            currentNested.addMethodField(new MethodField(ctx.for_statment().statement(j).varDec().varId.getText(), ctx.for_statment().statement(j).varDec().varClassName.getText(), isDefiend.contains(ctx.for_statment().statement(j).varDec().varClassName.getText())));
                        } else {
                            currentNested.addMethodField(new MethodField(ctx.for_statment().statement(j).varDec().varId.getText(), ctx.for_statment().statement(j).varDec().varType.getText(), isDefiend.contains(ctx.for_statment().statement(j).varDec().varType.getText())));
                        }
                    }
                }

            }

        }
    }

    @Override
    public void exitStatement(JythonParser.StatementContext ctx) {
        if (currentNested != null) {
            currentNested.printScope();
            currentNested = null;
        }

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

        currentConditionalBlock = new ConditionalBlock(ctx.WHILE().getText());
        currentConditionalBlock.scopeNumber = ctx.start.getLine();
        for (int j = 0; j < ctx.statement().size(); j++) {
            if (ctx.statement(j).varDec() != null) {
                if (ctx.statement(j).varDec().varType == null) {
                    currentConditionalBlock.addMethodField(new MethodField(ctx.statement(j).varDec().varId.getText(), ctx.statement(j).varDec().varClassName.getText(), isDefiend.contains(ctx.statement(j).varDec().varClassName.getText())));
                } else {
                    currentConditionalBlock.addMethodField(new MethodField(ctx.statement(j).varDec().varId.getText(), ctx.statement(j).varDec().varType.getText(), isDefiend.contains(ctx.statement(j).varDec().varType.getText())));
                }
            }
        }

    }

    @Override
    public void exitWhile_statment(JythonParser.While_statmentContext ctx) {
        currentConditionalBlock.printScope();
        currentConditionalBlock = null;
    }

    @Override
    public void enterIf_else_statment(JythonParser.If_else_statmentContext ctx) {
        currentConditionalBlock = new ConditionalBlock(ctx.IF().getText());
        currentConditionalBlock.scopeNumber = ctx.start.getLine();
        for (int j = 0; j < ctx.statement().size(); j++) {
            if (ctx.statement(j).varDec() != null) {
                if (ctx.statement(j).varDec().varType == null) {
                    currentConditionalBlock.addMethodField(new MethodField(ctx.statement(j).varDec().varId.getText(), ctx.statement(j).varDec().varClassName.getText(), isDefiend.contains(ctx.statement(j).varDec().varClassName.getText())));
                } else {
                    currentConditionalBlock.addMethodField(new MethodField(ctx.statement(j).varDec().varId.getText(), ctx.statement(j).varDec().varType.getText(), isDefiend.contains(ctx.statement(j).varDec().varType.getText())));
                }
            }
        }

    }

    @Override
    public void exitIf_else_statment(JythonParser.If_else_statmentContext ctx) {
        currentConditionalBlock.printScope();
        currentConditionalBlock = null;

    }

    @Override
    public void enterPrint_statment(JythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(JythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(JythonParser.For_statmentContext ctx) {
        currentConditionalBlock = new ConditionalBlock(ctx.FOR().getText());
        currentConditionalBlock.scopeNumber = ctx.start.getLine();
        for (int j = 0; j < ctx.statement().size(); j++) {
            if (ctx.statement(j).varDec() != null) {
                if (ctx.statement(j).varDec().varType == null) {
                    currentConditionalBlock.addMethodField(new MethodField(ctx.statement(j).varDec().varId.getText(), ctx.statement(j).varDec().varClassName.getText(), isDefiend.contains(ctx.statement(j).varDec().varClassName.getText())));
                } else {
                    currentConditionalBlock.addMethodField(new MethodField(ctx.statement(j).varDec().varId.getText(), ctx.statement(j).varDec().varType.getText(), isDefiend.contains(ctx.statement(j).varDec().varType.getText())));
                }
            }
        }


    }

    @Override
    public void exitFor_statment(JythonParser.For_statmentContext ctx) {
        currentConditionalBlock.printScope();
        currentConditionalBlock = null;
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
