import java.util.ArrayList;

public class VarDecNode implements JottTree {
    private TypeNode type;
    private IdNode id;
    private EndStmtNode endStmt;

    public VarDecNode(ArrayList<Token> tokens) throws Exception {
        type = new TypeNode(tokens);
        id = new IdNode(tokens);
        endStmt = new EndStmtNode(tokens);
    }

    @Override
    public String convertToJott() {
        return type.convertToJott() + id.convertToJott() + endStmt.convertToJott();
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
