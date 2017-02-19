package paintcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import settings.Defaults;

//TODO DEfault color, width selection
public class LineSegment extends PaintComponent {

	private SimplePoint fromPoint;
	private SimplePoint toPoint;

	private Color defaultColor;
	private Color selectColor;

	/**
	 * @return the toPoint
	 */
	public SimplePoint getToPoint() {
		return toPoint;
	}

	/**
	 * @param toPoint
	 *            the toPoint to set
	 */
	public void setToPoint(SimplePoint toPoint) {
		this.toPoint = toPoint;
	}

	/**
	 * @return the fromPoint
	 */
	public SimplePoint getFromPoint() {
		return fromPoint;
	}

	/**
	 * @param fromPoint
	 *            the fromPoint to set
	 */
	public void setFromPoint(SimplePoint fromPoint) {
		this.fromPoint = fromPoint;
	}

	public LineSegment(SimplePoint fromPoint, SimplePoint toPoint,
			Color defaultColor, Color selectColor) {
		super(0, 0);
		this.fromPoint = fromPoint;
		this.toPoint = toPoint;
		this.defaultColor = defaultColor;
		this.selectColor = selectColor;
	}

	public LineSegment(SimplePoint fromPoint, SimplePoint toPoint) {
		this(fromPoint, toPoint,
				Defaults.sharedDefaults().defaultColorForLineSegment(),
				Defaults.sharedDefaults().defaultColorForSelectedLineSegment());

	}

	@Override
	protected void paintNotSelected(Graphics g) {
		g.setColor(defaultColor);
		g.drawLine(fromPoint.getX(), fromPoint.getY(), toPoint.getX(),
				toPoint.getY());

	}

	@Override
	protected void paintSelected(Graphics g) {
		g.setColor(selectColor);
		g.drawLine(fromPoint.getX(), fromPoint.getY(), toPoint.getX(),
				toPoint.getY());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(fromPoint.getX(), fromPoint.getY(),
				toPoint.getX() - fromPoint.getX(),
				toPoint.getY() - fromPoint.getY());
	}

}
