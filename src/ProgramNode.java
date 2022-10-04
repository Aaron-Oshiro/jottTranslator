import java.util.*;
import java.util.function.Function;
public class ProgramNode implements JottTree{


    private FunctionListNode functionListNode;


    public ProgramNode(ArrayList<Token> tokens)throws Exception{
        functionListNode = new FunctionListNode(tokens);
    }
    //this should not be token type, but too lazy to change it rn
    private TokenType type;
    
    @Override
    public String convertToJott() {
        // I think this node doesn't have any printing, right?
        return functionListNode.convertToJott();
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
