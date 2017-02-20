package icons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.Icon;

import paintcomponents.PaintComponent;
public class LeftArrow{
	
	
	/**
	 * Constructs a PaintComponent from a specific polygon
	 * @param poly
	 * @param color
	 * @return
	 */
	public static PaintComponent paintComponentFromPolygon(Polygon poly, Color color)
	{
		return new PaintComponent(0, 0) {
			
			@Override
			protected void paintSelected(Graphics g) {
				paintNotSelected(g);
			}
			
			@Override
			protected void paintNotSelected(Graphics g) {
				g.setColor(color);
				
				//translates y to the correct position
				int dx = (int) (getX() - poly.getBounds().getX());
				int dy = (int) (getY() - poly.getBounds().getY());
				//center arrow, by shifting y up by half of the bounds.height
				dy -= poly.getBounds().getHeight() / 2;
				//shift the arrow to the right a little bit
				dx += poly.getBounds().getWidth() / 8;
				poly.translate(dx, dy);
				g.fillPolygon(poly);
			}
			
			

			@Override
			public boolean contains(int x2, int y2) {
				return poly.getBounds().contains(x2, y2);
			}
		};
		
	}
	
	/**
	 * Generate an Icon from polygon
	 * it will fill polygon with 10 default offset
	 * @param poly the polygon object
	 * @param color the color of the icon
	 * @return
	 */
	public static Icon iconFromPolygon(Polygon poly, Color color){
		return new Icon() {
			
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				//translates y to the correct position
				int dx = (int) (x - poly.getBounds().getX());
				int dy = (int) (y - poly.getBounds().getY());
				//center arrow, by shifting y up by half of the bounds.height
				dy += poly.getBounds().getHeight() / 2;
				//shift the arrow to the right a little bit
				dx += poly.getBounds().getWidth() / 4;
				g.setColor(color);
				g.fillPolygon(poly);
				
			}
			
			@Override
			public int getIconWidth() {
				return (int) poly.getBounds().getWidth();
			}
			
			@Override
			public int getIconHeight() {
				return (int) poly.getBounds().getHeight();
			}
		};
		
	}


	public static Polygon getPolygon(){

		Polygon p = new Polygon();

		p.addPoint(8, 0);
		p.addPoint(0, 9);
		p.addPoint(8, 20);
		p.addPoint(8, 17);
		p.addPoint(8, 14);
		p.addPoint(40, 15);
		p.addPoint(40, 8);
		p.addPoint(8, 7);
		p.addPoint(8, 1);


		return p;
	}

}