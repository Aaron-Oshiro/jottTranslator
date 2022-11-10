
/**
 * func_call Node
 *
 * @author Aaron Oshiro
 */

import java.util.ArrayList;
import java.util.HashMap;

public class FuncCallNode implements JottTree {

    private IdNode funcName;
    private ParamsNode paramsNode;

    public FuncCallNode(ArrayList<Token> tokens) throws Exception {
        // Needs to check for id, '[' , params, and ']'
        if (!Character.isLetter(tokens.get(0).getToken().charAt(0))) {
            throw new Exception("Syntax Error: Token " + tokens.get(0).getToken() + " needs to start with a letter at "
                    + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        this.funcName = new IdNode(tokens); // removes the id_keyword token

        if (!tokens.get(0).getToken().equals("[")) {
            Token thisToken = tokens.get(0);
            throw new Exception("Syntax Error: Token " + thisToken.getToken() + " cannot be parsed into a [ at "
                    + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0); // removes the '['

        paramsNode = new ParamsNode(tokens);

        if (!tokens.get(0).getToken().equals("]")) {
            Token thisToken = tokens.get(0);
            throw new Exception("Syntax Error: Token " + thisToken.getToken() + " cannot be parsed into a ] at "
                    + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0); // removes the ']'
    }

    public String getFuncName() {
        return funcName.getId();
    }

    @Override
    public String convertToJott() {
        return funcName.convertToJott() + "[" + paramsNode.convertToJott() + "]";
    }

    @Override
    public String convertToJava() {
        return null;
    }

    @Override
    public String convertToC() {
        return funcName.convertToC() + "(" + paramsNode.convertToC() + ")";
    }

    @Override
    public String convertToPython(int t) {
        return funcName.convertToPython(t) + "(" + paramsNode.convertToPython(t) + ")";
    }

    public boolean validateBuiltIn(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        if (funcName.convertToJott().equals("print")) {
            if (!paramsNode.isEmpty()) {
                // since print only accepts one argument
                 if ((paramsNode.getExpressionNode().getFirstExpr().getId() != null) &&
                         (!symbolTable.containsKey(paramsNode.getExpressionNode().getFirstExpr().getId().getId()) &&
                                 !functionTable.containsKey(paramsNode.getExpressionNode().getFirstExpr().getId().getId()))){
                     System.err.println("argument " + paramsNode.getExpressionNode().getFirstExpr().getId().getId() +
                             " undefined for built-in function print");
                     return false;
                 }
                 return true;
            }
            else {
                System.err.println("built-in function print called with no parameters");
            }
        }
        else if (funcName.convertToJott().equals("concat")) {
            if (!paramsNode.getParamsTNode().getParamsTNode().isEmpty()) {
                System.err.println("built-in function concat expects two strings as arguments");
                return false;
            }
            System.out.println(paramsNode.getExpressionNode().getFirstExpr().getValue());
            System.out.println(paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getValue());
        }
        return false;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        if (!functionTable.containsKey(funcName.convertToJott())) {     // tries to call function that does not exist
            System.err.println("Function " + funcName.convertToJott() + " is not defined");
            return false;
        }
        // check if the function is built-in
        if (funcName.convertToJott().equals("print") || funcName.convertToJott().equals("concat") ||
                funcName.convertToJott().equals("length") || funcName.convertToJott().equals("input")) {
            return validateBuiltIn(functionTable, symbolTable);
        }
        // calls a function with wrong number of params
        if (functionTable.get(funcName.convertToJott()).getFuncDefParamsNode().getLength() != this.paramsNode
                .getLength()) {
            System.err.println("Function " + funcName.convertToJott() + " is not given correct number of parameters");
            return false;
        }
        if (this.paramsNode.getLength() == 0) {
            return true;
        }
        // Checks types of each functionDefParam to the params
        FuncDefParamsNode funcDefParams = functionTable.get(funcName.convertToJott()).getFuncDefParamsNode();
        ParamsNode funcParams = this.paramsNode;
        // System.out.println(funcDefParams.convertToJott());
        // System.out.println(funcParams.convertToJott());
        if (funcDefParams.getType().convertToJott()
                .equals(funcParams.getExpressionNode().getType(functionTable, symbolTable))) {
            FuncDefParamsTNode funcDefT = funcDefParams.getFuncDefParamsT();
            ParamsTNode paramsT = funcParams.getParamsTNode();
            while (funcDefT.hasParamsT()) {
                if (!funcDefT.getType().convertToJott()
                        .equals(paramsT.getExpressionNode().getType(functionTable, symbolTable))) {
                    return false;
                }
                funcDefT = funcDefT.getFuncDefParamsT();
                paramsT = paramsT.getParamsTNode();
            }
        } else {
            return false;
        }
        return true;
    }
}
