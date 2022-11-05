import java.util.ArrayList;
import java.util.HashMap;

public class ValueNode implements JottTree {

    private String value;
    private String type;

    public ValueNode(ArrayList<Token> tokens) throws Exception {
        this.value = tokens.get(0).getToken();
        tokens.remove(0);
    }

    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String convertToJott() {
        return value;
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
        return true;
    }
}
