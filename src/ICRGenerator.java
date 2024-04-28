import java.util.List;

public class ICRGenerator {
    private List<Token> tokens;
    private symbolTables symbolTable; // A reference to the symbols table class
    private int tempVarCounter = 1;

    public ICRGenerator(List<Token> tokens, symbolTables symbolTable) 
    {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public void generateICR()
    {
        int lineNumber = 1;
        for (Token token : tokens)
        {
            if (token.getType() == TokenType.START)
            {
                // This part is supposed to handle variable declarations (add to symbolsTable)
            } else
            if (lineNumber == 6 || lineNumber == 7)
            {
                generateICRForExpression(token);
            } else 
            {
                // This part is meant to check for other types of errors...
            }
            lineNumber++;
        }
    }

    private void generateICRForExpression(Token startToken) 
    {
        System.out.println("The ICR for the expression is as follows: ");
        int startTokenIndex = tokens.indexOf(startToken);

        String resultVariable = processExpression(startTokenIndex);
        System.out.println(resultVariable + " = ...");
    }

    private String processExpression(int index) 
    {
        Token token = tokens.get(index);

        if (token.getType() == TokenType.OPERATOR)
        {
            return processOperator(index);
        } else
        if (token.getType() == TokenType.IDENTIFIER)
        {
            return token.getValue();
        } else 
        {
            return token.getValue();
        }
    }

    private String processOperator(int index) 
    {
        String operator = tokens.get(index).getValue();
        int leftOperatorAndIndex = index - 1;
        int rightOperatorAndIndex = index + 1;

        String rightVar = processExpression(rightOperatorAndIndex);
        String leftVar = processExpression(leftOperatorAndIndex);

        String newTemp = "t" + tempVarCounter++;
        System.out.println(newTemp + " = " + leftVar + " " + operator + " " + rightVar);
        // return operator;
        return newTemp;
        
    }
}
