import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Input program
        String program = "START\n" +
                "INTEGER M, N, K, P, R, H, |, g, k, m\n" +
                "READ M, N, K\n" +
                "ASSIN =G MN -/ K\n" +
                "READ g, H, |, m\n" +
                "P = g/H-l+m*N/k\n" +
                "R= M+N/K\n" +
                "WRITE P\n" +
                "STOP";

        // Lexical analysis
        ArrayList<String> tokens = LexicalAnalysis.tokenize(program);

        // Syntax analysis
        boolean isSyntaxValid = Parser.checkSyntax(tokens);
        if (!isSyntaxValid) {
            System.out.println("Syntax error!");
            return;
        }

        // Semantic analysis
        boolean isSemanticValid = SemanticAnalyzer.checkSemantics(tokens);
        if (!isSemanticValid) {
            System.out.println("Semantic error!");
            return;
        }

        // Intermediate code generation
        String intermediateCode = IntermediateCodeGenerator.generateIntermediateCode(tokens);
        System.out.println("Intermediate code: " + intermediateCode);

        // Code generation
        String machineCode = CodeGenerator.generateMachineCode(intermediateCode);
        System.out.println("Machine code: " + machineCode);

        // Code optimization
        String optimizedCode = CodeOptimizer.optimizeCode(machineCode);
        System.out.println("Optimized code: " + optimizedCode);
    }
}
