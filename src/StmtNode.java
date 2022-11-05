import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Raman Zatsarenko
 * I should probably do assignment before stmt
 */
public class StmtNode implements JottTree{

    private ArrayList<String> types = new ArrayList<>(Arrays.asList("Double", "String", "Integer", "Boolean"));
    private AsmtNode asmt;
    private FuncCallNode funcCall;
    private EndStmtNode endStmt;
    private VarDecNode varDec;

    public StmtNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable) throws Exception {
        // <type> <id> = ... (asmt)
        if (types.contains(tokens.get(0).getToken()) && tokens.get(2).getToken().equals("=") ||
                (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD && tokens.get(1).getToken().equals("="))) {
            asmt = new AsmtNode(tokens, symbolTable);
        }
        else if (tokens.get(1).getToken().equals("[")) {
            funcCall = new FuncCallNode(tokens);
            endStmt = new EndStmtNode(tokens);

        }
        else {
            varDec = new VarDecNode(tokens, symbolTable);
        }

    }

    @Override
    public String convertToJott() {
        if (asmt != null) return asmt.convertToJott();
        else if (varDec != null) return varDec.convertToJott();
        else if (funcCall != null) return funcCall.convertToJott() + endStmt.convertToJott();
        else return "Could not convert to Jott";
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
        return null;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        if (asmt != null) return asmt.validateTree(functionTable, symbolTable);
        else if (varDec != null) return varDec.validateTree(functionTable, symbolTable);
        else return funcCall.validateTree(functionTable, symbolTable) && endStmt.validateTree(functionTable, symbolTable);
    }
}
