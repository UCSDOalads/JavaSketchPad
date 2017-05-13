package paintcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import settings.Defaults;
import ui.PaintPanel;


/**
 * Text components displays a block of texts dilimited by \n characters
 * Use get rowHeight and get width to fetch the row height and width after calling the paint method
 * 
 * 
 * When overriding save and read method, subclasses should be calling setDisplayingText if the text 
 * being displayed when saving is not the save as the recovery
 * @author chenzb
 *
 */
public class TextPaintComponent extends PaintComponent{
	
	private String displayingText;
	
	/**
	 * The height per row
	 */
	private int rowHeight;
	private int numberOfRows;
	/**
	 * The maximum width of displaying texts
	 */
	private int width;
	
	private Color defaultTextColor;
	private Color selectedTextColor;
	
	private float fontSize;
	
	public Rectangle getBounds(){
		return new Rectangle(getX(), getY(), width, rowHeight * numberOfRows);
	}

	public TextPaintComponent(String displayingText, int x, int y) {
		super(x, y);
		this.displayingText = displayingText;
		this.defaultTextColor = Defaults.sharedDefaults().defaultColorForTextPaintComponent();
		this.selectedTextColor = Defaults.sharedDefaults().defaultColorForSelectedTextPaintComponent();
		this.fontSize = Defaults.sharedDefaults().defaultFontSizeForPaintComponent();
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		
		//add elem
		Element main= doc.createElement("textpaintcomponent");
		Element displayingTextElem = doc.createElement("text");
		Element defaultTextColorElem = doc.createElement("color");
		Element selectedTextColorElem = doc.createElement("selectedcolor");
		Element fontSizeElem = doc.createElement("fontsize");
		
		//fill data
		displayingTextElem.appendChild(doc.createTextNode(this.displayingText));
		XMLEncodingUtilForPaintComponents.attachRGB(defaultTextColor, defaultTextColorElem, doc);
		XMLEncodingUtilForPaintComponents.attachRGB(selectedTextColor, selectedTextColorElem, doc);
		fontSizeElem.appendChild(doc.createTextNode(Float.toString(fontSize)));
		
		//build relationship
		main.appendChild(displayingTextElem);
		main.appendChild(defaultTextColorElem);
		main.appendChild(selectedTextColorElem);
		main.appendChild(fontSizeElem);
		rootElement.appendChild(main);
		
	}
	

	public TextPaintComponent(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		Element main = (Element) rootElement.getElementsByTagName("textpaintcomponent").item(0);
		Element displayingTextElem = (Element) main.getElementsByTagName("text").item(0);
		Element defaultTextColorElem = (Element) main.getElementsByTagName("color").item(0);
		Element selectedTextColorElem =(Element) main.getElementsByTagName("selectedcolor").item(0);
		Element fontSizeElem = (Element) main.getElementsByTagName("fontsize").item(0);
		
		this.displayingText = displayingTextElem.getTextContent();
		this.defaultTextColor = XMLEncodingUtilForPaintComponents.getRGB(defaultTextColorElem);
		this.selectedTextColor = XMLEncodingUtilForPaintComponents.getRGB(selectedTextColorElem);
		this.fontSize = Float.parseFloat(fontSizeElem.getTextContent());
	}

	@Override
	protected void paintNotSelected(Graphics g) {
		g.setColor(defaultTextColor);

		//draw string starts from bottom left corner, shift to top left
		updateBoundsAndDrawString(g);
	}
	private void updateBoundsAndDrawString(Graphics g) {
		updateBounds(g);
		drawString(g);
		
	}

	/**
	 * Update bounds updates the width and row height of this text paint component
	 * @param g Graphics Object
	 */
	public void updateBounds(Graphics g) {
		g.setFont(g.getFont().deriveFont(fontSize));

		//reset bounds to begin calculation
		width = 0;
		//derive row Height
		Graphics2D g2 = (Graphics2D) g;
		rowHeight = g2.getFontMetrics().getHeight();
		
		//get number of rows
		String[] rows = this.displayingText.split("\n");
		numberOfRows = rows.length;
		
		for (int i = 0; i < rows.length; i++) {
			String string = rows[i];
			//update width
			int curStringWidth = g2.getFontMetrics().stringWidth(string);
			if( curStringWidth > width){
				width = curStringWidth;
			}
		}
	}
	
	/**
	 * Draws the string, do not update bounds
	 * @param g
	 */
	private void drawString(Graphics g){
		
		//get number of rows
		String[] rows = this.displayingText.split("\n");
		
		for (int i = 0; i < rows.length; i++) {
			String string = rows[i];
			
			g.drawString(string, getX(), (int) (getY() + rowHeight * (i + 1)));
		}
	}

	//IMPORTANT :: THIS COMPONENT IS SHIFTED DOWN, WHICH CAUSES PROBLEMS. WHEN FIXING COORDINATES, 
	//WE SHOULD COME UP WITH A COMMON SOLUTION
	//THIS IS NOT A BUG OR AN ERROR

	@Override
	protected void paintSelected(Graphics g) {
		g.setColor(selectedTextColor);

		//draw string starts from bottom left corner, shift to top left
		updateBoundsAndDrawString(g);
	}

	@Override
	public boolean contains(int x2, int y2) {
		
		return getBounds().contains(x2, y2);
		
	}

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

	/**
	 * @return the defaultTextColor
	 */
	public Color getDefaultTextColor() {
		return defaultTextColor;
	}

	/**
	 * @param defaultTextColor the defaultTextColor to set
	 */
	public void setDefaultTextColor(Color defaultTextColor) {
		this.defaultTextColor = defaultTextColor;
	}

	/**
	 * @return the selectedTextColor
	 */
	public Color getSelectedTextColor() {
		return selectedTextColor;
	}

	/**
	 * @param selectedTextColor the selectedTextColor to set
	 */
	public void setSelectedTextColor(Color selectedTextColor) {
		this.selectedTextColor = selectedTextColor;
	}

	/**
	 * @return the fontSize
	 */
	public float getFontSize() {
		return fontSize;
	}

	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @return the rowHeight
	 */
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * @return the numberOfRows
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 */
	public String getStringContent(){
		return displayingText;
	}

}
