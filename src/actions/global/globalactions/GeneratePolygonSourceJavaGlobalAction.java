package actions.global.globalactions;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import paintcomponents.SimplePoint;
import actions.global.GlobalPaintAction;
import ui.PaintPanel;

public class GeneratePolygonSourceJavaGlobalAction extends GlobalPaintAction {

	private int width;
	private int height;
	private String name;
	private ArrayList<SimplePoint> selectedComponents;
	private String result;
	
	public void setSelectedComponents(ArrayList<SimplePoint> selectedComponents) {
		this.selectedComponents = selectedComponents;
	}
	
	public void setWidth(int w) {
		width = w;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getResult() {
		return result;
	}
	
	@Override
	protected void execute(PaintPanel panel) {
		StringBuilder builder = new StringBuilder();
		builder.append("import java.awt.Polygon;\npublic class " + name + "{\n\n\n");
		builder.append("\tpublic static Polygon getPolygon(){\n\n\t\tPolygon p = new Polygon();\n\n");
		
		//get a bounds for current polygon
		Polygon poly = new Polygon();
		for (SimplePoint paintComponent : selectedComponents) {
			poly.addPoint(paintComponent.getX(), paintComponent .getY());

		}
		Rectangle actual = poly.getBounds();

		double xfactor = (double)actual.getWidth() / width;
		double yfactor = (double)actual.getHeight() / height;
		
		for (SimplePoint paintComponent : selectedComponents) {
			int x = (int)((paintComponent.getX() - actual.getX()) / xfactor);
			int y = (int)((paintComponent.getY() - actual.getY()) / yfactor);
			builder.append("\t\tp.addPoint(" + x + ", " + y + ");\n");
		}
		
		builder.append("\n\n\t\treturn p;\n\t}\n\n}");
		
		String home = System.getProperty("user.home");
		File file = new File(home+"/Downloads/" + name + ".java"); 		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(builder.toString().getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result = builder.toString();
	}

}
