package paintcomponents;

import java.awt.Color;
import java.awt.Graphics;

import settings.Defaults;

public class DataInputTextfieldPaintComponent extends TextPaintComponent {

	DataFromPoint<String> fromPoint;
	Color boundColor;
	Color selectedColor;
	

	public DataInputTextfieldPaintComponent(String displayingText, int x,
			int y) {
		super(displayingText, x, y);
		fromPoint = new DataFromPoint<>(x - 50, y);
		boundColor = Defaults.sharedDefaults().defaultColorForDataInputTextfield();
		selectedColor = Defaults.sharedDefaults().defaultColorForSelectedDataInputTextfield();
	}
	
	

	@Override
	protected void paintNotSelected(Graphics g) {
		super.paintNotSelected(g);
		g.setColor(boundColor);
		g.drawRect(getX(), getY(), (int)this.bounds.getWidth(), (int)this.bounds.getHeight());
		fromPoint.paintNotSelected(g);
		
	}

	@Override
	protected void paintSelected(Graphics g) {
		super.paintSelected(g);;
		g.setColor(selectedColor);
		g.drawRect(getX(), getY(), (int)this.bounds.getWidth(), (int)this.bounds.getHeight());
		fromPoint.paintSelected(g);
	}

	@Override
	public void translate(int i, int j) {
		super.translate(i, j);
		this.fromPoint.translate(i, j);
	}
	
	@Override
	public boolean contains(int x, int y) {

		return fromPoint.contains(x, y) || super.contains(x, y);
	}
}
