package script;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TokenizerTest {

	@Test
	public void testBaseCase() {
		Tokenizer tokenizer = new Tokenizer("a b c d");
		ArrayList<String> tokens = tokenizer.getTokens();
for (int i = 0; i < tokens.size(); i++) {
		  System.out.print(tokens.get(i));
	}
		assertArrayEquals(new String[] { "a", "b", "c", "d" }, tokens.toArray());
	}
	
	@Test
	public void testSlash() {
		Tokenizer tokenizer = new Tokenizer("a\\ b c d");
		ArrayList<String> tokens = tokenizer.getTokens();
		assertArrayEquals(new String[] { "a b", "c", "d" }, tokens.toArray());
	}

	@Test
	public void testQuote() {
		Tokenizer tokenizer = new Tokenizer("\"a b\" c d");
		ArrayList<String> tokens = tokenizer.getTokens();
		assertArrayEquals(new String[] { "a b", "c", "d" }, tokens.toArray());
	}

	@Test
	public void testSlashAndQuote() {
		// User input: --> "a\" b" c d
		Tokenizer tokenizer = new Tokenizer("\"a\\\" b\" c d");
		ArrayList<String> tokens = tokenizer.getTokens();
		assertArrayEquals(new String[] { "a\" b", "c", "d" }, tokens.toArray());
	}
}
