package paintcomponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import settings.Defaults;

//TODO DEfault color, width selection
public class LineSegment extends PaintComponent {

	private SimplePoint fromPoint;
	private SimplePoint toPoint;

	private Color defaultColor;
	private Color selectColor;
	private Stroke stroke;
	private double strokeWidth;

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
			Color defaultColor, Color selectColor, int strokeWidth) {
		super(0, 0);
		this.fromPoint = fromPoint;
		this.toPoint = toPoint;
		this.defaultColor = defaultColor;
		this.selectColor = selectColor;
		this.stroke = new BasicStroke(strokeWidth);
		this.strokeWidth = strokeWidth;
	}

	public LineSegment(SimplePoint fromPoint, SimplePoint toPoint) {
		this(fromPoint, toPoint,
				Defaults.sharedDefaults().defaultColorForLineSegment(),
				Defaults.sharedDefaults().defaultColorForSelectedLineSegment(),
				Defaults.sharedDefaults().defaultStrokeWidthForLineSegment());

	}

	@Override
	protected void paintNotSelected(Graphics g) {
		g.setColor(defaultColor);
		((Graphics2D) g).setStroke(stroke);
		g.drawLine(fromPoint.getX(), fromPoint.getY(), toPoint.getX(),
				toPoint.getY());

	}

	@Override
	protected void paintSelected(Graphics g) {
		g.setColor(selectColor);
		((Graphics2D) g).setStroke(stroke);
		g.drawLine(fromPoint.getX(), fromPoint.getY(), toPoint.getX(),
				toPoint.getY());
	}

	@Override
	public boolean contains(int x, int y) {
		// if either end points contains, I do not contain
		if (fromPoint.contains(x, y) || toPoint.contains(x, y)) {
			return false;
		}
		// else return the D(curPoint , fromPoint) + D(curPoint, toPoint) ==
		// D(fromPoint, toPoint)
		double distanceBetweenXYandFrom = Math
				.sqrt(Math.pow(fromPoint.getX() - x, 2)
						+ Math.pow(fromPoint.getY() - y, 2));
		double distanceBetweenXYandTo = Math
				.sqrt(Math.pow(toPoint.getX() - x, 2)
						+ Math.pow(toPoint.getY() - y, 2));
		double distanceBetweenFromAndTo = Math
				.sqrt(Math.pow(toPoint.getX() - fromPoint.getX(), 2)
						+ Math.pow(toPoint.getY() - fromPoint.getY(), 2));

		// checking delta distance
		// Note: this calculation is only an approximation
		if (Math.abs(distanceBetweenFromAndTo - distanceBetweenXYandFrom
				- distanceBetweenXYandTo) < Math.sqrt(strokeWidth)) {
			return true;

		}
		return false;
	}

	@Override
	public void translate(int i, int j) {
		super.translate(i, j);
		// if from and to points are not selected, translate them as well
		if (this.fromPoint.isSelected() == false){
			this.fromPoint.translate(i, j);
		}
		if (this.toPoint.isSelected() == false){
			this.toPoint.translate(i, j);
		}

	}

}
