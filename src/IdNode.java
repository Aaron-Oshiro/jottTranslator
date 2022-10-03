import java.util.ArrayList;

/**
 * @author Raman Zatsarenko
 */
public class IdNode implements JottTree{
    private String id;
    public IdNode(ArrayList<Token> tokens) throws Exception{
        this.id = tokens.get(0).getToken();
        tokens.remove(0);
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
