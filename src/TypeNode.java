import java.util.*;

public class TypeNode implements JottTree {

    private String type;

    public TypeNode(ArrayList<Token> tokens) throws Exception {
        Token tokenToCheck = tokens.get(0);

        if (tokenToCheck.getToken().equals("Double") || tokenToCheck.getToken().equals("Integer")
                || tokenToCheck.getToken().equals("String") || tokenToCheck.getToken().equals("Boolean")) {
            type = tokenToCheck.getToken();
            tokens.remove(0);
        } else {
            throw new Exception("Syntax Error: Token " + tokenToCheck.getToken() + " cannot be parsed into a Type at "
                    + tokenToCheck.getFilename() + " line " + tokenToCheck.getLineNum());
        }
    }

    @Override
    public String convertToJott() {
        return type;
    }

    @Override
    public String convertToJava() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String convertToC() {
        if (type.equals("Integer")) {
            return "int";
        } else if (type.equals("Double")) {
            return "double";
        } else if (type.equals("String")) {
            return "char const*";
        } else {
            return "bool";
        }
    }

    @Override
    public String convertToPython(int t) {
        return type;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        // TODO Auto-generated method stub
        return true;
    }

}
