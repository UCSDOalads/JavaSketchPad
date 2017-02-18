package settings;

import java.awt.Color;

public class Defaults {
	
	private static final Color SELECT_TOOL_ICON_COLOR = Color.BLACK;

	public static Defaults defaults = new Defaults();
	
	private static Color SIMPLE_POINT_COLOR = new Color(200, 41, 47); 
	private static Color SIMPLE_POINT_SELECTED_COLOR = new Color(0, 0, 132);
	private static int SIMPLE_POINT_DEFAULT_SIZE = 10;
	
	private Defaults(){
		
	}
	
	public static Defaults sharedDefaults(){
		
		return defaults;
	}
	
	public int defaultSimplePointSize(){
		return SIMPLE_POINT_DEFAULT_SIZE;
	}
	
	public Color defaultSimplePointColor(){
		return SIMPLE_POINT_COLOR;
	}
	public Color defaultSimplePointSelectedColor(){
		return SIMPLE_POINT_SELECTED_COLOR;
	}

	public Color defaultColorForSelectToolIcon() {
		return SELECT_TOOL_ICON_COLOR;
	}
}
