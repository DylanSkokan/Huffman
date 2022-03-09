/* Author: Ralph A. Foy
 * Class : ICS340 Spring 2022
 * 
 *       Copyright (c) 2022 
 *       Authorization is given to students enrolled in the course to reproduce 
 *       this material exclusively for their own personal use.
 */
package edu.metrostate.ics340.p1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics340.p1.ds5860.Huffman;

class SampleJunitTest {

	private static final String FILE_PATH = Path
			.of("src", "test", "resources", "huffman8.txt")
			.toFile()
			.getAbsolutePath();

	@Test
	void testHuffmanBasic() throws IOException {
		Huffman h = Huffman.build(FILE_PATH);

		final String clearText = "ABCDEFGHabcdefgh";
		assertEquals(clearText.toUpperCase(), h.decode(h.encode(clearText)));
		final var root = h.getDecodingTree();

		// check each character
		final var encMap = h.getEncodingMap();
		for (Character c : encMap.keySet()) {
			assertEquals(encMap.get(c), h.encode(c.toString())); // encode works
			assertEquals(c.toString(), h.decode(encMap.get(c))); // decode works
		}
		assertEquals(countLeaves(root), encMap.size());
	}

	private int countLeaves(TreeNode<Character> node) {
		return node == null ? 0
				: isLeaf(node) ? 1 
				: countLeaves(node.getLeftChild()) + countLeaves(node.getRightChild());
	}

	private boolean isLeaf(TreeNode<Character> node) {
		return node.getLeftChild() == null && node.getRightChild() == null;
	}

}
