import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Raman Zatsarenko
 */
public class AsmtNode implements JottTree {
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

    private String fileName;
    private int lineNumber;

    // probably need lookahead of 1 or 2?
    public AsmtNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable) throws Exception {
        String currentTokenStr = tokens.get(0).getToken();
        if (types.contains(currentTokenStr)) {
            hasType = true;
            type = tokens.get(0).getToken();
            fileName = tokens.get(0).getFilename();
            lineNumber = tokens.get(0).getLineNum();

            // remove type token
            tokens.remove(0);
            // <id>
            // TODO add a check here - make sure the id node has the token type of
            // ID/Keyword!
            id = new IdNode(tokens);
            if (!tokens.get(0).getToken().equals("=")) {
                throw new Exception(
                        "Syntax Error: Token " + tokens.get(0).getToken() + " cannot be parsed into a '=' at "
                                + tokens.get(0).getFilename() + "  line " + tokens.get(0).getLineNum());
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
            // sExpr = new StrExprNode(tokens);
            // <id>
            // <i_expr>
            // iExpr = new IExprNode(tokens);
            // <id>
            // <b_expr>
            // bExpr = new BExprNode(tokens);

        }
        // <id> = <*_expr><end_stmt>
        else {
            // TODO add a check here - make sure the id node has the token type of
            // ID/Keyword!
            id = new IdNode(tokens);
            if (!tokens.get(0).getToken().equals("=")) {
                throw new Exception(
                        "Syntax Error: Token " + tokens.get(0).getToken() + " cannot be parsed into a '=' at "
                                + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
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
        } else {
            return id.convertToJott() + " = " + expr.convertToJott() + endStmt.convertToJott();
        }
    }

    @Override
    public String convertToJava() {
        // has the same issue as convertToC with type
        String ret = "";
        if (!expr.getFirstExpr().isFuncCallNull()) {
            if (expr.getFirstExpr().getFuncCall().getFuncName().equals("input")) {
                String scannerName = "scanner_" + JottMain.SCANNERINT;
                String scan = "Scanner " + scannerName + " = new Scanner(System.in);\n" +
                        "System.out.print(" + expr.getFirstExpr().getFuncCall().getParamsNode().getExpressionNode().convertToJava() + ");\n";
                ret += scan;
            }
        }
        if (hasType) {
            ret += type + " ";
        }
        ret += id.convertToJava() + " = " + expr.convertToJava() + endStmt.convertToJava();
        return ret;
    }

    @Override
    public String convertToC() {
        if (hasType) {
            // ISSUE! because 'type' here is a string and not a typeNode
            // cannot call convertToC. if this can't change,
            // will have to include several if statements.
            // (because the type in C is written differently)
            return type + " " + id.convertToC() + " = " + expr.convertToC() + endStmt.convertToC();
        } else {
            return id.convertToC() + " = " + expr.convertToC() + endStmt.convertToC();
        }
    }

    @Override
    public String convertToPython(int t) {
        return id.convertToPython(t) + " = " + expr.convertToPython(t) + endStmt.convertToPython(t);
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        // <id> = <expr><end_stmt>
        if (!hasType) {
            if (!symbolTable.containsKey(id.getId())) {
                System.err.println("Semantic Error: Symbol '" + id
                        + "' was not found in the Symbol Table at File and Line: "  + fileName +":" + lineNumber);
                return false;
            } else {
                // Aaron asked for this
                // System.out.println(id.getType());
                id.setType(expr.getType(functionTable, symbolTable));
                if (id.validateTree(functionTable, symbolTable) && expr.validateTree(functionTable, symbolTable)){

                    if(id.getType().equals(expr.getType(functionTable, symbolTable))){
                    return true;
                }
                    else{
                        System.err.println(
                            "Semantic Error: The types of the two parts of the asmt did not match at File and Line: " + fileName +":" + lineNumber);
                            return false;
                    }
                }
                else {
                   // System.err.println(
                    //        "Semantic Error: Either id or the expression were invalid, OR the types of the two did not match at File and Line: ");
                    return false;
                }
            }
        } else {
            if (id.validateTree(functionTable, symbolTable) && expr.validateTree(functionTable, symbolTable)){
                if(type.equals(expr.getType(functionTable, symbolTable))){
                return true;
            }
            else{
                System.err.println(
                    "Semantic Error: The types of the two parts of the asmt did not match at File and Line: "  + fileName +":" + lineNumber);
                    return false;
            }
            }
            else {
                return false;
            }
        }
    }
}
