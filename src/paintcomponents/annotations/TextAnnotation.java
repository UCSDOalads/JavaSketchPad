package paintcomponents.annotations;

import java.awt.Graphics;

import paintcomponents.PaintComponent;
import paintcomponents.TextPaintComponent;

/**
 * the text annotation of the component
 * @author muchi
 *
 */
public class TextAnnotation extends PaintComponentAnnotation {

	private TextPaintComponent textPaintComponent;
	
	/**
	 * create the annotation of a component with information
	 * 
	 * @param attachedComponent the component attached to the annotation
	 * @param displayingText the information to be displayed
	 */
	public TextAnnotation(PaintComponent attachedComponent, String displayingText) {
		super(attachedComponent);
		this.textPaintComponent = new TextPaintComponent(displayingText, attachedComponent.getX(),
				attachedComponent.getY());		
	}

	@Override
	protected void paintNotSelected(Graphics g) {
		textPaintComponent.updateBounds(g);
		textPaintComponent.setY(getAttachedComponent().getY() - textPaintComponent.getRowHeight());
		textPaintComponent.paint(g);
	}

	@Override
	protected void paintSelected(Graphics g) {
		// TODO Auto-generated method stub
		textPaintComponent.updateBounds(g);
		textPaintComponent.setY(getAttachedComponent().getY() - textPaintComponent.getRowHeight());
		textPaintComponent.paint(g);
	}

	/**
	 * @return true if the given position is contained, otherwise false
	 */
	@Override
	public boolean contains(int x2, int y2) {
		// TODO Auto-generated method stub
		return textPaintComponent.contains(x2, y2);
	}

	/**
	 * move the text
	 * 
	 * @param x the movement on x-position
	 * @param y the movement on y-position
	 */
	public void translate(int x, int y){
		textPaintComponent.translate(x, y);
	}

	/**
	 * set the font size of the given annotation
	 * 
	 * @param input the size
	 */
	@Override
	public void setFontSize(float input) {
		textPaintComponent.setFontSize(input);
	}
}
