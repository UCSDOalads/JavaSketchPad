package painttools.tools;
import java.awt.Panel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.text.html.HTMLEditorKit.InsertHTMLTextAction;
import javax.xml.stream.events.StartDocument;

import paintcomponents.PaintComponent;
import ui.PaintPanel;

public abstract class PaintTool implements MouseListener, MouseMotionListener, MouseWheelListener {

	
	/**
	 * Set the start condition of this paint tool
	 */
	public abstract void start(PaintPanel panel);
	
	/**
	 * Resets all the pending states, called when a new tool is selected
	 * or when esc is pressed.
	 */
	public abstract void reset();
	

	public JButton getButton() {
		return new JButton(this.getClass().getName());
	}
}
