/**
 * 
 */
package paintcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import settings.Defaults;

/**
 * @author chenzb
 *
 */
public class RectanglePaintComponent extends PaintComponent {
	//TODO Maybe delegate this class to java.awt.Rectangle????

	private int width;
	private int height;
	
	private Color nonSelectColor;
	private Color selectColor;

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @param x
	 * @param y
	 */
	public RectanglePaintComponent(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.nonSelectColor = Defaults.sharedDefaults().defaultColorForRectanglePaintComponent();
		this.selectColor = Defaults.sharedDefaults().defaultColorForSelectedRectanglePaintComponent();
	}

	/* (non-Javadoc)
	 * @see paintcomponents.PaintComponent#paintNotSelected(java.awt.Graphics)
	 */
	@Override
	protected void paintNotSelected(Graphics g) {
		g.setColor(nonSelectColor);
		g.drawRect(getX(), getY(), width, height);

	}

	/* (non-Javadoc)
	 * @see paintcomponents.PaintComponent#paintSelected(java.awt.Graphics)
	 */
	@Override
	protected void paintSelected(Graphics g) {
		g.setColor(selectColor);
		g.drawRect(getX(), getY(), width, height);

	}

	/* (non-Javadoc)
	 * @see paintcomponents.PaintComponent#contains(int, int)
	 */
	@Override
	public boolean contains(int x2, int y2) {
		return new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(x2, y2);
	}

}
