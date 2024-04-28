import java.util.Scanner;
import java.util.List;

public class Compiler {
     public static void main(String[] args)
     {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your program (Type 99 to quit): ");

        String input = "";
        String line;
        while (sc.hasNextLine())
        {
            line = sc.nextLine();
            if (line.equals("99"))
            {
                break;
            }
            // Adds a new line for correct parsing in the case of a multi-line user input
            input += line + "\n";
        }

        // Lexical Analysis
        lexicalAnalyser lexAnal = new lexicalAnalyser();
        List<Token> tokens = lexAnal.tokenize(input);

        // Syntax Analysis
        syntaxAnalyser synAnal = new syntaxAnalyser(tokens);
        if (!synAnal.analyze())
        {
            System.out.println("Syntax Errors Found!");
            return;
            // This function stops the program if syntax errors exist
        }

        // Semantic Analysis
        symbolTables symTab = synAnal.getsymbolTables(); // This is assuming i have a getter
        semanticAnalyser semAnal = new semanticAnalyser(tokens, symTab);
        semAnal.analyze();

        // ICR Generation
        ICRGenerator icrGen = new ICRGenerator(tokens, symTab);
        icrGen.generateICR();

        System.out.println("Compilation Complete!");
     }
}
