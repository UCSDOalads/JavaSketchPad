package painttools.tools;
import java.awt.Panel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.text.html.HTMLEditorKit.InsertHTMLTextAction;
import javax.xml.stream.events.StartDocument;

import paintcomponents.PaintComponent;
import ui.PaintPanel;

public abstract class PaintTool<T extends PaintComponent> implements MouseListener, MouseMotionListener {

	
	//if this tool has finished
	private boolean done;
	
	/**
	 * Set the start condition of this paint tool
	 */
	public abstract void start(PaintPanel panel);
	
	
	/**
	 * the completed component
	 * @return
	 */
	public abstract T paintedComponent();

	/**
	 * @return whether this component has done painting
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * sets the done condition
	 * @param 
	 */
	public void setDone(boolean done) {
		this.done = done;
	}

	public JButton getButton() {
		return new JButton(this.getClass().getName());
	}
}
