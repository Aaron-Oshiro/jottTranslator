import java.util.ArrayList;
import java.util.HashMap;

public class IfStmtNode implements JottTree {
    private ExprNode expr;
    private BodyNode body;
    private ElseIfNode elseIfLst;
    private ElseNode lse;

    public IfStmtNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable) throws Exception {
        Token t0 = tokens.get(0);
        if (!t0.getToken().equals("if")) {
            throw new Exception("Syntax Error: Token " + t0.getToken() + " cannot be parsed into 'if' at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        } else {
            tokens.remove(0);
            t0 = tokens.get(0);
            if (!t0.getToken().equals("[")) {
                throw new Exception("Syntax Error: Token " + t0.getToken() + " cannot be parsed into a [ at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
            }
            tokens.remove(0);

            expr = new ExprNode(tokens);

            t0 = tokens.get(0);
            if (!t0.getToken().equals("]")) {
                throw new Exception("Syntax Error: Token " + t0.getToken() + " cannot be parsed into a ] at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
            }
            tokens.remove(0);
            t0 = tokens.get(0);
            if (!t0.getToken().equals("{")) {
                throw new Exception("Syntax Error: Token " + t0.getToken() + " cannot be parsed into a { at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
            }
            tokens.remove(0);
            body = new BodyNode(tokens, symbolTable);
            t0 = tokens.get(0);
            if (!t0.getToken().equals("}")) {
                throw new Exception("Syntax Error: Token " + t0.getToken() + " cannot be parsed into a } at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
            }
            tokens.remove(0);
            elseIfLst = new ElseIfNode(tokens, symbolTable);
            lse = new ElseNode(tokens, symbolTable);
        }
    }

    @Override
    public String convertToJott() {
        return "if[" + expr.convertToJott() + "]" + "{" + body.convertToJott() + "}"
                + elseIfLst.convertToJott() + lse.convertToJott();
    }

    @Override
    public String convertToJava() {
        return "if (" + expr.convertToJava() + ") {" + body.convertToJava() + "}"
                + elseIfLst.convertToJava() + lse.convertToJava();
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython(int t) {
        return "if " + expr.convertToPython(t) + ": " + body.convertToPython(t+1)
                 + elseIfLst.convertToPython(t) + lse.convertToPython(t);
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        return true;
    }

    public boolean isReturnable(String type,HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable){
        //this is true only if the body is returnable, and all elseIfs have returnable, and there is an else with a returnable value.


        
        return body.isReturnable(type, functionTable, symbolTable) && elseIfLst.isReturnable(type, functionTable, symbolTable) && lse.isReturnable(type, functionTable, symbolTable);
    }
}
