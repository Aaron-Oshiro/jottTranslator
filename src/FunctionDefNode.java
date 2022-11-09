import java.util.*;
public class FunctionDefNode implements JottTree{

    private IdNode idNode;

    private FuncDefParamsNode funcDefParamsNode;

    private FunctionReturnNode functionReturnNode;
    private BodyNode bodyNode;

    public HashMap<String, IdNode> symbolTable = new HashMap<>();


    public FunctionDefNode(ArrayList<Token> tokens) throws Exception{

        if(tokens.get(0).getTokenType()!= TokenType.ID_KEYWORD){
            throw new Exception("Syntax Error: Token "+ tokens.get(0).getToken() + " cannot be parsed into an id for FunctionDef at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        idNode = new IdNode(tokens);

        //< function_def > -> <id >[ func_def_params ]: < function_return >{ < body >}

        if(tokens.get(0).getTokenType()!= TokenType.L_BRACKET){
            throw new Exception("Syntax Error: Token "+ tokens.get(0).getToken() + " cannot be parsed into a [ for FunctionDef at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);

        funcDefParamsNode = new FuncDefParamsNode(tokens);

        funcDefParamsNode.addToSymbolTable(this.symbolTable);


        if(tokens.get(0).getTokenType()!= TokenType.R_BRACKET){
          
            throw new Exception("Syntax Error: Token "+ tokens.get(0).getToken() + " cannot be parsed into a ] for FunctionDef at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);

        if(tokens.get(0).getTokenType()!= TokenType.COLON){
            throw new Exception("Syntax Error: Token "+ tokens.get(0).getToken() + " cannot be parsed into a : for FunctionDef at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);

        functionReturnNode = new FunctionReturnNode(tokens);

        if(tokens.get(0).getTokenType()!= TokenType.L_BRACE){
            throw new Exception("Syntax Error: Token "+ tokens.get(0).getToken() + " cannot be parsed into a { for FunctionDef at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);
        
        bodyNode = new BodyNode(tokens, symbolTable);
        
        if(tokens.get(0).getTokenType()!= TokenType.R_BRACE){
            throw new Exception("Syntax Error: Token "+ tokens.get(0).getToken() + " cannot be parsed into a } for FunctionDef at " + tokens.get(0).getFilename() + " line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);
//         System.out.println(symbolTable);
        
    }

    private Exception syntaxError(Token token, String parseString) {
        String errorMessage = "Syntax Error: Token "+ token.getToken() + " cannot be parsed into a " + parseString + " for FunctionDef at " + token.getFilename() + " line " + token.getLineNum();
        return new Exception(errorMessage);
    }

    public IdNode getIdNode() {
        return idNode;
    }

    public FunctionReturnNode getFunctionReturnNode() {
        return functionReturnNode;
    }

    public FuncDefParamsNode getFuncDefParamsNode() {
        return funcDefParamsNode;
    }

    @Override
    public String convertToJott() {
         //< function_def > -> <id >[ func_def_params ]: < function_return >{ < body >}
        return idNode.convertToJott() + "[" + funcDefParamsNode.convertToJott() + "]" + ":" + functionReturnNode.convertToJott() + "{" + bodyNode.convertToJott() + "}";
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
    public String convertToPython(int t) {
        return "def " + idNode.convertToPython(t) + "(" + funcDefParamsNode.convertToPython(t) + "):" + bodyNode.convertToPython(t+1);
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {

        if(!functionReturnNode.isVoid()){
            if(!bodyNode.isReturnable(this.getType(functionTable))){
                System.err.println("Function body does not have a return in all control flows for function " +idNode.getId());
                return false;
                //THROW ERROR HERE WITH MESSAGE? OR NO?
            }
        }
        return idNode.validateTree(functionTable, symbolTable) &&functionReturnNode.validateTree(functionTable, symbolTable) &&  funcDefParamsNode.validateTree(functionTable, symbolTable) &&bodyNode.validateTree(functionTable, this.symbolTable);
    }


    public String getType(HashMap<String, FunctionDefNode> functionTable) {

        if(!functionTable.containsKey(this.idNode.convertToJott())) {
            // throw new Exception("This function is not defined");
            //TODO idk throw an exception or something
        }
        if (functionTable.get(this.idNode.convertToJott()).getFunctionReturnNode().isVoid()) {
            return "Void";
        } else {
            return functionTable.get(this.idNode.convertToJott()).getFunctionReturnNode().getReturnType().convertToJott();
        }
    }

}
