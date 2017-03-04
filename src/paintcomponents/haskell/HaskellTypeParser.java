package paintcomponents.haskell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.LookAndFeel;

public class HaskellTypeParser {

	private HashMap<String, ArrayList<String>> associatedTypeClasses;
	// (Eq a, Ord a, Eq t) => will become [<a,[Eq, Ord]>, <t,[Eq]>]
	private ArrayList<String> arguments;
	private String returnType;
	private String displayingExprType;

	/**
	 * @return the associatedTypeClasses
	 */
	public HashMap<String, ArrayList<String>> getAssociatedTypeClasses() {
		return associatedTypeClasses;
	}

	/**
	 * @return the arguments
	 */
	public ArrayList<String> getArguments() {
		return arguments;
	}

	/**
	 * @return the returnType
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * @return the displayingExprType
	 */
	public String getDisplayingExprType() {
		return displayingExprType;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	private HaskellEngine haskellEngine;
	private String displayingExpr;
	private String message;
	
	public HaskellTypeParser(String haskellExpr){
		this.displayingExpr = haskellExpr;
		
	}
	
	public boolean parseType(){
			haskellEngine = new HaskellEngine();
	String type = haskellEngine.typeForExpression(displayingExpr);
			if (type.contains("not in scope")) {
						this.message = "Not In Scope : " + type.substring(type.lastIndexOf(":"));
				return false;
			}
			System.out.println(type);
	
			// parse the type
			int doubleColonIndex = type.indexOf("::");
			int equalArrowIndex = type.indexOf("=>");
			System.out.println("Index of :: = " + doubleColonIndex + "; Index of => = " + equalArrowIndex);
			//if no typeclass, use the index of ::
			if(equalArrowIndex != -1){
				// assert name equals
				assert type.substring(0, doubleColonIndex).trim()
						.equals(displayingExpr);
		
				String typeClasses = type
						.substring(doubleColonIndex + 2, equalArrowIndex).trim();
				if (typeClasses.startsWith("(") && typeClasses.endsWith(")")) {
					typeClasses.substring(1, typeClasses.length() - 1);
				}
		
				parseTypeClasses(typeClasses);
			} else {
				equalArrowIndex = doubleColonIndex;
			}
			parseArguments(type.substring(equalArrowIndex + 2).trim());
			this.displayingExprType = type.substring(doubleColonIndex + 2).trim();
			return true;
	}

	private void parseArguments(String argStr) {
		this.arguments = new ArrayList<>();
		String[] arguments = argStr.split("->(?![^(]*\\))");
		for (int i = 0; i < arguments.length - 1; i++) {
			String arg = arguments[i];
			this.arguments.add(arg);
		}
		this.returnType = arguments[arguments.length - 1];
		
	}

	private void parseTypeClasses(String typeClasses) {
		associatedTypeClasses = new HashMap<>();
		String[] tokens = typeClasses.split(",");
		for (String token : tokens) {
			String[] parts = token.split(" ");
			String name = parts[1];
			String className = parts[0];
	
			// add name if not already had one
			if (associatedTypeClasses.containsKey(name)) {
				associatedTypeClasses.get(name).add(className);
			} else {
				associatedTypeClasses.put(name,
						new ArrayList<>(Arrays.asList(className)));
			}
		}
	}
	
	

}
