import java.util.ArrayList;
import java.util.Arrays;

public class ExprNode implements JottTree {

    private ExprNode expr;
    private boolean exprFlag;
    private IdNode id;
    private boolean idFlag;
    private ValueNode value;
    private boolean valueFlag;
    private opNode op;
    private boolean opFlag;
    private FuncCallNode funcCall;
    private boolean funcCallFlag;

    public ExprNode(ArrayList<Token> tokens) throws Exception {
        Token t0 = tokens.get(0);
        TokenType tt0 = (tokens.get(0).getTokenType());
        if (tt0 != TokenType.ID_KEYWORD && tt0 != TokenType.NUMBER && tt0 != TokenType.STRING) {
            throw new Exception(
                    "Token " + t0.getToken() + "cannot be parsed into an Expression at line " + t0.getLineNum());
        }
        // Token t1 = tokens.get(1);
        TokenType tt1 = (tokens.get(1).getTokenType());
        if (tt1 == TokenType.MATH_OP || tt1 == TokenType.REL_OP) {
            if (tt0 == TokenType.NUMBER || tt0 == TokenType.STRING) {
                value = new ValueNode(tokens);
                valueFlag = true;
            } else {
                id = new IdNode(tokens);
                idFlag = true;
            }
            op = new opNode(tokens);
            opFlag = true;

            expr = new ExprNode(tokens);
            exprFlag = true;

        } else {
            if (tt0 == TokenType.ID_KEYWORD) {
                id = new IdNode(tokens);
                idFlag = true;
            }

            if ((tt0 == TokenType.STRING) || (tt0 == TokenType.NUMBER)) {
                value = new ValueNode(tokens);
                valueFlag = true;
            }
        }

    }

    @Override
    public String convertToJott() {
        if (opFlag) {
            if (idFlag) {
                return id.convertToJott() + op.convertToJott() + expr.convertToJott();
            }
            return value.convertToJott() + op.convertToJott() + expr.convertToJott();
        }
        if (idFlag) {
            return id.convertToJott();
        }
        return value.convertToJott();
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
