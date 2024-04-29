import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner KBD = new Scanner(System.in);
        System.out.println("Enter your expression");
        String exp = KBD.nextLine();

        try {
            ifNinetyNine(exp);

            checkForSpecialChar(exp);
            checkForTwoOperators(exp);
            checkForOperators(exp);
            checkforspace(exp);
            tokenizeExpression(exp);
            SyntaxAnalyser(exp);




        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }


    }



    public static void checkForSpecialChar(String exp) {
        for (char string : exp.toCharArray()) {
            if (string == '!' || string == '@' || string == '#' || string == '%' || string == '$' || string == '^' || string == '&' || string == '(' || string == ')' || string == '_' || string == '=' || string == '?') {
                throw new IllegalArgumentException("Semantic error: Use of Special Characters ie &, &&, $, %, !, , etc, not permitted");
            }
        }
    }
    public static void checkforspace(String exp){
        for (char string : exp.toCharArray())
            if(string==' '){
                throw new IllegalArgumentException("Space between identifier and operand is not allowed ");
            }
    }

    public static void checkForTwoOperators(String exp) {
        for (int i = 0; i < exp.length() - 1; i++) {
            char current = exp.charAt(i);
            char next = exp.charAt(i + 1);
            if (isOperator(current) && isOperator(next)) {
                throw new IllegalArgumentException("SEMANTIC ERROR- Two operators (*,-,+,/) cannot be written together!");
            }
        }
    }

    public static void checkForOperators(String exp) {
        boolean operatorFound = false;
        for (char c : exp.toCharArray()) {
            if (isOperator(c)) {
                operatorFound = true;
                break;
            }
        }
        if (!operatorFound) {
            throw new IllegalArgumentException("ERROR - The expression must contain at least one of the following operators: +, -, *, /");
        }
    }

    public static void ifNinetyNine(String exp) {
        if (exp.equals("99")) {
            throw new IllegalArgumentException("Exiting program...");
        }
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static void checkIfSemi(String exp){
        boolean checkSemi=false;
        for (char c:exp.toCharArray()){
            if (c==';'){
                checkSemi=true;
                break;

            }

        }
        if (!checkSemi){
            throw new IllegalArgumentException("Semicolon is needed at the and of each line");
        }

    }

    public static void tokenizeExpression(String c) {

        StringTokenizer tokenizer = new StringTokenizer(c, " +-/*;", true);
        int tokenCount = 0;


        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.isEmpty()) {
                String tokenType = getTokenType(token);
                System.out.println("TOKEN#" + (++tokenCount) + " " + token + " " + tokenType);
            }


        }
        System.out.println("TOTAL NUMBER OF TOKENS FOR STRING: " + tokenCount);
    }

    public static String getTokenType(String token) {
        switch (token) {
            case "+":
            case "-":
            case "/":
            case "*":
                return "Operator";
            case ";":
                return "symbol";
            default:
                return "Identifier";
        }


    }



    public static void SyntaxAnalyser(String c){

        StringTokenizer tokenizer = new StringTokenizer(c, " +-/*;", true);
        System.out.println("GET A DERIVATION FOR : "+c);
        String derivation =c.replaceAll("[0-9]","digit")
                .replaceAll("[-+/*]","E")
                .replaceAll(";","");
        System.out.println(derivation);



    }

}