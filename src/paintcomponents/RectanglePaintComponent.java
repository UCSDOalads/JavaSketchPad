/**
 * 
 */
package paintcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		//list all tags
		Element main = doc.createElement("rectangle");
		Element widthElem = doc.createElement("width");
		Element heightElem = doc.createElement("height");
		Element nonSelecteColorElem = doc.createElement("nonSelectColor");
		Element selectColorElem = doc.createElement("selectColor");
		
		//fill the data
		widthElem.appendChild(doc.createTextNode(Integer.toString(width)));
		heightElem.appendChild(doc.createTextNode(Integer.toString(height)));
		XMLEncodingUtilForPaintComponents.attachRGB(nonSelectColor, nonSelecteColorElem, doc);
		XMLEncodingUtilForPaintComponents.attachRGB(selectColor, selectColorElem, doc);

		//structure
		main.appendChild(widthElem);
		main.appendChild(heightElem);
		main.appendChild(nonSelecteColorElem);
		main.appendChild(selectColorElem);

		rootElement.appendChild(main);
	}
	
	public RectanglePaintComponent(Element rootElement) {
		super(rootElement);
		Element main = (Element) rootElement.getElementsByTagName("rectangle").item(0);
		Element widthElem = (Element) main.getElementsByTagName("width").item(0);
		Element heightElem = (Element) main.getElementsByTagName("height").item(0);
		Element nonSelecteColorElem = (Element) main.getElementsByTagName("nonSelectColor").item(0);
		Element selectColorElem = (Element) main.getElementsByTagName("selectColor").item(0);
		
		this.width = Integer.parseInt(widthElem.getTextContent());
		this.height = Integer.parseInt(heightElem.getTextContent());
		this.nonSelectColor = XMLEncodingUtilForPaintComponents.getRGB(nonSelecteColorElem);
		this.selectColor = XMLEncodingUtilForPaintComponents.getRGB(selectColorElem);
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
