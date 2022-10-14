import java.util.ArrayList;

public class EndStmtNode implements JottTree {

    public EndStmtNode(ArrayList<Token> tokens) throws Exception {
        Token t0 = tokens.get(0);
        if (t0.getToken().equals(";")) {
            tokens.remove(0);
        } else {
            throw new Exception(
                    "Syntax Error: Token " + t0.getToken() + " cannot be parsed into a ; at " +t0.getFilename() + " line " + t0.getLineNum());
        }
    }

    @Override
    public String convertToJott() {
        return ";";
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
