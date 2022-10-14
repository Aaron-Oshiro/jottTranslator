import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
public class DigitNode implements JottTree {

    private String digit;

    private ArrayList<String> digits = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));

    public DigitNode(ArrayList<Token> tokens) throws Exception{

        Token tokenToCheck = tokens.get(0);

        if (digits.contains(tokenToCheck.getToken())) {
            digit = tokenToCheck.getToken();
            tokens.remove(0);
        }
        else {
            throw new Exception("Syntax Error: Token "+ tokenToCheck.toString() + " cannot be parsed into a digit at line " + tokenToCheck.getLineNum());
        }
    }

    @Override
    public String convertToJott() {

        return digit;
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
