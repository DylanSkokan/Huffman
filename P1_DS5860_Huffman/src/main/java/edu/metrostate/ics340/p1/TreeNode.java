/* Author: Ralph A. Foy
 * Class : ICS340-50 Spring 2022
 * 
 *       Copyright (c) 2022 
 *       Authorization is given to students enrolled in the course to reproduce 
 *       this material exclusively for their own personal use.
 */
package edu.metrostate.ics340.p1;

public interface TreeNode<T> {
	/** Returns the left child of this node
	 * 
	 * @return the left child of this node
	 */
	TreeNode<T> getLeftChild();
	
	/** Returns the right child of this node
	 * 
	 * @return the right child of this node
	 */
	TreeNode<T> getRightChild();
	
	/** Returns the value stored in this node
	 * 
	 * @return the value stored in this node
	 */
	T getValue();
}
