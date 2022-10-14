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

        TokenType thisToken = tokens.get(0).getTokenType();
        if (thisToken == TokenType.STRING || thisToken == TokenType.NUMBER || thisToken == TokenType.ID_KEYWORD) {
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
