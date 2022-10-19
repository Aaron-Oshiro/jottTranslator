import java.util.*;
import java.util.function.Function;
public class ProgramNode implements JottTree{


    private FunctionListNode functionListNode;

    private HashMap<String, FunctionDefNode> functionTable = new HashMap<>();


    public ProgramNode(ArrayList<Token> tokens)throws Exception{
        functionListNode = new FunctionListNode(tokens, functionTable);
    }

    @Override
    public String convertToJott() {
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
