package actions.singleinstanceoperations;

import actions.MenuBarPaintAction;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

/**
 * Single Instance Operation defines operation on a single instance. 
 * 
 * i.e., 
 * 1.
 * One and Only One Instance must be selected to perform operaion. 
 * 
 * 2. The
 * operation only changes the instance itself but nothing else
 *
 *
 * After updating, the subclass do not need to call paintcomponent.repaint method
 * 
 * 
 * @author chenzb
 *
 */
public abstract class SingleInstanceOperation<T> extends MenuBarPaintAction {

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
		
		
		//return if the object is kind of the desired class
		return getGenericClassType().isInstance(paintComponent);

	}

	@Override
	public void performAction() {
		@SuppressWarnings("unchecked")
		T comp = (T) panel.getSelectTool().getSelectedComponents().get(0);
		performActionOnInstance(comp);
		panel.repaint();
	}

	protected abstract void performActionOnInstance(T instance);
	
	/**
	 * Due to constraint in Java API, subclass MUST override this method 
	 * 
	 *  with method body :: <code>return T.class;</code>
	 * @return
	 */
	protected abstract Class<T> getGenericClassType();
		


}
