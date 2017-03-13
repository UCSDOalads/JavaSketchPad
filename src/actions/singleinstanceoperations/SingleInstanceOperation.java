package actions.singleinstanceoperations;

import actions.PaintAction;
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
public abstract class SingleInstanceOperation<T> extends PaintAction {

	public SingleInstanceOperation(PaintPanel panel) {
		super(panel);
	}

	/**
	 * The default behavior for a change method, only one component is selected
	 * and that component's type matchs the desired type
	 */
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

	/**
	 * The implementation of this method delegates to the "PerformActionOnInstance"
	 * 
	 */
	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		T comp = (T) panel.getSelectTool().getSelectedComponents().get(0);
		performActionOnInstance(comp);
		panel.repaint();
	}

	/**
	 * This method is called when the action is triggered. 
	 * Subclasses need to override this method to do appropriate operations
	 * @param instance
	 */
	protected abstract void performActionOnInstance(T instance);
	
	/**
	 * Due to constraint in Java API, subclass MUST override this method 
	 * 
	 *  with method body :: <code>return T.class;</code>
	 * @return
	 */
	protected abstract Class<T> getGenericClassType();
		

}
