import java.util.ArrayList;
import java.util.HashMap;

public class ValueNode implements JottTree {

    private String value;
    private String type;

    public ValueNode(ArrayList<Token> tokens) throws Exception {
        this.value = tokens.get(0).getToken();

        TokenType typeToCheck = tokens.get(0).getTokenType();

        if(typeToCheck == TokenType.STRING){
            type = "String";
        }
        else if (typeToCheck == TokenType.ID_KEYWORD){
            //i think it can only be a boolean if the token is a keyword... right?!?
            type = "Boolean";
        }
        else{
            if(value.contains(".")){
                type = "Double";
            }
            else{
                type = "Integer";
            }

        }
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
        return value;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython(int t) {
        // System.out.println(value);
        return value;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        return true;
    }

    @Override
    public String toString() {
        return "ValueNode{" +
                "value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
