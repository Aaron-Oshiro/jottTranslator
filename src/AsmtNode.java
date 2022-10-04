import java.util.ArrayList;
import java.util.Arrays;

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
    public AsmtNode(ArrayList<Token> tokens) throws Exception{
        String currentTokenStr = tokens.get(0).getToken();
        if (types.contains(currentTokenStr)) {
            hasType = true;
            type = tokens.get(0).getToken();
            switch (currentTokenStr) {
                case "Double":
                    // remove type token
                    tokens.remove(0);
                    // <id>
                    id = new IdNode(tokens);
                    if (!tokens.get(0).getToken().equals("=")) {
                        throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a '=' at line " + tokens.get(0).getLineNum());
                    }
                    tokens.remove(0);
                    // <d_expr>
                    dExpr = new DExprNode(tokens);
                    endStmt = new EndStmtNode(tokens);
                    break;

                case "String":
                    // remove type token
                    tokens.remove(0);
                    // <id>
                    id = new IdNode(tokens);
                    if (!tokens.get(0).getToken().equals("=")) {
                        throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a '=' at line " + tokens.get(0).getLineNum());
                    }
                    tokens.remove(0);
                    // <s_expr>
                    sExpr = new StrExprNode(tokens);
                    endStmt = new EndStmtNode(tokens);
                    break;

                case "Integer":
                    // remove type token
                    tokens.remove(0);
                    // <id>
                    id = new IdNode(tokens);
                    if (!tokens.get(0).getToken().equals("=")) {
                        throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a '=' at line " + tokens.get(0).getLineNum());
                    }
                    tokens.remove(0);
                    // <i_expr>
                    iExpr = new IExprNode(tokens);
                    endStmt = new EndStmtNode(tokens);
                    break;

                case "Boolean":
                    // remove type token
                    tokens.remove(0);
                    // <id>
                    id = new IdNode(tokens);
                    if (!tokens.get(0).getToken().equals("=")) {
                        throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a '=' at line " + tokens.get(0).getLineNum());
                    }
                    tokens.remove(0);
                    // <b_expr>
                    bExpr = new BExprNode(tokens);
                    endStmt = new EndStmtNode(tokens);
                    break;

                default:
                    throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a valid <asmt> at line " + tokens.get(0).getLineNum());
            }
        }
        // <id> = <*_expr><end_stmt>
        else {
            id = new IdNode(tokens);
            if (!tokens.get(0).getToken().equals("=")) {
                throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a '=' at line " + tokens.get(0).getLineNum());
            }
            tokens.remove(0);

            expr = new ExprNode(tokens);
            endStmt = new EndStmtNode(tokens);

        }
    }

    @Override
    public String convertToJott() {
        if (hasType) {
            switch (type) {
                case "Double": return type + id.convertToJott() + " = " + dExpr.convertToJott() + endStmt.convertToJott();
                case "String": return type + id.convertToJott() + " = " + sExpr.convertToJott() + endStmt.convertToJott();
                case "Integer" : return type + id.convertToJott() + " = " + iExpr.convertToJott() + endStmt.convertToJott();
                case "Boolean" : return type + id.convertToJott() + " = " + bExpr.convertToJott() + endStmt.convertToJott();

                default:
                   return "Unable to convert to Jott";
            }
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
