package actions;

import paintcomponents.PaintComponent;
import ui.PaintPanel;

public abstract class ZoomAction extends MenuBarPaintAction{

	private double zoomValue;
	private int centerX, centerY;
	
	public ZoomAction(PaintPanel panel) {
		super(panel);
		centerX = panel.getWidth() / 2;
		centerY = panel.getHeight() / 2;

	}
	
	public boolean canPerformAction() {
		return true;
	}
	
	public void performAction() {
		
		
		for ( PaintComponent com: panel.getPaintComponents() ) {
			int xDifference = com.getX() - centerX;
			int yDifference = com.getY() - centerY;
			
			com.translate((int)Math.floor((xDifference * getZoomValue())), (int)Math.floor(yDifference * getZoomValue()));
		}
		panel.repaint();
	}
	
	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public double getZoomValue() {
		return zoomValue;
	}

	public void setZoomValue(double value) {
		zoomValue = value;
	}

}
