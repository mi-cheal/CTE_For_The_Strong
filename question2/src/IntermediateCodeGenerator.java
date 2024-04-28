import java.util.ArrayList;

public class IntermediateCodeGenerator  {
    public static String generateIntermediateCode(ArrayList<String> tokens) {
        // Implement intermediate code generation logic here
        // For simplicity, return concatenated tokens
        StringBuilder intermediateCode = new StringBuilder();
        for (String token : tokens) {
            intermediateCode.append(token).append(" ");
        }
        return intermediateCode.toString();
    }
}
