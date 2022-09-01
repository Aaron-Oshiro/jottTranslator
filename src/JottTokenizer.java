/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author I added a test comment
 * @author Aaron Oshiro
 **/

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.transform.Source;

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

		ArrayList<Token> tokensInCurrentLine = new ArrayList<>();

		
		for(int i = 0; i < jottLine.length();i++){
				
			char currentCharacter = jottLine.charAt(i);

				//go through if statements - do we want to put this in a helper method? idk

				if(currentCharacter == ';'){
					tokensInCurrentLine.add(new Token(""+currentCharacter, fileName, lineNum, TokenType.SEMICOLON));
				}
				else if(currentCharacter == ':'){
					tokensInCurrentLine.add(new Token(""+currentCharacter, fileName, lineNum, TokenType.COLON));
				}
				else if(currentCharacter == '.'){
					if(i+1 < jottLine.length() && Character.isDigit(jottLine.charAt(i+1))){
						String fullNumber = ".";

						//go through each char until we find a non digit.
						i++;
						char nextCharacter = jottLine.charAt(i);
						while(i < jottLine.length() && Character.isDigit(jottLine.charAt(i))){
							nextCharacter = jottLine.charAt(i);
							fullNumber = fullNumber + nextCharacter;
							i++;
						}
						if(i<jottLine.length()){
							//go back to not skip a character. we want to start here next.
							i--;
						}
						tokensInCurrentLine.add(new Token(fullNumber, fileName, lineNum, TokenType.NUMBER));
					}
					else{
						//NON TERMINAL STATE - THROW ERROR HERE. This is a placeholder until we implement the error reporting.
						System.err.println("Syntax Error on line " + lineNum + ": Expected digit after '.'");
						//execution should halt here, so we need to throw an error I think. To do later.
					}

				}
				else if(currentCharacter == '/' || currentCharacter == '*' || currentCharacter == '-' || currentCharacter == '+'){
					
					tokensInCurrentLine.add(new Token(""+currentCharacter, fileName, lineNum, TokenType.MATH_OP));
				}
				else{
					//throw error. for now i just print so I can see when this triggers
					System.err.println("Syntax Error on line " + lineNum + ": Either invalid or not yet implemented character detected: " + currentCharacter);
				}

		}
    	return tokensInCurrentLine;
	}
}