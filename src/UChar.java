import java.util.ArrayList;
import java.util.Arrays;

/**
 * Upper case char
 *
 * @author Raman Zatsarenko
 */
public class UChar implements JottTree{
    private String uChar;
    private ArrayList<String> upperChars = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

    public UChar(ArrayList<Token> tokens) throws Exception {
        Token tokenToCheck = tokens.get(0);
        if (upperChars.contains(tokenToCheck.getToken())) {
            uChar = tokenToCheck.getToken();
            tokens.remove(0);
        }
        else {
            throw new Exception("Syntax Error: Token "+ tokenToCheck.toString() + " cannot be parsed into a u_char at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
    }

    @Override
    public String convertToJott() {
        return uChar;
    }

    @Override
    public String convertToJava() {
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
