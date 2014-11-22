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
 * The GuessingGame class...
 *
 * @author Ming Chew
 * @author Andrew Hard
 */
public class GuessingGame{

	public static BinaryTree<String> reset(Scanner in) throws IllegalBinaryTreeOpException{
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

	public static void play(Scanner in, BinaryTree<String> root) throws IllegalBinaryTreeOpException{
		String command;
		root.start();

		while(!root.isLeaf()){
			System.out.println(root.getCurrent());
			command = in.nextLine().toLowerCase();

			if(command.equals("y")){
				root.goLeft();
			}
			else if(command.equals("n")){
				root.goRight();
			}
			else{
				System.out.println("Invalid Command");
			}
		}

		String oldGuess = root.getCurrent();
		System.out.println("I guess: " + oldGuess + ". Was I right?");

		command = in.nextLine().toLowerCase();

		if(command.equals("y")){
			System.out.println("I win!");
		}
		else if(command.equals("n")){
			System.out.println("Darn. Ok, tell me a question that is true for your answer, but false for my guess.");
			root.changeCurrent(in.nextLine());
			System.out.println("Thanks! And what were you thinking of?");
			root.addLeftChild(in.nextLine());
			root.addRightChild(oldGuess);
		}
		else{
			System.out.println("Invalid Command");
		}
	}

	public static void main(String[] args) throws IllegalBinaryTreeOpException, FileNotFoundException{
		File gameFile;
		String command;
		BinaryTree<String> root = null;

		if(args.length != 1 && args.length != 0){
			System.out.println("Usage: java GuessingGame Game_File_Name");
			System.exit(0);
		}
		else if(args.length == 1){
			gameFile = new File(args[0]);

			if(gameFile.exists()){
				Scanner fileInput = new Scanner(gameFile);

				while(fileInput.hasNext()){
					System.out.println("Please enter command (o,p,q,r): ");
					command = fileInput.nextLine();
					
					switch(command){
						case "o" :
							if(root == null){
								System.out.println("Empty Tree");
							}
							else{
								root.print();
							}
							break;
						case "p" :
							if(root == null){
								root = reset(fileInput);
							}
							play(fileInput, root);
							break;
						case "q" :
							fileInput.close();
							System.exit(0);
							break;
						case "r" :
							root = reset(fileInput);
							break;
						default:
							break;
					}
				}
				fileInput.close();
			}
		}


		Scanner userInput = new Scanner(System.in);

		while(true){
			System.out.print("Please enter command (o,p,q,r): ");

			command = userInput.nextLine();

			switch(command){
				case "o" :
					if(root == null){
						System.out.println("Empty Tree");
					}
					else{
						root.print();
					}
					break;
				case "p" :
					if(root == null){
						root = reset(userInput);
					}
					play(userInput, root);

					break;
				case "q" :
					userInput.close();
					System.exit(0);
					break;
				case "r" :
					root = reset(userInput);
					break;
				default:
					System.out.println("Invalid command.");
					break;
			}
		}
	}
}
