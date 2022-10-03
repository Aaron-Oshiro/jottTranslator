import java.util.*;
public class DblNode implements JottTree{


    //SignNode sign

    //Digit

    private Double value;

    // I AM WAITING FOR SIGN AND DIGIT BEFORE I DO THIS ONE

    public DblNode(ArrayList<Token> tokens)throws Exception{
        Token tokenToCheck = tokens.get(0);

    try
    {
        value = Double.parseDouble(tokenToCheck.getToken());
        
        if(!tokenToCheck.getToken().contains(".")){
            throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a Double (Did not have decimal) at line " + tokenToCheck.getLineNum());
        }
        else{

        tokens.remove(0);
        }
    }
    catch(Exception e)
    {
        //not a double
        //throw error if we were expecting a double

        throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a Double at line " + tokenToCheck.getLineNum());
    }

      
    }
    
    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        return "";
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
