package paintcomponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import settings.Defaults;

public class DataInputTextfieldPaintComponent extends TextPaintComponent {

	private static final int HORIZONTAL_OFFSET = 10;
	private DataFromPoint<String> fromPoint;
	private RectanglePaintComponent rect;
	private Color defaultColor;
	private Color selectedColor;
	

	public DataInputTextfieldPaintComponent(String displayingText, int x,
			int y) {
		super(displayingText, x, y);
		fromPoint = new DataFromPoint<>(x - 50, y);
		defaultColor = Defaults.sharedDefaults().defaultColorForDataInputTextfield();
		selectedColor = Defaults.sharedDefaults().defaultColorForSelectedDataInputTextfield();
	}
	
	

	@Override
	protected void paintNotSelected(Graphics g) {
		g.setColor(defaultColor);
		((Graphics2D)g).setStroke(new BasicStroke(1));
		super.paintNotSelected(g);
		updateFromPointPosition();
		updateAndPaintBoudingRectangle(g);
		fromPoint.paintNotSelected(g);
		
	}
	

	@Override
	protected void paintSelected(Graphics g) {
		g.setColor(selectedColor);
		((Graphics2D)g).setStroke(new BasicStroke(1));
		super.paintSelected(g);;
		updateFromPointPosition();
		updateAndPaintBoudingRectangle(g);
		fromPoint.paintSelected(g);
	}
	
	private void updateAndPaintBoudingRectangle(Graphics g){
		rect = new RectanglePaintComponent(getX(), getY(), (int)this.bounds.getWidth(), (int)this.bounds.getHeight());
		//select rectangle according to current select status
		if(isSelected())rect.select(); else rect.deselect();
		rect.paint(g);
	}

	/**
	 * This method will use the protected bounds, which will be updated in super.paint[Not]Selected.
	 * Make sure you've already invoked super's paintNotSelectedMethod before invoking this one.
	 */
	private void updateFromPointPosition(){
		this.fromPoint.setX((int) (getX() + this.bounds.getWidth() + HORIZONTAL_OFFSET));
		this.fromPoint.setY((int) (getY() + this.bounds.getHeight() / 2));
	}

	@Override
	public void translate(int i, int j) {
		super.translate(i, j);
		this.fromPoint.translate(i, j);
	}
	
	@Override
	public boolean contains(int x, int y) {

		return fromPoint.contains(x, y) || super.contains(x, y);
	}

	public void inputData(String s) {
		fromPoint.offer(s);
		this.setDisplayingText(s);
	}
	
}
