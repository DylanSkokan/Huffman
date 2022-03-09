package edu.metrostate.ics340.p1.ds5860;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import edu.metrostate.ics340.p1.TreeNode;

/**
 * <h2>Huffman</h2> The Huffman program builds a Huffman object which can encode
 * and decode strings.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 * 
 * <p>
 * Sources used:
 * </p>
 * - http://ben-tanen.com/adaptive-huffman/ 
 * <br>
 * - https://stackoverflow.com/questions/4650976/accessing-external-files-within-java-applications-under-windows-and-linux-operat
 * <br>
 * - https://www.tutorialspoint.com/how-to-solve-an-illegalargumentexception-in-java
 * <br>
 * - https://www.geeksforgeeks.org/iterate-map-java/ 
 * <br>
 * - https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java
 */
public class Huffman {

	private Map<Character, String> encodingMap;
	private HuffNode rootNode;

	/**
	 * The build method takes a file path to a text file and builds an encoding map
	 * and a decoding tree off the frequency of characters in the file. For case
	 * insensitivity it calls .toUpperCase() on the String fileText.
	 * 
	 * @param filePath The file path to the text file to base the decoding tree and
	 *                 encoding map off of.
	 * @return coder: The Huffman object to be returned which has the encoding map
	 *         and decoding tree.
	 * @throws IOException
	 */
	public static Huffman build(String filePath) throws IOException {
		String fileText = null;
		Path fileName = null;
		try {
			fileName = Path.of(filePath);
			fileText = Files.readString(fileName).toUpperCase();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("File is either inaccessible or does not exist.");
		}

		var coder = new Huffman();
		coder.encodingMap = new HashMap<Character, String>();
		coder.rootNode = coder.buildDecodingTree(coder.getCharacterFrequencies(fileText));
		coder.buildEncodingMap(coder.rootNode, "");

		return coder;
	}

	private PriorityQueue<HuffNode> getCharacterFrequencies(String fileText) throws IOException {
		var uncodedTextReader = new StringReader(fileText);
		var unsortedFreq = new HashMap<Character, Integer>();
		var sortedFreq = new PriorityQueue<HuffNode>();
		int charCode = 0, charCount;
		char charSymbol = 0;

		while ((charCode = uncodedTextReader.read()) != -1) {
			charSymbol = (char) charCode;
			charCount = (unsortedFreq.containsKey(charSymbol)) ? unsortedFreq.get(charSymbol) + 1 : 1;
			unsortedFreq.put(charSymbol, charCount);
		}

		for (Entry<Character, Integer> currEntry : unsortedFreq.entrySet()) {
			var charFreq = new HuffNode();
			charFreq.setValue(currEntry.getKey());
			charFreq.setFrequency(currEntry.getValue());
			sortedFreq.add(charFreq);
		}
		return sortedFreq;
	}

	private HuffNode buildDecodingTree(PriorityQueue<HuffNode> charFrequencies) {
		var rootNode = new HuffNode();

		if (charFrequencies.size() == 1) {
			rootNode = charFrequencies.poll();
		}

		while (charFrequencies.size() > 1) {
			var firstSmallest = charFrequencies.poll();
			var secondSmallest = charFrequencies.poll();

			var mergedNode = new HuffNode();
			mergedNode.setFrequency(firstSmallest.getFrequency() + secondSmallest.getFrequency());
			mergedNode.leftNode = firstSmallest;
			mergedNode.rightNode = secondSmallest;

			rootNode = mergedNode;

			charFrequencies.add(rootNode);
		}
		return rootNode;
	}

	private void buildEncodingMap(TreeNode<Character> root, String prefix) {
		if (root.getLeftChild() == null) {
			if (prefix.equals("")) {
				prefix = "0";
			}
			encodingMap.put(root.getValue(), prefix);
			return;
		}
		buildEncodingMap(root.getLeftChild(), prefix + "0");
		buildEncodingMap(root.getRightChild(), prefix + "1");
	}

	/**
	 * The getEncodingMap method returns the encoding map for this Huffman object.
	 * 
	 * @return this.encodingMap: The encoding map for this Huffman object.
	 */
	public Map<Character, String> getEncodingMap() {
		return this.encodingMap;
	}

	/**
	 * The getDecodingTree method returns the decoding tree for this Huffman object.
	 * 
	 * @return this.rootNode: The root node for this Huffman object.
	 */
	public TreeNode<Character> getDecodingTree() {
		return this.rootNode;
	}

	/**
	 * The encode method returns encoded text based on the encoding map for this
	 * Huffman object.
	 * 
	 * @param text The text to be encoded which is capitalized to match case
	 *             insensitivity in the build method.
	 * @return encodedText: The text that has been encoded based on the encoding
	 *         map.
	 * @throws IOException
	 */
	public String encode(String text) throws IOException {
		text = text.toUpperCase();
		var uncodedTextReader = new StringReader(text);
		var encodedText = "";
		char charSymbol = 0;
		int charCode = 0;

		while ((charCode = uncodedTextReader.read()) != -1) {
			charSymbol = (char) charCode;

			if (!encodingMap.containsKey(charSymbol)) {
				throw new IllegalArgumentException("Unrecognized character during encoding" + "\nCharacter code: "
						+ charCode + "\nCharacter: " + charSymbol);
			}

			encodedText = encodedText + encodingMap.get(charSymbol);
		}
		return encodedText;
	}

	/**
	 * The decode method returns decoded text based on the decoding tree for this
	 * Huffman object.
	 * 
	 * @param text The encoded text to be decoded.
	 * @return decodedText: The text that has been decoded based on the decoding
	 *         tree.
	 */
	public String decode(String text) {
		String decodedText = "", currCode = "";
		var currNode = getDecodingTree();

		for (char currChar : text.toCharArray()) {
			if (!(currNode.getLeftChild() == null)) {
				currNode = (currChar == '0') ? currNode.getLeftChild() : currNode.getRightChild();
				currCode += currChar;
				
				if (currChar != '0' && currChar != '1') {
					throw new IllegalArgumentException("Unrecognized code during decoding" + "\nCode: " + currCode);
				}
			}
			if (currNode.getLeftChild() == null) {
				decodedText = decodedText + currNode.getValue();
				currNode = getDecodingTree();
				currCode = "";
			}
		}
		return decodedText;
	}
}