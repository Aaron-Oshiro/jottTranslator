import java.util.ArrayList;

/**
 * @author Raman Zatsarenko
 */
public class IdNode implements JottTree{

    private String id;
    private String type;
    private boolean isNull;

    public IdNode(ArrayList<Token> tokens) throws Exception{
        this.id = tokens.get(0).getToken();
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
    public boolean validateTree() {
        return false;
    }
}
