package ui;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import paintcomponents.PaintComponent;
import painttools.toolbar.ToolBarListener;
import painttools.tools.PaintTool;

public class PaintPanel extends JPanel implements ToolBarListener {

	public ArrayList<PaintComponent> components;

	enum State {
		TOOLS, DEFAULT
	}

	private State state = State.DEFAULT;
	private PaintTool tool;
	private PaintComponent tempComponent;

	/**
	 * @return the tempComponent
	 */
	public PaintComponent getTempComponent() {
		return tempComponent;
	}

	/**
	 * @param tempComponent the tempComponent to set
	 */
	public void setTempComponent(PaintComponent tempComponent) {
		this.tempComponent = tempComponent;
	}

	private void setTool(PaintTool tool) {
		if(this.state == State.TOOLS){
			resetTool();
		}
		this.tool = tool;
		this.tool.start(this);
		hideCursor();
		this.state = State.TOOLS;
		this.addMouseListener(this.tool);
		this.addMouseMotionListener(this.tool);
	}

	private void resetTool() {

		this.tempComponent = null;
		this.removeMouseListener(this.tool);
		this.removeMouseMotionListener(this.tool);
		this.tool = null;
		showCursor();
		this.state = State.DEFAULT;
		repaint();
	}

	public PaintPanel() {
		requestFocusInWindow();
		this.components = new ArrayList<>();
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocusInWindow();

				
			}


			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {

			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_ESCAPE:
						resetTool();
						break;

					default:
						break;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (PaintComponent paintComponent : components) {
			paintComponent.paint(g);
		}
		if (state == State.TOOLS) {
			tempComponent.paint(g);
		}
	}

	@Override
	public void toolSelected(PaintTool tool) {
		this.setTool(tool);

	}

	public void hideCursor() {
		setCursor(getToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB),
				new Point(), null));
	}
	public void showCursor() {
		setCursor(Cursor.getDefaultCursor());
	}

	public void addPaintComponent(PaintComponent comp) {
		components.add(comp);
		
	}
}