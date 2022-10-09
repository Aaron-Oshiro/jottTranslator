/**
 * func_call Node
 *
 * @author Aaron Oshiro
 */

import java.util.ArrayList;

public class FuncCallNode implements JottTree {

    private IdNode idNode;
    private ParamsNode paramsNode;

    public FuncCallNode(ArrayList<Token> tokens) throws Exception {
        // Needs to check for id, '[' , params, and ']'
        if(Character.isLetter(tokens.get(0).getToken().charAt(0))) {    //
            throw new Exception("Token " + tokens.get(0).getToken() + " needs to start with a letter");
        }
        this.idNode = new IdNode(tokens);   // removes the id_keyword token

        if(!tokens.get(0).getToken().equals("[")) {
            Token thisToken = tokens.get(0);
            throw new Exception("Token "+ thisToken.getToken() + "cannot be parsed into a [ at line " + thisToken.getLineNum());
        }
        tokens.remove(0);   // removes the '['

        paramsNode = new ParamsNode(tokens);

        if(!tokens.get(0).getToken().equals("]")) {
            Token thisToken = tokens.get(0);
            throw new Exception("Token "+ thisToken.getToken() + "cannot be parsed into a ] at line " + thisToken.getLineNum());
        }
        tokens.remove(0);
    }

    @Override
    public String convertToJott() {
        return idNode.convertToJott() + "[" + paramsNode.convertToJott() + "]";
    }

    @Override
    public String convertToJava() {
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
