package classpathutil;

import org.junit.Test;

public class ClassSearchTest {

	@Test
	public void test() {
		ClassSearch search = ClassSearch.sharedInstance();
		search.classesForName("somename");
		
	}

}
