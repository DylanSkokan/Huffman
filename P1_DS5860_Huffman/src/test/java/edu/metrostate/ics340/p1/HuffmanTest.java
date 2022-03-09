/* Author: Ralph A. Foy
 * Class : ICS340 Spring 2022
 * 
 *       Copyright (c) 2022 
 *       Authorization is given to students enrolled in the course to reproduce 
 *       this material exclusively for their own personal use.
 */
package edu.metrostate.ics340.p1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

class HuffmanTest {

	private static final Logger LOGGER = Logger.getLogger(HuffmanTest.class.getName());

	@Test
	void testFile1() {
		Path resourceDirectory = Path.of("src","test","resources", "evesDiary.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		LOGGER.info("Path: " + absolutePath );
		assertTrue(myFile.exists());
	}
	
	@Test
	void testFile2() {
		Path resourceDirectory = Path.of("src","test","resources", "monkeysPaw.txt");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File myFile = resourceDirectory.toFile();
		LOGGER.info("Path: " + absolutePath );
		assertTrue(myFile.exists());
	}
}