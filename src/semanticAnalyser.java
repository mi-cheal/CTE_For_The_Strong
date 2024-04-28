import java.util.List;

public class semanticAnalyser {
    private List<Token> tokens;
    private symbolTables symTab;

    /**
     * @param tokens
     * @param symTab
     */

    public semanticAnalyser(List<Token> tokens, symbolTables symTab) 
    {
        this.tokens = tokens;
        this.symTab = symTab;
    }

    public void analyze() 
    {
        int lineNumber = 1;
        for (Token token : tokens)
        {
            if (token.getType() == TokenType.START)
            {
                if (!handleVariableDeclarations(tokens))
                {
                    System.out.println("Error on line " + lineNumber +": Variable declaration error!");
                }
            } else 
            if (lineNumber == 6 || lineNumber == 7)
            {
                if (!analyzeExpression(token)) 
                {
                     System.out.println("Error on line " + lineNumber +": Syntactic or Semantic Error in expression.");
                } else
                {
                    System.out.println("CONCLUSION --> This expression on line " + lineNumber + " is Syntactically and Semantically correct");
                }
            }
            // If you have time, add a function that checks other lines for non-expression errors
            lineNumber++;
        }
    }

    private boolean analyzeExpression(Token token) 
    {
        // ... Your existing expression analysis logic with enhancements from previous prompts ... 
        return true; // Or false if an error is found
    }

    private boolean handleVariableDeclarations(List<Token> tokens2) 
    {
        // Implement variable declaration parsing and symbol table update logic here
        if (((Token) tokens2).getType() != TokenType.KEYWORD || !tokens2.getValue.equals("INTEGER"))
        return false;
        {
            return false;
        }
    }
    
}
