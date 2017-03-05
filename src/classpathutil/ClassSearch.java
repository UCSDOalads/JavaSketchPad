package classpathutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClassSearch {
	private static ClassSearch sharedInstance = new ClassSearch();

	public static ClassSearch sharedInstance() {
		return sharedInstance;
	}

	private ClassSearch() {
	}

	public ArrayList<String> classesForName(String name) {
		ArrayList<String> result = new ArrayList<>();
		ClassFinder.findClasses(new Visitor<String>() {
			
			@Override
			public boolean visit(String t) {
				result.add(t);

				return true;
			}
		});
		
		return sortAccordingToPrecedence(result, name);
	}

	private ArrayList<String> sortAccordingToPrecedence(ArrayList<String> result,
			String name) {
		ArrayList<String> ret = new ArrayList<>();
		
		
		for (String string : result) {
			String[] comps = string.split("\\.");
			if(comps.length == 0) continue;
			
			if(comps[comps.length - 1].startsWith(name)){
				ret.add(0, string);
			} else {
				ret.add(string);
			}
		}

		return ret;
	}
}
