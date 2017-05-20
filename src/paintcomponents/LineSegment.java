package paintcomponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import file.PanelIO;
import settings.Defaults;
import ui.PaintPanel;

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

	/**
	 * Constructs a line segment with default width and default height.
	 * 
	 * @param fromPoint
	 * @param toPoint
	 */
	public LineSegment(SimplePoint fromPoint, SimplePoint toPoint) {
		this(fromPoint, toPoint, Defaults.sharedDefaults()
				.defaultColorForLineSegment(), Defaults.sharedDefaults()
				.defaultColorForSelectedLineSegment(), Defaults
				.sharedDefaults().defaultStrokeWidthForLineSegment());

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

		double lineXDis = fromPoint.getX() - toPoint.getX();
		double lineYDis = toPoint.getY() - fromPoint.getY();
		double constantTerm = fromPoint.getY() * toPoint.getX()
				- toPoint.getY() * fromPoint.getX();

		// Calculate point to line distance with formula.
		double pointToLineDis = Math
				.abs((lineYDis * x + lineXDis * y + constantTerm)
						/ Math.sqrt(Math.pow(lineYDis, 2)
								+ Math.pow(lineXDis, 2)));

		// If the point is within 3 pixels of the line, return true.
		if (pointToLineDis <= 3 + strokeWidth / 2) {
			return true;

		}
		return false;
	}

	@Override
	public void translate(int i, int j) {
		super.translate(i, j);
		// if from and to points are not selected, translate them as well
		if (this.fromPoint.isSelected() == false) {
			this.fromPoint.translate(i, j);
		}
		if (this.toPoint.isSelected() == false) {
			this.toPoint.translate(i, j);
		}

	}

	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);

		// build elements
		Element main = doc.createElement("linesegment");

		Element fromPointElem = doc.createElement("frompoint");
		Element toPointElem = doc.createElement("topoint");
		Element defaultColorElem = doc.createElement("defaultcolor");
		Element selectColorElem = doc.createElement("selectcolor");
		Element strokeWidthElem = doc.createElement("strokewidth");

		// append relationship
		main.appendChild(fromPointElem);
		main.appendChild(toPointElem);
		main.appendChild(defaultColorElem);
		main.appendChild(selectColorElem);
		main.appendChild(strokeWidthElem);
		rootElement.appendChild(main);

		// fill data
		fromPointElem.setAttribute("id",
				Long.toString(fromPoint.getComponentID()));
		toPointElem.setAttribute("id", Long.toString(toPoint.getComponentID()));
		XMLEncodingUtilForPaintComponents.attachRGB(defaultColor,
				defaultColorElem, doc);
		XMLEncodingUtilForPaintComponents.attachRGB(selectColor,
				selectColorElem, doc);
		strokeWidthElem.appendChild(doc.createTextNode(Double
				.toString(strokeWidth)));

	}

	/**
	 * 
	 * Recover the line segment from a element that should be operated by
	 * saveToElement
	 * 
	 * The implementation searches all panel's paintcomponents to find the
	 * matching ids using PanelIO.idMapping
	 * 
	 * @param rootElement
	 * @param panel
	 */
	public LineSegment(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		Element main = (Element) rootElement
				.getElementsByTagName("linesegment").item(0);
		Element fromPointElement = (Element) main.getElementsByTagName(
				"frompoint").item(0);
		Element toPointElem = (Element) main.getElementsByTagName("topoint")
				.item(0);
		Element defaultColorElem = (Element) main.getElementsByTagName(
				"defaultcolor").item(0);
		Element selectColorElem = (Element) main.getElementsByTagName(
				"selectcolor").item(0);
		Element strokeWidthElem = (Element) main.getElementsByTagName(
				"strokewidth").item(0);

		defaultColor = XMLEncodingUtilForPaintComponents
				.getRGB(defaultColorElem);
		selectColor = XMLEncodingUtilForPaintComponents.getRGB(selectColorElem);
		strokeWidth = Double.parseDouble(strokeWidthElem.getTextContent());
		stroke = new BasicStroke((float) strokeWidth);

		// append from and to points

		ArrayList<PaintComponent> paintComponents = panel.getPaintComponents();

		// select only the paint components to check ID
		long fromPointID = PanelIO.idMapping.get(Long
				.parseLong(fromPointElement.getAttribute("id")));
		long toPointID = PanelIO.idMapping.get(Long.parseLong(toPointElem
				.getAttribute("id")));

		for (PaintComponent paintComponent : paintComponents) {
			if (paintComponent.getComponentID() == fromPointID) {
				fromPoint = (SimplePoint) paintComponent;
			} else if (paintComponent.getComponentID() == toPointID) {
				toPoint = (SimplePoint) paintComponent;
			}
		}
	}

}
