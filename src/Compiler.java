import gen.JythonLexer;
import gen.JythonListener;
import gen.JythonParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Compiler {


    public static void main(String[] args) throws IOException {
        CharStream stream = CharStreams.fromFileName("./sample/input.txt");
        JythonLexer lexer = new JythonLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        JythonParser parser = new JythonParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        JythonListener listener = new SymbolTableGenerator();
        walker.walk(listener, tree);
    }




    public Compiler() throws IOException {
    }
}
