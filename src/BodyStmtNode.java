import java.util.ArrayList;
import java.util.HashMap;

public class BodyStmtNode implements JottTree{

    private JottTree bodyStmtNode;

    public BodyStmtNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable) throws Exception {
        // body statement can either be a while loop, an if statement, or a statement
        if (tokens.get(0).getToken().equals("while")){  // is a while loop
            bodyStmtNode = new WhileLoopNode(tokens, symbolTable);
        } else if (tokens.get(0).getToken().equals("if")) { // is an if statement
            bodyStmtNode = new IfStmtNode(tokens, symbolTable);
        } else {    // is a statement
            bodyStmtNode = new StmtNode(tokens, symbolTable);
        }

    }
    @Override
    public String convertToJott() {
        return bodyStmtNode.convertToJott();
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
        return bodyStmtNode.validateTree();
    }
}
