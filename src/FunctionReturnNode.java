import java.util.ArrayList;

public class FunctionReturnNode implements JottTree {

    private TypeNode returnType;
    private boolean returnTypeFlag = false;
    private boolean voidFlag = false;

    public FunctionReturnNode(ArrayList<Token> tokens) throws Exception {
        String t0 = tokens.get(0).getToken();
        if (t0.equals("Void")) {
            voidFlag = true;
            tokens.remove(0); // removes 'Void'
        } else {
            returnTypeFlag = true;
            returnType = new TypeNode(tokens);
        }
    }

    @Override
    public String convertToJott() {
        if (voidFlag) {
            return "Void";
        } else {
            return returnType.convertToJott();
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
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        if (voidFlag == true) {
            return true;
        }
        return returnType.validateTree();
    }
}
