import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalysis {
    public static ArrayList<String> tokenize(String program) {
        ArrayList<String> tokens = new ArrayList<>();

        // Define regular expressions for different token types
        String keywordRegex = "\\b(INTEGER|ASSIN|READ|WRITE|START|STOP)\\b";
        String identifierRegex = "\\b[a-zA-Z]\\w*\\b";
        String symbolRegex = "[,|]";
        String errorRegex = "[^\\s]"; // Any character not allowed

        String regex = String.format("(%s)|(%s)|(%s)|(%s)", keywordRegex, identifierRegex, symbolRegex, errorRegex);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(program);

        while (matcher.find()) {
            String token = matcher.group();
            tokens.add(token);
        }

        return tokens;
    }
}
