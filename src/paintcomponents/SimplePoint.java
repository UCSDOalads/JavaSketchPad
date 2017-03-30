package paintcomponents;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import settings.Defaults;
import ui.PaintPanel;

public class SimplePoint extends PaintComponent {


	private int radius;
	
	private Color color;
	private Color selectedColor;
	
	public SimplePoint(int x, int y){
		this(x, y, Defaults.sharedDefaults().defaultSimplePointSize(),
				Defaults.sharedDefaults().defaultSimplePointColor(),
				Defaults.sharedDefaults().defaultSimplePointSelectedColor());
	}

	public SimplePoint(int x, int y, int radius, Color color, Color selectedColor) {
		super(x, y);
		this.radius = radius;
		this.color = color;
		this.selectedColor = selectedColor;
	}

	/**
	 * Returns a deep copy of this object
	 * @param p
	 */
	public SimplePoint(SimplePoint p) {
		this(p.getX(), p.getY(), p.radius, p.color, p.selectedColor);
	}


	@Override
	protected void paintNotSelected(Graphics g) {
		g.setColor(color);
		g.fillOval(this.getX()- this.radius/2, this.getY() - this.radius/2, radius, radius);
	}

	@Override
	protected void paintSelected(Graphics g) {
		g.setColor(selectedColor);
		g.fillOval(this.getX()- this.radius/2, this.getY() - this.radius/2, radius, radius);
		
	}

	@Override
	public boolean contains(int x, int y){
		return new Rectangle(this.getX()- this.radius/2, this.getY() - this.radius/2, radius, radius).contains(x, y);
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		//list all tags
		Element main = doc.createElement("simplepoint");
		Element radiusElem = doc.createElement("radius");
		Element colorElem = doc.createElement("color");
		Element selectedColorElem = doc.createElement("selectedcolor");
		
		//fill the data
		radiusElem.appendChild(doc.createTextNode(Integer.toString(radius)));
		XMLEncodingUtilForPaintComponents.attachRGB(color, colorElem, doc);
		XMLEncodingUtilForPaintComponents.attachRGB(selectedColor, selectedColorElem, doc);

		//structure
		main.appendChild(radiusElem);
		main.appendChild(colorElem);
		main.appendChild(selectedColorElem);

		rootElement.appendChild(main);
	}

	public SimplePoint(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		Element main = (Element) rootElement.getElementsByTagName("simplepoint").item(0);
		Element radiusElem = (Element) main.getElementsByTagName("radius").item(0);
		Element colorElem = (Element) main.getElementsByTagName("color").item(0);
		Element selectedColorElem = (Element) main.getElementsByTagName("selectedcolor").item(0);
		
		this.radius = Integer.parseInt(radiusElem.getTextContent());
		this.color = XMLEncodingUtilForPaintComponents.getRGB(colorElem);
		this.selectedColor = XMLEncodingUtilForPaintComponents.getRGB(selectedColorElem);

	}

	/**
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}


}
