import java.util.*;
public class StrExprNode implements JottTree{




    public StrExprNode(ArrayList<Token> tokens)throws Exception{
        Token tokenToCheck = tokens.get(0);

        //waiting until str literal, id and func call are done before I do this one

      
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
