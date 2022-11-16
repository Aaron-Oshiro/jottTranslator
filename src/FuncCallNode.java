
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

    private String fileName;
    private int lineNumber;

    public FuncCallNode(ArrayList<Token> tokens) throws Exception {
        // Needs to check for id, '[' , params, and ']'
        if (!Character.isLetter(tokens.get(0).getToken().charAt(0))) {
            throw new Exception("Syntax Error: Token " + tokens.get(0).getToken() + " needs to start with a letter at "
                    + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        fileName = tokens.get(0).getFilename();
        lineNumber = tokens.get(0).getLineNum();
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
        return funcName.convertToJava() + "(" + paramsNode.convertToJava() + ")";
    }

    @Override
    public String convertToC() {
        if (funcName.isPrint()) {
            return funcName.convertToC() + "(" + paramsNode.convertToCPrint() + ")";
        }
        return funcName.convertToC() + "(" + paramsNode.convertToC() + ")";
    }

    @Override
    public String convertToPython(int t) {
        return funcName.convertToPython(t) + "(" + paramsNode.convertToPython(t) + ")";
    }

    public boolean validateBuiltIn(HashMap<String, FunctionDefNode> functionTable,
            HashMap<String, IdNode> symbolTable) {
        if (funcName.convertToJott().equals("print")) {
            if (!paramsNode.isEmpty()) {
                // since print only accepts one argument
                if ((paramsNode.getExpressionNode().getFirstExpr().getId() != null) &&
                        (!symbolTable.containsKey(paramsNode.getExpressionNode().getFirstExpr().getId().getId()) &&
                                !functionTable
                                        .containsKey(paramsNode.getExpressionNode().getFirstExpr().getId().getId()))) {
                    System.err.println("argument " + paramsNode.getExpressionNode().getFirstExpr().getId().getId() +
                            " undefined for built-in function print at file and line: " + fileName + ": " + lineNumber );
                    return false;
                }
                return paramsNode.validateTree(functionTable, symbolTable);
            } else {
                System.err.println("built-in function print called with no parameters at file and line: "  + fileName +": " +  lineNumber);
            }
        } else if (funcName.convertToJott().equals("concat")) {
            boolean firstParamValid = false;
            boolean secondParamValid = false;
            if (!paramsNode.getParamsTNode().getParamsTNode().isEmpty()) {
                System.err.println("built-in function concat expects two strings as arguments");
                return false;
            } else {
                // check to see if the first param is a function call or a value
                // id and func call is null, so we have a value like "foo"
                if ((paramsNode.getExpressionNode().getFirstExpr().getId() == null) && (paramsNode.getExpressionNode().getFirstExpr().getFuncCall() == null)) {
                    if (paramsNode.getExpressionNode().getFirstExpr().getValue().getType().equals("String")) {
                        firstParamValid = true;
                    }
                }
                // id is not null == func call like foo[x] or just var
                // check that the function is defined & returns a String
                else {
                    if (!paramsNode.getExpressionNode().getFirstExpr().isFuncCallNull() && (functionTable.containsKey(paramsNode.getExpressionNode().getFirstExpr().getFuncCall().getFuncName()) /*||functionTable.containsKey(paramsNode.getExpressionNode().getFirstExpr().getId().getId())) &&
                            functionTable.get(paramsNode.getExpressionNode().getFirstExpr().getId().getId())
                                    .getFunctionReturnNode().getReturnType().convertToJott().equals("String"))*/
                    && functionTable.get(paramsNode.getExpressionNode().getFirstExpr().getFuncCall().getFuncName()).getFunctionReturnNode().getReturnType().convertToJott().equals("String")))
                    {
                        firstParamValid = paramsNode.validateTree(functionTable, symbolTable);
                    } else {
                        System.out.println(paramsNode.getExpressionNode().getFirstExpr().getFuncCall().getFuncName());
                        // make sure variable is defined & type is string
                        if (symbolTable.containsKey(paramsNode.getExpressionNode().getFirstExpr().getId().getId()) &&
                                symbolTable.get(paramsNode.getExpressionNode().getFirstExpr().getId().getId()).getType()
                                        .equals("String")) {
                            firstParamValid = true;
                        } else {
                            System.err.println("argument " + paramsNode.getExpressionNode().getFirstExpr().getId().getId() +
                                    " is not a String value or a variable of type String at file and line: " +fileName + ": " + lineNumber);
                        }
                    }
                }
                // same checks for second param
                if ((paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getId() == null) && (paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getFuncCall() == null)) {;
                    if (paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getValue().getType()
                            .equals("String")) {
                        secondParamValid = true;
                    }
                } else {
                    if (!paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().isFuncCallNull() && (functionTable.containsKey(paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getFuncCall().getFuncName()))
                            && functionTable
                            .containsKey(paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getFuncCall().getFuncName())
                            &&
                            functionTable
                                    .get(paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getFuncCall().getFuncName())
                                    .getFunctionReturnNode().getReturnType().convertToJott().equals("String")) {
                        secondParamValid = paramsNode.validateTree(functionTable, symbolTable);
                    } else {
                        if (symbolTable
                                .containsKey(
                                        paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getId().getId())
                                &&
                                symbolTable.get(
                                        paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getId().getId())
                                        .getType().equals("String")) {
                            secondParamValid = true;
                        } else {
                            System.err.println("argument " + paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getId().getId() +
                                    " is not a String value or a variable of type String at file and line: " +fileName + ": " + lineNumber);
                        }
                    }
                }
                return firstParamValid && secondParamValid;
            }
        } else if (funcName.convertToJott().equals("length")) {
            if (!paramsNode.getParamsTNode().isEmpty()) {
                System.err.println("Error: built-in function length expects only one parameter");
                return false;
            }
            if (paramsNode.getExpressionNode().getFirstExpr().getId() == null) {
                if (paramsNode.getExpressionNode().getFirstExpr().getValue().getType().equals("String")) {
                    return true;
                } else {
                    System.err.println("Error: the input param should be a String for built-in function length");
                    return false;
                }
            } else {
                // make sure variable is defined & type is string
                if (symbolTable.containsKey(paramsNode.getExpressionNode().getFirstExpr().getId().getId()) &&
                        symbolTable.get(paramsNode.getExpressionNode().getFirstExpr().getId().getId()).getType()
                                .equals("String")) {
                    return true;
                } else {
                    System.err.println("Error: the input param should be a String for built-in function length");
                    return false;
                }
            }
        } else if (funcName.convertToJott().equals("input")) {
            boolean firstParamValid = false;
            boolean secondParamValid = false;

            // input params can be variables or raw values
            // check if it's a raw value
            if (paramsNode.getExpressionNode().getFirstExpr().getId() == null) {
                if (paramsNode.getExpressionNode().getFirstExpr().getValue().getType().equals("String")) {
                    firstParamValid = true;
                } else {
                    System.err.println("Error: the first param for input should be a String");
                }
            } else {
                // make sure variable is defined & type is string
                if (symbolTable.containsKey(paramsNode.getExpressionNode().getFirstExpr().getId().getId()) &&
                        symbolTable.get(paramsNode.getExpressionNode().getFirstExpr().getId().getId()).getType()
                                .equals("String")) {
                    firstParamValid = true;
                } else {
                    System.err.println("Error: the first param for input should be a String ");
                }
            }

            // same checks but for second input
            if (paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getId() == null) {
                if (paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getValue().getType()
                        .equals("Integer")) {
                    secondParamValid = true;
                } else {
                    System.err.println("Error: the second param for input should be an Integer");
                }
            } else {
                if (symbolTable
                        .containsKey(paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getId().getId()) &&
                        symbolTable.get(paramsNode.getParamsTNode().getExpressionNode().getFirstExpr().getId().getId())
                                .getType().equals("Integer")) {
                    secondParamValid = true;
                } else {
                    System.err.println("Error: the second param for input should be an Integer");
                }
            }
            return firstParamValid && secondParamValid;
        }
        return false;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        if (!functionTable.containsKey(funcName.convertToJott())) { // tries to call function that does not exist
            System.err.println("Semantic Error: Function " + funcName.convertToJott() + " is not defined at file and line : " + fileName +":" + lineNumber);
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
            System.err.println("Semantic Error: Function " + funcName.convertToJott()
                    + " is not given correct number of parameters at file and line: "+ fileName +":" + lineNumber);
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
                    System.err.println(
                            "Semantic Error: The function's accepted parameters and the given parameters are not of the same type at file and line: "+ fileName +":" + lineNumber);
                    return false;
                }
                funcDefT = funcDefT.getFuncDefParamsT();
                paramsT = paramsT.getParamsTNode();
            }
        } else {
            System.err.println(
                    "Semantic Error: The expected parameter types do not match the types of the given parameters in the function call at file and line: "+ fileName +":" + lineNumber);
            return false;
        }
        return true;
    }
}
