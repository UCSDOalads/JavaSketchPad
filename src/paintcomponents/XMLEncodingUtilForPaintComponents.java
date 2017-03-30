package paintcomponents;

import java.awt.Color;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLEncodingUtilForPaintComponents {
	
	private static final String TAG_NAME_BLUE = "blue";
	private static final String TAG_NAME_GREEN = "green";
	private static final String TAG_NAME_RED = "red";

	/**
	 * attach (append to child) RGB to the element, in the format <red>12</red> etc
	 * @param rgb
	 * @param elem
	 * @param doc
	 */
	public static void attachRGB(Color rgb, Element elem, Document doc){
		int r = rgb.getRed();
		int g = rgb.getGreen();
		int b = rgb.getBlue();
		
		//create element
		Element red = doc.createElement(TAG_NAME_RED);
		Element green = doc.createElement(TAG_NAME_GREEN);
		Element blue = doc.createElement(TAG_NAME_BLUE);
		
		//fill the content
		red.appendChild(doc.createTextNode(Integer.toString(r)));
		green.appendChild(doc.createTextNode(Integer.toString(g)));
		blue.appendChild(doc.createTextNode(Integer.toString(b)));
		
		//structure the content
		elem.appendChild(red);
		elem.appendChild(green);
		elem.appendChild(blue);
	}
	
	/**
	 * Constructs a rgb value from given document
	 * @param elem
	 * @return
	 */
	public static Color getRGB(Element elem){
		int r = Integer.parseInt(elem.getElementsByTagName(TAG_NAME_RED).item(0).getTextContent());
		int g = Integer.parseInt(elem.getElementsByTagName(TAG_NAME_GREEN).item(0).getTextContent());
		int b = Integer.parseInt(elem.getElementsByTagName(TAG_NAME_BLUE).item(0).getTextContent());
		return new Color(r, g, b);
		
	}

}
