public class Main {
    public static void main(String[] args) {
        // Lexical Analysis (Scanner)
        lexicalAnalysis();

        // Syntax Analysis (Parser)
        syntaxAnalysis();

        // Semantic Analysis (Syntactic Analysis)
        semanticAnalysis();

    }

    public static void lexicalAnalysis() {
        // Call Lexical Analyzer
        LexicalAnalyzer.main(null);
    }

    public static void syntaxAnalysis() {
        // Call Syntax Analyzer
        SyntaxAnalyzer.main(null);
    }

    public static void semanticAnalysis() {
        // Call Semantic Analyzer
        SemanticAnalyzer.main(null);
    }

    public static void intermediateCodeGeneration() {
        // Call Intermediate Code Generator
        IntermediateCodeGenerator.main(null);
    }

    public static void codeGeneration() {
        // Call Code Generator
        CodeGenerator.main(null);
    }

    public static void codeOptimization() {
        // Call Code Optimizer
        CodeOptimizer.main(null);
    }
}
