import java.util.ArrayList;
import java.util.HashMap;

public class VarDecNode implements JottTree {
    private TypeNode type;
    private IdNode id;
    private EndStmtNode endStmt;

    public VarDecNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable) throws Exception {
        type = new TypeNode(tokens);
        id = new IdNode(tokens);
        endStmt = new EndStmtNode(tokens);
        if (symbolTable.containsKey(id.convertToJott())) {
            throw new Exception("VARIABLE IS ALREADY DEFINED");
        } else {
            id.setType(type.convertToJott());
            id.setNull(true);
            symbolTable.put(id.convertToJott(), id);
        }
    }

    @Override
    public String convertToJott() {
        return type.convertToJott() + id.convertToJott() + endStmt.convertToJott();
    }

    @Override
    public String convertToJava() {
        return type.convertToJava() + id.convertToJava() + endStmt.convertToJava();
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython(int t) {
        return "";
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        return true;
    }
}
