package actions;

import ui.PaintPanel;

public class AddComponentActions extends MenuBarPaintAction{

	protected int x;
	protected int y;
	
	public AddComponentActions(PaintPanel panel) {
		super(panel);
		
		setDefaultXY();
	}

	/**
	 * set the default point to the middle of paint panel
	 */
	public void setDefaultXY(){
		x = panel.getWidth()/2;
		y = panel.getHeight()/2;
		
	}
	
	
	public void setX(int newX){
		x = newX;
	}
	
	public void setY(int newY){
		y = newY;
	}
	
	public void setXY(int newX, int newY){
		x = newX;
		y = newY;
	}
	
	@Override
	public boolean canPerformAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return null;
	}

}
