package ui.helper.methodinput;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodSearch {

	private ArrayList<String> allMethods;

	/**
	 * Constructs a MethodSearch Instance
	 * The method 
	 */
	public MethodSearch(Class mtdClass) {
		//load classes
		allMethods = new ArrayList<>();
		
		Method[] methods = mtdClass.getMethods();
		
		for (Method mtd : methods) {
			allMethods.add(mtd.toString());
		}

	}

	public ArrayList<String> methodsForName(String name) {
		
		ArrayList<String> result = new ArrayList<>();
		for (String string : allMethods) {
			if(string.toUpperCase().contains(name.toUpperCase())){
				result.add(string); 
			}
		}
		
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
