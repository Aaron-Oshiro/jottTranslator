import java.util.ArrayList;

/**
 * FuncDefParamsNode
 *
 * @author Raman Zatsarenko
 */
public class FuncDefParamsNode implements JottTree{
    private boolean hasFuncDefParams;
    private IdNode id;
    private TypeNode type;
    private FuncDefParamsTNode funcDefParamsT;

    public FuncDefParamsNode(ArrayList<Token> tokens) throws Exception {
        // func params is [], so list is empty
        if(tokens.get(0).getToken().equals("]")) {
            hasFuncDefParams = false;
        }
        else {
            hasFuncDefParams = true;
            // The bracket check should be in FuncDefNode
            //if(!tokens.get(0).getToken().equals("[")) {
            //    throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a [ at line " + tokens.get(0).getLineNum());
            //}
            // remove [
            //tokens.remove(0);
            id = new IdNode(tokens);
            if(!tokens.get(0).getToken().equals(":")) {
                throw new Exception("Token "+ tokens.get(0).getToken() + "cannot be parsed into a : at line " + tokens.get(0).getLineNum());
            }
            // remove ":"
            tokens.remove(0);
            type = new TypeNode(tokens);
            funcDefParamsT = new FuncDefParamsTNode(tokens);

        }
    }

    @Override
    public String convertToJott() {
        if (!hasFuncDefParams) return "";
        else {
            return id.convertToJott() + ":" + type.convertToJott() + funcDefParamsT.convertToJott();
        }
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