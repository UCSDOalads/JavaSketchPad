package script;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import paintcomponents.SimplePoint;
import ui.PaintPanel;

/**
 * Interpret and execute 'generatePolygon' scripts 
 * @author Xiaoquan Jiang
 */
public class InterpreterGeneratePolygonAction {

	private PaintPanel panel;
	
	public InterpreterGeneratePolygonAction(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		this.panel = panel;
		performGeneratePolygon();
	}

	private void performGeneratePolygon() {
		try {
			@SuppressWarnings("unchecked")
			String str = generatePolygon((ArrayList) (panel.getSelectTool()
					.getSelectedComponents()));

			JOptionPane.showMessageDialog(panel, "Already Saved To Downloads: \n\n"
					+ str);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(panel, e);

		}
	}

	private String generatePolygon(ArrayList<SimplePoint> selectedComponents) {

		// get width and height for target
		String widthStr = JOptionPane
				.showInputDialog("Enter generated WIDTH for this polygon");
		String heightStr = JOptionPane
				.showInputDialog("Enter the HEIGHT for this polygon");
		int width = Integer.parseInt(widthStr);
		int height = Integer.parseInt(heightStr);
		String nameOfPolygon = JOptionPane
				.showInputDialog("Enter the NAME for this polygon");

		StringBuilder builder = new StringBuilder();
		builder.append("import java.awt.Polygon;\npublic class " + nameOfPolygon
				+ "{\n\n\n");
		builder
				.append("\tpublic static Polygon getPolygon(){\n\n\t\tPolygon p = new Polygon();\n\n");

		// get a bounds for current polygon
		Polygon poly = new Polygon();
		for (SimplePoint paintComponent : selectedComponents) {
			poly.addPoint(paintComponent.getX(), paintComponent.getY());

		}
		Rectangle actual = poly.getBounds();

		double xfactor = (double) actual.getWidth() / width;
		double yfactor = (double) actual.getHeight() / height;

		for (SimplePoint paintComponent : selectedComponents) {
			int x = (int) ((paintComponent.getX() - actual.getX()) / xfactor);
			int y = (int) ((paintComponent.getY() - actual.getY()) / yfactor);
			builder.append("\t\tp.addPoint(" + x + ", " + y + ");\n");
		}

		builder.append("\n\n\t\treturn p;\n\t}\n\n}");

		String home = System.getProperty("user.home");
		File file = new File(home + "/Downloads/" + nameOfPolygon + ".java");
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

		return builder.toString();
	}
}
