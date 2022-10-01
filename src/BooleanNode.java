/**
 * This is the boolean node
 * Contains the boolean given
 * Because there is no TokenType of BOOLEAN, we assume it is an ID_KEYWORD
 *
 * @author Aaron Oshiro
 */

import java.util.ArrayList;
import java.util.HashSet;

public class BooleanNode implements JottTree {

    private String boolNode;

    private HashSet<String> boolOps = new HashSet<>();

    public BooleanNode(ArrayList<Token> tokens) throws Exception {
        createBooleans();
        if (boolOps.contains(tokens.get(0).getToken()) && tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            Token newBoolNode = tokens.remove(0);
            boolNode = newBoolNode.getToken();
        } else {
            throw new Exception("Token can not be parsed into a Boolean");
        }
    }

    private void createBooleans(){
        boolOps.add("True");
        boolOps.add("False");
    }

    @Override
    public String convertToJott() {
        return boolNode;
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
        return true;
    }
}
