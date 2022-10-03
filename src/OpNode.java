import java.util.*;
public class OpNode implements JottTree{


    private String operation;

    public OpNode(ArrayList<Token> tokens)throws Exception{
        Token tokenToCheck = tokens.get(0);

        if(tokenToCheck.getToken().equals("+")||tokenToCheck.getToken().equals("-")||tokenToCheck.getToken().equals("/")||tokenToCheck.getToken().equals("*")){
            operation = tokenToCheck.getToken();
            tokens.remove(0);
        }
        else{
            throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a MathOp at line " + tokenToCheck.getLineNum());
        }
    }
    
    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        return operation;
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