///////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  GuessingGame.java 
// File:             BinaryTree.java
// Semester:         CS367 Fall 2014
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

/**
 * The BinaryTree class...
 *
 * @author Ming Chew
 * @author Andrew Hard
 */
public class BinaryTree<E> {
	private BinaryTreenode<E> root; //root of the tree
	private BinaryTreenode<E> curr; //curr position of the tree

	public BinaryTree(E data){
		root = new BinaryTreenode<E>(data);
	}

	public void start(){
		curr = root;
	}

	public E getCurrent() throws IllegalBinaryTreeOpException{
		if(curr == null){
			throw new IllegalBinaryTreeOpException();
		}
		else{
			return curr.getData();
		}
	}

	public void goLeft() throws IllegalBinaryTreeOpException{
		if(curr.getLeft() == null){
			throw new IllegalBinaryTreeOpException();
		}
		else{
			curr = curr.getLeft();
		}
	}

	public void goRight() throws IllegalBinaryTreeOpException{
		if(curr.getRight() == null){
			throw new IllegalBinaryTreeOpException();
		}
		else{
			curr = curr.getRight();
		}
	}

	public boolean isLeaf(){
		if(curr.getRight() == null && curr.getRight() == null){
			return true;
		}
		else{
			return false;
		}
	}

	public void changeCurrent(E data) throws IllegalBinaryTreeOpException{
		if(curr == null){
			throw new IllegalBinaryTreeOpException();
		}
		else{
			curr.setData(data);
		}
	}

	public void addRightChild(E data) throws IllegalBinaryTreeOpException{
		if(curr.getRight() != null){
			throw new IllegalBinaryTreeOpException();
		}
		else{
			curr.setRight(data);
		}
	}

	public void addLeftChild(E data) throws IllegalBinaryTreeOpException{
		if(curr.getLeft() != null){
			throw new IllegalBinaryTreeOpException();
		}
		else{
			curr.setLeft(data);
		}
	}

	public void print(){
		if(root != null){
			this.print(root, 0);
		}
	}

	private void print(BinaryTreenode<E> root, int space){
		if(root != null){
			if(space == 0){
				System.out.println(root.getData());
			}
			else{
				System.out.printf("%" + space + "s" + root.getData() + "\n", "");
			}
			print(root.getLeft(), space+3);
			print(root.getRight(), space+3);
		}
	}

}
