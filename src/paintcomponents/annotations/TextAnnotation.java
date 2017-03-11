package paintcomponents.annotations;

import java.awt.Graphics;

import paintcomponents.PaintComponent;

public class TextAnnotation extends PaintComponentAnnotation {

	public TextAnnotation(PaintComponent attachedComponent) {
		super(attachedComponent);
	}

	@Override
	protected void paintNotSelected(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void paintSelected(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(int x2, int y2) {
		// TODO Auto-generated method stub
		return false;
	}

}
