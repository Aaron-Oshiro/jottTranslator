import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class OpNode implements JottTree {

    private String operator;

    public OpNode(ArrayList<Token> tokens) throws Exception{
        this.operator = tokens.get(0).getToken();
        tokens.remove(0);
    }

    public String getOperator(){
        return operator;
    }
    @Override
    public String convertToJott() {
        return operator;
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
        HashSet<String> operators = new HashSet<>(Arrays.asList(">", ">=", "<", "<=", "==", "!=", "+", "-", "/", "*"));
        return operators.contains(operator);
    }
}
