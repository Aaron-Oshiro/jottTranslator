/**
 * @author Aaron Oshiro
 */

import java.util.ArrayList;
import java.util.HashMap;

public class ReturnNode implements JottTree {

    private ExprNode exprNode;
    private EndStmtNode endStmtNode;

    public ReturnNode(ArrayList<Token> tokens) throws Exception {
        if (!tokens.get(0).getToken().equals("return")) {
            throw new Exception("Syntax Error: Return statement must start with \"return\" but instead token " + tokens.get(0).getToken() + " was found at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
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
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        return exprNode.validateTree(functionTable, symbolTable) && endStmtNode.validateTree(functionTable, symbolTable)                                                      ;
    }
}
