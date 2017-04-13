package actions.global;

import ui.PaintPanel;

/**
 * Every instance uses the executer to execute an action from the action factory.
 * @author chenzb
 *
 */
public class GlobalPaintActionExecuter {
	
	private static GlobalPaintActionExecuter sharedInstance = new GlobalPaintActionExecuter();

	
	public void execute(GlobalPaintAction<?> action, PaintPanel panel){
		action.execute(panel); 
	}


	/**
	 * @return the sharedInstance
	 */
	public static GlobalPaintActionExecuter getSharedInstance() {
		return sharedInstance;
	}


	
}
