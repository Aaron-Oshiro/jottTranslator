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
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython(int t) {
        String type = id.getType();
        String ret = id.convertToPython(t) + " = ";
        switch(type) {
            case "String":
                ret += "\"\"";
                break;
            case "Integer":
                ret += "0";
                break;
            case "Boolean":
                ret += "True";
                break;
            case "Double":
                ret += "0.0";
                break;
            default:
                // do nothing ig
                break;
        }
        ret += endStmt.convertToPython(t);
        return ret;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        return false;
    }
}
