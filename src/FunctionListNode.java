import java.util.*;
public class FunctionListNode implements JottTree{


    private FunctionDefNode functionDefNode;
    private FunctionListNode functionListNode;
    private boolean hasFunctionDefinition;

    //this should not be token type, but too lazy to change it rn
    private TokenType type;

    public FunctionListNode(ArrayList<Token> tokens) throws Exception{
        
        if(tokens.size() == 0){
            //case where func list = epsilon
            hasFunctionDefinition = false;
        }
        else{
            //case where func list is not epsilon. It is then func def and another func list
            hasFunctionDefinition = true;
        functionDefNode = new FunctionDefNode(tokens);

        functionListNode = new FunctionListNode(tokens);
        }

    }
    
    @Override
    public String convertToJott() {
        // I think this node doesn't have any printing, right?
        if(!hasFunctionDefinition){
            return "";
        }
        return functionDefNode.convertToJott() + functionListNode.convertToJott();
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
