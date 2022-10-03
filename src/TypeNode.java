import java.util.*;
public class TypeNode implements JottTree{


    private String type;

    public TypeNode(ArrayList<Token> tokens)throws Exception{
        Token tokenToCheck = tokens.get(0);

        if(tokenToCheck.getToken().equals("Double")||tokenToCheck.getToken().equals("Integer")||tokenToCheck.getToken().equals("String")||tokenToCheck.getToken().equals("Boolean")){
            type = tokenToCheck.getToken();
            tokens.remove(0);
        }
        else{
            throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a Type at line " + tokenToCheck.getLineNum());
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        return false;
    }

    
    
}