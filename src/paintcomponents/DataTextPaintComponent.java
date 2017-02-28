package paintcomponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import painttools.tools.SelectTool;
import settings.Defaults;

/**
 * A text component with a bounding box,
 * this.bounds is updated in paint method
 * @author chenzb
 *
 */
public class DataTextPaintComponent extends TextPaintComponent {


	private RectanglePaintComponent rect;
	private Color defaultColor;
	private Color selectedColor;
	
	public DataTextPaintComponent(String displayingText, int x, int y) {
		super(displayingText, x, y);
		defaultColor = Defaults.sharedDefaults().defaultColorForDataInputTextfield();
		selectedColor = Defaults.sharedDefaults().defaultColorForSelectedDataInputTextfield();
		rect = new RectanglePaintComponent(x, y, 0, 0);
	}

	
	
	

	@Override
	protected void paintNotSelected(Graphics g) {
		g.setColor(defaultColor);
		((Graphics2D)g).setStroke(new BasicStroke(1));
		super.paintNotSelected(g);
		updateAndPaintBoudingRectangle(g);
		
	}
	

	@Override
	protected void paintSelected(Graphics g) {
		g.setColor(selectedColor);
		((Graphics2D)g).setStroke(new BasicStroke(1));
		super.paintSelected(g);;
		updateAndPaintBoudingRectangle(g);
	}
	
	private void updateAndPaintBoudingRectangle(Graphics g){
		rect.setWidth((int) bounds.getWidth());
		rect.setHeight((int) bounds.getHeight());
		rect.paint(g);
	}
	
	@Override
	public void translate(int i, int j) {
		super.translate(i, j);
		this.rect.translate(i, j);
	}
	
	@Override
	public void select(SelectTool selectTool) {
		super.select(selectTool);
		//pass in null to prevent current selection from being modified
		//only causes changes in apperance
		rect.select(null);
	}

	@Override
	public void deselect(SelectTool selectTool) {
		// TODO Auto-generated method stub
		super.deselect(selectTool);
		rect.deselect(null);
	}
	
	
}
