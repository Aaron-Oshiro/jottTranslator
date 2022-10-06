import java.util.ArrayList;

public class opNode implements JottTree {

    private String operator;

    public opNode(ArrayList<Token> tokens) throws Exception{
        this.operator = tokens.get(0).getToken();
        tokens.remove(0);
    }

    @Override
    public String convertToJott() {
        return operator;
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
