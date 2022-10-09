import java.util.ArrayList;

public class ParamsTNode implements JottTree {

    private boolean isEmpty;
    private ExprNode expressionNode;    // requires an expression node to be implemented
    private ParamsTNode paramsTNode;

    public ParamsTNode(ArrayList<Token> tokens) throws Exception {

        if (tokens.get(0).getToken().equals(",")) { // we assume it is not an empty params_t
            this.isEmpty = false;
            tokens.remove(0);

            expressionNode = new ExprNode(tokens);

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
        return ", " + this.expressionNode.convertToJott() + this.paramsTNode.convertToJott();
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
