import java.util.ArrayList;

public class BodyNode implements JottTree {

    private ArrayList<BodyNode> bodyArrayList = new ArrayList<BodyNode>();
    private ReturnNode rtrn;
    private boolean rtrnFlag = false;
    private boolean epsilonFlag = false;
    private BodyStmtNode bodyStatement;
    private boolean bodyStatementFlag = false;

    public BodyNode(ArrayList<Token> tokens) throws Exception {
        //System.out.println("AADASD");

        String t0 = tokens.get(0).getToken();
        //System.out.println(t0);
        if (t0.equals("}")) {
            epsilonFlag = true;
            tokens.remove(0);
        } else if (t0.equals("return")) {
            rtrn = new ReturnNode(tokens);
            rtrnFlag = true;

        } else {
            bodyStatement = new BodyStmtNode(tokens);
            bodyStatementFlag = true;
            while (!t0.equals("}") && (tokens.size() != 0)) {
                if (t0.equals("return")) {
                    rtrn = new ReturnNode(tokens);
                    break;
                } else {
                    
                    bodyArrayList.add(new BodyNode(tokens));
                }
            }
        }
    }

    @Override
    public String convertToJott() {
        if (epsilonFlag) {
            return "";
        }
        if (rtrnFlag) {
            return rtrn.convertToJott();
        } else {
            String allBodies = "";
            for (int i = 0; i < bodyArrayList.size(); i++) {
                allBodies += (bodyArrayList.get(i).convertToJott());
            }
            return bodyStatement.convertToJott() + allBodies;
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
