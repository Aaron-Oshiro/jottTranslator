import java.util.*;
public class ElseIfNode implements JottTree{

    private boolean hasElse;

    private BodyNode bodyNode;

    private ExprNode exprNode;

    private ElseIfNode elseIfNode;

    public ElseIfNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable)throws Exception{
        Token tokenToCheck = tokens.get(0);


        if(tokenToCheck.getToken().equals("elseif")){
            //get rid of the else
            tokens.remove(0);
            hasElse = true;

            //get rid of lbracket
            if(!tokens.get(0).getToken().equals("[")){
                throw new Exception("Syntax Error: Token "+ tokenToCheck.getToken() + " cannot be parsed into a [ at " +tokenToCheck.getFilename() + " line " + tokenToCheck.getLineNum());
            }
            tokens.remove(0);

            //TODO KEEP IN MIND THIS SHOULD BE A BOOLEAN - DO WE NEED TO CHECK THIS IN THIS PHASE?
             exprNode = new ExprNode(tokens);
             

             //get rid of rbracket
            if(!tokens.get(0).getToken().equals("]")){
                throw new Exception("Syntax Error: Token "+ tokenToCheck.getToken() + "cannot be parsed into a ] at at " +tokenToCheck.getFilename() + " line " + tokenToCheck.getLineNum());
            }
            tokens.remove(0);


            //get rid of lbracket
            if(!tokens.get(0).getToken().equals("{")){
                throw new Exception("Syntax Error: Token "+ tokenToCheck.getToken() + "cannot be parsed into a { at line " + tokenToCheck.getLineNum());
            }
            tokens.remove(0);

             bodyNode = new BodyNode(tokens, symbolTable);
             

             //get rid of rbracket
            if(!tokens.get(0).getToken().equals("}")){
                throw new Exception("Syntax Error: Token "+ tokenToCheck.getToken() + "cannot be parsed into a } at line " + tokenToCheck.getLineNum());
            }
            tokens.remove(0);

            //I just added this in phase 3 - didnt we need this to be in convert to jott for phase 2?!?
            elseIfNode = new ElseIfNode(tokens, symbolTable);

        }
        else{
                    hasElse = false;
                    //TODO make sure I handle Empty string case correctly. Do I need to look ahead? I think if the token is not else we assume it is the empty case. Or do I need to use the follow set to see what can follow an elseIf?
            //throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a Else at line " + tokenToCheck.getLineNum());
        }
    }
    
    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        if(!hasElse){
            return "";
        }
        return "elseif[" +exprNode.convertToJott() + "]{"  + bodyNode.convertToJott()  + "}";
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
        return null;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean isReturnable(String type){

        if(hasElse){

            return bodyNode.isReturnable(type) && elseIfNode.isReturnable(type);
        }
        return false;
    }
    
    
}
