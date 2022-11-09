
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

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        if (!functionTable.containsKey(funcName.convertToJott())) {
            System.err.println("Function " + funcName.convertToJott() + " is not defined");
            return false;
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
