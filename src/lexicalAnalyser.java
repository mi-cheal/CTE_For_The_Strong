import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lexicalAnalyser {
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String line;

        while (true)
        {
            System.out.println("Enter String: (Or Type 99 and press Enter to Quit)");
            line = sc.nextLine();

            if (line.equals("99"))
            {
                // Exit the loop if the user enters 99
                break; 
            }

            analyzeLine(line);
        }
        sc.close();
    }

    private static void analyzeLine(String line) {
        List<Token> tokens = tokenize(line);

        if (tokens == null)
        {
            System.out.println("Error: Invalid characters in the string.");
            return;
        }

        // Symbols Table
        List<Symbol> symbolTable = new ArrayList<>();

        System.out.println("\nSYMBOL TABLE COMPRISING ATTRIBUTES AND TOKENS:");
        int tokenNumber = 1;
        for (Token token : tokens)
        {
            System.out.println("TOKEN#" + tokenNumber + " " + token.value + " " + token.type);

            // Add to the symbol table if it's an identifier
            if (token.type.equals("identifier"))
            {
                // symbolTable.add(new Symbol(token.value, token.type));
                symbolTable.add(new Symbol(line, line));
            }

            tokenNumber++;
        }

        System.out.println("\nTotal number of Tokens: " + tokens.size());
    }

    private static List<Token> tokenize(String line) 
    {  
        
        List<Token> tokens = new ArrayList<>();

        // Acceptable expression patterns
        String identifierPattern = "[a-zA-Z][a-zA-Z0-9]*";
        String operatorPattern = "[\\+\\-\\*\\/\\=\\(\\)]";
        String numberPattern = "[0-9]+";

        Pattern p = Pattern.compile(identifierPattern + "|" + operatorPattern + "|" + numberPattern);
        Matcher m = p.matcher(line);

        while (m.find())
        {
            String tokenValue = m.group();

            if (m.pattern().pattern().equals(identifierPattern))
            {
                tokens.add(new Token(tokenValue, "identifier"));
            } else
            if (m.pattern().pattern().equals(operatorPattern))
            {
                tokens.add(new Token(tokenValue, "operator"));
            } else 
            if (m.pattern().pattern().equals(numberPattern))
            {
                tokens.add(new Token(tokenValue, "number"));
            } else {
                // Invalid character encountered
                return null;
            }
        }
        return tokens;
    }
}