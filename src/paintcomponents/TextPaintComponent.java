package paintcomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;


public class TextPaintComponent extends PaintComponent{
	
	public String displayingText;
	/**
	 * The bounds of the text area, this property is updated when paint method is invoked.
	 * This class uses Graphics2D's font matrices to determine the bounds
	 */
	protected Rectangle2D bounds;

	/**
	 * @return the displayingText
	 */
	public String getDisplayingText() {
		return displayingText;
	}

	/**
	 * @param displayingText the displayingText to set
	 */
	public void setDisplayingText(String displayingText) {
		this.displayingText = displayingText;
	}

	public TextPaintComponent(String displayingText, int x, int y) {
		super(x, y);
		this.displayingText = displayingText;
	}
	

	@Override
	protected void paintNotSelected(Graphics g) {
		bounds = ((Graphics2D)g).getFontMetrics().getStringBounds(displayingText,g); 

		//draw string starts from bottom left corner, shift to top left
		g.drawString(displayingText, getX(), (int) (getY() + bounds.getHeight()));
	}
	//IMPORTANT :: THIS COMPONENT IS SHIFTED DOWN, WHICH CAUSES PROBLEMS. WHEN FIXING COORDINATES, 
	//WE SHOULD COME UP WITH A COMMON SOLUTION

	@Override
	protected void paintSelected(Graphics g) {
		bounds = ((Graphics2D)g).getFontMetrics().getStringBounds(displayingText,g); 

		//draw string starts from bottom left corner, shift to top left
		g.drawString(displayingText, getX(), (int) (getY() + bounds.getHeight()));
		
	}

	@Override
	public boolean contains(int x2, int y2) {
		
		return new Rectangle(getX(), getY(),(int) bounds.getWidth(), (int)bounds.getHeight()).contains(x2, y2);
		
	}


}
