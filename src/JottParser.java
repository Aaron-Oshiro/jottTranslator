/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author
 */

import java.util.ArrayList;
import java.util.function.Function;

public class JottParser {

    /**
     * Parses an ArrayList of Jotton tokens into a Jott Parse Tree.
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     *         or null upon an error in parsing.
     */
    public static JottTree parse(ArrayList<Token> tokens){
      //top level call in the stack.
      try{
		return new ProgramNode(tokens);}
    catch(Exception e){
      System.err.println(e.getMessage());
      return null;
    }
    }

    /* 

    I am changing this a little bit because I think it should work a bit different. I think each child tokens should be created in the constructors, not in these static methods. Idk this can be discussed or changed.
    //Node rootNode = new Node("program", null);
    //Node firstFuncList = new Node("function_list", rootNode);


    public static FunctionDefNode createFunctionDefNode(ArrayList<Token>tokens){
      //this is not fully fleshed out, but logic continues here. Function def needs an id, params, colon, return, open bracket, body, close bracket for example. these should be passed in.
      FunctionDefNode def = new FunctionDefNode();

      return def;

    }

    public static FunctionListNode createFunctionListNode(ArrayList<Token>tokens){

      //function List needs a function def and another function list

      FunctionDefNode def = createFunctionDefNode(tokens);
      FunctionListNode functionListNode = new FunctionListNode(def, null);
      return functionListNode;
    }

    public static JottTree createProgramNode(ArrayList<Token> tokens){

      //program node needs function list and end program
      FunctionListNode functionListNode = createFunctionListNode(tokens);
      ProgramNode programNode = new ProgramNode(functionListNode);

      //end statement may not be required - ?

      return programNode;
    }

    */
}

