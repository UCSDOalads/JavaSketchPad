package paintcomponents;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import settings.Defaults;

public class SimplePoint extends PaintComponent {


	private static final int DEFUALT_RADIUS = 10;
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
	public Rectangle getBounds() {
		return new Rectangle(this.getX()- this.radius/2, this.getY() - this.radius/2, radius, radius);
	}

}
