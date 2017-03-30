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
import painttools.tools.SelectTool;

public class PaintPanel extends JPanel implements ToolBarListener {

	public ArrayList<PaintComponent> components;

	
	enum State {
		TOOLS, DEFAULT
	}

	private State state = State.DEFAULT;
	private PaintTool tool;
	private PaintComponent tempComponent;

	private SelectTool selectTool;
	private KeyHandler keyHandler;

	/**
	 * @return the tempComponent
	 */
	public PaintComponent getTempComponent() {
		return tempComponent;
	}

	/**
	 * Sets a temporary component. Temporary component is the compoennt this
	 * panel paints when tools are selected. When tools are not in operation,
	 * i.e. when user hits esc key, the temporary component is discarded The
	 * Point tool sets the temp component to a moving point on screen by calling
	 * this method in the tool's <code>start</code> method.
	 * 
	 * @param tempComponent
	 *            the tempComponent to set
	 */
	public void setTempComponent(PaintComponent tempComponent) {
		this.tempComponent = tempComponent;
	}

	private void setTool(PaintTool tool) {
		if (this.state == State.TOOLS) {
			resetTool();
		}
		this.tool = tool;
		this.tool.start(this);
		this.state = State.TOOLS;
		this.addMouseListener(this.tool);
		this.addMouseMotionListener(this.tool);
	}

	private void resetTool() {

		this.tempComponent = null;
		this.removeMouseListener(this.tool);
		this.removeMouseMotionListener(this.tool);
		this.tool.reset();
		this.tool = null;
		showCursor();
		this.state = State.DEFAULT;
		repaint();
	}

	public PaintPanel() {
		requestFocusInWindow();
		
		this.components = new ArrayList<>();
		this.keyHandler = new KeyHandler(this);
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
					keyHandler.keyPressed(e);
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
			if (tempComponent != null)
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
	
	public ArrayList<PaintComponent> getPaintComponents() {
		return components;
	}

	/**
	 * Returns the topmost component under a given point
	 * 
	 * @param x
	 * @param y
	 * @return null if there is no component under current point
	 */
	public PaintComponent componentUnderPoint(int x, int y) {
		for (PaintComponent paintComponent : components) {
			if (paintComponent.contains(x, y)) {
				return paintComponent;
			}
		}
		return null;
	}

	/**
	 * Gets the select tool for this panel
	 * 
	 * @return
	 */
	public SelectTool getSelectTool() {
		return selectTool;
	}

	/**
	 * Set the select tool for this panel
	 * 
	 * @param selectTool
	 */
	public void setSelectTool(SelectTool selectTool) {
		this.selectTool = selectTool;
	}

}
