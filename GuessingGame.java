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
    
    public static BinaryTree<String> reset(Scanner in) 
	throws IllegalBinaryTreeOpException {
	
	BinaryTree<String> root;
	
	System.out.println("Please enter a question.");
	root = new BinaryTree<String>(in.nextLine());
	root.start();
	
	System.out.println("Please enter something that is true for that question.");
	root.addLeftChild(in.nextLine());
	
	System.out.println("Please enter something that is false for that question.");
	root.addRightChild(in.nextLine());
	
	return root;
	
    }
    
    
    /**
     * 
     */
    public static void play(Scanner in, BinaryTree<String> root) 
	throws IllegalBinaryTreeOpException {
	
	String command;
	root.start();
	
	while (!root.isLeaf()) {
	    System.out.println(root.getCurrent());
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
	
	String oldGuess = root.getCurrent();
	System.out.println("I guess: " + oldGuess + ". Was I right?");
	
	command = in.nextLine().toLowerCase();
	
	if (command.equals("y")) {
	    System.out.println("I win!");
	}
	else if (command.equals("n")) {
	    System.out.println("Darn. Ok, tell me a question that is true for your answer, but false for my guess.");
	    root.changeCurrent(in.nextLine());
	    System.out.println("Thanks! And what were you thinking of?");
	    root.addLeftChild(in.nextLine());
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
     * @param root - the BinaryTree storing game data.
     * @param input - the Scanner used to input commands.
     * @param command - the current command to parse.
     * @returns true if the user chooses to quit, or if input file ends.
     */
    public static boolean checkOptions(BinaryTree root, Scanner input, 
				       String command)
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
		root = reset(input);
	    }
	    play(input, root);
	    break;
	    
	case "q" :// quit loading data for the game.
	    input.close();
	    result = true;
	    break;
	    
	case "r" :// reset the game (new tree etc.).
	    root = reset(input);
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
	
	BinaryTree<String> root = null;//tree storing questions and guesses
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
			doneLoading = checkOptions(root, fileInput, command);
			
		    }
		    //fileInput.close();// already done in checkOptions();
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
	    donePlaying = checkOptions(root, userInput, command);
	}
    }
}
