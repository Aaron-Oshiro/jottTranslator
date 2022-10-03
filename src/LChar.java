import java.util.ArrayList;
import java.util.Arrays;


/**
 * Lower case char
 *
 * @author Raman Zatsarenko
 */
public class LChar implements JottTree{
    private String lChar;
    private ArrayList<String> lowerChars = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
    public LChar(ArrayList<Token> tokens) throws Exception{
        Token tokenToCheck = tokens.get(0);
        if (lowerChars.contains(tokenToCheck.getToken())) {
            lChar = tokenToCheck.getToken();
            tokens.remove(0);
        }
        else {
            throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a l_char at line " + tokenToCheck.getLineNum());
        }
    }

    @Override
    public String convertToJott() {
        return lChar;
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
