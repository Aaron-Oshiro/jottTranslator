/**
 * This is the REL_OP node
 * Contains the relationship operator given
 *
 * @author Aaron Oshiro
 */

import java.util.ArrayList;
import java.util.HashSet;

public class RelOpNode implements JottTree {

    private String relationshipOperator;

    private HashSet<String> relOps = new HashSet<>();

    public RelOpNode(ArrayList<Token> tokens) throws Exception {
        createRelOps();
        if (relOps.contains(tokens.get(0).getToken())) {
            Token newRelOp = tokens.remove(0);
            relationshipOperator = newRelOp.getToken();
        } else {
            throw new Exception("Token given is not of type REL_OP");
        }
    }

    private void createRelOps() {
        relOps.add(">");
        relOps.add(">=");
        relOps.add("<");
        relOps.add("<=");
        relOps.add("==");
        relOps.add("!=");
    }

    @Override
    public String convertToJott() {
        return this.relationshipOperator;
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
        return true;
    }
}
