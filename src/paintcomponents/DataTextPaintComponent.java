package paintcomponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		rect = new RectanglePaintComponent(getX(), getY(), (int)this.bounds.getWidth(), (int)this.bounds.getHeight());
		//select rectangle according to current select status
		if(isSelected())rect.select(); else rect.deselect();
		rect.paint(g);
	}

}
