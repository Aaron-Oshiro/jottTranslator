import java.util.*;
public class FunctionDefNode implements JottTree{


    private ArrayList<JottTree> children;

    private IdNode idNode;

    private FuncDefParamsNode funcDefParamsNode;

    private FunctionReturnNode functionReturnNode;
    private BodyNode bodyNode;

    //this should not be token type, but too lazy to change it rn
    private TokenType type;

    public FunctionDefNode(ArrayList<Token> tokens) throws Exception{

        if(tokens.get(0).getTokenType()!= TokenType.ID_KEYWORD){
            throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into an id for FunctionDef at line " + tokens.get(0).getLineNum());
        }
        idNode = new IdNode(tokens);

        //< function_def > -> <id >[ func_def_params ]: < function_return >{ < body >}

        if(tokens.get(0).getTokenType()!= TokenType.L_BRACKET){
            throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a [ for FunctionDef at line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);

        funcDefParamsNode = new FuncDefParamsNode(tokens);


        if(tokens.get(0).getTokenType()!= TokenType.R_BRACKET){
          
            throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a ] for FunctionDef at line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);

        if(tokens.get(0).getTokenType()!= TokenType.COLON){
            throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a : for FunctionDef at line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);

        functionReturnNode = new FunctionReturnNode(tokens);

        if(tokens.get(0).getTokenType()!= TokenType.L_BRACE){
            throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a { for FunctionDef at line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);
        
        bodyNode = new BodyNode(tokens);
        
       if(tokens.get(0).getTokenType()!= TokenType.R_BRACE){
            throw new Exception("Token "+ tokens.get(0).getToken() + " cannot be parsed into a } for FunctionDef at line " + tokens.get(0).getLineNum());
        }
        tokens.remove(0);
        
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
