package classpathutil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

public class ClassSearch {
	private static ClassSearch sharedInstance = new ClassSearch();

	public static ClassSearch sharedInstance() {
		return sharedInstance;
	}

	private ClassSearch() {
	}

	public ArrayList<Class> classesForName(String name) {

		ArrayList<Class> classes;
		ClassLoader classLoader;
		classes = new ArrayList<Class>();
		classLoader = ClassSearch.class.getClassLoader();
		
		
		try {
			// get all class path roots
			Enumeration<URL> roots = ClassLoader.getSystemClassLoader().getResources("");
			System.out.println(roots);

			// iterate roots
			while (roots.hasMoreElements()) {
				// constrcut a file using the url
				File root = new File(roots.nextElement().getPath());
				classes.addAll(recSearch(root));
			}
		} catch (IOException io) {

		}
		return classes;

	}

	/**
	 * Recursively search all directories
	 * 
	 * @param file the directory to search
	 * @return
	 */
	private ArrayList<Class> recSearch(File directory) {
		ArrayList<Class> classes;
		ClassLoader classLoader;
		classes = new ArrayList<Class>();
		classLoader = ClassSearch.class.getClassLoader();

		if(directory == null || directory.listFiles() == null){
			System.out.println("null");
			return classes;
		}
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				classes.addAll(recSearch(file));
			} else {
				String fileName = file.getName();
				System.out.println("checking file" + file);
				// Check if it's a .class file or a .jar file and handle
				// accordingly.
				if(fileName.endsWith(".class")){
					
				} else if (fileName.endsWith(".jar")){
					
				}
			}
		}
		return classes;
	}
}
