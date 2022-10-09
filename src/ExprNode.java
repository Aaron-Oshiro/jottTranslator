import java.util.ArrayList;
import java.util.Arrays;

public class ExprNode implements JottTree {

    private ExprNode expr;
    private boolean exprFlag;
    private IdNode id;
    private boolean idFlag;
    private FuncCallNode funcCall;
    private boolean funcCallFlag;

    public ExprNode(ArrayList<Token> tokens) throws Exception {
        Token t0 = tokens.get(0);
        TokenType tt0 = (tokens.get(0).getTokenType());
        if (tt0 != TokenType.ID_KEYWORD && tt0 != TokenType.NUMBER && tt0 != TokenType.STRING) {
            throw new Exception("Token " + t0.getToken() + "cannot be parsed into a [ at line " + t0.getLineNum());
        }
        Token t1 = tokens.get(1);
        TokenType tt1 = (tokens.get(1).getTokenType());
        if (tt1 == TokenType.MATH_OP || tt1 == TokenType.REL_OP) {
            opNode(tokens);
        }

    }

    @Override
    public String convertToJott() {
        return null;
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
