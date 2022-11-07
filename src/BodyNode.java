import java.util.ArrayList;
import java.util.HashMap;

public class BodyNode implements JottTree {

    private ArrayList<BodyNode> bodyArrayList = new ArrayList<>();
    private ReturnNode rtrn;
    private boolean rtrnFlag = false;
    private boolean epsilonFlag = false;
    private BodyStmtNode bodyStatement;
    private boolean bodyStatementFlag = false;

    public BodyNode(ArrayList<Token> tokens, HashMap<String, IdNode> symbolTable) throws Exception {

        String t0 = tokens.get(0).getToken();
        //System.out.println(t0);
        if (t0.equals("}")) {
            epsilonFlag = true;
            tokens.remove(0);
        } else if (t0.equals("return")) {
            rtrn = new ReturnNode(tokens);
            rtrnFlag = true;
//            tokens.remove(0);

        } else {
            bodyStatement = new BodyStmtNode(tokens, symbolTable);
            bodyStatementFlag = true;
            while ( (tokens.size() != 0) && !tokens.get(0).getToken().equals("}") ) {
//                if (tokens.get(0).getToken().equals("return")) {
//                    rtrn = new ReturnNode(tokens);
//                    rtrnFlag = true;
//                    tokens.remove(0);
//                    break;
//                } else {
                bodyArrayList.add(new BodyNode(tokens, symbolTable));

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
    public String convertToPython(int t) {
        if (epsilonFlag) return "";
        if (rtrnFlag) return rtrn.convertToPython(t);
        String allBodies = "";
        for (int i = 0; i < bodyArrayList.size(); i++) {
            allBodies += (bodyArrayList.get(i).convertToPython(t));
        }
        return bodyStatement.convertToPython(t) + allBodies;
    }

    @Override
    public boolean validateTree(HashMap<String, FunctionDefNode> functionTable, HashMap<String, IdNode> symbolTable) {
        for (int i = 0; i < bodyArrayList.size(); i++) {
            if (!bodyArrayList.get(i).validateTree(functionTable, symbolTable)) {
                return false;
            }
        }

        if(epsilonFlag){
            return true;
        }
        if(rtrnFlag){
            return rtrn.validateTree(functionTable, symbolTable);
        }
        else{
        return bodyStatement.validateTree(functionTable, symbolTable);
    }
    }

    public boolean isReturnable(String type){
        //Todo, go through all bodies and body statements checking if there is a single return or not. Use that to determine this result. Used by FuncDef.
        //Type is passed down to verify the returned thing is of the correct type? may not need it tbh


        //should we check all bodies before statements? feel like this may cause an issue with the ifs and elses and outside returns?
        if(rtrnFlag){
            return true;
        }
        else if(bodyStatement.isReturnable(type)){
            return true;
        }
        else{

        for (int i = 0; i < bodyArrayList.size(); i++) {
            if (bodyArrayList.get(i).isReturnable(type)) {
                return true;
            }
        }
        }
        return false;
    }
}
