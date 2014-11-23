///////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  GuessingGame.java 
// File:             IllegalBinaryTreeOpException.java
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
 * The IllegalBinaryTreeOpException class signals that an illegal operation
 * has been attempted on a binary tree. 
 *
 * @author Ming Chew
 * @author Andrew Hard
 */
public class IllegalBinaryTreeOpException extends Exception {
    
    /**
     * Creates an exception with no message.
     */
    public IllegalBinaryTreeOpException() {
	super();
    }
    
    /**
     * Creates an exception with a specified message.
     * @param message - the message to accompany the exception.
     */
    public IllegalBinaryTreeOpException(String message) {
	super(message);
    }

}
	
