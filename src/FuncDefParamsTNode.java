import java.util.ArrayList;
import java.util.HashMap;

/**
 * FuncDefParamsNode
 *
 * @author Raman Zatsarenko
 */
public class FuncDefParamsTNode implements JottTree {

    private boolean hasParamsT;
    private IdNode id;
    private TypeNode type;
    private FuncDefParamsTNode funcDefParamsT;

    public FuncDefParamsTNode(ArrayList<Token> tokens) throws Exception {
        if(!tokens.get(0).getToken().equals(",")) hasParamsT = false;
        else {
            hasParamsT = true;
            // remove comma
            tokens.remove(0);
            id = new IdNode(tokens);
            if(!tokens.get(0).getToken().equals(":")) {
                throw new Exception("Syntax Error: Token "+ tokens.get(0).getToken() + " cannot be parsed into a : at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
            }
            tokens.remove(0);
            type = new TypeNode(tokens);
            funcDefParamsT = new FuncDefParamsTNode(tokens);
        }
    }

    public boolean hasParamsT() {
        return hasParamsT;
    }

    public TypeNode getType() {
        return type;
    }

    public FuncDefParamsTNode getFuncDefParamsT() {
        return funcDefParamsT;
    }

    public int getLength() {
        if (hasParamsT) {
            return 1 + funcDefParamsT.getLength();
        }
        return 0;
    }

    @Override
    public String convertToJott() {
        if(!hasParamsT) return "";
        else {
            return "," + id.convertToJott() + ":" + type.convertToJott() + funcDefParamsT.convertToJott();
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
        if (!hasParamsT) return "";
        else {
            return ", " + id.convertToPython(t) + funcDefParamsT.convertToPython(t);
        }
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        if (!hasParamsT) return true;
        else return id.validateTree(functionTable, symbolTable) && type.validateTree(functionTable, symbolTable) &&
                funcDefParamsT.validateTree(functionTable, symbolTable);
    }

}
