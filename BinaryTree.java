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
 * The BinaryTree class stores information to be used by the GuessingGame
 * program to ask questions and make guesses. Each node that is not a leaf
 * stores a question, and each leaf node stores a guess. The leaf nodes in the
 * left subree of a given parent node answer "yes" to the question in the 
 * parent node. The leaf nodes in the right subtree of a given parent node 
 * answer "no" to the question in the parent node. 
 *
 * @author Ming Chew
 * @author Andrew Hard
 */
public class BinaryTree<E> {
    
    private BinaryTreenode<E> root; //root of the tree
    private BinaryTreenode<E> curr; //curr position of the tree
    
    
    /**
     * Constructs an empty BinaryTree.
     */
    public BinaryTree() {
	root = new BinaryTreenode<E>();
    }
    
    
    /**
     * Constructs a BinaryTree with data stored in its root.
     * @param data - the data to add at the root of the new tree.
     */
    public BinaryTree(E data) {
	root = new BinaryTreenode<E>(data);
    }
    
    
    /**
     * Starts the current reference at the root of the tree to begin navigation
     * of the tree.
     */
    public void start() {
	curr = root;
    }
    
    
    /**
     * Returns the data stored in the current node in navigation. Throws an 
     * IllegalBinaryTreeOpException if there is no current node in navigation.
     * @throws IllegalBinaryTreeOpException
     */
    public E getCurrent() throws IllegalBinaryTreeOpException {
	
	// check that current node is defined:
	if (curr == null) {
	    throw new IllegalBinaryTreeOpException("current node not defined");
	}
	
	// return data in current node:
	else {
	    return curr.getData();
	}
    }
    
    
    /**
     * Moves the current reference to the left child of the current node in 
     * navigation. Throws an IllegalBinaryTreeOpException if the current node
     * does not have a right child.
     * @throws IllegalBinaryTreeOpException
     */
    public void goLeft() throws IllegalBinaryTreeOpException {
	
	// check that current node is defined:
	if (curr == null) {
	    throw new IllegalBinaryTreeOpException("current node not defined");
	}
	
	// checks that left child exists:
	if (curr.getLeft() == null) {
	    throw new IllegalBinaryTreeOpException("left child not defined");
	}
	
	// move current node reference to left child:
	else {
	    curr = curr.getLeft();
	}
    }
    
    
    /**
     * Moves the current reference to the right child of the current node in
     * navigation. Throws an IllegalBinaryTreeOpException if the current node
     * does not have a right child. 
     * @throws IllegalBinaryTreeOpException
     */
    public void goRight() throws IllegalBinaryTreeOpException {
	
	// check that current node is defined:
	if (curr == null ) {
	    throw new IllegalBinaryTreeOpException("current node not defined");
	}
	
	// checks that right child exists:
	if (curr.getRight() == null) {
	    throw new IllegalBinaryTreeOpException("right child not defined");
	}
	
	// move current node reference to right child:
	else {
	    curr = curr.getRight();
	}
    }
    
    
    /**
     * Returns true if the current node in navigation is a leaf (i.e. has no
     * children).
     */
    public boolean isLeaf() throws IllegalBinaryTreeOpException {
	
	// check that current node is defined:
	if (curr == null ) {
	    throw new IllegalBinaryTreeOpException("current node not defined");
	}
	
	// Return true if there are no left or right children:
	if (curr.getRight() == null && curr.getRight() == null) {
	    return true;
	}
	
	// Return false if the node has any children:
	else {
	    return false;
	}
    }
    
    
    /**
     * Changes the data held by the current node in navigation to the specified 
     * data.
     * @param data - the data to enter into the current node.
     * @throws IllegalBinaryTreeOpException
     */
    public void changeCurrent(E data) throws IllegalBinaryTreeOpException {
	
	// check that current node is defined:
	if (curr == null) {
	    throw new IllegalBinaryTreeOpException("current node not defined");
	}
	
	// set data in current node:
	else {
	    curr.setData(data);
	}
    }
    
    
    /**
     * Adds a node with the specified child as the right child of the current
     * node in navigation. Throws an IllegalBinaryTreeOpException if the 
     * current node already has a right child.
     * @param data - the data to add as the specified child.
     * @throws IllegalBinaryTreeOpException 
     */
    public void addRightChild(E data) throws IllegalBinaryTreeOpException {
	
	// check that current node is defined:
	if (curr == null) {
	    throw new IllegalBinaryTreeOpException("current node not defined");
	}
	
	// check that right child is not already defined:
	if (curr.getRight() != null) {
	    throw new IllegalBinaryTreeOpException("right child defined");
	}
	
	// add data to right child node:
	else {
	    curr.setRight(data);
	}
    }
    
    
    /**
     * Adds a node with the specified child as the left child of the current 
     * node in navigation. Throws an IllegalBinaryTreeOpException if the 
     * current node already has a left child. 
     * @param data - the data to add with the specified child
     * @throws IllegalBinaryTreeOpException
     */
     public void addLeftChild(E data) throws IllegalBinaryTreeOpException {
	
	// check that current node is defined:
	if (curr == null) { 
	    throw new IllegalBinaryTreeOpException("current node not defined");
	}
	
	// check that left child is not already defined:
	if (curr.getLeft() != null) {
	    throw new IllegalBinaryTreeOpExeption("left child defined");
	} 
	
	// add data to left child node:
	else {
	    curr.setLeft(data);
	}
    }
    
    
    /**
     * Pre-order prints the tree, starting at the root. Each additional level 
     * of the tree is incremented by three spaces.
     */
    public void print() {
	
	// check root exists before calling recursive companion print method
	if (root != null) {
	    this.print(root, 0);
	}
    }
    
    
    /**
     * A companion method for print() to enable an iterative implementation.
     */
    private void print(BinaryTreenode<E> root, int space) {
	
	// implicit base case: check root exists.
	if (root != null) {
	    
	    // for first level, print indentation of zero:
	    if (space == 0) {
		System.out.println(root.getData());
	    }
	    
	    // for other levels, add print spacing:
	    else {
		System.out.printf("%"+space+"s"+root.getData()+"\n", "");
	    }
	    
	    // recursive calls to print left and right child nodes:
	    print(root.getLeft(), space+3);
	    print(root.getRight(), space+3);
	}
    }
    
}
