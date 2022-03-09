package edu.metrostate.ics340.p1.ds5860;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

/**
 * <h2>HuffmanTester</h2> Provides a variety of JUnitTests to demonstrate the
 * functionality of the Huffman program.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class HuffmanTester {

	private static final Logger LOGGER = Logger.getLogger(HuffmanTester.class.getName());

	@Test
	void evesDiary() throws IOException {
		Path resourceDirectory = Path.of("src", "test", "resources", "evesDiary.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		Path fileName = Path.of(absolutePath);
		String originalText = Files.readString(fileName);
		Huffman h = Huffman.build(absolutePath);

		LOGGER.info("Path: " + absolutePath);

		assertTrue(myFile.exists());
		assertFalse(originalText.equals(h.decode(h.encode(originalText))));
		assertTrue(originalText.equalsIgnoreCase(h.decode(h.encode(originalText))));
	}

	@Test
	void monkeysPaw() throws IOException {
		Path resourceDirectory = Path.of("src", "test", "resources", "monkeysPaw.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		Path fileName = Path.of(absolutePath);
		String originalText = Files.readString(fileName);
		Huffman h = Huffman.build(absolutePath);

		LOGGER.info("Path: " + absolutePath);

		assertTrue(myFile.exists());
		assertFalse(originalText.equals(h.decode(h.encode(originalText))));
		assertTrue(originalText.equalsIgnoreCase(h.decode(h.encode(originalText))));
	}

	@Test
	void huffman8() throws IOException {
		Path resourceDirectory = Path.of("src", "test", "resources", "huffman8.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		Path fileName = Path.of(absolutePath);
		String originalText = Files.readString(fileName);
		Huffman h = Huffman.build(absolutePath);

		LOGGER.info("Path: " + absolutePath);

		assertTrue(myFile.exists());
		assertTrue(originalText.equalsIgnoreCase(h.decode(h.encode(originalText))));
	}

	@Test
	void twoLetterTest() throws IOException {
		Path resourceDirectory = Path.of("src", "test", "resources", "twoLetterTest.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		Path fileName = Path.of(absolutePath);
		String originalText = Files.readString(fileName);
		Huffman h = Huffman.build(absolutePath);

		LOGGER.info("Path: " + absolutePath);

		assertTrue(myFile.exists());
		assertTrue(originalText.equalsIgnoreCase(h.decode(h.encode(originalText))));
	}

	@Test
	void oneLetterTest() throws IOException {
		Path resourceDirectory = Path.of("src", "test", "resources", "twoLetterTest.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		Path fileName = Path.of(absolutePath);
		String originalText = Files.readString(fileName);
		Huffman h = Huffman.build(absolutePath);

		LOGGER.info("Path: " + absolutePath);

		assertTrue(myFile.exists());
		assertTrue(originalText.equalsIgnoreCase(h.decode(h.encode(originalText))));
	}

	@Test
	void emptyFile() throws IOException {
		Path resourceDirectory = Path.of("src", "test", "resources", "emptyFile.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		Path fileName = Path.of(absolutePath);
		String originalText = Files.readString(fileName);
		Huffman h = Huffman.build(absolutePath);

		LOGGER.info("Path: " + absolutePath);

		assertTrue(myFile.exists());
		assertTrue(originalText.equalsIgnoreCase(h.decode(h.encode(originalText))));
	}

	@Test
	void randomTextTest() throws IOException {
		Path resourceDirectory = Path.of("src", "test", "resources", "random.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();

		if (myFile.exists()) {
			myFile.delete();
			myFile.createNewFile();
		} else {
			myFile.createNewFile();
		}

		int charRange = 63;
		Random rand = new Random();
		String randomText = "";
		for (int i = 0; i < 1000; i++) {
			char c = (char) (rand.nextInt(charRange) + 'a');
			randomText += c;
		}

		FileWriter myWriter = new FileWriter(myFile);
		myWriter.write(randomText);
		myWriter.close();

		Path fileName = Path.of(absolutePath);
		String originalText = Files.readString(fileName);
		Huffman h = Huffman.build(absolutePath);

		LOGGER.info("Path: " + absolutePath);

		assertTrue(myFile.exists());
		assertTrue(originalText.equalsIgnoreCase(h.decode(h.encode(originalText))));
	}

	@Test
	void testUnkownEncoding() throws IOException {
		Path resourceDirectory = Path.of("src", "test", "resources", "unknownEncode.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		Path fileName = Path.of(absolutePath);
		String originalText = Files.readString(fileName);
		Huffman h = Huffman.build(absolutePath);

		LOGGER.info("Path: " + absolutePath);

		assertThrows(IllegalArgumentException.class, () -> h.encode("Z"));
		assertTrue(myFile.exists());
		assertTrue(originalText.equalsIgnoreCase(h.decode(h.encode(originalText))));
	}

	@Test
	void testUnkownDecoding() throws IOException {
		Path resourceDirectory = Path.of("src", "test", "resources", "unknownDecode.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		Path fileName = Path.of(absolutePath);
		String originalText = Files.readString(fileName);
		Huffman h = Huffman.build(absolutePath);

		LOGGER.info("Path: " + absolutePath);

		assertThrows(IllegalArgumentException.class, () -> h.decode("00000100002000000"));
		assertTrue(myFile.exists());
		assertTrue(originalText.equalsIgnoreCase(h.decode(h.encode(originalText))));
	}
}