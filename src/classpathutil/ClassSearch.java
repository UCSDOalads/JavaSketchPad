package classpathutil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

public class ClassSearch {
	private static ClassSearch sharedInstance = new ClassSearch();
	ArrayList<Class> classes;
	ClassLoader classLoader;
	
	public static ClassSearch sharedInstance() {
		return sharedInstance;
	}
	
	private ClassSearch() {
		classes = new ArrayList<Class>();
		classLoader = ClassSearch.class.getClassLoader();
	}
	
	public ArrayList<Class> classesForName(String name) {
		try {
			//get all class path roots
			Enumeration<URL> roots = classLoader.getResources("");
			
			// iterate roots
			while(roots.hasMoreElements()) {
				
				// constrcut a file using the url
				File root = new File(roots.nextElement().getPath());
				for (File file : root.listFiles()) {
					if (file.isDirectory()) {
				    // Loop through its listFiles() recursively.
					} else {
				    String fileName = file.getName();
				    // Check if it's a .class file or a .jar file and handle accordingly.
					}
				}
			}
		} catch(IOException io) {
			
		}
		return classes;
		
	}
}
