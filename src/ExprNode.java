import java.util.ArrayList;
import java.util.HashMap;

public class ExprNode implements JottTree {

    private IdNode id;
    private ValueNode value;
    private FuncCallNode funcCall;

    private ExprNode firstExpr;
    private OpNode op;
    private ExprNode secondExpr;

    public ExprNode(ArrayList<Token> tokens) throws Exception {
        // can be an id, funcCall, value, or expr op expr

        Token t0 = tokens.get(0);
        TokenType tt0 = (tokens.get(0).getTokenType());

        if (tt0 != TokenType.ID_KEYWORD && tt0 != TokenType.NUMBER && tt0 != TokenType.STRING) {
            throw new Exception(
                    "Syntax Error: Token " + t0.getToken() + " cannot be parsed into a value or id at " + t0.getFilename() + " line "
                            + t0.getLineNum());
        }
        firstExpr = new ExprNode(tokens, true);
        if (tokens.get(0).getTokenType() == TokenType.REL_OP || tokens.get(0).getTokenType() == TokenType.MATH_OP) {
            op = new OpNode(tokens);
            secondExpr = new ExprNode(tokens, true);
        }
    }

    private ExprNode(ArrayList<Token> tokens, boolean stop) throws Exception {
        Token t0 = tokens.get(0);
        TokenType tt0 = (tokens.get(0).getTokenType());

        if (tt0 != TokenType.ID_KEYWORD && tt0 != TokenType.NUMBER && tt0 != TokenType.STRING) {
            throw new Exception(
                    "Syntax Error: Token " + t0.getToken() + " cannot be parsed into a value or id at " + t0.getFilename() + " line "
                            + t0.getLineNum());
        }
        TokenType tt1 = (tokens.get(1).getTokenType());

        if (tt0 == TokenType.ID_KEYWORD && tt1 != TokenType.L_BRACKET) {    // if it's not a function call
            id = new IdNode(tokens);

        } else if ((tt0 == TokenType.STRING) || (tt0 == TokenType.NUMBER)) {
            value = new ValueNode(tokens);

        } else {
            funcCall = new FuncCallNode(tokens);
        }
    }

    @Override
    public String convertToJott() {
        if (id != null) {   // just an id_keyword
            return id.convertToJott();
        } else if (value != null) { // just a value/string/number
            return value.convertToJott();
        } else if (funcCall != null){   // just a function call
            return funcCall.convertToJott();
        } else if (secondExpr != null) {    // if there is a second Expression, then it's an (expr op expr) expression
            return firstExpr.convertToJott() + " " + op.convertToJott() + " " + secondExpr.convertToJott();
        } else {    // it's just the first expression, no (expr op expr) expression
            return firstExpr.convertToJott();
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
    public String convertToPython(int t) {
        return null;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {

        // ALSO CHECK FOR TYPES AND THEIR EQUALITIES WHEN NEEDED!!! TODO!!!
        if (id != null) {   // just an id_keyword - type don't matter.
            return id.validateTree(functionTable, symbolTable);
        } else if (value != null) { // just a value/string/number - type don't matter.
            return value.validateTree(functionTable, symbolTable);
        } else if (funcCall != null) {   // just a function call - type don't matter.
            return funcCall.validateTree(functionTable, symbolTable);
        } else if (secondExpr != null) {    // if there is a second Expression, then it's an (expr op expr) expression - type DO matter!!!
            // make sure both trees are valid. then make sure their types equal each other.
            return (firstExpr.validateTree(functionTable, symbolTable) && op.validateTree(functionTable, symbolTable) && secondExpr.validateTree(functionTable, symbolTable)) && (firstExpr.getType(functionTable, symbolTable).equals(secondExpr.getType(functionTable, symbolTable)));
        } else {    // it's just the first expression, no (expr op expr) expression - type don't matter
            return firstExpr.validateTree(functionTable, symbolTable);
        }
    }


    // this is pseduocode, prolly wont work until symbol table is up and running.
     public String getType(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable){
        if (id != null) {   // just an id_keyword - type don't matter.
            
            if(!symbolTable.containsKey(id.getId())){
               //todo uncomment this once others add exceptions?
                // throw new Exception("Semantic Error\nid " + id.getId() + " has not yet been declared, yet is used as a variable.\n");
            }
            return symbolTable.get(id.getId()).getType();
        } else if (value != null) { // just a value/string/number - type don't matter.
            return value.getType();
        } else if (funcCall != null){   // just a function call - type don't matter.
            return functionTable.get(funcCall.convertToJott()).getType(functionTable);
        } else if (secondExpr == null) {
            return firstExpr.getType(functionTable,symbolTable);
        } else {    // use op to determine which it should be a type of.
            if( (op.getOperator().equals(">")) || (op.getOperator().equals(">=")) ||(op.getOperator().equals("<"))||(op.getOperator().equals("<="))||(op.getOperator().equals("==")) || (op.getOperator().equals("!="))){
                //if relop, then exprs can ONLY be bool exprs. todo make sure this matches our string type format.
                return "Boolean";
            }
            else{
                //should be int or double if op is an math op
                return firstExpr.getType(functionTable,symbolTable);
            }
             
        }

     }
}
