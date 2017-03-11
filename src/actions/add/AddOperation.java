package actions.add;

import paintcomponents.PaintComponent;
import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import ui.PaintPanel;
import actions.PaintAction;

/**
 * Subcalss should override getPaintComponentToAdd to provide an adder should the action be performed
 * @author cs12waft
 *
 * @param <T>
 */
public abstract class AddOperation<T extends PaintComponent> extends PaintAction {

	public AddOperation(PaintPanel panel) {
		super(panel);
			}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		PaintComponent consComp = getPaintComponentToAdd();
		consComp.translate(panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(consComp);
		panel.repaint();
	}

	protected abstract  T getPaintComponentToAdd();
		
	

}
