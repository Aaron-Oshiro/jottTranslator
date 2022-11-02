import java.util.*;
public class ElseNode implements JottTree{

    private boolean hasElse;

    private BodyNode bodyNode;

    public ElseNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable)throws Exception{
        Token tokenToCheck = tokens.get(0);


        if(tokenToCheck.getToken().equals("else")){
            //get rid of the else
            tokens.remove(0);
            hasElse = true;

            //get rid of lbracket
            if(!tokens.get(0).getToken().equals("{")){
                throw new Exception("Syntax Error: Token "+ tokenToCheck.getToken() + " cannot be parsed into a { at " +tokenToCheck.getFilename() + " line " + tokenToCheck.getLineNum());
            }
            tokens.remove(0);

            bodyNode = new BodyNode(tokens, symbolTable);
             

             //get rid of rbracket
            if(!tokens.get(0).getToken().equals("}")){
                throw new Exception("Syntax Error: Token "+ tokenToCheck.getToken() + "cannot be parsed into a } at " +tokenToCheck.getFilename() + " line " + tokenToCheck.getLineNum());
            }
            tokens.remove(0);

        }
        else{
                    hasElse = false;
                    //TODO handle Empty string case. Do I need to look ahead? I think if the token is not else we assume it is the empty case.



            //throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a Else at line " + tokenToCheck.getLineNum());
        }
    }
    
    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        if(!hasElse){
            return "";
        }
        return "else{"+ bodyNode.convertToJott() + "}";
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
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        // TODO Auto-generated method stub
        return true;
    }

    
    
}
