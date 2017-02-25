package settings;

import java.awt.Color;

public class Defaults {
	
	private static final Color SELECT_TOOL_ICON_COLOR = Color.BLACK;


	public static Defaults defaults = new Defaults();
	
	private static Color SIMPLE_POINT_COLOR = new Color(200, 41, 47); 
	private static Color SIMPLE_POINT_SELECTED_COLOR = new Color(0, 0, 132);
	private static int SIMPLE_POINT_DEFAULT_SIZE = 10;
	private static final Color LINE_SEGMENT_COLOR = SIMPLE_POINT_COLOR;
	private static final Color LINE_SEGMENT_SELECTED_COLOR = SIMPLE_POINT_SELECTED_COLOR;
	private static final int LINE_SEGMENT_STROKE_WIDTH = 5;


	private static final Color DATA_INPUT_TEXTFIELD_COLOR = LINE_SEGMENT_COLOR;
	private static final Color DATA_INPUT_TEXTFIELD_SELECTED_COLOR = LINE_SEGMENT_SELECTED_COLOR;
	
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
	
	public Color defaultColorForLineSegment(){
		return LINE_SEGMENT_COLOR;
	}
	public Color defaultColorForSelectedLineSegment(){
		return LINE_SEGMENT_SELECTED_COLOR;
	}

	public int defaultStrokeWidthForLineSegment() {
		return LINE_SEGMENT_STROKE_WIDTH;
	}

	public Color defaultColorForDataInputTextfield() {
		return DATA_INPUT_TEXTFIELD_COLOR;
	}
	
	public Color defaultColorForSelectedDataInputTextfield(){
		return DATA_INPUT_TEXTFIELD_SELECTED_COLOR;
	}
}
