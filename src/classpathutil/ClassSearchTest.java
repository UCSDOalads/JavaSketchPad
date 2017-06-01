package classpathutil;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ClassSearchTest {

	@Test
	public void test() {
		ClassSearch search = ClassSearch.sharedInstance();
		ArrayList<String> classesForName = search.classesForName("System");
		
		assertTrue(classesForName.contains("System (java.lang)"));
		
	}

}
