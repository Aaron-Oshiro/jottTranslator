import java.util.*;
public class ElseNode implements JottTree{

    private boolean hasElse;

    //private BodyNode b;

    public ElseNode(ArrayList<Token> tokens)throws Exception{
        Token tokenToCheck = tokens.get(0);


        if(tokenToCheck.getToken().equals("else")){
            //get rid of the else
            tokens.remove(0);
            hasElse = true;

            //get rid of lbracket
            if(!tokens.get(0).getToken().equals("{")){
                throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a { at line " + tokenToCheck.getLineNum());
            }
            tokens.remove(0);

            /*
            * hopefully uncomment when BodyNode is implemented
             * BodyNode body = new BodyNode(tokens)
             */

             //get rid of rbracket
            if(!tokens.get(0).getToken().equals("}")){
                throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a } at line " + tokenToCheck.getLineNum());
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
        return "else{" /* + BodyNode.convertToJott */ + "}";
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