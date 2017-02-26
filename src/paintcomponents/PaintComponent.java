package paintcomponents;

import java.awt.Graphics;
import java.awt.Rectangle;

import painttools.tools.SelectTool;

/**
 * Abstracts the behavior of a paint component.
 * 
 * Override translate method if you want to customize tranlation. Override
 * select and deselect method to perform additional selections.
 * 
 * 
 * You should generally not override paint, toggleSelect as the default
 * implementation delegates to other methods that you have to.
 * 
 * MAKE SURE YOU CALL SUPER when overriding non-abstract methods.
 * 
 * @author chenzb
 *
 */
public abstract class PaintComponent {

	private int x;
	private int y;
	private boolean selected;

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	public PaintComponent(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Paints this component using a graphics object
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		if (selected) {
			paintSelected(g);
		} else {
			paintNotSelected(g);
		}

	}

	/**
	 * Paint the non-select version of this paint object
	 * 
	 * @param g
	 */
	protected abstract void paintNotSelected(Graphics g);

	/**
	 * Paints the selected version of this paint object
	 * 
	 * @param g
	 */
	protected abstract void paintSelected(Graphics g);

	/**
	 * Set the state of this object to be selected state
	 * 
	 * @param selectTool
	 *            TODO
	 */
	public void select(SelectTool selectTool) {
		selected = true;
		if (selectTool != null)
			selectTool.getSelectedComponents().add(this);
	}

	/**
	 * Set the state of this object to be unselected
	 * 
	 * @param selectTool
	 *            TODO
	 */
	public void deselect(SelectTool selectTool) {
		selected = false;
		if (selectTool != null)
			selectTool.getSelectedComponents().remove(this);
	}

	/**
	 * toggle the selection status of this paintable object
	 * 
	 * @param selectTool
	 *            TODO
	 */
	public void toggleSelect(SelectTool selectTool) {
		if (isSelected()) {
			deselect(selectTool);
		} else {
			select(selectTool);
		}
	}

	/**
	 * Check if this object is selected
	 * 
	 * @return
	 */
	public boolean isSelected() {
		return selected;
	}

	public void translate(int i, int j) {
		this.x += i;
		this.y += j;

	}

	public abstract boolean contains(int x2, int y2);

}
