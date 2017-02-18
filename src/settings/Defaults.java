package settings;

import java.awt.Color;

public class Defaults {
	
	public static Defaults defaults = new Defaults();
	
	private Defaults(){
		
	}
	
	public static Defaults sharedDefaults(){
		
		return defaults;
	}
	
	public int defaultSimplePointSize(){
		return 10;
	}
	
	public Color defaultSimplePointColor(){
		return new Color(200, 41, 47); 
	}
	public Color defaultSimplePointSelectedColor(){
		return new Color(136, 0, 32); 
	}
}
