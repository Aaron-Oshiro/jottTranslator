import java.util.ArrayList;

public class ExprNode implements JottTree {

    private ExprNode expr;
    private boolean exprFlag = false;
    private IdNode id;
    private boolean idFlag = false;
    private ValueNode value;
    private boolean valueFlag = false;
    private OpNode op;
    private boolean opFlag = false;
    private FuncCallNode funcCall;
    private boolean funcCallFlag = false;

    public ExprNode(ArrayList<Token> tokens) throws Exception {
        // System.out.println(tokens.get(0).getToken());
        // System.out.println(tokens.get(0).getToken());

        Token t0 = tokens.get(0);
        TokenType tt0 = (tokens.get(0).getTokenType());

        if (tt0 != TokenType.ID_KEYWORD && tt0 != TokenType.NUMBER && tt0 != TokenType.STRING) {
            throw new Exception(
                    "Syntax Error: Token " + t0.getToken() + "cannot be parsed into a value or id at line "
                            + t0.getLineNum());
        }
        TokenType tt1 = (tokens.get(1).getTokenType());

        if (tt1 == TokenType.MATH_OP || tt1 == TokenType.REL_OP) {

            if (tt0 == TokenType.NUMBER || tt0 == TokenType.STRING) {

                value = new ValueNode(tokens);
                valueFlag = true;
            } else {
                id = new IdNode(tokens);
                idFlag = true;
            }
            op = new OpNode(tokens);
            opFlag = true;

            expr = new ExprNode(tokens);
            exprFlag = true;

        } else if (tt0 == TokenType.ID_KEYWORD && tt1 != TokenType.L_BRACKET) {

            id = new IdNode(tokens);
            idFlag = true;
        } else if ((tt0 == TokenType.STRING) || (tt0 == TokenType.NUMBER)) {

            value = new ValueNode(tokens);
            valueFlag = true;
        } else {


            funcCall = new FuncCallNode(tokens);
            funcCallFlag = true;
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
        } else if (valueFlag) {
            return value.convertToJott();
        } else {
            return funcCall.convertToJott();
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
