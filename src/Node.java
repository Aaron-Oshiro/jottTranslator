import java.util.ArrayList;

public class Node {

    private String data;
    private Token parent;
    private ArrayList<Token> children;

    public Node(String data, Token parent) {
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

    public Token getParent() {
        return parent;
    }

    public void setParent(Token parent) {
        this.parent = parent;
    }

    public ArrayList<Token> getChildren() {
        return children;
    }

    public void addChild(Token child) {
        this.children.add(child);
    }
}
