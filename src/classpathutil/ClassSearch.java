package classpathutil;

import java.util.ArrayList;

public class ClassSearch {
	private static ClassSearch sharedInstance = new ClassSearch();

	public static ClassSearch sharedInstance() {
		return sharedInstance;
	}

	private ArrayList<String> allClasses;

	/**
	 * Constructs a ClassSearch Instance The method
	 */
	private ClassSearch() {
		// load classes

		allClasses = new ArrayList<>();
		ClassFinder.findClasses(new Visitor<String>() {

			@Override
			public boolean visit(String t) {
				allClasses.add(t);
				return true;
			}
		});

	}

	public ArrayList<String> classesForName(String name) {

		ArrayList<String> result = new ArrayList<>();
		for (String string : allClasses) {
			if (string.contains(name)) {
				result.add(string);
			}
		}

		return sortAccordingToPrecedence(result, name);
	}

	private ArrayList<String> sortAccordingToPrecedence(
			ArrayList<String> result, String name) {
		ArrayList<String> sorted = new ArrayList<>();

		for (String string : result) {
			String[] comps = string.split("\\.");
			if (comps.length == 0)
				continue;

			if (comps[comps.length - 1].startsWith(name)) {
				sorted.add(0, string);
			} else {
				sorted.add(string);
			}
		}

		ArrayList<String> ret = new ArrayList<>();
		for (String string : sorted) {
			String[] comps = string.split("\\.");

			if (comps.length > 1) {
				string = comps[comps.length - 1];
				string += " (";

				for (int i = 0; i < comps.length - 1; i++) {
					string += comps[i];

					if (i < comps.length - 2) {
						string += ".";
					}
				}

				string += ")";
			}

			ret.add(string);
		}

		return ret;
	}
}
