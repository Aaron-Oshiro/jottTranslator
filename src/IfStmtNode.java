import java.util.ArrayList;

public class IfStmtNode implements JottTree {
    private ExprNode expr;
    private BodyNode body;
    private ElseIfNode elseIfLst; // need implemented
    private ElseNode lse; // need implemented

    public IfStmtNode(ArrayList<Token> tokens) throws Exception {
        Token t0 = tokens.get(0);
        if (!t0.getToken().equals("if")) {
            throw new Exception("Syntax Error: Token " + t0.getToken() + "cannot be parsed into 'if' at line " +
                    t0.getLineNum());
        } else {
            tokens.remove(0);
            t0 = tokens.get(0);
            if (!t0.getToken().equals("[")) {
                throw new Exception("Syntax Error: Token " + t0.getToken() + "cannot be parsed into a [ at line " +
                        t0.getLineNum());
            }
            tokens.remove(0);

            expr = new ExprNode(tokens);

            t0 = tokens.get(0);
            if (!t0.getToken().equals("]")) {
                throw new Exception("Syntax Error: Token " + t0.getToken() + "cannot be parsed into a ] at line " +
                        t0.getLineNum());
            }
            tokens.remove(0);
            t0 = tokens.get(0);
            if (!t0.getToken().equals("{")) {
                throw new Exception("Syntax Error: Token " + t0.getToken() + "cannot be parsed into a { at line " +
                        t0.getLineNum());
            }
            tokens.remove(0);
            t0 = tokens.get(0);
            body = new BodyNode(tokens);
            if (!t0.getToken().equals("}")) {
                throw new Exception("Syntax Error: Token " + t0.getToken() + "cannot be parsed into a } at line " +
                        t0.getLineNum());
            }
            elseIfLst = new ElseIfNode(tokens);
            lse = new ElseNode(tokens);
        }
    }

    @Override
    public String convertToJott() {
        return "if[" + expr.convertToJott() + "]" + "{" + body.convertToJott() + "}"
                + elseIfLst.convertToJott() + lse.convertToJott();
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
