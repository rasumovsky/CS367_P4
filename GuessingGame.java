////////////////////////////////////////////////////////////////////////////////
// 
// Title:            GuessingGame
// Files:            GuessingGame.java, BinaryTree.java, BinaryTreenode.java
//                   IllegalBinaryOpException.java
// Semester:         CS302 Fall 2014
//
// Author:           Andrew Hard
// Email:            hard@wisc.edu
// CS Login:         hard
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC-002 (77632)
//
///////////////////////////////////////////////////////////////////////////////
//
// Pair Partner:     Wayne Chew
// Email:            mchew2@wisc.edu
// CS Login:         mchew
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC-001 (77631)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.io.*;

/**
 * The GuessingGame class creates and uses a BinaryTree to represent
 * and navigate through the information used to ask questions and make guesses
 * The program prompts the user for various console commands, and responds 
 * with the appropriate actions. Input files containing sets of commands are
 * optional.
 *
 * @author Ming Chew
 * @author Andrew Hard
 */
public class GuessingGame {
    
    // The data structure storing questions and guesses:
    private static BinaryTree<String> root;
    
    /**
     * reset is a method for resetting the game. It starts with a completely
     * empty tree and then prompts the user for a new root for the tree.
     * @param scanner - the input method (user console input or file).
     * @throws IllegalBinaryTreeOpException.
     */
    //public static BinaryTree<String> reset(Scanner in) 
    public static void reset(Scanner in) 
	throws IllegalBinaryTreeOpException {
		
	// Create a new tree root using the question given by user:
	System.out.println("Please enter a question.");
	//BinaryTree<String> root = new BinaryTree<String>(in.nextLine());
	root = new BinaryTree<String>(in.nextLine());
	root.start();//start navigation of tree by setting current node to root
	
	// Add true answer for the left child:
	System.out
	    .println("Please enter something that is true for that question.");
	root.addLeftChild(in.nextLine());
	
	// Add false answer for the right child:
	System.out
	    .println("Please enter something that is false for that question.");
	root.addRightChild(in.nextLine());
	
	// return the root of the new BinaryTree.
	//return root;
    }
    
    
    /**
     * play is a method for plaing the game with the program, based on inputs. 
     * @param in - the Scanner input from the user or from an input game file.
     * @throws IllegalBinaryTreeOptException.
     */
    //public static void play(Scanner in, BinaryTree<String> root) 
    public static void play(Scanner in) 
	throws IllegalBinaryTreeOpException {
	
	String command;
	root.start();
	
	// Continue asking questions until an answer (leaf) is reached in tree:
	while (!root.isLeaf()) {
	    // print current question:
	    System.out.println(root.getCurrent());
	    // get answer (y/n) from user or file:
	    command = in.nextLine().toLowerCase();
	    
	    if (command.equals("y")) {
		root.goLeft();
	    }
	    else if (command.equals("n")) {
		root.goRight();
	    }
	    else {
		System.out.println("Invalid Command");
	    }
	}
	
	// An answer (leaf node) has been reached:
	String oldGuess = root.getCurrent();
	System.out.println("I guess: " + oldGuess + ". Was I right?");
	
	command = in.nextLine().toLowerCase();
	
	if (command.equals("y")) {
	    System.out.println("I win!");
	}
	else if (command.equals("n")) {
	    // Change current node (a guess) to a new distinguishing question
	    System.out.println("Darn. Ok, tell me a question that is true for your answer, but false for my guess.");
	    root.changeCurrent(in.nextLine());
	    
	    System.out.println("Thanks! And what were you thinking of?");
	    // Set left child of current node (question) to user's true answer:
	    root.addLeftChild(in.nextLine());
	    // Set right child of current node (question) to false old guess:
	    root.addRightChild(oldGuess);
	}
	else {
	    System.out.println("Invalid Command");
	}
    }
    
    
    /**
     * The checkOptions method parses input either from a file or console to 
     * determine which actions the program should take. It is called by the 
     * main method and is implemented to reduce code redundancy.
     * @param input - the Scanner used to input commands.
     * @param command - the current command to parse.
     * @returns true if the user chooses to quit, or if input file ends.
     * @throws IllegalBinaryTreeOpException.
     */
    //public static boolean checkOptions(BinaryTree<String> root, Scanner input, 
    public static boolean checkOptions(Scanner input, String command)
	throws IllegalBinaryTreeOpException {
	// true if user chooses to quit
	boolean result = false;
	
	// Execute different routines based on input from file:
	switch (command) {
	    
	case "o" :// output the BinaryTree, printed to console.
	    if (root == null) {
		System.out.println("Empty Tree");
	    }
	    else {
		root.print();
	    }
	    break;
	    
	case "p" :// play the game.
	    if (root == null) {
		//root = reset(input);
		reset(input);
	    }
	    //play(input, root);
	    play(input);
	    break;
	    
	case "q" :// quit loading data for the game.
	    //input.close();
	    result = true;
	    break;
	    
	case "r" :// reset the game (new tree etc.).
	    //root = reset(input);
	    reset(input);
	    break;
	    
	default:// move on to next line.
	    System.out.println("Invalid command.");
	    break;
	}
	
	return result;
    }
    
    
    /**
     * The main method. Loads data from file and from console to setup and 
     * play the guessing game.
     * @param args - an optional input file with game data.
     */
    public static void main(String[] args) throws IllegalBinaryTreeOpException {
	
	//BinaryTree<String> root = null;//tree storing questions and guesses
	root = null;//tree storing questions and guesses
	File gameFile;//file with game data to import
	String command;//variable assigned to inputs
	
	// Improper argument format:
	if (args.length > 1) {
	    System.out.println("Usage: java GuessingGame Game_File_Name");
	    System.exit(0);
	}
	
	// Read the provided input file:
	else if (args.length == 1) {
	    
	    // Load file and print message if it doesn't exist:
	    gameFile = new File(args[0]);
	    if (!gameFile.exists()) {
		System.out.println("Cannot find the specified file.");
	    }
	    
	    // Load data in the file if it exists:
	    else {
		boolean doneLoading = false;
		try {
		    Scanner fileInput = new Scanner(gameFile);
		    
		    while (fileInput.hasNext() && !doneLoading) {
			System.out.println("Please enter command (o,p,q,r): ");
			command = fileInput.nextLine();
			
			// call a method that checks command options:
			//doneLoading = checkOptions(root, fileInput, command);
			doneLoading = checkOptions(fileInput, command);
			
		    }
		    fileInput.close();// already done in checkOptions();
		}
		
		// If input file not found, move on to console input:
		catch (FileNotFoundException fnfe) {
		    System.out.println("Cannot find the specified file.");
		}
		
	    }
	} // finished reading the provided input file.
	
	
	// Accept console input for the game:
	boolean donePlaying = false;
	Scanner userInput = new Scanner(System.in);
	while (!donePlaying) {
	    
	    // prompt user for command:
	    System.out.print("Please enter command (o,p,q,r): ");
	    command = userInput.nextLine();
	    
	    // call a method that checks command options:
	    //donePlaying = checkOptions(root, userInput, command);
	    donePlaying = checkOptions(userInput, command);
	}
	userInput.close();
    }
}
