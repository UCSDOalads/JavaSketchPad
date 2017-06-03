package painttools.tools;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import buttons.ToolButton;
import ui.PaintPanel;

public interface PaintToolsInterface extends MouseListener, MouseMotionListener, MouseWheelListener {

	
	/**
	 * Set the start condition of this paint tool
	 */
	public abstract void start(PaintPanel panel);
	
	/**
	 * Resets all the pending states, called when a new tool is selected
	 * or when esc is pressed.
	 */
	public abstract void reset();

	public ToolButton getButton();
	
	public void createButton();
}
