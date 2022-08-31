/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author I added a test comment
 * @author Aaron Oshiro
 **/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class JottTokenizer {

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){

		ArrayList<Token> tokens = new ArrayList<>();
    	try {
			Scanner jottFile = new Scanner(new File(filename));
			int lineNumber = 0;		// I think this is a good way of knowing which line we are on
			while (jottFile.hasNextLine()) {
				lineNumber++;
				String jottLine = jottFile.nextLine();
				ArrayList<Token> newTokens = createTokensPerLine(jottLine, filename, lineNumber);
				tokens.addAll(newTokens);
				System.out.println(jottLine);		// test sout

			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			return null;
		}

    	return tokens;
	}

	private static ArrayList<Token> createTokensPerLine(String jottLine, String fileName, int lineNum) {
    	return new ArrayList<>();
	}
}