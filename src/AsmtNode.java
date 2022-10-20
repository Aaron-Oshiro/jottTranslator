import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Raman Zatsarenko
 */
public class AsmtNode implements JottTree{
    private IdNode id;

    private DExprNode dExpr;
    private IExprNode iExpr;
    private StrExprNode sExpr;
    private BExprNode bExpr;
    private ExprNode expr;

    private ArrayList<String> types = new ArrayList<>(Arrays.asList("Double", "String", "Integer", "Boolean"));
    private boolean hasType = false;
    private String type;

    private EndStmtNode endStmt;

    // probably need lookahead of 1 or 2?
    public AsmtNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable) throws Exception{
        String currentTokenStr = tokens.get(0).getToken();
        if (types.contains(currentTokenStr)) {
            hasType = true;
            type = tokens.get(0).getToken();

            // remove type token
            tokens.remove(0);
            // <id>
            //TODO add a check here - make sure the id node has the token type of ID/Keyword!
            id = new IdNode(tokens);
            if (!tokens.get(0).getToken().equals("=")) {
                throw new Exception("Syntax Error: Token "+ tokens.get(0).getToken() + " cannot be parsed into a '=' at " + tokens.get(0).getFilename() + "  line " + tokens.get(0).getLineNum());
            }
            tokens.remove(0); // remove =

            expr = new ExprNode(tokens);
            id.setNull(false);
            id.setType(type);
            endStmt = new EndStmtNode(tokens);
            if (symbolTable.containsKey(id.convertToJott())) {
                throw new Exception("VARIABLE ALREADY IN SYMBOL TABLE");
            } else {
                symbolTable.put(id.convertToJott(), id);
            }

        // <id>
        // <s_expr>
            //sExpr = new StrExprNode(tokens);
        // <id>
        // <i_expr>
            //iExpr = new IExprNode(tokens);
        // <id>
        // <b_expr>
            //bExpr = new BExprNode(tokens);

        }
        // <id> = <*_expr><end_stmt>
        else {
            //TODO add a check here - make sure the id node has the token type of ID/Keyword!
            id = new IdNode(tokens);
            if (!tokens.get(0).getToken().equals("=")) {
                throw new Exception("Syntax Error: Token "+ tokens.get(0).getToken() + " cannot be parsed into a '=' at "  + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
            }
            tokens.remove(0);

            expr = new ExprNode(tokens);
            endStmt = new EndStmtNode(tokens);

        }
    }

    @Override
    public String convertToJott() {
        if (hasType) {
            return type + " " + id.convertToJott() + " = " + expr.convertToJott() + endStmt.convertToJott();
        }
        else {
            return id.convertToJott() + " = " + expr.convertToJott() + endStmt.convertToJott();
        }
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
