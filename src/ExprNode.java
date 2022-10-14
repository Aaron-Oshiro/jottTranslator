import java.util.ArrayList;

public class ExprNode implements JottTree {

    private IdNode id;
    private ValueNode value;
    private FuncCallNode funcCall;

    private ExprNode firstExpr;
    private OpNode op;
    private ExprNode secondExpr;

    public ExprNode(ArrayList<Token> tokens) throws Exception {
        // can be an id, funcCall, value, or expr op expr

        Token t0 = tokens.get(0);
        TokenType tt0 = (tokens.get(0).getTokenType());

        if (tt0 != TokenType.ID_KEYWORD && tt0 != TokenType.NUMBER && tt0 != TokenType.STRING) {
            throw new Exception(
                    "Syntax Error: Token " + t0.getToken() + " cannot be parsed into a value or id at " + t0.getFilename() + " line "
                            + t0.getLineNum());
        }
        firstExpr = new ExprNode(tokens, true);
        if (tokens.get(0).getTokenType() == TokenType.REL_OP || tokens.get(0).getTokenType() == TokenType.MATH_OP) {
            op = new OpNode(tokens);
            secondExpr = new ExprNode(tokens, true);
        }
    }

    private ExprNode(ArrayList<Token> tokens, boolean stop) throws Exception {
        Token t0 = tokens.get(0);
        TokenType tt0 = (tokens.get(0).getTokenType());

        if (tt0 != TokenType.ID_KEYWORD && tt0 != TokenType.NUMBER && tt0 != TokenType.STRING) {
            throw new Exception(
                    "Syntax Error: Token " + t0.getToken() + " cannot be parsed into a value or id at " + t0.getFilename() + " line "
                            + t0.getLineNum());
        }
        TokenType tt1 = (tokens.get(1).getTokenType());

        if (tt0 == TokenType.ID_KEYWORD && tt1 != TokenType.L_BRACKET) {    // if it's not a function call
            id = new IdNode(tokens);

        } else if ((tt0 == TokenType.STRING) || (tt0 == TokenType.NUMBER)) {
            value = new ValueNode(tokens);

        } else {
            funcCall = new FuncCallNode(tokens);
        }
    }

    @Override
    public String convertToJott() {
        if (id != null) {   // just an id_keyword
            return id.convertToJott();
        } else if (value != null) { // just a value/string/number
            return value.convertToJott();
        } else if (funcCall != null){   // just a function call
            return funcCall.convertToJott();
        } else if (secondExpr != null) {    // if there is a second Expression, then it's an (expr op expr) expression
            return firstExpr.convertToJott() + " " + op.convertToJott() + " " + secondExpr.convertToJott();
        } else {    // it's just the first expression, no (expr op expr) expression
            return firstExpr.convertToJott();
        }
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
