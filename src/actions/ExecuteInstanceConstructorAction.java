package actions;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.InstanceOperationComponent;
import ui.PaintPanel;

public class ExecuteInstanceConstructorAction extends PaintAction {

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
		InstanceOperationComponent insComp = 
				(InstanceOperationComponent)panel.getSelectTool().
				getSelectedComponents().get(0);
		insComp.executeConstructor();
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Lazy().Add().Execute_Instance_Constructor().toString();
	}
	
	
	
}
