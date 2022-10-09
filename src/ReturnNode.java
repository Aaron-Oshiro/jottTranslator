/**
 * @author Aaron Oshiro
 */

import java.util.ArrayList;

public class ReturnNode implements JottTree {

    private ExprNode exprNode;
    private EndStmtNode endStmtNode;

    public ReturnNode(ArrayList<Token> tokens) throws Exception {
        if (!tokens.get(0).getToken().equals("return")) {
            throw new Exception("Return statement must start with \"return\"");
        }
        tokens.remove(0);   // removes the 'return'
        this.exprNode = new ExprNode(tokens);
        this.endStmtNode = new EndStmtNode(tokens);
    }

    @Override
    public String convertToJott() {
        return "return " + exprNode.convertToJott() + endStmtNode.convertToJott();
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
