package actions.singleinstanceoperations;

import java.awt.Paint;

import actions.MenuBarPaintAction;
import actions.global.ActionName;
import actions.global.GlobalPaintAction;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.SingleInstanceOperationGlobalAction;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

/**
 * Single Instance Operation defines operation on a single instance.
 * 
 * i.e., 1. One and Only One Instance must be selected to perform operation.
 * 
 * 2. The operation only changes the instance itself but nothing else
 *
 *
 * After updating, the subclass do not need to call paintcomponent.repaint
 * method
 * 
 * 
 * @author chenzb
 *
 */
public abstract class SingleInstanceOperation<T extends PaintComponent> extends
		MenuBarPaintAction {

	public SingleInstanceOperation(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		PaintComponent paintComponent = panel.getSelectTool()
				.getSelectedComponents().get(0);

		// return if the object is kind of the desired class
		return getGenericClassType().isInstance(paintComponent);

	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		PaintComponent comp = panel.getSelectTool().getSelectedComponents()
				.get(0);

		// retrieve the right action to execute, and use executer to execute the
		// instance
		SingleInstanceOperationGlobalAction<GlobalPaintAction<?>> executingAction = getExecutingAction()
				.getAssiciatedAction();
		executingAction.setOperatingInstance(comp);
		GlobalPaintActionExecuter.getSharedInstance().execute(executingAction,
				panel);
		panel.repaint();
	}

	protected abstract ActionName getExecutingAction();

	/**
	 * Due to constraint in Java API, subclass MUST override this method
	 * 
	 * with method body :: <code>return T.class;</code>
	 * 
	 * @return
	 */
	protected abstract Class<T> getGenericClassType();

}
