import java.util.ArrayList;

public class BodyStmtNode implements JottTree{

    private JottTree bodyNode;

    public BodyStmtNode(ArrayList<Token> tokens) throws Exception {
        // body statement can either be a while loop, an if statement, or a statement
        if (tokens.get(0).getToken().equals("while")){  // is a while loop
            bodyNode = new WhileLoopNode(tokens);
        } else if (tokens.get(0).getToken().equals("if")) { // is an if statement
            bodyNode = new IfStmtNode(tokens);   //TODO un-comment once If node is implemented
        } else {    // is a statement
            bodyNode = new StmtNode(tokens);
        }

    }
    @Override
    public String convertToJott() {
        return bodyNode.convertToJott();
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
