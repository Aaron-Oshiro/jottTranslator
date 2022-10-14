import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Raman Zatsarenko
 * I should probably do assignment before stmt
 */
public class StmtNode implements JottTree{

    private ArrayList<String> types = new ArrayList<>(Arrays.asList("Double", "String", "Integer", "Boolean"));
    private AsmtNode asmt;
    private boolean asmtFlag;
    private FuncCallNode funcCall;
    private boolean funcCallFlag;
    private EndStmtNode endStmt;
    private boolean varDecFlag;
    private VarDecNode varDec;

    public StmtNode(ArrayList<Token> tokens) throws Exception {
        // <type> <id> = ... (asmt)
        if (types.contains(tokens.get(0).getToken()) && tokens.get(2).getToken().equals("=") ||
                (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD && tokens.get(1).getToken().equals("="))) {
            asmt = new AsmtNode(tokens);
        }
        else if (/*types.contains(tokens.get(0).getToken()) && */tokens.get(1).getToken().equals("[")) {
            funcCall = new FuncCallNode(tokens);
            endStmt = new EndStmtNode(tokens);

        }
        else {
            varDec = new VarDecNode(tokens);
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
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
