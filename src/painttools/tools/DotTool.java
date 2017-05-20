package painttools.tools;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.Icon;
import javax.swing.JButton;

import paintcomponents.SimplePoint;
import ui.PaintPanel;

public class DotTool extends PaintTool {
	
	SimplePoint p;
	private PaintPanel panel;

	@Override
	public void start(PaintPanel panel) {
		this.panel = panel;
		
		panel.hideCursor();
		p = new SimplePoint(0, 0);
		panel.setTempComponent(p);
		
	}


	
	@Override
	public JButton getButton() {
		JButton button = super.getButton();
		button.setIcon(new Icon() {
			
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				g.fillOval(10, 10, 20, 20);
				
			}
			
			@Override
			public int getIconWidth() {
				return 40;
			}
			
			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return 40;
			}
		});
		return button;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		SimplePoint pointToAdd = new SimplePoint(p);
		
		//change selection to be only the added point
		panel.getSelectTool().clearSelection();
		panel.getSelectTool().selectComponent(pointToAdd);

		panel.addPaintComponent(pointToAdd);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		p.setX(e.getX());
		p.setY(e.getY());
		panel.repaint();
	}



	@Override
	public void reset() {
		//do nothing on reset
		//temporary component will automatically be removed
		
		
	}



	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
