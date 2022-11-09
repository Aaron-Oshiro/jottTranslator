import java.util.*;
import java.util.function.Function;

public class ProgramNode implements JottTree {

    private FunctionListNode functionListNode;

    private HashMap<String, FunctionDefNode> functionTable = new HashMap<>();

    public ProgramNode(ArrayList<Token> tokens) throws Exception {

        functionTable.put("print", null);
        functionTable.put("concat", null);
        functionTable.put("length", null);

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
        return "#include <stdio.h>\n#include <string.h>\n" +
                "#include <stdlib.h>\n" + functionListNode.convertToC();
    }

    @Override
    public String convertToPython(int t) {
        return functionListNode.convertToPython(t);
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        return functionListNode.validateTree(this.functionTable, null);
    }

}
