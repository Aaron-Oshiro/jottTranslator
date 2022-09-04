import java.util.ArrayList;

public class JottMain {

    public static void main(String[] args) throws Exception {
        ArrayList<Token> hi = new ArrayList<Token>(JottTokenizer.tokenize("input.jott"));
        System.out.println(hi);
    }
}
