import java.util.ArrayList;

public class Node {

    private String data;
    private Node parent;
    private ArrayList<Token> children;

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
