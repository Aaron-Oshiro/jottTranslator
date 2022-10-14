import java.util.ArrayList;

public class WhileLoopNode implements JottTree{

    private ExprNode expr;
    private BodyNode body;

    public WhileLoopNode(ArrayList<Token> tokens) throws Exception {
        if (!tokens.get(0).getToken().equals("while")) {
            throw new Exception("Token "+ tokens.get(0).getToken() + " cannot be parsed into 'while' at line " +
                    tokens.get(0).getLineNum());
        }
        else {
            tokens.remove(0); // remove while
            if (!tokens.get(0).getToken().equals("[")) {
                throw new Exception("Token "+ tokens.get(0).getToken() + " cannot be parsed into a [ at line " +
                        tokens.get(0).getLineNum());
            }
            tokens.remove(0); // remove [

            expr = new ExprNode(tokens);

            if (!tokens.get(0).getToken().equals("]")) {
                throw new Exception("Token "+ tokens.get(0).getToken() + " cannot be parsed into a ] at line " +
                        tokens.get(0).getLineNum());
            }
            tokens.remove(0); // remove ]
            if (!tokens.get(0).getToken().equals("{")) {
                throw new Exception("Token "+ tokens.get(0).getToken() + " cannot be parsed into a { at line " +
                        tokens.get(0).getLineNum());
            }
            tokens.remove(0); // remove {
            body = new BodyNode(tokens);
            if (!tokens.get(0).getToken().equals("}")) {
                throw new Exception("Token "+ tokens.get(0).getToken() + " cannot be parsed into a } at line " +
                        tokens.get(0).getLineNum());
            }
            tokens.remove(0); // remove }
        }
    }

    @Override
    public String convertToJott() {
        return "while[" + expr.convertToJott() + "]" + "{" + body.convertToJott() + "}";
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
