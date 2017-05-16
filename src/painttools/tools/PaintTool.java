package painttools.tools;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import buttons.ToolButton;
import ui.PaintPanel;

public abstract class PaintTool implements MouseListener, MouseMotionListener {

	
	/**
	 * Set the start condition of this paint tool
	 */
	public abstract void start(PaintPanel panel);
	
	/**
	 * Resets all the pending states, called when a new tool is selected
	 * or when esc is pressed.
	 */
	public abstract void reset();
	

	public ToolButton getButton() {
		return new ToolButton(this.getClass().getName());
	}
}
