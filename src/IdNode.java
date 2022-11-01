import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Raman Zatsarenko
 */
public class IdNode implements JottTree{

    private String id;
    private String type;
    private boolean isNull;

    public IdNode(ArrayList<Token> tokens) throws Exception{
        this.id = tokens.get(0).getToken();
        HashSet<String> keywords = new HashSet<>(Arrays.asList("while", "if", "default", "this", "new", "class", "try", "this"));
        if (keywords.contains(this.id)) {
            throw new Exception("Semantic Error\nid " + this.id + " is a keyword and cannot be used\n" +
                    tokens.get(0).getFilename() + ":" + tokens.get(0).getLineNum());
        }

        tokens.remove(0);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    @Override
    public String convertToJott() {
        return id;
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
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        return false;
    }

    @Override
    public String toString() {
        return "IdNode{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", isNull=" + isNull +
                '}';
    }
}
