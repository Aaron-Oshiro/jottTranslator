import java.util.ArrayList;

public class Node {

    private String data;
    private Node parent;
    private ArrayList<Token> children;

    public enum nodeType {
        PROGRAM,
        FUNCTION_LIST,
        FUNCTION_DEF,
        FUNC_DEF_PARAMS,
        FUNC_DEF_PARAMS_T,
        BODY_STMT,
        RETURN_STMT,
        BODY,
        END_STMT,
        IF_STMT,
        ELSE,
        ELSEIF_LST,
        WHILE_LOOP,
        CHAR,
        L_CHAR,
        U_CHAR,
        DIGIT,
        SIGN,
        ID,
        STMT,
        FUNC_CALL,
        PARAMS,
        PARAMS_T,
        EXPR,
        TYPE,
        FUNCTION_RETURN,
        VAR_DEC,
        ASMT,
        OP,
        REL_OP,
        DBL,
        D_EXPR,
        BOOL,
        B_EXPR,
        INT,
        I_EXPR,
        STR_LITERAL,
        STR,
        S_EXPR
    }

    public Node(String data, Node parent) {
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Token> getChildren() {
        return children;
    }

    public void addChild(Token child) {
        this.children.add(child);
    }
}
