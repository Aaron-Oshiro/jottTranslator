import java.util.*;
public class FunctionListNode implements JottTree{


    private FunctionDefNode functionDefNode;
    private FunctionListNode functionListNode;

    //this should not be token type, but too lazy to change it rn
    private TokenType type;

    public FunctionListNode(FunctionDefNode def, FunctionListNode list){

        functionDefNode = def;
        functionListNode = list;
    }
    
    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        return null;
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
