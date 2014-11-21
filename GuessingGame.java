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
	public static void main(String[] args) throws IllegalBinaryTreeOpException, FileNotFoundException{
		if(args.length != 1){
			System.out.println("Usage: java GuessingGame Game_File_Name");
			System.exit(0);
		}

		File gameFile = new File(args[0]);
		if(!gameFile.exists()){
			System.out.println("Cannot find the specified file");
		}

		Scanner gameInput = new Scanner(gameFile);

		List<String> commands = new ArrayList<String>();
		String command;
		while(gameInput.hasNext()){
			//commands.add(gameInput.next());
			command = gameInput.nextLine();

			switch(command){

			}
		}

		/*for(int i=0; i<command.size(); i++){
			System.out.println(command.get(i));
		}*/

		BinaryTree<String> game = new BinaryTree<String>("Hello");
		game.start();
		game.addRightChild("YOLO");

		game.addLeftChild("Muahaha");

		game.print();
	}
}
