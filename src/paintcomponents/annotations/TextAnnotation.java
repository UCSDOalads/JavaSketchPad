package paintcomponents.annotations;

import java.awt.Graphics;

import paintcomponents.PaintComponent;
import paintcomponents.TextPaintComponent;

public class TextAnnotation extends PaintComponentAnnotation {

	private TextPaintComponent textPaintComponent;
	
	
	public TextAnnotation(PaintComponent attachedComponent, String displayingText) {
		super(attachedComponent);
		this.textPaintComponent = new TextPaintComponent(displayingText, attachedComponent.getX(),
				attachedComponent.getY());
		
	}

	@Override
	protected void paintNotSelected(Graphics g) {
		// TODO Auto-generated method stub
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

	@Override
	public boolean contains(int x2, int y2) {
		// TODO Auto-generated method stub
		return textPaintComponent.contains(x2, y2);
	}

	public void translate(int x, int y){
		textPaintComponent.translate(x, y);
	}
	
	
}
