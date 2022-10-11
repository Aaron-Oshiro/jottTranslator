import java.util.ArrayList;

public class JottMain {

    public static void main(String[] args) throws Exception {
        ArrayList<Token> tokens = new ArrayList<Token>(JottTokenizer.tokenize("input.jott"));
        System.out.println(tokens);

        //parse
        System.out.println("Now parsing tree...");
        JottTree tree= JottParser.parse(tokens);

       System.out.println(tree);
    }
}
