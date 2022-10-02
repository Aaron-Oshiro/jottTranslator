import java.util.*;
public class ElseIfNode implements JottTree{

    private boolean hasElse;

    //private BodyNode b;

    //pri=vate bExpr Node bexp;

    public ElseIfNode(ArrayList<Token> tokens)throws Exception{
        Token tokenToCheck = tokens.get(0);


        if(tokenToCheck.getToken().equals("elseif")){
            //get rid of the else
            tokens.remove(0);
            hasElse = true;

            //get rid of lbracket
            if(!tokens.get(0).getToken().equals("[")){
                throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a [ at line " + tokenToCheck.getLineNum());
            }
            tokens.remove(0);

            /*
            * hopefully uncomment when bExpr is implemented
             * bExprNode b = new bExprNode(tokens);
             */

             //get rid of rbracket
            if(!tokens.get(0).getToken().equals("]")){
                throw new Exception("Token "+ tokenToCheck.toString() + "cannot be parsed into a ] at line " + tokenToCheck.getLineNum());
            }
            tokens.remove(0);


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

            ElseIfNode elseIfNode = new ElseIfNode(tokens);

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
        return "elseif[" /* + bExpr.convertToJott */  + "]{" /* + BodyNode.convertToJott */ + "}";
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
