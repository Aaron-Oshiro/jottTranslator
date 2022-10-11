/**
 * params node
 *
 * @author Aaron Oshiro
 */

import java.util.ArrayList;

public class ParamsNode implements JottTree {

    private boolean isEmpty;
    private ExprNode expressionNode;
    private ParamsTNode paramsTNode;

    public ParamsNode(ArrayList<Token> tokens) throws Exception{

        // param nodes requires an expression node at the front, or it's empty
        // expression nodes require an id in front for all of them, so we check if the node in front is a lowercase letter

        //not necessarily true. exprs can start with ids, doubles, bools, etc. as well. oor even a string literal
        if (Character.isLowerCase(tokens.get(0).getToken().charAt(0))) {
            this.isEmpty = false;
            this.expressionNode = new ExprNode(tokens);

            this.paramsTNode = new ParamsTNode(tokens);
        } else {
            this.isEmpty = true;
        }


    }

    @Override
    public String convertToJott() {
        if (this.isEmpty) {
            return "";
        }
        return this.expressionNode.convertToJott() + this.paramsTNode.convertToJott();
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
