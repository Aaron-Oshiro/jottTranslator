import java.util.ArrayList;

public class BodyStmtNode implements JottTree{

    private JottTree bodyStmtNode;

    public BodyStmtNode(ArrayList<Token> tokens) throws Exception {
        // body statement can either be a while loop, an if statement, or a statement
        if (tokens.get(0).getToken().equals("while")){  // is a while loop
            bodyStmtNode = new WhileLoopNode(tokens);
        } else if (tokens.get(0).getToken().equals("if")) { // is an if statement
            bodyStmtNode = new IfStmtNode(tokens);
        } else {    // is a statement
            bodyStmtNode = new StmtNode(tokens);
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
        return false;
    }
}
