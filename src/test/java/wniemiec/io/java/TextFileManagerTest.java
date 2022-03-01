package wniemiec.io.java;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TextFileManagerTest {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private static final Path TXT_FILE;
	private static final List<String> TXT_FILE_CONTENT;
	private static final Charset TXT_FILE_CHARSET;
	
	
	//-------------------------------------------------------------------------
	//		Initialization blocks
	//-------------------------------------------------------------------------
	static {
		Path tmpDir = Path.of(System.getProperty("java.io.tmpdir"));
		
		TXT_FILE = tmpDir.resolve("txt-tmp-file.txt");
		TXT_FILE_CONTENT = List.of("hello", "world!");
		TXT_FILE_CHARSET = StandardCharsets.ISO_8859_1;
	}
	
	
	//-------------------------------------------------------------------------
	//		Test hooks
	//-------------------------------------------------------------------------
	@BeforeEach
	void beforeEachTest() throws IOException {
		Files.deleteIfExists(TXT_FILE);
	}
	
	
	//-------------------------------------------------------------------------
	//		Tests
	//-------------------------------------------------------------------------|
	@Test
	void testWriteAndRead() throws IOException {
		TextFileManager txtManager = new TextFileManager(TXT_FILE, TXT_FILE_CHARSET);
		
		txtManager.writeLines(TXT_FILE_CONTENT);
				
		Assertions.assertEquals(TXT_FILE_CONTENT, txtManager.readLines());
	}
	
	@Test
	void testConstructorWithNullFile() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new TextFileManager(null, TXT_FILE_CHARSET);
		});
	}
	
	@Test
	void testConstructorWithNullEncoding() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new TextFileManager(TXT_FILE, null);
		});
	}
	
	@Test
	void testWriteNullLines() throws IOException {
		TextFileManager txtManager = new TextFileManager(TXT_FILE, TXT_FILE_CHARSET);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			txtManager.writeLines(null);
		});
	}

	@Test
	void testGetFileLineThatContainsNull() throws IOException {
		TextFileManager txtManager = new TextFileManager(TXT_FILE, TXT_FILE_CHARSET);

		txtManager.writeLines(TXT_FILE_CONTENT);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			txtManager.getFileLineThatContains(null);
		});
	}

	@Test
	void testGetFileLineThatContainsSomethingUsingFalsePath() 
	throws IOException {
		TextFileManager txtManager = new TextFileManager(TXT_FILE, TXT_FILE_CHARSET);

		Assertions.assertThrows(IllegalStateException.class, () -> {
			txtManager.getFileLineThatContains("hello");
		});
	}

	@Test
	void testGetFileLineThatContainsSomething() throws IOException {
		TextFileManager txtManager = new TextFileManager(TXT_FILE, TXT_FILE_CHARSET);

		txtManager.writeLines(TXT_FILE_CONTENT);
		
		Assertions.assertEquals("hello", txtManager.getFileLineThatContains("hello"));
	}
}
