package actions;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.global.globalactions.ExecuteInstanceConstructorGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.InstanceOperationComponent;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

public class ExecuteInstanceConstructorAction extends MenuBarPaintAction {

	public ExecuteInstanceConstructorAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents()
				.get(0) instanceof InstanceOperationComponent) {
			return true;
		}
		return false;
	}

	@Override
	public void performAction() {
		ExecuteInstanceConstructorGlobalAction  associatedAction 
			= (ExecuteInstanceConstructorGlobalAction) ActionName.EXECUTE_INSTANCE_CONSTRUCTOR_ACTION
				.getAssociatedAction();
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
		
		
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Lazy().Add().Execute_Instance_Constructor().toString();
	}
	
	
	
}
