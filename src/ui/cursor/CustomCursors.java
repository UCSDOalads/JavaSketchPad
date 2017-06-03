package ui.cursor;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class CustomCursors {
	static Toolkit toolkit = Toolkit.getDefaultToolkit();

	
	public static Cursor addComponentcursor(){
		Image image = toolkit.getImage("./images/addCursor.png");
		Cursor c = toolkit.createCustomCursor(image, new Point(0,0), "cursor");
		return c;
	}

}
