/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for handling text files.
 */
public class TextFileManager {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private Path textFile;
	private Charset fileEncoding;
	
	
	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	/**
	 * Handles text files.
	 * 
	 * @param		textFile Text file
	 * @param		fileEncoding File encoding
	 * 
	 * @throws		IllegalArgumentException If textFile or file encoding is null
	 */
	public TextFileManager(Path textFile, Charset fileEncoding) {
		if (textFile == null)
			throw new IllegalArgumentException("Text file cannot be null");
		
		if (fileEncoding == null)
			throw new IllegalArgumentException("File encoding cannot be null");
		
		this.textFile = textFile;
		this.fileEncoding = fileEncoding;
	}
	
	
	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	/**
	 * Gets all lines from a file and puts them in a list.
	 * 
	 * @return		List containing all lines of the file
	 * 
	 * @throws		IOException If an I/O error occurs opening the file
	 */
	public List<String> readLines() throws IOException {
		List<String> lines = new ArrayList<>();
		String currentLine;
		
		try (BufferedReader br = Files.newBufferedReader(textFile, fileEncoding)) {
			while ((currentLine = br.readLine()) != null) {
				lines.add(currentLine);
			}
		}
		
		return lines;
	}
	
	/**
	 * Writes all items in a string list to a file.
	 * 
	 * @param		lines Content to be written
	 * 
	 * @throws		IOException If an I/O error occurs while writing the file
	 * @throws		IllegalArgumentException If lines is null
	 */
	public void writeLines(List<String> lines) throws IOException {
		if (lines == null)
			throw new IllegalArgumentException("Lines cannot be null");
		
		OpenOption[] options = {
				StandardOpenOption.CREATE
		};
		
		Files.deleteIfExists(textFile);
		Files.createDirectories(textFile.getParent());
		
		try (BufferedWriter bw = Files.newBufferedWriter(textFile, fileEncoding, options)) {
			for (String line : lines) {
				bw.write(line.replaceAll("\\n", ""));
				bw.newLine();
			}
		}
	}
}
