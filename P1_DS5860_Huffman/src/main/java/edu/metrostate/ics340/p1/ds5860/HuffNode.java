package edu.metrostate.ics340.p1.ds5860;
import edu.metrostate.ics340.p1.TreeNode;
/**
* <h2>HuffNode</h2>
* The HuffNode program extends TreeNode to create nodes for the Huffman tree.
* 
* @author  Dylan Skokan
* @since   2/23/22
* 
* <p>Sources used:</p>
* - http://ben-tanen.com/adaptive-huffman/
*/
public class HuffNode implements TreeNode<Character>, Comparable<HuffNode>{
	private char character;
	private int frequency;
	TreeNode<Character> leftNode;
	TreeNode<Character> rightNode;

	/**
	* The getValue method gets the character for this HuffNode.
	* 
    * @return this.character: The character stored in this HuffNode.
	*/
	@Override
	public Character getValue() {
		return this.character;
	}
	
	/**
	* The setValue method sets the character for this HuffNode.
	* 
    * @param character The char to be set as the character for this HuffNode.
	*/
	public void setValue(char character) {
		this.character = character;
	}
	
	/**
	* The getFrequency method gets the frequency for this HuffNode.
	* 
    * @return this.frequency: The frequency stored in this HuffNode.
	*/
	public int getFrequency() {
		return this.frequency;
	}

	/**
	* The setFrequency method sets the frequency for this HuffNode.
	* 
    * @param frequency The int to be set as the frequency for this HuffNode.
	*/
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	* The compareTo method defines how HuffNode objects will be compared.
	* 
    * @param node The node to be compared. 
    * @return this.frequency-node.frequency:  compares this nodes frequency against the parameter node's frequency.
	*/
	@Override
	public int compareTo(HuffNode node) {
		return this.frequency - node.frequency;
	}

	/**
	* The getLeftChild gets the left child of a node.
	* 
    * @return this.leftNode: The left child node of the one calling it.
	*/
	@Override
	public TreeNode<Character> getLeftChild() {
		return this.leftNode;
	}

	/**
	* The getRightChild gets the right child of a node.
	* 
    * @return this.rightNode: The right child node of the one calling it.
	*/
	@Override
	public TreeNode<Character> getRightChild() {
		return this.rightNode;
	}
}