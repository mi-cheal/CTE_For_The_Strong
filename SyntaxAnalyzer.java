public class SyntaxAnalyzer {
    public static void main(String[] args) {
        String input = "READ M, N, K";
        boolean isValid = syntaxCheck(input);
        System.out.println("Syntax is valid: " + isValid);
    }

    public static boolean syntaxCheck(String input) {
        // Implement your syntax checking logic here
        // For simplicity, we assume syntax is always valid in this example
        return true;
    }
}
