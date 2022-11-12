import java.util.ArrayList;
import java.util.HashMap;

public class BodyStmtNode implements JottTree {

    private JottTree bodyStmtNode;

    public BodyStmtNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable) throws Exception {
        // body statement can either be a while loop, an if statement, or a statement
        if (tokens.get(0).getToken().equals("while")) { // is a while loop
            bodyStmtNode = new WhileLoopNode(tokens, symbolTable);
        } else if (tokens.get(0).getToken().equals("if")) { // is an if statement
            bodyStmtNode = new IfStmtNode(tokens, symbolTable);
        } else { // is a statement
            bodyStmtNode = new StmtNode(tokens, symbolTable);
        }

    }

    @Override
    public String convertToJott() {
        return bodyStmtNode.convertToJott();
    }

    @Override
    public String convertToJava() {
        return "\n" + bodyStmtNode.convertToJava();
    }

    @Override
    public String convertToC() {
        return bodyStmtNode.convertToJott();
    }

    @Override
    public String convertToPython(int t) {
        StringBuilder tabs = new StringBuilder();
        tabs.append("\t".repeat(Math.max(0, t)));
        return "\n" + tabs + bodyStmtNode.convertToPython(t);
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        return bodyStmtNode.validateTree(functionTable, symbolTable);
    }

    public boolean isReturnable(String type) {

        // func call can while make no difference in the returnable status. we only need
        // check the if node.
        if (bodyStmtNode instanceof IfStmtNode) {

            IfStmtNode testReturnable = (IfStmtNode) bodyStmtNode;
            return testReturnable.isReturnable(type);

        }
        return false;
    }
}
