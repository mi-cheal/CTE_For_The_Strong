import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class syntaxAnalyser 
{
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
        result = prime * result + currentTokenIndex;
        result = prime * result + ((declaredVariables == null) ? 0 : declaredVariables.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        syntaxAnalyser other = (syntaxAnalyser) obj;
        if (tokens == null) {
            if (other.tokens != null)
                return false;
        } else if (!tokens.equals(other.tokens))
            return false;
        if (currentTokenIndex != other.currentTokenIndex)
            return false;
        if (declaredVariables == null) {
            if (other.declaredVariables != null)
                return false;
        } else if (!declaredVariables.equals(other.declaredVariables))
            return false;
        return true;
    }

    private List<Token> tokens;
    private int currentTokenIndex = 0;
    private Set<String> declaredVariables = new HashSet<>();

    public syntaxAnalyser(List<Token> tokens)
    {
        this.tokens = tokens;
        Symbol.push(new HashSet<>());
    }

    public boolean analyze()
    {
        currentTokenIndex = 0;
        if (parseStart() && currentTokenIndex == tokens.size())
        {
            System.out.println("String accepted by the grammar!");
        } else
        {
            System.out.println("Syntax error at position: " + currentTokenIndex);
        }
        return false;
    }

    private boolean parseStart() 
    {
        return match("START") && parseVariableList() && match("READ") && parseVariableList(true) && parseStatements() && match("STOP");
    }   

    private boolean parseVariableList(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseVariableList'");
    }

    private boolean parseVariableList() 
    {
        if (parseVariable())
        {
            // This is based on the assumption 'currentToken().getValue()' gives the identifier name
            ((Object) declaredVariables).addSymbol(((Token) currentToken()).getValue());
            Symbol.peek().add(((Token) currentToken()).getValue());
            return parseVariableListTail();
        }
        // return parseVariable() && parseVariableListTail();
        return false;
    }

    private boolean parseVariableListTail() 
    {
        if (match(","))
        {
            return parseVariable() && parseVariableListTail();
        } else
        {
            // Empty list is acceptable!
            return true;
        }
    }

    private boolean parseVariable() 
    {
        return match(TokenType.IDENTIFIER);
    }

    private boolean match(TokenType identifier) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'match'");
    }

    private boolean parseStatements() 
    {
        if (isStatementStart())
        {
            return parseStatements() && parseStatementsTail();
        } else
        {
            return true;
        } 
    }

    private boolean parseStatementsTail() 
    {
        if (isStatementStart())
        {
            return parseStatement() && parseStatementsTail();
        } else
        {
            // Empty sequence of statements is acceptable!
            return true;
        }
    }

    public enum SyntaxErrorType
    {
        MISSING_OPERAND,
        INVALID_OPERATOR_COMBINATION,
        UNDECLARED_VARIABLE,
        GENEREAL_SYNTAX_ERROR
    }

    private void reportSyntaxError(SyntaxErrorType errorType, int position)
    {
        switch (errorType) {
            case MISSING_OPERAND:
                System.out.println("Syntax Error: Missing operand at position: " + position);
                break;

            case INVALID_OPERATOR_COMBINATION:
                System.out.println("Syntax Error: Invalid operator combination at position: " + position);
                break;

            case UNDECLARED_VARIABLE:
                System.out.println("Syntax Error: Undeclared variable at position: " + position);
                break;

        
            default:
                System.out.println("Syntax Error at position: " + position);
                break;
        }
    }

    private boolean parseStatement() 
    {
        // Differentiate based on the first token
        if (match("ASSIGN"))
        {
            return parseAssignment();
        } else
        if (match("READ")) 
        {
            return parseStatement();
        } else 
        if (match("WRITE"))
        {
            return parseStatement();
        } else 
        {
            // Syntax error
            return false;
        }
    }

    private boolean parseAssignment() 
    {
        if (match("ASSIGN"))
        {
            if (match(TokenType.IDENTIFIER)) 
            {
                if (match("=")) 
                {
                    return parseExpression() && match(";");
                }
            }
        }
        return false;
    }

    private boolean parseExpression() 
    {
        if (parseTerm())
        {
            if (match("+") || match("-") || match("*") || match("/"))
            {
                if (parseExpression()) 
                {
                    return true;
                }
            } else
            {
                // Single term is also an expression
                return true;
            }
        }
        return false;
    }
    public Token nextToken() 
    {
        if (currentTokenIndex >= tokens.size())
        {
            // Indicates the end of the token stream
            return null;
        }
        return tokens.get(currentTokenIndex++);
    }

    private boolean parseTerm() 
    {
        if (match("(")) 
        {
            if (parseExpression())
            {
                return match(")");
            }
        }
        return false;

        // if (match("id") || match("digit") || match("(") || match(")"))
        // {
        //     if (isOperator(currentToken())) 
        //     {
        //         // Assume you have a helper function
        //         if (!nextTokenIsValidOperand())
        //         {
        //              // Check if the following token is a valid term/operand
        //             reportSyntaxError(SyntaxErrorType.MISSING_OPERAND, currentTokenIndex);
        //             return false;
        //         } else
        //         if (isOperator(nextToken()))
        //         {
        //             reportSyntaxError(SyntaxErrorType.INVALID_OPERATOR_COMBINATION, currentTokenIndex);
        //             return false;
        //         } else 
        //         if (!isOperator(currentToken()))
        //         {
        //             reportSyntaxError(SyntaxErrorType.UNDECLARED_VARIABLE, currentTokenIndex);
        //             return false;
        //         }
        //     } else
        //     {
        //         reportSyntaxError(SyntaxErrorType.GENEREAL_SYNTAX_ERROR, currentTokenIndex);
        //         return false;
        //     }
        // }
    }


    private boolean isOperator(Object token) 
    {
        if (token instanceof String) 
        {
            String tokenString = (String) token;
            return tokenString.equals("+") || tokenString.equals("-") || tokenString.equals("*") || tokenString.equals("/");
        }
        return false;
    }

    private boolean nextTokenIsValidOperand() 
    {
        Token nextToken;
        if (currentTokenIndex + 1 < tokens.size())
        {
            Token nexToken = tokens.get(currentTokenIndex + 1);
            if (nexToken.getType().equals(TokenType.IDENTIFIER))
            {
                return declaredVariables.contains(nexToken.getValue());
            } else 
            {
                return nexToken.getType().equals(TokenType.NUMBER);
            }
        } else 
        if (nexToken.getType().equals(TokenType.IDENTIFIER)) 
        {
            HashSet<String>[] symbolTables;
            for (HashSet<String> table : symbolTables)
            {
                // Iteration to be executed from top going down
                if (table.contains(nextToken().getValue()))
                {
                    return true;
                }
            }
            return false;
        }
        {
            return false;
        }
    }

    private Object currentToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'currentToken'");
    }

    private boolean match(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'match'");
    }
    
    private boolean isStatementStart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStatementStart'");
    }

    public symbolTables getsymbolTables() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getsymbolTables'");
    }

}
