/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
	private final Path textFile;
	private final Charset fileEncoding;
	
	
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
		if (textFile == null) {
			throw new IllegalArgumentException("Text file cannot be null");
		}
		
		if (fileEncoding == null) {
			throw new IllegalArgumentException("File encoding cannot be null");
		}
		
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
	 * @throws		IllegalStateException If file does not exist
	 */
	public List<String> readLines() throws IOException {
		List<String> lines = new ArrayList<>();
		String currentLine;
		
		try (BufferedReader buffer = buildBufferReader()) {
			while ((currentLine = buffer.readLine()) != null) {
				lines.add(currentLine);
			}
		}
		
		return lines;
	}

	private BufferedReader buildBufferReader() throws IOException {
		if (!Files.exists(textFile)) {
			throw new IllegalStateException("Cannot read from a non-existent file");
		}

		return Files.newBufferedReader(textFile, fileEncoding);
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
		if (lines == null) {
			throw new IllegalArgumentException("Lines cannot be null");
		}
		
		setUpOutput();
		
		try (BufferedWriter buffer = buildBufferWriter()) {
			for (String line : lines) {
				buffer.write(line.replace("\\n", ""));
				buffer.newLine();
			}
		}
	}

	private void setUpOutput() throws IOException {
		Files.deleteIfExists(textFile);
		Files.createDirectories(textFile.getParent());
	}

	private BufferedWriter buildBufferWriter() throws IOException {
		OpenOption[] options = {
			StandardOpenOption.CREATE
	};

		return Files.newBufferedWriter(textFile, fileEncoding, options);
	}

	/**
	 * Searches for a line that contains a sequence.
	 * 
	 * @param		sequence Sequence to be searched
	 * 
	 * @return		First line that contains the sequence or null if there is 
	 * no line with such sequence
	 * 
	 * @throws		IOException If an I/O error occurs while reading the file
	 * @throws		IllegalArgumentException If sequence is null
	 * @throws		IllegalStateException If file does not exist
	 */
	public String getFileLineThatContains(CharSequence sequence) throws IOException {
		if (sequence == null) {
			throw new IllegalArgumentException("Sequence cannot be null");
		}

        String targetLine = null;

        try (BufferedReader buffer = buildBufferReader()) {
            for(String line = buffer.readLine(); line != null; line = buffer.readLine()) {
                if (line.contains(sequence)) {
                    targetLine = line;
                    break;
                }
            }
        }

        return targetLine;
    }
}
