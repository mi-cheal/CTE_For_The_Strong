public class SemanticAnalyzer {
    public static void main(String[] args) {
        String input = "ASSIN =G MN -/ K";
        boolean isValid = semanticCheck(input);
        System.out.println("Semantic is valid: " + isValid);
    }

    public static boolean semanticCheck(String input) {
        // Implement your semantic checking logic here
        // For simplicity, we assume semantic is always valid in this example
        return true;
    }
}
