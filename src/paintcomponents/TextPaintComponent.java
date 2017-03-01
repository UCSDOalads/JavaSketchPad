package paintcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import settings.Defaults;


/**
 * Text components displays a block of texts dilimited by \n characters
 * Use get rowHeight and get width to fetch the row height and width after calling the paint method
 * @author chenzb
 *
 */
public class TextPaintComponent extends PaintComponent{
	
	public String displayingText;
	
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
		this.defaultTextColor = Defaults.sharedDefaults().defaultColorForTextPaintComponent();
		this.selectedTextColor = Defaults.sharedDefaults().defaultColorForSelectedTextPaintComponent();
		this.fontSize = Defaults.sharedDefaults().defaultFontSizeForPaintComponent();
	}
	

	@Override
	protected void paintNotSelected(Graphics g) {
		g.setFont(g.getFont().deriveFont(fontSize));
		g.setColor(defaultTextColor);

		//draw string starts from bottom left corner, shift to top left
		updateBoundsAndDrawString(g);
	}
	private void updateBoundsAndDrawString(Graphics g) {
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
			
			g.drawString(string, getX(), (int) (getY() + rowHeight * (i + 1)));
		}
	}

	//IMPORTANT :: THIS COMPONENT IS SHIFTED DOWN, WHICH CAUSES PROBLEMS. WHEN FIXING COORDINATES, 
	//WE SHOULD COME UP WITH A COMMON SOLUTION
	//THIS IS NOT A BUG OR AN ERROR

	@Override
	protected void paintSelected(Graphics g) {
		g.setFont(g.getFont().deriveFont(fontSize));
		g.setColor(selectedTextColor);

		//draw string starts from bottom left corner, shift to top left
		updateBoundsAndDrawString(g);
	}

	@Override
	public boolean contains(int x2, int y2) {
		
		return getBounds().contains(x2, y2);
		
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


}
