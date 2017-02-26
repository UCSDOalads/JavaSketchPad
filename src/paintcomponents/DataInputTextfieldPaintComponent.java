package paintcomponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import settings.Defaults;

public class DataInputTextfieldPaintComponent extends DataTextPaintComponent {


	private static final int HORIZONTAL_OFFSET = 10;
	private DataFromPoint<String> fromPoint;
	
	public DataInputTextfieldPaintComponent(String displayingText, int x,
			int y) {
		super(displayingText, x, y);
		this.fromPoint = new DataFromPoint<>(x, y);
	}
	
	@Override
	protected void paintSelected(Graphics g) {
		super.paintSelected(g);
		updateFromPointPosition();
		fromPoint.paint(g);
	}
	
	@Override
	protected void paintNotSelected(Graphics g) {
		super.paintNotSelected(g);
		updateFromPointPosition();
		fromPoint.paint(g);
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
